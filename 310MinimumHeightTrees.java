public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
    // if (n == 1) return Collections.singletonList(0);

    List<Integer> leaves = new ArrayList<>();
    if (n==1) {
		leaves.add(0);
		return leaves;
	}
	//idx: node, element-set: neighbors
    List<Set<Integer>> adj = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
        adj.add(new HashSet<>());
    }
    for (int[] edge : edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }
    //only one neighbors --> leaves
    for (int i = 0; i < n; i++) {
        if (adj.get(i).size() == 1){
            leaves.add(i);
        } 
    }
    //核心思路在这个while里，一个node的所有edge相连node都排除之后还剩一条edge连另一个node，这个就可以当做min height tree的root了
    while (n > 2) {
        //n = nodes - leaves
        n -= leaves.size();
        List<Integer> newLeaves = new ArrayList<>();
        for (int i : leaves) {
            int j = adj.get(i).iterator().next();
            adj.get(j).remove(i);
            if (adj.get(j).size() == 1) {
                newLeaves.add(j);
            }
        }
        leaves = newLeaves;
    }
    return leaves;
}
}