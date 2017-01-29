/* Name: OptionPanel
 * Author: Devon McGrath
 * Description: This class displays all the options that the user can change.
 * 
 * Version History:
 * 1.1 - 01/28/2017 - Updated the panel layout so that it can fit at the top of
 * 	the display window. - Devon McGrath
 * 1.0 - 10/14/2016 - Initial version - Devon McGrath
 */

package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import sorting.SortingAlgorithm;

public class OptionPanel extends JPanel {

	private static final long serialVersionUID = -8336679605945053672L;
	
	/** The action listener that handles the {@link #submit} button event. */
	private ActionListener actionListener;
	
	/** The combo box with the different sorting algorithms. */
	private JComboBox<String> sortTypes;
	
	/** The field that gets the size. */
	private JTextField size;
	
	/** The field that gets the max. */
	private JTextField max;
	
	/** The combo box that determines the FPS for the simulation. */
	private JComboBox<String> fps;
	
	/** The button that causes the simulation to be updated. */
	private JButton submit;

	/** Constructs an option panel with no action listener. */
	public OptionPanel() {
		init();
	}
	
	/**
	 * Constructs an option panel with an action listener.
	 * @param al - the action listener.
	 */
	public OptionPanel(ActionListener al) {
		this.actionListener = al;
		init();
	}

	/** Initializes the component. */
	private void init() {
		
		// Setup this panel
		this.removeAll();
		
		// Setup components
		this.sortTypes = new JComboBox<String>(SortingAlgorithm
				.getAvailableAlgorithms());
		this.size = new JTextField(""+Simulation.SIZE_DEFAULT, 10);
		this.max = new JTextField(""+Simulation.MAX_DEFAULT, 10);
		this.max.setToolTipText("Enter the max (all elements in the "
				+ "array are 0 to max)");
		String[] fpsValues = new String[13];
		fpsValues[0] = "Default";
		for (int i = 1; i < fpsValues.length; i ++) {
			fpsValues[i] = "" + (i*5);
		}
		this.fps = new JComboBox<>(fpsValues);
		this.submit = new JButton("Update");
		this.submit.addActionListener(new OptionListener());
		
		// Add components
		add(sortTypes);
		add(new JLabel("Array Size:"));
		add(size);
		add(new JLabel("Max:"));
		add(max);
		add(new JLabel("FPS:"));
		add(fps);
		add(submit);
	}
	
	/** The {@code OptionListener} class listens to the submit button. The
	 * input fields are then parsed to get the new simulation settings.
	 * Finally, the external action listener is called to further handle the
	 * submit. */
	private class OptionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// Get the new values to use in the simulation
			Values values = new Values();
			try {
				values.size = Integer.parseInt(size.getText()
						.replaceAll(" ", ""));
				values.size = Math.abs(values.size);
				size.setText(""+values.size);
			} catch (NumberFormatException ex) { // Invalid format
				ex.printStackTrace();
				size.setText(""+Simulation.SIZE_DEFAULT);
			}
			try {
				values.max = Integer.parseInt(max.getText()
						.replaceAll(" ", ""));
				values.max = Math.abs(values.max);
				max.setText(""+values.max);
			} catch (NumberFormatException ex) { // Invalid format
				ex.printStackTrace();
				max.setText(""+Simulation.MAX_DEFAULT);
			}
			values.algorithm = SortingAlgorithm.getAlgorithm(
					sortTypes.getSelectedItem().toString());
			values.fps = fps.getSelectedIndex()*5;
			if (values.fps == 0) {
				values.fps = (values.algorithm == null)
						? 30 : values.algorithm.getDefaultFPS();
			}

			// Tell the action listener to handle the update
			if (actionListener != null) {
				actionListener.actionPerformed(new ActionEvent(values, 0, null));
			}
		}
		
	}
	
	/** The {@code Values} class stores all the values that the user can
	 * change. This includes the size of the array, the max, and the sorting
	 * algorithm. */
	public class Values {
		
		/** The size of the array. */
		public int size = Simulation.SIZE_DEFAULT;
		
		/** The biggest element that the array can contain. */
		public int max = Simulation.MAX_DEFAULT;
		
		/** The new sorting algorithm to use. */
		public SortingAlgorithm algorithm;
		
		/** The frames per second to display the sorting if the array. */
		public int fps;
	}
}
