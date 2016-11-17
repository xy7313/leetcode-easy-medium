public class Solution {
    public boolean isAnagram(String s, String t) {
       int[] lookup = new int[26];
        for(char i: s.toCharArray()){
            lookup[i - 'a']++; 
        }
        for(char j: t.toCharArray()){
            lookup[j - 'a']--;
        }
        for(int l : lookup){
            if(l!=0){
                return false;
            }
        }
        return true;
    }
}