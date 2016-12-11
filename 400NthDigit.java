public class Solution {
  public int findNthDigit(int m) {
      
      /*
        For example given n is 1000, we first -9 and then -180. 
        The left is 811. The number(start) is 100+810/3=370. 
        The digit is the (810%3=0)th. Therefore, the digit is 3
        1~9 9个数 9*1=9个digit 
        10~99 90个数 90*2=180个digit 
        100~999 900个数 900*3=2700个digit 
        10^k ~ k个9连成的数 9*10^k个数 (90*10^k)*k个digit
      */
    long n=m; // convert int to long 
    long start=1,  len=1,  count=9;
 
    while(n>len*count){
        n=n-len*count;
        len++;
        count=count*10;
        start=start*10;
    }
 
    // identify the number，start是从10，100开始，n是-9，-99所以这里n-1
    start = start + (n-1)/len;
 
    // identify the digit
    return String.valueOf(start).charAt((int)((n-1)%len))-'0';
}
}