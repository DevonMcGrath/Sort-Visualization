/* Name: FastSelectionSort
 * Author: Devon McGrath
 * Description: This class implements selection sort. It sorts the array in
 * half the amount of steps the normal algorithm performs.
 * 
 * Version History:
 * 1.0 - 10/18/2016 - Initial version - Devon McGrath
 */

package sorting;

public class FastSelectionSort extends SortingAlgorithm {

	/** the display name of the algorithm. */
	public static final String DISPLAY_NAME = "Selection Sort (Fast)";
	
	public FastSelectionSort(int[] arr) {
		super(arr, DISPLAY_NAME, 120);
	}

	@Override
	public void sort() {

		// Sort the array
		final int lim = arr.length/2 + arr.length%2;
		for (int step = 0; step < lim; step ++) {

			int minIndex = step;

			// Find the index of the minimum value
			for (int test = step+1; test < arr.length - step; test ++) {
				if (arr[test] < arr[minIndex]) {
					minIndex = test;
				}
			}

			// Swap the values (minimum value to front of array)
			if (minIndex != step) {
				int hold = arr[step];
				arr[step] = arr[minIndex];
				arr[minIndex] = hold;
			}

			int maxIndex = step+1;

			// Find the index of the maximum value
			for (int test = step+2; test < arr.length - step; test ++) {
				if (arr[test] > arr[maxIndex]) {
					maxIndex = test;
				}
			}

			// Swap the values (maximum value to end of array)
			if (maxIndex != step+1) {
				int hold = arr[arr.length - step - 1];
				arr[arr.length - step - 1] = arr[maxIndex];
				arr[maxIndex] = hold;
			}
		}
	}

	@Override
	public void sort(int step) {

		// Only complete the task if the run is appropriate
		if (arr.length / 2 + arr.length % 2 > step) {

			int minIndex = step;

			// Find the index of the minimum value
			for (int test = step+1; test < arr.length - step; test ++) {
				if (arr[test] < arr[minIndex]) {
					minIndex = test;
				}
			}

			// Swap the values (minimum value to front of array)
			int hold = arr[step];
			arr[step] = arr[minIndex];
			arr[minIndex] = hold;

			int maxIndex = step+1;
			
			// Find the index of the maximum value
			for (int test = step+2; test < arr.length - step; test ++) {
				if (arr[test] > arr[maxIndex]) {
					maxIndex = test;
				}
			}

			// Swap the values (maximum value to end of array)
			hold = arr[arr.length - step - 1];
			arr[arr.length - step - 1] = arr[maxIndex];
			arr[maxIndex] = hold;
		}
	}

	@Override
	public int getStartingRunNumber() {
		return 0;
	}

}
