BFS+Union-find+follow up

##(前五个都是BFS+Union-find)

200. Number of Islands
0. Number of Islands II
1. Graph Valid Tree
2. Connected Component in Undirected Graph
128. Longest Consecutive Sequence 

>还有个    130. Surrounded Regions ，先拉黑

####200. Number of Islands
1. 选择方法BFS: 每次我遇到1，都bfs遍历所有他相邻的1，得到整个岛，把整个岛归零，岛的个数+1。实现：
    1. func1: go over the island; 遇到1就bfs所有相邻1，算作一个island
    1. func2: bfs each 1 in grid(use queue, coordinate arrys); 不需要size，但需要坐标函数，每个1找相邻上下左右四个方向的1，找到了把1换成0，
    2. func3: if this block is in the grid or out of bound 
2. Union-find
    1. 
    2. 




####1. Graph Valid Tree
1. 题目分析，什么时候graph valid tree
    1. edges = nodes-1
    2.  n-1 edges connect the whole tree
2. BFS
    1. initialize graph :`Map<Integer, Set<Integer>> graph = initializeGraph(n, edges);` 把给定的node和neighbors信息存入这样一个map钟
    2. BFS并同时计数，通过最后counter==n(number of nodes)来判断是否valid
3. Union-find
    1. `if (uf.compressed_find(edges[i][0]) == uf.compressed_find(edges[i][1])) return false;   //有环，返回false，退出`
    2. compressed_find(int x) 这个方法，当x的parent不是x时，找x的parent，是第一个while； 找到后把之前所有跟x在同一set的parent都改成x现在的parent
