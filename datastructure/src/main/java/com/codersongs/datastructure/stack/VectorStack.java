package com.codersongs.datastructure.stack;

import java.util.EmptyStackException;
import java.util.Vector;

public class VectorStack<T> implements StackInterface<T> {
	private Vector<T> stack;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public VectorStack() {
		this(DEFAULT_CAPACITY);
	}
	
	public VectorStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		stack = new Vector<T>(initialCapacity);
		initialized = true;
	}

	@Override
	public void push(T newEntry) {
		checkInitialization();
		stack.addElement(newEntry);
	}

	@Override
	public T pop() {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyStackException();
		}else {
			return stack.remove(stack.size() - 1);
		}
	}

	@Override
	public T peek() {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyStackException();
		}else {
			return stack.lastElement();
		}
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void clear() {
		stack.clear();
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
