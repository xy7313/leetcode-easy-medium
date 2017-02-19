##sort相关(4 problems)
215. Kth Largest Element in an Array
324. Wiggle Sort II
179. Largest Number
451. Sort Characters By Frequency
88. Merge Sorted Array


####215. Kth Largest Element in an Array
用quicksort的思想，实现O(n)的算法。快排的思想是分治，divide and conqure。这里因为只需要找到某元素，所以分治之后的部分只有包含要查找元素的那部分需要处理，剩下的不需要处理，所以原本快排是nlogn的算法，这里不完全排序，可以达到linear。

还有之所以看到了这题是因为下面的题用到了这个题目。
```
public int findKthLargest(int[] nums, int k) {
    //k<1||k>nums.length 也是边界值情况
    if(nums==null||nums.length==0) return Integer.MAX_VALUE;
    return findKthLargest(nums,0,nums.length-1,nums.length-k);
}
public int findKthLargest(int[] nums, int start, int end, int k){
    if(start>end) return Integer.MAX_VALUE;
    int pivot = nums[end];
    int left = start;
    for(int i = start; i<end; i++){
        //第一次默写这里少了if，对，默写，这个算法并不能完全理解自己再写出来，只能多写几次，装作是想出来的然后写出来
        if (nums[i] <= pivot){
            swap(nums,left,i);
            left++;
        }
    }
    swap(nums,left,end);
    if(left==k) return nums[left];
    if(left<k) return findKthLargest(nums,left+1,end,k);
    else return findKthLargest(nums,start,left-1,k);
}
public void swap(int[] n, int i, int j){
    int tmp = n[i];
    n[i] = n[j];
    n[j] = tmp;
}
```
这个题还有个tag是queue，也可以用priority queue解决，复杂度是(nlgk),因为queue.poll()都会先推出小的，所以，当size>k,会把比kth（也就是当前 size th）largest小的数推出。这样可以确保queue中只存比kth largest 大的数，也就是存k个数，所以可以打印试试，k==queue.size()，所以最后返回queue中最小的数就是kth largest。想到这个方法的人真厉害。这个方法也好棒
```
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> largeK = new PriorityQueue<Integer>(k + 1);
    for(int el : nums) {
        largeK.add(el);
        if (largeK.size() > k) {
            largeK.poll();
        }
    }

    return largeK.poll();
}
```

####324. Wiggle Sort II
google面试题，到处都有跟这个题有关的讲解和介绍，我最初看的是九章的，比较好理解，后来discuss区也看到了高赞的答案，两个都写了，思路如下：

1. 利用快速排序的思想找中位数的期望时间复杂度是O(N).为了防止相等的数放在一起，需要注意放置的顺序。
2. 九章笔者采用的方法是依nums长度分两种情况：
    若长度为奇数，把比中位数小的依次放在0,2,4,...位置，比中位数大的依次放在length-2,length-4,... 位置；
    若长度为偶数，把比中位数小的依次放在length-2,length-4,...位置，比中位数大的依次放在1,3,5,... 位置。
    其余位置填充中位数。这样可以保证中位数一定与较小与较大的数相邻（题目保证一定有解）

另外，两个方法都用了上题的findKthLargest

```
public void wiggleSort(int[] nums) {
    int len = nums.length;
    int median = findKthLargest(nums,0, len-1, len/2);
    int n = nums.length;
    int left = 0, i = 0, right = n - 1;
    while (i <= right) {
        if (nums[newIndex(i,n)] > median) {
            swap(nums, newIndex(left++,n), newIndex(i++,n));
        }
        else if (nums[newIndex(i,n)] < median) {
            swap(nums, newIndex(right--,n), newIndex(i,n));
        }
        else {
            i++;
        }
    }
}
//这个mapindex到135024这种方法跟上面说的思路其实是一样的
private int newIndex(int index, int n) {
    return (1 + 2*index) % (n | 1);
}
//method2
public void wiggleSort(int[] nums) {
    if (nums == null || nums.length == 0)   return;
    int len = nums.length;
    int median = findKthLargest(nums,0, len-1, len/2);
    int[] re = new int[len];
    //necessary？
    for(int i = 0;i<len;i++){
        re[i] = median;
    }
    int s,l;
    if(len%2==0){
        s=len-2;
        l=1;
        for(int i = 0; i<len; i++){
            if(nums[i]<median){
                re[s] = nums[i];
                s-=2;
            }else if(nums[i]>median){
                re[l] = nums[i];
                l+=2;
            }
        }
    }else{
        s=0;
        l=len-2;
        for(int i = 0; i<len; i++){
            if(nums[i]<median){
                re[s] = nums[i];
                s+=2;
            }else if(nums[i]>median){
                re[l] = nums[i];
                l-=2;
            }
        }
    }
    for(int i = 0; i<nums.length; i++){
        nums[i] = re[i];
    }
    
}
```

