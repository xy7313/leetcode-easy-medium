public class Solution {
    public boolean isPowerOfTwo(int n) {
        // power of 2 only contains one 1, (power of 2)-1 is only consist of 1(ex:8=1000,7=111)
        return n>0 && (n&(n-1))==0;
    }
}