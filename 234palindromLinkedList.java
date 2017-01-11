/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
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
}
//without stack, O(1)space, O(n) time
public class Solution {
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
}