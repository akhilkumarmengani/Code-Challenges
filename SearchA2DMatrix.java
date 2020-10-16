class Solution {
    
    public boolean binarySearch(int[][] matrix,int row, int right,int target){
        int left = 0;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(matrix[row][mid]==target)
                return true;
            if(matrix[row][mid]>target){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int R = matrix.length;
        if(R==0)
            return false;
        int C = matrix[0].length;
        for(int i = 0 ; i < R ; i++){
            if(i==R-1){
                return binarySearch(matrix,i,C-1,target);
            }
            if(matrix[i+1][0]==target)
                return true;
            if(matrix[i][0]<=target && target < matrix[i+1][0]){
                return binarySearch(matrix,i,C-1,target);
            }
        }
        return false;
    }
}