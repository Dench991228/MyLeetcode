import java.util.LinkedList;

public class MyQueue {
    private LinkedList<Integer> in_queue;
    private LinkedList<Integer> out_queue;
    /** Initialize your data structure here. */
    public MyQueue() {
        in_queue = new LinkedList<>();
        out_queue = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in_queue.addLast(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (out_queue.isEmpty()) {
            while (!in_queue.isEmpty()) {
                out_queue.addFirst(in_queue.pollLast());
            }
        }
        return out_queue.pollFirst();
    }

    /** Get the front element. */
    public int peek() {
        if (out_queue.isEmpty()) {
            while (!in_queue.isEmpty()) {
                out_queue.addFirst(in_queue.pollLast());
            }
        }
        return out_queue.peekFirst();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in_queue.isEmpty()&&out_queue.isEmpty();
    }
}
