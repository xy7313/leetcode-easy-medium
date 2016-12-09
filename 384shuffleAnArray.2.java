public class Solution {
    private int[] original;
    
    public Solution(int[] nums) {
       this.original = nums;       
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
//从0-i 之间选一个随机位置，把original[i]插入，把原位置的值放在后面（也就是shuffled[i]）位置，存着，直到把所有original中每个值都插入一遍

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuffled = new int[original.length];
        for (int i = 0; i < original.length; i++){
            int r = (int) (Math.random() * (i+1));
            shuffled[i] = shuffled[r];
            shuffled[r] = original[i];
        }
        return shuffled;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

 //！！
 //由于leetcode太蠢会因为system.out.println而提交超时的缘故，这个问题我之前的代码其实也是work的，以下
 public class Solution {
    private Map<Integer,Integer> m;
    private int[] original;
    private int[] shuffled;
    
    public Solution(int[] nums) {
        m = new HashMap<>();
        original = new int[nums.length];
        shuffled = new int[nums.length];

        for(int i = 0;i<nums.length;i++){
            m.put(i,nums[i]);
            original[i] = nums[i];
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        Map<Integer,Integer> ran = new HashMap<>();
        int i = 0;
        while(i<original.length){
            int idx = (int) Math.floor(Math.random()*(original.length));
            if(ran.containsKey(idx)){
                continue;
            }else{
                shuffled[i] = original[idx];
                ran.put(idx,0);
                i++;
            }
        }
        return shuffled;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */