
#### ##有个总结贴
http://www.sigmainfy.com/blog/summary-of-ksum-problems.html

目录：

1. Two Sum
167. Two Sum II - Input array is sorted
15. 3Sum
16. 3Sum Closest
18. 4Sum

另：4sum2属于binary search和hashmap，combination sum系列属于DFS（其中一小个follow-up属于DP）





#### 1. Two Sum
这类题可以算是刷题的动力吧，看过一次下一次居然真的会了，改了一个；错误提交居然过了，hashmap，O(n)
（后有改进版，arr in order，用hashmap太慢，用双指针，从前和从后同时查找，167题）

#### 167. Two Sum II - Input array is sorted
1题改进版，双指针更快，从前和后同时查找，注意while判断条件，我写的l<=r考虑到会有【1，2，4】t=4就会需要2+2，另外遇到匹配项记得跳出循环。

#### 15. 3Sum
看起来很简单的题，思路也知道，然而就是写不对。。。本来想写个函数调用调2sum，但可能对list的处理还是不太好，后来重新写到一个方法里了。

1. 思路 ：把题目要求的三个数和==0.转换成2sum=-第三个数. Note the triplets consists of real values in the input rather than the index of the input numbers
2. 代码 ：
    1. 首先要sort，
    2. 其次停止在length-2的位置就可以，
    3. 然后 left从i+1开始。 
    4. 再有，匹配成功时候的一个arrays方法：`Arrays.asList()`，
    5. 以及这个while，为了避免重复的代码，可能会被忽略掉
    6. 另外刚进入for循环还需要 去重 ：`if (i > 0 && nums[i] == nums[i - 1]) continue;`
```
public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null || nums.length==0){
            return results;
        }
        Arrays.sort(nums);
        for(int i = 0; i< nums.length-2; i++){
            // avoid duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            //left start from i+1
            int l = i+1;
            int r = nums.length-1;
            int target = 0 - nums[i];          
            while(l<r){
                if(nums[l]+nums[r]==target){
                    results.add(Arrays.asList(nums[i],nums[l],nums[r]));
                    //while: avoid duplicates too
                    while (l<r && nums[l] == nums[l+1]) l++;
                    while (l < r && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                }else if(nums[l]+nums[r]>target){
                    r--;
                }else l++;
            }
        }
        return results;
    }
```

#### 16. 3Sum Closest
大体思路跟上面差不多，这个题的target是明确的，不需要再变换target出来，但是需要额外的两个辅助变量，diff， closestSum。

1. diff： 要最小diff，所以每次当前sum和target差值变小，都需要更新
2. closest:  每次更新diff也要一起更新当前最closest sum
```
while(l<r){
    int sum = nums[i]+nums[l]+nums[r];
    if (Math.abs(sum-target) < diff) {
        diff = Math.abs(sum-target);
        closest = sum;
    }
    if(sum==target){
        return target;
    } else if(sum-target>0){
        r--;
    }else{
        l++;
    }
}
```

#### 18. 4Sum
翻了翻，大部分都选择了转换成3sum再转换成2sum的方法，时间复杂度: O(n^3)
```
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null || nums.length==0){
            return results;
        }
        Arrays.sort(nums);
        for(int i = 0; i< nums.length-2; i++){
            if (i > 0 && nums[i] == nums[i - 1]) {              // skip same result
                continue;
            }
            for(int j = i+1; j< nums.length-2; j++){
                if (j > i+1 && nums[j] == nums[j - 1]) {              // 注意这里对应要改动一些
                    continue;
                }
                int l = j+1;
                int r = nums.length-1;
                int tar = target - nums[i] -nums[j];          
                while(l<r){
                    if(nums[l]+nums[r]==tar){
                        results.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
                        while (l<r && nums[l] == nums[l+1]) l++;
                        while (l < r && nums[r] == nums[r-1]) r--;
                        l++;
                        r--;
                    }else if(nums[l]+nums[r]>tar){
                        r--;
                    }else l++;
                }
            }
        }
        return results;
    }
}
```
