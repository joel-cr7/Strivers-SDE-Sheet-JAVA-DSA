package ArraysPart3;

import java.util.*;


// Problem link: https://leetcode.com/problems/majority-element/description/


public class Majority_Element1 {

    /** Brute Force Approach:
     * Use Map to store freq. count of elements
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        public int majorityElement(int[] nums) {
            int ans = -1;
            int n = nums.length;

            for(int num: nums){
                int cnt = 0;
                for(int temp: nums){
                    if(num == temp)
                        cnt++;
                }
                if(cnt > n/2){
                    ans = num;
                    break;
                }
            }

            return ans;
        }
    }



    /** Better Approach:
     * Use Map to store freq. count of elements
     * */

    // Time Complexity: O(2n)
    // Space complexity: O(n)

    static class Solution2 {
        public int majorityElement(int[] nums) {
            int ans = -1;
            int n = nums.length;
            Map<Integer, Integer> mp = new HashMap<>();

            for(int num: nums){
                mp.put(num, mp.getOrDefault(num, 0) + 1);
            }

            for(int key: mp.keySet()){
                if(mp.get(key) > n/2){
                    ans = key;
                    break;
                }
            }

            return ans;
        }
    }




    /** Optimal Approach:
     * If the array contains a majority element, its occurrence must be greater than the floor(N/2).
     *
     * Use Moore's Voting algorithm:
     *
     * We can say that the count of minority elements and majority elements is equal up to a certain point in the array.
     * So when we traverse through the array we try to keep track of the count of elements and the element itself for
     * which we are tracking the count.
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
     * Again traverse array and check if it occurs more than n/2 times
     *
     * Eg: [2, 2, 1, 1, 1, 2, 2]
     * Assume ele = 2, cnt=1 at index 0
     * ele = 2, cnt=2 at index 1
     * ele = 2, cnt=1 at index 2 (reduce cnt by 1 as element at index 2 is different from ele)
     * ele = 2, cnt=0 at index 3 (reduce cnt by 1 as element at index 3 is different from ele)
     * ele = 1, cnt=1 at index 4 (as cnt=0, consider the element at current index ie. 4 to be ele)
     * ele = 1, cnt=0 at index 5 (reduce cnt by 1 as element at index 3 is different from ele)
     * ele = 2, cnt=1 at index 6
     * (at end verify if 2 occurs n/2 times)
     *
     * Video explanation with intuition: https://www.youtube.com/watch?v=Q6L5SoS-fTo
     * **/

    // Time Complexity: O(2n)
    // Space complexity: O(1)

    static class Solution3 {
        public int majorityElement(int[] nums) {
            int ele = -1;
            int cnt = 0;        //
            for(int i=0;i<nums.length;i++){
                if(cnt==0){
                    ele = nums[i];
                    cnt = 1;
                }
                else if(nums[i] == ele){
                    cnt++;
                }
                else{
                    cnt--;
                }
            }

            int counter = 0;
            for(int num: nums){
                if(num==ele) counter++;
            }

            if(counter > nums.length/2)
                return ele;

            return -1;
        }
    }
}