####179. Largest Number
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

代码看起来很长，第一个for循环其实不用看，int数组存成string数组，重点在sort，override compareTo方法这里，直接比较拼接后的结果，注意注释掉的是一套，现在的版本是根据拼接结果从大到小排，比如[1,2]-->[2,1]; [10,2]-->[2,10]之后判断String[]首位是不是0，如果是说明全是0，返回0即可，如果不是开始拼接，这里一直向后拼接就可以了，如果排序时候用的s1.compareTo(), 那么这里的拼接需要向前拼接，用注释里的代码。`new Comparator<String>() {...}`注意这个的写法
```
public String largestNumber(int[] nums) {
    if (nums == null || nums.length == 0) return "";
    String[] strs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
        strs[i] = nums[i]+"";
    }
    //s2.compareTo(s1) sort array 9-0, s1.compareTo(s2) sort array 0-9
    //String.compareTo(String s) return <0 this<argument:s,
    Arrays.sort(strs, new Comparator<String>() {
        @Override
        public int compare(String i, String j) {
            String s1 = i+j;
            String s2 = j+i;
            // return s1.compareTo(s2);
            return s2.compareTo(s1);
        }
    });
    //if (strs[str.length-1].charAt(0) == '0') return "0";
    if (strs[0].charAt(0) == '0') return "0";
    String res = new String();
    for (int i = 0; i < strs.length; i++) {
        //res=strs[i]+res;
        res += strs[i];
    }
    return res;
}
```

####451. Sort Characters By Frequency
自从开是刷medium的题之后，每天都是炼狱模式，各种没见过的题型，要么就是想不出解法，要么就是想出的不符合要求，每次看答案都要看好久（生无可恋脸，这个题也是，要求O(n)，所以hashmap+排序value的方法是不满足要求的

思路：

1. 还是hashmap，但注意除了正常的创建map之外还需要保留一个max，即最高频字符出现的次数，后面要用
2. 构造一个array，这个array的index是array的element出现的此数，这里的array element是一个list，用来存放 出现次数相同的多个字符
3. 拼组string。通过StringBuilder，index是几，index位置的list里的char就append几次，直到遍历完整个数组。

还有一个细节需要注意，map存放的方式和后面 forloop的临界值要对应好，比如注释中的情况。

突然想到这里可以不用map 就 new int[128]出现的字符对应的下标位置存出现次数（不行好像还要sort）。。。

```
public String frequencySort(String s) {
    if(s==null) return null;
    Map<Character,Integer> m = new HashMap<>();
    int max = 0;
    for(char c: s.toCharArray()){
        if(m.containsKey(c)){
            m.put(c,m.get(c)+1);
        }
        else m.put(c,0);
        // if(!m.containsKey(c)){
        //     m.put(c,0);
        // } 
        // m.put(c,m.get(c)+1);
        //如果这里的map构造形式写成上面两行注释中的代码，那下面 最外层for和最内层for也要从>=,<=变成>,<
        //for(int i = array.length-1; i>0; i--){
        //for(int j = 0; j<i; j++){
        max = Math.max(max,m.get(c));
    }
    List<Character>[] array = buildArray(m,max);
    StringBuilder sb = new StringBuilder();
    for(int i = array.length-1; i>=0; i--){
        List<Character> list = array[i];
        if(list!=null){
            for(Character c: list){
                for(int j = 0; j<=i; j++){
                    sb.append(c);
                }
            }
        }
    }
    return sb.toString();
}
private List<Character>[] buildArray(Map<Character,Integer> map, int max){
    List<Character>[] arr = new List[max+1];
    for(Character c : map.keySet()){
        int count = map.get(c);
        if(arr[count]==null) arr[count] = new ArrayList<Character>();
        arr[count].add(c);
    }
    return arr;
}
```

####88. Merge Sorted Array
看起来很简单的题，discuss里有很多3行代码1行代码什么的，我还是找了个最容易看懂的, 题目中有说明nums1的长度是存的下m+n的，既然是向nums1中插入，那为了避免懂前面已有元素，我们从后面操作，两个数组都是sort好的，所以秩序比较两个数组最右的元素，大的即为全场最大，可以直接放，后面也都是一样的思路，nums[n--]这种形式还玩不转，先不用
```
while(n>0){
    if(m>0&&nums1[m-1]>nums2[n-1]){
        nums1[m-1+n]=nums1[m-1];
        m--;
    }else{
        nums1[m-1+n]=nums2[n-1];
        n--; 
    }
}
```