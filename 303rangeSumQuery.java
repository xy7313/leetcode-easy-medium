public class NumArray {
    private int[] n;
    public NumArray(int[] nums) {
        this.n = nums;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for(int a = 0; a<n.length;a++){
            if(a==i){
                for(int b = a; b<=j;b++){
                    sum +=n[b];
                }
            }
        }
        return sum;
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);

public class NumArray {
    int[] nums;

    public NumArray(int[] nums) {
        for(int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];
        
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        if(i == 0)
            return nums[j];
        
        return nums[j] - nums[i - 1];
    }
}