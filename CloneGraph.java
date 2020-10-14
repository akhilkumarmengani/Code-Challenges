/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    Map<Node,Node> map = new HashMap<Node,Node>();
    Node root;
    public void dfs(Node root,Node node){
        map.put(node,root);
        for(Node child : node.neighbors){
            Node neighbor = null;
            if(!map.containsKey(child)){
                neighbor = new Node(child.val);
                map.put(child,neighbor);
                root.neighbors.add(neighbor);
                dfs(neighbor,child);
            }
            else{
                neighbor = map.get(child);
                root.neighbors.add(neighbor);
            }
        }
    }
    public Node cloneGraph(Node node) {
        if(node==null)
            return null;
        root = new Node(node.val);
        dfs(root,node);
        return root;
    }
}