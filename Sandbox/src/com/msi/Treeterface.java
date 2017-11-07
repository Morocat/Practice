package com.msi;

public interface Treeterface {
	void addNode(int data);
	void deleteNode(int data);
	boolean contains(int data);
	TreeNode getNode(int data);
	void mirror();
	void printDepth();
	void printBreadth(TreeNode root) throws InterruptedException;
	boolean isBalanced();
	TreeNode leftRotation(TreeNode node);
	TreeNode rightRotation(TreeNode node);
	TreeNode leftRightRotation(TreeNode node);
	TreeNode rightLeftRotation(TreeNode node);
	
}
