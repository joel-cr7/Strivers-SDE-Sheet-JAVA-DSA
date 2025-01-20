package ArraysPart2;


// Problem link: https://www.naukri.com/code360/problems/count-inversions_615?leftPanelTabValue=PROBLEM


public class Count_Inversions_Of_Array {

    /** Brute Force Approach:
     * Use Nested loop and counter to maintain the count
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        public static long getInversions(long arr[], int n) {
            long cnt = 0;
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    if(arr[i] > arr[j]){
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
     * pairs i.e. a1[i] and a2[j] such that a1[i] > a2[j].
     *
     * In order to solve this, we will keep two pointers i and j, where i will point to the first index of a1[]
     * and j will point to the first index of a2[]. Now in each iteration, we will do the following:
     *
     * 1) If a1[i] <= a2[j]: These two elements cannot be a pair, so we will move the pointer i to the next position
     * 2) If a1[i] > a2[j]: These two elements can be a pair, so update the count of pairs.
     *    Now, here we should observe that as a1[i] is greater than a2[j], all the elements after a1[i] will also be
     *    greater than a2[j] and so, those elements will also make pair with a2[j].
     *    So we will increment count by a1.length - i + 1
     *
     * **/

    // Time Complexity: O(nlogn)
    // Space complexity: O(n)

    static class Solution2 {
        private long counter = 0;

        private void merge(long arr[], int low, int mid, int high){
            long temp[] = new long[high-low+1];
            int ptr1 = low, ptr2 = mid+1;
            int ptr3 = 0;

            while(ptr1 <= mid && ptr2 <= high){
                if(arr[ptr1] <= arr[ptr2]){
                    temp[ptr3++] = arr[ptr1++];
                }
                else{
                    // element from left array is greater than element of right array
                    temp[ptr3++] = arr[ptr2++];
                    counter += (mid-ptr1+1);  // increment counter by the no. of elements in left array which are to the right of ptr1
                }
            }

            while(ptr1 <= mid){
                temp[ptr3++] = arr[ptr1++];
            }

            while(ptr2 <= high){
                temp[ptr3++] = arr[ptr2++];
            }

            for(int i=0, j=low;i<temp.length;i++, j++){
                arr[j] = temp[i];
            }
        }


        private void mergeSort(long arr[], int low, int high){
            if(low < high){
                int mid = (low + high) / 2;
                mergeSort(arr, low, mid);
                mergeSort(arr, mid+1, high);
                merge(arr, low, mid, high);
            }
        }


        public long getInversions(long arr[], int n) {
            mergeSort(arr, 0, n-1);
            return counter;
        }
    }
}
