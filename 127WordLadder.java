public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList==null || wordList.size()==0){
            return 0;
        }
        int len = 1;
        if(beginWord.equals(endWord)) return len;
        //list-->set 
        Set<String> dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        //bfs
        Queue<String> queue = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        queue.offer(beginWord);
        hash.add(beginWord);
        
        while(!queue.isEmpty()){
            len++;
            int size = queue.size();
            for(int i = 0; i<size; i++){
                String cur = queue.poll();
                //对 cur 做 bfs ，能走到的 word 必须是 dict 中的，hash确保每次 bfs 走的都是 new word
                for(String next : validNext(cur, dict)){
                    if(next.equals(endWord)) return len;
                    if(!hash.contains(next)){
                        hash.add(next);
                        queue.offer(next);
                    }
                }
                    
            }
        }
        return 0;
    }
    private ArrayList<String> validNext(String word, Set<String> wordList){
        ArrayList<String> nexts = new ArrayList<>();
        // new look of for-loop, char
        for(char c = 'a'; c<='z'; c++){
            for(int l = 0; l<word.length(); l++){
                if (c == word.charAt(l)) {
                    continue;
                }
                String next = replace(word,l,c);
                if (wordList.contains(next)) {
                    nexts.add(next);
                }
            }
        }
        return nexts;
    }
    private String replace(String word, int idx, char c){
        char[] str = word.toCharArray();
        str[idx] = c;
        return String.valueOf(str);
    }
}