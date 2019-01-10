package com.codersongs.arithmetic.sort;

import com.codersongs.algorithm.common.exception.ArrayCheckFailException;
import com.codersongs.algorithm.common.util.CommonUtils;
import com.codersongs.algorithm.common.util.IteratorUtils;

/**
 * 常用排序算法实现
 * @author song
 *
 */
public class SortUtils {
	
	/**
	 * 基数排序
	 * @param array
	 */
	public static void radixSort(int[] array){
		
	}
	
	/**
	 * 快速排序
	 * @param array
	 */
	public static void quickSort(int[] array){
		quickSort(array, 0, array.length - 1);
	}
	
	/**
	 * 
	 * @param array
	 * @param low
	 * @param high
	 */
	public static void quickSort(int[] array, int low, int high){
		if (low < high) {
			int index = partition(array, low, high);
			quickSort(array, low, index-1);
			quickSort(array, index+1, high);
		}
	}
	
	/**
	 * 
	 * @param array
	 * @param low
	 * @param high
	 * @return
	 */
	private static int partition(int[] array, int low, int high) {
		int i = low;
		int j = high;
		int tmp = array[low];
		while(i < j){
			while(i < j && array[j] >= tmp){
				j--;
			}
			while(i < j && array[i] <= tmp){
				i++;
			}
			if (i < j) {
				CommonUtils.swap(array, i, j);
			}
		}
		CommonUtils.swap(array, i, low);
		return i;
	}

	/**
	 * 堆排序
	 * @param array
	 */
	public static void heapSort(int[] array){
		checkArray(array);
		int len = array.length;
		//构造堆
		for (int i = (len - 2) / 2; i >= 0; i--) {
			reheap(array, i, len - 1);
		}
		IteratorUtils.traverseIntArray(array);
		//交换元素
		CommonUtils.swap(array, 0, len-1);
		//将两个半堆构造成堆，交换元素
		for (int i = len - 2; i > 0 ; i--) {
			reheap(array, 0, i);
			CommonUtils.swap(array, 0, i);
		}
	}
	
	/**
	 * 调整下沉
	 * @param array
	 * @param rootIndex
	 * @param lastIndex
	 */
	private static void reheap(int[] array, int rootIndex, int lastIndex) {
		boolean done = false;
		int root = array[rootIndex];
		int leftIndex = 2 * rootIndex + 1;
		while(!done && leftIndex <= lastIndex){
			//确定最大孩子
			int largerIndex = leftIndex;
			int rightIndex = leftIndex + 1;
			if (rightIndex <= lastIndex && array[rightIndex] > array[leftIndex]) {
				largerIndex = rightIndex;
			}
			if (array[largerIndex] >= root) {
				array[rootIndex] = array[largerIndex];
				rootIndex = largerIndex;
				leftIndex = 2 * rootIndex + 1;
			}else {
				done = true;
			}
		}
		
		array[rootIndex] = root;
	}

	/**
	 * 归并排序
	 * @param array
	 */
	public static void mergeSort(int[] array){
		mergeSort(array, 0, array.length-1);
	}
	
	public static void mergeSort(int[] array, int begin, int end){
		if (begin < end) {
			int mid = (begin + end) / 2;
			mergeSort(array, begin, mid);
			mergeSort(array, mid+1, end);
			merge(array, begin, mid, end);
		}
	}
	
	/**
	 * 合并两个排好序的数组
	 * @param array
	 * @param begin
	 * @param mid
	 * @param end
	 */
	private static void merge(int[] array, int begin, int mid, int end) {
		int[] tmpArray = new int[end - begin + 1];
		int left = begin;
		int right = mid + 1;
		int count = 0;
		while(left <= mid && right <= end){
			if (array[left] < array[right]) {
				tmpArray[count++] = array[left++];
			}else {
				tmpArray[count++] = array[right++];
			}
		}
		
		while(left <= mid){
			tmpArray[count++] = array[left++]; 
		}
		while(right <= end){
			tmpArray[count++] = array[right++];
		}
		
		for (int i = 0; i < tmpArray.length; i++) {
			array[begin+i] = tmpArray[i];
		}
	}

	/**
	 * 希尔排序
	 * @param array
	 */
	public static void shellSort(int[] array){
		int d = array.length;
		while(true){
			d /= 2;
			for (int x = 0; x < d; x++) {
				for (int i = x+d; i < array.length; i+=d) {
					int tmp = array[i];
					int j;
					for (j = i-d; j >= 0 && array[j] > tmp; j-=d) {
						array[j+d] = array[j];
					}
					array[j+d] = tmp;
				}
			}
			if (d == 1) {
				break;
			}
		}
	}

	/**
	 * 插入排序
	 * @param array
	 */
	public static void insertionSort(int[] array){
		checkArray(array);
		for (int i = 0; i < array.length; i++) {
			int j;
			int tmp = array[i];
			for (j = i-1; j >= 0; j--) {
				if (array[j] > array[i]) {
					array[j+1] = array[j];
				}
			}
			array[j+1] = tmp;
		}
	}
	

	/**
	 * 选择排序-升序
	 * @param array
	 */
	public static void selectionSort(int[] array){
		checkArray(array);
		for (int i = 0; i < array.length; i++) {
			int minIndex = i;
			for (int j = i+1; j < array.length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			CommonUtils.swap(array, minIndex, i);
		}
	}
	
	/**
	 * 冒泡排序
	 * @param array
	 */
	public static void bubbleSort(int[] array){
		checkArray(array);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length-i-1; j++) {
				if (array[j] > array[j+1]) {
					CommonUtils.swap(array, j, j+1);
				}
			}
		}
	}
	
	/**
	 * 检查数组是否合法
	 * @param array
	 */
	private static void checkArray(int[] array) {
		if (array == null) {
			throw new ArrayCheckFailException("array can not be null");
		}
	}
}
