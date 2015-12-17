package model;

import java.util.HashMap;

import algorithms.mazeGenerators.MazeProblem;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface Model {
	void search(MazeProblem p);

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
