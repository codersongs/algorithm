package com.codersongs.datastructure.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {
	private T[] stack;
	private int topIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayStack(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[])new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
		initialized = true;
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
	
	@Override
	public void push(T newEntry) {
		checkInitialization();
		ensureCapacity();
		stack[topIndex+1] = newEntry;
		topIndex++;
	}
	
	private void ensureCapacity() {
		if (topIndex == stack.length - 1) {
			int newLength = 2 * stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}

	@Override
	public T pop() {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyStackException();
		}else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}

	@Override
	public T peek() {
		checkInitialization();
		if (isEmpty()) {
			throw new EmptyStackException();
		}else {
			return stack[topIndex];
		}
	}

	@Override
	public boolean isEmpty() {
		return topIndex < 0;
	}

	@Override
	public void clear() {
		stack = null;
		topIndex = -1;
		initialized = false;
	}

}
