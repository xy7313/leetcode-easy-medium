public class Solution {
    public int addDigits(int num) {
        // while (num>=10){
        //     char[] newc = String.valueOf(num).toCharArray(); 
        //      num = 0;
        //     for (int i= 0; i <newc.length;i++){
        //         System.out.println(newc[i]);
        //         num +=Integer.parseInt(String.valueOf(newc[i]));
        //         System.out.println("for:"+num);
        //     }
        //     System.out.println("num:"+num);
        // }
        while(num>=10){
            int re = num%10;
            num= num/10+re;
        }
        return num;
    }
}

/*
这次先用了类型转换的方法
如果没记错的话之前是用的%10的方法

然后又用了%10的方法，发现好简单啊，我不需要把数字完全拆开啊
嗯，老衲涨姿势了

*/