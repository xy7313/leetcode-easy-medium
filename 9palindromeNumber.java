public class Solution {
    public boolean isPalindrome(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int rex = 0;
        while(x>rex){
            rex = rex*10+x%10;
            x/=10;
        }
        //位数是偶数||位数是奇数
        return (rex==x||x==rex/10);
    }
}