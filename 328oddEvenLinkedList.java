/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        //两个dummy的思路
        ListNode OddDummy = new ListNode(0);
        ListNode EvenDummy = new ListNode(0);
        ListNode odd = OddDummy;
        ListNode even = EvenDummy;
        //和value无关，只和第几个node有关
        boolean isodd = true;
        while(head!=null){
            if(!isodd){
                even.next = head;
                even = even.next;
            }else{
                odd.next = head;
                odd = odd.next;
            }
            head = head.next;
            isodd = !isodd;
        }
        even.next = null;
        odd.next = EvenDummy.next;
        
        return OddDummy.next;       
    }
}