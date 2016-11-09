public class Solution {
    public int getSum(int a, int b) {
        int sum = a^b;
        int carry = a&b;
        carry = carry<<1;
        while(carry!=0){
            int tmp = sum;
            sum = carry^sum;
            carry = (tmp&carry)<<1;
            // carry=carry<<1;
        }
        return sum;
    }
}

/*
sum 得到按位异或的结果，在没有进位的情况下就是我们想要的和
如果有进位，进入while循环处理仅为
*/