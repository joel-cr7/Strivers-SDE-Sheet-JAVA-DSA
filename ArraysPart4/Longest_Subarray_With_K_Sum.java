package ArraysPart4;

import java.util.*;


// Problem link: https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1
//Note: Below code assumes k=0


public class Longest_Subarray_With_K_Sum {

    /** Brute Force Approach:
     * Use nested loops to get all subarrays and keep track of sum of elements in each subarray
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        int maxLen(int arr[]) {
            // assuming k=0
            int k = 0;
            int maxLen = Integer.MIN_VALUE;

            for(int i=0;i<arr.length;i++){
                int tempLen = 0;
                int sum = 0;
                for(int j=i;j<arr.length;j++){
                    sum += arr[j];
                    if(sum==k){
                        tempLen = j-i+1;    // count the no. of elements between j and i
                    }
                }
                maxLen = Math.max(maxLen, tempLen);
            }

            return maxLen;
        }
    }



    /** Optimal Approach:
     * First, let us initialize a variable say sum = 0 which stores the sum of elements traversed so far
     * and another variable says max = 0 which stores the length of the longest subarray with sum zero.
     *
     * Declare a HashMap<Integer, Integer> which stores the prefix sum of every element as a key and its index as a value.
     * Now traverse the array, and add the array element to our sum.
     *  (i)  If sum = 0, then we can say that the subarray until the current index has a sum = 0,
     *  so we update max with the maximum value of (max, current_index+1)
     *
     * (ii)  If the sum is not equal to zero then we check the hashmap if we’ve seen a subarray with this sum before
     *
     * if HashMap contains sum -> this is where the above-discussed case occurs (subarray with equal sum), so we update our max
     *
     * else -> Insert (sum, current_index) into hashmap to store prefix sum until the current index
     *
     * After traversing the entire array, max variable has the length of the longest substring having a sum equal to k
     * NOTE: we do not update the index of a sum if it’s seen again because we require the length of the longest subarray
     *
     * Video solution: https://www.youtube.com/watch?v=frf7qxiN2qU
     * */

    // Time Complexity: O(n)
    // Space complexity: O(n)

    static class Solution2 {
        int maxLen(int arr[]) {
            int k = 0;

            Map<Integer, Integer> mp = new HashMap<>();

            int prefixSum = 0;
            int maxLen = 0;

            for(int i=0;i<arr.length;i++){
                prefixSum += arr[i];

                // if at any index we by chance get the subarray sum as k then consider it if its greater than maxLen
                if(prefixSum == k){
                    maxLen = Math.max(maxLen, i+1);
                }

                int remaining = prefixSum - k;

                if(mp.containsKey(remaining)){
                    maxLen = Math.max(maxLen, i-mp.get(remaining));
                }

                // this is to handle -ve elements in array, if already in map dont add again bcz we need the longest subarray
                // ie. keep track of leftmost index
                if(!mp.containsKey(prefixSum)){
                    mp.put(prefixSum, i);
                }
            }

            return maxLen;
        }
    }



    // similar problem: https://leetcode.com/problems/subarray-sum-equals-k/description/
    // Above problem we needed to count the length of longest subarray here we need to count total subarrays with sum k

    // approach is to store the frequency of prefixSum that we get

    // Video solution: https://www.youtube.com/watch?v=xvNwoz-ufXA

    static class Solution {
        public int subarraySum(int[] nums, int k) {
            int ans = 0;

            Map<Integer, Integer> mp = new HashMap<>();
            int prefixSum = 0;

            for(int i=0;i<nums.length;i++){
                prefixSum += nums[i];

                // if at any index we by chance get the subarray sum as k then count it
                if(prefixSum==k) ans++;

                int remaining = prefixSum - k;
                if(mp.containsKey(remaining)){
                    ans += mp.get(remaining);
                }

                mp.put(prefixSum, mp.getOrDefault(prefixSum, 0)+1);
            }

            return ans;
        }
    }
}
