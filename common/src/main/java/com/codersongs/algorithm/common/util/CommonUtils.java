package com.codersongs.algorithm.common.util;

/**
 * 基础工具类
 * @author song
 *
 */
public class CommonUtils {
	public static void swap(int[] array, int i, int j){
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}
