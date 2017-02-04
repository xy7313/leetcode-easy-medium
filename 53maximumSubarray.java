public class Solution {
    public int maxSubArray(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        for(int i : nums){
            sum += i;
            max = Math.max(max, sum-minSum);
            minSum = Math.min(minSum, sum);
        }
        return max;
    }
}