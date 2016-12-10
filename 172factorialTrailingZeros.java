public class Solution {
    public int trailingZeroes(int n) {
        int re = 0;
        while(n>0){
            re+=n/5;
            n/=5;
        }
        return re;
    }
}