package LinkedListPart1;

public class Middle_Of_Linked_List {

    /** Brute Force Approach:
     * Use nested loops to get all subarrays and keep track of xor of elements in each subarray
     * */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution1 {
        public ListNode middleNode(ListNode head) {
            if(head==null || head.next==null) return head;

            int len = 1;
            ListNode temp = head;

            while(temp.next!=null){
                temp = temp.next;
                len++;
            }

            int cnt = 0;
            temp = head;
            while(cnt != len/2){
                temp = temp.next;
                cnt++;
            }

            return temp;
        }
    }




    /** Optimal Approach:
     * Use tortoise and hare algorithm
     * */

    // Time Complexity: O(n/2)
    // Space complexity: O(1)

    static class Solution2 {
        public ListNode middleNode(ListNode head) {
            if(head==null || head.next==null) return head;

            ListNode slow = head, fast = head;
            while(fast!=null && fast.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }
    }
}
