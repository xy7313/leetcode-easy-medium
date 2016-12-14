public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> m = new HashMap<>();
        int[] re = new int[2];
        for(int i = 0; i<nums.length; i++){
            if(m.containsKey(target-nums[i])){
                re[0] = m.get(target-nums[i]);
                re[1] = i;
            }
            m.put(nums[i],i);
        }
        return re;
    }
}