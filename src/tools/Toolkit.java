/* Name: Toolkit
 * Author: Devon McGrath
 * Date: 10/20/2015
 * Description: This class contains methods that don't fit into any specific
 * class, but are useful for the program.
 */

package tools;

import java.awt.Component;

import javax.swing.JOptionPane;

public abstract class Toolkit {
	
	//Returns an array with random integer values from 1 -> 'max'
	public static int[] generateArray(int max, int size) {

		//Create the array full of random integers
		int[] arr = new int[size];
		for (int n = 0; n < size; n ++) {
			arr[n] = (int)(1 + Math.random()*max);
		}
		
		//Return array
		return arr;
	}
	
	//Returns a string that was entered by the user
	public static String getUserInput(String title, String text) {
		return (JOptionPane.showInputDialog(null, text, title,
				JOptionPane.QUESTION_MESSAGE));
	}
	
	//Returns a string entered by user (component is relative to another)
	public static String getUserInput(Component c, String title, String text) {
		return (JOptionPane.showInputDialog(c, text, title,
				JOptionPane.QUESTION_MESSAGE));
	}
	
	//Shows an error message window
	public static void showErrorMessage(String title, String errorMsg) {
		JOptionPane.showMessageDialog(null, errorMsg, title,
				JOptionPane.ERROR_MESSAGE);
	}
	
	//Shows an error message relative to a component
	public static void showErrorMessage(Component c,
			String title, String errorMsg) {
		JOptionPane.showMessageDialog(c, errorMsg, title,
				JOptionPane.ERROR_MESSAGE);
	}
}
