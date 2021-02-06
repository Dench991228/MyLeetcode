import java.util.LinkedList;
import java.util.List;

public class Question636 {
    /*
     * 从日志记录中获得函数ID
     * @param log 日志记录
     */
    private int getID(String log){
        String[] content = log.split(":");
        return Integer.parseInt(content[0]);
    }
    /*
     * 从日志记录中获得开始还是结束
     * @param log 日志记录
     * @return false:结束，true:开始
     */
    private boolean getState(String log){
        String[] content = log.split(":");
        return content[1].compareTo("start") == 0;
    }
    /*
     * 从日志记录中获得时间戳
     * @param log 日志记录
     */
    private int getStamp(String log){
        String[] contents = log.split(":");
        return Integer.parseInt(contents[2]);
    }
    /*
     * @param n 函数个数
     * @param log 日志数组
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        /*用来保存每一个函数的独占时间*/
        int[] res = new int[n];
        /*记录调用过程*/
        LinkedList<Integer> stack = new LinkedList<>();
        /*记录当前已经运行的时间*/
        LinkedList<Integer> time = new LinkedList<>();
        /*最后一次操作，相当于是当前运行的函数（栈顶）是从什么时候开始运行的*/
        int last_opr=-1;
        for(String log:logs){
            int func = this.getID(log);
            boolean state = this.getState(log);
            int time_stamp = this.getStamp(log);
            if(state){//新的函数开始跑
                if(!stack.isEmpty()){//如果之前已经有其他函数开始跑了
                    int last_func = stack.peekLast();
                    int last_start = time.peekLast();
                    res[last_func] += (time_stamp-last_opr);
                }
                stack.addLast(func);
                time.addLast(time_stamp);
            }
            else{//干掉之前的一个函数，这个函数应该加上的时间
                int last_func = stack.pollLast();
                int last_start = time.pollLast();
                res[last_func] += (time_stamp-last_opr);
            }
            last_opr = time_stamp;
        }
        return res;
    }
}
