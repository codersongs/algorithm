package com.codersongs.arithmetic.sort;

/**
 * 查找相关算法
 * @author song
 *
 */
public class SearchUtils {
	public static int binarySearch(int[] array, int dest){
		int low = 0;
		int high = array.length;
		while(low <= high){
			int middle = (low + high) >>> 1;
			if (dest == array[middle]) {
				return middle;
			}else if (dest < array[middle]) {
				high = middle - 1;
			}else {
				low = middle + 1;
			}
		}
		
		return -1;
	}
}
