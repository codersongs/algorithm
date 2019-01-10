package com.codersongs.algorithm.common.util;

import java.util.Random;

/**
 * 随机数生成工具类
 * @author song
 *
 */
public class RandomUtils {
	
	/**
	 * 生成int类型的随机数数组
	 * @param size 数组长度
	 * @param min 最小值
	 * @param max 最大值
	 * @return
	 */
	public static int[] randomIntArray(int size, int min, int max){
		int[] array = new int[size];
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			int randomInt = random.nextInt(max);
			if (randomInt < min) {
				randomInt += min;
			}
			array[i] = randomInt;
		}
		return array;
	}
}
