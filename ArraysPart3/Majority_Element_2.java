package ArraysPart3;

import java.util.*;


// Problem link: https://leetcode.com/problems/majority-element-ii/description/

/**
 * By observation, in the given array, there can be a maximum of two integers that can occur more than n/3 times.
 * Suppose n=9 so 9/3=3, for element to be majority element it should occur > 3 times ie. 4 times.
 * So there cannot be more than 2 such elements that occur >3 times,
 * bcz if we consider 3 such elements then 4+4+4 = 12 elements but n=9 so it cant accommodate 12 elements
 * */


public class Majority_Element_2 {

    /** Brute Force Approach:
     * Loop over elements and count the elements occurrence
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            for(int num: nums){
                if(ans.contains(num)){
                    continue;
                }
                int cnt = 0;
                for(int temp: nums){
                    if(temp==num)
                        cnt++;
                }
                if(cnt > nums.length/3){
                    ans.add(num);
                    if(ans.size()==2)
                        return ans;
                }
            }
            return ans;
        }
    }



    /** Better Approach:
     * Use Map to store freq. count of elements
     * */

    // Time Complexity: O(n)
    // Space complexity: O(n)

    static class Solution2 {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            Map<Integer, Integer> mp = new HashMap<>();
            for(int num: nums){
                mp.put(num, mp.getOrDefault(num, 0)+1);

                // we want elements with frequency > n/3, so just check one greater
                if(mp.get(num) == (nums.length/3)+1){
                    ans.add(num);
                }
            }
            return ans;
        }
    }



    /** Optimal Approach:
     * Use Moore's Voting algorithm:
     *
     * Initialize 2 variables:
     * Count –  for tracking the count of element
     * ele – for which element we are counting
     *
     * Traverse through the given array.
     *      If Count is 0 then store the current element of the array as ele.
     *      If the current element and ele are the same increase the Count by 1.
     *      If they are different decrease the Count by 1.
     * The integer present in ele should be the result we are expecting
     *
     * Again traverse array and check if it occurs more than n/3 times
     *
     * Video explanation with intuition: https://www.youtube.com/watch?v=Q6L5SoS-fTo
     * **/

    // Time Complexity: O(2n)
    // Space complexity: O(1)

    class Solution {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> ans = new ArrayList();
            int cnt1 = 0, cnt2 = 0;
            int ele1 = -1, ele2 = -1;
            for(int num: nums){
                if(num==ele1){
                    cnt1++;
                }
                else if(num==ele2){
                    cnt2++;
                }
                else if(cnt1==0){
                    ele1 = num;
                    cnt1 = 1;
                }
                else if(cnt2==0){
                    ele2 = num;
                    cnt2 = 1;
                }
                else{
                    cnt1--;
                    cnt2--;
                }
            }

            // verifying if freq of ele1 and ele2 are  > n/3

            int freq1 = 0, freq2 = 0;
            for(int num: nums){
                if(num==ele1) freq1++;
                else if(num==ele2) freq2++;
            }

            if(freq1 > nums.length/3) ans.add(ele1);
            if(freq2 > nums.length/3) ans.add(ele2);

            return ans;
        }
    }
}
