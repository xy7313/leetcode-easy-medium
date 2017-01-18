  //我以前ac的代码，虽然不一定是我写的吧，但我还是觉得我好机智啊，这个代码感觉不错啊
  //注意，这里把string转成char[]会更快一点！！另外hashmap也是个不错的选择，
  public boolean isSubsequence(String s, String t) {
       if(t.length() < s.length()) return false;
       int i=0,j=0;
       while(i<s.length()&&j<t.length()){
           if(s.charAt(i)==t.charAt(j)) i++;
           j++;
       }
       return i==s.length();
    }