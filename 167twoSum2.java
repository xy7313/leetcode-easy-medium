public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length-1;
        int[] re = new int[2];
        while(l<=r){
            int sum = numbers[l]+numbers[r]; 
            if(sum == target){
                re[0]=l+1;
                re[1] = r+1;
                break;
            }else if( sum<target ){
                l++;
            }else{
                r--;
            }
        }
        return re;
        
    }
}