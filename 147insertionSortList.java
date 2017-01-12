/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
             /* Before insert, the prev is at the last node of the sorted list.
           Only the last node's value is larger than the current inserting node 
           should we move the temp back to the head*/
            if (prev.val >= cur.val) prev = dummy;
            while (prev.next != null && prev.next.val < cur.val) {
                prev = prev.next;
            }
            cur.next = prev.next;
            prev.next = cur;
            // prev = dummy; // Don't set prev to the head of the list after insert
            cur = temp;
        }
        return dummy.next;
    }
}
//original version
public ListNode insertionSortList(ListNode head) {
    if( head == null ){
        return head;
    }
    
    ListNode helper = new ListNode(0); //new starter of the sorted list
    ListNode cur = head; //the node will be inserted
    ListNode pre = helper; //insert node between pre and pre.next
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
        //reset
        pre = helper;
        cur = next;
    }
    return helper.next;
}