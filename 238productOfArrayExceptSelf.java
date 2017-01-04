public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] re = new int[nums.length];
        int left = 1;
        for(int i = 0; i < nums.length; i++){
            if (i>0){
                left = left*nums[i-1];
            }
            re[i] = left;
        }
        int right = 1;
        for(int j = nums.length-1; j >=0; j-- ){
            if (j<nums.length-1){
                right = right*nums[j+1];
            }
            re[j]=re[j]*right;
        }
        return re;
    }
}
//上面是第一次做题的答案，下面是第二次做题的答案，回想起了当时做题的思路，再加上例子帮助，第二次的代码自己写的
//注意算right的时候不能简写省去right，因为此时re已经有左边的值了，不能像上面处理左边一样直接用re[i-1]/re[i+1]
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] re = new int[nums.length];
        int i = 0;
        re[0]=1;
        for( i = 1; i < nums.length; i++){
            re[i] = re[i-1]* nums[i-1];
        }
        int right = 1;
        for( i = nums.length-2; i >=0; i--){
            right *=nums[i+1];
            re[i]*= right;
        }
        return re;
    }
}