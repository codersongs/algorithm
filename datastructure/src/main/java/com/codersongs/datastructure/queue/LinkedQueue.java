package com.codersongs.datastructure.queue;

public class LinkedQueue<T> implements QueueInterface<T> {
	private Node firstNode;
	private Node lastNode;
	
	public LinkedQueue() {
		firstNode = null;
		lastNode = null;
	}

	@Override
	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry, null);
		if (isEmpty()) {
			firstNode = newNode;
		}else {
			lastNode.setNext(newNode);
		}
		lastNode = newNode;
	}
	
	@Override
	public T dequeue() {
		T front = firstNode.getData();
		assert firstNode != null;
		firstNode.setData(null);
		firstNode = firstNode.getNext();
		
		if (firstNode == null) {
			lastNode = null;
		}
		return front;
	}

	@Override
	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return firstNode.getData();
	}

	@Override
	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
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
