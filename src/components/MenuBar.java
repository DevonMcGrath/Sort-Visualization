/* Name: MenuBar
 * Author: Devon McGrath
 * Date: 02/19/2016
 * Description: This class acts as a menu for the 'Display' class. This menu
 * appears along the top of the display.
 */

package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import tools.Events;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5672513139773158274L;

	private String lastEvent;
	private Events event;
	private List<JMenu> menus;
	private List<List<JMenuItem>> menuItems;

	//Basic constructor
	public MenuBar(Events event) {
		this.lastEvent = "";
		this.event = event;
		this.menus = new ArrayList<JMenu>();
		this.menuItems = new ArrayList<List<JMenuItem>>();
	}

	//Adds all components to the menu bar
	public void addAll() {

		//Add all menu items to menus
		for (int i = 0; i < menus.size(); i ++) {
			for (JMenuItem item : menuItems.get(i)) {
				this.menus.get(i).add(item);
			}
		}

		//Add all menus
		for (JMenu menu : menus) {
			this.add(menu);
		}
	}
	
	//Removes then adds menus from menu bar and menu items from menus
	public void refresh() {
		
		//Remove all components
		for (JMenu menu : menus) {
			menu.removeAll();
		}
		this.removeAll();
		
		//Add all components
		addAll();
	}

	//Deletes all menus and menu items
	public void deleteAll() {
		this.lastEvent = "";
		this.removeAll();
		this.menus = new ArrayList<JMenu>();
		this.menuItems = new ArrayList<List<JMenuItem>>();
	}

	//Adds a new menu
	public void addMenu(String title) {
		if (!containsMenu(title)) {
			this.menus.add(new JMenu(title));
			this.menuItems.add(new ArrayList<JMenuItem>());
		}
	}
	
	//Adds multiple menus at once
	public void addMenus(String[] menuTitles) {
		if (menuTitles != null) {
			for (String s : menuTitles) {
				addMenu(s);
			}
		}
	}

	//Adds a new menu item with tool tip
	public void addMenuItem(int menu, String itemName, String tooltip) {
		if (menu >= 0 && menu < menus.size()) {
			
			//Create the item
			JMenuItem item = new JMenuItem(itemName);
			if (tooltip != null && tooltip.length() > 0) {
				item.setToolTipText(tooltip);
			}
			
			//Add the action listener
			item.addActionListener(new MenuItemEvents());
			
			//Add it to the menu
			this.menuItems.get(menu).add(item);
		}
	}
	
	//Adds a new menu item with tool tip
	public void addMenuItem(String menuName, String itemName, String tooltip) {
		addMenuItem(indexOfMenu(menuName), itemName, tooltip);
	}
	
	//Adds a menu with no tool tip
	public void addMenuItem(int menu, String itemName) {
		addMenuItem(menu, itemName, "");
	}
	
	//Adds a menu with no tool tip
	public void addMenuItem(String menuName, String itemName) {
		addMenuItem(indexOfMenu(menuName), itemName, "");
	}
	
	//Method to remove a menu
	public void removeMenu(int menu) {
		if (menu >= 0 && menu < menus.size()) {
			this.menus.remove(menu);
			this.menuItems.remove(menu);
		}
	}

	//Method to remove a menu item
	public void removeMenuItem(int menu, int itemIndex) {
		if (menu >= 0 && menu < menus.size() &&
				itemIndex >= 0 && itemIndex < menuItems.get(menu).size()) {
			this.menuItems.get(menu).remove(itemIndex);
		}
	}
	
	//Returns the number of menus
	public int menuCount() {
		return menus.size();
	}

	//Returns the number of menu items in a menu
	public int menuItemCount(int menu) {

		if (menu >= 0 && menu < menus.size()) {
			return menuItems.get(menu).size();
		}

		return -1;
	}

	//Returns the total number of menu items in the menu bar
	public int menuItemCount() {

		int count = 0;
		for (int i = 0; i < menus.size(); i ++) {
			count += menuItemCount(i);
		}

		return count;
	}

	//Returns menu names as a string array
	public String[] menuNames() {

		//Special cases
		if (menus == null) {
			return null;
		}
		if (menus.size() == 0) {
			String[] result = {""};
			return result;
		}

		String[] result = new String[menus.size()];
		for (int i = 0; i < result.length; i ++) {
			result[i] = menus.get(i).getText();
		}

		return result;
	}

	//Method checks if the string is contained in the array
	public boolean containsMenu(String title) {
		return (indexOfMenu(title) >= 0);
	}

	//Returns the menu index of 'title'
	public int indexOfMenu(String title) {

		String[] titles = menuNames();
		
		//Special cases
		if (titles == null || title == null) {
			return -1;
		}
		if (titles.length == 0) {
			return -1;
		}

		//Try to find 'title'
		int index = 0;
		for (String str : titles) {
			if (str.equals(title)) {
				return index;
			}
			index ++;
		}

		//Failed to find 'title'
		return -1;
	}
	
	//Returns the title of the item that had an event
	public String getLastEvent() {
		return lastEvent;
	}
	
	//Class for responding to menu item events
	private class MenuItemEvents implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			lastEvent = e.getActionCommand();
			event.actionEvent(e);
		}
	}
}
