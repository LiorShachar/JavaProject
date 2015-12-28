package model;

import java.util.HashMap;

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

	void generateMazeThread(String s, int y, int x, int z);

	void handleSaveMaze(byte[] maze, String path);

	void handleLoadMaze(String path, String name);

	void handleFileSize(String name);

	public HashMap<String, byte[]> getMazes();
	
	public void handleSolveMaze(String name,String algo);
	
	public HashMap<String, Solution<Position>> getSolutions();

	void handleSolveMazeThread(String string, String string2);
	

public String getError();






public String getMsg();



	void handleKill();

	void testThread();
}
