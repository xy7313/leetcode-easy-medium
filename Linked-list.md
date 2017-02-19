##61B刚好看到LinkedList 所以把之前空的链表题都补一下，下面全部都是linked list相关

61. Rotate List
206. Reverse LinkedList
92. Reverse Linked List II
237. Delete Node in a Linked List
203. Remove Linked List Elements
83. Remove Duplicates from Sorted List
328. Odd Even Linked List
24. Swap Nodes in Pairs
86. Partition List
82. Remove Duplicates from Sorted List II
19. Remove Nth Node From End of List
141. Linked List Cycle
142. Linked List Cycle2
234. Palindrome Linked List
160. Intersection of Two Linked Lists
148. Sort List
147. Insertion Sort List
21. Merge Two Sorted Lists
23. Merge k Sorted Lists
2. Add Two Numbers
445. Add Two Numbers II


####61. Rotate List
这题看起来很简单，没查答案就开始写了，果然轻敌了。踩得坑见代码，叫什么呢，偷鸡不成，，，
```
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode runner = dummy;
        ListNode walker = dummy;
        //以下投机代码不能处理k>length的情况
        // while(k>0 && runner.next!=null){
        //     runner = runner.next;
        //     k--;
        // }
        // while(runner.next!=null){
        //     walker = walker.next;
        //     runner = runner.next;
        // }
        int len = 0;
        for (;runner.next!=null;len++){//Get the total length 
    	    runner = runner.next;
         }
         System.out.println(len);
        for (int j= len - k%len ;j>0;j--){ //Get the i-n%i th node
    	    walker = walker.next;
        }
        // System.out.println(j);

        runner.next = dummy.next;
        dummy.next = walker.next;
        walker.next = null;
    
        return dummy.next;
    }
}
```
####206. Reverse LinkedList
没做过一上来感觉不太好想，有个视频，看到一半恍然大悟：https://www.youtube.com/watch?v=sYcOK51hl-A
这类题都开始要求用iteration写一次再用recursive写一个
```
 //假设1->2->3->4,先从head=1开始，要翻转，最后一个会变成head，所以head一步一步向后挪，每一步也一起翻转指向
public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head!=null){
            ListNode nextHead = head.next;
            head.next = prev;
            prev=head;
            //这里得到的prev即下一个head前面的数，也就是下一个head要指向的数，当head=最后一个node（tail）时，prev=tail，循环结束
            head = nextHead;
        } 
        return prev;
    }
    //recursive
     public ListNode reverseList(ListNode head) {
        return reverseRecursive(head,null);
    }
    public ListNode reverseRecursive(ListNode head,ListNode prev){
        if(head==null) return prev;
        ListNode nextHead = head.next;
        head.next=prev;
        //下面传参其实就相当于这两句：prev=head;head = nextHead;
        return reverseRecursive(nextHead,head);
    }
}
```

####92. Reverse Linked List II
For example:

Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

```
根据题中的例子，第二个for循环开始

loop1:
1 --> 2 --> 3 --> 4 --> 5 --> NULL
p     c     n
cur.next = next.next;
2 --> 4
next.next = prev.next;
3 --> 2
prev.next = next;
1 --> 3
==> 1 --> 3 --> 2 --> 4 --> 5 --> NULL
    p          c      n
loop2:
cur.next = next.next;
2 --> 5
next.next = prev.next;
4 --> 3
prev.next = next;
1 --> 4
==> 1 --> 4 --> 3 --> 2 --> 5 --> NULL
```

```
public ListNode reverseBetween(ListNode head, int m, int n) {
    if(m == n) return head;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode cur = head;
    //在reverse之前的部分平移
    for(int i = 0; i < m - 1; i++){
        prev = prev.next;
        cur = cur.next;
    }
    for(int i = 0; i < n - m; i++){
        ListNode next = cur.next;
        cur.next = next.next;
        next.next = prev.next;
        prev.next = next;
    }
    return dummy.next;
}
```

