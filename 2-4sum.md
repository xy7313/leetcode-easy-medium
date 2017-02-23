
######有个总结贴
http://www.sigmainfy.com/blog/summary-of-ksum-problems.html


####1. Two Sum
这类题可以算是刷题的动力吧，看过一次下一次居然真的会了，改了一个；错误提交居然过了，hashmap
（后有改进版，arr in order，用hashmap太慢，用双指针，从前和从后同时查找，167题）

####167. Two Sum II - Input array is sorted
1题改进版，双指针更快，从前和后同时查找，注意while判断条件，我写的l<=r考虑到会有【1，2，4】t=4就会需要2+2，另外遇到匹配项记得跳出循环。

####15. 3Sum
看起来很简单的题，思路也知道，然而就是写不对。。。本来想写个函数调用调2sum，但可能对list的处理还是不太好，后来重新写到一个方法里了。

1. 思路 ：把题目要求的三个数和==0.转换成2sum=-第三个数
2. 代码 ：首先要sort，其次停止在length-2的位置就可以，最后 left从i+1开始。 还有一个点是匹配成功时候的一个arrays方法，以及这个while，为了避免重复的代码，可能会被忽略掉
```
if(nums[l]+nums[r]==target){
    results.add(Arrays.asList(nums[i],nums[l],nums[r]));
    while (l<r && nums[l] == nums[l+1]) l++;
    while (l < r && nums[r] == nums[r-1]) r--;
    l++;
    r--;
}
```