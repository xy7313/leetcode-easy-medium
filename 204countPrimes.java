public class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        //Sieve of Eratosthenes\-ˌer-ə-ˈtäs-thə-ˌnēz\
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            //只需要遍历一半n就可以了，3*2=6和2*3=6一样
            // j+=i相当于遍历所有j的倍数
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
         }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
    return count;
    }
}