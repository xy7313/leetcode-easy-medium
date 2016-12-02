public class Solution {
    public int romanToInt(String s) {
     if (s == null || s.length()==0) {
 return 0;
	    }
	    Map<Character, Integer> m = new HashMap<Character, Integer>();
	    m.put('I', 1);
	    m.put('V', 5);
	    m.put('X', 10);
	    m.put('L', 50);
	    m.put('C', 100);
	    m.put('D', 500);
	    m.put('M', 1000);

	    int length = s.length();
	    int result = m.get(s.charAt(length - 1));
	    for (int i = length - 2; i >= 0; i--) {
	        if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) {
	            result += m.get(s.charAt(i));
	        } else {
	            result -= m.get(s.charAt(i));
	        }
	    }
	    return result;   
    }
}
/*
 这个题的难点好像在在于for里判断的时候要i+1个元素个i个元素比较，所以for循环很难遍历全整个数组，所以会一开始的时候就把遍历不到，
 而且刚好也需要加上的最后一位直接赋值给result
 跟之前写这题用了一样的思路，不一样的方法，之前看答案的那种更简单一点
 最近总是想到用true fale这种方式判断，并不简单
*/
public class Solution {
    public int romanToInt(String s) {
        if(s.length()==0||s==null){
            return 0;
        }
        Map<Character, Integer> m = new HashMap<Character, Integer>();
	    m.put('I', 1);
	    m.put('V', 5);
	    m.put('X', 10);
	    m.put('L', 50);
	    m.put('C', 100);
	    m.put('D', 500);
	    m.put('M', 1000);
	    int re = m.get(s.charAt(s.length()-1));
	   //int re = 0;
	   boolean minus = false;
	    for(int i = s.length()-2;i>=0;i--){
	        if(m.get(s.charAt(i+1))>m.get(s.charAt(i))){
	            minus = true;
	        }
	        if(minus==true){
	            re-=m.get(s.charAt(i));
	        }else{
	           	re += m.get(s.charAt(i));
	        }
	        minus = false;
	    }
	    return re;
    }
}
