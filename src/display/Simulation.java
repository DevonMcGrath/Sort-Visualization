/* Name: Simulation
 * Author: Devon McGrath
 * Date: 10/20/2015
 * Description: This class acts as the graphics class for the program.
 * It draws all of the numbers in the array to be sorted on the screen.
 */

package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import sorting.*;
import tools.Events;
import tools.Toolkit;

public class Simulation extends JPanel implements ActionListener, Events {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8215622129153754304L;

	private static final int RESET_DELAY = 2000;
	
	private static final Font text = new Font("Arial", Font.BOLD, 14);

	private Timer time;

	private int[] arr;
	private int max, size;
	private SortType sortType;
	private int run;

	public Simulation(int max, int size, SortType sortType) {

		this.arr = Toolkit.generateArray(max, size);
		this.max = max;
		this.size = size;
		this.sortType = sortType;
		this.run = 0;

		this.time = new Timer(50, this);
		this.setTimerDelay(1000 / sortType.getDefaultFPS());

		//Initialize the panel
		this.setBackground(Color.BLACK);
	}

	//Timer event listener
	public void actionPerformed(ActionEvent e) {

		sortArray();

		//Update the display
		repaint();
	}

	//Method that paints all of the graphics
	public void paint(Graphics g) {
		super.paint(g);

		
		//Draw a comparison line
		g.setColor(Color.GREEN);
		g.drawLine(0, getHeight(), getWidth(), 0);

		//Draw all the elements in the array
		g.setColor(Color.WHITE);
		for (int n = 0; n < arr.length; n ++) {
			g.fillRect(getBoxHoriz(n, arr.length),
					getHeight()-getBoxVert(max,arr[n]),
					getWidth() / arr.length,
					3);
		}
		
		//Draw label
		g.setFont(text);
		g.setColor(Color.GREEN);
		g.drawString(sortType.sortName(), 5, 20);

	}

	//Method to get the x location on the screen
	private int getBoxHoriz(int index, int arrayLength) {

		//Return the x location relative to the size of this panel
		return (index * getWidth()) / arrayLength;
	}

	//Method to get the y location on the screen
	private int getBoxVert(int max, int number) {

		//Return the height relative to the size of this panel
		return (number * getHeight()) / max;
	}

	//Method to decide how to sort the array
	private void sortArray() {

		if (sortType == SortType.BUBBLE_SORT) { //Bubble sort

			//Only continue to try and sort if the number of runs
			//is less than the size of the array
			if (run < arr.length) {
				run ++;
				BubbleSort.sortNextRun(arr, run);
			}

			//Increment counter for a few updates so it doesn't
			//reset the array immediately after the sorting is finished
			else if((run-arr.length)*time.getDelay() < RESET_DELAY) {
				run ++;
			}

			//Otherwise, reset the array and start over again
			else {
				reset();
			}
		}

		//Sort using slower selection sort
		else if (sortType == SortType.SELECTION_SORT_SLOW) {

			//Only sort if the run number is less than the size of the array
			if (run < arr.length) {
				SelectionSort.sortNextRun(arr, run);
				run ++;
			}

			//Increment counter for a few updates so it doesn't
			//reset the array immediately after the sorting is finished
			else if((run-arr.length)*time.getDelay() < RESET_DELAY) {
				run ++;
			}

			//Otherwise, reset the array and start over again
			else {
				reset();
			}
		}

		//Use fast selection sort
		else if (sortType == SortType.SELECTION_SORT_FAST) {

			//Only sort if the run number is less than the size of the array
			if (run < arr.length / 2
					+ arr.length % 2) {
				SelectionSort.sortNextRun2(arr, run);
				run ++;
			}

			//Increment counter for a few updates so it doesn't
			//reset the array immediately after the sorting is finished
			else if((run-arr.length / 2
					+ arr.length % 2) * time.getDelay() < RESET_DELAY) {
				run ++;
			}

			//Otherwise, reset the array and start over again
			else {
				reset();
			}
		}

		//Use bucket sort
		else if (sortType == SortType.BUCKET_SORT) {
			int deg = BucketSort.getDegree(arr);
			if (run <= deg) { //Next run sort
				BucketSort.sortNextRun(arr, run);
				run ++;
			}
			else if ((run-deg)*time.getDelay() < RESET_DELAY) {
				run ++;
			}
			else { //Has been sorted for more than 'RESET_DELAY' milliseconds
				reset();
			}

		}
		
		//TODO implement more sort methods
	}

	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public void setArray(int[] arr) {
		this.arr = arr;
	}
	
	public int[] getArray() {
		return arr;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		if (this.max != max && max > 0) {
			this.max = max;
			this.arr = Toolkit.generateArray(max, size);
		}
	}

	public int getArraylength() {
		return size;
	}

	public void setArraylength(int size) {
		if (this.size != size && size > 0) {
			this.size = size;
			this.arr = Toolkit.generateArray(max, size);
		}
	}

