/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    //divide and conquer
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        
        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.val);
        
        return result;
    }
    //iteration
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> postorder = new ArrayList<>();
        TreeNode prev = null;
        TreeNode curr = root;
        
        if(root == null) return postorder;
        
        stack.push(root);
        while(!stack.empyt()){
            curr = stack.peek();
            // traverse down the tree
            if(prev == null || prev.left==curr || prev.right==curr){
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            }else if (curr.left == prev){//traverse up from left
                if (curr.right != null) {
                    stack.push(curr.right);
                }
            }else{// traverse up from right
                postorder.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }
        
        return postorder;
    }
}