## PriorityQueue/Heap相关

linked list 里 23. Merge k Sorted Lists

sort 里 215. Kth Largest Element in an Array

和下面的：

347. Top K Frequent Elements
378. Kth Smallest Element in a Sorted Matrix

###关于queue！
1. The java.util.Queue is a subtype of java.util.Collection interface. It is an ordered list of objects with its use limited to inserting elements at the end of list and deleting elements from the start of list i.e. it follows FIFO principle.
2. 可以用priority queue 和 linked list实现
1. 用priority queue实现时, add(E) might throw an exception while offer(E) will simply return false.(offer，add区别：一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。)
2. poll will return null if there are no elements. remove() will throw a NoSuchElementException
3. peek，element区别：element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null
4. *异常类型（不太重要）：add：如果队列已满，则抛出一个 IlLegaISlabEepeplian异常；remove：如果队列为空，则抛出一个NoSuchElementException异常；element：如果队列为空，则抛出一个NoSuchElementException异常

#### 347. Top K Frequent Elements
还有一个重写compare的题，当然这个题用hashmap也可以，复杂度不知道行不行,在discuss区看到的maxheap实现的方法，因为要topk，所以用maxheap重写compare改变排序规则就可以实现，大牛 代码居然可以缩减到7行，有点厉害的，注意Lambda箭头函数，PriorityQueue 的 addAll(), Map 的 map.getOrDefault(n,0),map.entrySet(),map.getValue(),map.getKey()这些方法的灵活使用，基本可以记得，要priority queue，compare(a,b){return a-b;}
```
Map<Integer, Integer> map = new HashMap<>();
for(int n: nums) map.put(n,map.getOrDefault(n,0)+1);
PriorityQueue<Map.Entry<Integer,Integer>> maxHeap = new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
maxHeap.addAll(map.entrySet());
List<Integer> re = new ArrayList<Integer>();
while(re.size()<k) re.add(maxHeap.poll().getKey());
return re;
```

#### 378. Kth Smallest Element in a Sorted Matrix
第一眼看这个题的思路（注意错误思路，没做的话先看下面正确思路再看这个，以防记住了错误思路）：双层for循环遍历matrix，挨个存入priority queue，然后挨个poll找到kth smallest，错误原因，matrix中每个array是sorted，不代表整体也是sorted，比如matrix = [ [ 1,  5,  9],[10, 11, 15],[12, 13, 15] ],k = 8, return 13是正解，如果按照错误思路，都放入priority queue，得到queue：[1,5,9,10,11,12,13,15,15], k=8， return15 ,which is wrong answer

正确答案：按目前的水平是并不能想到的，看了之后表示是可以理解的，画queue，画一遍就会发现，是一行一行放入queue的，防止出现了上面思路的问题
根据代码来看是这么实现的：

1. 首先，把row1放入priority queue，主要是有个长度概念吧，这样方便放后面的array，从题目给的matrix的样子来说，方便向下层对应位置找数字
2. 开始k-1次循环，
    1. 目的是：poll出 [1~(k-1)th Smallest elements。 这样循环结束时，poll()就得到 kth element
    2. 过程是：向queue中依次poll, add， 只要poll出的元素的row<matrix.lenth-1， 每poll出一个row的元素，放入一个row+1对应位置的元素，（下一行对应位置,ie:poll(1)-->i.row<matrix.lenth-1-->add(10)）
    3. 原理是：其实我们想做的事情，就是 依次把每个array的每个元素放入queue中，直到遇到 kth element，，但这里实现的方式是通过拿出，就是相反的思路，先放，后拿，直到拿出到kth element。（真的太难说清楚了，我严重怀疑自己再次看这段话的时候都会懵逼，真的顺着代码的思路一步一步画个queue，肯定可以很好的理解到底咋回事）

Time complexity: O(klgk)
跟着答案画图过了一遍，然后自己写一遍，出现了好多typo，水爆了，因为typo面试编译不过岂不是懵逼。

还有，自己写的时候懒了，用了Lembda->然后发现慢了很多，问号脸，所以还是override compare方法吧。。。
```
public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        PriorityQueue<Elem> queue = new PriorityQueue<>(len,(a,b)->(a.val-b.val));
        for(int i = 0; i<len; i++){
            queue.add(new Elem(0,i,matrix[0][i]));
        } 
        for(int i = 0; i<k-1; i++){
            Elem elem = queue.poll();
            if(elem.row==(len-1)) continue;
            queue.add(new Elem(elem.row+1,elem.col,matrix[elem.row+1][elem.col]));
        }
        return queue.poll().val;
    }
    class Elem{
        int row, col, val;
        public Elem(int r, int col, int val){
            this.row=r;
            this.col=col;
            this.val=val;
        }
    }
    //不用Lambda的等效写法:PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }  
    @Override
    public int compareTo (Tuple that) {
        return this.val - that.val;
    }
}
```

这题discuss区还有个binary search的解法, 这个讲解绍了两种 search space

1. index -- A bunch of examples -- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ ( the array is sorted)
2. range -- https://leetcode.com/problems/find-the-duplicate-number/ (Unsorted Array)

The reason why we did not use index as "search space" for this problem is the matrix is sorted in two directions, we can not find a linear way to map the number and its index. 就是一开始说的错误思路有的问题.

注意：我只是贴上了代码，然而并不能看懂，，过程序好麻烦，放弃了，还是用上面queue的方法吧，binary search的方法地址在https://discuss.leetcode.com/topic/52948/share-my-thoughts-and-clean-java-code
```
   public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
```
