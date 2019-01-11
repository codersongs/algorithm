package com.codersongs.arithmetic.test.recursion;

import org.junit.Before;
import org.junit.Test;

import com.codersongs.algorithm.common.util.IteratorUtils;
import com.codersongs.algorithm.common.util.RandomUtils;
import com.codersongs.arithmetic.sort.SearchUtils;
import com.codersongs.arithmetic.sort.SortUtils;

/**
 * 测试查找算法
 * @author song
 *
 */
public class TestSearch {
	private int[] array;
	
	@Test
	public void testBinarySearch(){
		int index = 4;
		int binarySearchIndex = SearchUtils.binarySearch(array, array[index]);
		System.out.println(binarySearchIndex);
	}
	
	@Before
	public void init(){
		array = RandomUtils.randomIntArray(10, 0, 99);
		SortUtils.quickSort(array);
		IteratorUtils.traverseIntArray(array);
	}
}
