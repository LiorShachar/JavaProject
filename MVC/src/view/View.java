package view;

import algorithms.search.Solution;

public interface View {
	   void start();
	   void displaySolution(Solution s);
	void list(String string);
	void printMsg(String s);
	
	void displayMaze(byte[] arr);
	void displayCross(byte[] arr,String by,int i);

}
