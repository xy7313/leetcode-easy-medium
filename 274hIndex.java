//立马就想到的方法，但是这个题不是最优，follow up版也不是最优。。。
public class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        for(int i = 0; i<citations.length; i++){
            if(citations[i]>=(citations.length-i)) return citations.length-i;
        }
        return 0;
    }
}