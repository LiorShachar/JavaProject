package view;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public interface View {
	   void start();
	void list(String string);
	void printMsg(String s);
	
	void displayMaze(byte[] arr);
	void displayCross(byte[] arr,String by,int i);
	void displaySolution(Solution<Position> s);

}
