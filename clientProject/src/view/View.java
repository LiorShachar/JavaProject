package view;

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
* The interface façade of the View section in our MVC architectural pattern
* the model is responsible for presenting the elements and data without "knowing" it.
* 
* 
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-12-17
*/

public interface View {
	  void start();
	  void close();
	void showList(String string);
	void showMsg(String s);
	void showError(String s);
	 String getViewType();
	void showMaze(byte[] arr);
	void showCross(byte[] arr,String by,int i);
	void showSolution(Solution<Position> s);
	
	Object getData(String string);
	
	
	
	/////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////// TEST
	 void showMazeIsReady(String s);
	

}
