package com.codersongs.datastructure.queue;

public class LinkedDequeue<T> implements DequeInterface<T> {
	private DLNode firstNode;
	private DLNode lastNode;
	
	public LinkedDequeue() {
		firstNode = null;
		lastNode = null;
	}
	
	@Override
	public void addToFront(T newEntry) {
		DLNode newNode = new DLNode(null, newEntry, firstNode);
		if (isEmpty()) {
			lastNode = newNode;
		}else {
			firstNode.setPrevious(newNode);
		}
		firstNode = newNode;
	}

	@Override
	public void addToBack(T newEntry) {
		DLNode newNode = new DLNode(lastNode, newEntry, null);
		if (isEmpty()) {
			firstNode = newNode;
		}else {
			lastNode.setNext(newNode);
		}
		
		lastNode = newNode;
	}

	@Override
	public T removeFront() {
		T front = getFront();
		assert front != null;
		firstNode = firstNode.getNext();
		if (firstNode == null) {
			lastNode = null;
		}else {
			firstNode.setPrevious(null);
		}
		return front;
	}

	@Override
	public T removeBack() {
		T back = getBack();
		assert back != null;
		lastNode = lastNode.getPrevious();
		if (lastNode == null) {
			firstNode = null;
		}else {
			lastNode.setNext(null);
		}
		return back;
	}

	@Override
	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return firstNode.getData();
	}

	@Override
	public T getBack() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return lastNode.getData();
	}
	
	@Override
	public boolean isEmpty() {
		return firstNode == null || lastNode == null;
	}

	@Override
	public void clear() {
		while(!isEmpty()){
			removeFront();
		}
	}
	
	private class DLNode {
		private T data;
		private DLNode previous;
		private DLNode next;
		
		public DLNode(LinkedDequeue<T>.DLNode previous, T data, LinkedDequeue<T>.DLNode next) {
			this.data = data;
			this.previous = previous;
			this.next = next;
		}
		
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public DLNode getPrevious() {
			return previous;
		}
		public void setPrevious(DLNode previous) {
			this.previous = previous;
		}
		public DLNode getNext() {
			return next;
		}
		public void setNext(DLNode next) {
			this.next = next;
		}
	}

}
