package mazeengine.model;

//-----------------------------------------------
//Programming 2 -- Semester 2, 2013
//Maze Engine implementation
//Peter Tilmanis, 22 July 2013
//-----------------------------------------------
//Maze grid reference class
//-----------------------------------------------

public class MazeRef
{
private int x, y, z;

public MazeRef()
{
   this(-1, -1, -1);
}

public MazeRef(int x, int y, int z)
{
   this.x = x;
   this.y = y;
   this.z = z;
}

public int getX()
{
   return x;
}

public int getY()
{
   return y;
}

public int getZ()
{
   return z;
}

public boolean equals(Object in)
{
   if ( !(in instanceof MazeRef) )
      return false;
   else
   {
      MazeRef temp = (MazeRef) in;
      if ( (this.x == temp.getX()) &&
           (this.y == temp.getY()) &&
           (this.z == temp.getZ()) )
         return true;
      else
         return false;
   }
}

public int hashCode()
{
   return this.toString().hashCode();
}

public String toString()
{
   return x + "," + y + "," + z;
}
}
