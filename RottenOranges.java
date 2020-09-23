/*In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.


Example 1:

Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.

*/

class Solution {
    
    public boolean isSafe(int grid[][], int row, int col){
        return row>=0 && row<grid.length && col>=0 && col < grid[0].length; 
    }
    
    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer,Integer>> queue = new LinkedList<Pair<Integer,Integer>>();
        int time = -1;
        int freshOranges = 0;
        int ROWS = grid.length;
        int COLS = grid[0].length;
        for(int i =0; i < ROWS ; i++){
            for(int j = 0; j < COLS ; j++){
                if(grid[i][j]==2){
                    queue.add(new Pair(i,j));
                }
                else if (grid[i][j]==1){
                    freshOranges++;
                }
            }
        }
        queue.add(new Pair(-1,-1));
        
        int rowDir[] = {0,1,0,-1};
        int colDir[] = {1,0,-1,0};
        
        while(queue.size()>0){
            Pair cell = queue.peek();
            queue.remove();
            int row = (int)cell.getKey();
            int col = (int)cell.getValue();
            if(row == -1 && col == -1){
                time++;
                if(queue.size()>0){
                    queue.add(new Pair(-1,-1));
                }
            }
            else{
                
                for(int dir = 0; dir < 4 ; dir++){
                    int nRow = row + rowDir[dir];
                    int nCol = col + colDir[dir];
                    if(isSafe(grid,nRow,nCol) && grid[nRow][nCol] == 1){
                        grid[nRow][nCol] = 2;
                        queue.add(new Pair(nRow, nCol));
                        freshOranges--;
                    }
                }
            }
        }
        return freshOranges==0? time:-1; 
    }
}