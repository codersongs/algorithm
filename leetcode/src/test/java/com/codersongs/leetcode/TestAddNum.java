package com.codersongs.leetcode;

import org.junit.Test;

public class TestAddNum {
	AddNum addNum = new AddNum();
	@Test
	public void testAddTwoNumbers(){
		AddNum.ListNode listNode1 = addNum.new ListNode(3);
		listNode1.next = addNum.new ListNode(4);
		listNode1.next.next = addNum.new ListNode(4);
		
	}
}
