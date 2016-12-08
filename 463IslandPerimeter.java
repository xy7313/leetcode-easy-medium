public class Solution {
    public static int islandPerimeter(int[][] grid) {
        //if其实可以不加的，加上显得考虑比较全面吧，不过if里的所有情况也进不去for，会直接返回0，没问题
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    result += 4;
                    if (i > 0 && grid[i-1][j] == 1) result -= 2;
                    if (j > 0 && grid[i][j-1] == 1) result -= 2;
                }
            }
        }
        return result;
    }
}
 //！！
 //由于leetcode太蠢会因为system.out.println而提交超时的缘故，这个问题我之前的代码其实也是work的，以下
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int count = 0;
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid[0].length;j++){
                if(grid[i][j]==1){
                    if(i==0) count++; 
                    if(j==0) count++;
                    if(i==grid.length-1) count++;
                    if(j==grid[0].length-1) count++;
                }
                if(grid[i][j]==0){
                    if((i+1)<grid.length&&grid[i+1][j]==1){
                        count++;
                    }
                    if((j+1)<grid[0].length&&grid[i][j+1]==1){
                        count++;
                    }
                    if((i-1)>=0&&grid[i-1][j]==1){
                        count++;
                    }
                    if((j-1)>=0&&grid[i][j-1]==1){
                        count++;
                    }
                }
            }
        }
    return count;
    }
}