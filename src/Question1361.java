import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;

public class Question1361 {
    /**
     * 判断一棵树是不是二叉树，先找出全部入度为0的节点，如果没有就GG，如果有多个也GG，然后拓扑排序看能不能全部确定即可
     * */
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        HashSet<Integer> start_nodes = new HashSet<>();
        for(int i=0;i<n;i++){
            start_nodes.add(i);
        }
        for(int i=0;i<n;i++){
            start_nodes.remove(leftChild[i]);
            start_nodes.remove(rightChild[i]);
        }
        if(start_nodes.size() != 1)return false;
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] confirmed = new boolean[n];
        int start = 0;
        int number_confirmed = 1;
        for(int i:start_nodes){
            start = i;
        }
        queue.addLast(start);
        confirmed[start] = true;
        while(!queue.isEmpty()){
            int current = queue.pollFirst();
            if(leftChild[current]!=-1&&confirmed[leftChild[current]])return false;
            else if(leftChild[current]!=-1){
                queue.addLast(leftChild[current]);
                confirmed[leftChild[current]] = true;
                number_confirmed++;
            }
            if(rightChild[current]!=-1&&confirmed[rightChild[current]])return false;
            else if(rightChild[current]!=-1){
                queue.addLast(rightChild[current]);
                confirmed[rightChild[current]] = true;
                number_confirmed++;
            }
        }
        return number_confirmed==n;
    }
}
