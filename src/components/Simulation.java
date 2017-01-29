/* Name: Simulation
 * Author: Devon McGrath
 * Description: This class acts as the graphics class for the program.
 * It draws all of the numbers in the array to be sorted on the screen.
 * 
 * Version History:
 * 1.2 - 01/28/2017 - Removed support for 'SortType' and replaced it with the
 * 	abstract class 'SortingAlgorithm' for easy scaling (i.e. adding more
 * 	sorting algorithms). - Devon McGrath
 * 1.1 - 01/02/2017 - Updated documentation - Devon McGrath
 * 1.0 - 10/20/2015 - Initial version - Devon McGrath
 */

package components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import sorting.*;

/**
 * <p>The {@code Simulation} class is responsible for creating the graphics
 * and calling methods to sort an array of integers. It displays the array by
 * using the x-axis as the index in the array, and the y-axis is the value at
 * the index. Index 0 is at the left side, and the n'th index is displayed at
 * the width of the component (on the right). For the height, 0 is at the
 * bottom and the maximum value in the array is at the top.</p>
 */
public class Simulation extends JPanel {
	
	private static final long serialVersionUID = -8215622129153754304L;
	
	/** The default size of the array. */
	public static final int SIZE_DEFAULT = 600;
	
	/** The default maximum possible element in the array. */
	public static final int MAX_DEFAULT = 1000;

	/** The number of milliseconds between the array being finished sorting and
	 * a new array being randomly generated. */
	public static final int RESET_DELAY = 2000;

	/** The timer responsible for updating the graphics every so often. */
	private Timer time;
	
	/** The maximum value that can be generated in the array. */
	private int max;
	
	/** The length of the array. */
	private int size;
	
	/** The number of iterations the sorting algorithm has gone through. */
	private int run;
	
	/** The delay, in milliseconds, from the time the array was sorted until
	 * now. If the array is still being sorted, the value will be 0. */
	private int delay;
	
	/** The sorting algorithm used to sort the array (e.g. bubble sort). */
	private SortingAlgorithm algorithm;
	
	/** Constructs a simulation using a {@value #SIZE_DEFAULT} element array,
	 * with a maximum possible value being {@value #MAX_DEFAULT}-1. The default
	 * algorithm is bubble sort. */
	public Simulation() {
		this(MAX_DEFAULT, SIZE_DEFAULT, new BubbleSort(
				generateArray(MAX_DEFAULT, SIZE_DEFAULT)));
	}
	
	/**
	 * Constructs a simulation from array parameters.
	 * 
	 * @param max - the maximum number that can be in the array.
	 * @param size - the length of the array.
	 * @param sortType - the sort type.
	 */
	public Simulation(int max, int size, SortingAlgorithm algorithm) {

		this.time = new Timer(100, new TimerEventListener());
		this.max = max;
		this.size = size;
		setAlgorithm(algorithm);

		// Initialize the panel
		this.setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		// Draw a comparison line
		g.setColor(Color.GREEN);
		g.drawLine(0, getHeight(), getWidth(), 0);

		// Draw all the elements in the array
		g.setColor(Color.WHITE);
		final int[] arr = algorithm.getArray();
		for (int n = 0; n < arr.length; n ++) {
			g.fillRect((n * getWidth()) / arr.length,
					getHeight() - (arr[n] * getHeight()) / max,
					getWidth() / arr.length,
					3);
		}
		
		// Draw the name of the algorithm
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.setColor(Color.GREEN);
		g.drawString(algorithm.getAlgorithmName(), 5, 20);
	}

	/**
	 * <b><em>sortArray</em></b>
	 * 
	 * <p>Partially sorts the array by calling {@code algorithm.sort(run)}. If
	 * the array is already sorted, it will pause until it has been sorted for
	 * {@value #RESET_DELAY} milliseconds. After which, {@link #reset()} will
	 * be called to restart the simulation with a new random array.</p>
	 * 
	 * @see {@link #RESET_DELAY}, {@link #reset()}
	 */
	private void sortArray() {
		
		// Sort the array by the next step
		if (algorithm != null) {

			// Continue sorting
			if (delay == 0 && !algorithm.isSorted()) {
				this.algorithm.sort(run);
				run ++;
			}
			
			// Array is sorted
			else {
				
				this.delay += time.getDelay();
				
				// Reset if delay reached a threshold
				if (delay >= RESET_DELAY) {
					reset();
				}
			}
		}
	}

	/**
	 * <b><em>getAlgorithm</em></b>
	 * 
	 * <p>Gets the algorithm being used in the simulation. If it is not
	 * defined, the simulation will not display anything.</p>
	 * 
	 * @return the current instance of the sorting algorithm being used.
	 * @see {@link #setAlgorithm(SortingAlgorithm)}
	 */
	public SortingAlgorithm getAlgorithm() {
		return algorithm;
	}

