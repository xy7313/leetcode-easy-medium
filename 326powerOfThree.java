public class Solution {
    public boolean isPowerOfThree(int n) {
    // 1162261467 is 3^19,  3^20 is bigger than int，任何一个3的x次方一定能被int型里最大的3的x次方整除，  
    return ( n>0 &&  1162261467%n==0);
    }
}