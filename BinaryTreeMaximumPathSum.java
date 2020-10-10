/**
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int result = Integer.MIN_VALUE;
    public int maxPathSumUtil(TreeNode root) {
        if(root==null)
            return 0;
        int leftSum = maxPathSumUtil(root.left);
        int rightSum = maxPathSumUtil(root.right);
        if(leftSum < 0) leftSum = 0;
        if(rightSum < 0) rightSum = 0;
        result = Math.max(result, leftSum + rightSum + root.val );
        return Math.max(leftSum,rightSum) + root.val;
    }
    
    public int maxPathSum(TreeNode root){
        maxPathSumUtil(root);
        return result;
    }
}