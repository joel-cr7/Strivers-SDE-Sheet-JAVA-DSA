package ArraysPart4;


// Problem link: https://leetcode.com/problems/two-sum/description/


public class Two_Sum{

    /** Brute Force Approach:
     * Use nested loop to indices of both elements that add up to target 
     * */

    // Time Complexity: O(n^2)
    // Space complexity: O(1)

    static class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            int res[] = new int[2];
            for(int i=0;i<nums.length-1;i++){
                for(int j=i+1;j<nums.length;j++){
                    if(nums[i]+nums[j]==target){
                        res[0] = i;
                        res[1] = j;
                        return res;
                    }
                }
            }
            return res;
        }
    }




    /** Optimal Approach:
     * Use Map to store element and its index, before adding to map check if equivalent element is present in the map
     * */

    // Time Complexity: O(n)
    // Space complexity: O(n)

    static class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            int res[] = new int[2];
    
            Map<Integer, Integer> mp = new HashMap();
    
            for(int i=0;i<nums.length;i++){
                if(mp.containsKey(target-nums[i])){
                    res[0] = i;
                    res[1] = mp.get(target-nums[i]);
                    return res;
                }
                else{
                    mp.put(nums[i], i);
                }
            }
    
            return res;
        }
    }

}