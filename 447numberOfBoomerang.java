public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        for(int i= 0;i<points.length;i++){
            Map<Integer,Integer> m = new HashMap<Integer,Integer>();
            for(int j = 0; j <points.length;j++){
                // int dis=(int) Math.pow((double)(points[i][0]-points[j][0]),2.0)+Math.pow((double)(points[i][1]-points[j][0]),2.0);
                int dis = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                if(!m.containsKey(dis)){
                    m.put(dis,1);
                }
                else{
                    count += m.get(dis)*2;
                    m.put(dis,m.get(dis)+1);
                }

            }
        }
    return count;    
    }   
}