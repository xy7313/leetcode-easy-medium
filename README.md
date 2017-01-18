# leetcode-Easy/Midum(start from easy)

from easy ac
这个有点像流水账，前面还是知识点总结，后面题目不会的多了，就加入了大部分的代码，回头还是做成gitbook这种带个目录的比较方便，不然一长篇不太方便阅读。

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


####371. sum Two Integer
sum 得到按位异或的结果，在没有进位的情况下就是我们想要的和；如果有进位，进入while循环处理进位
[code](https://github.com/xy7313/leetEM/blob/master/371sumTwoInteger.java)

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

##substring & anagrams(上面242，共5)
####49. Group Anagrams
自己的思路是循环拼接，判断是不是anagram以前做过，可以用char值的和的方式，然而不行，不能避免重复匹配的情况。
discuss区不知道咋想到的排单个string的字符序，总之有可行的方法了.
这道题代码比较好读懂，也很简短，思路大体是：
1. 把每个string先按字符序重新排列一下，（anagrams都会得到一样的结果）
2. 把第一次排好的string添加到map中当key，这样后面的anagrams可以通过判断key是否存在的方法找到
3. anagrams 都存在了 key是自己字母升序排列 的 value中组成list。
4. 输出list（每个value本身就是list）
```
public List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> hm = new HashMap<>();
    for(int i = 0; i < strs.length; i++){
        String currString = strs[i];
        char[] charArr = strs[i].toCharArray();
        Arrays.sort(charArr);
        String sortString = new String(charArr);
        if (!hm.containsKey(sortString)){
            hm.put(sortString, new ArrayList<String>());
        } 
        hm.get(sortString).add(currString);
    }
    return new ArrayList<>(hm.values());
} 
```

####438. Find All Anagrams in a String
[Here is a 10-line template that can solve most 'substring' problems](https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems)
discuss里找了个高大上的方法，只能看懂，并不能想到，下次但愿能背会。。。还有自己想的一个方法，最直接但是很慢，反正ac了

####14. Longest Common Prefix
每种语言string的各种操作都很风骚，这个题用了两个string的方法，见下面，思路就是取strs[0]当做pre，去比较看是不是strs[1-->len]的prefix，所以需要两层循环，外层遍历数组中每个元素，内层不停剪短pre直到pre是strs[i]的prefix，内层：`while(strs[i].indexOf(pre) != 0){pre = pre.substring(0,pre.length()-1);}`
```
/*string.indexOf(String str): 
        Returns the index within this string of the first occurrence of the specified substring.
        If no such value of k exists, then -1 is returned.
      public String substring(int beginIndex,int endIndex)
        Returns a new string that is a substring of this string. 
        The substring begins at the specified beginIndex and extends to the character at index endIndex - 1. 
        Thus the length of the substring is endIndex-beginIndex.
        --JAVA API
*/
```

####459. Repeated Substring Pattern
1. substring的长度肯定是str的约数(str%substring==0), 
2. 遍历所有可能的substring(outer for),可以从str.length()/2开始，到1
3. check(inner for): 从头-依次-取长度为str约数的substring，然后通过叠加(str.length()/substring.length())个substring，看结果是不是str

####3. Longest Substring Without Repeating Characters
思路都差不多，实现方式不同，有用hashset，hashmap和array三种，代码文件中详细都有，这里只写最简单的一种，set实现的
start, end means start of set and end of set. we always keep a substing without repeating characters in the set, the substring's length may change, so the set.size() also changes, we keep track of this size to find the max size.
```
    public int lengthOfLongestSubstring(String s) {
        int start = 0, end = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (end < s.length()) {
            if (!set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(start));
                start++;
            }
        }
        return max;
    }
```

####28. Implement strStr()
看起来是个很简单的题，花了很长时间，心塞. 自己是用了类似双指针的方法，haystack中有needle首字符时，记index of haystack，然后比对needle，haystack不够长就直接返回-1，如果重合元素==needle.length,说明是包含的，返回此时记的index。另外一种大牛的方法看起来很简介，但是有一句j+i不太懂，考试略忙也没有仔细想，mark一下回头看
//postscript
又遇到了一次这个题，还是花了很久，最后还是看了之前这里的答案。。。心塞，再细细记一下思路：
1. 边界值，如果needle==“”那么所有的haystack都可以包含，needle出现在haystack第0位，所以直接返回0；如果target或haystack为null，haystack==“”,那么一定是返回-1的。
2. 实现，
    1. 首先要遍历haystack。可以用for循环；
    2. 之后从每个h开始，看是否对应在needle中，这里为了标记 相同元素的起点，所以把h值存在tmp中，一会儿操作tmp，还有每次新的h都要从头在needle找对应；
    3. 下一步是如果n==tmp了，注意这里用while，只要n==tmp，两者一起自增，直到不相等，
    4. 这个while中有三个操作，第一，如果n==needle.length()-1 说明全都匹配上了，返回之前保存的h即可；第二，如果tmp>haystack.length()-1，说明needle中出现了更多haystack中没有的元素，肯定返回-1；第三，正在朝着1，2两种情况进行。
    5. 还需要注意这三种情况的顺序，先判断 n 中元素遍历完了没，完了就可以返回了。
    ```
    for(int n = 0;n<haystack.length();n++){
        int i = n;
        int j = 0;
        while(haystack.charAt(i)==needle.charAt(j)){
            if(j==(needle.length()-1)) return n;
            j++;
            i++;
            if(i>haystack.length()-1) return -1;
        }
    }
    ```

####lintcode strStr2


####283. move Zeroes
设置计数器，从计数器=0开始放非零数字，剩余（length-计数器）置0


####349.Intersection
嵌套for-loop找相同元素，把未出现过的相同元素存入ArrayList`re.contains();re.add();` ArrayList再转回int[]很麻烦`for(int i : IntegerList)`

####350. Intersection of Two Arrays II
现在用的是类似指针的思路，用while循环（避免for循环会出现的各种index问题）先排序（一般用指针都要排序），然后就一步一步往后挪，如果上下相等了，那就一起后移，不相等就小的后移，找大数来跟另外一个数组中的数匹配
更好的方法应该是hashmap吧

####171. excelColumnNumber
`re+=(sc[i]-'A'+1)*Math.pow(26,sc.length-i-1);`

####168. Excel Sheet Column Title
n%26得到0-25之间的26个数，刚好对应A-Z,但题目中given int是从1-A开始，所以只需要把given int n=n-1 后面就可以按照0-25 分别+65去转换对应的asc码就可以了
```
while(n>0){
    n=n-1;
    re = Character.toString((char)(n%26+65))+re;
    n/=26;
}
```
discuss推送了一个one line code，用了递归，很厉害
```
return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
```

####169. Majority Element
有种取巧的写法，可以说得通但是想不到，传统解法还是hashmap再mark一下这个`for(Integer k: m.keySet())`

####217. Contains Duplicate
用set是比较简单的方法，通过set.contains的方法来判断，for in 来遍历nums

####219. Contains Duplicate II
刷过一边的题果然ac率大大大幅提升，用hashmap，value存index以便判断distance<k

####455 assign cookies
跟下面一题的思路一样，用while循环，把两个数组排序，第二个数组中对应元素不小于第一个数组中对应元素，output++，两个元素都向后移，如果第二个数组中元素比第一个数组中对应元素小，说明饼干不够，要往后找更多饼干，所以第二个数组j++

####350. Intersection of Two Arrays II
现在用的是类似指针的思路，用while循环（避免for循环会出现的各种index问题）先排序（一般用指针都要排序），然后就一步一步往后挪，如果上下相等了，那就一起后移，不相等就小的后移，找大数来跟另外一个数组中的数匹配
更好的方法应该是hashmap吧

####401 binary watch
强行倒着解，把所有可能的小时和分钟列出来，那一时刻刚好对应二进制的1的个数跟num 相等，就输出这个时间，性能不好，也不太好想但好写的一种方法
标准写法应该是backtracking

####13 roman to int
 1. 这个题对我来说的难点好像在于for里判断的时候要i+1个元素个i个元素比较，所以for循环很难遍历全整个数组，方法是：会一开始的时候就把遍历不到，而且刚好也需要加上的最后一位直接赋值给result
 2. 最近总是想到用true fale这种方式判断，并不简单，而且切记boolean这个值是需要不停改变的，for循环里最后一句重新minus=false忘记加找了半天问题
 3. 另外这个题的超时问题也是因为打印语句

####453 minMove
1. 自己的思路是按照题目里说的，数组里除了最大元素都++，然后判断符不符合标准，复杂度太高，不能accepted
2. 大神的思路，把上面那种 除最大值外都+1 做n次这种操作达到数组元素全部相等 转换成 每个非min的元素每次-1需要的操作数
```
for(int n:nums){
    re+=(n-min);
}
```

####462. Minimum Moves to Equal Array Elements II
跟上面453思路类似,都是先排序，因为这个是小的数可以+1，大的数可以-1，所以排序后是两边都可以操作，每次操作次数等于两边差值
```
while(i < j){
    count += nums[j]-nums[i];
    i++;
    j--;
}
```

####447. Number of Boomerangs
1. 暴力解，取每个点当基准点并给他一个hashmap存距离，看有没有两点到他距离相等，
2. 注意两点，首先是map每次外层for都新建一个，然后是count，到基准点距离相等的两点可以互换，所以又一次匹配就有两种形式

####415 Add String
1. string1.length()>string2.length()`int x=len1<0?0:num1.charAt(len1)-'0';int y=len2<0?0:num2.charAt(len2)-'0';`
2. carry 

####268. Missing Number
1. 方法1，排序，然后用数组中的元素跟索引比较，索引有，元素没有的（其实就是i!=nums[i]）直接返回i，复杂度略高
2. 方法2，求和，已知我们有的是[0,n]之间distinct的数字，可以直接把[0,n]这些全部相加再-数组中所有元素和
    1. 即1+2+。。。+（index+1）（其实也就是length）与nums[0]+nums[1]+...的差值. (we are missing only one number in [0,n], we just need to look at the difference between the sum([0,n]) = n * (n+1) / 2 and the sum of nums in our array.)

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

####70. Climbing Stairs
一个很简单的动态规划问题，居然写出来了一个动态规划，一颗老心都快活过来了，还是只有一句核心代码，`step[i] = (step[i-1])+(step[i-2]);`

####198. House Robber
还是DP，连续rob两个房子就会alert
1. 首先，输入nums.length<2时，分两种情况，输入==0，返回0；输入==1，返回nums[0]
2. for循环中也要单列i=0,i=1时的情况，之后就是通用情况，money[i]表示max value rob ith house， money= max(money[i-1],money[i-2]+nums[i])前者是ith不rob，后者是ith rob

####441. Arranging Coins
也是一个很简单的题，给n个硬币，在第k行放k个硬币，能放到第几行，不完整的一行不算
用了while循环，略慢

####263. Ugly Number
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5。1 is ugly number
所以就拿一个数，只要能被2整除就一直除以2，然后，只要能被3整除就一直除以3，然后，只要能被5整除就一直除以5，如果剩下的不是1，那就不是ugly number

####27. Remove Element
if(nums[i]!=val) {nums[newLength]=nums[i];newLength++;}

####26. Remove Duplicates from Sorted Array
需要直接改变原数组，加一个新的计数器顺便当做index，这种方法好多array都用了，比如上面的27，这俩代码几乎一模一样就是移除的东西不同
```
 int n = 1;
for(int i = 1;i<nums.length;i++){
    if(nums[i]!=nums[i-1]){
        nums[n]=nums[i];
        n++;
    }
}
```

####66. Plus One
从最末位开始，+1，如果<=9直接返回，>9继续下一位+1，如果整个数（数组）遍历完都没返回，说明发生了这种情况9+1=10，需要在最前面多加1位

####172. Factorial Trailing Zeroes
查了一些题解，刚看到的时候比较懵逼，题解基本都是一个思路：0是2*5得到的，也就是我们需要知道有多少2，5 pair，2个数肯定会比5多很多，所以只需要知道5的个数，又因为25这个特例，意思大概是，每个25都可以再得到两个5 去跟2凑pair，所以25看似是5-10-15-20这4个，但25自身还有两个五，所以可凑6pairs。如果能明白题意，并且可以分析题目得到这一步，那代码就很简单了
```
 while(n>0){
    re+=n/5;
    n/=5;
}
```

##palindrome(4,还有一道链表题234)
####9. palindromeNumber
不能转换string，所以首先想到 不停的取原数字%10得到新数字首位，然后对比新数字和原数字
代码也是这个思路，不过有很多细节需要注意,比如while的循环条件，不需要x一直到0，rex的组成方式，rex*10那里一开始没想到的，后面返回的时候也需要注意，看起来很简单的题，写起来全是坑，就这样
```
 while(x>rex){
    rex = rex*10+x%10;
    x/=10;
}
//位数是偶数||位数是奇数
return (rex==x||x==rex/10);
```

####125. Valid Palindrome
1. 方法1：string的问题用string的方法来解决，replaceAll, 前面通过正则去掉所有非字母字符，之后equal方法，但注意要new一个stringbuffer对象
```
String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
return actual.equals(new StringBuffer(actual).reverse().toString());
```
2. 操作数组，类似双指针的方法，一种神奇的for方式， 从数组前后，i，j一起for：`for(int i = 0, j = s.length() - 1; i < j; ++i, --j) {`
还有char奇奇怪怪的方法，`!Character.isLetterOrDigit(s.charAt(i))` 返回true的时候说明不是letter，还有`character.toLowerCase`

####409. Longest Palindrome
看上去吓一跳稍微想一下还挺简单的，就是出现奇数次数的字母和出现偶数次数的字母的判断，出现偶数次的，全+，出现奇数次的，除了%2这部分全加，最后，中间可以有一个奇数次的，所以如果有出现奇数次的字母出现，最终结果+1。我自己写的解法估计不太好，尤其a那里，accepied的时候也是忍不住笑出声

####36. Valid Sudoku
横竖的判断就是ij互换一下，需要哪个坐标变就把内层循环的int放过去，比如列的时候需要x坐标变，就把内层循环的j放过去
横竖都好判断，每个cube不好判断，坐标不好想，discuss里发现了一种很好的方法.类似用一次循环画一个cube或者二维数组的意思，i/3=x,i%3=y,x,y标明一个点
```
if(board[3*(i/3) + j/3][3*(i%3) + j%3]!='.' && !cube.add(board[3*(i/3) + j/3][3*(i%3)  + j%3])) return false;
```

####37. Sudoku Solver


####205. Isomorphic Strings
论坛里看到的解题思路，感觉现在不适合刷题，想到hashmap但是不知道isomorphic的两个词到底什么关系
思路：本题的核心点在于，如果不是isomorphic strings，s相同的char在t中会对应不同的char，t中相同的char会对应不同的char。check两者相互对应的关系，不难想到2个hashmap。我这里的做法是1个hashmap和1个set，在遍历s,t的过程中，以s的char做key，t对应的char做value，如果相同key出现了2个value，必然会错。同时set记录t已经用过的char，如果不同的key使用相同的char，必定也不是。（本题易忽略的就是后者）
 `for(int i:hashmap.values()){...}`
注意，hm.put()这个方法，官方api写的是：the previous value associated with key, or null if there was no mapping for key.
discuss里找到的代码，实现方法很简单，但是含义丰富，所以贴全部代码，又多了一道要背的题
```
 public boolean isIsomorphic(String s, String t) {
    Map<Character, Integer> m1 = new HashMap<>();
    Map<Character, Integer> m2 = new HashMap<>();
    for(Integer i = 0; i < s.length(); i++) {
        //hm.put() returns  it's the previous value associated to this key
        System.out.println("i:"+i+"-1:"+m1.put(s.charAt(i), i)+"-2:"+m2.put(t.charAt(i), i));
        if(m1.put(s.charAt(i), i) != m2.put(t.charAt(i), i)) {
            return false;
        }
    }
    return true;
}
/*
注意，hm.put()这个方法，官方api写的是：the previous value associated with key, or null if there was no mapping for key.
test case:
    "aba" "baa"
stdout:
    i:0-1:null-2:null
    i:1-1:null-2:null
    i:2-1:0-2:1
根据输出可以看出，如果映射已经存在，会输出对应的value，在这里就是以char为key对应的value（index）
*/
```

####290. Word Pattern
基本就是复用了上面的代码
注意两点：
1. 如果两者长度不同，pattern肯定不同，所以直接返回
2. for循环是通用的核心代码，就是看映射关系是否一致，比如pattern存入map的时候，如果key已存在，会返回key对应的value，即pattern中key-Character对应的value-index；同理parts存入map时，如果key-parts[i]已存在，会返回对应value-i

####118. Pascal's Triangle
又是杨辉三角，这种题看要求返回的类型立刻想到用嵌套的list， 实现的时候需要注意第一行自己放好，后面每行的第一个和最后一个肯定都是1，其他没了
注意用list.add，核心就这句`inner.add(tri.get(i-1).get(j-1)+tri.get(i-1).get(j));`

####119. Pascal's Triangle II
改了一下上面的题（两处：1. 外层for i<rowIndex+1; 2. 返回tri.(rowIndex);），秒ac，不过应该有更简单的方法
```
List<Integer> res = new ArrayList<Integer>();
for(int i = 0;i<rowIndex+1;i++) {
    res.add(1);
    for(int j=i-1;j>0;j--) {
        res.set(j, res.get(j-1)+res.get(j));
    }
}
```

####400. Nth digit
表示不会做，想了一下但是没想到怎么实现，有一个稍微好理解一点的例子，For example given n is 1000, we first -9（9个数，每个数len=1） and then -180（90个数，每个数len=2）. The left is 811. The number is 100+810/3=370.（注意这里是100+，前面都是-99，所以这里811-1） The digit is the (810%3=0)th. Therefore, the digit is 3

####58. Length of Last Word
一句话ac `return s.trim().length()-s.trim().lastIndexOf(" ")-1;`

####299. Bulls and Cows
算cows的时候用了创建新的长度=10的数组来表示每个string中数字出现情况, secret+1， guess-1 判断secret中ith char是否<0,如果小了说明guess中猜对了ith char，b++，就是cow++
```
if (numbers[secret.charAt(i)-'0'] <0) b++;
    numbers[secret.charAt(i)-'0']++;
if (numbers[guess.charAt(i)-'0'] >0) b++;
    numbers[guess.charAt(i)-'0']--;
```

####88. Merge Sorted Array
看起来很简单的题，discuss里有很多3行代码1行代码什么的，我还是找了个最容易看懂的, 题目中有说明nums1的长度是存的下m+n的，既然是向nums1中插入，那为了避免懂前面已有元素，我们从后面操作，两个数组都是sort好的，所以秩序比较两个数组最右的元素，大的即为全场最大，可以直接放，后面也都是一样的思路，nums[n--]这种形式还玩不转，先不用
```
while(n>0){
    if(m>0&&nums1[m-1]>nums2[n-1]){
        nums1[m-1+n]=nums1[m-1];
        m--;
    }else{
        nums1[m-1+n]=nums2[n-1];
        n--; 
    }
}
```

####223. Rectangle Area
需要判断一下overlap的各个坐标
```
int left = Math.max(A,E), right = Math.max(Math.min(C,G), left);
int bottom = Math.max(B,F), top = Math.max(Math.min(D,H), bottom);
return (C-A)*(D-B)+(G-E)*(H-F)-(right-left)*(top-bottom);
```

####1. Two Sum
这类题可以算是刷题的动力吧，看过一次下一次居然真的会了，改了一个；错误提交居然过了，hashmap
（后有改进版，arr in order，用hashmap太慢，用双指针，从前和从后同时查找，167题）

####167. Two Sum II - Input array is sorted
1题改进版，双指针更快，从前和后同时查找，注意while判断条件，我写的l<=r考虑到会有【1，2，4】t=4就会需要2+2，另外遇到匹配项记得跳出循环。

####204 count primes
Count the number of prime numbers less than a non-negative number, n. 和61B例子不同
根据hint采用这个方法：Sieve of Eratosthenes\-ˌer-ə-ˈtäs-thə-ˌnēz\
遍历的时候是i*i<n,  `j+=i;` 相当于遍历所有i的倍数

####189.Rotate Array  
1. 数组直接赋值是浅拷贝，int[] a = ...; int[] b = a;这种情况下，a改了b也会改动！！所以用array.clone();这个方法
2. k>nums.length的时候需要处理的，明显是%
3. k=0的时候不需要操作，所以操作前判断一下

####396. Rotate Function
笨方法就是按照题目给的强行算，自己能写出来但是跑巨慢，大神的方法在[这里](https://discuss.leetcode.com/topic/58459/java-o-n-solution-with-explanation), 一句两句的也说不清楚，算法太神奇了

####414. Third Maximum Number
看起来越是简单的题坑越多，按发现问题的顺序
1. 最大值赋给first后，原first值变为second，注意别丢了
2. 重复元素不计数，所以遇到重复元素直接跳过，不然影响结果
3. 根据测试用例来看，需要long,其实就是处理数据极值的问题，大部分题都要考虑这点

####303. Range Sum Query - Immutable
这个也是可以自己写的，就是写出来的比较慢，有个蜜汁答案，想的很新奇，复杂度很低，很好懂，太牛了。思路是，在构造方法中直接创建一个数组，每个元素=该元素和之前所有元素和，之后调用sumRange的时候就返回类属性这个数组的i j 元素差就可以了，棒

####67. Add binary
从末尾起遍历两个string，
1. 如果当前计数>长度，该位为1
2. sum= A当前位+B当前位+carry（可能=1 or 0）+之前求得的sum，拼接在后面
    (curIntA+curIntB+c)%2==curIntA ^ curIntB ^ c
3. 计算carry位

####461. Hamming Distance
字符串中不相同的字符有几个，hamming distance就是几，题目看起来好像很难很陌生的样子，其实看懂例子之后还挺简单的。居然自己写的一遍过，真真的一遍过了都没语法错误。
思路：题目的意思就是给两个int，这两个int的二进制表示方式中有几位是不一样的
很简单，%2之后作比较是否一样，然后这两个数/=2往下一位算

####6. ZigZag Conversio
discuss中看到的方法，思路是给rownumber， 然后就在row1放一个char，row=row+1放一个，+1和-1分别对应两种情况，其实可以理解为拐弯，比如row=0的时候，说明要往下走，row=row+1，row=rownumber-1的时候说明要往上往回走了，所以row=row-1。高端的不行

##binarySearch
####278. First Bad version
看题应该会立刻想到二分查找，另外这个思想和和438中sliding window的思路有有一点类似.
这个题需要搞清楚两点：
1. 如果mid不是，那mid前都不是，查找mid后面，如果mid是，查找mid前
2. 返回谁，我是举了个例子试了一下，不过从if这句可以看出，返回start
```
while(start<end){
    int mid = start+(end-start)/2;
    //all the versions after a bad version are also bad，所以如果mid没有，mid前肯定都没有，查找后一半
    if(!isBadVersion(mid)) start = mid+1;
    else end = mid;
}
```

####374. Guess Number Higher or Lower
跟上题一样，binary search 代码也几乎一样，==1说明更大，向后找，所以start=mid+1；==-1说明更小，向前找，返回start。可以背个模板了。

####475. Heaters
还是tag：binary search, 看题并没有很好的思路，discuss
```
public int findRadius(int[] houses, int[] heaters) {
    //用binary search前需要排序，不然答案不确定
    Arrays.sort(heaters);
    int result = Integer.MIN_VALUE;        
    for (int house : houses) {
        //Arrays.bianrySearch(object[] a, object key)如果它包含在数组中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。插入点被定义为将键插入数组的那一点：即第一个大于此键的元素索引。
        int index = Arrays.binarySearch(heaters, house);
        if (index < 0) {
            index = -(index + 1);
        }
        //如果<0，说明house在最左边heater的左边，dist1=max无意义，实际算的距离是dist2，此时=heaters[index] - house
        int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
        int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;        	
        result = Math.max(result, Math.min(dist1, dist2));
    }       
    return result;
}
```

####275. H-Index2
given: sorted array, require O(lgn)，搞清楚要求的话很容易想到binary search。注意有一种特殊情况：input: [0,1,2,4,5,6]，output: 3，整个while循环都不能输出正确答案，所以需要return输出的帮助
```
while(start<=end){
    int mid = (start+end)/2;
    if(citations[mid]==(citations.length-mid)) return citations.length-mid;
    else if(citations[mid]<(citations.length-mid)) start = mid+1;
    else end = mid-1;
}
return citations.length-start;
```

####274. H-Index
new一个新array实现类似哈希表的思想，新array的下标对应input的element，新array长度比input长1，输入的数组中，比较大的数字都记在最后一位：
比如input：[0,3，1，6，5] 
new array:[1，1，0，1，0，2]
      idx: 0  1 2  3  4 5
之后从后往前计算new array element sum 当 sum>=idx 此时的idx就是我们要找的h-index
```
public int hIndex(int[] citations) {
    int len = citations.length;
    if(len==0) return 0;
    int[] re = new int[len+1];
    for(int i = 0; i<len; i++){
        if(citations[i]>len) re[len]++;
        else re[citations[i]]++;
    }
    int sum = 0;
    for(int i = len; i>0; i--){
        sum+=re[i];
        if(sum>=i) return i;
    }
    return 0;
}
```

####448. Find All Numbers Disappeared in an Array
但愿是easy的最后一题了，总觉得easy要刷完了，结果就会出一道新题。。。
这个也是看起来简单，一开始的思路非常奇葩，看数组中的数+1和-1的数是不是存在，如果不存在就标记一下大概这样，实现了一下发现不行，应该是标记的问题，后来在discuss看到了一个不错的方法：
>The basic idea is that we iterate through the input array and mark elements as negative using nums[nums[i] -1] = -nums[nums[i]-1]. In this way all the numbers that we have seen will be marked as negative. In the second iteration, if a value is not marked as negative, it implies we have never seen that index before, so just add it to the return list.
另外这个题有点bug，原题给的example是：
>Input:
[4,3,2,7,8,2,3,1]
Output:
[5,6]
我看到discuss的代码的时候会想，what if nums[i]>nums.length，我就改动了下原有example,删掉了后面的2，3，1，expected answer也是报错的，但是，注意审题”Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.“

####190. Reverse Bits
result左移，空出一位放n的最右位，n右移，看见位运算还是懵逼，心塞

####atoi
1. 清除空格的方法`str = str.trim();`
2. 一位一位加`int digit = str.charAt(i) - 48;sum = sum*10-digit;`

####165 compare version
长度不相等的时候很麻烦:
1. 注意以 . perioud 为分隔符时候的写法`version1.split("[.]");version2.split("\\.");`
2. 注意每次while中才给temp赋值，即，如果s1比s2多一位，那s2的当前temp=0，然后判断
3. time complexity: O(m+n)，m和n分别是两个字符串的长度。

####7 reverse integer
1. int不行，long才可以,int放不下
2. 不需要取绝对值单独判断符号，%10的时候得到的结果是带-的
3. y是long，但需要的结果是int，所以需要判断y能不能转int，就是在最大值和最小值之间



easy除tree和linkedList问题外全部过一遍

##Medium

####338. Counting Bits
Q:For num = 5 you should return [0,1,1,2,1,2].
比如返回2-4之间的数，可以先算上2的结果，再加上后面的部分，所以核心代码`result[i] = result[i>>1] + (i&1);`

####260. Single Number3
1. 又是用了传统的hashmap方法，在以前代码的基础上，检测map中value==true的值部分做了很小的改动就可以了。思路还是把数组存入map，出现一次对应value=T，再出现的value=F，之后遍历map(`for(Integer a: m.keySet()){...}`)找value=true
但隐约记得当年用go刷题的时候看到过牛逼的解法，所有数组元素进行一次位运算，得到singlenumber（只有一个的时候），或者得到两个singlenumber的位运算结果，然后再用一种黑科技把两个数分开。
2. 当年笔记，其中一只不太懂的一点，如果是3^3 Vs. 3^2^1结果都是0，。。怎么区分
> n=n^val        //如果是出现两次的数，按位异或的结果是0，n就还是以前的n，所以最后return n 就是single number（一个single number）
如果两个single number，n就是剩下两个single number按位异或的结果
```
n &= -n
//n和自己的相反数相与（&），结果是该数的最低为1的位
//比如这里n是6，0010从右数第二位是1，也就是说3^5从右起第一位不一样的是在第二位
//3和5在倒数第二位上有所不同。再次遍历数组，只是这次我们根据倒数第二位上是0还是1，把数组中的数分成两组。这样可以保证3和5一定不在同一组。然后再对2组分别进行异或运算，即可得到最后的结果。
for _, v1 := range nums {
    if (v1 & n) == 0 {
        result[0] ^= v1
    } else {
        result[1] ^= v1
    }
}
```
3. discuss区类似上面的方法：Find the rightmost set bit, divide numbers into two groups. Each group will end up being one unique number.
下面是核心部分，
```
int xor = nums[0];
for (int i=1; i<nums.length; i++){
    xor ^= nums[i];
}
int bit = xor & ~(xor-1);
int num1 = 0;
int num2 = 0;
for (int num : nums){
    if ((num & bit) > 0) num1 ^= num;
    else num2 ^= num;
}
result[0] = num1;
result[1] = num2;
```

####413. Arithmetic Slices
Example: A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
>
一条很有用的discuss：sum += curr really does the trick. Brilliant!
I think the easy way to understand this is that adding current number to our existing arithmetic sequence, we will have curr additional combinations of new arithmetic slices.
Let's say if we have [1, 2, 3, 4] and currently we have 3 arithmetic slices (curr is 2). We are going to add 5 to our arithmetic sequence. So that we will have curr new slices (curr is 3), which is [3, 4, 5], [2, 3, 4, 5] and [1, 2, 3, 4, 5]. Now, the total valid arithmetic slices is 3 + curr = 6. That's exactly the same as sum += curr.

####406. Queue Reconstruction by Height
题目大意：Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.
>ex:
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

厉害了的思路（discuss）：(下面的算法就是这个思路的实现)
1. Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
2. For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.

一个java语法点：（parameters）->{statements;}
1. 只有一个参数时，可以不加（）
2. 没有参数时 ()->{...;}
3. statements有return时必须{}，其他情况可以不加
```
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
           @Override
           public int compare(int[] a, int[] b){
               //h不相等的时候大的在前面，h相等时，k小的在前面
               return a[0]!=b[0]?-a[0]+b[0]:a[1]-b[1];
           }
        });
        //        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> res = new LinkedList<>();
        for(int[] cur : people){
            res.add(cur[1],cur);       
        }
        return res.toArray(new int[people.length][]);
    }
}
```

####238. Product of Array Except Self
注意算right的时候不能简写省去right，因为此时re已经有左边的值了，不能像上面处理左边一样直接用re[i-1]/re[i+1]

####442. Find All Duplicates in an Array
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array),这句话很熟悉，数组中所有元素都是<n所以可以用a[a[i]]元素当下标的方式，跟一个题很类似
1. 遍历数组，把数组中元素值取绝对之后（确保>0）当做索引 idx
2. 如果 nums[idx]>0 (initially all elements >0) num[idx] = -num[idx]
3. 如果 nums[idx]<0，说明我们已经见过一次该元素了，此数即duplicate，注意这里的此数是nums[i]
```
for(int i=0;i<nums.length;i++){  
    int idx =Math.abs(nums[i]);  
    if(nums[idx-1]<0){
        re.add(idx);
    }else{
        nums[idx-1]=-nums[idx-1];
    }
}
return re;
```

####357. Count Numbers with Unique Digits
第一种是很数学方法，第二种是DP，DP很好理解，数组re[0],re[1]...re[n]分别存储n位数对应的每位不重复的数字的个数，
 * n = 0, count = 1 --> re[0]
 * n = 1, count = 1+9 --> re[0]+re[1]
 * n = 2, count = 1+9 + 9*9 --> re[0]+re[1]+re[2]
 * n = 3, count = 1+9 + 9*9 + 9*9*8
 * ...
 */

##61B刚好看到LinkedList 所以把之前空的链表题都补一下，下面全部都是linked list相关
####237. Delete Node in a Linked List
代码很简单，道理也都懂，思路也没错，但居然写成了注释里那种形式，有一点被注释里的class definition 影响了，还有是对构造函数的不熟悉，总之这个主意吧。
思路是最后一个node的值付给当前，当前的.next置成next.next
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x;  }
 * }
 */
