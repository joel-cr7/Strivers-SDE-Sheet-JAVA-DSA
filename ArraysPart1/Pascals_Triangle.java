package ArraysPart1;

import java.util.*;

// Problem link: https://leetcode.com/problems/pascals-triangle/

public class Pascals_Triangle {

    static class Solution {

        // Variation 1: Return the complete Pascal's triangle till the given number of row

        /** Approach:
         * First and last element will always remain 1, loop through the number of rows given
         * Add first and last element as 1, loop for the elements in-between and add the sum of elements from previous row **/

        // Time Complexity ~ O(n^2)
        // Space complexity ~ O(1)

        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> pascalTriangle = new ArrayList<>();
            pascalTriangle.add(new ArrayList<>(List.of(1)));  // initial element will be 1
    
            for(int i=1;i<numRows;i++){
                List<Integer> prevRow = pascalTriangle.get(i-1);
                // i+1 elements in each row with first and last element as 1
                List<Integer> currRow = new ArrayList<>(List.of(1));
                for(int j=1;j<i;j++){
                    currRow.add(prevRow.get(j) + prevRow.get(j-1));
                }
                currRow.add(1);     // last element will be 1
                pascalTriangle.add(currRow);
            }
    
            return pascalTriangle;
        }





        // Variation 2: Return number from Pascal's triangle for a particular row and column

        /** Approach:
         * Apply formula of nCr which is = n! / (r! * (n-r)!)
         * for given row (r) and column (c) apply nCr where n=r-1 and r=c-1 => (r-1)C(c-1) 
         * This will give the element at a row and column
         * Note: Brute force solution here is to directly apply formula by computing factorial, but we can reduce the nCr
         * as well. 
         * Eg1: 5C2 = (5*4)/(2*1)  ie. take digits as per lower bound which is 2
         * Eg2: 7C3 = (7*6*5)/(3*2*1)   only take 3 digits in numerator and denominator **/

        // Time Complexity ~ O(r),  r = no of column
        // Space complexity ~ O(1)

        public int findElementInPascalTriangle(int row, int col) {
            // Compute nCr with n=row-1 and r=col-1
            // note: During computing nCr, for denominator consider taking (1*2*3*..) rather than (..*3*2*1), 
            // it will be easy to divide

            int nr = row-1;  // numerator
            int resultEle = 1;
            for(int i=0;i<col-1;i++){
                resultEle = resultEle * (nr-i);
                resultEle = resultEle / (i+1);
            }
            return resultEle;
        }




        // Variation 3: Return the specified row from Pascal's triangle

        /** Approach:
         * Bruteforce approach is to compute nCr for every element in that row.
         * Better approach would be by observation:  eg: for row=5 elements are 1, 4, 6, 4, 1 (row 5 will have 5 cols)
         * So excluding first and last, above elements 1, 4, 6, 4, 1 can be expanded using nCr as :
         * 1, 4/1, (4/1)*(3/2), (4/1)*(3/2)*(2/3), 1
         * 
         * consider 0 based indexing for col and store previous element in prevRes.
         * Taking prev result common from above: 1, prevRes *(row-col/col), ..., 1   **/

        // Time Complexity ~ O(n),  n = no of row
        // Space complexity ~ O(1)

        public int[] findRowInPascalTriangle(int row) {
            int result[] = new int[row];
            int ans = 1;
            result[0] = 1;
            for(int col=1;col<row;col++){
                ans = ans * (row - col);
                ans = ans / col;
            }
            result[row-1] = 1;

            return result;
        }
        
    }

}