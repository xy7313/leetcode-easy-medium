/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 //是个可以ac的答案，但是讨论区都说这个复杂度并不是O(1)。这个题的mergesort用的是分治思想
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

/*
    strict O(1):
    1. First of all, allow me to explain the meaning of strict O(1) auxiliary space complexity.It means the maximum number of memory used by the program, except the memory taken by the input data, doesn't change with the input size.
    2. This indicates that, strictly speaking, any solution that involves recursion can never have a strict O(1) auxiliary space complexity. Because the maximum recursion level depends on the the input size and each recursion call consumes memory on stack, thus the maximum number of memory used depends on the input size.
    This method based on merge-sort
    1. Time complexity: In each level, each node is visited by at maximum twice. And there are log(n) level. Thus the time complexity is O(2n* log n ) => O( n* log n )
    2. Space complexity:
    There are no recursion calls in this solution. Thus the maximum number of function calls is constant.
    The number of dummy nodes is constant.
    Thus the auxiliary space complexity is O(1).
*/

//代码太长还没来得及看
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    private class MergeHelper{
        public ListNode newHead;
        public ListNode newTail;
    }
    public ListNode sortList(ListNode head) {
    if ( head == null || head.next == null) {
        return head;
    }
    
    ListNode dummyHeadOne = new ListNode(0);
    ListNode dummyHeadTwo = new ListNode(0);
    ListNode dummySortedHead = new ListNode(0);
    ListNode dummySortedLast = dummySortedHead;
    ListNode unvisitedNode = head;
    MergeHelper mergeRst = new MergeHelper();
    
    int listLength = 0;
    int level = 0;
    while ( unvisitedNode != null && unvisitedNode.next != null ) {
        unvisitedNode = addNode ( dummyHeadOne, unvisitedNode, 1<<level);
        unvisitedNode = addNode ( dummyHeadTwo, unvisitedNode, 1<<level);
        merge ( dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
        dummySortedLast.next = mergeRst.newHead;
        dummySortedLast = mergeRst.newTail;
        listLength += 2;
    }
    if (unvisitedNode != null) {
        dummySortedLast.next = unvisitedNode;
        listLength ++;
    }
    level ++;
    
    while ( listLength > 1 << level) {
        dummySortedLast = dummySortedHead;
        unvisitedNode = dummySortedHead.next;
        while (unvisitedNode != null) {
            unvisitedNode = addNode ( dummyHeadOne, unvisitedNode, 1<<level);
            unvisitedNode = addNode ( dummyHeadTwo, unvisitedNode, 1<<level);
            merge ( dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
            dummySortedLast.next = mergeRst.newHead;
            dummySortedLast = mergeRst.newTail;
        }
        level ++;
    }
    
    return dummySortedHead.next;
}

/* merge listOne and listTwo. 
Save the sorted list head into rst.newHead
Save the last node of the sorted list into rst.newTail
*/
private void merge (ListNode listOne, ListNode listTwo, MergeHelper rst) {
    ListNode dummyHead = new ListNode (0);
    ListNode lastNode = dummyHead;
    while (listOne != null && listTwo != null) {
        if ( listOne.val < listTwo.val ) {
            lastNode.next = listOne;
            listOne = listOne.next;
        } else {
            lastNode.next = listTwo;
            listTwo = listTwo.next;
        }
        lastNode = lastNode.next;
    }
    
    while (listOne != null) {
        lastNode.next = listOne;
        listOne = listOne.next;
        lastNode = lastNode.next;
    }
    while ( listTwo != null ) {
        lastNode.next = listTwo;
        listTwo = listTwo.next;
        lastNode = lastNode.next;
    }
    rst.newHead = dummyHead.next;
    rst.newTail = lastNode;
}

/*
 add at max #"count" nodes into "head" from "source"
 return the new position of source after adding.
*/
private ListNode addNode ( ListNode head, ListNode source, int count ) {
    while (count > 0 && source != null) {
        head.next = source;
        head = head.next;
        source = source.next;
        count --;
    }
    head.next = null;
    return source;
}
    
}