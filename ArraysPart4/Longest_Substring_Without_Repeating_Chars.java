package ArraysPart4;

import java.util.*;


// Problem link: https://leetcode.com/problems/longest-substring-without-repeating-characters/description/


public class Longest_Substring_Without_Repeating_Chars {

    /** Brute Force Approach:
     * Use nested loops to find a  possible substrings
     * Keep track of non repeating substring using set
     * */

    // Time Complexity: O(n^3)
    // Space complexity: O(1)

    static class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int res = 0;

            // outer loop for traversing the string
            for(int i=0;i<s.length();i++){
                Set<Character> set = new HashSet<>();

                // nested loop for getting different substrings starting with s[i]
                for(int j=i;j<s.length();j++){
                    // if element if found, break the loop
                    if(set.contains(s.charAt(j))){
                        break;
                    }
                    set.add(s.charAt(j));
                }

                res = Math.max(res, set.size());        // get the max size
            }
            return res;
        }
    }





    /** Optimal Approach:
     * Use sliding window approach
     * 1) Use 2 pointers left and right to represent the sliding window
     * 2) Traverse through each element and use a set to check repeating elements
     *      If element not found in set, add it to set and increase size of window from right
     *      If element found in set, remove elements from set till duplicate element is not removed and decrease
     *      window size from left
     *
     *
     * video solution: https://www.youtube.com/watch?v=qtVh-XEpsJo&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=27
     * */

    // Time Complexity: O(2n)
    // Space complexity: O(n)

    static class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int res = 0;

            Set<Character> set = new HashSet<>();
            int left = 0, right = 0;

            for(int i=0;i<s.length();i++){
                // if we get unique elements increase size of window
                if(!set.contains(s.charAt(i))){
                    set.add(s.charAt(i));
                    right++;
                }
                else{
                    // if we get duplicate elements decrease size of window
                    while(set.contains(s.charAt(i))){
                        set.remove(s.charAt(left));
                        left++;
                    }
                    set.add(s.charAt(i));
                }
                res = Math.max(res, set.size());
            }

            return res;
        }
    }
}
