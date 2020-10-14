class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n==1)
            return nums[0];
        if(n==2)
            return Math.max(nums[0],nums[1]);
        int firstMax = getMaxMoney(nums,0,n-2);
        int secondMax = getMaxMoney(nums,1,n-1);
        return Math.max(firstMax,secondMax);
    }
    
    public int getMaxMoney(int[] nums, int start, int end){
        int incl = nums[start];
        int excl = 0;
        for(int i = start+1 ; i <=end ; i++){
            int temp = incl;
            incl = excl+nums[i];
            excl = Math.max(excl,temp);
        }
        return Math.max(incl,excl);
    }
}