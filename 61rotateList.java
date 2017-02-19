/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode runner = dummy;
        ListNode walker = dummy;
        //以下投机代码不能处理k>length的情况
        // while(k>0 && runner.next!=null){
        //     runner = runner.next;
        //     k--;
        // }
        // while(runner.next!=null){
        //     walker = walker.next;
        //     runner = runner.next;
        // }
        int len = 0;
        for (;runner.next!=null;len++){//Get the total length 
    	    runner = runner.next;
         }
         System.out.println(len);
        for (int j= len - k%len ;j>0;j--){ //Get the i-n%i th node
    	    walker = walker.next;
        }
        // System.out.println(j);

        runner.next = dummy.next;
        dummy.next = walker.next;
        walker.next = null;
    
        return dummy.next;
    }
}