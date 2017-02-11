public List<List<Integer>> zigzagLevelOrder(TreeNode root) {        
    List<List<Integer>> res = new ArrayList<>();
    if(root == null) return res;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    boolean order = true;

    while(!q.isEmpty()) {
        List<Integer> tmp = new ArrayList<>();
        int size = q.size();
        for(int i = 0; i < size; ++i) {
            TreeNode n = q.poll();
            if(order) {
                tmp.add(n.val);
            } else {
                //Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
                tmp.add(0, n.val);
            }
            if(n.left != null) q.add(n.left);
            if(n.right != null) q.add(n.right);
        }
        res.add(tmp);
        order = !order;
    }
    return res;
}

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
         ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if(root==null) return results;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean zigzag = false;
        while(!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<Integer>();
            int size = queue.size();
            for(int i = 0; i<size; i++){
                TreeNode node = queue.poll();
                level.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            if(zigzag)  Collections.reverse(level);
            zigzag = !zigzag;
            results.add(level);
        }
        return results;
    }
    //这前两种复杂度应该差不多吧，一个用了shift，一个用了reverse，都增加了for循环中的工作量
    