//立马就想到的方法，但是这个题不是最优，follow up版(input sorted array)也不是最优。。。还有binary search
public class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for(int i = 0; i<citations.length; i++){
            if(citations[i]>=(citations.length-i)) return citations.length-i;
        }
        return 0;
    }
}
// use extra space to get more faster alg, Time complexity: O(n)
public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        if(len==0) return 0;
        int[] re = new int[len+1];
        for(int i = 0; i<len; i++){
            if(citations[i]>len) re[len]++;
            else re[citations[i]]++;
        }
        int sum = 0;
        for(int i = len; i>0; i--){
            sum+=re[i];
            if(sum>=i) return i;
        }
        return 0;
    }
}