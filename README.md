# leetEM(start from easy)

from easy ac
 

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

####349.Intersection
嵌套for-loop找相同元素，把未出现过的相同元素存入ArrayList`re.contains();re.add();` ArrayList再转回int[]很麻烦`for(int i : IntegerList)`

####350. Intersection of Two Arrays II
现在用的是类似指针的思路，用while循环（避免for循环会出现的各种index问题）先排序（一般用指针都要排序），然后就一步一步往后挪，如果上下相等了，那就一起后移，不相等就小的后移，找大数来跟另外一个数组中的数匹配
更好的方法应该是hashmap吧


##237 linked list
##100 tree

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

####169. Majority Element
有种取巧的写法，可以说得通但是想不到，传统解法还是hashmap再mark一下这个`for(Integer k: m.keySet())`

####409. Longest Palindrome
看上去吓一跳稍微想一下还挺简单的，就是出现奇数次数的字母和出现偶数次数的字母的判断，出现偶数次的，全+，出现奇数次的，除了%2这部分全加，最后，中间可以有一个奇数次的，所以如果有出现奇数次的字母出现，最终结果+1。我自己写的解法估计不太好，尤其a那里，accepied的时候也是忍不住笑出声

####217. Contains Duplicate
用set是比较简单的方法，通过set.contains的方法来判断，for in 来遍历nums

####219. Contains Duplicate II
刷过一边的题果然ac率大大大幅提升，用hashmap，value存index以便判断distance<k

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

####36. Valid Sudoku
横竖的判断就是ij互换一下，需要哪个坐标变就把内层循环的int放过去，比如列的时候需要x坐标变，就把内层循环的j放过去
横竖都好判断，每个cube不好判断，坐标不好想，discuss里发现了一种很好的方法.类似用一次循环画一个cube或者二维数组的意思，i/3=x,i%3=y,x,y标明一个点
```
if(board[3*(i/3) + j/3][3*(i%3) + j%3]!='.' && !cube.add(board[3*(i/3) + j/3][3*(i%3)  + j%3])) return false;
```

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

####438. Find All Anagrams in a String
[Here is a 10-line template that can solve most 'substring' problems](https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems)
discuss里找了个高大上的方法，只能看懂，并不能想到，下次但愿能背会。。。还有自己想的一个方法，最直接但是很慢，反正ac了

####223. Rectangle Area
需要判断一下overlap的各个坐标
```
int left = Math.max(A,E), right = Math.max(Math.min(C,G), left);
int bottom = Math.max(B,F), top = Math.max(Math.min(D,H), bottom);
return (C-A)*(D-B)+(G-E)*(H-F)-(right-left)*(top-bottom);
```

####1. Two Sum
这类题可以算是刷题的动力吧，看过一次下一次居然真的会了，改了一个；错误提交居然过了，hashmap

####125. Valid Palindrome
1. 方法1：string的问题用string的方法来解决，replaceAll, 前面通过正则去掉所有非字母字符，之后equal方法，但注意要new一个stringbuffer对象
```
String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
return actual.equals(new StringBuffer(actual).reverse().toString());
```
2. 操作数组，类似双指针的方法，一种神奇的for方式， 从数组前后，i，j一起for：`for(int i = 0, j = s.length() - 1; i < j; ++i, --j) {`
还有char奇奇怪怪的方法，`!Character.isLetterOrDigit(s.charAt(i))` 返回true的时候说明不是letter，还有`character.toLowerCase`

####204 count primes
根据hint采用这个方法：Sieve of Eratosthenes\-ˌer-ə-ˈtäs-thə-ˌnēz\
遍历的时候是i*i<n, `j+=i`相当于遍历所有j的倍数

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

####28. Implement strStr()
看起来是个很简单的题，花了很长时间，心塞. 自己是用了类似双指针的方法，haystack中有needle首字符时，记index of hayxtack，然后比对needle，haystack不够长就直接返回-1，如果重合元素==needle.length,说明是包含的，返回此时记的index。另外一种大牛的方法看起来很简介，但是有一句j+i不太懂，考试略忙也没有仔细想，mark一下回头看

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
还是tag：binary search

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










####278. 


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


算是目标吧，easy-->mediam,ac高到低排，到这里应该200道
####92. Reverse Linked List II 
 