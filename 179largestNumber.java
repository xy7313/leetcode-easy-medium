public class Solution {
    //注释里1，2，3是另一套代码，从小到大排然后从后向前拼接，记得if判断也要改成判断array里的最后一位
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
                // 1. return s1.compareTo(s2);
                return s2.compareTo(s1);
            }
        });
        //2. if (strs[str.length-1].charAt(0) == '0') return "0";
        if (strs[0].charAt(0) == '0') return "0";
        String res = new String();
        for (int i = 0; i < strs.length; i++) {
            //3. res=strs[i]+res;
            res += strs[i];
        }
        return res;
    }
    //sort+compare 那里也可以这么写
    /*
		Comparator<String> comp = new Comparator<String>(){
		    @Override
		    public int compare(String str1, String str2){
		        String s1 = str1 + str2;
			String s2 = str2 + str1;
			return s2.compareTo(s1); // reverse order here, so we can do append() later
		    }
	        };
            Arrays.sort(s_num, comp);     
    */
}