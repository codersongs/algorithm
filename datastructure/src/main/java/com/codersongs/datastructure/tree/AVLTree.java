package com.codersongs.datastructure.tree;

/**
 * 实现avl树
 * @author song
 *
 */
public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> implements SearchTreeInterface<T>{

	public AVLTree() {
		super();
	}

	public AVLTree(T rootEntity) {
		super(rootEntity);
	}
	
	/**
	 * Corrects an imbalance at the node closest to a structural change in the left subtree of the node's left child 
	 * @param nodeN is a node, closest to the newly added leaf, at which a imbalance occurs and that has a left child
	 * @return
	 */
	private BinaryNode<T> rotateRight(BinaryNode<T> nodeN){
		BinaryNode<T> nodeC = nodeN.getLeftChild();
		nodeN.setLeftChild(nodeC.getRightChild());
		nodeC.setRightChild(nodeN);
		return nodeC;
	}
	
	/**
	 * Corrects an imbalance at the node closest to a structural change in the right subtree of the node's right child 
	 * @param nodeN is a node, closest to the newly added leaf, at which a imbalance occurs and that has a right child
	 * @return
	 */
	private BinaryNode<T> rotateLeft(BinaryNode<T> nodeN){
		BinaryNode<T> nodeC = nodeN.getRightChild();
		nodeN.setRightChild(nodeC.getLeftChild());
		nodeC.setLeftChild(nodeN);
		return nodeC;
	}
	
	/**
	 * Corrects an imbalance at the node closest to a structural change in the left subtree of the node's right child 
	 * @param nodeN is a node, closest to the newly added leaf, at which a imbalance occurs and that has a right child
	 * @return
	 */
	private BinaryNode<T> rotateRightLeft(BinaryNode<T> nodeN){
		BinaryNode<T> nodeC = nodeN.getRightChild();
		nodeN.setRightChild(rotateRight(nodeC));
		return rotateLeft(nodeN);
	}
	
	/**
	 * Corrects an imbalance at the node closest to a structural change in the left subtree of the node's right child 
	 * @param nodeN is a node, closest to the newly added leaf, at which a imbalance occurs and that has a right child
	 * @return
	 */
	private BinaryNode<T> rotateLeftRight(BinaryNode<T> nodeN){
		BinaryNode<T> nodeC = nodeN.getLeftChild();
		nodeN.setLeftChild(nodeC.getRightChild());
		return nodeC;
	}
	
	private BinaryNode<T> rebalance(BinaryNode<T> nodeN){
		int heightDifference = getHightDifference(nodeN);
		if (heightDifference > 1) {
			if (getHightDifference(nodeN.getLeftChild()) > 0) {
				nodeN = rotateRight(nodeN);
			}else {
				nodeN = rotateLeftRight(nodeN);
			}
		}else if (getHightDifference(nodeN) < -1) {
			if (getHightDifference(nodeN) < 0) {
				nodeN = rotateRight(nodeN);
			}else {
				nodeN = rotateRightLeft(nodeN);
			}
		}
		
		return nodeN;
	}
	
	@Override
	public T add(T newEntry){
		T result = null;
		if (isEmpty()) {
			setRootNode(new BinaryNode<T>(newEntry));
		}else {
			BinaryNode<T> rootNode = getRootNode();
			addEntry(rootNode, newEntry);
			setRootNode(rebalance(rootNode));
		}
		
		return result;
	}
	
	@Override
	protected T addEntry(BinaryNode<T> rootNode, T newEntry) {
		assert rootNode != null;
		T result = null;
		int compareTo = newEntry.compareTo(rootNode.getData());
		if (compareTo == 0) {
			result = rootNode.getData();
			rootNode.setData(newEntry);
		}else if (compareTo < 0) {
			if (rootNode.hasLeftChild()) {
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				result = addEntry(leftChild, newEntry);
				rootNode.setLeftChild(rebalance(leftChild));
			}else {
				rootNode.setLeftChild(new BinaryNode<T>(newEntry));
			}
		}else {
			assert compareTo > 0;
			if (rootNode.hasRightChild()) {
				BinaryNode<T> rightChild = rootNode.getRightChild();
				result = addEntry(rightChild, newEntry);
				rootNode.setRightChild(rebalance(rightChild));
			}else {
				rootNode.setRightChild(new BinaryNode<T>(newEntry));
			}
		}
		
		return result;
	}
	
	private int getHightDifference(BinaryNode<T> nodeN) {
		int leftHeight = 0;
        int rightHeight = 0;
        if(nodeN.getLeftChild() != null){
            leftHeight = nodeN.getLeftChild().getHeight();
        }
        if(nodeN.getRightChild() != null){
            rightHeight = nodeN.getRightChild().getHeight();
        }
        return leftHeight - rightHeight;
	}
	
}
