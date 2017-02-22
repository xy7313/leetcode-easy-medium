public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        List<List<Integer>> coms = new ArrayList<>();
        if(candidates==null || candidates.length==0){
            return coms;
        }
        Arrays.sort(candidates);
        ArrayList<Integer> com = new ArrayList<>();
        dfs( 0, target,candidates,com, coms);
        return coms;
    }
    private void dfs(int startIdx, int target, int[] candidates, ArrayList<Integer> com, List<List<Integer>> coms){            
        if(target==0){
            coms.add(new ArrayList<Integer>(com));
            return;
        }
        for(int i = startIdx; i<candidates.length; i++){
            if(i!=startIdx && candidates[i]==candidates[i-1]) continue;
            if(target<candidates[i]) break;
            com.add(candidates[i]);
            dfs(i+1,target-candidates[i],candidates,com,coms);
            com.remove(com.size()-1);
        }
    }
}