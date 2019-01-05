package com.codersongs.datastructure.dict;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDictionary<K,V> implements DictionaryInterface<K, V> {
	private Entry<K, V>[] dictionary;
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayDictionary() {
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayDictionary(int initialCapacity) {
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
		int index = locateIndex(key);
		if (index < numberOfEntries) {
			result = dictionary[index].getValue();
			dictionary[index].setValue(value);
		}else {
			ensureCapacity();
			dictionary[index] = new Entry<K, V>(key, value);
			numberOfEntries++;
		}
		return result;
	}

	@Override
	public V remove(K key) {
		checkInitialization();
		if (key == null) {
			throw new IllegalArgumentException();
		}
		V result = null;
		int index = locateIndex(key);
		if (index < numberOfEntries) {
			result = dictionary[index].getValue();
			dictionary[index] = dictionary[numberOfEntries - 1];
			dictionary[numberOfEntries - 1] = null;
			numberOfEntries--;
		}
		return result;
	}

	@Override
	public V getValue(K key) {
		return null;
	}

	@Override
	public boolean contains(K key) {
		return false;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isFull() {
		return false;
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
		while(index < numberOfEntries && !key.equals(dictionary[index].getKey())){
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
	
	@Override
	public void clear() {
		
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
