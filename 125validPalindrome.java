public class Solution {
    public boolean isPalindrome(String s) {
        //trim all non-char characters
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
}