node.val=node.next.val;
        // node.ListNode(node.next.val);不能这么用，构造方法，new的时候才能用
node.next=node.next.next;
```

####206. Reverse LinkedList
没做过一上来感觉不太好想，有个视频，看到一半恍然大悟：https://www.youtube.com/watch?v=sYcOK51hl-A
这类题都开始要求用iteration写一次再用recursive写一个
```
 //假设1->2->3->4,先从head=1开始，要翻转，最后一个会变成head，所以head一步一步向后挪，每一步也一起翻转指向
public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head!=null){
            ListNode nextHead = head.next;
            head.next = prev;
            prev=head;
            //这里得到的prev即下一个head前面的数，也就是下一个head要指向的数，当head=最后一个node（tail）时，prev=tail，循环结束
            head = nextHead;
        } 
        return prev;
    }
    //recursive
     public ListNode reverseList(ListNode head) {
        return reverseRecursive(head,null);
    }
    public ListNode reverseRecursive(ListNode head,ListNode prev){
        if(head==null) return prev;
        ListNode nextHead = head.next;
        head.next=prev;
        //下面传参其实就相当于这两句：prev=head;head = nextHead;
        return reverseRecursive(nextHead,head);
    }
}
```

####92. Reverse Linked List II
For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,
return 1->4->3->2->5->NULL.
>根据题中的例子，第二个for循环开始
loop1:
1 --> 2 --> 3 --> 4 --> 5 --> NULL
p     c     n
cur.next = next.next;
2 --> 4
next.next = prev.next;
3 --> 2
prev.next = next;
1 --> 3
==> 1 --> 3 --> 2 --> 4 --> 5 --> NULL
    p          c      n
loop2:
cur.next = next.next;
2 --> 5
next.next = prev.next;
4 --> 3
prev.next = next;
1 --> 4
==> 1 --> 4 --> 3 --> 2 --> 5 --> NULL
```
public ListNode reverseBetween(ListNode head, int m, int n) {
    if(m == n) return head;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode cur = head;
    //在reverse之前的部分平移
    for(int i = 0; i < m - 1; i++){
        prev = prev.next;
        cur = cur.next;
    }
    for(int i = 0; i < n - m; i++){
        ListNode next = cur.next;
        cur.next = next.next;
        next.next = prev.next;
        prev.next = next;
    }
    return dummy.next;
}
```





####83. Remove Duplicates from Sorted List
还是一种iteration一种recursion,这个题可以不用dummy，删除第二个重复元素，确保head不会改动
```
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //改动list不改动head，最后返回dead
        ListNode list = head;
        while(list!=null){
            if(list.next==null){
                return head;
            }else if(list.next.val == list.val){
                //这里跟237那道delete一个意思
                list.next = list.next.next;
            } else{
                list = list.next;
            }
        }
        return head;
    }
    //recursive
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}
```

####82. Remove Duplicates from Sorted List II
这个题不会做！！第一个方法还稍微好懂一点，拿[1,1,1,2,3]的例子来说，第一个内层while结束，slow.next=head=1, fast=最后一个1， 进入if(注意并不是比较val)，之后就，代码肯定能看懂，但是dummy.next的变化一直想不通，fast slow都满满指向最后面去了，dummy.next是怎么移动到2，又是怎么不移动了的。。。
```
public ListNode deleteDuplicates(ListNode head) {
	//use two pointers, slow - track the node before the dup nodes, 
	// fast - to find the last node of dups.
    ListNode dummy = new ListNode(0), fast = head, slow = dummy;
    slow.next = fast;
    while(fast != null) {
    	while (fast.next != null && fast.val == fast.next.val) {
     		fast = fast.next;    //while loop to find the last node of the dups.
    	}
    	if (slow.next != fast) { //duplicates detected.
    		slow.next = fast.next; //remove the dups.
    		fast = slow.next;     //reposition the fast pointer.
    	} else { //no dup, move down both pointer.
    		slow = slow.next;
    		fast = fast.next;
    	}
    }
    return dummy.next;
}
```


####203. Remove Linked List Elements
删除指定element，看起来很简单，写错了两点,第二个方法，dummy-哑节点，的使用多针对单链表没有向前指针的问题，保证链表的head不会在删除操作中丢失，或者用来删除head
所以当链表head可能有变化时就用dummy，`ListNode dummy = New ListNode(0);dummy.next=head;`最后返回dummy.head
```
public ListNode removeElements(ListNode head, int val) {
    //1. while，不用if，test case：[1,1] 1,如果head一直是val，那就一直向后取，直到head.val!=val
    while(head!=null&&head.val==val) head= head.next;
    ListNode next = head;
    while(next!=null&&next.next!=null){
        if(next.next.val==val) next.next = next.next.next;
        //2. else 提交出错，忘了写else了。。。test case:[1,2,2,1] 2
        else next = next.next;
    }
    return head;
}
//dummy, 给加个head，这样之前的head就可以当做普通node来处理了，很好的方法
public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = New ListNode(0);
    dummy.next=head;
    head = dummy;
    //现在head.next才是之前的head
    while(head.next!=null){
        if(head.next.val==val) head.next = head.next.next;
        else head = head.next;
    }
    return dummy.next;
}
```

####19. Remove Nth Node From End of List
walker and runner, init walker,runner both as dummy, move runner n steps, so that the gap between runner and walker =n, then move runner and walker together, when runner get to the end of List, walker is before the nth from the end node, walker.next=walke.next.next， skip original walker.next
```
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode walker = dummy;
    ListNode runner = dummy;
    // after for loop, gap between runner and walker =n
    for(int i = 1; i <= n; i++){
        runner = runner.next;
    }
    while(runner.next!=null){
        runner = runner.next;
        walker = walker.next;
    }
    walker.next=walker.next.next;//skip nth node
    return dummy.next;
}
```

####328. Odd Even Linked List
拼接链表，可通过多个dummy实现，注意  和value无关，只和第几个node有关
```
public ListNode oddEvenList(ListNode head) {
    ListNode OddDummy = new ListNode(0);
    ListNode EvenDummy = new ListNode(0);
    ListNode odd = OddDummy;
    ListNode even = EvenDummy;
    boolean isodd = true;
    while(head!=null){
        if(!isodd){
            even.next = head;
            even = even.next;
        }else{
            odd.next = head;
            odd = odd.next;
        }
        head = head.next;
        isodd = !isodd;
    }
    even.next = null;
    odd.next = EvenDummy.next;
    return OddDummy.next;       
}
```

####86. Partition List
和上题类似的思路，大于x和小于x的部分分别用两个dummy连起来
代码也基本和上面一样，就是isodd换成和x大小的比较。加深了对dummy的理解。一开始的odd，even初始化那里，意思是odd和odddummy， even和evendummy分别指向同一个node，所以odd，even next改变的时候对应的dummy.next也改变了，但之后odd，even指向下一个node了，不和dummy指向同一个node了，所以改变都不影响到dummy

####24. Swap Nodes in Pairs
看起来很简单的题和思路，但是很容易想乱，比如哪个是真正的node，哪个是为了保存出来的node。这里current.next是1，current.next.next是2，这里要搞清楚。因为后面是要把1，2付给别人，所以，在复制之前不能改变他俩本身的值，，我也不知道我在说什么了，总之很容易乱，画个图就都清楚了
```
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode current = dummy;
    while (current.next != null && current.next.next != null) {
        ListNode first = current.next;
        ListNode second = current.next.next;
        first.next = second.next;
        current.next = second;
        current.next.next = first;
        current = current.next.next;
    }
    return dummy.next;
}
```

####141. Linked List Cycle
用双指针的思路，walker moves step by step. runner moves two steps at time. if the Linked List has a cycle walker and runner will meet at some
point. 解法代码下一题中其实是包含的，但我还是把这个代码贴出来了，因为判定条件那里需要注意，这道题的写法是，先判断了head==null，之后while中判断runner.next和runner.next.next，个人理解是runner跑的快，需要注意判断runner而不是walker。但是下一题的写法我又重新懵逼了，下一题没有单独的判断head，而是直接while种判断了runner和runner.next，我个人的理解是还需要再判断runner.next.next的，但是没判断也ac了，这里不太懂。。
```
public boolean hasCycle(ListNode head) {
    if(head==null) return false;
    ListNode walker = head;
    ListNode runner = head;
    // runner跑的快，在前面，所以判断runner.next, runner.next.next
    while(runner.next!=null&&runner.next.next!=null){
        walker = walker.next;
        runner = runner.next.next;
        if(walker==runner) return true;
    }
    return false;
}
```

####142. Linked List Cycle2
关于判定条件的一个问题上道题中解释了
这个题目的思路不太好想，discuss中有一个很好的解释，贴过来，其中关键的两点是，walker走过的距离和cycle长度的关系，以及walker,runner相遇之后再通过head和walker一齐走，相遇点是cycle起点这层关系
>[Explanations](https://discuss.leetcode.com/topic/27868/concise-java-solution-based-on-slow-fast-pointers)
Definitions:
Cycle = length of the cycle, if exists.
C is the beginning of Cycle, S is the distance of slow pointer from C when slow pointer meets fast pointer.
Distance(slow) = C + S, Distance(fast) = 2 * Distance(slow) = 2 * (C + S). To let slow poiner meets fast pointer, only if fast pointer run 1 cycle more than slow pointer. Distance(fast) - Distance(slow) = Cycle
=> 2 * (C + S) - (C + S)	= Cycle
=>	C + S = Cycle
=>	C = Cycle - S
=> This means if slow pointer runs (Cycle - S) more, it will reaches C. So at this time, if there's another point2(we use head Here) running from head
=> After C distance, point2 will meet slow pointer at C, where is the beginning of the cycle.
```
public ListNode detectCycle(ListNode head) {
    ListNode walker = head;
    ListNode runner = head;
    //这里不加runner.next.next!=null也ac
    while(runner!=null&&runner.next!=null&&runner.next.next!=null){
        runner = runner.next.next;
        walker = walker.next;
        if(runner==walker){
            while(head!=walker){
                head = head.next;
                walker = walker.next;
            }
            return walker;
        }
    }
    return null;
}
```

####2. Add Two Numbers
简单版：Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
这种题对我来说的难点都在 创建sentinel，和d，不停在d后面添加node这些地方，这两种方法其实思路一样，实现上稍有区别
```
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {     
    ListNode cur1 = l1,cur2 = l2;
    ListNode sentinel = new ListNode(0);
    ListNode d = sentinel;
    int carry = 0,sum=0;
    while(cur1!=null||cur2!=null||carry!=0){
        sum = (cur1 != null ? cur1.val : 0) + (cur2 != null ? cur2.val : 0) +carry;
        carry = sum / 10;
        d.next = new ListNode(sum % 10);
        d = d.next;
        cur1 = (cur1!=null?cur1.next:null);
        cur2 = (cur2!=null?cur2.next:null);    
    }
    return sentinel.next;
}
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode c1 = l1;
    ListNode c2 = l2;
    ListNode sentinel = new ListNode(0);
    ListNode d = sentinel;
    int sum = 0;
    while (c1 != null || c2 != null||sum/10!=0) {
        sum /= 10;//sum means carry here
        if (c1 != null) {
            sum += c1.val;
            c1 = c1.next;
        }
        if (c2 != null) {
            sum += c2.val;
            c2 = c2.next;
        }
        d.next = new ListNode(sum % 10);
        d = d.next;
    }
    return sentinel.next;
}
```

####445. Add Two Numbers II
跟上面那题很类似，但是Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7
代码用stack实现，一开始是用了三个stack，后来发现可以通过改变新链表的拼接方式生一个stack
```
//stack3
ListNode dummy = new ListNode(0);
ListNode d = dummy;
while(!s.empty()||!s2.empty()||carry!=0){
    sum = (s.empty()?0:s.pop().intValue())+(s2.empty()?0:s2.pop().intValue())+carry;
    carry = sum/10;
    s3.push(sum%10);
}
while(!s3.empty()){
    d.next = new ListNode(s3.pop().intValue());
    d=d.next;
}
//without stack3, 在dummy前面一步一步加node
ListNode dummy = new ListNode(0);
while(!s.empty()||!s2.empty()||carry!=0){
    sum = (s.empty()?0:s.pop().intValue())+(s2.empty()?0:s2.pop().intValue())+carry;
    carry = sum/10;
    dummy.val = sum%10;
    ListNode d = new ListNode(sum%10);
    d.next = dummy;
    dummy=d; 
}
```


####234. Palindrome Linked List
假设是odd长（even同理）
一开始的思路是，分成前后相等两部分，中间点可以不管，把后半部分reverse一下，然后和前半部分比较看是否完全相同，看了好几个gitbook的实现都是这样的，有个[YouTube视频](https://www.youtube.com/watch?v=Os5FM4KQtxw&index=14&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj)，讲这道题，用的是stack，个人觉得用在这里很适合，把前半部分压栈，过中点之后依次pop出来跟后半截比较。
另一个技巧：用都指向 head 的快慢指针可以判断链表长度奇偶，最后 fast == null 的时候为偶，slow 指向后半单第一个节点;fast.next == null 的时候链表长度为奇数，slow 指向中间节点
```
 public boolean isPalindrome(ListNode head) {
    ListNode walker = head, runner = head;
    Stack<Integer> s = new Stack<>();
    //runner==null, if list.size is even, runner.next==null,when list.size is odd
    while(runner!=null&&runner.next!=null){
        s.push(walker.val);
        walker = walker.next;
        runner = runner.next.next;
    }
    //if the list size is odd, we should skip the middle node
    if(runner!=null) walker = walker.next;
    while(walker!=null){
        if(s.pop().intValue()!=walker.val) return false;
        walker = walker.next;
    }
    return true;    
}
```
但是，题目有要求O(1) space complexity,可以用一开始的思路实现，主体部分跟上面的思路很像，都用到了fast==null来判断长度是odd还是even这点，单独写了compare方法，默认了传入的两个list长度相同的，另一个reverse方法之前写过，reverse每次看都懵逼
```
public boolean isPalindrome(ListNode head) {
    if(head == null || head.next == null) return true;
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
    }
    if(fast == null){
        ListNode headB = reverse(slow);
        return compare(head, headB);
    } else {
        ListNode headB = slow.next;
        slow = null;
        headB = reverse(headB);
        return compare(head, headB);
    }             
}
 //这里默认传入的两个list长度一定相同，调用前做的处理
