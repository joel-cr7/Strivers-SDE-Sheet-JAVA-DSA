package LinkedListPart1;

import java.util.*;


// Problem link: https://leetcode.com/problems/reverse-linked-list/description/


public class Reverse_Linked_List {

    /** Brute Force Approach:
     * Traverse LinkedList and use stack to store elements
     * Again traverse LinkedList and replace the values by popping stack
     * */

    // Time Complexity: O(2n)
    // Space complexity: O(n)

    static class Solution1 {
        public ListNode reverseList(ListNode head) {
            if(head==null || head.next==null) return head;

            Stack<Integer> st = new Stack<>();
            ListNode temp = head;

            while(temp!=null){
                st.push(temp.val);
                temp = temp.next;
            }

            temp = head;
            while(temp!=null){
                temp.val = st.pop();
                temp = temp.next;
            }

            return head;
        }
    }




    /** Optimal Iterative Approach:
     * Use 3 pointers to keep track of previous, current and next Node
     * Traverse through LinkedList and reverse next pointers
     * */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution2 {
        public ListNode reverseList(ListNode head) {
            if(head==null || head.next==null) return head;

            ListNode prevNode = null, currNode = head;
            ListNode nextNode = currNode.next;

            while(nextNode != null){
                currNode.next = prevNode;
                prevNode = currNode;
                currNode = nextNode;
                nextNode = nextNode.next;
            }
            currNode.next = prevNode;

            return currNode;
        }
    }




    /** Optimal Recursive Approach:
     * Use recursion to reverse and for each recursion return the newHead which is the head of the reversed LinkedList
     * Base case: if there is single node it is already reversed, just return it
     * Else just call for further nodes
     *
     * Video explanation: https://www.youtube.com/watch?v=D2vI2DNJGd8
     * */

    // Time Complexity: O(n)
    // Space complexity: O(n)   Auxiliary stack space

    static class Solution3 {
        public ListNode reverseList(ListNode head) {
            if(head==null || head.next==null) return head;

            ListNode newHead = reverseList(head.next);
            ListNode front = head.next;
            front.next = head;
            head.next = null;

            return newHead;
        }
    }
}
