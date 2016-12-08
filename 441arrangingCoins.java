public class Solution {
    //n is a non-negative integer and fits within the range of a 32-bit signed integer.
    public int arrangeCoins(int n) {
        if(n==0)return 0;
        int row = 1;
        while(n>0){
            n-=row;
            row++;
            if(n<row){
                return row=row-1;
            }
        }
        return row;
    }
}