package com.codersongs.leetcode;

/**
 * 
 * @author song
 *
 */
public class AddNum {
	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (target == nums[i] + nums[j]) {
					return new int[]{i, j};
				}
			}
		}
        return null;
    }
	
	/**
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSumHash(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (target == nums[i] + nums[j]) {
					return new int[]{i, j};
				}
			}
		}
        return null;
    }
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode currentOne = l1;
		ListNode currentOther = l2;
		boolean carryBit = false;
		ListNode head = null;
		ListNode result = null;
		while(currentOne != null || currentOther != null){
			int val = (currentOne == null ? currentOther.val : (currentOther == null ? currentOne.val : currentOne.val + currentOther.val));  
			
			if (carryBit) {
				val++;
			}
			
			if (val >= 10) {
				val -= 10;
				carryBit = true;
			}else {
				carryBit = false;
			}
			
			if (result == null) {
				head = result = new ListNode(val);
			}else {
				result.next = new ListNode(val);
				result = result.next;
			}
			if (currentOne != null) {
				currentOne = currentOne.next;
			}
			if (currentOther != null) {
				currentOther = currentOther.next;
			}
			
		}
		if (carryBit) {
			result.next = new ListNode(1);
		}
		return head;
    }
	
	class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	}
}
