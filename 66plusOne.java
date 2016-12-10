public class Solution {
    public int[] plusOne(int[] digits) {
       int carry = 1;
        for (int i = digits.length-1; i>= 0; i--) {
            digits[i] += carry;
            if (digits[i] <= 9) return digits;
            digits[i] = 0;
        }
        //如果一直到这里都没有返回，说明是需要在最前面加一位1的
        int[] ret = new int[digits.length+1];
        ret[0] = 1;
        return ret;
    }
}