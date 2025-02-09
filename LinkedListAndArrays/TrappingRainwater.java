package LinkedListAndArrays;

// Problem link:

public class TrappingRainwater {

    /** Brute force approach
     * By observation, there can only be water trapped if there is a taller building on the left and on the right.
     * So use prefixMax and suffixMax to find tallest building on the left and right respectively.
     * Get the min of left max and right max
     */

    // Time Complexity: O(3n)
    // Space complexity: O(n)

    static class Solution1 {

        private static int[] getPrefixMax(int[] height){
            int prefixMax[] = new int[height.length];
            prefixMax[0] = height[0];
            for(int i=1;i<height.length;i++){
                prefixMax[i] = Math.max(prefixMax[i-1], height[i]);
            }
            return prefixMax;
        }

        private static int[] getSuffixMax(int[] height){
            int n = height.length;
            int suffixMax[] = new int[n];
            suffixMax[n-1] = height[n-1];
            for(int i=n-2;i>=0;i--){
                suffixMax[i] = Math.max(suffixMax[i+1], height[i]);
            }
            return suffixMax;
        }

        public int trap(int[] height) {
            int n = height.length;
            int totalWater = 0;

            int prefixMax[] = getPrefixMax(height);
            int suffixMax[] = getSuffixMax(height);

            for(int i=0;i<n;i++){
                if(height[i] < prefixMax[i] && height[i] < suffixMax[i]){
                    totalWater += Math.min(prefixMax[i], suffixMax[i]) - height[i];
                }
            }

            return totalWater;
        }
    }




    /** Optimal approach
     * https://www.youtube.com/watch?v=ZI2z5pq0TqA
     *
     * Move the pointer of the smallest building at each traversal ie. from leftMax and rightMax which ever is smaller
     * move that pointer first ie left or right pointer respectively.
     *
     * Previously we needed the min of leftMax and rightMax to calculate the amount of water at a position.
     *
     * But as we are moving just the smallest building pointer at each time so we already know which is min,
     * suppose leftMax is smaller than rightMax, so no matter hot big the number may be at right side, we know that
     * if leftMax is smaller it will be the bottleneck.
     * Hence we dont need to see the right side to calculate the amount of water at a position.
     */

    // Time Complexity: O(n)
    // Space complexity: O(1)
    static class Solution2 {

        public int trap(int[] height) {
            int n = height.length;
            int totalWater = 0;

            // track the most maximums that we see on left and right side
            int leftMax = height[0];
            int rightMax = height[n-1];

            int left = 0;
            int right = n-1;

            while(left < right){
                // just move the pointer of the smaller building
                if(height[left] < height[right]){
                    int waterAtPos = leftMax - height[left];
                    totalWater += (waterAtPos < 0 ? 0 : waterAtPos);
                    leftMax = Math.max(leftMax, height[left]);
                    left++;
                }
                else{
                    int waterAtPos = rightMax - height[right];
                    totalWater += (waterAtPos < 0 ? 0 : waterAtPos);
                    rightMax = Math.max(rightMax, height[right]);
                    right--;
                }
            }

            return totalWater;
        }
    }
}
