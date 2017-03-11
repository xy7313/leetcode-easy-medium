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