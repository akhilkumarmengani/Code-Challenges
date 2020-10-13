/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    public ListNode findMidNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null){
            fast = fast.next;
            if(fast!=null && fast.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return slow;
    }
    
    public ListNode mergeLists(ListNode left,ListNode right){
        if(left == null)
            return right;
        if(right == null)
            return left;
        
        ListNode result = null;
        if(left.val <= right.val){
            result = new ListNode(left.val);
            result.next  = mergeLists(left.next,right);
        }
        else{
            result = new ListNode(right.val);
            result.next  = mergeLists(left,right.next);
        }
        return result;
    }
    
    public ListNode sortList(ListNode head) {
        if(head==null || head.next ==null)
            return head;
        ListNode midNode = findMidNode(head);
        ListNode second = midNode.next;
        midNode.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(second);
        return mergeLists(left,right);
    }
}