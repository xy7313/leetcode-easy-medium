public class Solution {
    public int countSegments(String s) {
        if(s.length()==0||s==null) return 0;
        int re = 0;
        for(int i=0; i<s.length(); i++){
            //  if(s.charAt(i)!=' '){
            //     if(i==0||s.charAt(i-1)==' '){
            //         re++;
            //     }
            // }
            if((i==0 || s.charAt(i-1)==' ') && s.charAt(i)!=' ')
                re++;
        }
        return re;
    }
}