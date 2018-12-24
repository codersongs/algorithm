package com.codersongs.datastructure.tree;

/**
 * 最大堆接口
 * @author song
 *
 * @param <T>
 */
public interface MaxHeapInterface<T extends Comparable<? super T>> {
	public void add(T newEntry);
	
	public T removeMax();
	
	public T getMax();
	
	public boolean isEmpty();
	
	public int getSize();
	
	public void clear();
}