####237. Delete Node in a Linked List
代码很简单，道理也都懂，思路也没错，但居然写成了注释里那种形式，有一点被注释里的class definition 影响了，还有是对构造函数的不熟悉，总之这个主意吧。
思路是最后一个node的值付给当前，当前的.next置成next.next
```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x;  }
 * }
 */
node.val=node.next.val;
        // node.ListNode(node.next.val);不能这么用，构造方法，new的时候才能用
node.next=node.next.next;
```

####203. Remove Linked List Elements
删除指定element，看起来很简单，写错了两点,第二个方法，dummy-哑节点，的使用多针对单链表没有向前指针的问题，保证链表的head不会在删除操作中丢失，或者用来删除head
所以当链表head可能有变化时就用dummy，`ListNode dummy = New ListNode(0);dummy.next=head;`最后返回dummy.head
```
public ListNode removeElements(ListNode head, int val) {
    //1. while，不用if，test case：[1,1] 1,如果head一直是val，那就一直向后取，直到head.val!=val
    while(head!=null&&head.val==val) head= head.next;
    ListNode next = head;
    while(next!=null&&next.next!=null){
        if(next.next.val==val) next.next = next.next.next;
        //2. else 提交出错，忘了写else了。。。test case:[1,2,2,1] 2
        else next = next.next;
    }
    return head;
}
//dummy, 给加个head，这样之前的head就可以当做普通node来处理了，很好的方法
public ListNode removeElements(ListNode head, int val) {
    ListNode dummy = New ListNode(0);
    dummy.next=head;
    head = dummy;
    //现在head.next才是之前的head
    while(head.next!=null){
        if(head.next.val==val) head.next = head.next.next;
        else head = head.next;
    }
    return dummy.next;
}
```

####83. Remove Duplicates from Sorted List
还是一种iteration一种recursion,这个题可以不用dummy，删除第二个重复元素，确保head不会改动
```
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //改动list不改动head，最后返回dead
        ListNode list = head;
        while(list!=null){
            if(list.next==null){
                return head;
            }else if(list.next.val == list.val){
                //这里跟237那道delete一个意思
                list.next = list.next.next;
            } else{
                list = list.next;
            }
        }
        return head;
    }
    //recursive
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}
```

####328. Odd Even Linked List
拼接链表，可通过多个dummy实现，注意  和value无关，只和第几个node有关
```
public ListNode oddEvenList(ListNode head) {
    ListNode OddDummy = new ListNode(0);
    ListNode EvenDummy = new ListNode(0);
    ListNode odd = OddDummy;
    ListNode even = EvenDummy;
    boolean isodd = true;
    while(head!=null){
        if(!isodd){
            even.next = head;
            even = even.next;
        }else{
            odd.next = head;
            odd = odd.next;
        }
        head = head.next;
        isodd = !isodd;
    }
    even.next = null;
    odd.next = EvenDummy.next;
    return OddDummy.next;       
}
```

####86. Partition List
和上题类似的思路，大于x和小于x的部分分别用两个dummy连起来
代码也基本和上面一样，就是isodd换成和x大小的比较。加深了对dummy的理解。一开始的odd，even初始化那里，意思是odd和odddummy， even和evendummy分别指向同一个node，所以odd，even next改变的时候对应的dummy.next也改变了，但之后odd，even指向下一个node了，不和dummy指向同一个node了，所以改变都不影响到dummy

####24. Swap Nodes in Pairs
看起来很简单的题和思路，但是很容易想乱，比如哪个是真正的node，哪个是为了保存出来的node。这里current.next是1，current.next.next是2，这里要搞清楚。因为后面是要把1，2付给别人，所以，在复制之前不能改变他俩本身的值，，我也不知道我在说什么了，总之很容易乱，画个图就都清楚了
```
public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode current = dummy;
    while (current.next != null && current.next.next != null) {
        ListNode first = current.next;
        ListNode second = current.next.next;
        first.next = second.next;
        current.next = second;
        current.next.next = first;
        current = current.next.next;
    }
    return dummy.next;
}
```

