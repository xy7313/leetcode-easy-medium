public class Solution {
   public String addStrings(String num1, String num2) {
        int len1=num1.length()-1;
        int len2=num2.length()-1;
        
        StringBuilder sb=new StringBuilder();
        int carry=0;
        while(len1>=0 || len2>=0) {
            int x=len1<0?0:num1.charAt(len1)-'0';
            int y=len2<0?0:num2.charAt(len2)-'0';
            carry+=x+y;
            if(carry<=9){
                sb.insert(0,carry);
                carry=0;
            } else {
                sb.insert(0,carry%10);
                carry=1;
            }
            len1--;
            len2--;
        }
    if(carry==1)sb.insert(0,"1");
    return sb.toString();
    }
}