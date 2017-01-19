public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i< board.length;i++){
            HashSet<Character> rows = new HashSet<Character>();
            HashSet<Character> columns = new HashSet<Character>();
            HashSet<Character> cube = new HashSet<Character>();
            for(int j = 0; j<board[0].length;j++){
                if(board[i][j]!='.' && !rows.add(board[i][j])) return false;
                if(board[j][i]!='.' && !columns.add(board[j][i])) return false;
                //eg i=4 j=3 对应上图中间那个6，board[3+1][3+0]-->board[4][3]，
                if(board[3*(i/3) + j/3][3*(i%3) + j%3]!='.' && !cube.add(board[3*(i/3) + j/3][3*(i%3)  + j%3])) return false;
                }
        }
        return true;
    }
}