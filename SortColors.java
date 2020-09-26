/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Follow up:

Could you solve this problem without using the library's sort function?
Could you come up with a one-pass algorithm using only O(1) constant space?
 

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]
Example 3:

Input: nums = [0]
Output: [0]
Example 4:

Input: nums = [1]
Output: [1]
 

Constraints:

n == nums.length
1 <= n <= 300
nums[i] is 0, 1, or 2.

*/


class Solution {
    public void sortColors(int[] nums) {
        /* int zeroIndex = 0;
        int twoIndex = nums.length - 1;
        int index = 0;
        for(; index <= twoIndex ;){
            if(nums[index]==0){
                int temp = nums[index];
                nums[index] = nums[zeroIndex];
                nums[zeroIndex] = temp;
                zeroIndex++;
                index++;
            }
            else if (nums[index]==2){
                int temp = nums[index];
                nums[index] = nums[twoIndex];
                nums[twoIndex] = temp;
                twoIndex--;
            }
            else{
                index++;
            }
        }
        */
        int n = nums.length;
        int count[] = new int[3];
        for(int i = 0; i < n ; i++){
            count[nums[i]]++;
        }
        for(int i = 1; i < 3 ; i++){
            count[i] += count[i-1];
        }
        
        int result[] = new int[n];
        for(int i = 0 ; i < n ; i++){
            result[count[nums[i]] - 1] = nums[i];
            count[nums[i]]--;
        }
        System.arraycopy(result,0,nums,0,n);
    }
}