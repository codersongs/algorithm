package com.codersongs.arithmetic.test.recursion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.codersongs.algorithm.common.util.IteratorUtils;
import com.codersongs.algorithm.common.util.RandomUtils;
import com.codersongs.arithmetic.sort.SortUtils;

/**
 * 测试排序算法
 * @author song
 *
 */
public class TestSort {
	private int[] array;
	
	@Test
	public void testRadixSort(){
		SortUtils.radixSort(array, 2);
	}
	
	@Test
	public void testQuickSort(){
		SortUtils.quickSort(array);
	}
	@Test
	public void test(){
		SortUtils.heapSort(array);
	}
	
	@Test
	public void testMergeSort(){
		SortUtils.mergeSort(array);
	}
	
	@Test
	public void testShellSort(){
		SortUtils.shellSort(array);
	}
	
	@Test
	public void testInsersionSort(){
		SortUtils.insertionSort(array);
	}
	
	@Test
	public void testInsertionSort(){
		SortUtils.selectionSort(array);
	}
	
	@Test
	public void testBubbleSort(){
		SortUtils.bubbleSort(array);
	}
	
	@Before
	public void initArray(){
		array = RandomUtils.randomIntArray(10, 0, 99);
		IteratorUtils.traverseIntArray(array);
	}
	
	@After
	public void afterSort(){
		System.out.println("==============sort line==============");
		IteratorUtils.traverseIntArray(array);
	}
}
