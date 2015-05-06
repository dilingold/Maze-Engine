package mazeengine.model;

/* completes the game if the player successfully reaches the finish point */
public class SolvedException extends GameException {

	public SolvedException() {
		super();
	}
	
	public SolvedException(String message) {
		super(message);
	}
}

