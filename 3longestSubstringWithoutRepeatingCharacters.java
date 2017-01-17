public class Solution {
    //第一种方法最容易懂，start end means start of set and end of set. we always keep a substing without repeating characters in the set, the substring's length may change, so the set.size also changes, we keep track to find the max size.
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }
    //和上题思路一样，但用的是hashmap存储数据
    public int lengthOfLongestSubstring(String s) {
            char[] cofS = s.toCharArray();
            Map<Character, Integer> m= new HashMap<Character,Integer>();
            int maxLen = 0,lastIdx = -1;
            for(int i = 0; i < cofS.length; i++){
                if(m.containsKey(cofS[i])&&(m.get(cofS[i])>lastIdx)){
                    lastIdx = m.get(cofS[i]);
                }
                maxLen = Math.max(maxLen,i-lastIdx);
                m.put(cofS[i],i);
            }
            return maxLen;
        }
        //用新数组存储，虽然看起来比较牛逼，但个人觉得不如set，hashmap这两种简明实用
    public int lengthOfLongestSubstring(String s) {  
        int length = s.length();  
        if (length == 0) {  
            return 0;  
        }  
        int [] countTable = new int[256];  
        Arrays.fill(countTable, -1);  
        int max = 1;  
        int start = 0;  
        int end = 1;  
          
        countTable[s.charAt(0)] = 0;  
        while (end < length) {  
            //Has not reached a duplicate char  
            if (countTable[s.charAt(end)] >= start) {  
                start = countTable[s.charAt(end)] + 1;                
            }  
            max = Math.max(max, end - start + 1);  
            countTable[s.charAt(end)] = end;  
            end++;  
        }  
        return max;  
    }  
}