##palindrome(除了这里的还有一道链表题234)
9. palindrome Number
409. Longest Palindrome
125. Valid Palindrome
336. Palindrome Pairs


####注意
125, 336 是两道面经题


####一段常用代码： isPalindrome
```
private boolean isPalindrome(String str) {
    int left = 0;
    int right = str.length() - 1;
    while (left <= right) {
        if (str.charAt(left) !=  str.charAt(right)){
            return false;
        } 
        left++;
        right--;
    }
    return true;
}
```

####9. palindrome Number
不能转换string，所以首先想到 不停的取原数字%10得到新数字首位，然后对比新数字和原数字

代码也是这个思路，不过有很多细节需要注意,比如while的循环条件，不需要x一直到0，rex的组成方式，rex*10那里一开始没想到的，后面返回的时候也需要注意，看起来很简单的题，写起来全是坑，就这样
```
 while(x>rex){
    rex = rex*10+x%10;
    x/=10;
}
//位数是偶数||位数是奇数
return (rex==x||x==rex/10);
```

####409. Longest Palindrome
看上去吓一跳稍微想一下还挺简单的，就是出现奇数次数的字母和出现偶数次数的字母的判断，出现偶数次的，全+，出现奇数次的，除了%2这部分全加，最后，中间可以有一个奇数次的，所以如果有出现奇数次的字母出现，最终结果+1。我自己写的解法估计不太好，尤其a那里，accepied的时候也是忍不住笑出声

后来，看到之前提交的代码，再看看discuss区代码，之前ac的那么烂，原来是自己写的。。。贴个discuss中更好看一点的，也很好理解。用了 hs.isEmpty() 类似我写的那个a的作用
```
public int longestPalindrome(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
    }
```

####125. Valid Palindrome
1. 方法1：string的问题用string的方法来解决，replaceAll, 前面通过正则去掉所有非字母字符，之后equal方法，但注意要new一个stringbuffer对象
```
String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
return actual.equals(new StringBuffer(actual).reverse().toString());
```
2. 操作数组，类似双指针的方法，一种神奇的for方式， 从数组前后，i，j一起for：`for(int i = 0, j = s.length() - 1; i < j; ++i, --j) {`
还有char奇奇怪怪的方法，`!Character.isLetterOrDigit(s.charAt(i))` 返回true的时候说明不是letter，还有`character.toLowerCase`
```
public boolean isPalindrome(String s) {
    for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
        if(!Character.isLetterOrDigit(s.charAt(i))) {
            j++;
            continue;
        }
        if(!Character.isLetterOrDigit(s.charAt(j))) {
            i--;
            continue;
        }            
        if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
            return false;
        } 
    }
    return true;
}
```

####336. Palindrome Pairs
1. put all strings in a map
2. for each string, divide it into two parts, list all possible results and compare if perfix of it is palindrome and the suffix combined with a string in map can be a palindrome.
3. special cases:
    1. j<=words[i]  Consider the test case of ["a", ""];
    2. str2.length()!=0     (consider test case ["abcd", "dcba"])
```
/*

/*
The <= in for (int j=0; j<=words[i].length(); j++) is aimed to handle empty string in the input. Consider the test case of ["a", ""];

Since we now use <= in for (int j=0; j<=words[i].length(); j++) instead of <. There may be duplicates in the output (consider test case ["abcd", "dcba"]). Therefore I put a str2.length()!=0 to avoid duplicates.

Another way to avoid duplicates is to use Set<List<Integer>> result = new HashSet<>(); and return new ArrayList<>(result);
*/
public class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>(); 
        if (words == null || words.length < 2) return result;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i<words.length; i++) map.put(words[i], i);
        for (int i=0; i<words.length; i++) {
            // System.out.println(words[i]);
            for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    //string 没有 reverse 方法，要reverse，记住下面这行代码
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        result.add(list);
                    }
                }
                //str2.length()!=0 because we set j<=words[i].length, we count the empty string as first str1, and another empty string as last str2, we may have duplicates list.
                if (isPalindrome(str2) && str2.length()!=0) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i) { 
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) !=  str.charAt(right)){
                return false;
            } 
            left++;
            right--;
        }
        return true;
    }
}
```