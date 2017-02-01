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