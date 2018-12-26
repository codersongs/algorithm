package com.codersongs.datastructure.tree;

import java.util.Arrays;

public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>{
	private T[] heap;
	private int lastIndex;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	
	
	public MaxHeap() {
		this(DEFAULT_CAPACITY);
	}
	
	public MaxHeap(int initialCapacity) {
		if (initialCapacity < DEFAULT_CAPACITY) {
			initialCapacity = DEFAULT_CAPACITY;
		}else {
			checkCapacity(initialCapacity);
		}
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[])new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		initialized = true;
	}
	
	public MaxHeap(T[] entries){
		this(entries.length);
		assert initialized = true;
		for (int i = 0; i < entries.length; i++) {
			heap[i+1] = entries[i]; 
		}
		
		for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--) {
			reheap(rootIndex);
		}
	}

	/**
	 * 检查容量是否超过最大值
	 * @param initialCapacity
	 */
	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 添加一个元素
	 */
	@Override
	public void add(T newEntry) {
		checkInitialization();
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		while(parentIndex > 0 && newEntry.compareTo(heap[parentIndex]) > 0){
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
		}
		
		heap[newIndex] = newEntry;
		lastIndex ++;
		ensureCapacity();
	}
	
	/**
	 * 扩容
	 */
	private void ensureCapacity() {
		if (lastIndex >= heap.length) {
			int newCapacity = 2 * (heap.length - 1);
			checkCapacity(newCapacity);
			heap = Arrays.copyOf(heap, newCapacity);
		}
	}

	@Override
	public T removeMax() {
		checkInitialization();
		T root = null;
		if (!isEmpty()) {
			root = heap[1];
			heap[1] = heap[lastIndex];
			lastIndex --;
			reheap(1);
		}
		return root;
	}
	
	/**
	 * 将半树转换为树
	 */
	private void reheap(int rootIndex){
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		while (!done && (leftChildIndex <= lastIndex)) {
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			if (rightChildIndex <= lastIndex &&  heap[rightChildIndex].compareTo(orphan) > 0) {
				largerChildIndex = rightChildIndex;
			}
			if (orphan.compareTo(heap[largerChildIndex]) < 0) {
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * largerChildIndex;
			}else {
				done = true;
			}
		}
		heap[rootIndex] = orphan;
	}
	
	private static  <T extends Comparable<? super T>> void reheap(T[] heap, int rootIndex, int lastIndex){
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		while(!done && leftChildIndex <= lastIndex){
			int largerChildIndex = leftChildIndex;
			int rightChildIndex = leftChildIndex + 1;
			if (rightChildIndex <= lastIndex && heap[rightChildIndex].compareTo(heap[leftChildIndex]) > 0) {
				largerChildIndex = rightChildIndex;
			}
			
			if (orphan.compareTo(heap[largerChildIndex]) < 0) {
				heap[rootIndex] = heap[largerChildIndex];
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * largerChildIndex;
			}else {
				done = true;
			}
		}
		heap[rootIndex] = orphan;
	}
	
	/**
	 * 堆排序
	 * @param array
	 * @param n
	 */
	public static <T extends Comparable<? super T>> void heapSort(T[] array, int n){
		for (int rootIndex = n / 2 - 1; rootIndex >= 0; rootIndex--) {
			reheap(array, rootIndex, n - 1);
		}
		swap(array, 0, n - 1);
		for (int lastIndex = n - 2; lastIndex > 0; lastIndex--) {
			reheap(array, 0, lastIndex);
			swap(array, 0, lastIndex);
		}
	}
	
	public static <T extends Comparable<? super T>> void swap(T[] array, int start, int end){
		T tmp = array[start];
		array[start] = array[end];
		array[end] = tmp;
	}
	@Override
	public T getMax() {
		checkInitialization();
		T root = null;
		if (!isEmpty()) {
			root = heap[1];
		}
		return root;
	}
	
	/**
	 * 检查是否初始化
	 */
	private void checkInitialization() {
		if (!initialized) {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean isEmpty() {
		return lastIndex < 1;
	}

	@Override
	public int getSize() {
		return lastIndex;
	}
	
	@Override
	public void clear() {
		checkInitialization();
		while (lastIndex > -1) {
			heap[lastIndex] = null;
			lastIndex --;
		}
		lastIndex = 0;
	}

}