	/**
	 * <b><em>setAlgorithm</em></b>
	 * 
	 * <p>Sets the algorithm the simulation will use to sort the integer array.
	 * Note that if it is not defined, the simulation will not display
	 * anything. Therefore, if a null reference is used, the simulation will
	 * default to {@link BubbleSort}.</p>
	 * 
	 * @param algorithm - the sorting algorithm.
	 * 
	 * @see {@link #getAlgorithm()}
	 */
	public void setAlgorithm(SortingAlgorithm algorithm) {
		if (algorithm == null) {
			algorithm = new BubbleSort(null);
		}
		this.algorithm = algorithm;
		this.algorithm.setArray(generateArray(max, size));
		reset();
		this.setTimerDelay(1000 / algorithm.getDefaultFPS());
	}

	/**
	 * <b><em>getMax</em></b>
	 * 
	 * <p>Gets the max.</p>
	 * 
	 * @return the maximum magnitude of an integer that can exist in the array.
	 * @see {@link #setMax(int)}
	 */
	public int getMax() {
		return max;
	}

	/**
	 * <b><em>setMax</em></b>
	 * 
	 * <p>Sets the maximum magnitude (+1) of the integers that can possibly
	 * exist in the array being sorted. For more info on {@code max}, see
	 * {@link #generateArray(int, int)}.</p>
	 * 
	 * @param max - the max.
	 * 
	 * @see {@link #getMax()}
	 */
	public void setMax(int max) {
		if (this.max != max && max > 0) {
			this.max = max;
			this.algorithm.setArray(generateArray(max, size));
		}
	}

	/**
	 * <b><em>getArrayLength</em></b>
	 * 
	 * <p>Gets the length of the array being used in the simulation.</p>
	 * 
	 * @return the length of the array.
	 * @see {@link #setArrayLength(int)}
	 */
	public int getArrayLength() {
		return size;
	}

	/**
	 * <b><em>setArrayLength</em></b>
	 * 
	 * <p>Sets the length of the array being used in the simulation. Note that
	 * if the size is too large, the elements of the array may not be displayed
	 * on screen.</p>
	 * 
	 * @param size - the length of the array.
	 * 
	 * @see {@link #getArrayLength()}
	 */
	public void setArrayLength(int size) {
		if (this.size != size && size > 0) {
			this.size = size;
			this.algorithm.setArray(generateArray(max, size));
		}
	}

	/**
	 * <b><em>setTimerDelay</em></b>
	 * 
	 * <p>Sets the timer delay, in milliseconds.</p>
	 * 
	 * @param delay - the new timer delay.
	 */
	public void setTimerDelay(int delay) {

		boolean running = isRunning();
		if (running) {
			time.stop();
		}
		this.time.setDelay(delay);
		if (running) {
			time.start();
		}
	}

	/**
	 * <b><em>start</em></b>
	 * 
	 * <p>Starts the simulation by starting the timer.</p>
	 * 
	 * @see {@link #stop()}, {@link #isRunning()}
	 */
	public void start() {
		if (time != null) {
			time.start();
		}
	}

	/**
	 * <b><em>stop</em></b>
	 * 
	 * <p>Stops the simulation by stopping the timer.</p>
	 * 
	 * @see {@link #start()}, {@link #isRunning()}
	 */
	public void stop() {
		if (time != null) {
			time.stop();
		}
	}
	
	/**
	 * <b><em>reset</em></b>
	 * 
	 * <p>Resets the simulation by generating a new array.</p>
	 */
	public void reset() {
		this.algorithm.setArray(generateArray(max, size));
		this.run = algorithm.getStartingRunNumber();
		this.delay = 0;
	}

	/**
	 * <b><em>isRunning</em></b>
	 * 
	 * <p>Gets the current state of the algorithm.</p>
	 * 
	 * @return true if and only if the timer is running.
	 * 
	 * @see {@link #start()}, {@link #stop()}
	 */
	public boolean isRunning() {
		if (time == null) {
			return false;
		}
		return time.isRunning();
	}
	
	/** Class to listen to timer updates. */
	private class TimerEventListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// Sort the next step in the array
			sortArray();

			// Update the display
			repaint();
		}
		
	}
	
	/**
	 * <b><em>generateArray</em></b>
	 * 
	 * <p>Generates an array of the specified size with elements that are all
	 * less than the max. No negative numbers will be generated. For example,
	 * {@code generateArray(10, 5)} could generate {0, 2, 8, 5, 9} but would
	 * never generate {0, 2, 8, 3, 10} or {-1, 0, 2, 6, 4}.</p>
	 * 
	 * @param max - the magnitude of the biggest possible number in the 
	 * array + 1.
	 * @param size - the size of the array.
	 * @return the array of integers such that for all {@code size} elements,
	 * element 'i' will be greater than or equal to 0 but less than |max|.
	 */
	public static int[] generateArray(int max, int size) {
		
		// Special cases
		if (size < 1) {
			return new int[0];
		}
		max = Math.abs(max);

		// Create the array full of random integers
		int[] arr = new int[size];
		for (int i = 0; i < size; i ++) {
			arr[i] = (int)(1 + Math.random()*max);
		}
		
		return arr;
	}
}
