/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode ld = new ListNode(0);
        ListNode sd = new ListNode(0);
        ListNode large = ld;
        ListNode small = sd;
        while(head!=null){
            if(head.val<x){
                small.next = head;
                small = small.next;
            }else{
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = ld.next;
        return sd.next;
    }
}

