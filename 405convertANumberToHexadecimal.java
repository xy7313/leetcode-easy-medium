public class Solution {
    public String toHex(int num) {
        if(num==0) return "0";
        String re = "";
        int bitNum = 8;
        Boolean negative = false;
        if(num<0){
            negative = true;
            //-1="ffffffff";
            num = 0-num-1;
        } 

        while(!negative&&num>0){
            int reminder = num%16;
            if(reminder<10){
                re+=reminder+"";
            }else{
                re += (char)(reminder+87)+"";
            }
            num/=16;
        }
        //负数这里不太会，看答案觉得凑合可以理解，其实应该用正统的位操作，但我看见位操作就懵逼
        /*
        1. 首先负数这里需要在去掉符号之后-1，-1-->0，-2-->1以此类推，因为-1=‘ffffffff’,然后-2=‘fffffffe'
        2. 之后是num % 16<6 用abcdef剩下用0-9.其他位（前面的位）保留f
        */
        while(negative&& bitNum>0){
            System.out.println("num:"+num+":re:"+re);
            int rest = num % 16;
            if (rest < 6) {
                re+= (char)('f' - rest)+"";
            }
            else {
                re+= 15 - rest+"";
            }
            num /= 16;
            bitNum--;
        }
        return (new StringBuffer(re).reverse().toString());
    }
}
