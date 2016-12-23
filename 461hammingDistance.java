public class Solution {
    public int hammingDistance(int x, int y) {
        if(x==y) return 0;
        int c = 0;
        while(x>0||y>0){
            if(x%2!=y%2){
                c++;
            }
            x=x/2;
            y/=2;
        }
        return c;
    }
}