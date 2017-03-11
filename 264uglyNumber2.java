public class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int multiply2 = 2, multiply3 = 3, multiply5 = 5;
        int index2 = 0, index3 = 0, index5 = 0;
        ugly[0] = 1;
        for(int i = 1; i<n;i++){
            int min = Math.min(Math.min(multiply2,multiply3),multiply5);
            ugly[i] = min;
            if(min==multiply2){
                index2++;
                multiply2=2*ugly[index2];
            }
            if(min==multiply3){
                index3++;
                multiply3=3*ugly[index3];
            }
            if(min==multiply5){
                index5++;
                multiply5=5*ugly[index5];
            }
        }
        return ugly[n-1];
    }
   
}