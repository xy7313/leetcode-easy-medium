/*
Linkedin

Find k closest elements to a given value
Given a sorted array arr[] and a value X, find the k closest elements to X in arr[]. 
Examples:

Input: K = 4, X = 35
       arr[] = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56}
Output: 30 39 42 45


A simple solution is to do linear search for k closest elements.
1) Start from the first element and search for the crossover point (The point before which elements are smaller than or equal to X and after which elements are greater). This step takes O(n) time.
这个crossover point到底是什么是通过findCrossOver这个方法看出来的，三种情况，如果 target > max  返回 min ； 如果 target < min  返回 min ；其他情况，返回离他所在区间的左区间值 （eg: if (arr[mid] <= target && arr[mid+1] > target) return mid;），比如这个例子的35，如果target改成36，还是返回35，但如果改成34，则返回30，体会一下。。

2) Once we find the crossover point, we can compare elements on both sides of crossover point to print k closest elements. This step takes O(k) time.

The time complexity of the above solution is O(n).

An Optimized Solution is to find k elements in O(Logn + k) time. The idea is to use Binary Search to find the crossover point. Once we find index of crossover point, we can print k closest elements in O(k) time.

*/
class KClosest{
    public static void main(String args[]){
		KClosest kc = new KClosest();
		int arr[] = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};
		int n = arr.length;
		int x = 34, k = 4;
		kc.printKclosest(arr, x, 4, n);
	}

	//O(n)
	int findCrossOver(int arr[], int low, int high, int x){
		// Base cases： x is greater than all or smaller than all
		if (arr[high] <= x) return high;
		if (arr[low] > x) return low;

		// Binary Search-xy
		int mid = (low + high)/2; 

		/* If x is same as middle element, then return mid */
		// if (arr[mid] <= x && arr[mid+1] > x) return mid;

		/* If x is greater than arr[mid], then either arr[mid + 1]
		is ceiling of x or ceiling lies in arr[mid+1...high] */
		if(arr[mid] < x) return findCrossOver(arr, mid+1, high, x);
        else if(arr[mid] > x) return findCrossOver(arr, low, mid - 1, x);
		return mid;
	}

    //O(k)
	void printKclosest(int arr[], int x, int k, int n){
		// Find the crossover point
		int l = findCrossOver(arr, 0, n-1, x); 
		System.out.println(l);
		int r = l+1; // Right index to search
		int count = 0; // To keep track of count of elements

		// If x is present in arr[], then reduce left index
		// Assumption: all elements in arr[] are distinct
		if (arr[l] == x) l--;

		// Compare elements on left and right of crossover
		// point to find the k closest elements
        //注意这里打印的时候是有移动左右index操作的
		while (l >= 0 && r < n && count < k){
			if (x - arr[l] < arr[r] - x) System.out.print(arr[l--]+" ");
			else System.out.print(arr[r++]+" ");
			count++;
		}

		// If there are no more elements on right side, then
		// print left elements
		while (count < k && l >= 0){
			System.out.print(arr[l--]+" ");
			count++;
		}

		// If there are no more elements on left side, then
		// print right elements
		while (count < k && r < n){
			System.out.print(arr[r++]+" ");
			count++;
		}
	}
	
}
