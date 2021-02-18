import java.util.HashMap;

public class Question765 {
    private int getSet(int[] parent, int x){
        if(parent[x]!=x){
            parent[x] = getSet(parent, parent[x]);
        }
        return parent[x];
    }
    private void merge(int[] parent, int x, int y){
        int root_x = getSet(parent, x);
        int root_y = getSet(parent, y);
        parent[root_y] = root_x;
    }
    /**
     * 力扣第765题：情侣牵手。本质上是一道并查集的题
     * */
    public int minSwapsCouples(int[] row) {
        int length = row.length;
        int[] parent = new int[length/2];
        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }
        for(int i=0;i<length;i+=2){
            int left = row[i]/2;//坐在[i,i+1]左边的人的情侣编号
            int right = row[i+1]/2;//右边的人的情侣编号
            merge(parent, left, right);//这两对情侣相关
        }
        HashMap<Integer, Integer> map = new HashMap<>();//记录每一个情侣集合的大小
        int result = 0;
        for(int i=0;i<parent.length;i++){
            int set = getSet(parent, i);
            if(!map.containsKey(set))map.put(set, 0);
            map.put(set, map.get(set)+1);
            result++;
        }
        return result-map.size();
    }
}
