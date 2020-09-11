/*
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
*/
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
       TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the element in the set which is greater than or equals to nums[i]
            Long s = set.ceiling(Long.valueOf(nums[i]));
            if (s != null && s <= Long.valueOf(nums[i] + t)) return true;

            // Find the element in the set which is smaller than or equals to nums[i]
            Long g = set.floor(Long.valueOf(nums[i]));
            if (g != null && nums[i] <= Long.valueOf(g + t)) return true;

            set.add(Long.valueOf(nums[i]));
            if (set.size() > k) {
                set.remove(Long.valueOf(nums[i-k]));
            }
        }
        return false;
    }
}