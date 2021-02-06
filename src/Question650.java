public class Question650 {
    //在此处复制全部，并且只通过粘贴达成一定数量的字母
    private void updateNum(int[] steps, int num, int cur_pos){
        int i = 1;
        while(cur_pos+i*cur_pos<= num){
            steps[cur_pos+i*cur_pos] = steps[cur_pos+i*cur_pos]==0?steps[cur_pos]+i+1:Math.min(steps[cur_pos]+i+1,steps[cur_pos+i*cur_pos]);
            i++;
        }
    }
    public int minSteps(int n) {
        int[] steps = new int[1010];
        int i;
        steps[1] = 0;
        if(n==1)return 0;
        for(i=1;i<=n/2+1;i++){
            this.updateNum(steps, n, i);
        }
        return steps[n];
    }
}
