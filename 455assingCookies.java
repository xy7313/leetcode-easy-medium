public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int op = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0; 
        int j = 0;
        while(i < g.length && j < s.length){
                if(g[i]>s[j]){
                    j++;
                }else{
                    op++;
                    i++;
                    j++;
                }
            }
        
        return op;
    }
}