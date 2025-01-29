package LinkedListPart1;

public class Add_Two_numbers {

    /** Optimal Approach:
     * Iterate through lists, add numbers and maintain carry
     * */

    // Time Complexity: O(N)
    // Space complexity: O(1)
    // where: N is the length of list1 + list2

    static class Solution1 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;
            ListNode ptr1 = l1;
            ListNode ptr2 = l2;

            ListNode dummyNode = new ListNode(-1);
            ListNode ptr3 = dummyNode;

            while(ptr1!=null || ptr2!=null){
                int sum = 0;
                sum += ptr1!=null ? ptr1.val : 0;
                sum += ptr2!=null ? ptr2.val : 0;
                sum += carry;

                carry = sum/10;

                ptr3.next = new ListNode(sum%10);

                ptr1 = ptr1!=null ? ptr1.next : null;
                ptr2 = ptr2!=null ? ptr2.next : null;
                ptr3 = ptr3.next;
            }

            if(carry!=0){
                ptr3.next = new ListNode(carry);
            }

            return dummyNode.next;
        }
    }
}
