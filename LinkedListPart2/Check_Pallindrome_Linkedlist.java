package LinkedListPart2;

import java.util.*;


//Problem link: https://leetcode.com/problems/palindrome-linked-list/description/


public class Check_Pallindrome_Linkedlist {

    /** Bruteforce Approach:
     *  Use stack to store data of linkedlist in reverse order
     *  and again traverse linkedlist and pop from stack to check
     *  */

    // Time Complexity: O(2n)
    // Space complexity: O(n)

    static class Solution1{
        public boolean isPalindrome(ListNode head) {
            if(head==null || head.next==null) return true;

            Stack<Integer> st = new Stack();
            ListNode temp = head;
            while(temp!=null){
                st.add(temp.val);
                temp = temp.next;
            }
            temp = head;
            while(temp!=null){
                if(temp.val!=st.pop()){
                    return false;
                }
                temp = temp.next;
            }
            return true;
        }
    }




    /** Optimal Approach:
     *  find middle of Linkedlist and reverse from that point
     *  compare and return result
     *  Note: before returning final answer undo the reverse of linkedlist
     *  */

    // Time Complexity: O(2n)
    // Space complexity: O(1)

    static class Solution2 {

        private static ListNode findMiddle(ListNode head){
            ListNode fast = head, slow = head;
            while(fast!=null && fast.next!=null && fast.next.next!=null){
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        private static ListNode reverse(ListNode head){
            if(head==null || head.next==null) return head;

            ListNode prev = null;
            ListNode curr = head;
            ListNode nextNode = head.next;
            while(curr.next!=null){
                curr.next = prev;
                prev = curr;
                curr = nextNode;
                nextNode = nextNode.next;
            }
            curr.next = prev;
            return curr;
        }

        public boolean isPalindrome(ListNode head) {
            if(head==null || head.next==null) return true;

            ListNode mid = findMiddle(head);
            ListNode newHead = reverse(mid.next);

            ListNode ptr1 = head;
            ListNode ptr2 = newHead;
            while(ptr2!=null){
                if(ptr2.val!=ptr1.val){
                    reverse(newHead);
                    return false;
                }
                ptr2 = ptr2.next;
                ptr1 = ptr1.next;
            }
            reverse(newHead);
            return true;
        }
    }
}
