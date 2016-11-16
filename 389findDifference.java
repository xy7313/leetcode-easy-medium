public class Solution {
    //mark这题，审题有个问题，要计数的，并不是简单的哪个字母出现过哪个没出现过，而且每个字母出现的次数也得算，不一致的字母被返回
    //比如 ab aab 返回a，下面是hashmap实现的比较麻烦的方法
    public char findTheDifference(String s, String t) {
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        for(int i = 0; i <s.length();i++){
            if(!m.containsKey(s.charAt(i))){ 
                m.put(Character.valueOf(s.charAt(i)),1);
            }else{
                int tmp = (m.get(Character.valueOf(s.charAt(i)))).intValue()+1;
                m.put(Character.valueOf(s.charAt(i)),Integer.valueOf(tmp));
            }
        }
        
        for(int j = 0; j <t.length();j++){
            System.out.println(Character.valueOf(t.charAt(j)));
              if(!m.containsKey(t.charAt(j))){
                    return t.charAt(j);
              }else{
                  int tmp = (m.get(Character.valueOf(t.charAt(j)))).intValue()-1;
                System.out.println(Character.valueOf(t.charAt(j))+"v:"+m.get(Character.valueOf(t.charAt(j))));

                  if(tmp<0){
                      return t.charAt(j);
                  }
                  m.put(Character.valueOf(t.charAt(j)),Integer.valueOf(tmp)); 
              }
        }
        return 0;
        }
    //一种简单的方法
    public char findTheDifference(String s, String t) {
        char re = 0;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        for(int i = 0; i < sc.length; i++  ){
            re -= sc[i];
        }
         for(int j = 0; j < tc.length; j++  ){
            re += tc[j];
        }
        return re;
    }

