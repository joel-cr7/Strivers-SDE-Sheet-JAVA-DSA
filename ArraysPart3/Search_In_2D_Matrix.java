package ArraysPart3;


//Problem link: https://leetcode.com/problems/search-a-2d-matrix/description/


public class Search_In_2D_Matrix {

    /** Brute Force Approach:
     * Traverse through every element and find the target
     * */

    // Time Complexity: O(n*m)
    // Space complexity: O(1)

    static class Solution1 {
        public boolean searchMatrix(int[][] matrix, int target) {
            for(int i=0;i<matrix.length;i++){
                for(int j=0;j<matrix[0].length;j++){
                    if(matrix[i][j] == target){
                        return true;
                    }
                }
            }
            return false;
        }
    }



    /** Better Approach:
     * As every row of the matrix is in sorted order
     * So first check if the target is present in current row
     * if yes, then we can use binary search on that row
     * **/

    // Time Complexity: O(n) + O(log m)
    // Space complexity: O(1)

    static class Solution2 {
        public boolean searchMatrix(int[][] matrix, int target) {
            for(int i=0;i<matrix.length;i++){
                int currRow[] = matrix[i];
                int left = 0, right = currRow.length-1;
                if(target >= currRow[left] && target <= currRow[right]) {
                    while(left <= right){
                        int mid = left + (right-left) / 2;
                        if(currRow[mid] == target){
                            return true;
                        }
                        else if(currRow[mid] < target){
                            left = mid + 1;
                        }
                        else{
                            right = mid - 1;
                        }
                    }
                }
            }
            return false;
        }
    }




    /** Optimal Approach:
     * As the elements of matrix are sorted, hypothetically flatten the 2d array into 1d array and use binary search.
     * We dont actually flatten the matrix but use pointers in such a way that we traverse matrix and perform binary search.
     * for matrix [[ 1   4   6   8]
     *             [10, 12, 13, 15]
     *             [17, 18, 19, 20]]
     * if we flatten we would get 1 at index 0, 4 at index 1, and so on... 20 at index 11.
     * when using binary search the element at mid index can be found in matrix at position (i,j),
     * where for i we divide by mid by m and for j we modulo mid by m   (m is no. of cols and n is no. of rows)
     * ie. i = mid/m and j = mid%m
     * for above Eg: m=4 and mid we reached is 6, we can find this element in matrix at (6/4, 6%4) ie at (1,2) = 13
     * **/

    // Time Complexity: O(log(m*n))
    // Space complexity: O(1)

    static class Solution3 {
        public boolean searchMatrix(int[][] matrix, int target) {
            int n = matrix.length, m = matrix[0].length;
            // for element (i,j) for i divide by m and for j modulo by m

            int left = 0, right = n*m-1;
            while(left <= right){
                int mid = left + (right-left)/2;
                int i = mid/m, j = mid%m;
                if(matrix[i][j]==target){
                    return true;
                }
                else if(matrix[i][j]<target){
                    left = mid+1;
                }
                else{
                    right = mid-1;
                }
            }
            return false;
        }
    }


}
