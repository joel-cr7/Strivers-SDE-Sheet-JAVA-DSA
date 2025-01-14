package ArraysPart2;

import java.util.*;


//Problem link: https://leetcode.com/problems/find-the-duplicate-number/description/


public class Find_Duplicate_In_Nplus1 {

    /** Brute Force Approach:
     * Sort array and compare to find duplicate
     * **/

    // Time Complexity: O(nlogn)
    // Space complexity: O(1)

    static class Solution1 {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            int duplicate = 0;
            for(int i=0;i<nums.length-1;i++){
                if(nums[i]==nums[i+1]){
                    duplicate = nums[i];
                    break;
                }
            }
            return duplicate;
        }
    }



    /** Better Approach:
     * Use hashing to find duplicates
     * **/

    // Time Complexity: O(n)
    // Space complexity: O(n)

    static class Solution2 {
        public int findDuplicate(int[] nums) {
            Map<Integer, Integer> mp = new HashMap<>();
            int duplicate = 0;
            for (int num : nums) {
                if (mp.containsKey(num)) {
                    duplicate = num;
                    break;
                } else {
                    mp.put(num, 1);
                }
            }
            return duplicate;
        }
    }



    /** Optimal Approach:
     * Use Floyd's Hare and Tortoise algorithm. We treat the array as a linked list, where the index represents the node
     * and the value at that index represents the next node.
     * **/

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution3 {
        public int findDuplicate(int[] nums) {
            int slowPtr = nums[0];
            int fastPtr = nums[0];

            do {
                slowPtr = nums[slowPtr];
                fastPtr = nums[nums[fastPtr]];
            } while(fastPtr != slowPtr);

            slowPtr = nums[0];
            while(slowPtr != fastPtr){
                slowPtr = nums[slowPtr];
                fastPtr = nums[fastPtr];
            }

            return slowPtr;
        }
    }
}
