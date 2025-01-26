package ArraysPart4;

import java.util.*;


// Problem link: https://leetcode.com/problems/longest-consecutive-sequence/


public class Longest_Consecutive_Sequence {

    /** Brute Force Approach:
     * Use nested loops and linear search in the array to find the next element in sequence
     * Keep track of current count of element sequence and longest sequence
     * */

    // Time Complexity: O(n^3)
    // Space complexity: O(1)

    static class Solution1 {
        private static boolean linearSearch(int[] nums, int ele){
            for (int num : nums) {
                if (num == ele) {
                    return true;
                }
            }
            return false;
        }

        public int longestConsecutive(int[] nums) {
            int longest = 0;
            for(int i=0;i<nums.length;i++){
                int currNum = nums[i];
                int cnt = 0;
                while(linearSearch(nums, currNum) == true){
                    cnt++;
                    currNum++;
                }
                longest = Math.max(longest, cnt);
            }
            return longest;
        }
    }



    /** Better Approach:
     * Sort array to get consecutive elements together
     * Keep track of counter and longest sequence
     *
     * Alternately we can use Treeset as well, which will take same time complexity, but extra space
     * */

    // Time Complexity: O(nlogn + n)
    // Space complexity: O(1)

    static class Solution2 {
        public int longestConsecutive(int[] nums) {
            Arrays.sort(nums);

            int longest = 0;
            int cnt = 1;
            for(int i=0;i<nums.length;i++){
                // for handling duplicates, if current element is same as prev element just move to next
                if(i>0 && nums[i] == nums[i-1]){
                    continue;
                }
                // if current element is 1 greater than prev element, so we got the sequence, just increment counter
                else if(i>0 && nums[i] == nums[i-1]+1){
                    cnt += 1;
                }
                // next element is not in sequence, so reset counter
                else{
                    cnt = 1;
                }
                longest = Math.max(longest, cnt);
            }
            return longest;
        }
    }




    /** Optimal Approach:
     * By observation we know in a consecutive sequence the first number will never have a previous number
     * Eg: 1 2 3 4, so 1 can never have a prev element ie. 0, or else the sequence would start from 0 and not 1
     *
     * Store the array elements in a set to remove duplicates
     * Go through the set. If the number num is the start of a streak (i.e. num-1 is not in the set),
     * then test temp = num+1, num+2, num+3, ... and stop at the first number temp not in the set.
     * Keep a counter to count the streak
     * */

    // Time Complexity: O(n)
    // Space complexity: O(n)

    static class Solution3 {
        public int longestConsecutive(int[] nums) {
            int longest = 0;
            Set<Integer> set = new HashSet<>();
            for(int num: nums){
                set.add(num);
            }

            for(int num: set){
                // only start counting if set does not contain num-1, as we need to start counting from
                // smallest element
                if(!set.contains(num-1)){
                    // as there is no element as num-1 we can start counting the streak from num, so next number to search
                    // for consecutive sequence will be num+1
                    int temp = num+1;
                    int cnt = 1;
                    while(set.contains(temp)){
                        temp++;
                        cnt++;
                    }
                    longest = Math.max(longest, cnt);
                }
            }
            return longest;
        }
    }
}
