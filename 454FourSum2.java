public class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> sums = new HashMap<>();
        for(int i = 0; i< A.length; i++){
            for(int j=0;j<B.length;j++){
			    int sum = A[i]+B[j];
			    if(sums.containsKey(sum)) {
				    sums.put(sum, sums.get(sum)+1);
			    } else {
				    sums.put(sum, 1);
			    }
		    }
        }
        int count = 0;
        for(int i = 0; i< C.length; i++){
            for(int j=0;j<D.length;j++){
			    int sum = 0-(C[i]+D[j]);
			    if(sums.containsKey(sum)) {
				    count+=sums.get(sum);
			    }
		    }
        }
        return count;
    }
}