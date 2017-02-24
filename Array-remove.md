####27. Remove Element
if(nums[i]!=val) {nums[newLength]=nums[i];newLength++;}

####26. Remove Duplicates from Sorted Array
需要直接改变原数组，加一个新的计数器顺便当做index，这种方法好多array都用了，比如上面的27，这俩代码几乎一模一样就是移除的东西不同
```
 int n = 1;
for(int i = 1;i<nums.length;i++){
    if(nums[i]!=nums[i-1]){
        nums[n]=nums[i];
        n++;
    }
}
```

####80. Remove Duplicates from Sorted Array II
这个题可以背了,下面代码分别是保留1个原数和保留两个原数，返回都是新数组长度
```
public int removeDuplicates(int[] nums) {
    int i = 0;
    for(int n : nums)
        if(i < 1 || n > nums[i - 1]) 
            nums[i++] = n;
    return i;
}
```
```
public int removeDuplicates(int[] nums) {
   int i = 0;
   for (int n : nums)
      if (i < 2 || n > nums[i - 2])
         nums[i++] = n;
   return i;
}
```
