```
53 Maximum Subarray
62 Unique Paths
63 Unique Paths II
64 Minimum Path Sum
70 Climbing Stairs
72 Edit Distance
97 Interleaving String
115 Distinct Subsequences
139 Word Break
140 Word Break II
152 Maximum Product Subarray
174 Dungeon Game
198 House Robber
213 House Robber II
221 Maximal Square
343. Integer break
357. Count Numbers with Unique Digits
//others
10. Regular Expression Matching
44. Wildcard Matching（通配符）
- house robber2
- combination sum 4
- target sum
```

#### 53. Maximum Subarray
```
public class Solution {
    // general
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum < 0) sum = nums[i];
            else sum += nums[i];
            if (sum > max) max = sum;
        }
        return max;
    }
   // dp
   public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];
        
        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }        
        return max;
    } 
    // prefix
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }
        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        return max;
    } 
    //(greedy, divide+conquer)
}
```

#### 62. Unique Paths
```
public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(i==0 || j==0){
                    dp[i][j] = 1;
                }else{
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
```

#### 63. Unique Paths II(Obstacles)
Four kind of point: Obstacles, start point, points in two side(vertical and horizontal), normal points
```
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int x = obstacleGrid.length;
        int y = obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[x-1][y-1]==1){
            return 0;
        }
        for(int i = 0; i<x; i++){
            for(int j = 0; j<y; j++){
                if(obstacleGrid[i][j]==1){
                    obstacleGrid[i][j]=0;
                }else if(i==0 && j==0){
                    obstacleGrid[i][j] = 1;
                }else if(i==0){
                    //the former element has been marked as the number of path, not the origin number0/1
                   obstacleGrid[i][j]= obstacleGrid[i][j-1]==1? 1:0;
                }else if(j==0){
                    obstacleGrid[i][j]= obstacleGrid[i-1][j]==1? 1:0;
                }else{
                    obstacleGrid[i][j] = obstacleGrid[i-1][j]+obstacleGrid[i][j-1];
                }
            }
        }
        return obstacleGrid[x-1][y-1];
    }
```

#### 64. Minimum Path Sum 
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.--> All points are divided into three types
```
public int minPathSum(int[][] grid) {      
    if(grid.length==0 || grid[0].length==0) return 0;
    int x = grid.length;
    int y = grid[0].length;
    for(int i = 0; i<x; i++){
        for(int j = 0; j<y; j++){
            if(i==0 && j==0){
                grid[i][j]=grid[i][j];
            }else if(i==0){
                grid[i][j]=grid[i][j-1]+grid[i][j];
            }else if(j==0){
                grid[i][j]=grid[i-1][j]+grid[i][j];
            }else{
                grid[i][j] = Math.min(grid[i][j-1],grid[i-1][j])+grid[i][j];
            }
        }
    }
    return grid[x-1][y-1];
}
```

#### 70. Climbing Stairs
一个很简单的动态规划问题，居然写出来了一个动态规划，一颗老心都快活过来了，还是只有一句核心代码，`step[i] = (step[i-1])+(step[i-2]);`
```
public int climbStairs(int n) {
        if(n==0 || n==1 || n==2){
            return n;
        }
        int[] ways = new int[n];
        //1 step:1 way, 2 steps: 2
        ways[0] = 1;
        ways[1] = 2;
        for(int i = 2; i<n; i++){
            ways[i] = ways[i-1]+ways[i-2];
        }
        return ways[n-1];      
    }
```

#### 198. House Robber
还是DP，连续rob两个房子就会alert

1. 首先，输入nums.length<2时，分两种情况，输入==0，返回0；输入==1，返回nums[0]
2. for循环中也要单列i=0,i=1时的情况，之后就是通用情况，money[i]表示max value rob ith house， money= max(money[i-1],money[i-2]+nums[i])前者是ith不rob，后者是ith rob

```
public int rob(int[] nums) {
        if(nums.length==0||nums==null) return 0;
        if(nums.length==1) return nums[0];
        int[] money = new int[nums.length];
        for(int i = 0; i<nums.length;i++){
            if(i==0){
                money[i] = nums[i];
            }else if(i==1){
                money[i]= Math.max(nums[i],nums[i-1]);
            }else{
                money[i] = Math.max(nums[i]+money[i-2],money[i-1]);
            }
        }
    return money[nums.length-1];    
}
```

