import java.util.HashMap;
import java.util.LinkedList;

public class Question407 {
    private int getSet(int[] parent, int i){
        if(parent[i] != i){
            parent[i] = getSet(parent, parent[i]);
        }
        return parent[i];
    }
    private void merge(int[] parent, int i, int j){
        int root_i = getSet(parent, i);
        int root_j = getSet(parent, j);
        parent[root_i] = root_j;
    }
    public int trapRainWater(int[][] heightMap) {
        int height = heightMap.length;
        int width = heightMap[0].length;
        int[][] vertical_height = new int[height][width];
        int[][] level_height = new int[height][width];
        /*calculate vertical height*/
        for(int j = 0;j<width;j++){
            int[] parent = new int[height];
            for(int i = 0;i<height;i++){
                parent[i] = i;
            }
            LinkedList<Integer> stack = new LinkedList<>();
            int current_maximum = 0;
            for(int i=0;i<height;i++){
                // update the highest height on above
                if(stack.isEmpty()){
                    stack.addLast(i);
                    current_maximum = Math.max(current_maximum, heightMap[i][j]);
                }else{
                    while(!stack.isEmpty()&&heightMap[stack.peekLast()][j]<heightMap[i][j]){
                        if(heightMap[stack.peekLast()][j]<heightMap[i][j]){
                            int position = stack.pollLast();
                            merge(parent, position, i);// record the first pillar taller on below
                        }
                    }
                    current_maximum = Math.max(current_maximum, heightMap[i][j]);
                    stack.addLast(i);
                }
                vertical_height[i][j] = current_maximum;// update the highest height on above
            }
            // update the highest height on below
            for(int i=0;i<height;i++){
                vertical_height[i][j] = Math.min(vertical_height[i][j], heightMap[getSet(parent,i)][j]);
            }
        }
        /*calculate level height*/
        for(int i = 0;i<height;i++){
            int[] parent = new int[width];
            for(int j=0;j<width;j++){
                parent[j] = j;
            }
            LinkedList<Integer> stack = new LinkedList<>();
            int current_maximum = 0;
            for(int j=0;j<width;j++){
                if(stack.isEmpty()){
                    stack.add(j);
                    current_maximum = Math.max(current_maximum, heightMap[i][j]);
                }else{
                    while(!stack.isEmpty()&&heightMap[i][stack.peekLast()]<heightMap[i][j]){
                        if(heightMap[i][stack.peekLast()]<heightMap[i][j]){
                            int position = stack.pollLast();
                            merge(parent, position, j);
                        }
                    }
                    current_maximum = Math.max(current_maximum, heightMap[i][j]);
                    stack.addLast(j);
                }
                level_height[i][j] = current_maximum;
            }
            for(int j=0;j<width;j++){
                level_height[i][j] = Math.min(level_height[i][j], heightMap[i][getSet(parent, j)]);
            }
        }
        /*calculating paddles and summarize the height*/
        /*level_height will be used to remember the height of water theoretically*/
        System.out.println("modified level_height");
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                int temp = Math.min(level_height[i][j], vertical_height[i][j]);
                level_height[i][j] = Math.max(temp, heightMap[i][j]);
                System.out.print("("+level_height[i][j]+","+(level_height[i][j]-heightMap[i][j])+")");
            }
            System.out.println();
        }
        HashMap<Integer, Integer> paddle_height = new HashMap<>();
        int[][] belonging = new int[height][width];
        boolean[][] visited = new boolean[height][width];
        int current_id = 1;
        int current_height = Integer.MAX_VALUE;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(level_height[i][j]>heightMap[i][j]&&!visited[i][j]){
                    LinkedList<Integer> h_queue = new LinkedList<>();
                    LinkedList<Integer> w_queue = new LinkedList<>();
                    h_queue.addLast(i);
                    w_queue.addLast(j);
                    visited[i][j] = true;
                    current_height = Math.min(current_height, level_height[i][j]);
                    while(!h_queue.isEmpty()&&!w_queue.isEmpty()){
                        int h = h_queue.pollFirst();
                        int w = w_queue.pollFirst();
                        current_height = Math.min(current_height, level_height[h][w]);
                        belonging[h][w] = current_id;
                        if(h-1>=0&&heightMap[h-1][w]<level_height[h-1][w]&&!visited[h-1][w]){
                            h_queue.addLast(h-1);
                            w_queue.addLast(w);
                            visited[h-1][w]=true;
                        }
                        if(h+1<height&&heightMap[h+1][w]<level_height[h+1][w]&&!visited[h+1][w]){
                            h_queue.addLast(h+1);
                            w_queue.addLast(w);
                            visited[h+1][w]=true;
                        }
                        if(w+1<width&&heightMap[h][w+1]<level_height[h][w+1]&&!visited[h][w+1]){
                            h_queue.addLast(h);
                            w_queue.addLast(w+1);
                            visited[h][w+1]=true;
                        }
                        if(w-1>=0&&heightMap[h][w-1]<level_height[h][w-1]&&!visited[h][w-1]){
                            h_queue.addLast(h);
                            w_queue.addLast(w-1);
                            visited[h][w-1]=true;
                        }
                    }
                    paddle_height.put(current_id, current_height);
                    current_id++;
                }
            }
        }
        System.out.println("paddles");
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                System.out.print(belonging[i][j]);
            }
            System.out.println();
        }
        System.out.println(paddle_height);
        /*visit belonging and calculate trapped water*/
        int trapped = 0;
        for(int i=0;i<height;i++){
            for(int j=0;j<height;j++){
                if(belonging[i][j]!=0){
                    int temp = paddle_height.get(belonging[i][j])-heightMap[i][j];
                    trapped+= temp;
                }
            }
        }
        return trapped;
    }
}
