package com.codersongs.datastructure.tree;

/**
 * 二叉树结点类
 * @author song
 *
 * @param <T>
 */
public class BinaryNode<T> {
	private T data;
	private BinaryNode<T> leftChild;
	private BinaryNode<T> rightChild;
	
	public BinaryNode() {
		this(null);
	}

	public BinaryNode(T dataPortion) {
		this.data = dataPortion;
	}

	public BinaryNode(T dataPortion, BinaryNode<T> newLeftChild, BinaryNode<T> newRightChild) {
		this.data = dataPortion;
		this.leftChild = newLeftChild;
		this.rightChild = newRightChild;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(BinaryNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public BinaryNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinaryNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean hasLeftChild(){
		return leftChild != null;
	}
	
	public boolean hasRightChild(){
		return rightChild != null;
	}
	
	public boolean isLeaf(){
		return (leftChild == null) && (rightChild == null);
	}
	
	/**
	 * @return
	 */
	public int getNumberOfNodes(){
		int leftNumber = 0;
		int rightNumber = 0;
		if (leftChild != null) {
			leftNumber = leftChild.getNumberOfNodes();
		}
		if (rightChild != null) {
			rightNumber = rightChild.getNumberOfNodes();
		}
		return 1 + leftNumber + rightNumber;
	}
	
	/**
	 * @return
	 */
	public int getHeight(){
		return getHeight(this);
	}
	
	private int getHeight(BinaryNode<T> node){
		int height = 0;
		if (node != null) {
			height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
		}
		return height;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public BinaryNode<T> copy(){
		BinaryNode<T> newRoot = new BinaryNode<>(data);
		if (leftChild != null) {
			newRoot.setLeftChild(leftChild.copy());
		}
		if (rightChild != null) {
			newRoot.setRightChild(rightChild.copy());
		}
		return newRoot;
	}
	
}
