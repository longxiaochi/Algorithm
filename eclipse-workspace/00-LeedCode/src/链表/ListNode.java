package 链表;

public class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { 
		val = x; 
	}
	
	@Override
	public String toString() {
		StringBuilder buider = new StringBuilder();
		buider.append("links: ");
		ListNode node = this;
		while (node != null) {
			buider.append(node.val).append("_");
			node = node.next;
		}
		buider.append("null");
		
		return buider.toString();
	}
}