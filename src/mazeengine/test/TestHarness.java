package mazeengine.test;

import mazeengine.model.*;
import mazeengine.model.facade.*;

/**
 * <b>Programming 2, Assignment 2</b><br/>
 * Maze System Implementation<p/>
 *
 * This class will test the maze system.
 *
 * USAGE
 *
 * Run it for an interactive test menu.
 *
 * Test #15 ("Test all") will dump any debugging output made by the test
 * program, your code, and the JRE, into a text file named "tests.txt".
 *
 * It will also estimate your mark (out of 7) for the "functionality" section.
 *
 * The test #15 can be invoked from the commandline with the "all" argument.
 *
 *
 * @author Peter Tilmanis
 * @version 1.0, 22 July 2013
 *
 */

// NOTE TO STUDENTS: you may need to include a package declaration here, so
// that you can include the test harness in your project.

import java.io.*;
import java.util.*;

public class TestHarness
{
   static Buildable b;
   static Traversable t;
   static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
   
   /**
    * Initialise a maze system instance.
    *
    * NOTE TO STUDENTS: this is where you change the constructor name/parameters,
    * if necessary. This should be the ONLY thing you might need to modify.
    *
    * @param   size  the size of the maze system to create.
    */
   public static void initialiseEngine(MazeRef size)
   {
      System.out.println("Initialising maze engine of size " + size.getX() +
                         "," + size.getY() + "," + size.getZ() + "...");

      // this is the line that creates a maze engine instance
      b = new MazeEngine(size);

      t = (Traversable) b;
   }
   
   
// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************

//              BE VERY CAREFUL CHANGING ANYTHING PAST HERE

//    You may add your own extra lines here and there while debugging
//                          your work, but ...

//      YOUR MAZE SYSTEM SHOULD BE ABLE TO WORK WITH ANYTHING AFTER
//                    THIS POINT, LEFT UNMODIFIED !!

// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************



