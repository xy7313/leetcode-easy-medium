A new start coding leetcode problems from yesterday. -- 07/14

## First part: Binary search 

#### Problem list:
```
33 Search in Rotated Sorted Array
34 Search for a Range
35 Search Insert Position
69 Sqrt(x)
74 Search a 2D Matrix
81 Search in Rotated Sorted Array II
153 Find Minimum in Rotated Sorted Array
154 Find Minimum in Rotated Sorted Array II
240 Search a 2D Matrix II
```


##### 33 Search in Rotated Sorted Array
1. first of all, conner case
2. while(start+1<end) deal with start and end separately in the end
3. do normal binary search in the sorted part,
    ```
    public int search(int[] nums, int target) {
        if(nums==null||nums.length==0)return -1;
        int start = 0, end = nums.length-1;     
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid]==target) return mid;
            if(nums[start]<nums[mid]){
                if(nums[start]<=target&&target<=nums[mid]) end = mid;
                else start = mid;
            }else{
                //if(nums[end] >= target && target >= nums[mid]) start = mid;
                //else end = mid;
                 if(target>=nums[start] || target<=nums[mid]) end = mid;
                else start = mid;
            }
        }
        if(nums[start]==target) return start;
        if(nums[end]==target) return end;
        return -1;
    }
    ```

##### 34 Search for a Range
    ```
    public int[] searchRange(int[] nums, int target) {
        if(nums==null || nums.length==0) return new int[]{-1,-1};
        int start = 0;
        int end = nums.length-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid]>=target) end = mid;
            else start = mid;    
        }
        int left = nums[start]==target? start:(nums[end]== target? end:-1);
        start = 0;
        end = nums.length-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid]<=target) start = mid;
            else end = mid;    
        }
        int right = nums[end]==target? end:(nums[start]== target? start:-1);
        
        return new int[]{left,right};
    }
    ```

##### 35 Search Insert Position

##### 69 Sqrt(x)

##### 74 Search a 2D Matrix

##### 81 Search in Rotated Sorted Array II

##### 153 Find Minimum in Rotated Sorted Array

##### 154 Find Minimum in Rotated Sorted Array II

##### 240 Search a 2D Matrix II
