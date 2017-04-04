public int canCompleteCircuit(int[] gas, int[] cost) {
	int remain = 0; // track total remaining
	int total = 0; 
	int start = 0; 
 
	for (int i = 0; i < gas.length; i++) {
		int curRemaining = gas[i] - cost[i];
 
		//if total remain of (i-1) >= 0, update total remain with adding current remaining 
		if (remain >= 0) { 
			remain += curRemaining;
		//otherwise, reset start index to be current, update: total remain=current remain
		} else {
			remain = curRemaining;
			start = i;
		}
		total += curRemaining;
	}
    //start from idx k, we know that we can get gas[gas.length-1] from gas[k], then we need to know if we can get gas[k-1] from gas[0]. we use total 
	if (total >= 0){
		return start;
	}
	return -1;
}