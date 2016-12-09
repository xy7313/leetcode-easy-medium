public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while(n != 0){
            n = n & (n-1);
            count++;
        }
        return count;
    }
//     public static int hammingWeight(int n) {
// 	    int ones = 0;
// 	    //n&1 means the rightest bit of n is 1
//     	while(n!=0) {
//             //ones = ones + (n & 1);
//             //if这种方法比上面那行慢一倍
//             if((n&1)==1) ones++;
//     		n = n>>>1;
//     	}
//     	return ones;
// }
}