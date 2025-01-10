package Arrays;

import java.util.*;


// Problem link: https://leetcode.com/problems/next-permutation/description/


public class Next_Permutation {

    /**
     * Brute force Approach:
     * Eg: For a = [2 1 5 4 3 0 0] the next permutation is [2 3 0 0 1 4 5].
     * Try to generate all possible permutations using recursion and then print the next permutation.
     *
     * 1) At each step of recursion go through every element,
     *      if base case reached push into final ans
     *      if not visited push it in res and mark it as visited
     *      after we are done taking an element unmark it as visited and remove from res
     */

    // Time Complexity ~ O(n! + n)
    // Space complexity ~ O(2n)

    static class Solution1 {

        private static void findAllPermutations(int[] nums, List<Integer> res, boolean vis[], List<List<Integer>> ans){
            // Base case: if permutation found add it to result
            if(res.size()==nums.length){
                ans.add(res);
                return;
            }

            // loop will execute for every recursion pass
            for(int i=0;i<nums.length;i++){
                if(!vis[i]){
                    vis[i] = true;
                    res.add(nums[i]);
                    findAllPermutations(nums, res, vis, ans);
                    vis[i] = false;
                    res.remove(res.size()-1);
                }
            }
        }

        public List<Integer> nextPermutation(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> res = new ArrayList<>();
            boolean vis[] = new boolean[nums.length];
            findAllPermutations(nums, res, vis, ans);

            for(int i=0;i<ans.size();i++){
                int arr[] = ans.get(i).stream().mapToInt(k-> k).toArray();
                if(Arrays.equals(arr, nums)){
                    if(i==ans.size()-1)
                        return ans.get(0);
                    else
                        return ans.get(i+1);
                }
            }
            return null;
        }
    }





    /**
    * Optimal Approach:
     * Eg: For a = [2 1 5 4 3 0 0] the next permutation is [2 3 0 0 1 4 5].
     * Getting next permutation means to find the next greater number in the sequence (Lexicographically greater number)

     * 1) Try to get longest prefix match possible to the next permutation by finding the breakpoint while traversing backwards
        ie.  breakpoint will be at i if a[i]<a[i+1].   (breakpoint will be at index 1 as a[1] < a[2], while traversing from back and checking).

     * 2) Try to find an element which is slightly greater than the breakpoint element ie. a[i], and swap a[i] with that element.
        (from the elements at right of a[1] ie. 1, the element slightly greater than 1 is 3, so swap 1 and 3). so we get [2 3 5 4 1 0 0]

     * 3) Reverse all the elements from a[i+1] till end.    (we finally get [2 3 0 0 1 4 5])
    */

    // Time Complexity ~ O(3n)
    // Space complexity ~ O(1)

    static class Solution2 {

        private void reverseArr(int arr[], int start, int end){
            while(start < end){
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }

        public void nextPermutation(int[] nums) {
            // find the breakpoint (required to get longest prefix)
            int idx = -1;
            for(int i=nums.length-2; i>=0; i--){
                if(nums[i] < nums[i+1]){
                    idx = i;
                    break;
                }
            }

            // edge case (there is no next permutation as all elements are in descending order)
            // return the first permutation in the sequence ie. elements in ascending order
            if(idx == -1){
                reverseArr(nums, 0, nums.length-1);
                return;
            }

            // find the element from right which is slightly greater than breakpoint element and swap
            for(int i=nums.length-1;i>idx;i--){
                if(nums[i] > nums[idx]){
                    int temp = nums[i];
                    nums[i] = nums[idx];
                    nums[idx] = temp;
                    break;
                }
            }

            // reverse the rest elements towards right of idx
            reverseArr(nums, idx+1, nums.length-1);
        }
    }
}
