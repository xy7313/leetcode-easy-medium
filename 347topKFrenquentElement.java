// use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency
// implement maxHeap by seting Comparator: (a,b)->(b.getValue()-a.getValue()),if we set (a,b)->(a.getValue()-b.getValue()), we get minHeap, which is priority queue.
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        /*
        //复杂版 my code Vs. others
        Map<Integer, Integer> map = new HashMap<>();
        for(int i= 0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
                         new PriorityQueue<>(map.size(),new Comparator<Map.Entry<Integer, Integer>>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> a,Map.Entry<Integer, Integer> b){
              return b.getValue()-a.getValue();
            }
        });
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        */
        
        //简化版，for和while写到一行的话这个答案只有7lines
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        } 
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =  new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));  
        maxHeap.addAll(map.entrySet());
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            res.add(maxHeap.poll().getKey());
        }
        return res;
    }
}