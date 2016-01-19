package model;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import singletonexplicitpack.Properties;

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
	void handleUpdatePosition (Position p,String name);
	void handleLoadMaze(String path, String name);

	 void handleFileSize(String name);
	 void handleMazeSize(String name);
	 
	 
	 /***
	  * 
	  * get the solution for the problem represented by this string
	  * 
	  * */
	 Object getSolutionFor(String name);
	 
	 void handleSolveMaze(String name,String algo);
	
	
	public Object getData(String string);

	
	

	void close();
	
	void start();

	Object getMazeByName(String string);
	
	void handleLoadProperties();
	
	void handleSaveProperties(Properties p,String path);
	
	
	
}
