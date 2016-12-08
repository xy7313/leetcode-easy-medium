public class Solution {
    public boolean isPowerOfFour(int num) {
        // return Integer.toString(num, 4).matches("10*");
        // return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
        return (num&(num-1))==0 && num>0 && (num-1)%3==0;
    }
}