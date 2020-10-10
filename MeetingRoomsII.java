class Solution {
    
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[][] timeMap = new int[2*n][2];
        int i = 0;
        for(int[] interval:intervals){
            timeMap[i][0] = interval[0];
            timeMap[i][1] = 0;
            timeMap[i+1][0] = interval[1];
            timeMap[i+1][1] = 1;
            i+=2;
        }
        Arrays.sort(timeMap, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0]==b[0]){
                    return Integer.compare(b[1],a[1]);
                }
                return Integer.compare(a[0],b[0]);
            }
        });
        int result = 0;
        int currentRooms = 0;
        for(int[] time : timeMap){
            if(time[1]==0){
                currentRooms++;
            }
            else{
                currentRooms--;
            }
            result = Math.max(result,currentRooms);
        }
        return result;
    }
}