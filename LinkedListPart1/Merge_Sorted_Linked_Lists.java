package LinkedListPart1;

import java.util.*;


// Problem link: https://leetcode.com/problems/merge-two-sorted-lists/description/


public class Merge_Sorted_Linked_Lists {

    /** Brute Force Approach:
     * Use extra array space to store all linked list elements
     * Sort the elements
     * Create new linked list from sorted elements
     * */

    // Time Complexity: O(2N) + O(NlogN)
    // Space complexity: O(N)
    // where: N is the length of list1 + list2

    static class Solution1 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if(list1==null) return list2;
            if(list2==null) return list1;

            ListNode dummyNode = new ListNode(-1);
            ListNode ptr1 = list1, ptr2 = list2;

            ListNode temp = dummyNode;

            List<Integer> lst = new ArrayList<>();
            while(ptr1!=null){
                lst.add(ptr1.val);
                ptr1 = ptr1.next;
            }
            while(ptr2!=null){
                lst.add(ptr2.val);
                ptr2 = ptr2.next;
            }

            Collections.sort(lst);

            for(int num: lst){
                temp.next = new ListNode(num);
                temp = temp.next;
            }

            return dummyNode.next;
        }
    }




    /** Optimal Approach:
     * Merge the linked lists in-place, without creating new nodes
     * */

    // Time Complexity: O(N)
    // Space complexity: O(1)
    // where: N is the length of list1 + list2

    static class Solution2 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if(list1==null) return list2;
            if(list2==null) return list1;

            ListNode dummyNode = new ListNode(-1);

            ListNode ptr1 = list1, ptr2 = list2;
            ListNode ptr3 = dummyNode;

            while(ptr1!=null && ptr2!=null){
                if(ptr1.val <= ptr2.val){
                    ptr3.next = ptr1;
                    ptr1 = ptr1.next;
                }
                else{
                    ptr3.next = ptr2;
                    ptr2 = ptr2.next;
                }
                ptr3 = ptr3.next;
            }

            if(ptr1!=null){
                ptr3.next = ptr1;
            }
            if(ptr2!=null){
                ptr3.next = ptr2;
            }

            return dummyNode.next;
        }
    }

}
