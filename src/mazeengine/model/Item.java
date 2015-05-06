package mazeengine.model;

public class Item {

	/* instance variables for the name of an Item and its health value */
	private String name;
	private int health;
	
	/*constructor for Item object, takes parameters name and health value */
	public Item (String name, int health) {
		this.name = name;
		this.health = health;
	}
	
	/* returns a String name of item */
	public String getName() {
		return name;
	}
	
	/* returns an int health value of item */
	public int getHealth() {
		return health;
	}
	
	/* returns a String in the format name:health value */
	public String toString() {
		return name+":"+health;
	}
}