private boolean compare(ListNode headA, ListNode headB){
    while(headA != null && headB != null){
        if(headA.val != headB.val) return false;
        headA = headA.next;
        headB = headB.next;
    }
    return true;
}
private ListNode reverse(ListNode head){
    ListNode prev = null;
    while(head != null){
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}
```

####160. Intersection of Two Linked Lists
1. 一个general的方法, 比较两个linked list的长度，把较长的一个链表后移几位，从长度和另一链表相等处开始比较node是否相同。
一开始在想相交之后还会不会分开，比如一开始就相交，那长度不等情况下先向后移就说不过去了，但是这里应该是利用了链表特性，每个node都指向另一个node，所以相交之后就一定都一样了。
2. 一个很机智的方法，感觉用到了类似single linked list中判断是否有cycle时候用的runner 和walker双指针的方法，这个题中的“双指针”总会在intersection处相遇或者没有intersection在最后的null相遇.
disscuss区大神的分析:
>use two iterations here. In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two pointers until they points to the same node. Our operations in first iteration will help us counteract the difference.
So if two linkedlist intersects, the meeting point in second iteration must be the intersection point. If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null

Notice：只贴一下第二个方法，第一个方法很简单，分别遍历链表直到空，通过counter获取长度，然后通过两个长度差值移动指向较长链表的node的位置，在等长之后比较node是否相同，是就返回该node。
 ```
 public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null) return null;
    ListNode a = headA;
    ListNode b = headB;
    while( a != b){
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }
    return a;
}
 ``` 

####147. Insertion Sort List
目前看来打算写的最后一道linked list题
看题的时候在discuss发现了很多有意思的东西，比如这个
>Thoughts from google interviewer
For God's sake, don't try sorting a linked list during the interviewer

and
>sort array or sort linkedlist[here](http://stackoverflow.com/questions/1525117/whats-the-fastest-algorithm-for-sorting-a-linked-list/1525419#1525419)
Depending on a number of factors, it may actually be faster to copy the list to an array and then use a Quicksort.
The reason this might be faster is that an array has much better cache performance than a linked list. If the nodes in the list are dispersed in memory, you may be generating cache misses all over the place. Then again, if the array is large you will get cache misses anyway.
Mergesort parallelises better, so it may be a better choice if that is what you want. It is also much faster if you perform it directly on the linked list.
Since both algorithms run in O(n * log n), making an informed decision would involve profiling them both on the machine you would like to run them on.
这个题表示不会做，简化算法if判断的原因： Before insert, the prev is at the last node of the sorted list. Only the last node's value is larger than the current inserting node should we move the temp back to the head
```
public ListNode insertionSortList(ListNode head) {
    if( head == null ){
        return head;
    }
    ListNode dummy = new ListNode(0); //new starter of the sorted list
    ListNode cur = head; //the node will be inserted
    ListNode pre = dummy; //insert node between pre and pre.next
    ListNode next = null; //the next node will be inserted
    //not the end of input list
    while( cur != null ){
        next = cur.next;
        //find the right place to insert
        while( pre.next != null && pre.next.val < cur.val ){
            pre = pre.next;
        }
        //insert between pre and pre.next
        cur.next = pre.next;
        pre.next = cur;
        //reset，还有一种简化的方法，这里pre不重置到dummy，下次近inner while前判断一下if (prev.val >= head.val) prev = dummy;
        pre = dummy;
        cur = next;
    }
    return dummy.next;
}
```

####148. Sort List
sortlist的题都很麻烦的样子，所以记得不要sortlist。。。
这个题有特殊要求，O(1) space complexity。首先，strict O(1) auxiliary space complexity means the maximum number of memory used by the program, except the memory taken by the input data, doesn't change with the input size. 所以，strictly speaking, any solution that involves recursion can never have a strict O(1) auxiliary space complexity. Because the maximum recursion level depends on the the input size and each recursion call consumes memory on stack, thus the maximum number of memory used depends on the input size.
简单来说，递归的都做不到O(1)，大部分是O(logn)，但我只能看懂一个递归的方法，不递归的思路看起来很简单就是merge sort，代码看起来好复杂
```
public class Solution {
    public ListNode sortList(ListNode head) {
        //这个if很棒，返回head
        if(head==null||head.next==null) return head;
        //split the list into 2 pieces;
        ListNode midEnd = head;
        ListNode walker = head;
        ListNode runner = head;
        while(runner != null&& runner.next!=null){
            midEnd = walker;
            walker = walker.next;
            runner = runner.next.next;
        }
        midEnd.next=null;
        //sort each part by divide conqure method: divide --> merge in order
        ListNode l1 = sortList(walker);
        ListNode l2 = sortList(head);       
        return merge(l1,l2);
    }
    public ListNode merge(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;   
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }       
        if (l1 != null) cur.next = l1;    
        if (l2 != null) cur.next = l2;
        return dummy.next;         
    }
}
```

####21. Merge Two Sorted Lists
还是iteration和recursion,iteration代码太长了，由此可见递归的好处，代码简介易懂
iteration注意 l1,l2挨个merge的时候为了方便，l1,l2在merge后指向自己next，即后移，同时head即新链表的当前node也后移，另外这里也是head不确定的情况，所以用dummy
```
//dummy
    ListNode dummy = new ListNode(0);
    ListNode head = dummy;
    ...
    return dummy.next;
