public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> tri = new ArrayList<>();
        for(int i = 0; i<numRows;i++){
            List<Integer> inner = new ArrayList<>();
            if(i==0){
                inner.add(1);
            }else{
                for(int j = 0; j<=i;j++){
                    if(j==0||j==i){
                        inner.add(1);
                    }else{
                        int d = tri.get(i-1).get(j-1);
                        int f = tri.get(i-1).get(j);
                        inner.add(d+f);
                    }
                }
            }
            tri.add(inner);
        }
        return tri;
    }
}