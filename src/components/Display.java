/* Name: Display
 * Author: Devon McGrath
 * Date: 01/31/2016
 * Description: This class acts as the skeleton frame for the display.
 */

package components;

import java.awt.BorderLayout;

import javax.swing.*;

public class Display extends JFrame {

	private static final long serialVersionUID = 8706461222313558719L;

	/**
	 * Constructs a window with a title, size, and the initial window state.
	 * 
	 * @param isMaximized - true if the window should start maximized.
	 * @param title - the title of the window.
	 * @param width - the width of the window.
	 * @param height - the height of the window.
	 */
	public Display(boolean isMaximized, String title, int width, int height) {

		// Initialize the display
		this.setSize(width, height);
		if (isMaximized) {
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		this.setLocationRelativeTo(null);
		this.setTitle(title);
		this.setLayout(new BorderLayout());
	}
}
