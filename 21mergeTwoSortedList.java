/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                head.next = l1;
                l1=l1.next;
            }else{
                head.next = l2;
                l2=l2.next;
            }
            //head 新链表中当前元素，所以head每连接一个next，head后移
            head = head.next;
        }
        //l2更小，整个链表都merge完了，那剩下就把l1中元素依次添加到当前head后面
        while(l1!=null){
            head.next = l1;
            l1=l1.next;
            head = head.next;
        }
        while(l2!=null){
            head.next = l2;
            l2=l2.next;
            head = head.next;
        }
        return dummy.next;
    }
    //recursion
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }
}