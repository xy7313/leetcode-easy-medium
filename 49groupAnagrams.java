public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hm = new HashMap<>();
        for(int i = 0; i < strs.length; i++){
            String currString = strs[i];
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String sortString = new String(charArr);
            if (!hm.containsKey(sortString)){
                hm.put(sortString, new ArrayList<String>());
            } 
            hm.get(sortString).add(currString);
        }
        return new ArrayList<>(hm.values());
    }  
}