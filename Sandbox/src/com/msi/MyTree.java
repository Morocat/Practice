package com.msi;

import java.util.concurrent.ArrayBlockingQueue;

public class MyTree {
    private TreeNode root;

    public void addNode(byte[] data) {
        if (root == null) {
            root = new TreeNode(data);
            return;
        }
        TreeNode n = root;
        while(true) {
            if (cmpVal(data, n.val) == 1) {
                if (n.left == null) { // done
                    n.left = new TreeNode(data);
                    return;
                }
                n = n.left;
            } else if (cmpVal(data, n.val) == -1) {
                if (n.right == null) {
                    n.right = new TreeNode(data);
                    return;
                }
                n = n.right;
            } else {
                n.occurences++;
                return;
            }
        }
    }

    /**
     * Compares two strings represented by their byte arrays
     * @param lh String 1
     * @param rh String 2
     * @return 1 if rh comes after lh, -1 if rh comes before lh, otherwise 0 when equal
     */
    private int cmpVal(byte[] lh, byte[] rh) {
        if (lh.length != rh.length)
            throw new IllegalArgumentException("Parameters are unequal in length");
        for (int i = 0; i < lh.length; i++) {
            if (lh[i] < rh[i])
                return 1;
            if (lh[i] > rh[i])
                return -1;
        }
        return 0;
    }

    public void printDepth() {
        // in-traversal
        _printDepth(root);
        System.out.println("");
    }

    private void _printDepth(TreeNode n) {
        if (n == null)
            return;

        _printDepth(n.left);
        System.out.print(new String(n.val) + " ");
        _printDepth(n.right);
    }

    public void printBreadth() {
        if (root == null)
            return;
        TreeNode n = root;
        ArrayBlockingQueue<TreeNode> queue = new ArrayBlockingQueue<>(_treeSize(n));
        queue.offer(n);
        while(queue.size() > 0) {
            n = queue.poll();
            if (n.val != null)
                System.out.println(new String(n.val) + " " + n.occurences);
            if (n.left != null)
                queue.offer(n.left);
            if (n.right != null)
                queue.offer(n.right);
        }
        System.out.println("");
    }

    private int _treeSize(TreeNode n) {
        if (n == null)
            return 0;
        n.numChildren += _treeSize(n.left);
        n.numChildren += _treeSize(n.right);
        return n.numChildren + 1;
    }

}
