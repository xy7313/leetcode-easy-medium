##ALL ** numbers
single number * 3
happy number
missing number
ugly number * 3
Valid Number
additive number(dfs || iteration)



palindrom number 在 palindrome，他们的处理方法比较相像
Largest number 在 sort

####136. single number
1. hashmap<Integer,Boolean>
2. array 排序的方法：（排序时间复杂度nlogn）Arrays.sort(nums); 循环的时候，判断相邻两数差==0，i+=2
3. notice:如果有多个singlenumber，题目中没有描述清楚，应该问清楚

####137. Single Number II
bit 操作的基本知识
```
a = 0011 1100
b = 0000 1101
a&b = 0000 1100 -- & (bitwise and)
a|b = 0011 1101 -- | (bitwise or)
a^b = 0011 0001 -- ^ (bitwise XOR)
~a  = 1100 0011 -- ~ (bitwise compliment)
C |= 2 is same as C = C | 2
| (bitwise or)
>>=	Right shift AND assignment operator.	C >>= 2 is same as C = C >> 2
&=	Bitwise AND assignment operator.	C &= 2 is same as C = C & 2
^=	bitwise exclusive OR and assignment operator.	C ^= 2 is same as C = C ^ 2
|=	bitwise inclusive OR and assignment operator.	C |= 2 is same as C = C | 2
```
都是twice，一个once的例子，就是single number原版
```
//k = 2, p = 1. k is 2, then m = 1, we need only one 32-bit integer(x1) as the counter. And 2^m = k so we do not even need a mask!
public int singleNumber(int[] A) {
     int x1 = 0;      
     for (int i : A) {
        x1 ^= i;
     }
     return x1;
}
```
都是appear three times expect for one only appears once
```
//k = 3, p = 1. k is 3, then m = 2, we need two 32-bit integers(x2, x1) as the counter. And 2^m > k so we do need a mask. Write k in its binary form: k = '11', then k1 = 1, k2 = 1, so we have mask = ~(x1 & x2).
public int singleNumber(int[] A) {
     int x1 = 0;   
     int x2 = 0; 
     int mask = 0;

     for (int i : A) {
        x2 ^= x1 & i;
        x1 ^= i;
        mask = ~(x1 & x2);
        x2 &= mask;
        x1 &= mask;
     }
     return x1;  // p = 1, in binary form p = '01', then p1 = 1, so we should return x1; 
                 // if p = 2, in binary form p = '10', then p2 = 1, so we should return x2.
}
```
这个题discuss区也有个不错的解法，也是bit manipulattion

1. First time number appear -> save it in "ones"
2. Second time -> clear "ones" but save it in "twos" for later check
3. Third time -> try to save in "ones" but value saved in "twos" clear it. （如果clear了，说明出现了三次，一切回到原始状态，如果只出现一次，就没有后面的过程，就回不到原始状态，那初始状态就一直在ones里）
```
public int singleNumber(int[] nums) {
    int ones = 0, twos = 0;
    for(int i = 0; i < nums.length; i++){
        ones = (ones ^ nums[i]) & ~twos;
        twos = (twos ^ nums[i]) & ~ones;
        System.out.println(nums[i]+":"+ones+":"+twos);
    }
    return ones;
}
```
另外这个方法也可以试着理解下：https://discuss.leetcode.com/topic/43166/java-o-n-easy-to-understand-solution-easily-extended-to-any-times-of-occurance/2
This has complexity of O(32n), which is essentially O(n) and very easy to think and implement. Plus, you get a general solution for any times of occurrence. Say all the numbers have 5 times, just do sum %= 5.
```
public int singleNumber(int[] nums) {
    int ans = 0;
    for(int i = 0; i < 32; i++) {
        int sum = 0;
        //count how many 1s are there in each bit, and sum %= 3 will clear it once it reaches 3
        for(int j = 0; j < nums.length; j++) {
            if(((nums[j] >> i) & 1) == 1) {
                sum++;
                sum %= 3;
            }
        }
        //After running for all the numbers for each bit, if we have a 1, then that 1 belongs to the single number, we can simply move it back to its spot
        if(sum != 0) {
            ans |= sum << i;
        }
    }
    return ans;
}
```
最后是一个墙裂推荐的方法，我这种bit白痴几乎就要看懂了：https://discuss.leetcode.com/topic/2926/accepted-code-with-proper-explaination-does-anyone-have-a-better-idea/2 。 代码是cpp，但不影响阅读
```
class Solution {
    public:
    // Let us take the example of {3, 3, 2, 3} to understand this
        int singleNumber(int A[], int n) {
            int ones=0, twos =0;
            int common_bit_mask;
            for(int i=0; i<n;i++)
            {
                 /* The expression "one & arr[i]" gives the bits that are
               there in both 'ones' and new element from arr[].  We
               add these bits to 'twos' using bitwise OR
     
               Value of 'twos' will be set as 0, 3, 3 and 1 after 1st,
               2nd, 3rd and 4th iterations respectively */
               
                twos= twos|(ones&A[i]);
                /* XOR the new bits with previous 'ones' to get all bits
               appearing odd number of times
     
               Value of 'ones' will be set as 3, 0, 2 and 3 after 1st,
               2nd, 3rd and 4th iterations respectively */
                ones=ones^A[i];
                 /* The common bits are those bits which appear third time
               So these bits should not be there in both 'ones' and 'twos'.
               common_bit_mask contains all these bits as 0, so that the bits can 
               be removed from 'ones' and 'twos'   
     
               Value of 'common_bit_mask' will be set as 00, 00, 01 and 10
               after 1st, 2nd, 3rd and 4th iterations respectively */
                common_bit_mask= ~(ones&twos);
                /* Remove common bits (the bits that appear third time) from 'ones'
                 
               Value of 'ones' will be set as 3, 0, 0 and 2 after 1st,
               2nd, 3rd and 4th iterations respectively */
                ones &=common_bit_mask;
                /* Remove common bits (the bits that appear third time) from 'twos'
     
               Value of 'twos' will be set as 0, 3, 1 and 0 after 1st,
               2nd, 3rd and 4th itearations respectively */
                twos &=common_bit_mask;
            }
            return ones;
        }
    };
```

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

