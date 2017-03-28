##subset：dfs+backtracking系列
78. Subset
90. Subset2
46. permutation
00. permutation2
39. combination sum
40. Combination Sum II
216. Combination Sum III
79. Word Search
212. Word Search II

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

####39. combination sum
第二次遇到这个题，dfs可以独立写出来了，背的不错，需要注意的：

1. 结果里每种combination都只能出现一次，又每个数字使用次数不限，所以需要去重，（如果不removeDuplicate，也可直接排序一下，不过remove Diplicates应该复杂度低一些）
2. 居然写不出bug-free的removeDuplicates，我总记得上次这个代码是自己写的而且没啥问题啊。。。

```
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> coms = new ArrayList<>();
        if(candidates==null || candidates.length==0){
            return coms;
        }
        //结果里每种combination都只能出现一次，又每个数字使用次数不限，所以需要去重
        int[] nums = removeDuplicates(candidates);
        ArrayList<Integer> com = new ArrayList<>();
        dfs( 0, target,nums,com, coms);
        return coms;
    }
    private void dfs(int startIdx, int target, int[] candidates, ArrayList<Integer> com, List<List<Integer>> coms){
        if(target==0){
            coms.add(new ArrayList<Integer>(com));
            return;
        }
        for(int i = startIdx; i<candidates.length; i++){
            if(target<candidates[i]) break;
            com.add(candidates[i]);
            // System.out.println(target-candidates[i]);
            dfs(i,target-candidates[i],candidates,com,coms);
            com.remove(com.size()-1);
        }
        
    }
    private int[] removeDuplicates(int[] cands){
        Arrays.sort(cands);
        int idx = 1;
        for(int i = 1; i<cands.length; i++){
            if(cands[i]!=cands[i-1]){
                cands[idx] = cands[i];
                idx++;
            }
        }
        
        int[] nums = new int[idx];
        for(int i = 0; i<nums.length; i++){
            nums[i] = cands[i];
        }
        return nums;
    }
}
```

####40. Combination Sum II
######这题和上题的题目区别在于

Combination sum

1. 结果里每种combination都只能出现一次，又每个数字使用次数不限，所以需要去重
2. 因为每个数不限出现次数，所以for循环中每次dfs都传i进去，而不再是i+1
3. for-loop里还有一点需要注意，target<nums[i] break;

Combination sum2

给定数组中的数字出现几次，combination中总共只能出现几次。也就是每个数只能用一次，当然combinations里还是不能有重复combination。 比如输入是[7,1,2,5,1,6,10], 8，排序后是[1,1,2,5,6,7,10],输出里可以有116，但如果input：1，1，1.target：1， result： 1 不能有三个1的

1. 第一次for-loop，i=startIndex=0，combination先添加1，然后继续找，等所有情况找完之后（此时已递归好多次），
2. 从下一个元素开始，发现又是1，因为刚才已经把包含1的组合都找过了（因为每个数只能用1次，所以可以和1组成combination的都用过了，所以第二个1没有数可以用了），所以这次不再找，continue；而且如果从这个1再找，可能就会找到跟之前那个1组成的一样的combination，但result里，combination不能重复的。总之就是要跳过了


######两道题代码写法的区别有四点：

1. 不需要removeDuplicate（如果原题不removeDuplicate，也可直接排序一下，不过remove Diplicates应该复杂度低一些）
2. 需要sort（第二题这个是必须sort，没有别的选择）
3. 每次dfs开始从i+1，不再是i
4. 多一次去重`if(i!=startIdx && candidates[i]==candidates[i-1]) continue;`

####216. Combination Sum III
不给数组了，直接从1-9，比如：Input: k = 3, n = 7； Output: [[1,2,4]]。 需要注意的是：

1. 从1-9，dfs第一次传入1，for循环<=9
2. com.size==k
其实是个最近本的dfs了
```
public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> coms = new ArrayList<>();
        ArrayList<Integer> com = new ArrayList<>();
        dfs( 1, n, k, com, coms);
        return coms;
    }
     private void dfs(int startIdx, int target, int k, ArrayList<Integer> com, List<List<Integer>> coms){
        if(target==0 && com.size()==k){
            coms.add(new ArrayList<Integer>(com));
            return;
        }
        for(int i = startIdx; i<=9; i++){
            com.add(i);
            dfs(i+1,target-i,k,com,coms);
            com.remove(com.size()-1);
        }
    }
}
```

####79. Word Search
1. To save memory I decuded to apply bit mask for every visited cell.`board[x][y] ^= 256;` it is like setting `board[x][y] = '#'`. The range of char is between 0 - 255. By doing xor with 256, board[x][y] becomes a number >= 256 and thus is different from any character.
```
public class Solution {
    public boolean exist(char[][] board, String word) {
        for (int x=0; x<board.length; x++) {
    	    for (int y=0; y<board[x].length; y++) {
    		    if (dfsHelper(board, x, y , word, 0)) return true;
    	    }
        }

        return false;
    }
    private boolean dfsHelper(char[][] board, int x, int y, String word, int i){
        if (i == word.length()) return true;
        // inBound
	    if (y<0 || x<0 || x >= board.length || y >= board[x].length) return false;
	    
	    if (board[x][y] != word.charAt(i)) return false;
	    //After board[y][x] ^= 256 the char became not a valid letter. After second board[y][x] ^= 256 it became a valid letter again.
	    board[x][y] ^= 256;
	    boolean exist = dfsHelper(board, x+1,y, word, i+1)
		|| dfsHelper(board, x-1, y, word, i+1)
		|| dfsHelper(board, x, y+1, word, i+1)
		|| dfsHelper(board, x, y-1, word, i+1);
	    board[x][y] ^= 256;
	    return exist;
    }
}
```

####212. Word Search II