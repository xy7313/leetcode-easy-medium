/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode list = head;
        while(list!=null){
            if(list.next==null){
                return head;
            }else if(list.next.val == list.val){
                list.next = list.next.next;
            } else{
                list = list.next;
            }
        }
        return head;
    }
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }
}