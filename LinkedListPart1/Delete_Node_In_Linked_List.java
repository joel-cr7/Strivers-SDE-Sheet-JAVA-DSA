package LinkedListPart1;


// Problem link: https://leetcode.com/problems/delete-node-in-a-linked-list/


public class Delete_Node_In_Linked_List {

    /** Optimal Approach:
     * Assign current node value of next node, and remove next node
     * */

    // Time Complexity: O(1)
    // Space complexity: O(1)

    static class Solution1 {
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
