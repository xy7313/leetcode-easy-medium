public class Solution {
    public String convertToTitle(int n) {
        String re ="";
        while(n>0){
           if(n%26==0){
               re=Character.toString ('Z')+re;
               n=n/26-1;
           }else{
                re=Character.toString ((char) (n%26+64))+re;
                n/=26;
           }
        }
        return re;
    }
}
//思路算法都挺简单的，第一次提交遇到了一个边界值的错误，就是26的倍数，我处理的方法明显面向结果编程了，根据错误答案加了if判断，问题解决了，但方法很不好，思路也不好。

//更好的思路，n%26得到0-25之间的26个数，刚好对应A-Z,但题目中given int是从1-A开始，所以只需要把given int n=n-1 后面就可以按照0-25 分别+65去转换对应的asc码就可以了。
public class Solution {
    public String convertToTitle(int n) {
        String re ="";
        while(n>0){
            n=n-1;
            re = Character.toString((char)(n%26+65))+re;
            n/=26;
        }
        return re;
    }
}


