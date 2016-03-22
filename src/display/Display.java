/* Name: Display
 * Author: Devon McGrath
 * Date: 01/31/2016
 * Description: This class acts as the skeleton frame for the display.
 */

package display;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

import components.MenuBar;

import tools.ResourceLoader;

public class Display extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;

	//Constructor - creates the JFrame
	public Display(boolean isMaximized, String title, int width, int height) {

		//Initialize the display
		this.setSize(width, height);
		if (isMaximized) {
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		this.setLocationRelativeTo(null);
		this.setTitle(title);
		this.setLayout(new BorderLayout());
		this.width = width;
		this.height = height;
	}

	//Constructor - creates the JFrame with an image icon set
	public Display(boolean isMaximized, String title, int width,
			int height, String imageName) {

		//Initialize the display
		this.setSize(width, height);
		if (isMaximized) {
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		this.setLocationRelativeTo(null);
		this.setTitle(title);
		this.setIconImage(ResourceLoader.loadImage(imageName));
		this.setLayout(new BorderLayout());
		this.width = width;
		this.height = height;
	}
	
	//Sets the menu bar for the display
	public void setMenuBar(MenuBar m) {
		if (m.menuCount() > 0) {
			m.addAll();
			this.setJMenuBar(m);
		}
	}
	
	//Sets the minimum dimension
	public void setMinimumSize(int width, int height) {
		
		//Only change the settings if values are within a range
		if (width > 0 && width <= this.width &&
				height > 0 && height <= this.height) {
			this.setMinimumSize(new Dimension(width, height));
		}
	}
	
	//Sets the default close operation
	public void exitsOnClose() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
