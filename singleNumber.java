public class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i <nums.length;i+=2){
            if(nums[i-1]-nums[i]!=0){
                return nums[i-1];
            }
        }
        return nums[nums.length-1];
    }
}



/*
array 排序的方法：
Arrays.sort(nums);

如果有多个singlenumber，
题目中没有描述清楚，面试遇到了应该问清楚
这里就看test case和expected output意思
要输出数组最后一个元素

*/