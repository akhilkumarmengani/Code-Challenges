/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.


Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above


*/



class Solution {
    
    class Pair{
        int first;
        int second;
        Pair(int first,int second){
            this.first = first;
            this.second = second;
        }
    }
    boolean rowMap[];
    boolean colMap[];
    boolean board[][];
    List<Pair> diagonal;
    List<List<String>> result;
    int size;
    public List<List<String>> solveNQueens(int n) {
        rowMap = new boolean[n];
        colMap = new boolean[n];
        board = new boolean[n][n];
        result = new ArrayList<List<String>>();
        size = n;
        diagonal = new ArrayList<Pair>();
        for(int i = 0; i < n ; i++){
            rowMap[i] = true;
            colMap[0] = true;
            board[i][0] = true;
            Pair pair = new Pair(i,0);
            diagonal.add(pair);
            findBoard(i,1);
            rowMap[i] = false;
            colMap[0] = false;
            board[i][0] = false;
            removePair(i,0);
        }  
        return result;
    }
    
    public void findBoard(int p , int q){
        if(q==size){
            addToResult();
            return;
        }
        for(int i = 0 ; i < size ; i++){
            if(i!=p-1 && i!=p+1 && !rowMap[i] && !colMap[q] && !inTheDiagonal(i,q)){
                rowMap[i] = true;
                colMap[q] = true;
                board[i][q] = true;
                Pair pair = new Pair(i,q);
                diagonal.add(pair);
                findBoard(i,q+1);
                rowMap[i] = false;
                colMap[q] = false;
                board[i][q] = false;
                removePair(i,q);
            }
        }
    }
    
    public void removePair(int i , int j){
        for(int k = 0;k  < diagonal.size() ; k++){
            Pair p = diagonal.get(k);
            if(p.first == i && p.second == j ){
                diagonal.remove(k);
            }
        }
    }
    
    public boolean inTheDiagonal(int row, int col){
        
        for(int i = 0; i < diagonal.size() ; i++){
            Pair p = diagonal.get(i);
            if(Math.abs(p.first-row) == Math.abs(p.second-col) ){
                return true;
            }
        }
        return false;
        
    }
    
    public void addToResult(){
        List<String> bList = new ArrayList<>(); 
        for(int i = 0; i< size ; i++){
            String result = "";
            for(int j = 0; j< size ; j++){
                if(board[i][j]){
                    result += "Q";
                }
                else{
                    result +=".";
                }
            }
            bList.add(result);
        }
        result.add(bList);
    }
}