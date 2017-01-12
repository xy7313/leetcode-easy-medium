/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> s3 = new Stack<>();
        int sum = 0, carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode d = dummy;
        while(l1!=null){
            s.push(l1.val);
            l1=l1.next;
        }
        while(l2!=null){
            s2.push(l2.val);
            l2=l2.next;
        }
        while(!s.empty()||!s2.empty()||carry!=0){
            sum = (s.empty()?0:s.pop().intValue())+(s2.empty()?0:s2.pop().intValue())+carry;
            carry = sum/10;
            s3.push(sum%10);
        }
        while(!s3.empty()){
            d.next = new ListNode(s3.pop().intValue());
            d=d.next;
        }
        return dummy.next;
    }
      public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        // Stack<Integer> s3 = new Stack<>();
        int sum = 0, carry = 0;
        ListNode dummy = new ListNode(0);
        while(l1!=null){
            s.push(l1.val);
            l1=l1.next;
        }
        while(l2!=null){
            s2.push(l2.val);
            l2=l2.next;
        }
        while(!s.empty()||!s2.empty()||carry!=0){
            sum = (s.empty()?0:s.pop().intValue())+(s2.empty()?0:s2.pop().intValue())+carry;
            carry = sum/10;
            dummy.val = sum%10;
            ListNode d = new ListNode(sum%10);
            d.next = dummy;
            dummy=d;
            
        }
        return dummy.next;
    }
}