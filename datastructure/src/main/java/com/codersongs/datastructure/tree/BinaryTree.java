package com.codersongs.datastructure.tree;

import java.util.Iterator;
/**
 * 实现二叉树
 * @author song
 *
 * @param <T>
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
	private BinaryNode<T> root;
	
	public BinaryTree() {
		root = null;
	}
	
	public BinaryTree(T rootData) {
		this.root = new BinaryNode<>(rootData);
	}
	
	public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		privateSetTree(rootData, leftTree, rightTree);
	}
	
	@Override
	public T getRootData() {
		if (isEmpty()) {
			throw new EmptyTreeException();
		}
		return root.getData();
	}
	
	public void setRootData(T rootData){
		root.setData(rootData);
	}
	
	public void setRootNode(BinaryNode<T> rootNode){
		root = rootNode;
	}
	
	public BinaryNode<T> getRootNode(){
		return root;
	}
	
	@Override
	public int getHight() {
		return root.getHight();
	}

	@Override
	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public Iterator<T> gerPreOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> gerPostOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> gerInOrderIterator() {
		return null;
	}
	
	@Override
	public Iterator<T> gerLevelOrderIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTree(T rootData) {
		root = new BinaryNode<>(rootData);
	}

	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
	}
	
	private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		root = new BinaryNode<>(rootData);
		if (leftTree != null && !leftTree.isEmpty()) {
			root.setLeftChild(leftTree.root);
		}
		if (rightTree != null && !rightTree.isEmpty()) {
			if (rightTree != leftTree) {
				root.setRightChild(rightTree.root);
			}else {
				root.setRightChild(leftTree.root.copy());
			}
			
		}
		if (leftTree != null && leftTree != this) {
			leftTree.clear();
		}
		if (rightTree != null && rightTree != this) {
			rightTree.clear();
		}
	}
	
	public void inorderTraverse(){
		inorderTraverse(root);
	}
	
	public void inorderTraverse(BinaryNode<T> node){
		if (node != null) {
			inorderTraverse(node.getLeftChild());
			System.out.println(node.getData());
			inorderTraverse(node.getRightChild());
		}
	}
	
	private class InorderIterator implements Iterator<T>{

		@Override
		public boolean hasNext() {
			return false;
		}
		
		@Override
		public T next() {
			return null;
		}
		
	} 
	
	public static void main(String[] args) {
		BinaryTree<String> binaryTree = new BinaryTree<String>();
		BinaryTree<String> fTree = new BinaryTree<String>("f", null, new BinaryTree<String>("g"));
		BinaryTree<String> bTree = new BinaryTree<String>("b", new BinaryTree<String>("d"), new BinaryTree<String>("e"));
		BinaryTree<String> cTree = new BinaryTree<String>("c", fTree, null);
		
		binaryTree.setTree("a", bTree, cTree);
		binaryTree.inorderTraverse();
	}
}