//recursion
public ListNode mergeTwoLists(ListNode l1, ListNode l2){
    if(l1 == null) return l2;
    if(l2 == null) return l1;
    if(l1.val < l2.val){
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else{
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```

####23. Merge k Sorted Lists
根据priority queue的特性，我们可以通过重写compare方法利用priority queue实现，还有dummy，从后向前拼接。
和下面sort里179一样，都重写了compare。一个是sort方法内，一个是priority queue
```
public ListNode mergeKLists(ListNode[] lists) {
    if (lists==null||lists.length==0) return null;
    PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>(){
        @Override
        /*
        1. 这里compare方法可以直接return n1.val-n2.val;
        */
        public int compare(ListNode n1, ListNode n2){
            if(n1.val<n2.val) return -1;
            else if(n1.val==n2.val) return 0;
            else return 1;
        }
    });
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    for(ListNode n:lists){
        if(n!=null) queue.add(n);
    }
    while(!queue.isEmpty()){
        tail.next = queue.poll();
        tail=tail.next;
        
        if(tail.next!=null){
            queue.add(tail.next)；
        } 
    }
    return dummy.next;
}
```

##sort相关(3 problems)
####215. Kth Largest Element in an Array
用quicksort的思想，实现O(n)的算法。快排的思想是分治，divide and conqure。这里因为只需要找到某元素，所以分治之后的部分只有包含要查找元素的那部分需要处理，剩下的不需要处理，所以原本快排是nlogn的算法，这里不完全排序，可以达到linear。
还有之所以看到了这题是因为下面的题用到了这个题目。
```
public int findKthLargest(int[] nums, int k) {
    //k<1||k>nums.length 也是边界值情况
    if(nums==null||nums.length==0) return Integer.MAX_VALUE;
    return findKthLargest(nums,0,nums.length-1,nums.length-k);
}
public int findKthLargest(int[] nums, int start, int end, int k){
    if(start>end) return Integer.MAX_VALUE;
    int pivot = nums[end];
    int left = start;
    for(int i = start; i<end; i++){
        //第一次默写这里少了if，对，默写，这个算法并不能完全理解自己再写出来，只能多写几次，装作是想出来的然后写出来
        if (nums[i] <= pivot){
            swap(nums,left,i);
            left++;
        }
    }
    swap(nums,left,end);
    if(left==k) return nums[left];
    if(left<k) return findKthLargest(nums,left+1,end,k);
    else return findKthLargest(nums,start,left-1,k);
}
public void swap(int[] n, int i, int j){
    int tmp = n[i];
    n[i] = n[j];
    n[j] = tmp;
}
```

####324. Wiggle Sort II
google面试题，到处都有跟这个题有关的讲解和介绍，我最初看的是九章的，比较好理解，后来discuss区也看到了高赞的答案，两个都写了，思路如下：
1. 利用快速排序的思想找中位数的期望时间复杂度是O(N).为了防止相等的数放在一起，需要注意放置的顺序。
2. 九章笔者采用的方法是依nums长度分两种情况：
    若长度为奇数，把比中位数小的依次放在0,2,4,...位置，比中位数大的依次放在length-2,length-4,... 位置；
    若长度为偶数，把比中位数小的依次放在length-2,length-4,...位置，比中位数大的依次放在1,3,5,... 位置。
    其余位置填充中位数。这样可以保证中位数一定与较小与较大的数相邻（题目保证一定有解）
另外，两个方法都用了上题的findKthLargest

```
public void wiggleSort(int[] nums) {
    int len = nums.length;
    int median = findKthLargest(nums,0, len-1, len/2);
    int n = nums.length;
    int left = 0, i = 0, right = n - 1;
    while (i <= right) {
        if (nums[newIndex(i,n)] > median) {
            swap(nums, newIndex(left++,n), newIndex(i++,n));
        }
        else if (nums[newIndex(i,n)] < median) {
            swap(nums, newIndex(right--,n), newIndex(i,n));
        }
        else {
            i++;
        }
    }
}
//这个mapindex到135024这种方法跟上面说的思路其实是一样的
private int newIndex(int index, int n) {
    return (1 + 2*index) % (n | 1);
}
//method2
public void wiggleSort(int[] nums) {
    if (nums == null || nums.length == 0)   return;
    int len = nums.length;
    int median = findKthLargest(nums,0, len-1, len/2);
    int[] re = new int[len];
    //necessary？
    for(int i = 0;i<len;i++){
        re[i] = median;
    }
    int s,l;
    if(len%2==0){
        s=len-2;
        l=1;
        for(int i = 0; i<len; i++){
            if(nums[i]<median){
                re[s] = nums[i];
                s-=2;
            }else if(nums[i]>median){
                re[l] = nums[i];
                l+=2;
            }
        }
    }else{
        s=0;
        l=len-2;
        for(int i = 0; i<len; i++){
            if(nums[i]<median){
                re[s] = nums[i];
                s+=2;
            }else if(nums[i]>median){
                re[l] = nums[i];
                l-=2;
            }
        }
    }
    for(int i = 0; i<nums.length; i++){
        nums[i] = re[i];
    }
    
}
```

####179. Largest Number
Given a list of non negative integers, arrange them such that they form the largest number.
For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
代码看起来很长，第一个for循环其实不用看，int数组存成string数组，重点在sort，override compareTo方法这里，直接比较拼接后的结果，注意注释掉的是一套，现在的版本是根据拼接结果从大到小排，比如[1,2]-->[2,1]; [10,2]-->[2,10]之后判断String[]首位是不是0，如果是说明全是0，返回0即可，如果不是开始拼接，这里一直向后拼接就可以了，如果排序时候用的s1.compareTo(), 那么这里的拼接需要向前拼接，用注释里的代码。`new Comparator<String>() {...}`注意这个的写法
```
public String largestNumber(int[] nums) {
    if (nums == null || nums.length == 0) return "";
    String[] strs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
        strs[i] = nums[i]+"";
    }
    //s2.compareTo(s1) sort array 9-0, s1.compareTo(s2) sort array 0-9
    //String.compareTo(String s) return <0 this<argument:s,
    Arrays.sort(strs, new Comparator<String>() {
        @Override
        public int compare(String i, String j) {
            String s1 = i+j;
            String s2 = j+i;
            // return s1.compareTo(s2);
            return s2.compareTo(s1);
        }
    });
    //if (strs[str.length-1].charAt(0) == '0') return "0";
    if (strs[0].charAt(0) == '0') return "0";
    String res = new String();
    for (int i = 0; i < strs.length; i++) {
        //res=strs[i]+res;
        res += strs[i];
    }
    return res;
}
```

####347. Top K Frequent Elements
还有一个重写compare的题，当然这个题用hashmap也可以，复杂度不知道行不行,在discuss区看到的maxheap实现的方法，因为要topk，所以用maxheap重写compare改变排序规则就可以实现，大牛 代码居然可以缩减到7行，有点厉害的，注意Lambda箭头函数，PriorityQueue 的 addAll(), Map 的 map.getOrDefault(n,0),map.entrySet(),map.getValue(),map.getKey()这些方法的灵活使用，基本可以记得，要priority queue，compare(a,b){return a-b;}
```
Map<Integer, Integer> map = new HashMap<>();
for(int n: nums) map.put(n,map.getOrDefault(n,0)+1);
PriorityQueue<Map.Entry<Integer,Integer>> maxHeap = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
maxHeap.addAll(map.entrySet());
List<Integer> re = new ArrayList<Integer>();
while(re.size()<k) re.add(maxHeap.poll().getKey());
return re;
```

####451. Sort Characters By Frequency
自从开是刷medium的题之后，每天都是炼狱模式，各种没见过的题型，要么就是想不出解法，要么就是想出的不符合要求，每次看答案都要看好久（生无可恋脸，这个题也是，要求O(n)，所以hashmap+排序value的方法是不满足要求的
思路：
1. 还是hashmap，但注意除了正常的创建map之外还需要保留一个max，即最高频字符出现的次数，后面要用
2. 构造一个array，这个array的index是array的element出现的此数，这里的array element是一个list，用来存放 出现次数相同的多个字符
3. 拼组string。通过StringBuilder，index是几，index位置的list里的char就append几次，直到遍历完整个数组。
还有一个细节需要注意，map存放的方式和后面 forloop的临界值要对应好，比如注释中的情况。
突然想到这里可以不用map 就 new int[128]出现的字符对应的下标位置存出现次数（不行好像还要sort）。。。

```
public String frequencySort(String s) {
    if(s==null) return null;
    Map<Character,Integer> m = new HashMap<>();
    int max = 0;
    for(char c: s.toCharArray()){
        if(m.containsKey(c)){
            m.put(c,m.get(c)+1);
        }
        else m.put(c,0);
        // if(!m.containsKey(c)){
        //     m.put(c,0);
        // } 
        // m.put(c,m.get(c)+1);
        //如果这里的map构造形式写成上面两行注释中的代码，那下面 最外层for和最内层for也要从>=,<=变成>,<
        //for(int i = array.length-1; i>0; i--){
        //for(int j = 0; j<i; j++){
        max = Math.max(max,m.get(c));
    }
    List<Character>[] array = buildArray(m,max);
    StringBuilder sb = new StringBuilder();
    for(int i = array.length-1; i>=0; i--){
        List<Character> list = array[i];
        if(list!=null){
            for(Character c: list){
                for(int j = 0; j<=i; j++){
                    sb.append(c);
                }
            }
        }
    }
    return sb.toString();
}
private List<Character>[] buildArray(Map<Character,Integer> map, int max){
    List<Character>[] arr = new List[max+1];
    for(Character c : map.keySet()){
        int count = map.get(c);
        if(arr[count]==null) arr[count] = new ArrayList<Character>();
        arr[count].add(c);
    }
    return arr;
}
```

####78. Subset
DFS recursion 经典题，可以当做模板来背，画递归树或者按递归步骤推演程序运算过程很有利于理解
tips: lintcode代码中必须加入排序提交才能通过
```
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null||nums.length==0) return results;
        //subset2 add:Arrays.sort(nums);
        ArrayList<Integer> subset = new ArrayList<>();
        dfsHelper(0,nums,subset,results);
        return results;
    }
    private void dfsHelper(int start, int[] nums, ArrayList<Integer> subset, List<List<Integer>> results){
        results.add(new ArrayList(subset));
        for(int i = start; i<nums.length; i++){
            //subset2 add: if(i>start&&nums[i]==nums[i-1]) continue;
            subset.add(nums[i]);
            dfsHelper(i+1,nums,subset,results);
            subset.remove(subset.size()-1);
        }  
    }
}
```

####90. Subset2
跟上题基本一样，多了两步，1 是排序数组，为了把重复元素防止一起，2 是helper里， 如果i不是start(i>start)且nums[i]和左边相邻元素相等，helper不进行，continue进入下一次forloop
代码标注在上面了。

####46. permutation
背会了subset也不会写permutation，好烦躁。这个题可以看出和subset的区别是 拼完之后才放入result中，所以就想helper之后再results.add，但走了条错路，正确的思路是，跟以前一样开始helper的时候result.add不同的是只有当subset==nums.length才添加这个subset. 又挣扎了一会儿发现就算写在后面也可以，写在后面跑一遍发现还是添加了subset那么多东西，就想到要要等三个了在加入results，再想到判断subset大小满足条件才添加，一样可以ac。。。一直觉得递归很难想可能就在于，我怎么都想不到在helper刚进入的时候添加上一次的subset，顶多能看懂，自己想总是把添加操作想在后面，sign。。
这题的关键就在于helper中的 if(){...}
```
//按照subset的格式写的话是这样的
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null||nums.length==0) return results;
        List<Integer> subset = new ArrayList<>();
        helper(nums,subset,results);
        return results;
    }
    private void helper(int[] nums, List<Integer> subset, List<List<Integer>> results ){
        if(subset.size()==nums.length){
            results.add(new ArrayList<Integer>(subset));
        }else{
            for(int i = 0; i< nums.length; i++){
                if(subset.contains(nums[i])) continue;
                subset.add(nums[i]);
                helper(nums,subset,results);
                subset.remove(subset.size() - 1);
            }
        }
    }
}
```

####permutation2
`if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue; `这个判断判断主要是为了去除重复元素影响。
比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就不应该让后面的2使用。
如果看不懂这句话，找一张大点的纸，按照程序写一下执行流程，执行到第二次放入第一个元素1的时候就明白了。
```
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        
    List<List<Integer>> results = new ArrayList<>();
        if(nums==null||nums.length==0) return results;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        ArrayList<Integer> subset = new ArrayList<>();
        dfsHelper(nums,used,subset,results);
        return results;
    }
    private void dfsHelper( int[] nums, boolean[] used, ArrayList<Integer> subset, List<List<Integer>> results){
        if(subset.size()==nums.length){
            results.add(new ArrayList<Integer>(subset));
        }else{
            for(int i = 0; i<nums.length; i++){
                if(used[i]) continue;
                if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue;
                used[i] = true;
                subset.add(nums[i]);
                dfsHelper(nums,used,subset,results);
                used[i] = false;
                subset.remove(subset.size()-1);
            }  
        }
    }
}
```

####392. is Subsequence
注意题目：A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
所以有个很简单的方法就是这样，注意转成char[]会快很多，原因还没仔细想，应该是string的操作都慢，因为string都是reference拷贝，所以慢吧，
想了个hashmap的方法,一个存map一次比较，注意这种不行，不能保证顺序
```
public boolean isSubsequence(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int i = 0, j = 0;
        while( i<ss.length&& j<tt.length){
            if(ss[i]==tt[j]){
                i++;
            }
            j++;
        }
        return i == s.length();
    }
```


































算是目标吧，easy-->mediam,ac高到低排，到这里应该200道
 



###遇到的关于github的问题
问题：
12/23/2016刷的题的commit都没显示到账户。具体如下：
12/23 换电脑，在新电脑上通过terminal，clone repository，开始刷题，通过vscode commit和push，push的时候vscode提示输入github帐密，我就输入了一下，然后github上不显示我原本github的用户名xy7313的commit反而显示了真实用户名提交的commit，所以github说23号这天的contribution是0，然而并不是，是3，只不过归在了真实姓名下面而不是xy7313下面。
解决方法：
1. 更改了vs code 用户setting git sync false改成了true，猜测可能是vscode问题，所以在vscode中随便改了一点用户设置作为尝试，失败
2. 想到更应该用终端解决，果然在终端中尝试commit的时候会提示committer的username，是真实姓名，根据终端提示设置修改配置，之后成功
>终端提示内容：
You can suppress this message by setting them explicitly:
    git config --global user.name "Your Name"
    git config --global user.email you@example.com
After doing this, you may fix the identity used for this commit with:
    git commit --amend --reset-author