####82. Remove Duplicates from Sorted List II
这个题不会做！！第一个方法还稍微好懂一点，拿[1,1,1,2,3]的例子来说，第一个内层while结束，slow.next=head=1, fast=最后一个1， 进入if(注意并不是比较val)，之后就，代码肯定能看懂，但是dummy.next的变化一直想不通，fast slow都满满指向最后面去了，dummy.next是怎么移动到2，又是怎么不移动了的。。。
```
public ListNode deleteDuplicates(ListNode head) {
	//use two pointers, slow - track the node before the dup nodes, 
	// fast - to find the last node of dups.
    ListNode dummy = new ListNode(0), fast = head, slow = dummy;
    slow.next = fast;
    while(fast != null) {
    	while (fast.next != null && fast.val == fast.next.val) {
     		fast = fast.next;    //while loop to find the last node of the dups.
    	}
    	if (slow.next != fast) { //duplicates detected.
    		slow.next = fast.next; //remove the dups.
    		fast = slow.next;     //reposition the fast pointer.
    	} else { //no dup, move down both pointer.
    		slow = slow.next;
    		fast = fast.next;
    	}
    }
    return dummy.next;
}
```

####19. Remove Nth Node From End of List
walker and runner, init walker,runner both as dummy, move runner n steps, so that the gap between runner and walker =n, then move runner and walker together, when runner get to the end of List, walker is before the nth from the end node, walker.next=walke.next.next， skip original walker.next
```
public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode walker = dummy;
    ListNode runner = dummy;
    // after for loop, gap between runner and walker =n
    for(int i = 1; i <= n; i++){
        runner = runner.next;
    }
    while(runner.next!=null){
        runner = runner.next;
        walker = walker.next;
    }
    walker.next=walker.next.next;//skip nth node
    return dummy.next;
}
```

####141. Linked List Cycle
用双指针的思路，walker moves step by step. runner moves two steps at time. if the Linked List has a cycle walker and runner will meet at some
point. 

解法代码下一题中其实是包含的，但我还是把这个代码贴出来了，因为判定条件那里需要注意，这道题的写法是，先判断了head==null，之后while中判断runner.next和runner.next.next，个人理解是runner跑的快，需要注意判断runner而不是walker。下一题的写法看起来跟这个不同，其实一样
```
public boolean hasCycle(ListNode head) {
    if(head==null) return false;
    ListNode walker = head;
    ListNode runner = head;
    // runner跑的快，在前面，所以判断runner.next, runner.next.next
    while(runner.next!=null&&runner.next.next!=null){
        walker = walker.next;
        runner = runner.next.next;
        if(walker==runner) return true;
    }
    return false;
}
```

####142. Linked List Cycle2
关于判定条件的一个问题上道题中解释了

这个题目的思路不太好想，discuss中有一个很好的解释，贴过来，其中关键的两点是，walker走过的距离和cycle长度的关系，以及walker,runner相遇之后再通过head和walker一齐走，相遇点是cycle起点这层关系

