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
}