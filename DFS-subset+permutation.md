##subset：dfs+backtracking系列
####78. Subset
DFS recursion 经典题，可以当做模板来背，画递归树或者按递归步骤推演程序运算过程很有利于理解
tips: lintcode代码中必须加入排序提交才能通过
```
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null||nums.length==0) return results;
        //subset2 add:Arrays.sort(nums);
        ArrayList<Integer> subset = new ArrayList<>();
        dfsHelper(0,nums,subset,results);
        return results;
    }
    private void dfsHelper(int start, int[] nums, ArrayList<Integer> subset, List<List<Integer>> results){
        results.add(new ArrayList(subset));
        for(int i = start; i<nums.length; i++){
            //subset2 add: if(i>start&&nums[i]==nums[i-1]) continue;
            subset.add(nums[i]);
            dfsHelper(i+1,nums,subset,results);
            subset.remove(subset.size()-1);
        }  
    }
}
```

####90. Subset2
跟上题基本一样，多了两步，1 是排序数组，为了把重复元素防止一起，2 是helper里， 如果i不是start(i>start)且nums[i]和左边相邻元素相等，helper不进行，continue进入下一次forloop
代码标注在上面了。

####46. permutation
背会了subset也不会写permutation，好烦躁。这个题可以看出和subset的区别是 拼完之后才放入result中，所以就想helper之后再results.add，但走了条错路，正确的思路是，跟以前一样开始helper的时候result.add不同的是只有当subset==nums.length才添加这个subset. 又挣扎了一会儿发现就算写在后面也可以，写在后面跑一遍发现还是添加了subset那么多东西，就想到要要等三个了在加入results，再想到判断subset大小满足条件才添加，一样可以ac。。。一直觉得递归很难想可能就在于，我怎么都想不到在helper刚进入的时候添加上一次的subset，顶多能看懂，自己想总是把添加操作想在后面，sign。。

这题的关键就在于helper中的 if(){...}
```
//按照subset的格式写的话是这样的
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(nums==null||nums.length==0) return results;
        List<Integer> subset = new ArrayList<>();
        helper(nums,subset,results);
        return results;
    }
    private void helper(int[] nums, List<Integer> subset, List<List<Integer>> results ){
        if(subset.size()==nums.length){
            results.add(new ArrayList<Integer>(subset));
        }else{
            for(int i = 0; i< nums.length; i++){
                //注意这里，for每次都从0开始，而subset是每次从startindex开始，即每次向后顺移一位开始，所以这里，需要if，意思是放入过的数字不再重复放
                if(subset.contains(nums[i])) continue;
                subset.add(nums[i]);
                helper(nums,subset,results);
                subset.remove(subset.size() - 1);
            }
        }
    }
}
```

####permutation2
`if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue; `这个判断判断主要是为了去除重复元素影响。

比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果当中也应该排在前面，这样就保证了唯一性。所以当前面的2还没有使用的时候，就不应该让后面的2使用。

如果看不懂这句话，找一张大点的纸，按照程序写一下执行流程，执行到第二次放入第一个元素1的时候就明白了。
```
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        
    List<List<Integer>> results = new ArrayList<>();
        if(nums==null||nums.length==0) return results;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        ArrayList<Integer> subset = new ArrayList<>();
        dfsHelper(nums,used,subset,results);
        return results;
    }
    private void dfsHelper( int[] nums, boolean[] used, ArrayList<Integer> subset, List<List<Integer>> results){
        if(subset.size()==nums.length){
            results.add(new ArrayList<Integer>(subset));
        }else{
            for(int i = 0; i<nums.length; i++){
                if(used[i]) continue;
                if(i>0 &&nums[i-1]==nums[i] && !used[i-1]) continue;
                used[i] = true;
                subset.add(nums[i]);
                dfsHelper(nums,used,subset,results);
                used[i] = false;
                subset.remove(subset.size()-1);
            }  
        }
    }
}
```
