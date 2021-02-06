import java.util.LinkedList;

public class Question45 {
    class state{
        int pos;//当前位置
        int jumps;//已经跳了几下了
        public state(){
            super();
        }
        public state(int cur_pos, int num_jmp){
            this.pos = cur_pos;
            this.jumps = num_jmp;
        }
    }
    public int jump(int[] nums) {
        LinkedList<state> jump_queue = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        jump_queue.addLast(new state(0,0));
        while(!jump_queue.isEmpty()){
            state cur_state = jump_queue.pollFirst();
            if(cur_state.pos==nums.length-1){
                return cur_state.jumps;
            }
            int longestDist = nums[cur_state.pos];
            int i;
            for(i=1;i<=longestDist;i++){
                if(cur_state.pos+i<nums.length&&!visited[cur_state.pos+i]){
                    jump_queue.addLast(new state(cur_state.pos+i,cur_state.jumps+1));
                    visited[cur_state.pos+1]=true;
                }
            }
        }
        return -1;
    }
}
