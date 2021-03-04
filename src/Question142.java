import java.util.HashSet;

public class Question142 {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode cur = head;
        while(cur!=null){
            if(visited.contains(cur)){
                return cur;
            }
            visited.add(cur);
            cur = cur.next;
        }
        return null;
    }
}
