package com.codersongs.datastructure.tree;

public class ReturnObject<T> {
	private T t;
	
	
	
	public ReturnObject(T t) {
		this.t = t;
	}

	public T get() {
		return t;
	}

	public void set(T t) {
		this.t = t;
	}
}
