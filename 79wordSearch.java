public class Solution {
    public boolean exist(char[][] board, String word) {
        for (int x=0; x<board.length; x++) {
    	    for (int y=0; y<board[x].length; y++) {
    		    if (dfsHelper(board, x, y , word, 0)) return true;
    	    }
        }

        return false;
    }
    private boolean dfsHelper(char[][] board, int x, int y, String word, int i){
        if (i == word.length()) return true;
        // inBound
	    if (y<0 || x<0 || x >= board.length || y >= board[x].length) return false;
	    
	    if (board[x][y] != word.charAt(i)) return false;
	    //After board[y][x] ^= 256 the char became not a valid letter. After second board[y][x] ^= 256 it became a valid letter again.
	    board[x][y] ^= 256;
	    boolean exist = dfsHelper(board, x+1,y, word, i+1)
		|| dfsHelper(board, x-1, y, word, i+1)
		|| dfsHelper(board, x, y+1, word, i+1)
		|| dfsHelper(board, x, y-1, word, i+1);
	    board[x][y] ^= 256;
	    return exist;
    }
}