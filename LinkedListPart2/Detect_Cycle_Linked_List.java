package LinkedListPart2;


// Problem link:https://leetcode.com/problems/linked-list-cycle/description/


public class Detect_Cycle_Linked_List {

    /** Optimal Approach:
     * Use floyds hare and tortoise algorithm
     * */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    public static class Solution1 {
        public boolean hasCycle(ListNode head) {
            if(head==null || head.next==null){
                return false;
            }
            ListNode fast = head, slow = head;
            while(fast!=null && fast.next!=null){
                slow = slow.next;
                fast = fast.next.next;
                if(fast==slow){
                    return true;
                }
            }
            return false;
        }
    }
}
