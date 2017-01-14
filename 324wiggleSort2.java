public class Solution {
      public void wiggleSort(int[] nums) {
          int len = nums.length;
        int median = findKthLargest(nums,0, len-1, len/2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        while (i <= right) {

            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++,n), newIndex(i++,n));
            }
            else if (nums[newIndex(i,n)] < median) {
                swap(nums, newIndex(right--,n), newIndex(i,n));
            }
            else {
                i++;
            }
        }
    }

    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }
    //method2
    public void wiggleSort(int[] nums) {
        /*
        利用快速排序的思想找中位数的期望时间复杂度是O(N).为了防止相等的数放在一起，需要注意放置的顺序。
        九章笔者采用的方法是依nums长度分两种情况：
            若长度为奇数，把比中位数小的依次放在0,2,4,...位置，比中位数大的依次放在length-2,length-4,... 位置；
            若长度为偶数，把比中位数小的依次放在length-2,length-4,...位置，比中位数大的依次放在1,3,5,... 位置。
            其余位置填充中位数。这样可以保证中位数一定与较小与较大的数相邻（题目保证一定有解）
        */
        if (nums == null || nums.length == 0)   return;
        int len = nums.length;
        int median = findKthLargest(nums,0, len-1, len/2);
        int[] re = new int[len];
        //necessary？
        for(int i = 0;i<len;i++){
            re[i] = median;
        }
        int s,l;
        if(len%2==0){
            s=len-2;
            l=1;
            for(int i = 0; i<len; i++){
                if(nums[i]<median){
                    re[s] = nums[i];
                    s-=2;
                }else if(nums[i]>median){
                    re[l] = nums[i];
                    l+=2;
                }
            }
        }else{
            s=0;
            l=len-2;
            for(int i = 0; i<len; i++){
                if(nums[i]<median){
                    re[s] = nums[i];
                    s+=2;
                }else if(nums[i]>median){
                    re[l] = nums[i];
                    l-=2;
                }
            }
        }
        for(int i = 0; i<nums.length; i++){
            nums[i] = re[i];
        }
        
    }
 
 //basic, general method to get medain, more details see leetcode 215
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