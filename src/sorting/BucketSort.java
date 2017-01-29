/* Name: BucketSort
 * Author: Devon McGrath
 * Description: This class implements bucket sort.
 * 
 * Version History:
 * 1.1 - 10/17/2016 - Now extends the 'SortingAlgorithm' class - Devon McGrath
 * 1.0 - 10/20/2015 - Initial version - Devon McGrath
 */

package sorting;

public class BucketSort extends SortingAlgorithm {

	/** the display name of the algorithm. */
	public static final String DISPLAY_NAME = "Bucket Sort";
	
	public BucketSort(int[] arr) {
		super(arr, DISPLAY_NAME, 2);
	}

	//Method to sort an integer array in one step
	public static void sort(int[] array) {

		//Get the degree of the largest element and the smallest element
		int degree = getDegree(array);
		int smallest = getSmallest(array);

		int bucket[][] = new int[10][array.length];

		//Loop through the array by degree level
		for (int deg = 0; deg <= degree; deg ++) {

			createNewBucket(bucket, array.length, smallest);

			//Loop through the array and place elements in the bucket
			for (int n = 0; n < array.length; n ++) {
				bucket[(int)((array[n] / Math.pow(10, deg)) % 10)][n] = array[n];
			}

			//Empty the bucket
			emptyBucket(array, bucket, smallest);
		}

	}

	//Sorts an integer array after multiple calls (unless |max| < 10)
	public static void sortNextRun(int[] array, int run) {

		//Get the degree of the largest element and the smallest element
		int degree = getDegree(array);
		if (run <= degree && run >= 0) {
			int smallest = getSmallest(array);

			int bucket[][] = new int[10][array.length];

			createNewBucket(bucket, array.length, smallest);

			//Loop through the array and place elements in the bucket
			for (int n = 0; n < array.length; n ++) {
				bucket[(int)((array[n] / Math.pow(10, run)) % 10)][n] = array[n];
			}

			//Empty the bucket
			emptyBucket(array, bucket, smallest);
		}
	}

	private static void emptyBucket(int[] array, int[][] bucket, int smallest) {

		// Loop through the elements in the bucket
		int index = 0;
		for (int y = 0; y < 10; y ++) {
			for (int x = 0; x < array.length; x ++) {

				// Place the numbers in the array
				if (bucket[y][x] >= smallest) {
					array[index] = bucket[y][x];
					index ++;
				}
			}
		}

	}

	private static void createNewBucket(int[][] bucket, int size, int smallest) {

		// Loop through the bucket and clear it
		for (int y = 0; y < 10; y ++) {
			for (int x = 0; x < size; x ++) {
				bucket[y][x] = smallest - 1;
			}
		}
	}

	public static int getDegree(int[] array) {

		// Find the largest element
		int maxIndex = 0;
		for (int n = 0; n < array.length; n ++) {
			if (array[n] > array[maxIndex]) {
				maxIndex = n;
			}
		}

		// Return the log of largest
		return (int) Math.log10(array[maxIndex]);
	}

	private static int getSmallest(int[] array) {

		// Loop through the array to find the smallest number
		int index = 0;
		for (int n = 0; n < array.length; n ++) {
			if (array[index] > array[n]) {
				index = n;
			}
		}

		return array[index];
	}

	@Override
	public void sort() {
		
		// Get some info about the array
		int degree = getDegree(arr);
		int smallest = getSmallest(arr);

		int bucket[][] = new int[10][arr.length];

		// Loop through the array by degree level
		for (int deg = 0; deg <= degree; deg ++) {

			createNewBucket(bucket, arr.length, smallest);

			// Loop through the array and place elements in the bucket
			for (int n = 0; n < arr.length; n ++) {
				bucket[(int)((arr[n] / Math.pow(10, deg)) % 10)][n] = arr[n];
			}

			// Empty the bucket
			emptyBucket(arr, bucket, smallest);
		}
		
	}

	@Override
	public void sort(int step) {
		
		// Get some info about the array
		int degree = getDegree(arr);
		if (step <= degree && step >= 0) {
			int smallest = getSmallest(arr);

			int bucket[][] = new int[10][arr.length];

			createNewBucket(bucket, arr.length, smallest);

			// Loop through the array and place elements in the bucket
			for (int n = 0; n < arr.length; n ++) {
				bucket[(int)((arr[n] / Math.pow(10, step)) % 10)][n] = arr[n];
			}

			// Empty the bucket
			emptyBucket(arr, bucket, smallest);
		}
		
	}

	@Override
	public int getStartingRunNumber() {
		return 0;
	}
}
