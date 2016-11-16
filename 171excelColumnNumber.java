public class Solution {
    public int titleToNumber(String s) {
        char[] sc = s.toCharArray();
        int re = 0;
        for(int i = sc.length-1;i>=0; i--){
            // 次方啊，簡直！！
            re+=(sc[i]-'A'+1)*Math.pow(26,sc.length-i-1);
        }
        return re;
    }
}