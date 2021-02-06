import java.util.LinkedList;
import java.util.List;

public class Question1462 {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        int[][] graph = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                graph[i][j] = 0x70000000;
            }
        }
        /*建图，准备使用floyd算法*/
        for(int[] request: prerequisites){
            graph[request[0]][request[1]] = 1;
        }
        /*floyd 算法主干*/
        for(int k = 0; k<n; k++){//中间节点
            for(int i = 0;i<n; i++){
                for(int j = 0; j<n;j++){
                    if(graph[i][j]>graph[i][k]+graph[k][j]){
                        graph[i][j] = graph[i][k]+graph[k][j];
                    }
                }
            }
            for(int line = 0; line<n;line++){
                for(int i:graph[line]){
                    System.out.print(i+" ");
                }
                System.out.println();
            }
        }
        LinkedList<Boolean> result = new LinkedList<>();
        for(int[] query:queries){
            result.addLast(graph[query[0]][query[1]] < 0x70000000);
        }
        return result;
    }
}
