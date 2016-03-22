package sorting;

public class BubbleSort {
	
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

	//Method to sort a floating point array in a single step
	public static void sort(float[] array) {

		//Loop through the sorting process array.length times
		for (int run = 1; run < array.length; run ++) {

			//Loop for comparing two values that checks the entire array
			for (int test = 0; test < array.length - run; test ++) {

				//Check if the values should be swapped
				if (array[test] > array[test+1]) {

					//Swap the value
					float hold = array[test];
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

	//Method to complete one step of the sort
	public static void sortNextRun(float[] array, int run) {

		//Loop for comparing two values that checks the entire array
		for (int test = 0; test < array.length - run; test ++) {

			//Check if the values should be swapped
			if (array[test] > array[test+1]) {

				//Swap the value
				float hold = array[test];
				array[test] = array[test+1];
				array[test+1] = hold;
			}
		}
	}
}
