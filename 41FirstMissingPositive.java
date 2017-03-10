public class Solution {
 public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0)
        {
            return 1;
        }
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length || nums[i] == nums[nums[i]-1])
            {
                continue;
            }
            swap(nums, i, nums[i]-1);
//            int temp = nums[i];
//            nums[i] = nums[nums[i] - 1];
//            nums[temp-1] = temp;
            i--;
        }

        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] != i + 1)
            {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}