#### 72. Edit Distance
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step. Three operations: insert, replace, delete)
```
public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] cost = new int[m+1][n+1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;
        for(int i = 1; i <= n; i++)
            cost[0][i] = i;       
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j))
                    cost[i + 1][j + 1] = cost[i][j];
                else {               
                    cost[i + 1][j + 1] = 1+ Math.min(cost[i][j],Math.min(cost[i][j + 1],cost[i + 1][j]));
                }
            }
        }
        return cost[m][n];
    }
```

#### 97. Interleaving String
```
public boolean isInterleave(String s1, String s2, String s3) {
        int x = s1.length();
        int y = s2.length();
        
        if(x+y!=s3.length()){
            return false;
        }
        
        boolean[][] isOrnot = new boolean[x+1][y+1];
        isOrnot[0][0] = true;
        for(int i = 1; i<=x; i++){
            isOrnot[i][0] = s3.charAt(i-1)==s1.charAt(i-1) && isOrnot[i-1][0];
        }
        for(int i = 1; i<=y; i++){
            isOrnot[0][i] = s3.charAt(i-1)==s2.charAt(i-1) && isOrnot[0][i-1];
        }
        for(int i = 1; i<=x; i++){
            for(int j = 1; j<=y; j++){
                isOrnot[i][j] = (s3.charAt(i+j-1)==s1.charAt(i-1) && isOrnot[i-1][j]) || 
                    (s3.charAt(i+j-1)==s2.charAt(j-1) && isOrnot[i][j-1]);
            }
        }
        return isOrnot[x][y];
    }
```

#### 115. Distinct Subsequences
Given a string S and a string T, count the number of distinct subsequences of S which equals T.
```
public int numDistinct(String s, String t) {
        //x-t,y-s
        int y = s.length();
        int x = t.length();
              
        /*Initialize:
        filling the first row with 1s,
        because the empty string is a subsequence of any string but only 1 time. 
        leave the first column is 0 by default
        */
        
        int[][] dp = new int[x+1][y+1];
        for(int j=0; j<=y; j++) {
            dp[0][j] = 1;
        }
        
        for(int i=0; i<x; i++) {
            for(int j=0; j<y; j++) {
                if(t.charAt(i) == s.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + dp[i+1][j];
                } else {
                    dp[i+1][j+1] = dp[i+1][j];
                }
            }
        }
        return dp[x][y];
    }
```

#### 139. Word Break
Using substring to check if all subsquences is in word dict.
```
public boolean wordBreak(String s, List<String> wordDict) {
        if(s==null || s.length()==0){
            return false;
        }
        int l = s.length();
        boolean[] dp = new boolean[l+1];
        dp[0] = true;
        //break, out of current for, which is inner for
        for(int i = 1; i<=l; i++){
            for(int j = 0; j<i; j++){
                dp[i] = dp[j] && wordDict.contains(s.substring(j,i));
                System.out.println(s.substring(j,i));
                if (dp[i]) {
                    break;//贪心
                }
            }

        }
        return dp[l];
    }
```

#### 152. Maximum Product Subarray
Be careful when dealing with the negatives.
```
public int maxProduct(int[] nums) {
        if(nums==null || nums.length==0){
            return 0;
        } 
        int max = nums[0], min = nums[0], result = nums[0];
        for(int i = 1; i<nums.length; i++){
            int tmp = max;
            max = Math.max(nums[i], Math.max(max*nums[i],min*nums[i]));
            min = Math.min(nums[i], Math.min(min*nums[i],tmp*nums[i]));
            if(max>result){
                result = max;
            }
        }
        return result;
    }
```

#### 343. Integer break
1. 根据regularity
>n % 3 == 0 时，分为n个3的乘积
n % 3 == 1 时，分为n-1个3和两个2的乘积
n % 3 == 2 时，分为n个3和一个2的乘积
2. DP：Let dp[i] to be the max production value for breaking the number i. Since dp[i+j] can be i*j.`dp[i+j]=Math.max(Math.max(dp[i],i)*Math.max(dp[j],j), dp[i+j]);`

#### 357. Count Numbers with Unique Digits
第一种是很数学方法，第二种是DP，DP很好理解，数组re[0],re[1]...re[n]分别存储n位数对应的每位不重复的数字的个数，
 * n = 0, count = 1 --> re[0]
 * n = 1, count = 1+9 --> re[0]+re[1]
 * n = 2, count = 1+9 + 9*9 --> re[0]+re[1]+re[2]
 * n = 3, count = 1+9 + 9*9 + 9*9*8
 * ...
 */

