public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if(nums==null || nums.length==0){
            return 0;
        }
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE,closest = 0;
        for(int i = 0; i< nums.length-2; i++){
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            int l = i+1;
            int r = nums.length-1;
            while(l<r){
                int sum = nums[i]+nums[l]+nums[r];
                if (Math.abs(sum-target) < diff) {
                    diff = Math.abs(sum-target);
                    closest = sum;
                }
                if(sum==target){
                   return target;
                } else if(sum-target>0){
                    r--;
                }else{
                    l++;
                }
            }
        }
        return closest;
    }
}