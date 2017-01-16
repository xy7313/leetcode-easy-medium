//given sorted array, require O(lgn)，搞清楚要求的话很容易想到binary search
public class Solution {
    public int hIndex(int[] citations) {
        int start = 0;
        int end = citations.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(citations[mid]==(citations.length-mid)) return citations.length-mid;
            else if(citations[mid]<(citations.length-mid)) start = mid+1;
            else end = mid-1;
        }
        return citations.length-start;
    }
}
//input: [0,1,2,4,5,6]
//output: 3
//最后一行的return就是为了保证这种情况能正确输出，3并不在arr中