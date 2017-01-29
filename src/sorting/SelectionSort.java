/* Name: SelectionSort
 * Author: Devon McGrath
 * Description: This class implements selection sort.
 * 
 * Version History:
 * 1.1 - 10/17/2016 - Now extends the 'SortingAlgorithm' class - Devon McGrath
 * 1.0 - 10/20/2015 - Initial version - Devon McGrath
 */

package sorting;

public class SelectionSort extends SortingAlgorithm {

	/** the display name of the algorithm. */
	public static final String DISPLAY_NAME = "Selection Sort";
	
	public SelectionSort(int[] arr) {
		super(arr, DISPLAY_NAME, 120);
	}

	//Method that sorts an integer array in a single step
	public static void sort(int[] array) {

		int smallestLocation;

		//Loop through the array array.length times
		for (int run = 0; run < array.length; run ++) {

			smallestLocation = run;

			//Find the smallest location starting from the run location
			for (int test = run; test < array.length; test ++) {
				if (array[test] < array[smallestLocation]) {
					smallestLocation = test;
				}
			}

			//Swap the values
			int hold = array[run];
			array[run] = array[smallestLocation];
			array[smallestLocation] = hold;
		}
	}

	//Method that completes one sort step on an integer array
	public static void sortNextRun(int[] array, int run) {

		int smallestLocation = run;

		//Find the smallest location starting from the run location
		for (int test = run; test < array.length; test ++) {
			if (array[test] < array[smallestLocation]) {
				smallestLocation = test;
			}
		}

		//Swap the values
		int hold = array[run];
		array[run] = array[smallestLocation];
		array[smallestLocation] = hold;
	}

	//Method that completes one sort step on an integer array (method 2)
	public static void sortNextRun2(int[] array, int run) {

		//Only complete the task if the run is appropriate
		if (array.length / 2 + array.length % 2 > run) {

			int smallestLocation = run;
			int largestLocation = run;

			//Loop through the array
			for (int test = run; test < array.length - run; test ++) {

				//Find the smallest location starting from the run location
				if (array[test] < array[smallestLocation]) {
					smallestLocation = test;
				}
			}

			//Swap the values
			int hold = array[run]; //Smallest location
			array[run] = array[smallestLocation];
			array[smallestLocation] = hold;

			//Loop through the array
			for (int test = run; test < array.length - run; test ++) {

				//Find the largest location starting from the run location
				if (array[test] > array[largestLocation]) {
					largestLocation = test;
				}
			}

			//Swap the values
			hold = array[array.length - run - 1]; //Largest location
			array[array.length - run - 1] = array[largestLocation];
			array[largestLocation] = hold;
		}
	}

	@Override
	public void sort() {
		
		int smallestLocation;

		// Sort the array
		for (int run = 0; run < arr.length; run ++) {

			smallestLocation = run;

			// Find the minimum value's index
			for (int test = run+1; test < arr.length; test ++) {
				if (arr[test] < arr[smallestLocation]) {
					smallestLocation = test;
				}
			}

			// Swap the values
			if (smallestLocation != run) {
				int hold = arr[run];
				arr[run] = arr[smallestLocation];
				arr[smallestLocation] = hold;
			}
		}
	}

	@Override
	public void sort(int step) {

		int smallestLocation = step;

		// Find the minimum value's index
		for (int test = step+1; test < arr.length; test ++) {
			if (arr[test] < arr[smallestLocation]) {
				smallestLocation = test;
			}
		}

		// Swap the values
		if (smallestLocation != step) {
			int hold = arr[step];
			arr[step] = arr[smallestLocation];
			arr[smallestLocation] = hold;
		}
	}

	@Override
	public int getStartingRunNumber() {
		return 0;
	}
}
