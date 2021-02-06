public class Question50 {
    private double fastPow(double x, long n){
        if(n==0)return 1;
        double halfWay = fastPow(x, n/2);
        return n%2==0?halfWay*halfWay:halfWay*halfWay*x;
    }
    public double myPow(double x, int n) {
        long N = n;
        return N>=0?fastPow(x, N):1/fastPow(x, -N);
    }
}
