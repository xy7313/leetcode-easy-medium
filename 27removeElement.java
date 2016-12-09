public class Solution {
    public int removeElement(int[] nums, int val) {
        // if(nums.length==0||nums==null) return 0;
        int re = 0;
        for(int i = 0; i<nums.length;i++){
            if (nums[i]!=val) {
                nums[re] = nums[i];
                re++;
            }
        }
        return re;
    }
}