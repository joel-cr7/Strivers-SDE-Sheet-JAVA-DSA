package LinkedListPart2;

import java.util.*;


//Problem link: https://leetcode.com/problems/intersection-of-two-linked-lists/description/


public class Intersection_Of_Linked_List {

    /** Brute force Approach:
     * Iterate through lists, and use hashing (hashset) to store the nodes
     * */

    // Time Complexity: O(m+n)
    // Space complexity: O(m+n)

    public static class Solution1 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> set = new HashSet<>();

            ListNode temp = headA;
            while(temp!=null){
                set.add(temp);
                temp = temp.next;
            }

            temp = headB;
            while(temp!=null){
                if(set.contains(temp)) return temp;
                set.add(temp);
                temp = temp.next;
            }

            return null;
        }
    }



    /** Better Approach:
     * By observation if we start traversing both linkedlists from the same length from start it will collide at
     * common node (if there is a collision)
     *
     * Find the length of both lists.
     * Find the positive difference between these lengths.
     * Move the dummy pointer of the larger list by the difference achieved. This makes our search length reduced to a smaller list length.
     * Move both pointers, each pointing two lists, ahead simultaneously if both do not collide.
     *
     *
     * */

    // Time Complexity: O(m+n)
    // Space complexity: O(1)

    public static class Solution2 {
        private static int findLength(ListNode head){
            int len = 0;
            while(head!=null){
                len++;
                head = head.next;
            }
            return len;
        }

        private static ListNode checkIntersection(ListNode ptr1, ListNode ptr2){
            while(ptr1!=null && ptr2!=null){
                if(ptr1==ptr2) return ptr1;
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }
            return null;
        }

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode ptr1 = headA;
            ListNode ptr2 = headB;
            int m = findLength(ptr1);
            int n = findLength(ptr2);

            if(m>n){
                int diff = m-n;
                ptr1 = headA;
                while(diff!=0){
                    ptr1 = ptr1.next;
                    diff--;
                }
                return checkIntersection(ptr1, ptr2);
            }
            else{
                int diff = n-m;
                ptr2 = headB;
                while(diff!=0){
                    ptr2 = ptr2.next;
                    diff--;
                }
                return checkIntersection(ptr1, ptr2);
            }
        }
    }




    /** Optimal Approach:
     * The difference of length method requires various steps to work on it. Using the same concept of difference of length,
     * a different approach can be implemented. The process is as follows:-
     *
     * Take two dummy nodes for each list. Point each to the head of the lists.
     * Iterate over them. If anyone becomes null, point them to the head of the opposite lists and continue iterating until they collide.
     * */

    // Time Complexity: O(n1+n2)
    // Space complexity: O(1)

    public static class Solution3 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if(headA==null || headB==null) return null;

            ListNode ptr1 = headA;
            ListNode ptr2 = headB;

            while(ptr1!=ptr2){
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;

                if(ptr1==ptr2) return ptr1;

                if(ptr1==null) ptr1 = headB;
                if(ptr2==null) ptr2 = headA;
            }

            return ptr1;
        }
    }
}
