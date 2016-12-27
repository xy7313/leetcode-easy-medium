//又用了hashmap的方法，

public class Solution {
    public int[] singleNumber(int[] nums) {
        int[] re = new int[2];
        Map<Integer, Boolean> m = new HashMap<Integer, Boolean>();
        for(int i = 0; i <nums.length;i++){
            if(!m.containsKey(nums[i])){
                m.put(Integer.valueOf(nums[i]),true);
            }else{
                m.put(Integer.valueOf(nums[i]),false);
            }
        }
        int r = 0;
        // for(int i = 0; i <nums.length;i++){
        //     if(m.get(Integer.valueOf(nums[i]))){
        //         // return nums[i];
        //         re[r] = nums[i];
        //         r++;
        //     }
        // }
        for(Integer a: m.keySet()){
            if(m.get(a)){
                // return nums[i];
                re[r] = a;
                r++;
            }
        }
        return re;
    }
}

//discuss方法：Find the rightmost set bit, divide numbers into two groups. Each group will end up being one unique number.

public class Solution {
   public int[] singleNumber(int[] nums) {
    int result[] = new int[2];        
    int xor = nums[0];
    for (int i=1; i<nums.length; i++){
        xor ^= nums[i];
    }
    
    int bit = xor & ~(xor-1);
    int num1 = 0;
    int num2 = 0;
    
    for (int num : nums){
        if ((num & bit) > 0) num1 ^= num;
        else num2 ^= num;
    }
    result[0] = num1;
    result[1] = num2;
    return result;
    }
}