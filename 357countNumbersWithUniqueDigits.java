/**
 * n = 0, count = 1
 * n = 1, count = 1+9
 * n = 2, count = 1+9 + 9*9
 * n = 3, count = 1+9 + 9*9 + 9*9*8
 * ...
 * 第一种是很数学方法，第二种是DP，DP很好理解
 */

 public class Solution {
     public int countNumbersWithUniqueDigits(int n) {
        if(n==0) return 1;
        int res=10,base=9;
        for(int i=2;i<=n&&i<=10;i++){
            base*=11-i;
            res+=base;
        }
        return res;
    }
}

public class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        n = Math.min(n,10);
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1;i<=n;i++){
            dp[i] = 9;
            for(int x = 9; x >= 9 - i + 2;x--){
                dp[i] *= x;
            }
        }
        int ans = 0;
        for(int i= 0;i<dp.length;i++) ans += dp[i];
        return ans;
    }
}