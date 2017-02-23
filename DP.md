
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





house robber2
combination sum 4
target sum

####64. Minimum Path Sum 