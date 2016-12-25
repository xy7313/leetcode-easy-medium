/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    //根据题目可以想到 二分查找，但是并不会写二分,遇到过类似的，sliding window的问题438
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while(start<end){
            int mid = start+(end-start)/2;
            //all the versions after a bad version are also bad，所以如果mid没有，mid前肯定都没有，查找后一半
            if(!isBadVersion(mid)) start = mid+1;
            else end = mid;
        }
        return start;
    }
}