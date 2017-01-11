/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 discuss:https://discuss.leetcode.com/topic/27868/concise-java-solution-based-on-slow-fast-pointers
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode walker = head;
        ListNode runner = head;
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
}