####268. Missing Number
1. 方法1，排序，然后用数组中的元素跟索引比较，索引有，元素没有的（其实就是i!=nums[i]）直接返回i，复杂度略高
2. 方法2，求和，已知我们有的是[0,n]之间distinct的数字，可以直接把[0,n]这些全部相加再-数组中所有元素和
    1. 即1+2+。。。+（index+1）（其实也就是length）与nums[0]+nums[1]+...的差值. (we are missing only one number in [0,n], we just need to look at the difference between the sum([0,n]) = n * (n+1) / 2 and the sum of nums in our array.)

####263. Ugly Number
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5。1 is ugly number
所以就拿一个数，只要能被2整除就一直除以2，然后，只要能被3整除就一直除以3，然后，只要能被5整除就一直除以5，如果剩下的不是1，那就不是ugly number

####264. Ugly Number II
找nth ungy number，思路是，从1，2，3，4，5.。。这些ugly numbers，挨个分别*2，*3，*5，得到的都还是ugly number，所以哪个小那个排在前面，往list，array，queue里放，到第n个，返回。compute all the ugly numbers in sequence and count to the given number of k ugly numbers

discuss有这三种数据结构的实现。

1. array: https://discuss.leetcode.com/topic/21791/o-n-java-solution 这个discuss里的帖子说的很形象，用了类似归并的算法，每次取最小，取了最小的那个乘数（2，3或5）向后推移一位
```
 public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        int multiply2 = 2, multiply3 = 3, multiply5 = 5;
        int index2 = 0, index3 = 0, index5 = 0;
        ugly[0] = 1;
        for(int i = 1; i<n;i++){
            int min = Math.min(Math.min(multiply2,multiply3),multiply5);
            ugly[i] = min;
            if(min==multiply2){
                index2++;
                multiply2=2*ugly[index2];
            }
            if(min==multiply3){
                index3++;
                multiply3=3*ugly[index3];
            }
            if(min==multiply5){
                index5++;
                multiply5=5*ugly[index5];
            }
        }
        return ugly[n-1];
    }
```
2. ArrayList：https://discuss.leetcode.com/topic/22982/java-easy-understand-o-n-solution
3. PriorityQueue：https://discuss.leetcode.com/topic/25088/java-solution-using-priorityqueue 这个帖子用的queue，这里1l意思是存入long类型的1，int会有越界，用PriorityQueue<Long> 插入n个数，平均时间是logn，应该是比array快
```
public int nthUglyNumber(int n) {
    if(n==1) return 1;
    PriorityQueue<Long> q = new PriorityQueue();
    q.add(1l);
    
    for(long i=1; i<n; i++) {
        long tmp = q.poll();
        while(!q.isEmpty() && q.peek()==tmp) tmp = q.poll();
        
        q.add(tmp*2);
        q.add(tmp*3);
        q.add(tmp*5);
    }
    return q.poll().intValue();
}
```
####313. Super Ugly Number
用了很类似上面的方法，稍微有一些变化，见代码注释
```
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] index = new int[primes.length];
   
        ugly[0] = 1;
        for(int i = 1; i<n;i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j<primes.length; j++){
                //primes[j]变化之前就得存入ugly,用ugly number2的方法，primes里元素会改变
                min = Math.min(ugly[index[j]]*primes[j],min);
            }
            ugly[i] = min;
            for(int j = 0; j<primes.length; j++){
                if(ugly[i]%primes[j]==0){
                    index[j]++;
                    // primes[j]=(primes[j]/index[j])*ugly[index[j]];
                }
            }
        }
        return ugly[n-1];
    
    }
}
```

####65. Valid Number
是个比较常规的题，if-else和switch都可以。需要4个boolean来统计

1. number出现了没
2. "."
3. "+","-"
4. "e"
5. e后面是否有数字，比如1e就是invalid。 numberAfterE 有数字的时候就true，有e出现就false
```
public boolean isNumber(String s) {
    if(s==null||s.length()==0) return false;
    s = s.trim();
    char[] cs =  s.toCharArray();
    
    boolean point = false;
    boolean e = false;
    boolean number = false;
    boolean numberAfterE = true;
    
    for(int i = 0; i<cs.length; i++){
        if('0' <= cs[i] && cs[i] <= '9') {
            number = true;
            numberAfterE = true;
        } else if(cs[i] == '.') {
            if(e || point) {
                return false;
            }
            point = true;
        } else if(cs[i] == 'e') {
            if(e || !number) {
                return false;
            }
            numberAfterE = false;
            e = true;
        } else if(cs[i] == '-' || cs[i] == '+') {
            if(i != 0 && cs[i-1] != 'e') {
                return false;
            }
        } else {
            return false;
        }
    }
    return number && numberAfterE;
}
```

####306. Additive Number
1. Generate the first and second of the sequence, check if the rest of the string match the sum recursively. i and j are length of the first and second number. i should in the range of [0, n/2]. The length of their sum should >= max(i,j)
2. startwith（ string s, int offset）