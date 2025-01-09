package Arrays;

public class Kadanes_Algorithm {

    /** Bruteforce approach:
     * Generate all possible sub-arrays using 2 nested loops
     * Use third loop to calculate sum of all elements in the sub-array
     * */

    // Time complexity: ~O(n^3)
    // Space complexity: O(1)

    static class Solution1 {
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            for(int i=0;i<nums.length;i++){
                for(int j=i;j<nums.length;j++){
                    int sum = 0;
                    for(int k=i;k<=j;k++){
                        sum += nums[k];
                    }
                    maxSum = Math.max(maxSum, sum);
                }
            }
            return maxSum;
        }
    }



    /** Better approach:
     * Remove the third nested loop by storing current sum in a variable,
     * use this variable to calculate further sub array sum
     * */

    // Time complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution2 {
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            int n = nums.length;
            for(int i=0;i<n;i++){
                int currSum = 0;
                for(int j=i;j<n;j++){
                    currSum += nums[j];
                    maxSum = Math.max(maxSum, currSum);
                }
            }
            return maxSum;
        }
    }



    /** Optimal approach:
     * Use Greedy approach by keeping track of current sum
     * if current sum becomes negative, it wont add value to find max sum. So re-initialize to 0
     * */

    // Time complexity: O(n)
    // Space complexity: O(1)

    static class Solution3 {
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            int currSum = 0;
            int maxIfNegative = Integer.MIN_VALUE;
            for(int i=0;i<nums.length;i++){
                currSum += nums[i];

                // greedily drop the negative sum as it will further reduce the maxSum
                if(currSum < 0){
                    currSum = 0;
                }
                if(nums[i] > maxIfNegative){
                    maxIfNegative = nums[i];
                }
                maxSum = Math.max(maxSum, currSum);
            }
            if(maxIfNegative < 0)
                return maxIfNegative;

            return maxSum;
        }
    }


    /** Follow-up question: print the sub-array with max sum
     * Use 2 variables to store the start and end of the array
     * reset start each time current sum becomes 0
     * */

    static class Solution4 {
        public int maxSubArray(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            int currSum = 0;
            int maxIfNegative = Integer.MIN_VALUE;
            int start = 0;
            int ansStart = -1, ansEnd = -1;
            for(int i=0;i<nums.length;i++){
                if(currSum == 0) start = i;
                currSum += nums[i];

                // greedily drop the negative sum as it will further reduce the maxSum
                if(currSum < 0){
                    currSum = 0;
                }
                if(nums[i] > maxIfNegative){
                    maxIfNegative = nums[i];
                }
                if(currSum > maxSum){
                    ansStart = start;
                    ansEnd = i;
                    maxSum = currSum;
                }
            }
            if(maxIfNegative < 0)
                return maxIfNegative;

            return maxSum;
        }
    }

}
