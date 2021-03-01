public class Question896 {
    /**
     * 判断一个数列是不是单调的
     * */
    public boolean isMonotonic(int[] A) {
        boolean un_increase = false;//是不是单调不增数列
        if(A[0]>=A[A.length-1]){
            un_increase = true;
        }
        int current = A[0];
        for(int i=1;i<A.length;i++){
            if(un_increase){
                if(A[i]>current)return false;
            }else{
                if(A[i]<current)return false;
            }
        }
        return true;
    }
}
