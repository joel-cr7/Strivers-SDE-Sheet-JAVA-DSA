package ArraysPart2;


//Problem link: https://leetcode.com/problems/missing-number/description/

// In addition to the question we also need to find the repeating element in the array


public class Repeating_And_Missing_Number {

    /** Brute Force Approach:
     * We know numbers in nums are between [0,n], use nested for loops to find the missing and repeating number
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        public int[] missingNumber(int[] nums){
            int missingNum = -1, repeatingNum = -1;
            for(int i=0;i<=nums.length;i++){
                int counter = 0;
                for(int j=0;j<nums.length;j++){
                    if(i == nums[j]){
                        counter++;
                    }
                }
                if(counter==2){
                    repeatingNum = i;
                }
                else if(counter==1){
                    missingNum = i;
                }
                if(missingNum!=-1 && repeatingNum!=-1){
                    break;
                }
            }
            return new int[]{missingNum, repeatingNum};
        }
    }



    /** Better Approach:
     * Use hashing to maintain count of each element and later check for missing and repeating element
     * **/

    // Time Complexity: O(2n)
    // Space complexity: O(n)

    static class Solution2 {
        public int[] missingNumber(int[] nums) {
            int missingNum = -1, repeatingNum = -1;
            int hashArr[] = new int[nums.length+1];

            for(int i=0;i<nums.length;i++){
                hashArr[nums[i]]++;
            }

            for(int i=0;i<hashArr.length;i++){
                if(hashArr[i]==0){
                    missingNum = i;
                }
                else if(hashArr[i]==2){
                    repeatingNum = i;
                }
                if(missingNum!=-1 && repeatingNum!=-1){
                    break;
                }
            }

            return new int[]{missingNum, repeatingNum};
        }
    }



    /** Optimal Approach:
     * Convert the given problem into mathematical equations. Since we have two variables i.e. missing and repeating,
     * try to form two linear equations. And find values of two variables using those equations.

     * Assume the repeating number = X and the missing number = Y.
     * Sn = (N*(N+1))/2    (Sum of the first N numbers)
     * S = sum of array elements.
     * Therefore, X - Y = S - Sn …………………equation 1

     * S2n = (N*(N+1)*(2N+1))/6     (Sum of squares of the first N numbers)
     * S2 = sum of squares of array elements.
     * Therefore, X^2 - Y^2 = S2 - S2n...................equation 2

     * expanding equation 2 as (x^2-y^2) = (x+y)(x-y) we can conclude,
     * X+Y = (S2 - S2n) / (X-Y)     [From equation 1, we get the value X-Y] ………… equation 3

     * From equation 1 and equation 3, we can easily find the value of X and Y.
     * **/

    // Time Complexity: O(m+n)
    // Space complexity: O(1)

    static class Solution3 {
        public int[] missingNumber(int[] nums) {
            int n = nums.length;
            long S = 0, S2 = 0;

            for (int num : nums) {
                S += num;   // sum of array elements
                S2 += ((long) num * num);      // sum of squares of array elements
            }

            long Sn = ((long) n *(n+1))/2;
            long S2n = (n*(n+1)*(2L * n+1))/6;

            long val1 = S - Sn;      // equation 1
            long val2 = (S2 - S2n) / val1;      // equation 3

            // solving equation x-y = val1 and x+y = val2 we get 2x = constant1 + constant2
            // therefore x = (constant1 + constant2) / 2   and    y = x-val1

            long x = (val1 + val2) / 2;
            long y = x - val1;

            return new int[]{(int) y, (int) x};     // y is missing num and x is repeating num

        }
    }
}
