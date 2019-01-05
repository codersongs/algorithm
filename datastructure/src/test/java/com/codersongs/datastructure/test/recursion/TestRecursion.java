package com.codersongs.datastructure.test.recursion;

import org.junit.Test;

public class TestRecursion {
	@Test
	public void testCountDown(){
		countDown(100);
	}
	
	public static void countDown(int integer){
		if (integer >= 1) {
			System.out.println(integer);
			countDown(integer - 1);
		}
	}
}
