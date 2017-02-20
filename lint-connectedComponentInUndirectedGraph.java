/**
 * Definition for Undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        Map<UndirectedGraphNode, Boolean> visited = new HashMap<>();
        for(UndirectedGraphNode node : nodes){
            visited.put(node,false);
        }
        List<List<Integer>> ccs = new ArrayList<>();
        for(UndirectedGraphNode node : nodes){
            if(!visited.get(node)){
                bfs(node, visited, ccs);
            }
        }
        return ccs;
    }
    private void bfs(UndirectedGraphNode node, Map<UndirectedGraphNode, Boolean> visited, List<List<Integer>> ccs){
        List<Integer> cc = new ArrayList<Integer>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        visited.put(node,true);
        while(!queue.isEmpty()){
            UndirectedGraphNode cur = queue.poll();
            cc.add(cur.label);
            for(UndirectedGraphNode neighbor: cur.neighbors){
                if(!visited.get(neighbor)){
                    queue.offer(neighbor);
                    visited.put(neighbor,true);
                }
            }
        }
        //这里有个强制排序，不知道为啥，lintcode不加不能ac
        Collections.sort(cc);
        ccs.add(cc);
    }
}