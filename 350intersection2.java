public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> re = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        //类似指针的思路，需要排序，所以不算最优解
        while(i<nums1.length&&j<nums2.length){
                if(nums1[i]<nums2[j]){
                    i++;
                }else if(nums1[i]>nums2[j]){
                    j++;
                }else{
                    re.add(nums1[i]);
                    i++;
                    j++;
                    // break;
                }
        }        
        int[] result = new int[re.size()];
        int ind = 0;
        for(Integer a : re){
            result[ind]=a;
            ind++;
        }
        return result;
    }
}