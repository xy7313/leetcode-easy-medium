/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x;  }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        node.val=node.next.val;
        // node.ListNode(node.next.val);不能这么用，构造方法，new的时候才能用
        node.next=node.next.next;
    }
}