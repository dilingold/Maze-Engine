package mazeengine.model;

import java.util.HashMap;
import java.util.Map;

public class MazeSpace {
	
	/* instance variables include a HashMap call rooms containing
	 * a MazeRef object pointing to a Room object, a MazeRef object
	 * called limits in which all MazeRef objects must be less than,
	 * a MazeRef object which sets the location of the start room,
	 * and a MazeRef object which sets the location of the finish room */
	private Map<MazeRef, Room> rooms;
	private MazeRef limits;
	private MazeRef start;
	private MazeRef finish;
	
	/* constructor for MazeSpace object, takes parameter limits, which
	 * sets the values for x,y and z that all Room locations must be less than. */
	public MazeSpace (MazeRef limits) {
		rooms = new HashMap<MazeRef, Room>();
		this.limits = limits;
	}
	
	/* checks if a given MazeRef object is valid or not. It checks
	 * x,y and z values of the MazeRef that it is greater than or equal
	 * to zero, and that it is less than the x,y and z of MazeRef object
	 * limits. If the MazeRef object is valid, it returns a boolean true,
	 * otherwise it returns false ... see mazeengine.model.MazeRef#getX(),
	 * mazeengine.model.MazeRef#getY(), mazeengine.model.MazeRef#getZ() */
	public boolean isValidReference(MazeRef in) {
		boolean valid;
		int xLimit = limits.getX();
		int yLimit = limits.getY();
		int zLimit = limits.getZ();
		
		boolean validX = in.getX()>=0 && in.getX()<xLimit;
		boolean validY = in.getY()>=0 && in.getY()<yLimit;
		boolean validZ = in.getZ()>=0 && in.getZ()<zLimit;
		
		if (validX && validY && validZ) {
			valid = true;
		}
		else {
			valid = false;
		}
		return valid;
	}
		
	/* checks if a room already exists in the MazeSpace by checking if a given 
	 * MazeRef object is in the HashMap rooms. If it does already exist, 
	 * it returns boolean true, otherwise it returns false. */
	public boolean roomExists(MazeRef in) {
		
		boolean exists;
		Room room = rooms.get(in);
		if (room != null) {
			exists = true;
		}
		else exists = false;
		
		return exists;
	}
	
	/* adds a new Room object to the MazeSpace given the Room object.
	 * It finds the MazeRef location of the given room, and then adds
	 * the location and room to the rooms HashMap ...
	 * see mazeengine.model.Room#getLocation() */
	public void addRoom(Room in) {
		
		rooms.put(in.getLocation(), in);
	}
	
	/* returns a Room object given a MazeRef object */
	public Room getRoom(MazeRef in) {
		return rooms.get(in);
	}
	/* returns an array of all Room objects by converting the rooms HashMap
	 * values of Room objects into an array */
	public Room[] getAllRooms() {
		return rooms.values().toArray(new Room[0]);
	}
	
	/* attempts to set a start point for the MazeSpace given a MazeRef location
	 * by checking first that the MazeRef location given already exists. If it does
	 * exist, it sets the given MazeRef location as the start point and returns 
	 * boolean true, otherwise it returns false ... 
	 * see mazeengine.model.MazeSpace#roomExists(MazeRef) */
	public boolean setStartPoint(MazeRef loc) {
		boolean valid = roomExists(loc);
		
		if (valid == true) {
			start = loc;
		}
		return valid;
	}
	
	/* attempts to set a finish point for the MazeSpace given a MazeRef location
	 * by checking first that the MazeRef location given already exists. If it does
	 * exist, it sets the given MazeRef location as the finish point and returns 
	 * boolean true, otherwise it returns false ... 
	 * see mazeengine.model.MazeSpace#roomExists(MazeRef) */
	public boolean setFinishPoint(MazeRef loc) {
		boolean valid = roomExists(loc);
		
		if (valid == true) {
			finish = loc;
		}
		return valid;
	}
	
	/* returns the MazeRef object of the start point */
	public MazeRef getStartPoint() {
		return start;
	}
	
	/* returns the MazeRef object of the start point */
	public MazeRef getFinishPoint() {
		return finish;
	}
	
	/* returns a MazeRef object of the MazeSpace size */
	public MazeRef getMazeSize() {
		return limits;
	}

}
