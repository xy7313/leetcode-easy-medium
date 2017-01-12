/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
    
    /*
    一种不用算length difference的方法，用了类似快慢指针跑，总会在intersection处相遇或者没有intersection在最后的null相遇
     use two iterations here. In the first iteration, we will reset the pointer of one linkedlist to the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two pointers until they points to the same node. Our operations in first iteration will help us counteract the difference.
    So if two linkedlist intersects, the meeting point in second iteration must be the intersection point. If the two linked lists have no intersection at all, then the meeting pointer in second iteration must be the tail node of both lists, which is null
    */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        // if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;    
        }
        return a;
    }

    //这里是一个更general的方法, 一开始在想相交之后还会不会分开，比如一开始就相交，那长度不等情况下先向后移就说不过去了，但是这里应该是利用了链表特性，每个node都指向另一个node，所以相交之后就一定都一样了。
     public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int la = 0, lb=0;
        ListNode pa = headA, pb = headB;
        while(pa!=null){
            pa=pa.next;
            la++;
        }
        while(pb!=null){
            pb=pb.next;
            lb++;
        }
        pa=headA;
        pb=headB;
        if(la>lb){
            while(la>lb){
                pa=pa.next;
                la--;
            }
        }else{
            while(lb>la){
                pb=pb.next;
                lb--;
            }
        }
        while(pa!=pb){
            pa=pa.next;
            pb=pb.next;
        }
        return pa;
    }
}