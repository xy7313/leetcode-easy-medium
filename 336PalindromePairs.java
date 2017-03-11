
/*
The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the input. Consider the test case of ["a", ""];

Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <. There may be duplicates in the output (consider test case ["abcd", "dcba"]). Therefore I put a str2.length()!=0 to avoid duplicates.

Another way to avoid duplicates is to use Set<List<Integer>> result = new HashSet<>(); and return new ArrayList<>(result);
*/
public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>(); 
        if (words == null || words.length < 2) return result;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i<words.length; i++) map.put(words[i], i);
        for (int i=0; i<words.length; i++) {
            // System.out.println(words[i]);
            for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    //string 没有 reverse 方法，要reverse，记住下面这行代码
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        result.add(list);
                    }
                }
                //str2.length()!=0 because we set j<=words[i].length, we count the empty string as first str1, and another empty string as last str2, we may have duplicates list.
                if (isPalindrome(str2) && str2.length()!=0) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i) { 
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) !=  str.charAt(right)){
                return false;
            } 
            left++;
            right--;
        }
        return true;
    }
}