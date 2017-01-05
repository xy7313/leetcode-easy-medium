/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        //while，不用if，test case：[1,1] 1
        while(head!=null&&head.val==val) head= head.next;
        ListNode next = head;
        while(next!=null&&next.next!=null){
            if(next.next.val==val) next.next = next.next.next;
            //else 提交出错，忘了写else了。。。test case:[1,2,2,1] 2
            else next = next.next;
        }
        return head;
    }
}