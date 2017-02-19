##刚好看到树，以下全是树的题
94. Binary Tree Inorder Traversal
145. Binary Tree Postorder Traversal
144. Binary Tree Preorder Traversal
104. Maximum Depth of Binary Tree
111. Minimum Depth of Binary Tree
110. Balanced Binary Tree
257. Binary Tree Paths



####94. Binary Tree Inorder Traversal
看过答案后自己写的时候卡在了内层while判断条件和最后cur=cur.right 内层循环条件是为了帮助找到当前root的最左下角的，cur=cur.right 是左边和root完成后去处理右边
```
while(cur!=null || !stack.empty()){
    while(cur != null){
        stack.push(cur);
        cur = cur.left;
    }  
    cur = stack.pop();
    result.add(cur.val);
    cur = cur.right;
}
```

####145. Binary Tree Postorder Traversal
1. divide and conquer 这种跟下面pre的基本一样
2. iteration 这种很难想，贴代码，背
```
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
```

####144. Binary Tree Preorder Traversal
1. divide and conquer 这种跟上面post的基本一样
2. iteration 注意要先把当前节点存入result，然后再把子节点放入stack，
```
stack.push(root);
while(!stack.empty()){
    TreeNode cur = stack.pop();
    result.add(cur.val);
    if(cur.right!=null) stack.push(cur.right);
    if(cur.left!=null) stack.push(cur.left); 
}
```

####104. Maximum Depth of Binary Tree
divide-conquer，对队规还是不太熟，感觉难点在于return什么
```
public int maxDepth(TreeNode root) {
    int dep = 0;
    if(root==null) return dep;
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left,right)+1;
}
```

####111. Minimum Depth of Binary Tree
大眼一看跟上面这个题差不多，但还是上面说的，需要注意return什么，这里出现了特殊情况。强行解释一波，根据题目描述“The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.”可以不太严谨的理解为，这个min至少是2，所以如果左或右的minDep==0，不能直接取min+1了。
`return (left == 0 || right == 0) ? left + right + 1:Math.min(left,right)+1;`

####110. Balanced Binary Tree
不用result大类的方法比较简单，贴了代码，因为返回是boolean，所以实现起来不需要保留当前根节点，如过这种情况还是想用Result class，class中可以存放一个boolean，一个maxDepth。
```
public boolean isBalanced(TreeNode root) {
    return maxDepth(root)!=-1;
}
public int maxDepth(TreeNode root){
    if(root== null) return 0;
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    if(left==-1 || right==-1 || Math.abs(right-left)>1){
        return -1;
    }
    return Math.max(left,right)+1;
}
```

####257. Binary Tree Paths
分治法，每次到leaf，添加leaf到path，每次到root，给root左右子树分别添加root当做path
```
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        
        if(root==null) return paths;
        if(root.left==null&&root.right==null){
            paths.add(root.val+"");
            return paths;
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        for(String l: left){
            paths.add(root.val+"->"+l);
        }
        for(String r : right){
            paths.add(root.val+"->"+r);
        }
        return paths;
    }
}
```