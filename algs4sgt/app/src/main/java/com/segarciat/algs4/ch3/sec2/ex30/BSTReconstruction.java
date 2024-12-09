package com.segarciat.algs4.ch3.sec2.ex30;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * <strong>3.2.30)</strong>
 * Implements algorithms to reconstruct a binary search tree from its preorder and
 * its postorder traversals.
 * @author Sergio E. Garcia Tapia
 */
public final class BSTReconstruction {
    public static <Key extends Comparable<Key>, Value> BST<Key, Value> bstFromPreorder(Queue<Key> preorderKeys, Queue<Value> preorderValues) {
        BST<Key, Value> bst = new BST<>();
        while (!preorderKeys.isEmpty()) {
            bst.put(preorderKeys.dequeue(), preorderValues.dequeue());
        }
        return bst;
    }

    public static <Key extends Comparable<Key>, Value> BST<Key, Value> bstFromPostorder(Queue<Key> postorderKeys, Queue<Value> postorderValues) {
        BST<Key, Value> bst = new BST<>();
        Stack<Key> postOrderKeysReversed = new Stack<>();
        for (Key key: postorderKeys)
            postOrderKeysReversed.push(key);

        Stack<Value> postOrderValuesReversed = new Stack<>();
        for (Value value: postorderValues)
            postOrderValuesReversed.push(value);

        while (!postOrderKeysReversed.isEmpty()) {
            bst.put(postOrderKeysReversed.pop(), postOrderValuesReversed.pop());
        }

        return bst;
    }
}
