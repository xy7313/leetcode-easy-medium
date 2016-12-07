public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        /*
        若序列S为等差数列，其长度为N，则其等差数列切片的个数SUM = 1 + 2 + ... + (N - 2), 例如，等差数列[1, 2, 3, 4, 5, 6]的切片个数为1+2+3+4 = 10
        */
        int cur = 0, sum = 0;
        for(int i=0;i<A.length-2;i++){
            if(A[i+1]-A[i]==A[i+2]-A[i+1]){
                cur += 1;
            }else{
                cur = 0;
            }
            sum+=cur;
        }
        return sum;
    }
}

