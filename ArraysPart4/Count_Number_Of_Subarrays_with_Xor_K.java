package ArraysPart4;

import java.util.*;


//Problem link: https://www.geeksforgeeks.org/problems/count-subarray-with-given-xor/1


public class Count_Number_Of_Subarrays_with_Xor_K {

    /** Brute Force Approach:
     * Use nested loops to get all subarrays and keep track of xor of elements in each subarray
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        public long subarrayXor(int arr[], int k) {
            long ans = 0;

            for(int i=0;i<arr.length;i++){
                int xor = 0;
                for(int j=i;j<arr.length;j++){
                    xor ^= arr[j];
                    if(xor==k)
                        ans++;
                }
            }

            return ans;
        }
    }




    /** Optimal Approach:
     * Store the frequency of prefix_xor that we get
     *
     * Observation:
     * Eg: [n1, n2, n3, n4, n5] , k = n
     * suppose xor of 1st part of array ie. from [n1 to n2] is x
     * suppose xor of 2nd part of array is k (the one which we are searching) ie. from [n3 to n5]
     * suppose the prefix xor from [n1 to n5] is xr
     * So we can say, xr = x ^ k
     * taking xor of k on both sides we get: xr ^ k = x ^ k ^ k
     * NOTE: xor of two same elements is 0
     * so we get: xr ^ k = x
     * Hence, the xor of first part of the array must be xr ^ k, for the xor of the other part of the array to be k
     * (similar logic to Longest_Subarray_With_K_Sum problem)
     *
     * Declare a HashMap<Integer, Integer> which stores the prefix xor of every element as a key and its count as a value.
     * Now traverse the array, and add the array element to our prefix_xor.
     *
     * Video solution: https://www.youtube.com/watch?v=eZr-6p0B7ME
     * */

    // Time Complexity: O(n)
    // Space complexity: O(n)

    static class Solution2 {
        public long subarrayXor(int arr[], int k) {
            long ans = 0;

            Map<Integer, Integer> mp = new HashMap<>();
            int prefix_xor = 0;

            for(int i=0;i<arr.length;i++){
                prefix_xor ^= arr[i];

                // if at any index we by chance get the subarray xor as k then count it
                if(prefix_xor == k) ans++;

                int remaining = prefix_xor ^ k;
                if(mp.containsKey(remaining)){
                    ans += mp.get(remaining);
                }

                mp.put(prefix_xor, mp.getOrDefault(prefix_xor,0)+1);
            }

            return ans;
        }
    }
}