   // --- test1  ----------------------------------------------------------
   public static boolean test1() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));

      // check player string representation
      String s = t.getPlayer().toString();
      System.out.print("Player string is ");
      System.out.print("\"" + s + "\"");
      if(s.toUpperCase().indexOf("UNKNOWN:50") != 0)
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
      {
         System.out.println(" ... correct");
         return true;
      }
   }



   // --- test2  ----------------------------------------------------------
   public static boolean test2() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));
      String[] expected = {"1,1,1:RoomA::", "2,2,2:RoomB::"};

      // add first room
      System.out.print("Creating first room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,1), "RoomA") ,true))
         return false;

      // add first room
      System.out.print("Creating second room...");
      if (failureCheck( b.createRoom(new MazeRef(2,2,2), "RoomB") ,true))
         return false;

      // find first room
      System.out.print("Finding first room with getRoom()...");
      if (failureCheck( b.getRoom(new MazeRef(1,1,1)).toString().equalsIgnoreCase("1,1,1:RoomA::") ,true)) 
         return false;
   
      // find first room
      System.out.print("Finding second room with getRoom()...");
      if (failureCheck( b.getRoom(new MazeRef(2,2,2)).toString().equalsIgnoreCase("2,2,2:RoomB::") ,true))
         return false;

      System.out.print("Checking rooms with getAllRooms()...");
      if (failureCheck( checkForRooms(b.getAllRooms(), expected) ,true))
         return false;

      return true;
   }



   // --- test3  ----------------------------------------------------------
   public static boolean test3() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));
      String[] expected = {"ItemA", "ItemB"};

      // add first room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,1), "RoomA") ,true))
         return false;

      // add first item
      System.out.print("Adding first item...");
      if (failureCheck( b.addItem(new MazeRef(1,1,1), "ItemA", 5) ,true))
         return false;

      // add second item
      System.out.print("Adding second item...");
      if (failureCheck( b.addItem(new MazeRef(1,1,1), "ItemB", 3) ,true))
         return false;

      // check for items
      System.out.print("Checking items in the room...");
      System.out.println(b.getRoom(new MazeRef(1,1,1)).toString());
      if (failureCheck( checkForItems(b.getRoom(new MazeRef(1,1,1)), expected) ,true))
         return false;

      return true;
   }



   // --- test4  ----------------------------------------------------------
   public static boolean test4() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));
      String[] expected1 = {"ItemA", "ItemB"};
      String[] expected2 = {"ItemA"};

      // add first room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,1), "RoomA") ,true))
         return false;

      // add first item
      System.out.print("Adding first item...");
      if (failureCheck( b.addItem(new MazeRef(1,1,1), "ItemA", 5) ,true))
         return false;

      // add second item
      System.out.print("Adding second item...");
      if (failureCheck( b.addItem(new MazeRef(1,1,1), "ItemB", 3) ,true))
         return false;

      // check for items
      System.out.print("Checking items in the room...");
      if (failureCheck( checkForItems(b.getRoom(new MazeRef(1,1,1)), expected1) ,true))
         return false;

      // remove item that doesn't exist
      System.out.print("Removing item that doesn't exist...");
      if (failureCheck( b.removeItem(new MazeRef(1,1,1), "ItemZ") ,false))
         return false;

      // check for items
      System.out.print("Checking items in the room...");
      if (failureCheck( checkForItems(b.getRoom(new MazeRef(1,1,1)), expected1) ,true))
         return false;

      // remove item that does exist
      System.out.print("Removing item that does exist...");
      if (failureCheck( b.removeItem(new MazeRef(1,1,1), "ItemB") ,true))
         return false;

      // check for items
      System.out.print("Checking items in the room...");
      if (failureCheck( checkForItems(b.getRoom(new MazeRef(1,1,1)), expected2) ,true))
         return false;

      return true;
   }



   // --- test5  ----------------------------------------------------------
   public static boolean test5() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));
      String[] expected = {"ExitAtoB", "ExitAtoC"};

      // add first room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,1), "RoomA") ,true))
         return false;

      // add second room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,2), "RoomB") ,true))
         return false;

      // add third room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,2,1), "RoomC") ,true))
         return false;

      // add first exit
      System.out.print("Adding first exit point...");
      if (failureCheck( b.addExitPoint(new MazeRef(1,1,1), "ExitAtoB", new MazeRef(1,1,2)) ,true))
         return false;

      // add second exit
      System.out.print("Adding second exit point...");
      if (failureCheck( b.addExitPoint(new MazeRef(1,1,1), "ExitAtoC", new MazeRef(1,2,1)) ,true))
         return false;

      // check for items
      System.out.print("Checking exits in the room...");
      if (failureCheck( checkForExits(b.getRoom(new MazeRef(1,1,1)), expected) ,true))
         return false;

      return true;
   }



   // --- test6  ----------------------------------------------------------
   public static boolean test6() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));
      String[] expected1 = {"ExitAtoB", "ExitAtoC"};
      String[] expected2 = {"ExitAtoC"};

      // add first room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,1), "RoomA") ,true))
         return false;

      // add second room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,2), "RoomB") ,true))
         return false;

      // add third room
      System.out.print("Creating a room...");
      if (failureCheck( b.createRoom(new MazeRef(1,2,1), "RoomC") ,true))
         return false;

      // add first exit
      System.out.print("Adding first exit point...");
      if (failureCheck( b.addExitPoint(new MazeRef(1,1,1), "ExitAtoB", new MazeRef(1,1,2)) ,true))
         return false;

      // add second exit
      System.out.print("Adding second exit point...");
      if (failureCheck( b.addExitPoint(new MazeRef(1,1,1), "ExitAtoC", new MazeRef(1,2,1)) ,true))
         return false;

      // check for items
      System.out.print("Checking exits in the room...");
      if (failureCheck( checkForExits(b.getRoom(new MazeRef(1,1,1)), expected1) ,true))
         return false;

      // remove exit that doesn't exist
      System.out.print("Removing exit that doesn't exist...");
      if (failureCheck( b.removeExitPoint(new MazeRef(1,1,1), "ExitZ") ,false))
         return false;

      // check for items
      System.out.print("Checking exits in the room...");
      if (failureCheck( checkForExits(b.getRoom(new MazeRef(1,1,1)), expected1) ,true))
         return false;

      // remove item that does exist
      System.out.print("Removing exit that does exist...");
      if (failureCheck( b.removeExitPoint(new MazeRef(1,1,1), "ExitAtoB") ,true))
         return false;

      // check for items
      System.out.print("Checking exits in the room...");
      if (failureCheck( checkForExits(b.getRoom(new MazeRef(1,1,1)), expected2) ,true))
         return false;

      return true;
   }



   // --- test7  ----------------------------------------------------------
   public static boolean test7() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));
      String[] expected1 = {"1,1,1:RoomA1::"};
      String[] expected2 = {"1,1,1:RoomA1::", "2,2,2:RoomB1::"};

      // add first room
      System.out.print("Creating first room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,1), "RoomA1") ,true))
         return false;

      // add a clashing room
      System.out.print("Creating a clashing room...");
      if (failureCheck( b.createRoom(new MazeRef(1,1,1), "RoomA2") ,false))
         return false;

      // check rooms
      System.out.print("Checking rooms with getAllRooms()...");
      if (failureCheck( checkForRooms(b.getAllRooms(), expected1) ,true))
         return false;

      // add another room
      System.out.print("Creating a second room...");
      if (failureCheck( b.createRoom(new MazeRef(2,2,2), "RoomB1") ,true))
         return false;

      // check rooms
      System.out.print("Checking rooms with getAllRooms()...");
      if (failureCheck( checkForRooms(b.getAllRooms(), expected2) ,true))
         return false;

      return true;
   }



   // --- test8  ----------------------------------------------------------
   public static boolean test8() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(5,5,5));

      // check maze size
      MazeRef mr = b.getMazeSize();

      System.out.print("Checking maze size");      
      if (mr.getX() != 5 || mr.getY() != 5 || mr.getZ() != 5)      
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
         System.out.println(" ... correct");

      // create new maze system instance
      initialiseEngine(new MazeRef(1,2,3));

      // check maze size
      mr = b.getMazeSize();

      System.out.print("Checking maze size");      
      if (mr.getX() != 1 || mr.getY() != 2 || mr.getZ() != 3)      
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
         System.out.println(" ... correct");

      return true;
   }



   // --- test9  ----------------------------------------------------------
   public static boolean test9() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(3,3,3));
      String[] expected = {"0,0,0:Origin::",
                           "2,0,0:MaxX::",
                           "0,2,0:MaxY::",
                           "0,0,2:MaxZ::",
                           "2,2,0:MaxXY::",
                           "0,2,2:MaxYZ::",
                           "2,0,2:MaxXZ::",
                           "2,2,2:MaxXYZ::"};

      // add rooms
      System.out.print("Creating origin room...");
      if (failureCheck( b.createRoom(new MazeRef(0,0,0), "Origin") ,true))
         return false;

      System.out.print("Creating MaxX room...");
      if (failureCheck( b.createRoom(new MazeRef(2,0,0), "MaxX") ,true))
         return false;

      System.out.print("Creating MaxY room...");
      if (failureCheck( b.createRoom(new MazeRef(0,2,0), "MaxY") ,true))
         return false;

      System.out.print("Creating MaxZ room...");
      if (failureCheck( b.createRoom(new MazeRef(0,0,2), "MaxZ") ,true))
         return false;

      System.out.print("Creating MaxXY room...");
      if (failureCheck( b.createRoom(new MazeRef(2,2,0), "MaxXY") ,true))
         return false;

      System.out.print("Creating MaxYZ room...");
      if (failureCheck( b.createRoom(new MazeRef(0,2,2), "MaxYZ") ,true))
         return false;

      System.out.print("Creating MaxXZ room...");
      if (failureCheck( b.createRoom(new MazeRef(2,0,2), "MaxXZ") ,true))
         return false;

      System.out.print("Creating MaxXYZ room...");
      if (failureCheck( b.createRoom(new MazeRef(2,2,2), "MaxXYZ") ,true))
         return false;

      // check rooms
      System.out.print("Checking rooms with getAllRooms()...");
      if (failureCheck( checkForRooms(b.getAllRooms(), expected) ,true))
         return false;

      // add an out of bounds room
      System.out.print("Creating a room out of bounds...");
      if (failureCheck( b.createRoom(new MazeRef(3,3,3), "OOB") ,false))
         return false;

      // check rooms
      System.out.print("Checking rooms with getAllRooms()...");
      if (failureCheck( checkForRooms(b.getAllRooms(), expected) ,true))
         return false;

      return true;
   }



   // --- test10 ----------------------------------------------------------
   public static boolean test10() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(10,10,10));

      // create new maze system instance
      buildTestMaze();

      // check reset
      System.out.print("Checking for reset result...");
      if (failureCheck( t.reset() ,false))
         return false;

      // set start point
      System.out.print("Setting a start point...");
      if (failureCheck( b.setStartPoint(new MazeRef(1,2,3)) ,true))
         return false;

      // set finish point
      System.out.print("Setting a finish point...");
      if (failureCheck( b.setFinishPoint(new MazeRef(2,2,5)) ,true))
         return false;

      // check reset
      System.out.print("Checking for reset result...");
      if (failureCheck( t.reset() ,true))
         return false;

      // check player string representation
      String s = t.getPlayer().toString();
      System.out.print("Player string is ");
      System.out.print("\"" + s + "\"");
      if(!s.equalsIgnoreCase("UNKNOWN:50:1,2,3"))
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
         System.out.println(" ... correct");
      
      return true;
   }



   // --- test11 ----------------------------------------------------------
   public static boolean test11() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(10,10,10));

      // create new maze system instance
      buildTestMaze();

      // set start point
      System.out.print("Setting a start point...");
      if (failureCheck( b.setStartPoint(new MazeRef(1,2,3)) ,true))
         return false;

      // set finish point
      System.out.print("Setting a finish point...");
      if (failureCheck( b.setFinishPoint(new MazeRef(2,2,5)) ,true))
         return false;

      // check reset
      System.out.print("Checking for reset result...");
      if (failureCheck( t.reset() ,true))
         return false;

      // check player string representation
      String s = t.getPlayer().toString();
      System.out.print("Player string is ");
      System.out.print("\"" + s + "\"");
      if(!s.equalsIgnoreCase("UNKNOWN:50:1,2,3"))
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
         System.out.println(" ... correct");

      // move player
      System.out.print("Moving the player...");
      if (failureCheck( t.move("test") ,true))
         return false;

      // check player string representation
      s = t.getPlayer().toString();
      System.out.print("Player string is ");
      System.out.print("\"" + s + "\"");
      if(!s.equalsIgnoreCase("UNKNOWN:48:1,2,4"))
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
         System.out.println(" ... correct");
      
      return true;
   }



   // --- test12 ----------------------------------------------------------
   public static boolean test12() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(10,10,10));

      // create new maze system instance
      buildTestMaze();

      // set start point
      System.out.print("Setting a start point...");
      if (failureCheck( b.setStartPoint(new MazeRef(1,2,3)) ,true))
         return false;

      // set finish point
      System.out.print("Setting a finish point...");
      if (failureCheck( b.setFinishPoint(new MazeRef(2,2,5)) ,true))
         return false;

      // check reset
      System.out.print("Checking for reset result...");
      if (failureCheck( t.reset() ,true))
         return false;

      // move player
      System.out.print("Moving the player...");
      if (failureCheck( t.move("test") ,true))
         return false;

      // check player string representation
      String s = t.getPlayer().toString();
      System.out.print("Player string is ");
      System.out.print("\"" + s + "\"");
      if(!s.equalsIgnoreCase("UNKNOWN:48:1,2,4"))
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
         System.out.println(" ... correct");


      // move player
      System.out.print("Moving the player...");
      if (failureCheck( t.move("test") ,true))
         return false;

      // check player string representation
      s = t.getPlayer().toString();
      System.out.print("Player string is ");
      System.out.print("\"" + s + "\"");
      if(!s.equalsIgnoreCase("UNKNOWN:46:1,2,5"))
      {
         System.out.println(" ... not correct");
         return false;
      }
      else
         System.out.println(" ... correct");
      
      return true;
   }



   // --- test13 ----------------------------------------------------------
   public static boolean test13() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(10,10,10));

      // create new maze system instance
      buildTestMaze();

      // set start point
      System.out.print("Setting a start point...");
      if (failureCheck( b.setStartPoint(new MazeRef(1,2,3)) ,true))
         return false;

      // set finish point
      System.out.print("Setting a finish point...");
      if (failureCheck( b.setFinishPoint(new MazeRef(2,2,5)) ,true))
         return false;

      // check reset
      System.out.print("Checking for reset result...");
      if (failureCheck( t.reset() ,true))
         return false;

      // move player
      System.out.print("Moving the player to win (1)...");
      if (failureCheck( t.move("win") ,true))
         return false;

      // move player
      System.out.print("Moving the player to win (2)...");
      if (failureCheck( t.move("win") ,true))
         return false;

      // move player
      System.out.print("Moving the player to win (3)...");
      try
      {
         if (failureCheck( t.move("win") ,true))
            return false;
      }
      catch (GameException ge)
      {
         System.out.println(" game exception thrown, OK");
         return true;
      }

      return false;
   }



   // --- test14 ----------------------------------------------------------
   public static boolean test14() throws Exception
   {
      // create new maze system instance
      initialiseEngine(new MazeRef(10,10,10));

      // create new maze system instance
      buildTestMaze();

      // set start point
      System.out.print("Setting a start point...");
      if (failureCheck( b.setStartPoint(new MazeRef(1,2,3)) ,true))
         return false;

      // set finish point
      System.out.print("Setting a finish point...");
      if (failureCheck( b.setFinishPoint(new MazeRef(2,2,5)) ,true))
         return false;

      // check reset
      System.out.print("Checking for reset result...");
      if (failureCheck( t.reset() ,true))
         return false;

      // move player
      System.out.print("Moving the player to lose (1)...");
      if (failureCheck( t.move("lose") ,true))
         return false;

      // move player
      System.out.print("Moving the player to lose (2)...");
      if (failureCheck( t.move("lose") ,true))
         return false;

      // move player
      System.out.print("Moving the player to lose (3)...");
      try
      {
         if (failureCheck( t.move("lose") ,true))
            return false;
      }
      catch (GameException ge)
      {
         System.out.println(" game exception thrown, OK");
         return true;
      }

      return false;
   }



// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************

//                   DO NOT TOUCH ANYTHING PAST THIS POINT

// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************
// ************************************************************************
   
      
   // --- test15 ----------------------------------------------------------
   public static void test15()
   {
      boolean anyFail = false;
      int count = 0;
      
      System.out.println("These tests failed: ");
      PrintStream realErr, realOut;

      PrintStream outFile = null;
      try
      {
        outFile = new PrintStream(new FileOutputStream(new File("tests.txt")));
      }
      catch (FileNotFoundException e)
      {
         System.out.println("test harness can't open a file for summarising");
         return;
      }
      
      realErr = System.err;
      realOut = System.out;
      System.setErr(outFile);
      System.setOut(outFile);

      // okay remember here that System.out and System.err are now the file
      // the real output streams are in realOut and realErr!
      try
      {
         System.out.println("--- Test 1 --------------------------");
         if (!test1())
         {
            realOut.println("Test 1 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 1 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 2 --------------------------");
         if (!test2())
         {
            realOut.println("Test 2 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 2 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 3 --------------------------");
         if (!test3())
         {
            realOut.println("Test 3 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 3 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 4 --------------------------");
         if (!test4())
         {
            realOut.println("Test 4 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 4 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 5 --------------------------");
         if (!test5())
         {
            realOut.println("Test 5 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 5 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 6 --------------------------");
         if (!test6())
         {
            realOut.println("Test 6 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 6 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 7 --------------------------");
         if (!test7())
         {
            realOut.println("Test 7 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 7 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 8 --------------------------");
         if (!test8())
         {
            realOut.println("Test 8 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 8 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 9 --------------------------");
         if (!test9())
         {
            realOut.println("Test 9 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 9 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 10 --------------------------");
         if (!test10())
         {
            realOut.println("Test 10 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 10 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 11 --------------------------");
         if (!test11())
         {
            realOut.println("Test 11 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 11 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 12 --------------------------");
         if (!test12())
         {
            realOut.println("Test 12 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 12 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 13 --------------------------");
         if (!test13())
         {
            realOut.println("Test 13 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 13 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }


      try
      {
         System.out.println("--- Test 14 --------------------------");
         if (!test14())
         {
            realOut.println("Test 14 **FAIL** (incorrect implementation)");
            anyFail = true;
            count++;
         }
      }
      catch (Exception e)
      {
         realOut.println("Test 14 **FAIL** (unexpected exception occurred)");
         anyFail = true;
         count++;
      }
      
      // set the output streams back to normal, and close the file
      System.setErr(realErr);
      System.setOut(realOut);
      outFile.close();

      if (!anyFail)
         System.out.println("NONE -- Working maze engine.");
      else
         System.out.println("Some tests failed...");
      
      System.out.println("\nTests failed:  " + count);
      System.out.println("Tests passed:  " + (14 - count));
      System.out.println();
      System.out.println("\nFunctionality Mark: " + (0.5 * (14 - count)));
   }



   /**
    * Display the test menu.
    */
   public static int mainMenu()
   {
      // variables for menu operation
      int answer = -1;
      boolean valid = false;
      
      // continue prompting until valid data given
      while (!valid)
      {
         // display menu and prompt for entry
         System.out.println();
         System.out.println("===============================================================================");
         System.out.println("Tests");
         System.out.println("===============================================================================");
         System.out.println();
         System.out.println("MAZE BUILDING TESTS                      MAZE TRAVERSAL TESTS");
         System.out.println(" 1. blank maze                           10. maze reset");
         System.out.println(" 2. room creation                        11. move operation");
         System.out.println(" 3. adding an item                       12. multiple movements");
         System.out.println(" 4. removing an item                     13. completing a maze");
         System.out.println(" 5. adding an exit point                 14. dying in a maze");
         System.out.println(" 6. removing an exit point");
         System.out.println(" 7. managing multiple rooms              15. test all");
         System.out.println(" 8. maze size");
         System.out.println(" 9. maze boundary checking               16. quit");

         System.out.println();
         System.out.print("Enter an option: ");
         
         // try to read and validate entered data
         try
         {
            answer = Integer.parseInt(stdin.readLine());
            System.out.println();
            if ((answer >= 1) && (answer <= 16))
               valid = true;
         }
         catch (NumberFormatException e)
         {
            System.out.println("Unparsable number entered");
         }
         catch (IOException e)
         {
            System.out.println("I/O Exception. Goodbye");
            System.exit(1);
         }
         
         // if data was invalid, print an error
         if (!valid)
         {
            System.out.println();
            System.out.println("Please enter a valid option (1-16).");
         }
      }
      
      // return the entered data
      return answer;
   }

   public static void main(String[] args)
   {
      if (args.length > 0)
      {
         if (args[0].equalsIgnoreCase("all"))
         {
            test15();
            System.exit(0);
         }
      }
      
      // menu and data declarations
      int menuOption = -1;
      
      // repeat processing menu items until quit is entered
      while (menuOption != 16)
      {
         menuOption = mainMenu();
         
         try
         {
            switch (menuOption)
            {
               case 1:
                  if (test1())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 2:
                  if (test2())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 3:
                  if (test3())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 4:
                  if (test4())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 5:
                  if (test5())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 6:
                  if (test6())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 7:
                  if (test7())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 8:
                  if (test8())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 9:
                  if (test9())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 10:
                  if (test10())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 11:
                  if (test11())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 12:
                  if (test12())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 13:
                  if (test13())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 14:
                  if (test14())
                     System.out.println("**PASS**");
                  else
                     System.out.println("**FAIL**");
                  break;
               case 15:
                  test15(); 
            }
         }
         catch (Exception e)
         {
            System.out.println("Unexpected exception! " + e.getMessage());
            e.printStackTrace();
            System.out.println("**FAIL**");
         }
      }
   }

   
   public static boolean checkForRooms(Room[] rooms, String[] expected) throws Exception
   {
      try
      {
         // if number of rooms is different from expected, return failure
         if (rooms.length != expected.length)
            return false;
   
         // make some spots to tick
         boolean[] tickbox = new boolean[expected.length];
   
         // for each expected room string...
         for (int i = 0; i < expected.length; i++)
         {
            // try to find a match in the actual room string list.
            boolean found = false;
            for (int j = 0; (j < rooms.length) && (!found); j++)
            {
               if (expected[i].equalsIgnoreCase(rooms[j].toString()))
                  found = true;
            }
   
            // store result for this room string in the tick box
            tickbox[i] = found;
         }
   
         // check there's a tick in every box
         for (int i = 0; i < tickbox.length; i++)
         {
            if (!tickbox[i])
               return false;
         }
      }
      catch (Exception e)
      {
         System.out.println("Parsing error: could not check room list successfully!");
         return false;
      }

      return true;
   }

   public static boolean failureCheck(boolean check, boolean expected)
   {
      if (check == expected)
      {
         System.out.println(" OK");
         return false;
      }
      else
      {
         System.out.println(" not OK!");
         return true;
      }
   }


   public static boolean checkForItems(Room room, String[] expected) throws Exception
   {
      try
      {
         // make some spots to tick
         boolean[] tickbox = new boolean[expected.length];

         // split the room string into its parts
         String[] split = room.toString().split(":");

         // check the correct number of parts are there
         if (split.length != 3)
            return false;

         // take the exit part and split it into individual exits
         String[] itemList = split[2].split(",");

         // for each expected item string...
         for (int i = 0; i < expected.length; i++)
         {
            // try to find a match in the actual item list.
            boolean found = false;
            for (int j = 0; (j < itemList.length) && (!found); j++)
            {
               if (expected[i].equalsIgnoreCase(itemList[j].toString()))
                  found = true;
            }
   
            // store result for this room string in the tick box
            tickbox[i] = found;
         }
   
         // check there's a tick in every box
         for (int i = 0; i < tickbox.length; i++)
         {
            if (!tickbox[i])
               return false;
         }
      }
      catch (Exception e)
      {
         return false;
      } 

      return true;
   }

   public static boolean checkForExits(Room room, String[] expected) throws Exception
   {
      try
      {
         // make some spots to tick
         boolean[] tickbox = new boolean[expected.length];

         // split the room string into its parts
         String[] split = room.toString().split(":");

         // check the correct number of parts are there
         if (split.length != 4)
            return false;

         // take the exit part and split it into individual exits
         String[] exitList = split[3].split(",");
         

         // for each expected item string...
         for (int i = 0; i < expected.length; i++)
         {
            // try to find a match in the actual item list.
            boolean found = false;
            for (int j = 0; (j < exitList.length) && (!found); j++)
            {
               if (expected[i].equalsIgnoreCase(exitList[j].toString()))
                  found = true;
            }
   
            // store result for this room string in the tick box
            tickbox[i] = found;
         }
   
         // check there's a tick in every box
         for (int i = 0; i < tickbox.length; i++)
         {
            if (!tickbox[i])
               return false;
         }
      }
      catch (Exception e)
      {
         return false;
      } 

      return true;
   }

   public static void buildTestMaze()
   {
      System.out.println("Building test maze...");
      // create rooms
      b.createRoom(new MazeRef(1,2,3),"Start");
      b.createRoom(new MazeRef(1,2,4),"Test1");
      b.createRoom(new MazeRef(1,2,5),"Test2");
      b.createRoom(new MazeRef(2,2,3),"Win1");
      b.createRoom(new MazeRef(2,2,4),"Win2");
      b.createRoom(new MazeRef(2,2,5),"Win3");
      b.createRoom(new MazeRef(0,2,3),"Lose1");
      b.createRoom(new MazeRef(0,2,4),"Lose2");
      b.createRoom(new MazeRef(0,2,5),"Lose3");

      // add items
      b.addItem(new MazeRef(1,2,4),"ItemA",-2);
      b.addItem(new MazeRef(1,2,5),"ItemB",3);
      b.addItem(new MazeRef(1,2,5),"ItemC",-5);
      b.addItem(new MazeRef(0,2,3),"ItemD",-20);
      b.addItem(new MazeRef(0,2,4),"ItemE",10);
      b.addItem(new MazeRef(0,2,4),"ItemF",-20);
      b.addItem(new MazeRef(0,2,5),"ItemG",10);
      b.addItem(new MazeRef(0,2,5),"ItemH",-25);

      // add exits
      b.addExitPoint(new MazeRef(1,2,3),"test",new MazeRef(1,2,4));
      b.addExitPoint(new MazeRef(1,2,4),"test",new MazeRef(1,2,5));

      b.addExitPoint(new MazeRef(1,2,3),"win",new MazeRef(2,2,3));
      b.addExitPoint(new MazeRef(2,2,3),"win",new MazeRef(2,2,4));
      b.addExitPoint(new MazeRef(2,2,4),"win",new MazeRef(2,2,5));

      b.addExitPoint(new MazeRef(1,2,3),"lose",new MazeRef(0,2,3));
      b.addExitPoint(new MazeRef(0,2,3),"lose",new MazeRef(0,2,4));
      b.addExitPoint(new MazeRef(0,2,4),"lose",new MazeRef(0,2,5));
   }

}
