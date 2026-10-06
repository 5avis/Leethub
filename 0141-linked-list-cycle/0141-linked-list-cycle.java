/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // Base case: An empty list or a list with only one node cannot have a cycle
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Move fast by 2 steps and slow by 1 step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            // If they meet, a cycle exists
            if (slow == fast) {
                return true;
            }
        }
        
        // If fast reaches null, there is no cycle
        return false;
    }
}