	//Sets the timer delay
	public void setTimerDelay(int delay) {

		boolean running = isRunning();
		if (running) {
			time.stop();
		}
		time = new Timer(delay, this);

		if (running) {
			time.start();
		}
	}

	//Starts the simulation
	public void start() {
		if (time != null) {
			time.start();
		}
	}

	//Stops this simulation
	public void stop() {
		if (time != null) {
			time.stop();
		}
	}
	
	//Resets the array
	public void reset() {
		this.arr = Toolkit.generateArray(max, size);
		this.run = 0;
	}

	//Method returns the status of the simulation
	public boolean isRunning() {
		if (time == null) {
			return false;
		}
		return time.isRunning();
	}

	//Called from component events (such as the user clicking a menu option)
	public void actionEvent(ActionEvent e) {
		
		//Check event id and respond
		String id = e.getActionCommand();
		if (id.equals(Command.SORT_TYPE)) { //Change the sort type
			SortType temp = changeSortType();
			if (temp != sortType) {
				this.sortType = temp;
				reset();
				this.setTimerDelay(1000 / temp.getDefaultFPS());
			}
		}
		else if (id.equals(Command.MAX)) { //Changing max value
			
			//Get user input
			String in = Toolkit.getUserInput(this,
					"Change Max Array Value",
					"Enter the new max array value (max > 0):");
			int max = this.max;
			if (in != null) {
				try { //Try to convert to integer
					max = Integer.parseInt(in);
				}
				catch (Exception err) {
					Toolkit.showErrorMessage(this, "Conversion Error",
							"There was an error converting \""
									+ in + "\" to an integer!");
				}
				
				//Set the new max value
				if (max != this.max && max > 0) {
					this.max = max;
					reset(); //Reset sorting
				}
				else if (max <= 0) {
					Toolkit.showErrorMessage(this, "Error",
							"The program does not support\na "
							+ "negative or zero max value!");
				}
			}
		}
		else if (id.equals(Command.SIZE)) { //Change array size
			
			//Get user input
			String in = Toolkit.getUserInput(this,
					"Change Array Size",
					"Enter the new array size:");
			int size = this.size;
			if (in != null) {
				try { //Try to convert to integer
					size = Integer.parseInt(in);
				}
				catch (Exception err) {
					Toolkit.showErrorMessage(this, "Conversion Error",
							"There was an error converting \""
									+ in + "\" to an integer!");
				}
				
				//Set the new size
				if (size != this.size && size > 0) {
					this.size = size;
					reset(); //Reset sorting
				}
				else if (size <= 0) {
					Toolkit.showErrorMessage(this, "Error",
							"Can't have an array with negative size!");
				}
			}
		}
		else if (id.equals(Command.FPS)) { //Change the rate of sorting
			
			//Get user input
			String in = Toolkit.getUserInput(this,
					"Change FPS",
					"Enter the new FPS value (0 < FPS < 121):");
			int fps = -1;
			if (in != null) {
				try { //Try to convert to integer
					fps = Integer.parseInt(in);
				}
				catch (Exception err) {
					Toolkit.showErrorMessage(this, "Conversion Error",
							"There was an error converting \""
									+ in + "\" to an integer!");
				}
				
				//Set the FPS if in range
				if (fps > 0 && fps < 121) {
					this.setTimerDelay(1000 / fps);
				}
				else {
					Toolkit.showErrorMessage(this, "Error",
							"FPS value out of bounds!");
				}
			}
		}
		else if (id.equals(Command.EXIT)) {
			System.exit(0);
		}
		else if (id.equals(Command.ABOUT)) {
			
		}
	}
	
	//Gets input from the user on what sorting type the program will use
	private SortType changeSortType() {
		SortType t = sortType;
		
		//Get user input
		String in = Toolkit.getUserInput(this,
				"Change Sort Type",
				"Enter the sort ID:\n1: Bubble Sort\n2: Selection Sort "
				+ "(Slow)\n3: Selection Sort (Fast)\n"
				+ "4: Bucket Sort");
		int val = 0;
		if (in != null) {
			try { //Try to convert to integer
				val = Integer.parseInt(in);
			}
			catch (Exception err) {
				Toolkit.showErrorMessage(this, "Conversion Error",
						"There was an error converting \""
								+ in + "\" to an integer!");
			}
			
			//Return the new sort method
			if (val > 0 && val < 5) {
				if (val == 1) {
					return SortType.BUBBLE_SORT;
				}
				if (val == 2) {
					return SortType.SELECTION_SORT_SLOW;
				}
				if (val == 3) {
					return SortType.SELECTION_SORT_FAST;
				}
				if (val == 4) {
					return SortType.BUCKET_SORT;
				}
			}
			else { //Out of bounds
				Toolkit.showErrorMessage(this, "Error",
						"Input out of bounds!");
			}
		}
		
		return t;
	}
}
