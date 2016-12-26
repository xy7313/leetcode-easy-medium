public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;        
        for (int house : houses) {
        	int index = Arrays.binarySearch(heaters, house);
        	if (index < 0) {
        		index = -(index + 1);
        	}
        	//如果<0，说明house在最左边heater的左边，dist1=max无意义，实际算的距离是dist2，此时=heaters[index] - house
        	int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
        	int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;        	
        	result = Math.max(result, Math.min(dist1, dist2));
        }       
        return result;
    }
}
//如果它包含在数组中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。插入点被定义为将键插入数组的那一点：即第一个大于此键的元素索引。