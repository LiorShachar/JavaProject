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
	
/**
 * generates a maze with the given name and sizes for each axis
 * **/
	void handleGenerate(String name, int y, int x, int z);
	/**
	 * gets the content of a dir in the path specified
	 * **/
	void handleDir(String string);
	/**
	 * saves a serialized maze to the path given
	 * **/
	void handleSaveMaze(byte[] maze, String path);
	/**
	 * updates the starting position of a maze
	 * **/
	void handleUpdatePosition (Position p,String name);
	/***
	 * loads a maze from a compressed format file from the path given and name the maze 
	 * */
	void handleLoadMaze(String path, String name);
	/**
	 * measure the maze's size inside a file
	 * **/
	 void handleFileSize(String name);
	 /**
	  * measures the actual maze size in memory
	  * **/
	 void handleMazeSize(String name);
	 
	 
	 /***
	  * 
	  * get the solution for the problem represented by this string
	  * 
	  * */
	 Object getSolutionFor(String name);
	 
	 
	 /**
	  * 
	  * solves the maze represented by the given name, using the specified algorithm
	  * **/
	 void handleSolveMaze(String name,String algo);
	
	 /**
	  * 
	  * get the update observable data by specifing the string key
	  * **/
	public Object getData(String string);

	
	
	/**
	 * a thread safe methode to close the model operations
	 * **/
	void close();
	/**
	 * starts the model operations
	 * **/
	void start();
	/**
	 * 
	 * returns the maze by its key name
	 * **/
	Object getMazeByName(String string);
	/**
	 * 
	 * loads the XML properties
	 * **/
	void handleLoadProperties();
	/**
	 * loads the XML properties from the path given
	 * **/
	void handleCustomProperties(String path);
	/**
	 * Saves a custom properties XML to the specified path
	 * **/
	void handleSaveProperties(Properties p,String path);
	/**
	 * check if sultion exists for the specified maze name
	 * **/
	boolean hasSolution(String name);

	


	
	
	
	
}
