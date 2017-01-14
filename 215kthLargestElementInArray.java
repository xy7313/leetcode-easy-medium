public class Solution {
    public int findKthLargest(int[] nums, int k) {
        //k<1||k>nums.length 也是边界值情况
        if(nums==null||nums.length==0) return Integer.MAX_VALUE;
        return findKthLargest(nums,0,nums.length-1,nums.length-k);
    }
    public int findKthLargest(int[] nums, int start, int end, int k){
        if(start>end) return Integer.MAX_VALUE;

        int pivot = nums[end];
        int left = start;
        for(int i = start; i<end; i++){
            //第一次默写这里少了if，对，默写，这个算法并不能完全理解自己再写出来，只能多写几次，装作是想出来的然后写出来
            if (nums[i] <= pivot){
                swap(nums,left,i);
                left++;
            }
        }
        swap(nums,left,end);
        if(left==k) return nums[left];
        if(left<k) return findKthLargest(nums,left+1,end,k);
        else return findKthLargest(nums,start,left-1,k);
    }
    public void swap(int[] n, int i, int j){
        int tmp = n[i];
        n[i] = n[j];
        n[j] = tmp;
    }
}