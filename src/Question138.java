import java.util.HashMap;

public class Question138 {
    public Node copyRandomList(Node head) {
        Node start = new Node(head.val);
        HashMap<Node, Node> dict = new HashMap<>();//旧链表到新链表
        dict.put(head, start);
        Node new_cur = start;
        Node old_cur = head.next;
        while(old_cur!=null){
            new_cur.next = new Node(old_cur.val);
            new_cur = new_cur.next;
            dict.put(old_cur, new_cur);
            old_cur = old_cur.next;
        }
        for(Node n: dict.keySet()){
            Node old = dict.get(n);
            old.random = dict.get(n.random);
        }
        return start;
    }
}
