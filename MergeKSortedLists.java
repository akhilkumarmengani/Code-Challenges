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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                return Integer.compare(a.val,b.val);
            }
        }); 
        int n = lists.length;
        for(int i = 0; i < n ; i++){
            if(lists[i]!=null)
                queue.add(lists[i]);
        }
        ListNode result = null;
        ListNode head = null;
        while(!queue.isEmpty()){
            ListNode top = queue.peek();
            queue.poll();
            if(result == null){
                result = new ListNode(top.val);
                head = result;
            }
            else{
                result.next = new ListNode(top.val);
                result = result.next;
            }
            if(top.next!=null){
                queue.add(top.next);
            }
        }
        return head;
    }
}