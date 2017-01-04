public class Solution {
    // public int missingNumber(int[] nums) {
    //     int sum = nums.length;
    //     for(int i = 0; i<nums.length;i++){
    //         sum = sum+(i-nums[i]);
    //     }
    //     return sum;
    // }
//     public int missingNumber(int[] nums) {
//     int result = 0;
//     for(int i = 0; i < nums.length; i++){
//         result += i+1-nums[i];
//     }
//     return result;
// }
//下面这种方法最好理解，所以选了这种，即1+2+。。。+（index+1）（其实也就是length）与nums[0]+nums[1]+...的差值
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int num: nums)
            sum += num;
        return (nums.length * (nums.length + 1) )/ 2 - sum;
    }
    
}