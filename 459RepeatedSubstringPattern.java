public class Solution {
    public boolean repeatedSubstringPattern(String str) {
        /*
        1. substring的长度肯定是str的约数(str%substring==0), 
        2. 遍历所有可能的substring(outer for),可以从str.length()/2开始，到1
        3. check(inner for): 从头-依次-取长度为str约���数的substring，然后通过叠加(str.length()/substring.length())个substring，看结果是不是str
        */     
        for(int l = str.length()/2;l>0;l--){
            if(str.length()%l==0){
                String sub = str.substring(0,l);
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i<str.length()/l;i++){
                    sb.append(sub);
                }
                if(sb.toString().equals(str)){
                    return true;
                }
            }
        }
        return false;
    }
}