public class Question1669 {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        /*想办法找出指向第a个节点的节点和b指向的节点*/
        ListNode last_first = null;
        ListNode first_last = null;
        int next_pos = 1;
        ListNode cur = list1;
        while(next_pos < a){
            cur = cur.next;
            next_pos++;
        }
        last_first = cur;
        while(next_pos < b){
            cur = cur.next;
            next_pos++;
        }
        first_last = cur.next.next;
        last_first.next = list2;
        ListNode next = list2;
        while(next!=null){
            cur = next;
            next = next.next;
        }
        cur.next = first_last;
        return list1;
    }
}
