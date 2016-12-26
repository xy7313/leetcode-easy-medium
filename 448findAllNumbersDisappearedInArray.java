public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> l = new ArrayList<>();
        for(int i = 0; i<nums.length; i++){
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }
        for(int a = 0; a<nums.length;a++){
            if(nums[a]>0) l.add(a+1);
        }
        return l;
    }
}