package ArraysPart2;
import java.util.*;


//Problem link: https://leetcode.com/problems/merge-sorted-array/description/

public class Merge_Sorted_Array {

    /** Brute Force Approach:
     * Place all elements from nums2 in extra spaces provided in nums1 and sort nums1
     * **/

    // Time Complexity: O(nlogn),  where n = m+n (length of both arrays combined)
    // Space complexity: O(1)

    static class Solution1 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for(int i=0;i<n;i++){
                nums1[m+i] = nums2[i];
            }
            Arrays.sort(nums1);
        }
    }




    /** Better Approach:
     * Use extra array space and 2-pointer approach to merge sorted array and store the end result into nums1
     * **/

    // Time Complexity: O(m+n)
    // Space complexity: O(m+n)

    static class Solution2 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int arr[] = new int[m+n];
            int ptr1 = 0, ptr2 = 0;
            int ptr3 = 0;
            while(ptr1 < m && ptr2 < n){
                if(nums1[ptr1] <= nums2[ptr2]){
                    arr[ptr3++] = nums1[ptr1++];
                }
                else{
                    arr[ptr3++] = nums2[ptr2++];
                }
            }

            while(ptr1 < m){
                arr[ptr3++] = nums1[ptr1++];
            }

            while(ptr2 < n){
                arr[ptr3++] = nums2[ptr2++];
            }

            for(int i=0;i<arr.length;i++){
                nums1[i] = arr[i];
            }
        }
    }



    /** Optimal Approach:
     * Traverse backwards in both arrays and try to fill the larger elements first
     * video soln: https://www.youtube.com/watch?v=P1Ic85RarKY
     * **/

    // Time Complexity: O(m+n)
    // Space complexity: O(1)

    static class Solution3 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int ptr1 = m-1;     // traverse through nums1
            int ptr2 = n-1;     // traverse through nums2
            int ptr3 = m+n-1;   // keep track of elements inserted backwards

            // move all elements of nums2 in nums1 ie. loop through elements in nums2
            while(ptr1>=0 && ptr2>=0){
                // element in nums1 greater than element in nums2
                if(nums1[ptr1] > nums2[ptr2]){
                    nums1[ptr3] = nums1[ptr1];
                    ptr1--;
                }
                else{
                    nums1[ptr3] = nums2[ptr2];
                    ptr2--;
                }
                ptr3--;
            }

            // fill remaining elements of nums2 in nums1 if any.
            // (as we know both arrays are already sorted, no need to check ordering)
            while(ptr2>=0){
                nums1[ptr3] = nums2[ptr2];
                ptr2--;
                ptr3--;
            }
        }
    }
}
