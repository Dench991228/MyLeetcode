import java.util.LinkedList;

public class Question135 {
    /**
     * 单调栈里面只保留最长的递减子序列，新的小孩进入之后，需要把之前的都弹出来
     * @param performance 记录之前每一个小孩的表现
     * @param bigger 之前有连续几个递增
     * @param cur 现在加进来的小孩的表现
     * @return 这次弹出需要多少糖果
     */
    private int popKid(LinkedList<Integer> performance, LinkedList<Integer> bigger, int cur){
        int sum = 0;
        /*右边小孩糖果数*/
        int cur_candy = 0;
        /*右边小孩的表现*/
        int former_kid = Integer.MIN_VALUE;
        int final_bigger = bigger.peekLast();
        while(!performance.isEmpty()&&!bigger.isEmpty()){
            int cur_kid = performance.pollLast();//当前小孩的表现
            int cur_bigger = bigger.pollLast();//当前小孩比左边连续递增的多少人高
            if(cur_kid>former_kid){
                cur_candy=Math.max(cur_candy+1,cur_bigger+1);
            }
            else{
                cur_candy=cur_bigger+1;
            }
            former_kid = cur_kid;
            sum+=cur_candy;
        }
        performance.addLast(cur);
        bigger.addLast(final_bigger+1);
        return sum;
    }
    public int candy(int[] ratings) {
        int i, len = ratings.length;
        int total = 0;
        LinkedList<Integer> kids = new LinkedList<>();
        LinkedList<Integer> biggers = new LinkedList<>();
        for(i=0;i<len;i++){
            if(kids.isEmpty()){
                kids.push(ratings[i]);
                biggers.push(0);
            }
            else if(kids.peekLast()>=ratings[i]){
                kids.addLast(ratings[i]);
                biggers.addLast(0);
            }
            else{
                total += this.popKid(kids,biggers,ratings[i]);
            }
        }
        total += this.popKid(kids,biggers,Integer.MAX_VALUE);
        return total;
    }
}
