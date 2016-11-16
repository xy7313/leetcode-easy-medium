public class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        for(int i = 0; i <s.length();i++){
            if(!m.containsKey(s.charAt(i))){ 
                m.put(Character.valueOf(s.charAt(i)),1);
            }else{
                int tmp = (m.get(Character.valueOf(s.charAt(i)))).intValue()+1;
                m.put(Character.valueOf(s.charAt(i)),Integer.valueOf(tmp));
            }
        }
        char[] sc = s.toCharArray();
        int re = s.length();
        for(Character k: m.keySet()){
            if (m.get(k)==1){
                if( s.indexOf(Character.valueOf(k)) <re){
                    re=s.indexOf(Character.valueOf(k));
                }
                
            }
        }
        return re==s.length()? -1:re ;
    }
    //大神的方法，把小寫字母對應成1-26作為索引
    public int firstUniqChar(String s) {
        int[] lookup = new int[26];
        for(int i = 0 ; i < s.length(); i++){
            lookup[s.charAt(i) - 'a']++; 
        }
        for(int i = 0 ; i < s.length(); i++){
            if(lookup[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}