#### 10. Regular Expression Matching
https://discuss.leetcode.com/topic/40371/easy-dp-java-solution-with-detailed-explanation/4

题目：

'.' Matches any single character.

'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

eg: isMatch("aab", "c*a*b") → true
```
/*
Here are some conditions to figure out, then the logic can be very straightforward.

1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
3, If p.charAt(j) == '*': 
   here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
                           
*/
public class Solution {
    public boolean isMatch(String s, String p) {

    if (s == null || p == null) {
        return false;
    }
    boolean[][] dp = new boolean[s.length()+1][p.length()+1];
    dp[0][0] = true;
    for (int i = 0; i < p.length(); i++) {
        if (p.charAt(i) == '*' && dp[0][i-1]) {
            dp[0][i+1] = true;
        }
    }
    for (int i = 0 ; i < s.length(); i++) {
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '.') {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == s.charAt(i)) {
                dp[i+1][j+1] = dp[i][j];
            }
            if (p.charAt(j) == '*') {
                if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                    dp[i+1][j+1] = dp[i+1][j-1];
                } else {
                    dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                }
            }
        }
    }
    return dp[s.length()][p.length()];
    }
}
```

#### 44. Wildcard Matching（通配符）
https://discuss.leetcode.com/topic/22516/my-java-dp-solution-using-2d-table/5

题目：

'?' Matches any single character.

'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

eg: isMatch("aab", "c*a*b") → false
```
public class Solution {
    /*
    The basic idea is to have one pointer for the s and one pointer for the p. This algorithm iterates at most length(string) + length(p) times, for each iteration, at least one pointer advance one step.
    */
//     public boolean isMatch(String s, String p) {
//         int ss = 0, pp = 0, match = 0, starIdx = -1;            
//         while (ss < s.length()){
//             // advancing both pointers when (both characters match) or ('?' found in pattern)
//             if (pp < p.length()  && (p.charAt(pp) == '?' || s.charAt(ss) == p.charAt(pp))){
//                 ss++;
//                 pp++;
//             }
//             // * found, only advancing p pointer
//             else if (pp < p.length() && p.charAt(pp) == '*'){
//                 starIdx = pp;
//                 match = ss;
//                 pp++;
//             }
//           // last p pointer was *, advancing string pointer
//             else if (starIdx != -1){
//                 pp = starIdx + 1;
//                 match++;
//                 ss = match;
//             }
//           //current p pointer is not star, last patter pointer was not *
//           //characters do not match
//             else return false;
//         }
        
//         //check for remaining characters in p
//         while (pp < p.length() && p.charAt(pp) == '*')
//             pp++;
        
//         return pp == p.length();
//      }
/*
DP:
dp[i][j] denotes whether s[0....i-1] matches p[0.....j-1],

First, we need to initialize dp[i][0], i= [1,...,m]. All the dp[i][0] should be false because p has nothing in it.

Then, initialize dp[0][j], j = [1, n]. In this case, s has nothing, to get dp[0][j] = true, p must be '*', '*', '**',etc. Once p.charAt(j-1) != '*', all the dp[0][j] afterwards will be false.

Then start the typical DP loop.

Though this solution is clear and easy to understand. It is not good enough in the interview. it takes O(mn) time and O(mn) space.

Improvement: 1) optimize 2d dp to 1d dp, this will save space, reduce space complexity to O(N). 2) use iterative 2-pointer.

*/
public boolean isMatch(String s, String p) {
	int m=s.length(), n=p.length();
	boolean[][] dp = new boolean[m+1][n+1];
	dp[0][0] = true;
	for (int i=1; i<=m; i++) {
		dp[i][0] = false;
	}
	
	for(int j=1; j<=n; j++) {
		if(p.charAt(j-1)=='*'){
			dp[0][j] = true;
		} else {
			break;
		}
	}
	
	for(int i=1; i<=m; i++) {
		for(int j=1; j<=n; j++) {
			if (p.charAt(j-1)!='*') {
				dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?');
			} else {
				dp[i][j] = dp[i-1][j] || dp[i][j-1];
			}
		}
	}
	return dp[m][n];
}
}
```








#### 139. word break
用题目给的例子试一下可以发现，内层循环中fj， fi这种方法，可以确保整个string都存在，不会少字母或什么的。注意substring是左闭右开区间，所以i<=s.length()
```
public boolean wordBreak(String s, Set<String> dict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        //discuss 区一个不错的dp方法
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[s.length()];
    }
```
