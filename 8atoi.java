public class Solution {
    public int myAtoi(String str) {
        //1. null, empty string 
        if(str==null||str.length()==0){
            return 0;
        }
        //2. remove whitespaces: trim() returns a copy of the string, with leading and trailing whitespace omitted.
        str = str.trim();
        //3. +/- sign
        boolean negative = false;
        int i = 0;
        if(str.charAt(0)=='-'){
            negative = true;
            i++;
        }else if (str.charAt(0)=='+'){
            i++;
        }
        //4. true value
        double sum = 0;
        for( ; i <str.length(); i++){
            // int digit = str.charAt(i) - '0'; this is the same as next line
            int digit = str.charAt(i) - 48;
            if(digit<0||digit>9){
                break;
           }else{
               if(negative){
                   sum = sum*10-digit;
                   if(sum<Integer.MIN_VALUE){
                      return Integer.MIN_VALUE; 
                   } 
               }else{
                   sum = sum*10+digit;
                   if(sum> Integer.MAX_VALUE) {
                       return Integer.MAX_VALUE;
                   }
               }
               
           }
       }
       return (int)sum;
    }
}

