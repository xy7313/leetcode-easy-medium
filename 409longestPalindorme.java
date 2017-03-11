public class Solution {
    public int longestPalindrome(String s) {
        char[] cs = s.toCharArray();
        HashMap<Character,Integer> m= new HashMap<>();
        for(int i= 0; i<cs.length; i++){
            if(m.containsKey(cs[i])){
                m.put(cs[i],m.get(cs[i])+1);
            }else{
                m.put(cs[i],1);
            }
        }
        int sum = 0;
        int a = 0;
        for(Integer v : m.values()){
            if (v%2==0){
                sum+=v;
            }else{
                a = 1;
                sum+=(v-v%2);
            }
        }
      return sum+a;  
    }
    //more elegant
    // just count the number of same pairs, then this can be used to put in the different direction to consist of palindrome. Then if there exist more chars, we can put one in the middle.
    public int longestPalindrome(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
    }
}