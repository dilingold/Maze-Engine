package mazeengine.model;

import java.util.ArrayList;

public class Room {

	/* instance variables including a MazeRef location of a Room,
	 * the Room object name, an ArrayList of all items in a Room and an
	 * ArrayList of all ExitPoints in a Room */
	private MazeRef location;
	private String name;
	private ArrayList<Item> items;
	private ArrayList<ExitPoint> exits;
	
	/* constructor for a new Room object, takes parameters MazeRef location
	 * of room and name of room. Instance variable location is set to the given
	 * MazeRef location, the Room name is set to the given name, and the ArrayLists
	 * are initialized */
	public Room(MazeRef loc, String name) {
		this.location = loc;
		this.name = name;
		items = new ArrayList<Item>();
		exits = new ArrayList<ExitPoint>();
	}
	
	/* returns a Room MazeRef location */
	public MazeRef getLocation() {
		return location;
	}
	
	/* returns a String of the Room object's name */
	public String getName() {
		return name;
	}
	
	/* adds an ExitPoint object to a room by adding it to the
	 * Room ArrayList of exits */
	public void addExit(ExitPoint in) {
		exits.add(in);
	}
	
	/* returns an ExitPoint given an ExitPoint name. It goes through
	 * all exit point names in the Room until it finds the given name, and
	 * then returns that ExitPoint object. If no ExitPoint was found with the
	 * given name, it returns null ... see mazeengine.model.ExitPoint#getName()*/
	public ExitPoint getExit(String name) {
		for (int i=0; i<exits.size(); i++) {
			ExitPoint exit = exits.get(i);
			String exitName = exit.getName();
			if (exitName.compareTo(name)==0) {
				return exit;
			}
		}
		return null;
	}
	
	/* attempts to remove an ExitPoint from a Room given an ExitPoint name.
	 * It goes through all ExitPoints in a Room until it finds the one with the
	 * given name, and then removes it from the Room's ArrayList of ExitPoints.
	 * If it was successful in finding the ExitPoint, it returns boolean true,
	 * otherwise it returns false ... see mazeengine.model.ExitPoint#getName() */
	public boolean removeExit(String name) {
		boolean valid = false;
		int i = 0;
		while (!valid && i<exits.size()) {
			ExitPoint exitI = exits.get(i);
			String exitName = exitI.getName();
			if (exitName.compareTo(name)==0) {
				exits.remove(i);
				valid = true;
			}
			i++;
		}
		return valid;
	}
	
	/* returns an array of all ExitPoint objects in a room by converting the 
	 * exit ArrayList into an array */
	public ExitPoint[] getExitList() {
		ExitPoint[] exitArray = exits.toArray(new ExitPoint[exits.size()]);
		return exitArray;
	}
	
	/* adds an Item object to a room by adding it to the
	 * Room's ArrayList of items */
	public void addItem(Item in) {
		items.add(in);
	}
	
	/* returns an Item given an Item name. It goes through
	 * all Item names in the Room until it finds the given name, and
	 * then returns that Item object. If no Item was found with the
	 * given name, it returns null ... see mazeengine.model.Item#getName()*/
	public Item getItem(String name) {
		for (int i=0; i<items.size(); i++) {
			Item item = items.get(i);
			String itemName = item.getName();
			if (itemName.compareTo(name)==0) {
				return item;
			}
		}
		return null;
	}
	
	/* attempts to remove an Item from a Room given an Item name.
	 * It goes through all Items in a Room until it finds the one with the
	 * given name, and then removes it from the Room's ArrayList items.
	 * If it was successful in finding the Item, it returns boolean true,
	 * otherwise it returns false ... see mazeengine.model.Item#getName() */
	public boolean removeItem(String name) {
		boolean valid = false;
		int i = 0;
		while (!valid && i<items.size()) {
			Item itemI = items.get(i);
			String itemName = itemI.getName();
			if (itemName.compareTo(name)==0) {
				items.remove(i);
				valid = true;
			}
			i++;
		}
		return valid;
	}
	
	/* returns an array of all Item objects in a room by converting the 
	 * item ArrayList into an array */
	public Item[] getItemList() {
		Item[] itemArray = items.toArray(new Item[items.size()]);
		return itemArray;
	}
	
	/* returns a String in the format location:name:<items>:<exits> */
	public String toString() {
		String itemList = "";
		for (int i=0; i<items.size(); i++) {
			String itemName = items.get(i).getName();
			if (i == 0) {
				itemList = itemName;
			}
			else {
				itemList = itemList + ","+itemName;
			}
		}
		
		String exitList = "";
		for (int i=0; i<exits.size(); i++) {
			String exitName = exits.get(i).getName();
			if (i == 0) {
				exitList = exitName;
			}
			else {
				exitList = exitList + ","+exitName;
			}
		}
		
		return location+":"+name+":"+itemList+":"+exitList;
	}
}
