package mazeengine.model;

public class Player {
	
	/* instance variables including the player's name, health value
	 * and MazeRef location in the MazeSpace */
	private String name;
	private int health;
	private MazeRef location;
	
	/* constructor for a new Player object, takes parameters for player's
	 * name and health value */
	public Player (String name, int health) {
		this.name = name;
		this.health = health;
	}

	/* returns a String of the player's name */
	public String getName() {
		return name;
	}
	
	/* returns an in of the player's health */
	public int getHealth() {
		return health;
	}
	
	/* changes the player's health by applying a given int
	 * to the player's health, and then returning the changed health
	 * value */
	public int applyHealth(int change) {
		health = health + change;
		return health;
	}
	
	/* returns a MazeRef object of the player's current location */
	public MazeRef getLocation() {
		return location;
	}
	
	/* sets the player's location to a given MazeRef location */
	public void setLocation(MazeRef in) {
		location = in;
	}
	
	/* returns a String in the format name:health value:location */
	public String toString() {
		return String.format(name+":"+health+":"+location);
	}
}
