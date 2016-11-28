public class Solution {
    public void rotate(int[] nums, int k) {
        /*
        重点就在前三行！！！
        1. 数组直接赋值是浅拷贝，int[] a = ...; int[] b = a;这种情况下，a改了b也会改动！！所以用array.clone();这个方法
        2. k>nums.length的时候需要处理的，明显是%
        3. k=0的时候不需要操作，所以操作前判断一下
        */
        int[] t = nums.clone();
        k = k%nums.length;
        if(0<k){
            int j = 0;
            for(int i = 0; i <t.length; i++){
                if(i<k){
                    nums[i] = t[t.length-k+i];
                }else{
                    nums[i] = t[j];
                    j++;
                }
            }
        }
        return;
    }
}

