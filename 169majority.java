// 先是一个很简单也很取巧的方法，应该是利用了题里说的majority一定存在这一点
public int majorityElement(int[] nums) {
        int c = 1;
        int m=nums[0];
        for (int a=1;a<nums.length;a++){
            if (m==nums[a]){
                c++;
            }else{
                c--;
                if(c==0){
                    System.out.println("else");
                    m=nums[a];
                    c=1;
                }
            }
        }
        return m;
    }

// 这个是传统的hashmap写法
public class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> m = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            if(m.containsKey(nums[i])){
                m.put(nums[i],m.get(nums[i])+1);
            }else{
                m.put(nums[i],1);
            }
        }
        for(Integer k: m.keySet()){
            if((m.get(k)>(nums.length-1)/2)){
                return k;
            }
        }
        return 0;
    }
}