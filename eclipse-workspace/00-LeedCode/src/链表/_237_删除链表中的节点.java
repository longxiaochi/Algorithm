package 链表;

/**
 * @author longchi
 *
 * leetCod: https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @Date Jun 14, 20209:18:55 PM
 */
public class _237_删除链表中的节点 {
	
	class Solution {
	    public void deleteNode(ListNode node) {
	        node.val = node.next.val;
	        node.next = node.next.next;
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
