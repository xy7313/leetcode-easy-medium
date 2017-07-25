## Second part: Binary Tree 

#### Problem list:
```
94 Binary Tree Inorder Traversal
95 Unique Binary Search Trees II
96 Unique Binary Search Trees
98 Validate Binary Search Tree
99 Recover Binary Search Tree
100 Same Tree
101 Symmetric Tree
102 Binary Tree Level Order Traversal
103 Binary Tree Zigzag Level Order Traversal
104 Maximum Depth of Binary Tree
105 Construct Binary Tree from Preorder and Inorder Traversal
106 Construct Binary Tree from Inorder and Postorder Traversal
107 Binary Tree Level Order Traversal II
108 Convert Sorted Array to Binary Search Tree
109 Convert Sorted List to Binary Search Tree
110 Balanced Binary Tree
111 Minimum Depth of Binary Tree
112 Path Sum
113 Path Sum II
114 Flatten Binary Tree to Linked List
116 Populating Next Right Pointers in Each Node
117 Populating Next Right Pointers in Each Node II
124 Binary Tree Maximum Path Sum
129 Sum Root to Leaf Numbers
144 Binary Tree Preorder Traversal
145 Binary Tree Postorder Traversal
173 Binary Search Tree Iterator
199 Binary Tree Right Side View
222 Count Complete Tree Nodes
226 Invert Binary Tree
230 Kth Smallest Element in a BST
235 Lowest Common Ancestor of a Binary Search Tree
236 Lowest Common Ancestor of a Binary Tree
257 Binary Tree Paths
297 Serialize and Deserialize Binary Tree
```


##### 94 Binary Tree Inorder Traversal
inorder: left-root-right，
```
public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> result = new ArrayList<>();
      if(root==null) return result;
      Stack<TreeNode> stack = new Stack<>();
      TreeNode cur = root;
      while(cur!=null || !stack.empty()){
          while(cur!=null){
              stack.push(cur);
              cur = cur.left;
          }
          cur = stack.pop();
          result.add(cur.val);
          cur = cur.right;
      }
      return result;
  }
```


##### 95 Unique Binary Search Trees II
divide-conquer. divide:0 ~ i-1 & i+1 ~ n; conquer: i.left = left, i.right = right;     
```
public List<TreeNode> generateTrees(int n) {
    if (n <= 0) {
        return new ArrayList<TreeNode> ();
    }
    return generate(1, n);
}
 public ArrayList<TreeNode> generate(int start, int end) {
    ArrayList<TreeNode> list = new ArrayList<TreeNode>();
    if (start > end) {
        list.add(null);
        return list;
    }

    for (int i=start; i<=end; i++) {
        ArrayList<TreeNode> left = generate(start, i-1);
        ArrayList<TreeNode> right = generate(i+1, end);
        for (TreeNode l : left) {
            for (TreeNode r : right) {
                TreeNode root = new TreeNode(i);
                root.left = l;
                root.right = r;
                list.add(root);
            }
        }
    }
    return list;
}
    
```

##### 96 Unique Binary Search Trees 
dp

##### 98 Validate Binary Search Tree
root.left<=root<=root.right
```
public boolean isValidBST(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
    if (root == null) return true;
    if (root.val >= maxVal || root.val <= minVal) return false;
    return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);

}
```

##### 107 Binary Tree Level Order Traversal II
just like level order traversal, the original version. We reverse each level, then add it to the result.
```
 public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    if(root == null) return result;

    //queue implemented using linked list
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while(!queue.isEmpty()){
        int size = queue.size();
        System.out.println(size);
        List<Integer> level = new ArrayList<>();
        for(int i = 0; i<size; i++){
            TreeNode head = queue.poll();
            level.add(head.val);
            //if, if, not if else
            if(head.left!=null){
                queue.offer(head.left);
            }
            if(head.right!=null){
                queue.offer(head.right);
            }
        }
        result.add(level);
    }
    Collections.reverse(result);
    return result;
}
```

##### 108. Convert Sorted Array to Binary Search Tree
find root, divide array into two part
