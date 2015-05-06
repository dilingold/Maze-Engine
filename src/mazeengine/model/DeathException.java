package mazeengine.model;

/* terminates the game if the player's health value reaches 0 or below */
public class DeathException extends GameException {

	public DeathException() {
		super();
	}
	
	public DeathException(String message) {
		super(message);
	}
}
