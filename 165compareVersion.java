public class Solution {
    public int compareVersion(String version1, String version2) {
        //注意以 . perioud 为分隔符时候的写法
        String[] s1 = version1.split("[.]");
        String[] s2 = version2.split("\\.");

        int i = 0;
        while(i<s1.length||i<s2.length){
            int temp1 = 0, temp2 = 0;
            if (i < s1.length) {
                temp1 = Integer.parseInt(s1[i]);
            }
            if (i < s2.length) {
                temp2 = Integer.parseInt(s2[i]);
            }
            if (temp1 > temp2) {
                return 1;
            } else if (temp1 < temp2) {
                return -1;
            }
            i++;
        }
        return 0;
    }
    
}