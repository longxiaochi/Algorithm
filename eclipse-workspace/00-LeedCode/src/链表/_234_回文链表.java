package 链表;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;

import org.w3c.dom.NodeList;

public class _234_回文链表 {
	
//  使用双指针
//	public boolean isPalindrome(ListNode head) {
//        ArrayList<ListNode> list = new ArrayList<ListNode>();
//        
//		while (head != null) {
//			list.add(head);
//			head = head.next;
//		}
//		int start = 0;
//		int end = list.size() - 1;
//		
//		for (int i = 0; i < end; i ++) {
//			if (list.get(start).val != list.get(end).val) {
//				return false;
//			}
//			start++;
//			end--;
//		}
//		
//		return true;
//    }

	// 使用递归
//	private ListNode frontNode;
//	public boolean isPalindrome(ListNode head) {
//		frontNode = head;
//		return recursivelyCheck(head);
//	}
//	
//	private boolean recursivelyCheck(ListNode currentNode) {
//		if (currentNode != null) {
//			if (!recursivelyCheck(currentNode.next)) return false;
//			if (frontNode.val != currentNode.val) return false;
//			frontNode = frontNode.next;
//		}
//		return true;
//	}
	
	// 使用快慢指针和链表的翻转
	
	/*
	 * 1. 找到前半部分链表的尾节点
	 * 2. 反转后半部分的链表
	 * 3. 遍历后半部分并与前半部分做比较
	 * 4. 将链表恢复正常
	 * 5. 返回结果
	 */
	
	public boolean isPalindrome(ListNode head) {
		if (head == null) return true;
		
		ListNode firstHalfEnd = endOfFirstHalf(head);
		ListNode secondHalfStart = reverseList(firstHalfEnd.next);
		
		ListNode first = head;
		ListNode second = secondHalfStart;
		
		boolean result = true;
		while (result && second != null) {
			if (first.val != second.val) {
				result = false;
			}
			second = second.next;
			first = first.next;
		}
		firstHalfEnd.next = reverseList(secondHalfStart);
		return result;
	}
	
	public ListNode reverseList(ListNode head) {
		ListNode oldHead = head;
		ListNode newHead = null;
		
		while (oldHead != null) {
			ListNode temp = oldHead.next;
			oldHead.next = newHead;
			newHead = oldHead;
			oldHead = temp;
		}
		return newHead;
	}
	
	public ListNode endOfFirstHalf(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
}
