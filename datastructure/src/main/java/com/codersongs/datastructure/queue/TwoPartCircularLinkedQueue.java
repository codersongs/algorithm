package com.codersongs.datastructure.queue;

public class TwoPartCircularLinkedQueue<T> implements QueueInterface<T> {
	private Node queueNode;
	private Node freeNode;
	
	public TwoPartCircularLinkedQueue() {
		freeNode = new Node(null, null);
		queueNode = freeNode;
		freeNode.setNext(queueNode);
	}
	
	@Override
	public void enqueue(T newEntry) {
		freeNode.setData(newEntry);
		if (isChainFull()) {
			Node newNode = new Node(null, freeNode.getNext());
			freeNode.setNext(newNode);
		}
		freeNode = freeNode.getNext();
	}

	private boolean isChainFull() {
		return freeNode.getNext() == queueNode;
	}

	@Override
	public T dequeue() {
		T front = getFront();
		assert !isEmpty();
		queueNode.setData(null);
		queueNode = queueNode.getNext();
		return front;
	}

	@Override
	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return queueNode.getData();
	}

	@Override
	public boolean isEmpty() {
		return freeNode == queueNode;
	}

	@Override
	public void clear() {
		while(!isEmpty()){
			dequeue();
		}
	}
	
	private class Node {
		private T data;
		private Node next;
		
		
		public Node(T data, Node next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
	}
}
