package mazeengine.model;

public class ExitPoint {
	
	/* instance variables for the name of the ExitPoint object 
	 * and the destination where it takes the player */
	private String name;
	private MazeRef destination;
	
	/* constructor for ExitPoint object, takes parameter name and
	 * a destination MazeRef object */
	public ExitPoint(String name, MazeRef dest) {
		this.name = name;
		this.destination = dest;
	}
	
	/* returns a String name of the ExitPoint object */
	public String getName() {
		return name;
	}
	
	/* returns a MazeRef object of the destination where the ExitPoint 
	 * leads to */
	public MazeRef getDestination() {
		return destination;
	}
	
}
