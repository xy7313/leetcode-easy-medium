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
    //进阶版方法，来源未知，不好想，注释里的方法好想一点
    public int addDigits(int num) {
        // if(num==0) return num;
        // if(num%9==0) return 9;
        // return (num)%9;
        return (num-1)%9+1;
    }
}

/*
这次先用了类型转换的方法
如果没记错的话之前是用的%10的方法

然后又用了%10的方法，发现好简单啊，我不需要把数字完全拆开啊
嗯，老衲涨姿势了

进阶版：不用loop/recursive
*/