public class Solution {
     public List<String> readBinaryWatch(int num) {
         List<String> re = new ArrayList<String>();
         for(int h = 0; h <12; h++){
             for(int m = 0; m<12;m++){
                 int count = count(h)+count(m);
                 if(count==num){
                     if (m < 10) {
                        re.add(h + ":0" + m);
                    } else {
                        re.add(h + ":" + m);
                    }
                 }
             }
         }
         return re;
     }
     public int count(int a){
         int c = 0;
         while(a>0){
             if((a&1) ==1){
                 c++;
             }
             a>>=1;
         }
         return c;
         
     }
}

//h + ":" + (m >= 10 ? m : "0" + m) 比 String.format("%d:%02d", h, m) 快上不少。