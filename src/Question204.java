public class Question204 {
    /*计算小于等于n的数中，质数的个数*/
    public int countPrimes(int n) {
        if(n<=1)return 0;
        int[] primes = new int[n+1];
        int i;
        int count = 0;
        for(i=2;i<n;i++){
            if(primes[i]==0){//是质数，把后面的筛掉
                count++;
                int cur = i*2;
                while(cur<n){
                    primes[cur] = 1;
                    cur+=i;
                }
            }
        }
        return count;
    }
}
