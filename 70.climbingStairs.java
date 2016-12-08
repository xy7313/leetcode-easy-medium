public class Solution {
    public int climbStairs(int n) {
        int[] step = new int[n];
        for(int i = 0; i<n;i++){
            if(i==0){
                step[i]=1;
            }else if(i==1){
                step[i]=2;
            }else{
                step[i] = (step[i-1])+(step[i-2]);
            }
        }
        return step[n-1];
    }
}