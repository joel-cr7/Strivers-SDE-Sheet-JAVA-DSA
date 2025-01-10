package Arrays;

import java.util.*;


//Problem link: https://leetcode.com/problems/sort-colors/description/


public class DNF_Sort {

    /** Brute Force Approach:
     * Use in-built sorting method
     * */

    // Time complexity: O(nlogn)
    // Space complexity: O(n)

    static class Solution1 {
        public void sortColors(int[] nums) {
            Arrays.sort(nums);
        }
    }



    /** Better Approach:
     * Maintain counter variables for every number ie. 0, 1, 2
     * Replace every element in array based on the counts
     * */

    // Time complexity: O(2n)
    // Space complexity: O(1)

    static class Solution2 {
        public void sortColors(int[] nums) {
            // maintain counts of each numbers
            int count0 = 0, count1 = 0, count2 = 0;
            for(int i=0;i<nums.length;i++){
                if(nums[i]==0) count0++;
                else if(nums[i]==1) count1++;
                else count2++;
            }

            // replace the elements based on count
            for(int i=0;i<nums.length;i++){
                if(count0!=0){
                    nums[i] = 0;
                    count0--;
                }
                else if(count1!=0){
                    nums[i] = 1;
                    count1--;
                }
                else{
                    nums[i] = 2;
                    count2--;
                }
            }
        }
    }


    /** Optimal Approach (DNF sort):
     * divide the array into 4 parts:
     * 0 to low-1 will contain all 0's
     * low to mid-1 will contain all 1's
     * mid to high will contain all un-sorted elements
     * high+1 to end will contain all 2's
     * */

    // Time complexity: O(n)
    // Space complexity: O(1)

    static class Solution3{
        public void sortColors(int[] nums) {
            int n = nums.length;
            int low = 0, mid = 0;
            int high = n-1;
            while(mid <= high){
                if(nums[mid] == 0){
                    int temp = nums[mid];
                    nums[mid] = nums[low];
                    nums[low] = temp;
                    low++;
                    mid++;
                }
                else if(nums[mid] == 1){
                    mid++;
                }
                else{
                    int temp = nums[mid];
                    nums[mid] = nums[high];
                    nums[high] = temp;
                    high--;
                }
            }
        }
    }


}
