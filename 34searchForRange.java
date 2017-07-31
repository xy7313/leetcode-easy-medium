public class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length==0) return new int[]{-1,-1};
        int start = 0;
        int end = nums.length-1;
        //search left
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid]>=target) end = mid;
            else start = mid;    
        }
        int left = nums[start]==target? start:(nums[end]== target? end:-1);
        //search right
        start = 0;
        end = nums.length-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid]<=target) start = mid;
            else end = mid;    
        }
        int right = nums[end]==target? end:(nums[start]== target? start:-1); 
        return new int[]{left,right};
    }
}