public class Solution {
    public boolean isNumber(String s) {
        if(s==null||s.length()==0) return false;
        s = s.trim();
        char[] cs =  s.toCharArray();
        
        boolean point = false;
        boolean e = false;
        boolean number = false;
        boolean numberAfterE = true;
        
        for(int i = 0; i<cs.length; i++){
            if('0' <= cs[i] && cs[i] <= '9') {
                number = true;
                //下面这句好神奇，为啥在这里==true，大概是因为一开始是没有e，所以这项就可以是true，恩
                numberAfterE = true;
            } else if(cs[i] == '.') {
                if(e || point) {
                    return false;
                }
                point = true;
            } else if(cs[i] == 'e') {
                if(e || !number) {
                    return false;
                }
                numberAfterE = false;
                e = true;
            } else if(cs[i] == '-' || cs[i] == '+') {
                if(i != 0 && cs[i-1] != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return number && numberAfterE;
    }
}


