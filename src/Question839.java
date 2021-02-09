import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Question839 {
    class Edge{
        int i,j;
        Edge(int a, int b){
            i = a;
            j = b;
        }
    }
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
     * compare two string to see whether they have only two difference
     * @param s string one
     * @param t string two
     * @return rt
     * */
    private boolean compareStr(String s, String t){
        int num_diff = 0;
        int len = s.length();
        for(int i=0;i<len;i++){
            if(s.charAt(i)!=t.charAt(i))num_diff++;
        }
        return num_diff==2;
    }

    public int numSimilarGroups(String[] strs) {
        int[] parent = new int[strs.length];
        int num_branch = strs.length;
        LinkedList<Edge> edges= new LinkedList<>();
        for(int i=0; i<parent.length;i++){
            parent[i] = i;
        }
        for(int i=0;i<strs.length;i++){
            for(int j=i;j<strs.length;j++){
                if(compareStr(strs[i], strs[j])){
                    edges.add(new Edge(i,j));
                    edges.add(new Edge(j,i));
                }
            }
        }
        while(!edges.isEmpty()){
            Edge e = edges.pollFirst();
            if(getSet(parent, e.i)!=getSet(parent, e.j)){
                merge(parent, e.i, e.j);
                num_branch --;
            }
        }
        return num_branch;
    }
}
