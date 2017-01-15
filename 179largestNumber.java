public class Solution {
     public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i]+"";
        }
        //s2.compareTo(s1) sort array 9-0, s1.compareTo(s2) sort array 0-9
        //String.compareTo(String s) return <0 this<argument:s,
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String i, String j) {
                String s1 = i+j;
                String s2 = j+i;
                // return s1.compareTo(s2);
                return s2.compareTo(s1);
            }
        });
        //if (strs[str.length-1].charAt(0) == '0') return "0";
        if (strs[0].charAt(0) == '0') return "0";
        String res = new String();
        for (int i = 0; i < strs.length; i++) {
            //res=strs[i]+res;
            res += strs[i];
        }
        return res;
    }
}