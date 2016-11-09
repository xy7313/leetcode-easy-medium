public class Solution {
    public String reverseString(String s) {
        char[] tmp = s.toCharArray();
        char[] re = new char[tmp.length];
        for(int i = 0;i<tmp.length;i++){
            re[i] = tmp[tmp.length-1-i];
        }
        return String.valueOf(re);
    }
}

/*
注意这个的用法：char[] 转成string
String.valueOf(re);


*/