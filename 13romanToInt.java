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
 1. 这个题的难点好像在在于for里判断的时候要i+1个元素个i个元素比较，所以for循环很难遍历全整个数组，所以会一开始的时候就把遍历不到，
 而且刚好也需要加上的最后一位直接赋值给result
 2. 最近总是想到用true fale这种方式判断，并不简单，而且切记boolean这个值是需要不停改变的，
 for循环里最后一句重新minus=false忘记加找了半天问题
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

//！！
//由于leetcode太蠢会因为system.out.println而提交超时的缘故，这个问题我之前的代码其实也是work的，以下
//也是醉了，当时还百思不得其家，本来就是一毛一样的方法嘛，怎么就一直超时，，，
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
	   boolean minus = false;
	    for(int i = s.length()-2;i>=0;i--){
	        if(m.get(s.charAt(i+1))>m.get(s.charAt(i))){
	            minus = true;
	        }else{
	            minus = false;
	        }
	        if(minus==true){
	            re-=m.get(s.charAt(i));
	        }else{
	           	re += m.get(s.charAt(i));
	        }
	    }
	    return re;
    }
}