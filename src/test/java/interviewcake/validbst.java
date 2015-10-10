package interviewcake;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Stack;

import static java.util.Objects.requireNonNull;

/**
 * Created by mullermw on 9/4/15.
 */
public class validbst {

    public static class Node<T extends Comparable<T>> {
        Node left, right;
        final T value;

        public Node(T value) {
            this.value = requireNonNull(value);
        }
    }

    public static void main(String[] args) {
        Node node = new Node(10);
        node.left = new Node(5);
        node.right = new Node(15);
        node.left.left = new Node(3);
        node.left.right = new Node(8);
        node.right.left = new Node(12);
        node.right.left.left = new Node(1);
        System.out.println(validBst(node));
    }

    public static <T extends Comparable<T>> boolean validBst(Node<T> root) {
        Objects.requireNonNull(root);
        Stack<Entry<Node<T>,Entry<T,T>>> stack = new Stack<>(); //Node, minAllowedValue, maxAllowedValue
        stack.push(entry(root, entry(null, null)));
        while (!stack.isEmpty()) {
            Entry<Node<T>, Entry<T,T>> entry = stack.pop();
            Node<T> node = entry.getKey();
            T min = entry.getValue().getKey();
            T max = entry.getValue().getValue();
            if ( (min != null && node.value.compareTo(min) <  1 ) ||
                 (max != null && node.value.compareTo(max) > -1 )    ) {
                return false;
            }
            if (node.left != null) {
                stack.push(entry(node.left, entry(min, node.value)));
            }
            if (node.right != null) {
                stack.push(entry(node.right, entry(node.value, max)));
            }
        }
        return true;
    }

    private static <K,V> Entry<K,V> entry(K k, V v) {
        return new AbstractMap.SimpleImmutableEntry<>(k, v);
    }

}
