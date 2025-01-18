package ArraysPart3;


// Problem link: https://leetcode.com/problems/powx-n/description/


public class Pow_x_n {

    /** Brute Force Approach:
     * Calculate x^n using for loop
     * */

    // Time Complexity: O(n)
    // Space complexity: O(1)

    static class Solution1 {
        public double myPow(double x, int n) {
            double ans = 1.0;
            for(int i=0;i<Math.abs(n);i++){
                ans = x*ans;
            }
            if(n < 0){
                ans = 1 / ans;
            }
            return ans;
        }
    }




    /** Optimal Approach:
     * Use binary exponentiation approach
     *
     * Eg: x = 2 and n=10  ==>  2^10
     * 1. 2^10 ==> (2*2)^5  ==> 4^5     (even power)
     * 2. 4^5  ==>  4 * (4)^4       (odd power)
     * 3. 4^4  ==> (4*4)^2  ==> 16^2
     * 4. 16^2  ==> (16*16)^1  ==> 256^1
     * 5. 256^1  ==>  256 * 256^0
     *
     * If we get odd power multiple the number into ans and reduce power by 1
     * If we get even power multiply number with itself and divide power by 2
     * **/

    // Time Complexity: O(log(n))
    // Space complexity: O(1)

    static class Solution2 {
        public double myPow(double x, int n) {
            double ans = 1.0;
            long pow = n;    // Use long to handle edge case of Integer.MIN_VALUE overflow

            if (pow < 0) {
                pow = -1*pow; // Convert pow to positive
            }

            while(pow>0){
                // odd pow
                if(pow%2 != 0){
                    ans = ans*x;
                    pow = pow-1;
                }
                //even pow , 2^10 -> (2*2)^10/2 ie. (2*2)^5
                else{
                    x = x*x;
                    pow /= 2;
                }
            }
            return n<0 ? 1 / ans : ans; // For negative pows, take reciprocal;
        }
    }
}
