package mazeengine.model.facade;

import java.util.Arrays;

import mazeengine.model.*;

public class MazeEngine implements Buildable, Traversable {
	
	/* instance variables including a Player object, a MazeSpace object
	 * and the player's initial health value */
	private Player player;
	private MazeSpace maze;
	private int initialHealth;
	
	/*constructor for MazeEngine in the case of only size as parameter. 
	 * In this case name is set to UNKNOWN and initial health is set to 50.*/
	public MazeEngine(MazeRef size) {
		maze = new MazeSpace(size);
		player = new Player("UNKNOWN", 50);
		initialHealth = 50;
	}
	
	/*constructor for MazeEngine in the case of size, name and initial health as parameter.*/
	public MazeEngine (MazeRef size, String name, int initHealth) {
		maze = new MazeSpace(size);
		player = new Player(name, initHealth);
		initialHealth = initHealth;
	}
	
	/*returns maze size ... see mazeengine.model.MazeSpace#getMazeSize() */
	public MazeRef getMazeSize() {
		return maze.getMazeSize();
	}
	
	/*returns Room object given location ... see mazeengine.model.MazeSpace#getRoom(MazeRef)*/
	public Room getRoom(MazeRef loc) {
		return maze.getRoom(loc);
	}
	
	/*returns an array of all rooms in the maze ... see mazeengine.model.MazeSpace#getAllRooms() */
	public Room[] getAllRooms() {
		return maze.getAllRooms();
	}
	
	/*attempts to set a Start Point at a given location and returns a boolean as to whether
	 * it was successful or not ... see mazeengine.model#setStartPoint(MazeRef) */
	public boolean setStartPoint(MazeRef loc) {
		boolean valid = maze.setStartPoint(loc);
		return valid;
	}
	
	/*attempts to set a Finish Point at a given location and returns a boolean as to whether
	 * it was successful or not ... see mazeengine.model#setFinishPoint(MazeRef) */
	public boolean setFinishPoint(MazeRef loc) {
		boolean valid = maze.setFinishPoint(loc);
		return valid;
	}
	
	/*attempts to create a new Room object given a location and name. It first checks
	 * if the room is unique and that the location is valid. If this is the case, it
	 * creates a new Room object and returns boolean true, otherwise it returns false
	 *  ... see mazeengine.model.MazeSpace#roomExists(MazeRef), 
	 *  mazeengine.model.MazeSpace#isValidReference(MazeRef), 
	 *  mazeengine.model.MazeSpace#addRoom(Room)*/
	public boolean createRoom(MazeRef loc, String name) {
		
		boolean roomAdded;
		
		if (maze.roomExists(loc)) {
			roomAdded = false;
		}
		
		else if (!maze.isValidReference(loc)) {
			roomAdded = false;
		}
		
		else {
			maze.addRoom(new Room(loc, name));
			roomAdded = true;
		}
		
		return roomAdded;
	}
	
	/*attempts to add an item to a room given a location, item name and health value. 
	 * It first checks that a room exists at the given location. If this is the case
	 * the item is added to that room and a boolean true is returned. Otherwise it 
	 * returns false ... see mazeengine.model.MazeSpace#roomExists(MazeRef),
	 * mazeengine.model.MazeSpace#getRoom(MazeRef), mazeengine.model.Room#addItem(Item) */
	public boolean addItem(MazeRef loc, String name, int health) {
		boolean valid = maze.roomExists(loc);
		
		if (valid == true) {
			Room r = getRoom(loc);
			r.addItem(new Item(name, health));
		}
		return valid;
	}
	
	/*attempts to remove an item from a room given a location and Item name.
	 * It first checks that a room exists at the given location. If this is the case
	 * the item is removed from that room and a boolean true is returned. Otherwise it 
	 * returns false ... see mazeengine.model.MazeSpace#roomExists(MazeRef),
	 * mazeengine.model.MazeSpace#getRoom(MazeRef), mazeengine.model.Room#removeItem(String)  */
	public boolean removeItem(MazeRef loc, String name) {
		boolean valid = maze.roomExists(loc);
		
		if (valid==true) {
			Room r = maze.getRoom(loc);
			valid = r.removeItem(name);
		}
		return valid;
	}
	
