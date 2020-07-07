package 链表;

public class _203_移除链表元素 {
	public static ListNode removeElements(ListNode head, int val) {
        ListNode newHead = null;
        ListNode preHead = null;
        while(head != null) {
            if (head.val == val) {
                 if (newHead != null) {
                     // 这里就要进行处理了。
                    if (head.next == null){
                        preHead.next = null;
                        break;
                    }
                    head.val = head.next.val;
                    head.next = head.next.next;
                    continue;
                 }
            } else {
                if (newHead == null){
                    newHead = head;
                }
            }
            preHead = head;
            head = head.next;
        }
        return newHead;
    }
}
