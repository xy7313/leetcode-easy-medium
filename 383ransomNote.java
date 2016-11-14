public class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> m = new HashMap<Character, Integer>();
        for(int i = 0; i <magazine.length();i++){
            if(!m.containsKey(magazine.charAt(i))){ 
                m.put(Character.valueOf(magazine.charAt(i)),1);
            }else{
                int tmp = (m.get(Character.valueOf(magazine.charAt(i)))).intValue()+1;
                m.put(Character.valueOf(magazine.charAt(i)),Integer.valueOf(tmp));
            }
        }
        
        for(int j = 0; j <ransomNote.length();j++){
              if(!m.containsKey(ransomNote.charAt(j))){
                    return false;
              }else{
                  int tmp = (m.get(Character.valueOf(ransomNote.charAt(j)))).intValue()-1;
                  if(tmp<0){
                      return false;
                  }
                  m.put(Character.valueOf(ransomNote.charAt(j)),Integer.valueOf(tmp)); 
              }
        }
        return true;
    }
}
//跟刚才的389 findDifference 基本一毛一样，就是注意谁包含谁