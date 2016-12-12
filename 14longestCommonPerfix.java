 public String longestCommonPrefix(String[] strs) {
    /*string.indexOf(String str): 
        Returns the index within this string of the first occurrence of the specified substring.
        If no such value of k exists, then -1 is returned.
      public String substring(int beginIndex,int endIndex)
        Returns a new string that is a substring of this string. 
        The substring begins at the specified beginIndex and extends to the character at index endIndex - 1. 
        Thus the length of the substring is endIndex-beginIndex.
        --JAVA API
    */
        if (strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++){
            //剪短pre直到pre是strs[i]的prefix
            while(strs[i].indexOf(pre) != 0){
                pre = pre.substring(0,pre.length()-1);
            }
        }
        return pre;
    }