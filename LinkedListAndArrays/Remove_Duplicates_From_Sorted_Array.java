package LinkedListAndArrays;

import java.util.*;


// Problem link: https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/


public class Remove_Duplicates_From_Sorted_Array {

    /** Brute force approach
     * Use set to eliminate duplicates
     */

    // Time Complexity: O(nlogn) -> logn for insertion into TreeSet
    // Space complexity: O(n)

    static class Solution1 {
        public int removeDuplicates(int[] nums) {
            Set<Integer> set = new TreeSet<>();
            for(int i: nums){
                set.add(i);
            }
            int itr = 0;
            for(int i: set){
                nums[itr] = i;
                itr++;
            }
            return set.size();
        }
    }


    /** Optimal approach
     * use 2-pointers
     */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution2 {
        public int removeDuplicates(int[] nums) {
            int ptr1 = 0;
            int ptr2 = 1;
            while(ptr2 < nums.length){
                if(nums[ptr1]!=nums[ptr2]){
                    ptr1++;
                    nums[ptr1] = nums[ptr2];
                }
                ptr2++;
            }
            return ptr1+1;
        }
    }
}
