package mazeengine.model;

// -----------------------------------------------
// Programming 2 -- Semester 2, 2013
// Maze Engine implementation
// Peter Tilmanis, 22 July 2013
// -----------------------------------------------
// Abstract game exception
// -----------------------------------------------
public abstract class GameException extends Exception
{
   public GameException() 
{
      super();
}  

public GameException(String message)   
{
      super(message); 
}
}
