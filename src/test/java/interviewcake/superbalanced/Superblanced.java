package interviewcake.superbalanced;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Stack;

/**
 * Created by mullermw on 9/4/15.
 */
public class Superblanced {

    public static class Node {
        Node left, right;
    }

    public static void main(String[] args) {
        Node node = new Node();
        node.left = new Node();
        node.right = new Node();
        node.right.right = new Node();
        node.right.right.left = new Node();
        System.out.println(isSuperbalanced(node));
    }

    public static boolean isSuperbalanced(Node node) {
        Objects.requireNonNull(node);
        Stack<Entry<Node,Integer>> nodes = new Stack<>();
        nodes.push(entry(node, 0));
        int minLeafDepth = Integer.MAX_VALUE;
        int maxLeafDepth = -1;
        while (!nodes.isEmpty()) {
            Entry<Node,Integer> entry = nodes.pop();
            Node nextNode = entry.getKey();
            int nextNodeDepth = entry.getValue();
            if (nextNode.left == null) {
                if (nextNode.right == null) {
                    //it's a leaf. //Check for a new depth
                    minLeafDepth = Math.min(minLeafDepth, nextNodeDepth);
                    maxLeafDepth = Math.max(maxLeafDepth, nextNodeDepth);
                    if (maxLeafDepth - minLeafDepth > 1) {
                        return false;
                    }
                } else {
                    nodes.push(entry(nextNode.right, nextNodeDepth+1));
                }
            } else {
                nodes.push(entry(nextNode.left, nextNodeDepth+1));
                if (nextNode.right != null) {
                    nodes.push(entry(nextNode.right, nextNodeDepth+1));
                }
            }
        }
        return true;
    }

    private static <K,V> Map.Entry<K,V> entry(K k, V v) {
        return new AbstractMap.SimpleImmutableEntry<>(k, v);
    }
}
