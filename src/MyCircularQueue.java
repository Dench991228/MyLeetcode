class MyCircularQueue {
    int[] Queue;
    int top = 0;
    int tail = 0;
    int capacity;
    int content = 0;
    public MyCircularQueue(int k) {
        this.Queue = new int[k];
        this.capacity = k;
    }

    public boolean enQueue(int value) {
        if(content==capacity)return false;
        Queue[tail] = value;
        tail = (tail+1)%capacity;
        content++;
        return true;
    }

    public boolean deQueue() {
        if(content==0)return false;
        top = (top+1)%capacity;
        content--;
        return true;
    }

    public int Front() {
        if(content==0)return -1;
        return Queue[top];
    }

    public int Rear() {
        if(content==0)return -1;
        return Queue[(tail+capacity-1)%capacity];
    }

    public boolean isEmpty() {
        return content==0;
    }

    public boolean isFull() {
        return content==capacity;
    }
}