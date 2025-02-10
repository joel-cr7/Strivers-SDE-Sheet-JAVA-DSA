package LinkedListAndArrays;

// Problem link: https://leetcode.com/problems/max-consecutive-ones/description/

public class Maximum_Consecutive_Ones {

    /** Optimal approach
     * Iterate through array and store max consecutive
     */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution1 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int maxOnes = Integer.MIN_VALUE;
            int currCnt = 0;

            for(int i=0;i<nums.length;i++){
                if(nums[i]==1){
                    currCnt++;
                    maxOnes = Math.max(maxOnes, currCnt);
                }
                else{
                    currCnt = 0;
                }
            }

            return maxOnes==Integer.MIN_VALUE ? 0 : maxOnes;
        }
    }
}
