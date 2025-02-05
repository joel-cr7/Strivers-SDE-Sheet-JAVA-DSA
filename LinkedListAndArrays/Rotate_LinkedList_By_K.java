package LinkedListAndArrays;


// Problem link: https://leetcode.com/problems/rotate-list/description/


public class Rotate_LinkedList_By_K {

    // Optimal Approach:

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution1 {
        private static ListNode findTail(ListNode head){
            ListNode temp = head;
            while(temp.next!=null){
                temp = temp.next;
            }
            return temp;
        }

        private static int findLength(ListNode head){
            ListNode temp = head;
            int i = 0;
            while(temp!=null){
                temp = temp.next;
                i++;
            }
            return i;
        }

        private static ListNode getNodeAtPos(ListNode head, int m){
            ListNode temp = head;
            int pos = 1;
            while(pos!=m){
                temp = temp.next;
                pos++;
            }
            return temp;
        }

        public ListNode rotateRight(ListNode head, int k) {
            if(head==null || head.next==null) return head;

            int n = findLength(head);
            int noOfRotations = k%n;

            // if k is a multiple of the length of linkedlist ie. n, then after all rotations each node will
            // be at same place as start
            if(noOfRotations != 0){
                ListNode tail = findTail(head);
                tail.next = head;
                ListNode kthNode = getNodeAtPos(head, n-noOfRotations);
                head = kthNode.next;
                kthNode.next = null;
            }

            return head;
        }
    }
}
