BFS+Union-find+follow up

##(前五个都是BFS+Union-find)

200. Number of Islands
130. Surrounded Regions 
0. Number of Islands II
1. Graph Valid Tree
2. Connected Component in Undirected Graph
128. Longest Consecutive Sequence 

####200. Number of Islands
1. 选择方法BFS: 每次我遇到1，都bfs遍历所有他相邻的1，得到整个岛，把整个岛归零，岛的个数+1
2. 实现：func1: go over the island; func2: bfs each 1 in grid(use queue, coordinate arrys); func3: if this block is in the grid or out of bound
