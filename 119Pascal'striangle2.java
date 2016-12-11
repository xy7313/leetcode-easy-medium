public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> tri = new ArrayList<>();
        for(int i = 0; i<rowIndex+1;i++){
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
        return tri.get(rowIndex);
    }
}
//O(k) space complexity
public List<Integer> getRow(int rowIndex) {
    List<Integer> res = new ArrayList<Integer>();
    for(int i = 0;i<rowIndex+1;i++) {
        res.add(1);
        for(int j=i-1;j>0;j--) {
            res.set(j, res.get(j-1)+res.get(j));
        }
    }
    return res;
}