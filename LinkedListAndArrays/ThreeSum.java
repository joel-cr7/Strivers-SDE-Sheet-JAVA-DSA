package LinkedListAndArrays;

import java.util.*;




public class ThreeSum {

    /** Brute force approach
     * To avoid duplicates is to sort the triplets and store in set
     */

    // Time Complexity: O(n^3)
    // Space complexity: O(n)

    static class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {
            int n = nums.length;
            Set<List<Integer>> set = new HashSet<>();

            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    for(int k=j+1;k<n;k++){
                        if(nums[i] + nums[j] + nums[k]==0){
                            List<Integer> lst = Arrays.asList(nums[i], nums[j], nums[k]);
                            Collections.sort(lst);
                            set.add(lst);
                        }
                    }
                }
            }

            return new ArrayList<>(set);
        }
    }




    /** Better approach
     * By observation if nums[i] + nums[j] + nums[k] == 0, then nums[k] should be -(nums[i] + nums[j])
     * For this, loop for elements i and j and
     * store the array elements in a set which are between i and j to avoid picking duplicates
     * search for nums[k] within that stored elements
     *
     * Eg: if we hash all elements and dont just hash middle elements then we might pick duplicate elements in triplets
     * [-1, 0, 1, 2, -1, -4]    =>  suppose we pick 2 and -4, so we are searching for 2 to form a triplet
     * if we use normal hashing we again pick 2 which is wrong, bcz we already picked 2 so it is duplicate
     */

    // Time Complexity: O(n^2)
    // Space complexity: O(n)

    static class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            int n = nums.length;
            Set<List<Integer>> res = new HashSet<>();

            for(int i=0;i<n;i++){
                Set<Integer> set = new HashSet<>();
                for(int j=i+1;j<n;j++){
                    int searchEle = -(nums[i] + nums[j]);
                    if(set.contains(searchEle)){
                        List<Integer> lst = Arrays.asList(nums[i], nums[j], searchEle);
                        Collections.sort(lst);
                        res.add(lst);
                    }
                    set.add(nums[j]);
                }
            }

            return new ArrayList<>(res);
        }
    }





    /** Optimal approach
     * Sort array
     * Traverse elements, fix one element and use 2-pointers (Two-sum)
     */

    // Time Complexity: O(n^2)
    // Space complexity: O(n)

    static class Solution3 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);      // sort array to eliminate taking duplicate triplets
            List<List<Integer>> res = new ArrayList<>();

            for(int i=0;i<nums.length;i++){
                // skip duplicates
                if(i>0 && nums[i]==nums[i-1]){
                    continue;
                }

                // Two pointer approach (perform TwoSum2 approach)
                int j = i+1;
                int k = nums.length-1;

                int prevJ = Integer.MIN_VALUE;
                int prevK = Integer.MIN_VALUE;
                while(j<k){
                    if(nums[i] + nums[j] + nums[k] == 0 && nums[j]!=prevJ && nums[k]!=prevK){
                        // triplet found
                        // store the prev values of nums[j] and nums[k] if we found triplet, so that we dont consider it again
                        prevJ = nums[j];
                        prevK = nums[k];
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                        j++;
                        k--;
                    }
                    else if(nums[i] + nums[j] + nums[k] < 0){
                        j++;
                    }
                    else{
                        k--;
                    }
                }
            }

            return res;
        }
    }
}
