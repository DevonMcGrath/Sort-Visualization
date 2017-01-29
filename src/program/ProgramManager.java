/* Name: ProgramManager
 * Author: Devon McGrath
 * Date: 10/20/2015
 * Description: This class contains the main method for the program
 * and manages the entire program.
 */

package program;

import java.awt.Dimension;

import javax.swing.UIManager;

import components.Display;
import components.GUI;

public class ProgramManager {

	public static void main(String[] args) {
		
		// Set the look and feel to the system's look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create the display
		Display display = new Display(true, "Sorting Visualizer", 700, 500);
		display.setDefaultCloseOperation(Display.EXIT_ON_CLOSE);
		display.setMinimumSize(new Dimension(580, 150));
		display.add(new GUI());
		display.setVisible(true);
	}
}