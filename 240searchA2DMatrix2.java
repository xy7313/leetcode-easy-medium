public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0) return false;
        if(matrix[0]==null||matrix[0].length==0) return false;
        int row = matrix.length;
        int col = matrix[0].length;

        int x = row-1, y =0;
        while(y<col&&x>=0){
            if(matrix[x][y]==target){
                return true;
            }else if(matrix[x][y]>target){
                x--;
            }else if(matrix[x][y]<target){
                y++;
            }
        }
        return false;
    }
}