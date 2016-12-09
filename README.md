# leetEM(start from easy)


 

####412. fizzbuzz
最简单的数学题，注意if判断顺序或者就都单列出来总不会错

####344.reverse string
转成数组`s.toCharArray()`转回string`String.valueOf(charArrayName)`

####292. nim game
只要石头堆对4取余有剩余，剩余的就是我的，就能赢

####136. single number
1. hashmap<Integer,Boolean>
2. array 排序的方法：（排序时间复杂度nlogn）Arrays.sort(nums); 循环的时候，判断相邻两数差==0，i+=2
3. notice:如果有多个singlenumber，题目中没有描述清楚，应该问清楚
##新题455. assign cookies

####371. sum Two Integer
sum 得到按位异或的结果，在没有进位的情况下就是我们想要的和；如果有进位，进入while循环处理进位
[code](https://github.com/xy7313/leetEM/blob/master/371sumTwoInteger.java)

##104 tree

####258. add Digits
(num-1)%9+1

#### 389-383-387-242
都是很类似题
第一种解法：哈希表（key：character，value：次数或者index）
389findDifference：第一次遍历向hashmap中存string1，key-character，value-出现次数，第二次遍历key出现1次value-1，value<0说明是different的char
387UniqueCharacter：第一次遍历向hashmap中存string1，key-character，value-出现次数，第二次通过key-char找最小index`s.indexOf()`
383ransomNote：和finddifference方法（hashmap）一样，只不过string的位置换了一下，谁包含谁换了

第二种解法：char转成int，来做index
389findDifference：要求的结果变量re先依次减去第一个string中每个char转成int的值，然后再依次加上第二个string中的每个char，得到正确difference（运行时间比hashmap快了很多）
387UniqueCharacter：char转成int当做array的索引，没出现依次，该索引位置对应值++，取所有索引位置对应值=1中最小索引，对应char
242validAnagram：考虑到两个string可能长度不一样，用普通for容易出现outofIndex，for in形式比较好`for(char j: t.toCharArray())`

##226 tree

####283. move Zeroes
设置计数器，从计数器=0开始放非零数字，剩余（length-计数器）置0

##404 tree
##453

####349.intersaction
嵌套for-loop找相同元素，把未出现过的相同元素存入ArrayList`re.contains();re.add();` ArrayList再转回int[]很麻烦`for(int i : IntegerList)`

##237 linked list
##100 tree

####171. excelColumnNumber
`re+=(sc[i]-'A'+1)*Math.pow(26,sc.length-i-1);`


####169. Majority Element
有种取巧的写法，可以说得通但是想不到，传统解法还是hashmap再mark一下这个`for(Integer k: m.keySet())`

####409. Longest Palindrome
看上去吓一跳稍微想一下还挺简单的，就是出现奇数次数的字母和出现偶数次数的字母的判断，出现偶数次的，全+，出现奇数次的，除了%2这部分全加，最后，中间可以有一个奇数次的，所以如果有出现奇数次的字母出现，最终结果+1。我自己写的解法估计不太好，尤其a那里，accepied的时候也是忍不住笑出声

####217. Contains Duplicate
用set是比较简单的方法，通过set.contains的方法来判断，for in 来遍历nums

####455 assign cookies
跟下面一题的思路一样，用while循环，把两个数组排序，第二个数组中对应元素不小于第一个数组中对应元素，output++，两个元素都向后移，如果第二个数组中元素比第一个数组中对应元素小，说明饼干不够，要往后找更多饼干，所以第二个数组j++

####350. Intersection of Two Arrays II
现在用的是类似指针的思路，用while循环（避免for循环会出现的各种index问题）先排序（一般用指针都要排序），然后就一步一步往后挪，如果上下相等了，那就一起后移，不相等就小的后移，找大数来跟另外一个数组中的数匹配
更好的方法应该是hashmap吧

##206 linked list

####401 binary watch
强行倒着解，把所有可能的小时和分钟列出来，那一时刻刚好对应二进制的1的个数跟num 相等，就输出这个时间，性能不好，也不太好想但好写的一种方法
标准写法应该是backtracking

####13 roman to int
 1. 这个题对我来说的难点好像在于for里判断的时候要i+1个元素个i个元素比较，所以for循环很难遍历全整个数组，方法是：会一开始的时候就把遍历不到，
 而且刚好也需要加上的最后一位直接赋值给result
 2. 最近总是想到用true fale这种方式判断，并不简单，而且切记boolean这个值是需要不停改变的，for循环里最后一句重新minus=false忘记加找了半天问题
 3. 另外这个题的超时问题也是因为打印语句

####453 minMove
1. 自己的思路是按照题目里说的，数组里除了最大元素都++，然后判断符不符合标准，复杂度太高，不能accepted
2. 大神的思路，把上面那种 除最大值外都+1 做n次这种操作达到数组元素全部相等 转换成 每个非min的元素每次-1需要的操作数

####447. Number of Boomerangs
1. 暴力解，取每个点当基准点并给他一个hashmap存距离，看有没有两点到他距离相等，
2. 注意两点，首先是map每次外层for都新建一个，然后是count，到基准点距离相等的两点可以互换，所以又一次匹配就有两种形式

####415 Add String
1. string1.length()>string2.length()`int x=len1<0?0:num1.charAt(len1)-'0';int y=len2<0?0:num2.charAt(len2)-'0';`
2. carry 

####268. Missing Number
1. 方法1，排序，然后用数组中的元素跟索引比较，索引有，元素没有的（其实就是i!=nums[i]）直接返回i，复杂度略高
2. 方法2，求和，已知我们有的是[0,n]之间distinct的数字，可以直接把[0,n]这些全部相加再-数组中所有元素和

####463. Island Perimeter
1. 首先想到的思路：每个0格上下左右是否有1，有的话计数器+1，格子是否在周围一圈上的位置，如果是，计数器+1，这个方法一开始超时跑不过，以为写错了，后来发现超时的原因是因为有system.out.println的打印语句。。。
2. 直接用1的格子，每个格子四条边，每和一个左边的1（遍历过的1）相邻，两个格子各少1，所以是-2
3. 程序开始的if判断，没有作用，可以表示一下思路吧，没实质性作用

####384. Shuffle an Arrays
1. 自己想的，生成随机数来做索引，为了避免随机生成了相同的数，把生成过的索引存为map的key，这个方法一开始也是超时跑不过，以为写错了，后来发现超时的原因是因为有system.out.println的打印语句。。。
2. 大神的思路：从0-i 之间选一个随机位置，把original[i]插入，把原位置的值放在后面（也就是shuffled[i]）位置，存着，直到把所有original中每个值都插入一遍
（想不到）

####419. Battleships in a Board
有一点点类似上面那个island perimeter，题目意思其实是以battleships的左上角的X来代表一个battleship，所以算有几个X是top-left

####343. Integer break
1. 根据regularity
>n % 3 == 0 时，分为n个3的乘积
n % 3 == 1 时，分为n-1个3和两个2的乘积
n % 3 == 2 时，分为n个3和一个2的乘积
2. DP：Let dp[i] to be the max production value for breaking the number i. Since dp[i+j] can be i*j.`dp[i+j]=Math.max(Math.max(dp[i],i)*Math.max(dp[j],j), dp[i+j]);`

####413. Arithmetic Slices
若序列S为等差数列，其长度为N，则其等差数列切片的个数SUM = 1 + 2 + ... + (N - 2), 例如，等差数列[1, 2, 3, 4, 5, 6]的切片个数为1+2+3+4 = 10`if(A[i+1]-A[i]==A[i+2]-A[i+1]) cur += 1;`

####434. Number of Segments in a String
这类题都不要变换思路，要求什么就用什么，求有几个words，就算有几个words，把判定情况写全，这里是两种情况，
1. 第一个words，`i==0 && charAt(i)!=' '；`
2. 后面的words，`charAt(i-1)==' ' && charAt(i)!=' ';`

####202 Happy Number
看起来很简单，写起来很麻烦
1. 首先要确定需要两层循环，外层循环直到得到的 和<10，内层循环确保 每一位都平方并sum了，
2. 1,7是happynumber
代码不多，直接贴了，
```
public boolean isHappy(int n) {
        if (n <= 0) return false;
        while(n>=10){
            int sum = 0;      
            while(n!=0){
                sum +=(n%10)*(n%10);
                n/=10;   //确保算了每一位的平方
            }            
            n=sum;  //sum是每一位的平方的和
        }
        return n == 1 || n == 7; 
    }
```

####231. Power of Two
power of 2 only contains one 1, (power of 2)-1 is only consist of 1(ex:8=1000,7=111),one line code: `return n>0 && (n&(n-1))==0;`

####326. Power of Three
 任何一个3的x次方一定能被int型里最大的3的x次方整除，1162261467 is 3^19,  3^20 is bigger than int,`return ( n>0 && 1162261467%n==0);`  

####342. Power of Four
1. 方法1: 4^0 = 1, 4^1 = 100, 4^2 = 10000.所以4的二进制只有一个1且在odd位; 另外Integer.toString(int i, int radix)这个方法是把前面的数按后面数的进制转换，这里相当于4进制，既3=3，4=10，5=11，15=33，16=100`return Integer.toString(num, 4).matches("10*");`
2. 方法2：`return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;//make sure that 1 bit always appears at the odd position `
3. 方法3：这个比较数学讨论区大神有解释，一个数既是2的幂，又等于3的倍数+1，`x^n-1=(x-1)(x^(n-1)+...+x^1+1)右边乘法展开是可以得到左边的，所以 4^n=3*(...)+1`就是power of 4`return (num&(num-1))==0 && num>0 && (num-1)%3==0;`

####191. Number of 1 Bits
还是看到就头疼的题，估计再看点这类题就会好点了，，，
1. 方法1：比较直接，判断最右1位是不是1，n&1 如果是，计数器+1，右移，判断下一位，这里有两种代码写法，前面这种比后面的快1倍`count = count + (n & 1);`,`if((n&1)==1) count++;`
2. 方法2：用到了类似power of two中n&(n-1)的方法,n & (n-1)!=0说明n不是2的power，肯定还有不止一个1，但为什么n=n & (n-1)不太懂
```
while(n != 0){
    n = n & (n-1);
    count++;
}
```


####405. Convert a Number to Hexadecimal
1. 首先负数这里需要在去掉符号之后-1，-1-->0，-2-->1以此类推，因为-1=‘ffffffff’,然后-2=‘fffffffe'
2. 之后是num % 16<6 用abcdef剩下用0-9.其他位（前面的位）保留f

####459. Repeated Substring Pattern
1. substring的长度肯定是str的约数(str%substring==0), 
2. 遍历所有可能的substring(outer for),可以从str.length()/2开始，到1
3. check(inner for): 从头-依次-取长度为str约数的substring，然后通过叠加(str.length()/substring.length())个substring，看结果是不是str

####70. Climbing Stairs
一个很简单的动态规划问题，居然写出来了一个动态规划，一颗老心都快活过来了，还是只有一句核心代码，`step[i] = (step[i-1])+(step[i-2]);`

####441. Arranging Coins
也是一个很简单的题，给n个硬币，在第k行放k个硬币，能放到第几行，不完整的一行不算
用了while循环，略慢

####263. Ugly Number
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5。1 is ugly number
所以就拿一个数，只要能被2整除就一直除以2，然后，只要能被3整除就一直除以3，然后，只要能被5整除就一直除以5，如果剩下的不是1，那就不是ugly number










##倒着刷了两道easy

####atoi
1. 清除空格的方法`str = str.trim();`
2. 一位一位加`int digit = str.charAt(i) - 48;sum = sum*10-digit;`

####165 compare version
长度不相等的时候很麻烦
1. 注意以 . perioud 为分隔符时候的写法`version1.split("[.]");version2.split("\\.");`
2. 注意每次while中才给temp赋值，即，如果s1比s2多一位，那s2的当前temp=0，然后判断

####7 reverse integer
1. int不行，long才可以,int放不下
2. 不需要取绝对值单独判断符号，%10的时候得到的结果是带-的
3. y是long，但需要的结果是int，所以需要判断y能不能转int，就是在最大值和最小值之间
