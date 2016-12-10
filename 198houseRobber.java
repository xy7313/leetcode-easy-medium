public class Solution {
    public int rob(int[] nums) {
        if(nums.length==0||nums==null) return 0;
        if(nums.length==1) return nums[0];
        int[] money = new int[nums.length];
        for(int i = 0; i<nums.length;i++){
            if(i==0){
                money[i] = nums[i];
            }else if(i==1){
                money[i]= Math.max(nums[i],nums[i-1]);
            }else{
                money[i] = Math.max(nums[i]+money[i-2],money[i-1]);
            }
        }
    return money[nums.length-1];    
    }
}