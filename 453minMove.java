//自己好不容易而写了个解，然而这个用例跑不过，其他的目前都还行[1,2147483647]，总之应该是复杂度太高吧
public class Solution {
    public int minMoves(int[] nums) {
        int re = 0;
        boolean notEqu = true;
        boolean in = true;
        
        while(notEqu){
            int a = 0;
            for(int i = 0; i<nums.length-1;i++){
                 if((nums[i])>Integer.MAX_VALUE){
                    notEqu = false;
                    break;
                }
                if (nums[i]!=nums[i+1]){
                    a++;
                    break;
                }
            }
            if(a==0) notEqu = false;
            if(notEqu==false) return re;
            Arrays.sort(nums);
            for(int j = 0; j<nums.length-1;j++){
                    nums[j]++;
            }
            re++;
        }
        return re;
    }
}
//大神的方法，把除最大一个元素以外都+1的概念转换成，把最大值-1,每个值和最小值的差值就是他变成最小值所需要的操作数，
public class Solution {
    public int minMoves(int[] nums) {
        int re = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <nums.length;i++){
            if(nums[i]<min){
                min = nums[i];
            }
        }
        for(int n:nums){
            re+=(n-min);
        }
        return re;
    }
}