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

99 Recover Binary Search Tree
100 Same Tree
101 Symmetric Tree
102 Binary Tree Level Order Traversal
103 Binary Tree Zigzag Level Order Traversal
104 Maximum Depth of Binary Tree
105 Construct Binary Tree from Preorder and Inorder Traversal
106 Construct Binary Tree from Inorder and Postorder Traversal


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
find root(mid of array), divide array into two part, find next root in left and right.
```
public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length==0) return null;
  TreeNode head = helper(nums,0,nums.length-1);
  return head;
}
TreeNode helper(int[] nums, int start, int end){
  if(start>end) return null;
  int mid = (start+end)/2;
  //mid==>root/parent
  TreeNode cur = new TreeNode(nums[mid]);
  cur.left = helper(nums,start,mid-1);
  cur.right = helper(nums,mid+1,end);
  return cur;
}
```

##### 109. Convert Sorted List to Binary Search Tree
very similar to the problem above, just using different way to find mid of linked list
```
public TreeNode sortedListToBST(ListNode head) {
  if(head==null) return null;
  return toBST(head,null);

}
TreeNode toBST(ListNode head, ListNode tail){
  if(head==tail) return null;
  ListNode runner = head;
  ListNode walker = head;
  //go through all nodes between start and tail
  while(runner!=tail && runner.next!=tail){
      runner = runner.next.next;
      walker = walker.next;
  }
  //ListNode==>TreeNode
  TreeNode cur = new TreeNode(walker.val);
  cur.left = toBST(head,walker);
  cur.right = toBST(walker.next,tail);
  return cur;
}
```


##### 110 Balanced Binary Tree
1) Left subtree of T is balanced
2) Right subtree of T is balanced
3) The difference between heights of left subtree and right subtree is not more than 1.(we use -1)

```
public boolean isBalanced(TreeNode root) {
 return  maxDep(root)!=-1;
}
public int maxDep(TreeNode root){
  if(root==null) return 0;
  int left = maxDep(root.left);
  int right = maxDep(root.right);
  //one child -1, always -1
  if(left==-1 || right==-1 || Math.abs(right-left)>1){
      return -1;
  }
  return Math.max(left,right)+1;
}
```

##### 111 Minimum Depth of Binary Tree
can not return min in left and right directly like we did in computing max dep.
```
public int minDepth(TreeNode root) {
        if(root==null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //if right or left == 0 means only one child, we can not choose min because the min will be 0,
        //we can also write if,else if here to state more clearer.
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left, right)+1;
    }
```

##### 112 Path Sum

```
public boolean hasPathSum(TreeNode root, int sum) {
  if(root==null) {
      return false;
  }
  if(root.left==null && root.right==null && root.val==sum){
      return true;
  } 
  //递归参数==stack个数
  return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);
}
```

##### 113 Path Sum II
want the exact path not just if this path exist or not.

backtracking
```
public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) return result;
        List<Integer> path = new ArrayList<>();
        helper(root,path,result,sum);
        return result;
    }
    void helper(TreeNode root,List<Integer> path, List<List<Integer>> result,int sum){
        if(root==null) return;
        path.add(new Integer(root.val));
        
        if (root.left == null && root.right == null && sum == root.val) {
		    result.add(new LinkedList(path));
	    } else {
		    helper(root.left, path, result, sum - root.val);
		    helper(root.right, path, result, sum - root.val);
	    }
	    path.remove(path.size() - 1);
        return;


    }
```

##### 114 Flatten Binary Tree to Linked List
node.left=null, node,right=
```
public class Solution {
// method 1: iteration
//    public void flatten(TreeNode root) {
//         if(root==null) return;

//         Stack<TreeNode> s = new Stack<>();
//         s.push(root);
        
//         while(!s.empty()){
//             TreeNode cur = s.pop();
//             if(cur.right!=null){
//                 s.push(cur.right);
//             }
//             if(cur.left!=null){
//                 s.push(cur.left);
//             }
//             cur.left=null;
//             if(s.empty()){
//                 cur.right=null;
//             }else{
//                 cur.right = s.peek();
//             }
//         }
//     }
//method 2 recursion 
private TreeNode lastNode;
    public void flatten(TreeNode root) {
        if(root==null) return;
        
        if(lastNode !=null){
            lastNode.left = null;
            lastNode.right = root;
        }
        
        lastNode = root;
    // why can not use flatten(root.rifht) directly 
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    
    }
}
```

##### 116 Populating Next Right Pointers in Each Node

##### 117 Populating Next Right Pointers in Each Node II

##### 124 Binary Tree Maximum Path Sum
we need to keep a global variable to record the max path
```
public class Solution {
    int maxV;
    public int maxPathSum(TreeNode root) {
        maxV = Integer.MIN_VALUE;
        maxPath(root); 
        return maxV;
    }
    int maxPath(TreeNode node){
        if(node==null) return 0;
        int left = Math.max(0,maxPath(node.left));
        int right = Math.max(0,maxPath(node.right));
        int cur = left+right+node.val;
        maxV = Math.max(maxV, cur);
        //path
        return Math.max(left,right)+node.val;
    }
}
```
##### 129 Sum Root to Leaf Numbers
sum
```
public int sumNumbers(TreeNode root) {
	return sum(root, 0);
}

public int sum(TreeNode n, int subsum){
	if (n == null) return 0;
	if (n.right == null && n.left == null) return subsum*10 + n.val;
	return sum(n.left, subsum*10 + n.val) + sum(n.right, subsum*10 + n.val);
}
```

