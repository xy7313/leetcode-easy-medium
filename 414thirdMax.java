public class Solution {
    public int thirdMax(int[] nums) {
        long f = Long.MIN_VALUE;
        long s = Long.MIN_VALUE;
        long t = Long.MIN_VALUE;
        for(int i = 0; i< nums.length; i++){
            if(nums[i]==t||nums[i]==f||nums[i]==s) continue;
            if(nums[i]>t) t = nums[i];
            if(t>s){
                long tmp = s;
                s=t;
                t=tmp;
            }
            if(s>f){
                long t2 = f;
                f=s;
                s=t2;
            } 
        }
        System.out.println(t);
        return t==Long.MIN_VALUE? (int)f:(int)t;
    }
}
/*
    看起来越是简单的题坑越多，按发现问题的顺序
    1. 最大值赋给first后，原first值变为second，注意别丢了
    2. 重复元素不计数，所以遇到重复元素直接跳过，不然影响结果
    3. 根据测试用例来看，需要long
*/