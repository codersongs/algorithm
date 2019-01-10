package com.codersongs.algorithm.common.test.util;

import org.junit.Test;

import com.codersongs.algorithm.common.util.IteratorUtils;
import com.codersongs.algorithm.common.util.RandomUtils;
/**
 * 测试RandomUtils工具类
 * @author song
 *
 */
public class TestRandomUtils {
	@Test
	public void testRandomInt(){
		int[] randomIntArray = RandomUtils.randomIntArray(10, 9, 30);
		IteratorUtils.traverseIntArray(randomIntArray);
	}
}
