public class Solution {
    public void moveZeroes(int[] nums) {
        int in = 0;
        for(int i = 0; i<nums.length; i++){
            if (nums[i]!=0){
                nums[in] = nums[i];
                in++;
            }
        }
        for( ; in<nums.length; in++){
            nums[in]=0;
        }
    }
}