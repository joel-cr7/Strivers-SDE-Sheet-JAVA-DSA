package ArraysPart3;


// Problem link: https://leetcode.com/problems/reverse-pairs/description/



public class Reverse_Pairs{

    /** Brute Force Approach:
     * Use nested loop to find all pairs where nums[i] > 2*nums[j] in the array 
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        public int reversePairs(int[] nums) {
            int cnt = 0;
            for(int i=0;i<nums.length;i++){
                for(int j=i+1;j<nums.length;j++){
                    if(nums[i] > (long)2*nums[j]){
                        cnt++;
                    }
                }
            }
            return cnt;
        }
    }




    /** Optimal Approach:
     * Use Merge Sort Algorithm
     *
     * Track count using counter variable
     * Assume two sorted arrays are given i.e. a1[] = {2, 3, 5, 6} and a2[] = {2, 2, 4, 4, 8}. Now, we have to count the
     * pairs i.e. a1[i] and a2[j] such that a1[i] > 2 * a2[j].
     *
     * Count the pairs before merging the 2 arrays:
     * In the count function: traverse through every element of left array and check every element with (2 * element of right array)
     * eg: arr1 = [16 17 18]  arr2 = [5 6 12]
     * for 16: 16 > 2*5 and 16 > 2*6, so 17 and 18 will also be greater than 2*5 and 2*6 so no need to check in next iteration
     * for 17: check 17 with 12 directly
     *
     * **/

    // Time Complexity: O(nlogn)
    // Space complexity: O(n)

    static class Solution2 {
        private int counter = 0;
    
        private void countPairs(int[] nums, int low, int mid, int high){
            // 1st array is low to mid and 2nd array is mid+1 to high 
             
            int right = mid+1;      // track right array elements
            for(int i=low;i<=mid;i++){
                while(right<=high && nums[i]>(long)2*nums[right]){
                    right++;
                }
                counter += (right-(mid+1));
            }
        }
    
        private static void merge(int[] nums, int low, int mid, int high){
            int temp[] = new int[high-low+1];
            int ptr1 = low, ptr2 = mid+1;
            int ptr3 = 0;
            while(ptr1<=mid && ptr2<=high){
                if(nums[ptr1] <= nums[ptr2]){
                    temp[ptr3++] = nums[ptr1++];
                }
                else{
                    temp[ptr3++] = nums[ptr2++];
                }
            }
            while(ptr1<=mid){
                temp[ptr3++] = nums[ptr1++];
            }
            while(ptr2<=high){
                temp[ptr3++] = nums[ptr2++];
            }
            for(int i=low, j=0;j<temp.length;i++, j++){
                nums[i] = temp[j];
            }
        }
    
        private void mergeSort(int[] nums, int low, int high){
            if(low < high){
                int mid = (low + high) / 2;
                this.mergeSort(nums, low, mid);
                this.mergeSort(nums, mid+1, high);
                this.countPairs(nums, low, mid, high);      // before merging 2 sorted arrays count no. of reverse pairs 
                merge(nums, low, mid, high);
            }
        }
    
        public int reversePairs(int[] nums) {
            this.mergeSort(nums, 0, nums.length-1);
            return counter;
        }
    }
}