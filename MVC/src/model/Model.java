package model;

import algorithms.mazeGenerators.MazeProblem;

public interface Model {
	   void  search(MazeProblem p);
	   void generateMaze(String name,int y, int x, int z);
	void generateMazeThread(String s, int y, int x, int z);

}
