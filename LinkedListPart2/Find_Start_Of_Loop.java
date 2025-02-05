package LinkedListPart2;

import java.util.*;


//Problem link: https://leetcode.com/problems/linked-list-cycle-ii/description/


public class Find_Start_Of_Loop {

    /** Bruteforce Approach:
     *  Map to store nodes and check if nodes is previously visited or not
     *  */

    // Time Complexity: O(n)
    // Space complexity: O(n)

    public static class Solution1 {
        public ListNode detectCycle(ListNode head) {
            Map<ListNode, Integer> mp = new HashMap<>();
            ListNode temp = head;
            while(temp!=null){
                if(mp.containsKey(temp)){
                    return temp;
                }
                mp.put(temp, 1);
                temp = temp.next;
            }
            return null;
        }
    }




    /** Optimal Approach:
     *  Use floyds hare and tortoise algo
     *  */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    public static class Solution2 {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head, slow = head;
            while(fast!=null && fast.next!=null){
                fast = fast.next.next;
                slow = slow.next;
                if(fast == slow){
                    slow = head;
                    while(fast!=slow){
                        fast = fast.next;
                        slow = slow.next;
                    }
                    return fast;
                }
            }
            return null;
        }
    }
}
