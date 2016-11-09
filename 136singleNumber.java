public class Solution {
    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i <nums.length;i+=2){
            if(nums[i-1]-nums[i]!=0){
                return nums[i-1];
            }
        }
        return nums[nums.length-1];
    }
}

public int singleNumber(int[] nums) {
        Map<Integer, Boolean> m = new HashMap<Integer, Boolean>();
        for(int i = 0; i <nums.length;i++){
            if(!m.containsKey(nums[i])){
                m.put(Integer.valueOf(nums[i]),true);
            }else{
                m.put(Integer.valueOf(nums[i]),false);
            }
        }
                for(int i = 0; i <nums.length;i++){
                    if(m.get(Integer.valueOf(nums[i]))){
                        return nums[i];
                    }
                }
        return nums[nums.length-1];
    }

/*
array 排序的方法：（排序时间复杂度nlogn）
Arrays.sort(nums);

如果有多个singlenumber，
题目中没有描述清楚，面试遇到了应该问清楚
这里就看test case和expected output意思
要输出数组最后一个元素


hashmap的方法：（这个时间复杂度会好一点）
注意放入的key是nums[i]
int--Integer的类型转换用Integer.valueOf(nums[i])
*/