import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Question990 {
    class pair{
        int first;
        int second;
        public pair(int a, int b){
            this.first = a;
            this.second = b;
        }
    }
    /**
     * 合并两个集合
     * @param parent 并查集相关
     * @param i 集合i
     * @param j 集合j
     */
    private void merge(int[] parent, int i, int j){
        int root_i = this.getSet(parent, i);
        int root_j = this.getSet(parent, j);
        parent[root_j] = root_i;
    }

    /**
     * 得到一个节点所属的集合
     * @param parent 并查集信息
     * @param i 目标节点
     * @return 所属的集合
     */
    private int getSet(int[] parent, int i){
        if(parent[i]!=i){
            parent[i] = this.getSet(parent, parent[i]);
        }
        return parent[i];
    }
    public boolean equationsPossible(String[] equations) {
        /*初始化并查集*/
        int[] parent = new int[26];
        int i=0;
        for(i=0;i<26;i++)parent[i]=i;
        LinkedList<pair> equals = new LinkedList<>();
        LinkedList<pair> unequals = new LinkedList<>();
        int num_equations = equations.length;
        for(i=0;i<num_equations;i++){
            if(equations[i].charAt(1)=='!'){//这是不等关系
                unequals.add(new pair((equations[i].charAt(0)-'a'),(equations[i].charAt(3)-'a')));
            }
            else{
                equals.add(new pair((equations[i].charAt(0)-'a'),(equations[i].charAt(3)-'a')));
            }
        }
        for(pair p: equals){
            merge(parent, p.first, p.second);
        }
        for(pair p:unequals){
            if(getSet(parent, p.first)==getSet(parent, p.second))return false;
        }
        return true;
    }
}
