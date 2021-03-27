class Solution {
    
    Set<String> result = new HashSet<>();
    class TrieNode{
        boolean isLeaf;
        Map<Character,TrieNode> children;
        TrieNode(){
            this.isLeaf = false;
            children = new HashMap<>();
        }
    }
    
    TrieNode root = new TrieNode();
 
    
    public void insert(String word){
        TrieNode head = root;
        for(int i = 0; i < word.length() ; i++){
            char c = word.charAt(i);
            if(root.children.containsKey(c)){
                root = root.children.get(c);
            }
            else{
                TrieNode newNode = new TrieNode();
                root.children.put(c,newNode);
                root = root.children.get(c);
            }
        }
        root.isLeaf = true;
        root = head;
    } 
    
    public boolean isSafe(char[][] board,int row, int col){
        return row>=0 && col>=0 && row<board.length && col<board[0].length && board[row][col]!='*';
    }
    
    public void dfs(char[][] board, int row , int col,String word){
        if(!isSafe(board,row,col)){
            return;
        }
        
        char temp = board[row][col];
        if(root.children.containsKey(board[row][col])){
            word += board[row][col];
            root = root.children.get(board[row][col]);
            TrieNode tempNode = root;
            if(root.isLeaf){
                result.add(word);
            }
            board[row][col] = '*';
            // put this in a loop
            dfs(board,row-1,col,word);
            root = tempNode;
            dfs(board,row,col+1,word);
            root = tempNode;
            dfs(board,row+1,col,word);
            root = tempNode;
            dfs(board,row,col-1,word);
            root = tempNode;
        }
        board[row][col] = temp;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        
        int rows = board.length;
        if(rows == 0)
            return new ArrayList<>();
        int cols = board[0].length;
        for(String word : words){
            insert(word);
        }
        
        TrieNode head = root;
        
        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols ; j++){
                dfs(board,i,j,"");
                root = head;
            }
        }
        return new ArrayList<String>(result);
    }
}