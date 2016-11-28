public class Solution {
    public int reverse(int x) {
        /*
        1. int不行，long才可以,int放不下
        2. 不需要取绝对值单独判断符号，%10的时候得到的结果是带-的
        3. y是long，但需要的结果是int，所以需要判断y能不能转int，就是在最大值和最小值之间
        */
        long y = 0;
        while(x!=0){
            y= y * 10 + x%10;
            System.out.println(x%10);
            if(y>Integer.MAX_VALUE||y < Integer.MIN_VALUE){
                return 0;
            }
            x=x/10;
        }
        return (int)y;
    }
}