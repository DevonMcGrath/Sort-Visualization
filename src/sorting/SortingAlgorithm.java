/* Name: SortingAlgorithm
 * Author: Devon McGrath
 * Description: This class contains the basic methods needed
 * by the program to display a sorting algorithm. If a class is created that
 * extends this one, the getAlgorithm(String) and getAvailableAlgorithms()
 * methods should be updated so that 
 * 
 * Version History:
 * 1.0 - 10/14/2016 - Initial version - Devon McGrath
 */

package sorting;

/**
 * <p>The {@code SortingAlgorithm} class is used to implement different
 * sorting algorithms. Any class which extends this one must implement
 * {@link #sort()}, {@link #sort(int)}, and {@link #getStartingRunNumber()}.
 * An instance of a sorting algorithm can be retrieved using a string with the
 * {@link #getAlgorithm(String)} method. The valid strings are contained in the
 * array from {@link #getAvailableAlgorithms()}</p>
 */
public abstract class SortingAlgorithm {
	
	/** The array of integers to sort */
	protected int[] arr;
	
	/** The name of the sorting algorithm */
	private String algorithmName = "<UNDEFINED>";
	
	/** The default FPS the GUI uses to display the sorting of the algorithm */
	private int defaultFPS = 120;

	/**
	 * Constructs a sorting algorithm with an array and some display info.
	 * @param arr - the array of unsorted integers.
	 * @param defaultFPS - the default FPS value to display the algorithm in.
	 * @param algorithmName - the name of the sorting algorithm.
	 */
	public SortingAlgorithm(int[] arr, String algorithmName, int defaultFPS) {
		this.arr = arr;
		this.algorithmName = algorithmName;
		setDefaultFPS(defaultFPS);
	}

	/**
	 * Sorts the array of integers in ascending order,
	 * e.g. {1,2,3,4,5}
	 */
	public abstract void sort();
	
	/**
	 * Sorts the array of integers in ascending order,
	 * e.g. {1,2,3,4,5}. Performs a single step then returns.
	 * @param step - the step in the algorithm being executed.
	 */
	public abstract void sort(int step);

	/** @return the name of the sorting algorithm. */
	public final String getAlgorithmName() {
		return algorithmName;
	}

	/**
	 * Sets the name of the sorting algorithm.
	 * @param algorithmName - the new name of the sorting algorithm.
	 */
	public final void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	
	/** @return the array that the algorithm is sorting. */
	public final int[] getArray() {
		return arr;
	}
	
	public final void setArray(int[] arr) {
		this.arr = arr;
	}

	/** @return the default FPS value to display the algorithm in. */
	public final int getDefaultFPS() {
		return defaultFPS;
	}

	/**
	 * Sets the default FPS for the sorting algorithm. This value is used
	 * by the display to show the sorting in action. The value should be
	 * big when the algorithm has many steps while small when the algorithm
	 * has few steps.
	 * @param defaultFPS - the new value for the default FPS.
	 */
	public final void setDefaultFPS(int defaultFPS) {
		this.defaultFPS = defaultFPS;
	}
	
	/** @return true if the array is sorted in ascending order. */
	public final boolean isSorted() {
		
		// Special case
		if (arr == null || arr.length < 2) {
			return true;
		}
		
		// Check if the array is sorted
		for (int i = 0; i < arr.length-1; i ++) {
			if (arr[i+1] < arr[i]) {
				return false;
			}
		}
		
		// Array must be sorted
		return true;
	}
	
	/** @return the starting run number, e.g. 0, 1, 2. */
	public abstract int getStartingRunNumber();
	
	/** @return an array of algorithms that inherit from this class. */
	public static final String[] getAvailableAlgorithms() {
		
		final String[] algorithms = {BubbleSort.DISPLAY_NAME,
				SelectionSort.DISPLAY_NAME, FastSelectionSort.DISPLAY_NAME,
				BucketSort.DISPLAY_NAME};
		
		return algorithms;
	}
	
	/**
	 * Gets an instance of a sorting algorithm.
	 * 
	 * @param displayName - the display name of the algorithm (use
	 * {@code [class].DISPLAY_NAME}, e.g. using {@code BubbleSort.DISPLAY_NAME}
	 * returns an instance of the bubble sort algorithm).
	 * 
	 * @return an instance of a sorting algorithm with an empty (non-null)
	 * array. If a sorting algorithm cannot be found with the appropriate
	 * display name, this method returns null.
	 */
	public static final SortingAlgorithm getAlgorithm(String displayName) {
		
		// Special case
		if (displayName == null || displayName.length() == 0) {
			return null;
		}
		
		// Try to find algorithm
		final int[] arr = {};
		if (displayName.equals(BubbleSort.DISPLAY_NAME)) {
			return new BubbleSort(arr);
		}
		if (displayName.equals(SelectionSort.DISPLAY_NAME)) {
			return new SelectionSort(arr);
		}
		if (displayName.equals(FastSelectionSort.DISPLAY_NAME)) {
			return new FastSelectionSort(arr);
		}
		if (displayName.equals(BucketSort.DISPLAY_NAME)) {
			return new BucketSort(arr);
		}
		
		// Could not find algorithm
		return null;
	}
}
