/* Name: ProgramManager
 * Author: Devon McGrath
 * Date: 10/20/2015
 * Description: This class contains the main method for the program
 * and manages the entire program.
 */

package display;

import components.MenuBar;

import sorting.SortType;

public class ProgramManager {

	public static void main(String[] args) {
		
		//Create the simulation
		int max = 1000, size = 1000;
		Simulation simulation = new Simulation(max, size, SortType.BUBBLE_SORT);
				
		//Create the menu for the program
		MenuBar m = new MenuBar(simulation);
		m.addMenu("Simulation");
		m.addMenu("Help");
		m.addMenuItem(0, Command.SORT_TYPE,
				"Change the sorting method used by the program");
		m.addMenuItem(0, Command.MAX,
				"Change the maximum value that can be generated");
		m.addMenuItem(0, Command.SIZE,
				"Change the size of the array being sorted");
		m.addMenuItem(0, Command.FPS,
				"Change the rate at which the array is sorted");
		m.addMenuItem(0, Command.EXIT);
		m.addMenuItem(1, Command.ABOUT,
				"Info about the program and how it works");
		
		//Create the display
		Display display = new Display(true, "Sorting Visualizer", 700, 500);
		display.exitsOnClose();
		display.setMenuBar(m);
		display.add(simulation);
		display.setVisible(true);
		simulation.start();
	}
}