public class Solution {
    public int strStr(String haystack, String needle) {
        //可直接用indexOf()，但感觉不是本题目的，这个discuss区大牛代码，第二个i+j不太懂
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
        
    }
}
//在自己原有思路上，参考了一个js代码写出来的，js坑死人啊，对数组下标要求不严，但是java就会一直有outofindex的报错，心塞，但最后还是ac了
public class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length()==0||needle=="") return 0;
        if(haystack==""||needle.length()>haystack.length()) return -1;
  
        for(int n = 0;n<haystack.length();n++){
            int i = n;
            int j = 0;
            while(haystack.charAt(i)==needle.charAt(j)){
                if(j==(needle.length()-1)) return n;
                j++;
                i++;
                //needle里有haystack中没有的字符
                if(i>haystack.length()-1) return -1;
            }
        }
        return -1;
    }
}