package com.codersongs.datastructure.queue;

public class ArrayQueue<T> implements QueueInterface<T>{
	private T[] queue;
	private int frontIndex;
	private int backIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	
	public ArrayQueue(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		initialized = false;
	}


	@Override
	public void enqueue(T newEntry) {
		checkInitialization();
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	}

	private void ensureCapacity() {
		if (frontIndex == (backIndex + 2) % queue.length) {
			T[] oldQueue = queue;
			int oldSize = queue.length;
			int newSize = oldSize * 2;
			checkCapacity(newSize);
			
			T[] tempQueue = (T[]) new Object[newSize];
			queue = tempQueue;
			for (int index = 0; index < oldSize -1; index++) {
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			frontIndex = 0;
			backIndex = oldSize - 2;
		}
	}

	@Override
	public T dequeue() {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		}
	}

	@Override
	public T getFront() {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return queue[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		return frontIndex == (backIndex + 1) % queue.length;
	}

	@Override
	public void clear() {
		queue = null;
		frontIndex = 0;
		backIndex = 0;
	}
	
	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > MAX_CAPACITY) {
			throw new IllegalArgumentException("capacity is larger than max capacity");
		}
	}
	
	private void checkInitialization(){
		if (!initialized) {
			throw new UnsupportedOperationException("operation is unsupported before initialization");
		}
	}
	
}
