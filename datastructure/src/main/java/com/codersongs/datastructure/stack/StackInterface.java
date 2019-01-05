package com.codersongs.datastructure.stack;

/**
 * 栈接口
 * @author song
 *
 * @param <T>
 */
public interface StackInterface<T> {
	
	public void push(T newEntry);
	
	public T pop();
	
	public T peek();
	
	public boolean isEmpty();
	
	public void clear();
}
