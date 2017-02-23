public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null || nums.length==0){
            return results;
        }
        Arrays.sort(nums);
        for(int i = 0; i< nums.length-2; i++){
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            int l = i+1;
            int r = nums.length-1;
            int target = 0 - nums[i];          
            while(l<r){
            if(nums[l]+nums[r]==target){
                    results.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    while (l<r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                }else if(nums[l]+nums[r]>target){
                    r--;
                }else l++;
            }
        }
        return results;
    }
}