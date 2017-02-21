public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //idx of edges: node, element(arraylist) of edges: node.neighbors
        int[] indegree = new int[numCourses];
        List[] edges = new List[numCourses];
        // initialization: each node has a list to store its neighbors
        for (int i = 0;i < numCourses; i++){
            edges[i] = new ArrayList<Integer>();
        }
        //eg:pair[0,1] means to take course 0 you have to first take course 1,0-indegree=1
        for(int i = 0; i<prerequisites.length; i++){
            indegree[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        //add all nodes with indegree==0, no prerequisites needed
        Queue<Integer> queue = new LinkedList();
        for(int i = 0; i < indegree.length; i++){
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        //bfs from nodes above, update indegree once visited
        int count = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            count ++;
            int n = edges[course].size();
            for(int i = 0; i < n; i++){
                int pointer = (int)edges[course].get(i);
                indegree[pointer]--;
                if (indegree[pointer] == 0) {
                    queue.add(pointer);
                }
            }
        }
        //if it has cycle, there will be some node with indegree>0 left, return false, and count<numCourse
        return count == numCourses;
    }
}