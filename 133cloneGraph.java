/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        //get all nodes
        Set<UndirectedGraphNode> nodes = getNodes(node);
        
        //mapping old nodes to new
        Map<UndirectedGraphNode,UndirectedGraphNode> mapping = new HashMap<>();
        for(UndirectedGraphNode n : nodes){
            mapping.put(n,new UndirectedGraphNode(n.label));
        }
        
        //set all neighbors, all nodes have been newed when constructed mapping, so all newNodes, newNighbors do not need to be redeclare again, just mapping.get(), we get the new node
        Map<UndirectedGraphNode, Set<UndirectedGraphNode>> graph = new HashMap<>();
        for(UndirectedGraphNode n : nodes){
            UndirectedGraphNode newN = mapping.get(n);
            for(UndirectedGraphNode neighbor : n.neighbors){
                UndirectedGraphNode newNeighbor = mapping.get(neighbor);
                newN.neighbors.add(newNeighbor);
            }
        }
        
        //so smart return statement!!
        return mapping.get(node);
    }
    private Set<UndirectedGraphNode> getNodes(UndirectedGraphNode node){
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> hash = new HashSet<UndirectedGraphNode>();
        
        queue.offer(node);
        hash.add(node);
        while(!queue.isEmpty()){
            for(int i = 0; i<queue.size(); i++){
                UndirectedGraphNode cur = queue.poll();
                for(UndirectedGraphNode neighbor: cur.neighbors){
                    if(!hash.contains(neighbor)){
                        queue.offer(neighbor);
                        hash.add(neighbor);
                    }
                }
                
            }
        }
        return hash;
    }
}