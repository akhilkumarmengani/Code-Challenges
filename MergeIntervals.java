/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/


class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if(n==0 || n==1)
            return intervals;
        Arrays.sort(intervals, new Comparator<int[]>(){
             public int compare(int[] a, int[] b){
                 return Integer.compare(a[0],b[0]);
             }
        });
        List<int[]> list = new ArrayList<int[]>();
        list.add(intervals[0]);
        for(int i = 1; i < n ; i++){
            int[] top = list.get(list.size()-1);
            int[] interval = new int[2];
            if(top[1] >= intervals[i][0]){
                interval[0] = top[0];
                interval[1] = Math.max(top[1],intervals[i][1]);
                list.remove(list.size()-1);
                list.add(interval);
            }
            else{
                list.add(intervals[i]);
            }
        }
        int[][] result = new int[list.size()][2];
        int k = 0;
        for(int[] a:list){
            result[k][0] = a[0];
            result[k][1] = a[1];
            k++;
        }
        return result;
    }
}