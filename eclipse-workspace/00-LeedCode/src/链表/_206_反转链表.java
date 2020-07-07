package 链表;

import javax.xml.soap.Node;

/**
 * @author longchi
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @Date Jun 14, 20209:55:32 PM
 */
public class _206_反转链表 {
	class Solution {
		public ListNode reverseList(ListNode head) {
			if (head == null || head.next == null) return head;
			
			ListNode node = reverseList(head);
			head.next.next = head;
			head.next = null;
			return node;
		}
		
//	    public ListNode reverseList(ListNode head) {
//	    	// 新头部
//	    	ListNode newHead = null;   
//	    	while (head != null) {
//	    		ListNode tmp = head.next;   // 保住head后面的狗头
//	    		head.next = newHead;
//	    		newHead = head;
//	    		head = tmp;
//			}
//	    	return newHead;
//	    }
	    
//	    public ListNode reverseList(ListNode head) {
//	    	if (head == null || head.next == null) return head;
//	    	
//	    	ListNode cur = head;
//	    	while (head.next != null) {
//	    		ListNode tmp = head.next.next;
//	    		head.next.next = cur;
//	    		cur = head.next;
//	    		head = tmp;
//			}
//	    	
//	    	return cur;
//	    }
	    
	}
}


