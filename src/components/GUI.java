/* Name: GUI
 * Author: Devon McGrath
 * Description: The GUI creates the main user interface for the program. The
 * main GUI includes a panel across the top and the main visualization.
 * 
 * Version History:
 * 1.0 - 01/28/2017 - Initial version - Devon McGrath
 */

package components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GUI extends JPanel {

	private static final long serialVersionUID = 5021688894839267716L;
	
	/** The components that allow the simulation settings to be changed. */
	private OptionPanel options;
	
	/** The simulation that takes up most of the window. */
	private Simulation simulation;
	
	/** Constructs a GUI with the default simulation. */
	public GUI() {
		this(new Simulation());
	}
	
	/**
	 * Constructs a GUI with a predefined simulation.
	 * @param simulation - the simulation.
	 */
	public GUI(Simulation simulation) {
		
		setSimulation(simulation);
		
		// Create the GUI layout
		setLayout(new BorderLayout());
		this.options = new OptionPanel(new OptionListener());
		add(options, BorderLayout.NORTH);
		add(simulation, BorderLayout.CENTER);
		
		// Start the simulation
		this.simulation.start();
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		if (simulation == null) {
			simulation = new Simulation();
		}
		this.simulation = simulation;
	}
	
	/** The {@code OptionListener} class listens to the option panel. */
	private class OptionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// Send the event to the simulation
			if (e.getSource() instanceof OptionPanel.Values) {
				OptionPanel.Values v = (OptionPanel.Values) e.getSource();
				simulation.setMax(v.max);
				simulation.setArrayLength(v.size);
				simulation.setAlgorithm(v.algorithm);
				simulation.setTimerDelay(1000 / v.fps);
			}
		}
	}
}
