public class Question464 {
    /*使用动态规划和状态压缩解决问题*/
    /*一个二进制数，如果第i位是1表名这个数已经被取过了*/

    /**
     * 判断某个状态下，某个数有没有被取走
     * @param state 当前状态
     * @param num 目标数
     * @return 是否已经被取走
     */
    private boolean hasTaken(int state, int num){
        return ((state)&(1<<(num-1))) != 0;
    }

    /**
     * 从当前状态下取走一个数
     * @param state 当前状态
     * @param num 要取走的数
     * @return 取完之后的状态
     */
    private int take(int state, int num){
        return state|(1<<(num-1));
    }

    /**
     * 当前state代表的，拿出来的那些数是不是大于等于num
     * @param state 当前的状态
     * @param num 一共有几个数可以选
     * @param target 目标数
     * @return 状态是否大于等于目标数
     */
    private boolean isBiggerEqual(int state, int num, int target){
        int i;
        int sum = 0;
        for(i=1;i<=num;i++){
            if((state&(1<<(i-1)))!=0)sum+=i;
        }
        return sum>=target;
    }

    /**
     * 判断在一个状态下，我先出手能不能赢
     * 已经提前把取出的数的和大于目标数的情况，在winOrLose中标记过了
     * @param state 当前状态
     * @param winOrLose 某个人，取完最后一个数之后的状态是第二个下标，此时能不能赢
     * @param visited 与winOrLose相同，用来记载一个地方是不是计算过
     * @param p 当前轮到谁
     * @return 能不能赢
     */
    private boolean canWin(int state, boolean[][] winOrLose, boolean[][] visited, boolean p, int num){
        boolean win = false;
        int i;
        for(i=1;i<num;i++){//这个数之前没有被拿出来过，假设这一轮p选择这个数，!p是不是必输？
            if(!this.hasTaken(state,i)){
                if(visited[p?1:0][this.take(state,i)]&&winOrLose[p?1:0][this.take(state,i)]){//如果拿完这个数就赢了
                    win = true;
                }
                else if(!visited[!p ?1:0][this.take(state,i)]){//拿完这个数还赢不了，没计算过如果是别人先走能不能赢
                    win ^= !this.canWin(this.take(state,i), winOrLose, visited,!p,num);
                }
                else{
                    win ^= !winOrLose[!p?1:0][this.take(state,i)];
                }
                if(win)break;
            }
        }
        winOrLose[0][state] = win;
        winOrLose[1][state] = win;
        visited[p?1:0][state] = true;
        visited[p?1:0][state] = true;
        return win;
    }
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int i;
        boolean[][] states = new boolean[2][(int)Math.pow(2,maxChoosableInteger)];
        boolean[][] visited = new boolean[2][(int)Math.pow(2,maxChoosableInteger)];
        int len = states.length;
        /*初始化：对于拿出来的数已经大于desiredTotal的部分，初始化为true*/
        for(i=0;i<=len-1;i++){
            if(this.isBiggerEqual(i,maxChoosableInteger,desiredTotal)){
                states[0][i] = true;
                visited[0][i] = true;
            }
        }
        for(i=0;i<=len-1;i++){
            if(this.isBiggerEqual(i,maxChoosableInteger,desiredTotal)){
                states[1][i] = true;
                visited[1][i] = true;
            }
        }
        return this.canWin(0,states,visited,false,desiredTotal);
    }
}
