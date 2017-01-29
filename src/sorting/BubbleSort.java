/* Name: BubbleSort
 * Author: Devon McGrath
 * Description: This class implements bubble sort.
 * 
 * Version History:
 * 1.1 - 10/17/2016 - Now extends the 'SortingAlgorithm' class - Devon McGrath
 * 1.0 - 10/20/2015 - Initial version - Devon McGrath
 */

package sorting;

public class BubbleSort extends SortingAlgorithm {
	
	/** the display name of the algorithm. */
	public static final String DISPLAY_NAME = "Bubble Sort";
	
	public BubbleSort(int[] arr) {
		super(arr, DISPLAY_NAME, 120);
	}

	//Method to sort an integer array in a single step
	public static void sort(int[] array) {

		//Loop through the sorting process array.length times
		for (int run = 1; run < array.length; run ++) {

			//Loop for comparing two values that checks the entire array
			for (int test = 0; test < array.length - run; test ++) {

				//Check if the values should be swapped
				if (array[test] > array[test+1]) {

					//Swap the value
					int hold = array[test];
					array[test] = array[test+1];
					array[test+1] = hold;
				}
			}
		}
	}

	//Method to complete one step of the sort
	public static void sortNextRun(int[] array, int run) {

		//Loop for comparing two values that checks the entire array
		for (int test = 0; test < array.length - run; test ++) {

			//Check if the values should be swapped
			if (array[test] > array[test+1]) {

				//Swap the value
				int hold = array[test];
				array[test] = array[test+1];
				array[test+1] = hold;
			}
		}
	}

	@Override
	public void sort() {

		// Loop through the array
		for (int run = 1; run < arr.length; run ++) {

			// Compare values
			for (int test = 0; test < arr.length - run; test ++) {

				// Check if the values should be swapped
				if (arr[test] < arr[test+1]) {

					// Swap the value
					int hold = arr[test];
					arr[test] = arr[test+1];
					arr[test+1] = hold;
				}
			}
		}
		
	}

	@Override
	public void sort(int step) {

		// Compare values
		for (int test = 0; test < arr.length - step; test ++) {

			// Check if the values should be swapped
			if (arr[test] > arr[test+1]) {

				// Swap the value
				int hold = arr[test];
				arr[test] = arr[test+1];
				arr[test+1] = hold;
			}
		}
		
	}

	@Override
	public int getStartingRunNumber() {
		return 1;
	}
}
