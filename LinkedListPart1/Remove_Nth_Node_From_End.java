package LinkedListPart1;

public class Remove_Nth_Node_From_End {

    /** Optimal Approach:
     * Use 2-pointers (fast and slow) and maintain n-nodes difference between the 2 pointers by initially moving
     * fast pointer n positions and then moving both by 1 step till end
     * */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution2 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if(head.next == null) return null;

            ListNode fastPtr = head;
            ListNode slowPtr = head;

            // move fastPtr n steps
            for(int i=0;i<n;i++){
                fastPtr = fastPtr.next;
            }

            // if fastPtr reaches end, means we have to delete first node
            // Eg: if 1->2->3 and n=3, we need to delete node 1 which is start
            if(fastPtr==null){
                return head.next;
            }

            while(fastPtr != null && fastPtr.next != null){
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next;
            }

            ListNode toDelete = slowPtr.next;

            slowPtr.next = slowPtr.next.next;
            toDelete.next = null;

            return head;
        }
    }
}
