public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> re = new ArrayList<Integer>();     // creating a new List
        for(int i=0;i<nums.length;i++){  
            int idx =Math.abs(nums[i]);  
            if(nums[idx-1]<0){
                re.add(Math.abs(nums[i]));
            }else{
                nums[idx-1]=-nums[idx-1];
            }
        }
        return re;
    }
}