	/*attempts to add an exit point to a room given a location, exit name and destination location. 
	 * It first checks that a room exists at the given locations. If this is the case
	 * the exit point is added to that room and a boolean true is returned. Otherwise it 
	 * returns false ... see mazeengine.model.MazeSpace#roomExists(MazeRef),
	 * mazeengine.model.MazeSpace#getRoom(MazeRef), mazeengine.model.Room#addExit(ExitPoint) */
	public boolean addExitPoint(MazeRef loc, String name, MazeRef destination) {
		boolean valid = false;
		if (maze.roomExists(loc) && maze.roomExists(destination)) {
			Room r = maze.getRoom(loc);
			r.addExit(new ExitPoint(name, destination));
			valid = true;
		}
		return valid;
	}
	
	/*attempts to remove an exit point from a room given a location and exit name.
	 * It first checks that a room exists at the given location. If this is the case
	 * the exit point is removed from that room and a boolean true is returned. Otherwise it 
	 * returns false ... see mazeengine.model.MazeSpace#roomExists(MazeRef),
	 * mazeengine.model.MazeSpace#getRoom(MazeRef), mazeengine.model.Room#removeExit(String)  */
	public boolean removeExitPoint(MazeRef loc, String name) {
		boolean valid = maze.roomExists(loc);
		
		if (valid==true) {
			Room r = maze.getRoom(loc);
			valid = r.removeExit(name);
		}
		return valid;
	}
	
	/* attempts to reset the maze for traversal state by checking for valid start and finish points,
	 * setting the player's health back to their initial health value, and setting the player back to 
	 * the start point. If there is a valid start and finish point, a boolean true will be returned,
	 * otherwise it will return false ... see mazeengine.model.MazeSpace#getStartRoom(),
	 * mazeengine.model.MazeSpace#getFinishRoom(), mazeengine.model.Player#applyHealth(int),
	 * mazeengine.model.Player#setLocation(MazeRef), mazeengine.model.Player#getHealth() */
	public boolean reset() {
		
		boolean valid;
		
		if (maze.getStartPoint() != null && maze.getStartPoint() != null) {
			valid = true;
		}
		else {
			valid = false;
		}

		player.applyHealth(initialHealth-player.getHealth());
		player.setLocation(maze.getStartPoint());
		
		return valid;
	}
	
	/* returns Player object */
	public Player getPlayer() {
		return player;
	}
	
	/* attempts to move the player from one room to another given an exit point name. It first gets the
	 * Room the player is currently in given the player's location, it goes through all the ExitPoint objects
	 * until it finds the one of the given exit point name, it gets the destination location for that
	 * ExitPoint object and then sets the player's location to that destination location. If no ExitPoint
	 * object is found for the given exit point name, a boolean false is returned and nothing happens. Otherwise,
	 * it sorts all the items in the destination room from lowest to highest value, and then applies them in order.
	 * If the player's health at any point reaches 0 or below, a DeathException is thrown and the player is out. 
	 * Otherwise, it checks if the destination room is the finish point. If this is the case, a SolvedException
	 * is thrown and the player wins the game. Otherwise a boolean true is returned ...
	 * see mazeengine.model.Player#getLocation(), mazeengine.model.MazeSpace#getRoom(MazeRef),
	 * mazeengine.model.Room#getExitList(), mazeengine.model.ExitPoint#getName(), 
	 * mazeengine.model.Player#setLocation(MazeRef), mazeengine.model.Room#getItemList(),
	 * mazeengine.model.Item#getHealth(), mazeengine.model.Player#applyHealth(int),
	 * mazeengine.model.DeathException, mazeengine.model.Room#getLocation(), 
	 * mazeengine.model.MazeSpace#getFinishPoint(), mazeengine.model.SolvedException */
	public boolean move(String exitPointName) throws GameException {
		Room r = getRoom(player.getLocation());
		boolean valid = false;
		for (int i=0; i<r.getExitList().length; i++) {
			ExitPoint exit = r.getExitList()[i];
			String exitName = exit.getName();
			if (exitName.compareTo(exitPointName)==0) {
				valid = true;
				player.setLocation(exit.getDestination());
			}
		}
		if (valid==true) {
			Room d = getRoom(player.getLocation());
			
			Item[] itemArray  = d.getItemList();
			int[] healthValue = new int[itemArray.length];
			for (int i=0; i<itemArray.length; i++) {
				int health = itemArray[i].getHealth();
				healthValue[i] = health;
			}
			Arrays.sort(healthValue);
			for (int i=0; i<itemArray.length; i++) {
				int health = player.applyHealth(healthValue[i]);
				if (health<=0) {
					throw new DeathException("You lose!");
				}	
			}
			
			if (d.getLocation().equals(maze.getFinishPoint())) {
				throw new SolvedException("You win!");
			}
		}
		return valid;

	}
	
	
}
