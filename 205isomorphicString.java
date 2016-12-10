public class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        for(Integer i = 0; i < s.length(); i++) {
            //hm.put() returns  it's the previous value associated to this key
            System.out.println("i:"+i+"-1:"+m1.put(s.charAt(i), i)+"-2:"+m2.put(t.charAt(i), i));
            if(m1.put(s.charAt(i), i) != m2.put(t.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }
}
/*
注意，hm.put()这个方法，官方api写的是：the previous value associated with key, or null if there was no mapping for key.
test case:
    "aba" "baa"
stdout:
    i:0-1:null-2:null
    i:1-1:null-2:null
    i:2-1:0-2:1
根据输出可以看出，如果映射已经存在，会输出对应的value，在这里就是以char为key对应的value（index）
*/