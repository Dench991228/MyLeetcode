import java.util.Arrays;
import java.util.Comparator;

public class Question1579 {
    /**
     * merges two disjoint set
     * @param parent the array which stores information about disjoint set
     * @param x a disjoint set
     * @param y a disjoint set
     * */
    private void merge(int[] parent, int x, int y){
        int root_x = getSet(parent, x);
        int root_y = getSet(parent, y);
        parent[root_y] = root_x;
    }

    /**
     * get the information about the disjoint set of an element
     * @param parent the disjoint set element position belongs to
     * @param position the element inquired
     * @return the disjoint set element belongs to
     * */
    private int getSet(int[] parent, int position){
        if(parent[position] != position){
            parent[position] = getSet(parent, parent[position]);
        }
        return parent[position];
    }

    /**
     * 使用类似于kruscal算法的方式来确定连通性
     * */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] parent_alice = new int[n+1];
        int[] parent_bob = new int[n+1];
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return -o1[0]+o2[0];
            }
        });
        for(int i=0; i<=n;i++){
            parent_alice[i] = i;
            parent_bob[i] = i;
        }
        int num_abandon_edges = 0;
        int num_connected_bob = 0;
        int num_connected_alice = 0;
        for(int[] edge:edges){
            switch (edge[0]){
                case 3://common edge for both people
                    if(getSet(parent_alice, edge[1])!=getSet(parent_alice, edge[2])){
                        merge(parent_alice, edge[1], edge[2]);
                        merge(parent_bob, edge[1], edge[2]);
                        num_connected_alice ++;
                        num_connected_bob ++;
                    }else{
                        num_abandon_edges ++;
                    }
                    break;
                case 2:// an edge for bob
                    if(getSet(parent_bob, edge[1])!=getSet(parent_bob, edge[2])){
                        merge(parent_bob, edge[1], edge[2]);
                        num_connected_bob ++;
                    }else{
                        num_abandon_edges ++;
                    }
                    break;
                case 1:
                    if(getSet(parent_alice, edge[1])!=getSet(parent_alice, edge[2])){
                        merge(parent_alice, edge[1], edge[2]);
                        num_connected_alice ++;
                    }else{
                        num_abandon_edges ++;
                    }
                    break;
            }
        }
        if(num_connected_alice!=n||num_connected_bob!=n){
            return num_abandon_edges;
        }else{
            return -1;
        }
    }
}
