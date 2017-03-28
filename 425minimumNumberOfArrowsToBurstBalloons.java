public class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points==null || points.length==0) return 0;
       //sort by xend
       Arrays.sort(points,(a,b)->(a[1]-b[1]));
       int arrow = 1;
       int posi = points[0][1];
       for(int[] point : points){
           // ballon xstart > max arrow position, we need one more arrow, and update the max arrow position
           if(point[0]>posi){
               arrow++;
               posi = point[1];
           }
       }
       return arrow;
    }
}