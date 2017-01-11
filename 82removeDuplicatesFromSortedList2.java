    
	//这个方法还稍微好懂一点，拿[1,1,1,2,3]的例子来说，第一个内层while结束，slow.next=head=1, fast=最后一个1， 进入else，slow=slow.next就是此时的head，是fast=最后一个1，fast=fast.next=2,再次进入外层while，内层while跳过，slow.next=2=fast 进入else，slow=2，fast=3
public ListNode deleteDuplicates(ListNode head) {
	//use two pointers, slow - track the node before the dup nodes, 
	// fast - to find the last node of dups.
    ListNode dummy = new ListNode(0), fast = head, slow = dummy;
    slow.next = fast;
    while(fast != null) {
    	while (fast.next != null && fast.val == fast.next.val) {
     		fast = fast.next;    //while loop to find the last node of the dups.
    	}
    	if (slow.next != fast) { //duplicates detected.
    		slow.next = fast.next; //remove the dups.
    		fast = slow.next;     //reposition the fast pointer.
    	} else { //no dup, move down both pointer.
    		slow = slow.next;
    		fast = fast.next;
    	}
    }
    return dummy.next;
}

  public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while(cur!=null){
            while(cur.next!=null&&cur.val ==cur.next.val){
                cur = cur.next;   
            }
            if(pre.next ==cur) pre = pre.next;
            else pre.next = cur.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }

//recursive solution
public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;
    
    if (head.next != null && head.val == head.next.val) {
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return deleteDuplicates(head.next);
    } else {
        head.next = deleteDuplicates(head.next);
    }
    return head;
}

