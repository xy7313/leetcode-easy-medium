public class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        str = str.trim();
        String[] parts = str.split(" ");
        //如果两者长度不同，pattern肯定不同，所以直接返回
        if(pattern.length()!=parts.length) return false;
        /*
        for循环是通用的核心代码，就是看映射关系是否一致，
        比如pattern存入map的时候，如果key已存在，会返回key对应的value，即pattern中key-Character对应的value-index，
        同理parts存入map时，如果key-parts[i]已存在，会返回对应value-i
        */
        for(Integer i = 0; i < pattern.length(); i++) {
            if(m1.put(pattern.charAt(i), i) != m2.put(parts[i], i)) {
                return false;
            }
        }
        return true;
    }
}
//其实就是用了205. Isomorphic Strings的代码