package com.codersongs.datastructure.tree;

import java.util.Iterator;

public interface TreeIteratorInterface<T> {
	public Iterator<T> gerPreOrderIterator();
	public Iterator<T> gerPostOrderIterator();
	public Iterator<T> gerInOrderIterator();
	public Iterator<T> gerLevelOrderIterator();
}
