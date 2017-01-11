/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
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
}

