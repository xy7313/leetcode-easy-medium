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
        
        ListNode cur1 = l1,cur2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int carry = 0,sum=0;
        while(cur1!=null||cur2!=null||carry!=0){
            sum = (cur1 != null ? cur1.val : 0) + (cur2 != null ? cur2.val : 0) +carry;
            carry = sum / 10;
            d.next = new ListNode(sum % 10);
            d = d.next;
            cur1 = (cur1!=null?cur1.next:null);
            cur2 = (cur2!=null?cur2.next:null);
            
        }
        return sentinel.next;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        // while (c1 != null || c2 != null) {   //add sum/10!=0, we do not need the if statement before return.
        while (c1 != null || c2 != null||sum/10!=0) {
            sum /= 10;//sum means carry here
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        // if (sum / 10 == 1)
        //     d.next = new ListNode(1);
        return sentinel.next;
    }
   
}