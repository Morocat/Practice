package com.msi;

import java.util.Arrays;

public class TreeNode {
    public TreeNode left, right;
    public byte[] val;
    public int occurences;
    public int numChildren;

    public TreeNode() {
    }

    public TreeNode(byte[] val) {
        this.val = Arrays.copyOf(val, val.length);
    }

    @Override
    public String toString() {
        return new String(val);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        return o instanceof TreeNode
                && Arrays.equals(val, ((TreeNode) o).val);
    }
}
