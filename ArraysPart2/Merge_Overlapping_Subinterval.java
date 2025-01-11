package ArraysPart2;

import java.util.*;


//Problem link: https://leetcode.com/problems/merge-intervals/description/


public class Merge_Overlapping_Subinterval {

    /** Optimal Approach:
     * Sort array based on first element to get all sub-intervals in increasing order
     * Add first sub-interval in result
     * Iterate over array to merge the rest if the condition matches or else simply add to result
     * */

    // Time Complexity ~ O(nlogn + n)
    // Space complexity ~ O(n)


    static class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

            List<int[]> res = new ArrayList<>();
            res.add(intervals[0]);

            for(int i=1;i<intervals.length;i++){
                int lastInserted[] = res.get(res.size()-1);
                if(intervals[i][0] <= lastInserted[1]){
                    lastInserted[1] = Math.max(lastInserted[1], intervals[i][1]);
                }
                else{
                    res.add(intervals[i]);
                }
            }

            return res.toArray(new int[res.size()][]);
        }
    }
}
