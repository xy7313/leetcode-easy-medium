##palindrome(4,还有一道链表题234)
####9. palindromeNumber
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