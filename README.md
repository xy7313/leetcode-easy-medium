# leetEM(start from easy)


 

####412. fizzbuzz
最简单的数学题，注意if判断顺序或者就都单列出来总不会错

####344.reverse string
转成数组`s.toCharArray()`转回string`String.valueOf(charArrayName)`

####292. nim game
只要石头堆对4取余有剩余，剩余的就是我的，就能赢

####136. single number
hashmap<Integer,Boolean>

##新题455. assign cookies

####371. sum Two Integer
sum 得到按位异或的结果，在没有进位的情况下就是我们想要的和；如果有进位，进入while循环处理进位
[code](https://github.com/xy7313/leetEM/blob/master/371sumTwoInteger.java)

##104 tree

####258. add Digits
(num-1)%9+1

#### 389-383-387-242
都是很类似题
第一种解法：哈希表（key：character，value：次数或者index）
389findDifference：第一次遍历向hashmap中存string1，key-character，value-出现次数，第二次遍历key出现1次value-1，value<0说明是different的char
387UniqueCharacter：第一次遍历向hashmap中存string1，key-character，value-出现次数，第二次通过key-char找最小index`s.indexOf()`
383ransomNote：和finddifference方法（hashmap）一样，只不过string的位置换了一下，谁包含谁换了

第二种解法：char转成int，来做index
389findDifference：要求的结果变量re先依次加上第一个string中每个char转成int的值，然后再依次减去第二个string中的每个char，得到正确re
387UniqueCharacter：char转成int当做array的索引，没出现依次，该索引位置对应值++，取所有索引位置对应值=1中最小索引，对应char
242validAnagram：考虑到两个string可能长度不一样，用普通for容易出现outofIndex，for in形式比较好`for(char j: t.toCharArray())`

##226 tree

####283. move Zeroes
设置计数器，从计数器=0开始放非零数字，剩余（length-计数器）置0

##404 tree
##453

####349.intersaction
嵌套for-loop找相同元素，把未出现过的相同元素存入ArrayList`re.contains();re.add();` ArrayList再转回int[]很麻烦`for(int i : IntegerList)`
##237 linked list
##100 tree

####171. excelColumnNumber
`re+=(sc[i]-'A'+1)*Math.pow(26,sc.length-i-1);`


####169. Majority Element
有种取巧的写法，可以说得通但是想不到，传统解法还是hashmap再mark一下这个`for(Integer k: m.keySet())`
####409. Longest Palindrome
看上去吓一跳稍微想一下还挺简单的，就是出现奇数次数的字母和出现偶数次数的字母的判断，出现偶数次的，全+，出现奇数次的，除了%2这部分全加，最后，中间可以有一个奇数次的，所以如果有出现奇数次的字母出现，最终结果+1。我自己写的解法估计不太好，尤其a那里，accepied的时候也是忍不住笑出声
####217. Contains Duplicate
用set是比较简单的方法，通过set.contains的方法来判断，for in 来遍历nums
####350. Intersection of Two Arrays II

