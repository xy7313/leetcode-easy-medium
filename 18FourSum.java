public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null || nums.length==0){
            return results;
        }
        Arrays.sort(nums);
        for(int i = 0; i< nums.length-2; i++){
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            for(int j = i+1; j< nums.length-2; j++){
                if (j > i+1 && nums[j] == nums[j - 1]) {              // skip same result
                    continue;
                }
                int l = j+1;
                int r = nums.length-1;
                int tar = target - nums[i] -nums[j];          
                while(l<r){
                    if(nums[l]+nums[r]==tar){
                        results.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while (l<r && nums[l] == nums[l+1]) l++;
                        while (l < r && nums[r] == nums[r-1]) r--;
                        l++;
                        r--;
                    }else if(nums[l]+nums[r]>tar){
                        r--;
                    }else l++;
                }
            }
        }
        return results;
    }
}