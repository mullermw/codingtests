package codeeval.lowestcommonancestor;

import java.io.*;
public class Main {

    private static class Node {

        Node (int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        final int value;
        final Node left,right;
    }

    private static final Node tree;
    static {
        Node node0, node1, node2;
        node0 = new Node(10,null,null);
        node1 = new Node(29,null,null);
        node2 = new Node(20, node0, node1);

        node0 = new Node(3, null, null);
        node1 = new Node(8, node0, node2);

        node0 = new Node(52, null, null);
        node2 = new Node(30, node1, node0);

        tree = node2;
    }


    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] fields = line.split("\\s+");
            int i0 = Integer.parseInt(fields[0]);
            int i1 = Integer.parseInt(fields[1]);
            System.out.println(findAncestor(i0, i1));
        }
    }

    private static int findAncestor(int first, int second) {
        Node node = tree;
        while (node != null) {
            if (node.value == first) return first;  //first must be an ancestor of second
            if (node.value == second) return second; //second must be an ancestor of first

            //check for divergence:
            Node path0 = first < node.value ? node.left : node.right;
            Node path1 = second < node.value ? node.left : node.right;
            assert path0 != null;
            assert path1 != null;
            if (path0 != path1) {
                return node.value; //divergence
            }
            node = path0;
        }
        throw new RuntimeException("Fatal error"); //should never happen
    }
}