/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    /**
     * @param n an integer
     * @param m an integer
     * @param operators an array of point
     * @return an integer array
     */
    int converttoId(int x, int y, int m){
        return x*m + y;
    }
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> re = new ArrayList<Integer>();
        if(operators == null) {
            return re;
        }
        
        int[] dx = {0,-1, 0, 1};
        int[] dy = {1, 0, -1, 0};
        int[][] isLand = new int[n][m];
        
        UnionFind uf = new UnionFind(n,m);
        int count = 0;
        for(int i = 0; i<operators.length; i++ ){
            count++;
            int curx = operators[i].x;
            int cury = operators[i].y;
            if(isLand[curx][cury]!=1){
                isLand[curx][cury]=1;
                int id = converttoId(curx,cury,m);
                for(int dir = 0; dir<4; dir++){
                    int nextx = curx+dx[dir];
                    int nexty = cury+dy[dir];
                    if(0 <= nextx && nextx < n && 0 <= nexty && nexty < m && isLand[nextx][nexty] == 1) {
                        int nextID = converttoId(nextx,nexty,m);
                        
                        int curfa = uf.compressed_find(id);
                        int nextfa = uf.compressed_find(nextID);
                        if(curfa!=nextfa){
                            count--;
                            uf.union(id,nextID);
                        }
                    }
                    
                }
                
            }
            //add count to re when operated
            re.add(count);
        }
        return re;
    }
   
    class UnionFind{
        HashMap<Integer, Integer> father = new HashMap<Integer, Integer>();
        //initialization
        public UnionFind(int n, int m){
            for(int i = 0 ; i < n; i++) {
                for(int j = 0 ; j < m; j++) {
                    int id = converttoId(i,j,m);
                    father.put(id, id); 
                }
            }
        }
        //find parent
        int compressed_find(int x){
            int parent =  father.get(x);
            while(parent!=father.get(parent)) {
                parent = father.get(parent);
            }
            int temp = -1;
            int fa = x;
            //update parent for all element in this set
            while(fa!=father.get(fa)) {
                temp = father.get(fa);
                father.put(fa, parent) ;
                fa = temp;
            }
            return parent;
                
        }
        //union
        void union(int x, int y){
            int fa_x = compressed_find(x);
            int fa_y = compressed_find(y);
            if(fa_x != fa_y)
                father.put(fa_x, fa_y);
        }
    }
}