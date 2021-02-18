public class Question995 {
    private void modify(int[] arr, int pos, int interval){
        for(int i=0;i<interval;i++){
            arr[pos+i] ++;
        }
    }
    public int minKBitFlips(int[] A, int K) {
        int[] diff = new int[A.length];
        int sum = 0;
        for(int i=0;i<A.length;i++){
            if((diff[i]+A[i]%2)==0){
                if(i+K<A.length){
                    modify(diff, i, K);
                    sum++;
                }
            }
            if((diff[i]+A[i]%2)==0){
                return -1;
            }
        }
        return sum;
    }
}
