package ArraysPart3;


// Problem link: https://leetcode.com/problems/unique-paths/description/


public class Grid_Unique_Paths {

    /** Brute Force Approach:
     * Use recursion to fina all possible paths.
     * At a particular cell in recursion we have 2 decisions to make:
     *  1) Move down   OR   2) Mode right
     * As we need to count the total paths to reach destination, we return 1 or 0 indicating valid path or invalid path
     * Base condition: if we are able to reach destination return 1 else if out of bound return 0
     *
     * */

    // Time Complexity: exponential
    // Space complexity: exponential

    static class Solution1 {
        private int findUniquePaths(int row, int col, int m, int n) {
            if(row==m-1 && col==n-1){
                return 1;
            }
            if(row>=m || col>=n){
                return 0;
            }
            else{
                return findUniquePaths(row+1, col, m, n) + findUniquePaths(row, col+1, m, n);
            }
        }

        public int uniquePaths(int m, int n) {
            int totalPaths = findUniquePaths(0, 0, m, n);
            return totalPaths;
        }
    }




    /** Better Approach:
     * Use dynamic programming (hash table) to hash all the results, so if we encounter same recursion sub-problem
     * in future we can return the hashed result.
     * */

    // Time Complexity: O(m*n)
    // Space complexity: O(m*n)

    static class Solution2 {
        private int findUniquePaths(int row, int col, int m, int n, int dp[][]) {
            if(row==m-1 && col==n-1) return 1;

            if(row>=m || col>=n) return 0;

            if(dp[row][col]!=-1) return dp[row][col];

            else{
                dp[row][col] = findUniquePaths(row+1, col, m, n, dp) + findUniquePaths(row, col+1, m, n, dp);
                return dp[row][col];
            }
        }

        public int uniquePaths(int m, int n) {
            int dp[][] = new int[m][n];
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    dp[i][j] = -1;
                }
            }
            int totalPaths = findUniquePaths(0, 0, m, n, dp);
            return totalPaths;
        }
    }
}
