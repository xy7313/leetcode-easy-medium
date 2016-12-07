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