public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> coms = new ArrayList<>();
        if(candidates==null || candidates.length==0){
            return coms;
        }
        //结果里每种combination都只能出现一次，又每个数字使用次数不限，所以需要去重
        int[] nums = removeDuplicates(candidates);
        ArrayList<Integer> com = new ArrayList<>();
        dfs( 0, target,nums,com, coms);
        return coms;
    }
    private void dfs(int startIdx, int target, int[] candidates, ArrayList<Integer> com, List<List<Integer>> coms){
        if(target==0){
            coms.add(new ArrayList<Integer>(com));
            return;
        }
        for(int i = startIdx; i<candidates.length; i++){
            if(target<candidates[i]) break;
            com.add(candidates[i]);
            // System.out.println(target-candidates[i]);
            dfs(i,target-candidates[i],candidates,com,coms);
            com.remove(com.size()-1);
        }
        
    }
    private int[] removeDuplicates(int[] cands){
        Arrays.sort(cands);
        int idx = 1;
        for(int i = 1; i<cands.length; i++){
            if(cands[i]!=cands[i-1]){
                cands[idx] = cands[i];
                idx++;
            }
        }
        
        int[] nums = new int[idx];
        for(int i = 0; i<nums.length; i++){
            nums[i] = cands[i];
        }
        return nums;
    }
}