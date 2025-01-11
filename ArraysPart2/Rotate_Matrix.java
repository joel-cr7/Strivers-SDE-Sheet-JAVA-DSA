package ArraysPart2;


//Problem link: https://leetcode.com/problems/rotate-image/description/


public class Rotate_Matrix {

    /** Brute Force Approach:
     * By observation, first row elements in matrix move to last column, second row moves to second-last column, and so on ...
     * Use extra space to perform rotations in matrix.
     * **/

    // Time Complexity ~ O(n^2)
    // Space complexity ~ O(n^2)

    static class Solution1 {
        public void rotate(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            int temp[][] = new int[rows][cols];

            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    temp[j][cols-i-1] = matrix[i][j];
                }
            }

            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    matrix[i][j] = temp[i][j];
                }
            }

        }
    }



    /** Optimal Approach:
     * By observation: If we compute transpose of matrix and swap the column elements we can achieve clockwise rotation
     * **/

    // Time Complexity ~ O(n^2)
    // Space complexity ~ O(1)

    static class Solution2 {
        public void rotate(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;

            // Transpose of matrix (swap elements across diagonal)
            for(int i=0;i<rows;i++){
                for(int j=i+1;j<cols;j++){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }

            // swap column elements using 2 pointers to appear as clockwise rotation
            int col1Ptr = 0, col2Ptr = cols-1;
            while(col1Ptr < col2Ptr){
                for(int i=0;i<rows;i++){
                    int temp = matrix[i][col1Ptr];
                    matrix[i][col1Ptr] = matrix[i][col2Ptr];
                    matrix[i][col2Ptr] = temp;
                }
                col1Ptr++;
                col2Ptr--;
            }

        }
    }


}
