70. Climbing Stairs
198. House Robber
343. Integer break
357. Count Numbers with Unique Digits
//回头一起看

10. Regular Expression Matching
44. Wildcard Matching（通配符）


house robber2
combination sum 4
target sum


####70. Climbing Stairs
一个很简单的动态规划问题，居然写出来了一个动态规划，一颗老心都快活过来了，还是只有一句核心代码，`step[i] = (step[i-1])+(step[i-2]);`

####198. House Robber
还是DP，连续rob两个房子就会alert

1. 首先，输入nums.length<2时，分两种情况，输入==0，返回0；输入==1，返回nums[0]
2. for循环中也要单列i=0,i=1时的情况，之后就是通用情况，money[i]表示max value rob ith house， money= max(money[i-1],money[i-2]+nums[i])前者是ith不rob，后者是ith rob

####343. Integer break
1. 根据regularity
>n % 3 == 0 时，分为n个3的乘积
n % 3 == 1 时，分为n-1个3和两个2的乘积
n % 3 == 2 时，分为n个3和一个2的乘积
2. DP：Let dp[i] to be the max production value for breaking the number i. Since dp[i+j] can be i*j.`dp[i+j]=Math.max(Math.max(dp[i],i)*Math.max(dp[j],j), dp[i+j]);`

####357. Count Numbers with Unique Digits
第一种是很数学方法，第二种是DP，DP很好理解，数组re[0],re[1]...re[n]分别存储n位数对应的每位不重复的数字的个数，
 * n = 0, count = 1 --> re[0]
 * n = 1, count = 1+9 --> re[0]+re[1]
 * n = 2, count = 1+9 + 9*9 --> re[0]+re[1]+re[2]
 * n = 3, count = 1+9 + 9*9 + 9*9*8
 * ...
 */

####10. Regular Expression Matching
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

####44. Wildcard Matching（通配符）
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





####64. Minimum Path Sum 