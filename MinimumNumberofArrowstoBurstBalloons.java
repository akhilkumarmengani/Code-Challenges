class Solution {
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if(n==0)
            return 0;
        Arrays.sort(points,new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0]==b[0]){
                    return Integer.compare(a[1],b[1]);
                }
                return Integer.compare(a[0],b[0]);
            }
        });
        for(int[] point:points){
            System.out.println(point[0]+" "+point[1]);
        }
        int result = 1;
        int curEnd = points[0][1];
        for(int i = 1; i < n ; i++){
            if(curEnd>=points[i][0]){
                curEnd = Math.min(curEnd,points[i][1]);
            }
            else{
                curEnd = points[i][1];
                result++;
            }
        }
        return result;
    }
}