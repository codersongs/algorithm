package com.codersongs.datastructure.dict;

import java.util.Arrays;
import java.util.Iterator;

import org.omg.PortableInterceptor.DISCARDING;

public class SortedArrayDictionary<K extends Comparable<? super K>,V> implements DictionaryInterface<K, V> {
	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public SortedArrayDictionary() {
		this(DEFAULT_CAPACITY);
	}

	public SortedArrayDictionary(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		Entry<K, V>[] tempDict = new Entry[initialCapacity];
		dictionary = tempDict;
		numberOfEntries = 0;
		initialized = true;
	}

	@Override
	public V add(K key, V value) {
		checkInitialization();
		if (key == null || value == null) {
			throw new IllegalArgumentException();
		}
		V result = null;
		int locateIndex = locateIndex(key);
		if (locateIndex < numberOfEntries && key.equals(dictionary[locateIndex].getKey())) {
			result = dictionary[locateIndex].getValue();
			dictionary[locateIndex].setValue(value);
		}else {
			ensureCapacity();
			makeRoom(locateIndex);
			dictionary[locateIndex] = new Entry<K, V>(key, value);
			numberOfEntries++;
		}
		return result;
	}

	private void makeRoom(int locateIndex) {
		for (int index = numberOfEntries - 1; index > locateIndex; index--) {
			dictionary[index + 1] = dictionary[index];
		}
	}

	@Override
	public V remove(K key) {
		checkInitialization();
		if (key == null) {
			throw new IllegalArgumentException();
		}
		V result = null;
		int locateIndex = locateIndex(key);
		if (locateIndex < numberOfEntries && key.equals(dictionary[locateIndex].getKey())) {
			result = dictionary[locateIndex].getValue();
			removeArrayEntry(locateIndex);
			numberOfEntries--;
		}
		
		return result;
	}

	private void removeArrayEntry(int locateIndex) {
		for (int index = locateIndex; index < numberOfEntries - 1; index++) {
			dictionary[index] = dictionary[index + 1];
		}
	}

	@Override
	public V getValue(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getSize() {
		return numberOfEntries;
	}
	
	
	private void checkCapacity(int initialCapacity) {
		if (initialCapacity > MAX_CAPACITY) {
			throw new IllegalArgumentException();
		}
	}
	
	private void checkInitialization() {
		if (!initialized) {
			throw new UnsupportedOperationException();
		}
	}
	
	private int locateIndex(K key){
		int index = 0;
		while(index < numberOfEntries && key.compareTo(dictionary[index].getKey()) > 0){
			index++;
		}
		return index;
	}
	
	private void ensureCapacity() {
		if (numberOfEntries >= dictionary.length) {
			int newLength = dictionary.length << 2;
			checkCapacity(newLength);
			dictionary = Arrays.copyOf(dictionary, newLength);
		}
	}
	
	private class Entry<K, V> {
		private K key;
		private V value;
		
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
