package mazeengine.model.facade;

import mazeengine.model.MazeRef;
import mazeengine.model.Room;

//-----------------------------------------------

//Programming 2 -- Semester 2, 2013

//Maze Engine implementation

//Peter Tilmanis, 22 July 2013

//-----------------------------------------------

//Buildable interface

//-----------------------------------------------

public interface Buildable
{
public MazeRef getMazeSize();

public Room getRoom(MazeRef location);

public Room[] getAllRooms();

public boolean setStartPoint(MazeRef location);

public boolean setFinishPoint(MazeRef location);

public boolean createRoom(MazeRef location, String name);

//room removal functionality not defined, for simplicity

public boolean addItem(MazeRef location, String name, int health);

public boolean removeItem(MazeRef location, String name);

public boolean addExitPoint(MazeRef location, String name, MazeRef destination);

public boolean removeExitPoint(MazeRef location, String name);
}
