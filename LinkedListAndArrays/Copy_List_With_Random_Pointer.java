package LinkedListAndArrays;

import java.util.*;


// Problem link: https://leetcode.com/problems/copy-list-with-random-pointer/


class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Copy_List_With_Random_Pointer {

    /** Optimal Approach:
     *  We cant create copy of nodes normally as we also have random pointers which point to any node and it may
     *  happen that the node is not yet created.
     *  So we only create deep copy of the node value and store it in HashMap (deep copy is new node from original node and not just pointer to original node)
     *  later traverse through original LL and map to next and random pointers in copied nodes.
     */

    // Time Complexity: O(n)
    // Space complexity: O(n)

    static class Solution1 {
        public Node copyRandomList(Node head) {
            if(head==null) return head;

            Node dummyNode = new Node(-1);
            Node temp = head;

            Map<Node, Node> mp = new HashMap<>();
            while(temp!=null){
                mp.put(temp, new Node(temp.val));
                temp = temp.next;
            }

            temp = head;
            while(temp!=null){
                Node copyNode = mp.get(temp);
                if(temp==head)
                    dummyNode.next = copyNode;
                copyNode.next = mp.getOrDefault(temp.next, null);
                copyNode.random = mp.getOrDefault(temp.random, null);
                temp = temp.next;
            }

            return dummyNode.next;
        }
    }
}
