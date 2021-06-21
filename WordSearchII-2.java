class Solution {
    
    Set<String> result = new TreeSet<>();
    List<String> remWords = new LinkedList<>();
    int rows;
    int cols;
    int rowDir[] = new int[]{0,1,0,-1};
    int colDir[] = new int[]{1,0,-1,0};
    
    class TrieNode{
        HashMap<Character,TrieNode> children;
        boolean isWord;
        TrieNode(){
            this.children = new HashMap<Character,TrieNode>();
            this.isWord = false;
        }
    }
    
    class Trie{
        TrieNode root;
        
        Trie(){
            this.root = new TrieNode();
        }
        
        public TrieNode getRoot(){
            return this.root;
        }
        
        public void insert(String word){
            TrieNode current = root;
            for(int i = 0; i < word.length() ; i++){
                char ch = word.charAt(i);
                if(!current.children.containsKey(ch)){
                    TrieNode next = new TrieNode();
                    current.children.put(ch,next);
                }
                current = current.children.get(ch);
            }
            current.isWord = true;
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        
        this.rows = board.length;
        this.cols = board[0].length;
        
        Trie trie = new Trie();
        TrieNode root = trie.getRoot();
        
        for(int i = 0; i < words.length ; i++){
            trie.insert(words[i]);
        }
        
        for(int i = 0; i < this.rows ; i++){
            for(int j = 0; j < this.cols ; j++){
                if(root.children.containsKey(board[i][j])){
                    dfs(board,i,j,root.children.get(board[i][j]),String.valueOf(board[i][j]));
                }
            }
        }
        return new ArrayList<String>(result);
        
    }
    
    public boolean isSafe(int row , int col){
        return row>=0 && row<this.rows && col>=0 && col<this.cols;
    }
    
    public void dfs(char board[][], int i, int j, TrieNode root,String str){
        if(root.isWord){
            result.add(str);
        }
        
        char temp = board[i][j];
        board[i][j] = '*';
        
        for(int k = 0; k < 4 ; k++){
            int nR = i + rowDir[k];
            int nC = j + colDir[k];
            if(isSafe(nR,nC)){
               char ch = board[nR][nC];
               if(ch !='*' && root.children.containsKey(ch)){
                    dfs(board,nR,nC,root.children.get(ch),str+String.valueOf(board[nR][nC]));
                }
            }
        }
        board[i][j] = temp;
    }
    
    
//     Set<String> result = new TreeSet<>();
//     List<String> remWords = new LinkedList<>();
//     int rows;
//     int cols;
//     int rowDir[] = new int[]{0,1,0,-1};
//     int colDir[] = new int[]{1,0,-1,0};
    
//     public boolean isSafe(int row , int col){
//         return row>=0 && row<this.rows && col>=0 && col<this.cols;
//     }
    
//     public void dfs(char[][] board, int index, int i, int j, String word, String curStr){
//         if(curStr.length()==word.length()){
//             result.add(word);
//             return;
//         }
//         char temp = board[i][j];
//         board[i][j] = '*';
//         for(int k = 0; k < 4 ; k++){
//             int nR = i + rowDir[k];
//             int nC = j + colDir[k];
//             if(isSafe(nR,nC) 
//                && board[nR][nC]!='*' 
//                && board[nR][nC] == word.charAt(index)
//                ){
//                 dfs(board,index+1,nR,nC,word,curStr+String.valueOf(board[nR][nC]));
//             }
//         }
//         board[i][j] = temp;
//     }
    
    
//     public List<String> findWords(char[][] board, String[] words) {
//         this.rows = board.length;
//         this.cols = board[0].length;
//         for(int i = 0; i < words.length ; i++){
//             remWords.add(words[i]);
//         }
//         for(int i = 0; i < this.rows; i++){
//             for(int j = 0; j < this.cols ; j++){
//                 for(int k = 0; k < remWords.size() ; k++){
//                     String word = remWords.get(k);
//                     if(word.charAt(0)==board[i][j] && !result.contains(word)){
//                         dfs(board,1,i,j,word,String.valueOf(word.charAt(0)));
//                         if(result.contains(word)){
//                             remWords.remove(k);
//                             k--;
//                         }
//                     }
//                 }
//             }
//         }
//         return new ArrayList<String>(result);
//     }
}