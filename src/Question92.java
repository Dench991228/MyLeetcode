public class Question92 {
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int pos = 1;
        /*找出被翻转的第一个节点的前一个节点，和被翻转的最后一个节点的后继节点*/
        ListNode cur = head;
        ListNode start = null;
        ListNode end = null;
        while(cur!=null){
            if(pos==m-1){
                start = cur;
            }
            if(pos==n+1){
                end = cur;
            }
            cur = cur.next;
        }
        ListNode prev = end;
        ListNode current = start==null?head:start.next;
        while(current!=end){
            ListNode temp = current;
            current = localReverse(prev, current);
            prev = temp;
        }
        if(start!=null)start.next = prev;
        else{
            return prev;
        }
        return head;
    }
}
