package com.codersongs.datastructure.queue;

public interface PriorityQueueInterface<T> {
	void add(T newEntry);
	T remove();
	T peek();
    boolean isEmpty();
    int getSize();
    void clear();
}
