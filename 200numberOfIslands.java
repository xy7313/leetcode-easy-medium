public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int number = 0;
        int row = grid.length;
        int col = grid[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j]=='1'){
                    bfs(grid,i,j); 
                    number++;
                }
            }
        }
        return number;
    }
    private void bfs(char[][] grid, int x, int y){
        int[] deltaX = {1,0,0,-1};
        int[] deltaY = {0,1,-1,0};
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x,y));
        grid[x][y]='0';
        
        while(!queue.isEmpty()){
            Point cur = queue.poll();
            //bfs走四个方向的点，看下个点是不是1，是的话就都在同一个点，不返回，直到bfs走过所有1，返回，number+1。
            for(int i = 0; i<4; i++){
                Point next = new Point(cur.x+deltaX[i],cur.y+deltaY[i]);
                //智障了这里没写cur.x，直接用了xy，算了半天，注意注意
                if(inBound(grid, next) && grid[next.x][next.y]=='1'){
                    queue.offer(next);
                    grid[next.x][next.y]='0';
                }
            }
        }
    }
    private boolean inBound(char[][] grid, Point p){
        return p.x>=0 && p.y>=0 && p.x<grid.length && p.y<grid[0].length;
    }
    
}
class Point{
    int x;
    int y;
    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}