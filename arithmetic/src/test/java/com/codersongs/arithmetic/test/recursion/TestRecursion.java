package com.codersongs.arithmetic.test.recursion;

import org.junit.Test;

public class TestRecursion {
	@Test
	public void testSum(){
		System.out.println(sumOf(100));
	}
	
	public static int sumOf(int n){
		int sum;
		if (n == 1) {
			sum = 1;
		}else {
			sum = sumOf(n - 1) + n;
		}
		return sum;
	}
	
	@Test
	public void testDisplayArrays(){
		int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//		displayArrays(array, 0, 9);
//		displayArrays2(array, 0, 9);
		displayArraysMid(array, 0, 9);
	}
	
	/**
	 * 头递归
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void displayArrays(int[] array, int start, int end){
		System.out.println(array[start]);
		if (start < end) {
			displayArrays(array, start + 1, end);
		}
	}
	
	/**
	 * 尾递归
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void displayArrays2(int[] array, int start, int end){
		if (start <= end) {
			displayArrays(array, start, end - 1);
			System.out.println(array[end]);
		}
	}
	
	/**
	 * 中间序列递归
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void displayArraysMid(int[] array, int start, int end){
		if (start == end) {
			System.out.println(array[start]);
		}else {
			int mid =  (start + end) / 2;
			displayArraysMid(array, start, mid);
			displayArraysMid(array, mid + 1, end);
		}
	}
}
