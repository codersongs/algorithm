package com.codersongs.algorithm.common;


import org.apache.commons.lang3.StringUtils;

/**
 * 链表节点
 */
public class ListNode {
      /**
       * 值
       */
      public int val;
      /**
       * 下一个节点
       */
      public ListNode next;
      public ListNode() {}
      public ListNode(int val) {
            this.val = val;
      }
      public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
      }

      public static String toString(ListNode start) {
            StringBuilder res = new StringBuilder();
            ListNode tmp = start;
            while (tmp != null) {
                  res.append(tmp.val).append("->");
                  tmp = tmp.next;
            }
            if (StringUtils.endsWith(res.toString(), "->")) {
                  return res.substring(0, res.length()-2);
            }
            return res.toString();
      }

      public static ListNode generateByArray(int... array) {
            if (array == null || array.length == 0) {
                  return null;
            }
            ListNode head = new ListNode(array[0]);
            ListNode tmp = head;
            for (int i = 1; i < array.length; i++) {
                  int num = array[i];
                  tmp.next = new ListNode(num);
                  tmp = tmp.next;
            }
            return head;
      }
      public static void main(String[] args) {
            String res = ListNode.toString(generateByArray(1, 2, 3));
            System.out.println(res);
      }
  }
