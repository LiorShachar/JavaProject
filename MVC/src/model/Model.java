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
	

	void generateMaze(String name, int y, int x, int z);

	void generateMazeThread(String s, int y, int x, int z);

	void saveMaze(byte[] maze, String path);

	void loadMaze(String path, String name);

	void fileSize(String name);

	public HashMap<String, byte[]> getMazes();

	public void setMazes(HashMap<String, byte[]> mazes);
	
	public void solveMaze(String name,String algo);
	
	public HashMap<String, Solution<Position>> getSolutions();

	void solveMazeThread(String string, String string2);

	void kill();

	void testThread();
}
