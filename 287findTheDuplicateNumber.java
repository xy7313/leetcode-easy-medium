public class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = (int) (low + (high - low) * 0.5);
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) cnt++;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
}

public class Solution {
    public int findDuplicate(int[] nums) {
        if (nums.length > 1)
        {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast)
            {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            System.out.println(slow+"-"+fast);
            fast = 0;
            while (fast != slow)
            {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }
}

public class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int missing = nums.length;
        for(int i = 0;i<nums.length-1;i++){
            if ((nums[i+1]-nums[i])==0){
                return nums[i];
            }
        }
        return missing;   
    }
}