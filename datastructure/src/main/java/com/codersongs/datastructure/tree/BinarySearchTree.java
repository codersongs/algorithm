package com.codersongs.datastructure.tree;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> implements SearchTreeInterface<T>{
	
	
	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(T rootEntity) {
		super(rootEntity);
	}
	
	/**
	 * disable setTree
	 * 只允许通过add方法添加元素，防止setTree方法破坏原有的结构，包括数据排序和平衡性
	 */
	@Override
	public void setTree(T rootData) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * disable setTree <br/>
	 * 只允许通过add方法添加元素，防止setTree方法破坏原有的结构，包括数据排序和平衡性
	 * 
	 */
	@Override
	public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean contains(T entry) {
		return false;
	}

	@Override
	public T getEntry(T entry) {
		return findEntry(getRootNode(), entry);
	}

	private T findEntry(BinaryNode<T> rootNode, T entry) {
		T result = null;
		if (rootNode != null) {
			T rootEntry = rootNode.getData();
			if (entry.equals(rootEntry)) {
				result = rootEntry;
			}else if (entry.compareTo(rootEntry) < 0) {
				result = findEntry(rootNode.getLeftChild(), entry);
			}else if (entry.compareTo(rootEntry) > 0) {
				result = findEntry(rootNode.getRightChild(), entry);
			}
		}
		
		return result;
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty()) {
			setRootNode(new BinaryNode<T>(newEntry)); 
		}else {
			result = addEntry(getRootNode(), newEntry);
		}
		return result;
	}
	
	/**
	 * 添加项递归实现
	 * @param rootNode
	 * @param newEntry
	 * @return
	 */
	private T addEntry(BinaryNode<T> rootNode, T newEntry) {
		assert rootNode != null;
		T result = null;
		int compareTo = newEntry.compareTo(rootNode.getData());
		
		if (compareTo == 0) {
			result = rootNode.getData();
			rootNode.setData(newEntry);
		}else if (compareTo < 0) {
			if (rootNode.hasLeftChild()) {
				result = addEntry(rootNode.getLeftChild(), newEntry);
			}else {
				rootNode.setLeftChild(new BinaryNode<T>(newEntry));
			}
		}else {
			if (rootNode.hasRightChild()) {
				result = addEntry(rootNode.getRightChild(), newEntry);
			}else {
				rootNode.setRightChild(new BinaryNode<T>(newEntry));
			}
		}
		
		return result;
	}
	
	/**
	 * 添加项迭代实现
	 * @return
	 */
	public T addEntry(T newEntry){
		BinaryNode<T> currentNode = getRootNode();
		assert currentNode != null;
		T result = null;
		boolean found = false;
		while (!found) {
			T currentData = currentNode.getData();
			int compareTo = newEntry.compareTo(currentData);
			if (compareTo == 0) {
				result = currentData;
				currentNode.setData(newEntry);
				found = true;
			}else if (compareTo < 0) {
				if (currentNode.hasLeftChild()) {
					currentNode = currentNode.getLeftChild();
				}else {
					currentNode.setLeftChild(new BinaryNode<T>(newEntry));
					found = true;
				}
			}else {
				if (currentNode.hasRightChild()) {
					currentNode = currentNode.getRightChild();
				}else {
					currentNode.setRightChild(new BinaryNode<T>(newEntry));
					found = true;
				}
			}
		}
		return result;
	}
	
	/**
	 * 递归实现
	 * remove entry
	 */
	@Override
	public T remove(T entry) {
		ReturnObject<T> oldEntry = new ReturnObject<T>(null);
		BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
		setRootNode(newRoot);
		return oldEntry.get();
	}
	
	/**
	 * 迭代实现
	 * @param entry
	 * @return
	 */
	public T removeIterator(T entry){
		return null;
	}
	
	/**
	 * removeEntry递归实现
	 * @param rootNode
	 * @param entry
	 * @param oldEntry
	 * @return
	 */
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject<T> oldEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int compareTo = entry.compareTo(rootData);
			if (compareTo == 0) {
				oldEntry.set(rootData);
				rootNode = removeFromRoot(rootNode);
			}else if (compareTo < 0) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> subTreeRoot = removeEntry(leftChild, entry, oldEntry);
				rootNode.setLeftChild(subTreeRoot);
			}else {
				BinaryNode<T> rightChild = rootNode.getRightChild();
				BinaryNode<T> subTreeRoot = removeEntry(rightChild, entry, oldEntry);
				rootNode.setRightChild(subTreeRoot);
			}
		}
		return rootNode;
	}
	
	/**
	 * 
	 * @param rootNode
	 * @return
	 */
	private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
			BinaryNode<T> leftSubTreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNode = findLargestNode(leftSubTreeRoot);
			
			rootNode.setData(largestNode.getData());
			rootNode.setLeftChild(removeLargest(leftSubTreeRoot));
		}else if (rootNode.hasRightChild()) {
			rootNode = rootNode.getRightChild();
		}else {
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}
	
	/**
	 * 
	 * @param leftSubTreeRoot
	 * @return
	 */
	private BinaryNode<T> removeLargest(BinaryNode<T> leftSubTreeRoot) {
		if (leftSubTreeRoot.hasRightChild()) {
			BinaryNode<T> rightChild = leftSubTreeRoot.getRightChild();
			rightChild = removeLargest(rightChild);
			leftSubTreeRoot.setRightChild(rightChild);
		}else {
			leftSubTreeRoot = leftSubTreeRoot.getLeftChild();
		}
		return leftSubTreeRoot;
	}
	
	/**
	 * 寻找左子树中的最大节点
	 * @param leftSubTreeRoot
	 * @return
	 */
	private BinaryNode<T> findLargestNode(BinaryNode<T> leftSubTreeRoot) {
		if (leftSubTreeRoot.hasRightChild()) {
			findLargestNode(leftSubTreeRoot.getRightChild());
		}
		return leftSubTreeRoot;
	}

	@Override
	public Iterator<T> getInorderIterator() {
		return null;
	}
	
}
