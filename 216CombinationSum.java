public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> coms = new ArrayList<>();
        ArrayList<Integer> com = new ArrayList<>();
        dfs( 1, n, k, com, coms);
        return coms;
    }
     private void dfs(int startIdx, int target, int k, ArrayList<Integer> com, List<List<Integer>> coms){
        if(target==0 && com.size()==k){
            coms.add(new ArrayList<Integer>(com));
            return;
        }
        for(int i = startIdx; i<=9; i++){
            com.add(i);
            dfs(i+1,target-i,k,com,coms);
            com.remove(com.size()-1);
        }
    }
}