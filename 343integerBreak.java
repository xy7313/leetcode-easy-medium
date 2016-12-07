public class Solution {
    public int integerBreak(int n) {
        if(n<=3) return n-1;
        int re = 0;
        if(n%3==0) re = (int)Math.pow(3,n/3);
        if(n%3==1) re = (int)Math.pow(3,n/3-1)*4;
        if(n%3==2) re = (int)Math.pow(3,n/3)*2;
        return re;
    }
}