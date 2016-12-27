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