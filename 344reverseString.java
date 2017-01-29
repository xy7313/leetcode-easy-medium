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

//下面这个方法是 in place
public class Solution {
    public String reverseString(String s) {
        char[] str = s.toCharArray();
        int start = 0;
        int end = str.length-1;
        for (int i = start, j = end; i < j; i++, j--) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
        return String.valueOf(str);
    }
}