package ArraysPart1;


// Problem link: https://leetcode.com/problems/set-matrix-zeroes/description/

public class Set_Matrix_Zeroes {

    /** Brute Force Approach:
     * Traverse through matrix, if encounter 0 mark the entire row and col with -10 constant value (except for 0 value)
     * Again traverse matrix and replace every -10 value with 0 **/

    // Time Complexity ~ O(n^3)
    // Space complexity ~ O(1)

    static class Solution1 {

        private void markRow(int[][] matrix, int row){
            for(int col=0;col<matrix[row].length;col++){
                if(matrix[row][col]!=0)
                    matrix[row][col] = -10;
            }
        }

        private void markCol(int[][] matrix, int col){
            for(int row=0;row<matrix.length;row++){
                if(matrix[row][col]!=0)
                    matrix[row][col] = -10;
            }
        }

        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(matrix[i][j]==0){
                        markRow(matrix, i);
                        markCol(matrix, j);
                    }
                }
            }

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(matrix[i][j]==-10){
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }


    /** Optimal Approach:
     * Maintain row and col flags that indicate the particular row/col need to be marked as 0
     * Traverse through matrix, if encounter 0 mark row and col flags to true
     * Again traverse matrix, if row/col flag found then mark as 0 **/

    // Time Complexity ~ O(n^2)
    // Space complexity ~ O(m+n)

    static class Solution2 {
        public void setZeroes(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;

            boolean rowFlag[] = new boolean[matrix.length];
            boolean colFlag[] = new boolean[matrix[0].length];

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(matrix[i][j]==0){
                        rowFlag[i] = true;
                        colFlag[j] = true;
                    }
                }
            }

            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(rowFlag[i] || colFlag[j]){
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }
}
