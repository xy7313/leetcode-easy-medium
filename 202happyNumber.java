public class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) return false;
        while(n>=10){
            int sum = 0;
            //确保算了每一位的平方
            while(n!=0){
                sum +=(n%10)*(n%10);
                n/=10;
            }
            //sum是每一位的平方的和
            n=sum;
        }
        //只有==1和==7才是happyNumber
        return n == 1 || n == 7; 
    }
}