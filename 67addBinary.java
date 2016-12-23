public class Solution {
    public String addBinary(String a, String b) {
        if(a == "") return b;
        if(b == "") return a;
        String res = "";
        int c = 0;
        int i = a.length()-1;
        int j = b.length()-1;
        /*
        从末尾起遍历两个string，
            1. 如果当前计数>长度，该位为1
            2. sum= A当前位+B当前位+carry（可能=1 or 0）+之前求得的sum，拼接在后面
                (curIntA+curIntB+c)%2==curIntA ^ curIntB ^ c
            3. 计算carry位
        */
        while(i>=0 || j>=0) {
            int curIntA = i>-1?Character.getNumericValue(a.charAt(i)):0;
            int curIntB = j>-1?Character.getNumericValue(b.charAt(j)):0;
            res=(curIntA ^ curIntB ^ c)+res;
            // res=(curIntA+curIntB+c)%2+res;
            //按位异或1^1=0;1^0=1
            c = (curIntA+curIntB+c) >= 2 ? 1 : 0;
            i--;
            j--;
        }  
        return c != 0 ? c+res : res;
    }
}

//第二次做这道题发现了一种新方法，但是速度没快多少，所以没有深究
public class Solution {
        public String addBinary(String a, String b) {
        String result = "";
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >=0 || j >=0) {
            int tmp = (i >=0?(a.charAt(i)- '0'):0 ) + (j >=0?(b.charAt(j) - '0'):0) + carry;
            carry = tmp / 2;
            int digit = tmp % 2;
            result = digit + result;
            i --;
            j --;
        }
        if (carry > 0) {
            result = carry + result;
        }
        return result;
    }
}