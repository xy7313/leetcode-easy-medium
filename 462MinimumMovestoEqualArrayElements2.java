public class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        int count = 0;
        while(i < j){
            //跟之前那个minMove思路类似，先排序，然后从大到小和从小到大一起+，- 直到得到同一值
            //比如1，2，3，4，5，5-1= 5-->3的步数 + 1-->3的步数
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        return count;
    }
}