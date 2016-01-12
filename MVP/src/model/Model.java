package model;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
*
* 
* 
* 
* 
* <h1>Model</h1>
* The interface façade of the model section in our MVC architectural pattern
* the model is responsible for making the "tough" calculations, it handles threads, changes values, 
* creating and deleting elements etc.
* 
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-12-17
*/
public interface Model {
	

	void handleGenerate(String name, int y, int x, int z);
	
	void handleDir(String string);

	void handleSaveMaze(byte[] maze, String path);
	void handleUpdateStartPosition (Position p,String name);
	void handleLoadMaze(String path, String name);

	void handleFileSize(String name);
	void handleMazeSize(String name);
	 Solution<Position> getSolutionFor(String name);
	HashMap<String, Maze3d> getMazes();
	
	 void handleSolveMaze(String name,String algo);
	
	 HashMap<Maze3d, Solution<Position>> getSolutions();

	public Object getData(String string);

	
	void handleKill();

	void testThread();

	Object getMazeByName(String string);
}