>[Explanations](https://discuss.leetcode.com/topic/27868/concise-java-solution-based-on-slow-fast-pointers)

```
Definitions:
Cycle = length of the cycle, if exists.
C is the beginning of Cycle, S is the distance of slow pointer from C when slow pointer meets fast pointer.
Distance(slow) = C + S, Distance(fast) = 2 * Distance(slow) = 2 * (C + S). To let slow poiner meets fast pointer, only if fast pointer run 1 cycle more than slow pointer. Distance(fast) - Distance(slow) = Cycle
=> 2 * (C + S) - (C + S)	= Cycle
=>	C + S = Cycle
=>	C = Cycle - S
=> This means if slow pointer runs (Cycle - S) more, it will reaches C. So at this time, if there's another point2(we use head Here) running from head
=> After C distance, point2 will meet slow pointer at C, where is the beginning of the cycle.
```
```
public ListNode detectCycle(ListNode head) {
    ListNode walker = head;
    ListNode runner = head;
    //这里不加runner.next.next!=null也ac
    while(runner!=null&&runner.next!=null&&runner.next.next!=null){
        runner = runner.next.next;
        walker = walker.next;
        if(runner==walker){
            while(head!=walker){
                head = head.next;
                walker = walker.next;
            }
            return walker;
        }
    }
    return null;
}
```

####234. Palindrome Linked List
假设是odd长（even同理）

一开始的思路是，分成前后相等两部分，中间点可以不管，把后半部分reverse一下，然后和前半部分比较看是否完全相同，看了好几个gitbook的实现都是这样的，有个[YouTube视频](https://www.youtube.com/watch?v=Os5FM4KQtxw&index=14&list=PLNmW52ef0uwsjnM06LweaYEZr-wjPKBnj)，讲这道题，用的是stack，个人觉得用在这里很适合，把前半部分压栈，过中点之后依次pop出来跟后半截比较。

另一个技巧：用都指向 head 的快慢指针可以判断链表长度奇偶，最后 fast == null 的时候为偶，slow 指向后半单第一个节点;fast.next == null 的时候链表长度为奇数，slow 指向中间节点
```
 public boolean isPalindrome(ListNode head) {
    ListNode walker = head, runner = head;
    Stack<Integer> s = new Stack<>();
    //runner==null, if list.size is even, runner.next==null,when list.size is odd
    while(runner!=null&&runner.next!=null){
        s.push(walker.val);
        walker = walker.next;
        runner = runner.next.next;
    }
    //if the list size is odd, we should skip the middle node
    if(runner!=null) walker = walker.next;
    while(walker!=null){
        if(s.pop().intValue()!=walker.val) return false;
        walker = walker.next;
    }
    return true;    
}
```
但是，题目有要求O(1) space complexity,可以用一开始的思路实现，主体部分跟上面的思路很像，都用到了fast==null来判断长度是odd还是even这点，单独写了compare方法，默认了传入的两个list长度相同的，另一个reverse方法之前写过，reverse每次看都懵逼
```
public boolean isPalindrome(ListNode head) {
    if(head == null || head.next == null) return true;
    ListNode slow = head;
    ListNode fast = head;
    while(fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
    }
    if(fast == null){
        ListNode headB = reverse(slow);
        return compare(head, headB);
    } else {
        ListNode headB = slow.next;
        slow = null;
        headB = reverse(headB);
        return compare(head, headB);
    }             
}
 //这里默认传入的两个list长度一定相同，调用前做的处理
private boolean compare(ListNode headA, ListNode headB){
    while(headA != null && headB != null){
        if(headA.val != headB.val) return false;
        headA = headA.next;
        headB = headB.next;
    }
    return true;
}
private ListNode reverse(ListNode head){
    ListNode prev = null;
    while(head != null){
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}
```

####160. Intersection of Two Linked Lists
1. 一个general的方法, 比较两个linked list的长度，把较长的一个链表后移几位，从长度和另一链表相等处开始比较node是否相同。
一开始在想相交之后还会不会分开，比如一开始就相交，那长度不等情况下先向后移就说不过去了，但是这里应该是利用了链表特性，每个node都指向另一个node，所以相交之后就一定都一样了。
2. 一个很机智的方法，感觉用到了类似single linked list中判断是否有cycle时候用的runner 和walker双指针的方法，这个题中的“双指针”总会在intersection处相遇或者没有intersection在最后的null相遇.
disscuss区大神的分析:
>use two iterations here. In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two pointers until they points to the same node. Our operations in first iteration will help us counteract the difference.
So if two linkedlist intersects, the meeting point in second iteration must be the intersection point. If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null

>The problem description especially required the code to run in O(n) time and O(1) space. Thus I came up with the most direct way.
　　Just count the lengths of both lists, set two pointers from the list heads, align them to equipotential position and move'em forward until they coincide.
　　That would be the answer we seek.
　　Time complexity should be O(n + m), if you name the lengths of both lists to be "n" and "m". Extra space required is O(1).

Notice：只贴一下第二个方法，第一个方法很简单，分别遍历链表直到空，通过counter获取长度，然后通过两个长度差值移动指向较长链表的node的位置，在等长之后比较node是否相同，是就返回该node。
```
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null) return null;
    ListNode a = headA;
    ListNode b = headB;
    while( a != b){
        a = a == null? headB : a.next;
        b = b == null? headA : b.next;    
    }
    return a;
}
``` 

####148. Sort List
sortlist的题都很麻烦的样子，所以记得不要sortlist。。。

这个题有特殊要求，O(1) space complexity。首先，strict O(1) auxiliary space complexity means the maximum number of memory used by the program, except the memory taken by the input data, doesn't change with the input size. 所以，strictly speaking, any solution that involves recursion can never have a strict O(1) auxiliary space complexity. Because the maximum recursion level depends on the the input size and each recursion call consumes memory on stack, thus the maximum number of memory used depends on the input size.

简单来说，递归的都做不到O(1)，大部分是O(logn)，但我只能看懂一个递归的方法，不递归的思路看起来很简单就是merge sort，代码看起来好复杂
```
public class Solution {
    public ListNode sortList(ListNode head) {
        //这个if很棒，返回head
        if(head==null||head.next==null) return head;
        //split the list into 2 pieces;
        ListNode midEnd = head;
        ListNode walker = head;
        ListNode runner = head;
        while(runner != null&& runner.next!=null){
            midEnd = walker;
            walker = walker.next;
            runner = runner.next.next;
        }
        midEnd.next=null;
        //sort each part by divide conqure method: divide --> merge in order
        ListNode l1 = sortList(walker);
        ListNode l2 = sortList(head);       
        return merge(l1,l2);
    }
    public ListNode merge(ListNode l1,ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;   
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }       
        if (l1 != null) cur.next = l1;    
        if (l2 != null) cur.next = l2;
        return dummy.next;         
    }
}
```

####147. Insertion Sort List
目前看来打算写的最后一道linked list题
看题的时候在discuss发现了很多有意思的东西，比如这个
>Thoughts from google interviewer
For God's sake, don't try sorting a linked list during the interviewer

and

Sort array or sort linkedlist[here](http://stackoverflow.com/questions/1525117/whats-the-fastest-algorithm-for-sorting-a-linked-list/1525419#1525419)
Depending on a number of factors, it may actually be faster to copy the list to an array and then use a Quicksort.
       
The reason this might be faster is that an array has much better cache performance than a linked list. If the nodes in the list are dispersed in memory,you may be generating cache misses all over the place. Then again, if the array is large you will get cache misses anyway.

Mergesort parallelises better, so it may be a better choice if that is what you want. It is also much faster if you perform it directly on the linked list.

Since both algorithms run in O(n * log n), making an informed decision would involve profiling them both on the machine you would like to run them on.

这个题表示不会做，简化算法if判断的原因： Before insert, the prev is at the last node of the sorted list. Only the last node's value is larger than the current inserting node should we move the temp back to the head

```
public ListNode insertionSortList(ListNode head) {
    if( head == null ){
        return head;
    }
    ListNode dummy = new ListNode(0); //new starter of the sorted list
    ListNode cur = head; //the node will be inserted
    ListNode pre = dummy; //insert node between pre and pre.next
    ListNode next = null; //the next node will be inserted
    //not the end of input list
    while( cur != null ){
        next = cur.next;
        //find the right place to insert
        while( pre.next != null && pre.next.val < cur.val ){
            pre = pre.next;
        }
        //insert between pre and pre.next
        cur.next = pre.next;
        pre.next = cur;
        //reset，还有一种简化的方法，这里pre不重置到dummy，下次近inner while前判断一下if (prev.val >= head.val) prev = dummy;
        pre = dummy;
        cur = next;
    }
    return dummy.next;
}
```

####21. Merge Two Sorted Lists
还是iteration和recursion,iteration代码太长了，由此可见递归的好处，代码简介易懂

iteration注意 l1,l2挨个merge的时候为了方便，l1,l2在merge后指向自己next，即后移，同时head即新链表的当前node也后移，另外这里也是head不确定的情况，所以用dummy
```
//dummy
    ListNode dummy = new ListNode(0);
    ListNode head = dummy;
    ...
    return dummy.next;
//recursion
public ListNode mergeTwoLists(ListNode l1, ListNode l2){
    if(l1 == null) return l2;
    if(l2 == null) return l1;
    if(l1.val < l2.val){
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else{
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}
```

####23. Merge k Sorted Lists
根据priority queue的特性，我们可以通过重写compare方法利用priority queue实现，还有dummy，从后向前拼接。

和下面sort里179一样，都重写了compare。一个是sort方法内，一个是priority queue
```
public ListNode mergeKLists(ListNode[] lists) {
    if (lists==null||lists.length==0) return null;
    PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>(){
        @Override
        /*
        1. 这里compare方法可以直接return n1.val-n2.val;
        */
        public int compare(ListNode n1, ListNode n2){
            if(n1.val<n2.val) return -1;
            else if(n1.val==n2.val) return 0;
            else return 1;
        }
    });
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    for(ListNode n:lists){
        if(n!=null) queue.add(n);
    }
    while(!queue.isEmpty()){
        tail.next = queue.poll();
        tail=tail.next;
        
        if(tail.next!=null){
            queue.add(tail.next)；
        } 
    }
    return dummy.next;
}
```

####2. Add Two Numbers
简单版：

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)

Output: 7 -> 0 -> 8

这种题对我来说的难点都在 创建sentinel，和d，不停在d后面添加node这些地方，这两种方法其实思路一样，实现上稍有区别
```
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {     
    ListNode cur1 = l1,cur2 = l2;
    ListNode sentinel = new ListNode(0);
    ListNode d = sentinel;
    int carry = 0,sum=0;
    while(cur1!=null||cur2!=null||carry!=0){
        sum = (cur1 != null ? cur1.val : 0) + (cur2 != null ? cur2.val : 0) +carry;
        carry = sum / 10;
        d.next = new ListNode(sum % 10);
        d = d.next;
        cur1 = (cur1!=null?cur1.next:null);
        cur2 = (cur2!=null?cur2.next:null);    
    }
    return sentinel.next;
}
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode c1 = l1;
    ListNode c2 = l2;
    ListNode sentinel = new ListNode(0);
    ListNode d = sentinel;
    int sum = 0;
    while (c1 != null || c2 != null||sum/10!=0) {
        sum /= 10;//sum means carry here
        if (c1 != null) {
            sum += c1.val;
            c1 = c1.next;
        }
        if (c2 != null) {
            sum += c2.val;
            c2 = c2.next;
        }
        d.next = new ListNode(sum % 10);
        d = d.next;
    }
    return sentinel.next;
}
```

####445. Add Two Numbers II
跟上面那题很类似，但是Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 8 -> 0 -> 7

代码用stack实现，一开始是用了三个stack，后来发现可以通过改变新链表的拼接方式生一个stack
```
//stack3
ListNode dummy = new ListNode(0);
ListNode d = dummy;
while(!s.empty()||!s2.empty()||carry!=0){
    sum = (s.empty()?0:s.pop().intValue())+(s2.empty()?0:s2.pop().intValue())+carry;
    carry = sum/10;
    s3.push(sum%10);
}
while(!s3.empty()){
    d.next = new ListNode(s3.pop().intValue());
    d=d.next;
}
//without stack3, 在dummy前面一步一步加node
ListNode dummy = new ListNode(0);
while(!s.empty()||!s2.empty()||carry!=0){
    sum = (s.empty()?0:s.pop().intValue())+(s2.empty()?0:s2.pop().intValue())+carry;
    carry = sum/10;
    dummy.val = sum%10;
    ListNode d = new ListNode(sum%10);
    d.next = dummy;
    dummy=d; 
}
```