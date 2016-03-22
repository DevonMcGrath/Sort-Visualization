package sorting;

public class SelectionSort {

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

	//Method that sorts a floating point array in a single step
	public static void sort(float[] array) {

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
			float hold = array[run];
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

	//Method that completes one sort step on an integer array (method 1)
	public static void sortNextRun(float[] array, int run) {

		int smallestLocation = run;

		//Find the smallest location starting from the run location
		for (int test = run; test < array.length; test ++) {
			if (array[test] < array[smallestLocation]) {
				smallestLocation = test;
			}
		}

		//Swap the values
		float hold = array[run];
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

	//Method that completes one sort step on a floating point array (method 2)
	public static void sortNextRun2(float[] array, int run) {

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
			float hold = array[run]; //Smallest location
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
}
