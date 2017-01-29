public class Solution {
    public boolean isPalindrome(String s) {
        //trim all non-char characters
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}

public class Solution {
    public boolean isPalindrome(String s) {
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if(!Character.isLetterOrDigit(s.charAt(i))) {
                j++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(j))) {
                i--;
                continue;
            }            
            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            } 
        }
        return true;
    }
}