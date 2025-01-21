package ArraysPart4;

import java.util.*;


// Problem link: https://leetcode.com/problems/4sum/description/


public class Four_Sum {

    /** Brute Force Approach:
     * Use 4 nested loops to find 4 elements that add up to target
     * Sort the numbers and use a set to avoid duplicates of the quadruplets
     * */

    // Time Complexity: O(n^4)
    // Space complexity: O(no. of quadruplets)

    static class Solution1 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Set<List<Integer>> set = new HashSet<>();

            for(int i=0;i<nums.length;i++){
                for(int j=i+1;j<nums.length;j++){
                    for(int k=j+1;k<nums.length;k++){
                        for(int l=k+1;l<nums.length;l++){
                            // adding separately, bcz if we add together int might overflow
                            long currSum = nums[i] + nums[j];
                            currSum += nums[k];
                            currSum += nums[l];
                            if(currSum == target){
                                List<Integer> lst = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                                Collections.sort(lst);   // sort to get unique numbers in set
                                set.add(lst);
                            }
                        }
                    }
                }
            }

            // convert set to list
            return new ArrayList<>(set);
        }
    }




    /** Better Approach:
     * nums[i] + nums[j] + nums[k] + nums[l] == target, so nums[l] = target - (nums[i] + nums[j] + nums[k])
     * Eliminate 4th loop by using intermediate set and search the forthEle ie. nums[l]
     *
     * store the array elements in a tempSet which are between j and k to avoid picking duplicates and
     * search for forthEle within the stored elements
     * */

    // Time Complexity: O(n^3)
    // Space complexity: O(n) + O(no. of quadruplets)

    static class Solution2 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Set<List<Integer>> set = new HashSet<>();

            for(int i=0;i<nums.length;i++){
                for(int j=i+1;j<nums.length;j++){
                    // all elements between j and k will go into the temp hashSet and search the 4th element within
                    // that temp hashSet, this is done bcz we cannot consider duplicate indexes while finding quadruplets
                    Set<Long> tempSet = new HashSet<>();
                    for(int k=j+1;k<nums.length;k++){
                        long currSum = nums[i] + nums[j];
                        currSum += nums[k];

                        long forthEle = target - currSum;
                        if(tempSet.contains(forthEle)){
                            List<Integer> lst = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], (int)forthEle));
                            Collections.sort(lst);   // sort to get unique numbers in set
                            set.add(lst);
                        }
                        tempSet.add((long)nums[k]);
                    }
                }
            }

            // convert set to list
            return new ArrayList<>(set);
        }
    }




    /** Optimal Approach:
     * Sort the entire array to tackle the problem of taking duplicate quadruplets
     * then traverse through array by fixing 2 pointers at a time ie. i and j and using other 2 pointers k and l go
     * through the remaining part of array and determine weather nums[i] + nums[j] + nums[k] + nums[l]  = target
     *
     * after we find a quadruplets move pointers such that we dont consider the prev values of k and l again
     * similarly do it for i and j
     *
     * video explanation: https://www.youtube.com/watch?v=eD95WRfh81c
     * */

    // Time Complexity ~ O(n^3)
    // Space complexity: O(1)

    static class Solution3 {
        // we use similar logic from ThreeSum and TwoSum-2 problems
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();

            Arrays.sort(nums);

            for(int i=0;i<nums.length;i++){
                if(i>0 && nums[i]==nums[i-1]) continue;     // skip duplicates as its already considered in prev iteration

                for(int j=i+1;j<nums.length;j++){
                    if(j>i+1 && nums[j]==nums[j-1]) continue;  // skip duplicates as its already considered in prev iteration

                    // use 2 pointers from here as we have fixed 2 elements ie. nums[i] and nums[j]
                    int k = j+1, l = nums.length-1;

                    while(k < l){
                        // calculate the sum (adding seperately to avoid int overflow)
                        long currSum = nums[i];
                        currSum += nums[j];
                        currSum += nums[k];
                        currSum += nums[l];

                        if(currSum == target){
                            res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l])));
                            k++; l--;
                            // skip duplicates
                            while(k<l && nums[k] == nums[k-1]) k++;
                            while(k<l && nums[l] == nums[l+1]) l--;
                        }
                        else if(currSum < target){
                            k++;
                        }
                        else{
                            l--;
                        }
                    }
                }
            }

            // convert set to list
            return res;
        }
    }
}
