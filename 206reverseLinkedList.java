/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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