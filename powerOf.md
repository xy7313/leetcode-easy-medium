#### 231. Power of Two
power of 2 only contains one 1, (power of 2)-1 is only consist of 1(ex:8=1000,7=111),one line code: `return n>0 && (n&(n-1))==0;`

#### 326. Power of Three
 任何一个3的x次方一定能被int型里最大的3的x次方整除，1162261467 is 3^19,  3^20 is bigger than int,`return ( n>0 && 1162261467%n==0);`  

#### 342. Power of Four
1. 方法1: 4^0 = 1, 4^1 = 100, 4^2 = 10000.所以4的二进制只有一个1且在odd位; 另外Integer.toString(int i, int radix)这个方法是把前面的数按后面数的进制转换，这里相当于4进制，既3=3，4=10，5=11，15=33，16=100`return Integer.toString(num, 4).matches("10*");`
2. 方法2：`return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;//make sure that 1 bit always appears at the odd position `
3. 方法3：这个比较数学讨论区大神有解释，一个数既是2的幂，又等于3的倍数+1，`x^n-1=(x-1)(x^(n-1)+...+x^1+1)右边乘法展开是可以得到左边的，所以 4^n=3*(...)+1`就是power of 4`return (num&(num-1))==0 && num>0 && (num-1)%3==0;`
