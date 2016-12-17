public class Solution {
    public int maxRotateFunction(int[] A) {
        if(A==null||A.length==0) return 0;
        int[] f = new int[A.length];
        for(int k = 0; k <A.length; k++){
            int tmpf = 0;
            for(int i = 0; i<A.length; i++){
                if(k==0){
                    tmpf+=i*A[i];
                }else{
                    if((A.length-k+i) > (A.length-1)){
                        tmpf+=i*A[i-k];
                    }else{
                        tmpf+=i*A[A.length-k+i];
                    }
                }
            }
            f[k] = tmpf;
        }
        Arrays.sort(f);
        return f[f.length-1];
    }
}

//上面是原创的，原创的通常都是最慢的0.0

//下面是个大神的解法，链接：https://discuss.leetcode.com/topic/58459/java-o-n-solution-with-explanation
public class Solution {
    public int maxRotateFunction(int[] A) {
        int allSum = 0;
        int len = A.length;
        int F = 0;
        for (int i = 0; i < len; i++) {
            F += i * A[i];
            allSum += A[i];
        }
        int max = F;
        for (int i = len - 1; i >= 1; i--) {
            F = F + allSum - len * A[i];
            max = Math.max(F, max);
        }
        return max; 
    }
}