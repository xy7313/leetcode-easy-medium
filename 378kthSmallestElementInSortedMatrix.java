//O(klgk)
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        PriorityQueue<Elem> queue = new PriorityQueue<>(len,(a,b)->(a.val-b.val));
        for(int i = 0; i<len; i++){
            queue.add(new Elem(0,i,matrix[0][i]));
        } 
        for(int i = 0; i<k-1; i++){
            Elem elem = queue.poll();
            if(elem.row==(len-1)) continue;
            queue.add(new Elem(elem.row+1,elem.col,matrix[elem.row+1][elem.col]));
        }
        return queue.poll().val;
    }
    class Elem{
        int row, col, val;
        public Elem(int r, int col, int val){
            this.row=r;
            this.col=col;
            this.val=val;
        }
    }
}

//discuss中的这款代码快很多，难道是因为Lambda箭头函数？
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }
}

class Tuple implements Comparable<Tuple> {
    int x, y, val;
    public Tuple (int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
    
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}

//另一种解法，binary search
https://discuss.leetcode.com/topic/52948/share-my-thoughts-and-clean-java-code

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}