##### 144 Binary Tree Preorder Traversal
root->left->right
```
public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if(root == null){
            return preorder;
        }   
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            preorder.add(cur.val);
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                 stack.push(cur.left);
            }
        }
        return preorder;
    }
```

##### 145 Binary Tree Postorder Traversal
```
public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<>();
        if(root==null) return postorder;
        Stack<Object> stack = new Stack<Object>();
        stack.push(root);
        while(!stack.isEmpty()){
            Object cur = stack.pop();
            if(cur instanceof TreeNode){
                TreeNode curNode = (TreeNode) cur;
                stack.push(curNode.val);
                if(curNode.right != null){
                    stack.push(curNode.right);
                }
                if(curNode.left != null){
                    stack.push(curNode.left);
                }
                
            }else{
                postorder.add((Integer)cur);
            }
        }
        return postorder;
    }
```

##### 173 Binary Search Tree Iterator


##### 199 Binary Tree Right Side View
BFS, only record the right side nodes
```
public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList();        
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    result.add(cur.val);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
            
        }
        return result;
    }
```

##### 222 Count Complete Tree Nodes
we trace the depth to calculate left tree nodes and right tree nodes, then sum them.
```
 public int countNodes(TreeNode root) {
        if(root==null) return 0;
        int leftDepth = leftDepth(root);
	    int rightDepth = rightDepth(root);
        if(leftDepth == rightDepth){
            return (1 << leftDepth) - 1;
        }else{
            return 1+countNodes(root.left) + countNodes(root.right);
        }
    }
    int leftDepth(TreeNode root){
        int dep = 0;
	    while (root != null) {
		    root = root.left;
		    dep++;
	    }
	    return dep;
    }
    int rightDepth(TreeNode root){
        int dep = 0;
	    while (root != null) {
		    root = root.right;
		    dep++;
	    }
	    return dep;
    }
```

##### 226 Invert Binary Tree
BFS + we need a temp node for node exchange.
```
public TreeNode invertTree(TreeNode root) {        
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.right != null) {
                queue.offer(node.right);
            }
            if(node.left != null) {
                queue.offer(node.left);
            }
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
        }
        return root;
    }
```

##### 230 Kth Smallest Element in a BST
we need a counter, and it's BST, it's easier to count
```
public int kthSmallest(TreeNode root, int k) {
        if(root==null) return 0;
        //inorder
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        TreeNode cur = root;
        while( cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;                
            }
            cur = stack.pop();
            count++;
            if(count==k) return cur.val;
            cur = cur.right;            
        }   
           return 0; 
    }
```

##### 235 Lowest Common Ancestor of a Binary Search Tree
##### 236 Lowest Common Ancestor of a Binary Tree

##### 257 Binary Tree Paths
left, right, merge result: 95 unique path, 110 balanced BT, 111 min depth, 112 path sum, 124 path sum, 129 sum of leaf, 222 count complete tree nodes, 
```
public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root==null) return paths;
        if(root.left==null && root.right==null){
            paths.add(root.val+"");
            return paths;
        }
        List<String> left =binaryTreePaths(root.left);
        List<String> right =binaryTreePaths(root.right);
        
        for(String l : left){
            paths.add(root.val+"->"+l);
        }
        for(String r : right){
            paths.add(root.val+"->"+r);
        }
        return paths;
        
    }
```

##### 297 Serialize and Deserialize Binary Tree
tree-->string, string-->tree
```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "{}";
        }
        
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        for(int i = 0; i<list.size(); i++){
            TreeNode cur = list.get(i);
            if(cur==null){
                continue;
            }
            list.add(cur.left);
            list.add(cur.right);
        }
        
        while(list.get(list.size()-1)==null){
            list.remove(list.size()-1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(list.get(0).val);
        for(int i = 1; i<list.size(); i++){
            if(list.get(i)==null){
                sb.append(",#");
            }else{
                sb.append(",");
                sb.append(list.get(i).val);
            }
        }  
        sb.append("}");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("{}")){
            return null;
        }
        String[] nodes = data.substring(1,data.length()-1).split(",");
        ArrayList<TreeNode> list = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        list.add(root);
        int index = 0;
        boolean isLeftChild = true;
        for(int i = 1; i<nodes.length; i++){
            if(!nodes[i].equals("#")){
                TreeNode node = new TreeNode(Integer.parseInt(nodes[i]));
                if(isLeftChild){
                    list.get(index).left = node;
                }else{
                    list.get(index).right = node;
                }
                list.add(node);
            }
            if(!isLeftChild){
                index++;
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```
