package LinkedListPart2;

// Problem link: https://leetcode.com/problems/reverse-nodes-in-k-group/description/


public class Reverse_Nodes_In_K_Group {

    /** Optimal Approach:
     *  Use pointer to traverse k group at a time, and reverse each time
     *  Use other pointers to keep track of further linkedlist
     *  */

    // Time Complexity: O(2n)
    // Space complexity: O(1)

    static class Solution1 {

        private static ListNode reverse(ListNode head){
            if(head==null || head.next==null) return head;

            ListNode prev = null;
            ListNode curr = head;
            ListNode nextNode = curr.next;
            while(nextNode!=null){
                curr.next = prev;
                prev = curr;
                curr = nextNode;
                nextNode = nextNode.next;
            }
            curr.next = prev;
            return curr;
        }

        private static ListNode getKthNode(ListNode node, int k){
            ListNode temp = node;
            int i=1;
            while(i!=k){
                if(temp==null) return null;
                temp = temp.next;
                i++;
            }
            return temp;
        }

        public ListNode reverseKGroup(ListNode head, int k) {
            if(head==null || head.next==null) return head;

            ListNode start = head;
            ListNode nextNode = null;       // to keep track of further part of linkedlist
            ListNode prevNode = null;

            while(start!=null){
                ListNode kthNode = getKthNode(start, k);
                if(kthNode == null){
                    if(prevNode!=null)
                        prevNode.next = start;
                    break;
                }
                nextNode = kthNode.next;
                kthNode.next = null;
                reverse(start);

                if(start==head){
                    head = kthNode;
                }
                else{
                    prevNode.next = kthNode;
                }
                prevNode = start;
                start = nextNode;
            }
            return head;
        }
    }
}
