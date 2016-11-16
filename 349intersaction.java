public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        ArrayList<Integer> re = new ArrayList<Integer>();
        for (int j = 0; j < nums2.length; j++){
            for(int i = 0; i <nums1.length;i++){
                if (nums1[i]==nums2[j]){
                    if(!re.contains(nums2[j])){
                        re.add(nums2[j]);
                    }
                }
            }        
        }
        return convert(re);
    }
    //convert
    public int[] convert(ArrayList<Integer> IntegerList) {
        int[] intArray = new int[IntegerList.size()];
        int count = 0;
        for(int i : IntegerList){
            intArray[count++] = i;
        }
        return intArray;
    }
   
}