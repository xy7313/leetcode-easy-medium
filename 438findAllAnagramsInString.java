public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] ch = new int[26];
        List<Integer> re = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) return re;
        //ch stores the letters and the number of each letter in p 
        for (char c : p.toCharArray()){
            ch[c-'a']++;
        }
        //initial a sliding window
        int start = 0, end = 0, count = p.length();
        while (end < s.length()) {
            if(ch[s.charAt(end)-'a']>0){
                count--;
            }
            ch[s.charAt(end)-'a']--;
            end++;
            // count==0 means all chars in p showed
            if(count==0){
                re.add(start);
            }
            if(end-start==p.length()){
                if (ch[s.charAt(start)-'a'] >= 0) {
                    count++;
                }
                ch[s.charAt(start)-'a']++;
                start++;
            }

        }
        return re;
    }
}
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> re = new ArrayList<>();
        int len = p.length();
        if (s == null || p == null || s.length() < p.length()) return re;
        for(int i = 0; i+len<=s.length();i++){
            if(isAnagrams(s.substring(i,i+len),p)){
                re.add(i);
            }
        }
        return re;
    }
    public boolean isAnagrams(String subs, String p){
        if(subs.equals(p)) return true;
        int[] re = new int[26];
        for(char a:subs.toCharArray()){
            re[a-'a']++;
        }
        for(char b:p.toCharArray()){
            re[b-'a']--;
        }
        for(int i : re){
            if(i!=0) return false;
        }
        return true;
    }
}

