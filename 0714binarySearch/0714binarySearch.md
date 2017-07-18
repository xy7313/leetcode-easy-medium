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
Notice: when do binary search in the rotated part, it should be smaller than or equal and bigger than or equal. Do not forget the equal.
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
For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
Solution: search the left index, search the right index. 很像 Last Position of Target 和 First Position of Target 结合起来的方法。 find the first position of target, marked as left_index, if cannot find, return new int[]{-1,-1}; find last position of target, marked as right\_index as well, return new int[]{left_index, right_index}; 
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
可以用二分的模板，返回值比较麻烦。有几个例子： [1,3],2 return 1; [1,3],0 return 0;[1,3],4 return 2; [1],1 return 0; 

Notice: the last step, it should be target<=nums[start] based on the given example.
```
public int searchInsert(int[] nums, int target) {
    if(nums==null || nums.length==0) return -1;
    int start = 0;
    int end = nums.length-1;
    while(start+1<end){
        int mid = start+(end-start)/2;
        if(nums[mid]==target) return mid;
        else if(nums[mid]>target) end = mid;
        else start = mid;
    }
    if(target>nums[end]) return end+1;
    else if(target<=nums[start]) return start;
    else return start+1;
}
```

##### 69 Sqrt(x)
用模板，注意：从1开始；返回较小的start；input x==0 单独算；

Notice: it will have an error when we use int directly. Declare variables as long and cast the answer to int when return. Return start because we always want a the smaller result.
    ```
    public int mySqrt(int x) {
        if(x==0) return 0;
        long start = 1;
        long end = x;
        while(start+1<end){
            long mid = start+(end-start)/2;
            if(mid*mid==x){
                return (int) mid;
            } else if(mid*mid>=x){
                end = mid;
            } else {
                start = mid;
            }            
        }
        return (int)start;
    }
    ```

##### 74 Search a 2D Matrix
This matrix has the following properties:
- Integers in each row are sorted from left to right.
- The first integer of each row is greater than the last integer of the previous row.

- Solution: binary search, convers the nth number to matrix[n/col][n%col]. check start and end together after binary search because we are just supposed to return a boolean.
- Notice: we should check `if(matrix[0]==null||matrix[0].length==0) return false;` too.
    ```
      public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0) return false;
        if(matrix[0]==null || matrix[0].length==0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row*col-1;
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(matrix[mid/col][mid%col]==target){
                return true;
            }else if(matrix[mid/col][mid%col]>target){
                end = mid;
            }else{
                start = mid;
            }
        }
        if(matrix[start/col][start%col] == target ||matrix[end/col][end%col] == target){
            return true;
        }
        return false;          
    }
    ```

##### 81 Search in Rotated Sorted Array II
- as long as mid != end, not worst case. If worst case like: 00010000, we want to find 1, the time complexity will be O(n)
- binary search(this problem also can be solved using Divide conquer)
    ```
     public boolean search(int[] nums, int target) {
        if(nums==null||nums.length==0)return false;
        int start = 0, end = nums.length-1;     
        while(start+1<end){
            int mid = start+(end-start)/2;
            if(nums[mid]==target) return true;
            if(nums[start]<nums[mid]){
                if(nums[start]<=target&&target<=nums[mid]) end = mid;
                else start = mid;
            }else if (nums[start]>nums[mid]){
                //if(nums[end] >= target && target >= nums[mid]) start = mid;
                //else end = mid;
                if(target>=nums[start] || target<=nums[mid]) end = mid;
                else start = mid;
            }else{
                start++;
            }
        }
        if(nums[start]==target || nums[end]==target) return true;
        
        return false;
    
    }
    //or just go through the array,O(n), have the same worst case time complexity     
    ```



##### 153 Find Minimum in Rotated Sorted Array
easy~
```
public int findMin(int[] nums) {
    if (nums == null || nums.length==0) return -1;
    int start = 0;
    int end = nums.length-1;
    while(start+1<end){
        int mid = start+(end-start)/2;
        if(nums[mid]<nums[mid-1]){
            return nums[mid];
        }else if (nums[mid]>nums[start] && nums[mid]>nums[end]){
            start = mid;
        }else{
            end = mid;
        }
    }  
    return nums[start]<nums[end]?nums[start]:nums[end];
}
```

##### 154 Find Minimum in Rotated Sorted Array II
divide conquer: determine if this part is an ascending sequence
```
public class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return dc(nums, 0, nums.length - 1);
    }

    public int dc(int[] nums, int s, int e) {
        if (s > e) {
            return Integer.MAX_VALUE;
        }
        if (s == e) {
            return nums[s];
        }
        if (s + 1 == e) {
            return Math.min(nums[s], nums[e]);
        }
        if (nums[s] < nums[e]) {
            return nums[s];
        }

        int m = s + (e - s) / 2;
        int left = dc(nums, s, m);
        int right = dc(nums, m + 1, e);
        return Math.min(left, right);
    } 
}
```

##### 240 Search a 2D Matrix II
This matrix has the following properties:
- Integers in each row are sorted in ascending from left to right.
- Integers in each column are sorted in ascending from top to bottom.

Each time we exclude a col or a row.
```
   public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0) return false;
        if(matrix[0]==null || matrix[0].length==0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int x = matrix.length-1;
        int y = 0;
        while(x>=0 && y<matrix[0].length){
           if(matrix[x][y]==target){
               return true;
           }else if(matrix[x][y]>target){
               x--;
           }else if (matrix[x][y]<target){
               y++;
           }
        }
        return false;
    }
```
