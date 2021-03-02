public class Question206 {
    /**
     * 翻转一个链表节点，让它指向former，并且返回下一个节点
     * @param former 希望让current指向的节点
     * @param current 被翻转的节点
     * @return current.next
     * */
    private ListNode localReverse(ListNode former, ListNode current){
        ListNode result = current.next;
        current.next = former;
        return result;
    }
    /**
     * 反转一个单链链表
     * */
    public ListNode reverseList(ListNode head) {
        ListNode former = null;
        ListNode current = head;
        while(current!=null){
            ListNode temp = current;
            current = localReverse(former, current);
            former = temp;
        }
        return former;
    }
}
