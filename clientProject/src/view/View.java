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
	
	/**
	 * starts the view and notifies other parts of the system
	 * */
	  void start();
		/**
		 * closes the view, notifies the presenter and disposes any running threads or gui components
		 * */
	  void close();
		/**
		 * prints a list of path content
		 * @param string the array of strings to print
		 * */
	void showDir(String []string);
	/**
	 * prints a message to the user
	 * @param s the text to print
	 * */
	void showMsg(String s);
	/**
	 * prints an error
	 * @param s the string to print
	 * */
	void showError(String s);
	/**
	 * returns the simple name of the view class
	 * */
	 String getViewType();
		/**
		 * shows the serialized maze
		 * */
	void showMaze(byte[] arr);
	/**
	 * show a cross section of the maze
	 * @param arr is the serialized problem
	 * @param by is the axis to fix
	 * @param i is the index of the axis
	 * */
	void showCross(byte[] arr,String by,int i);
	/**
	 * prints the given solution
	 * */
	void showSolution(Solution<Position> s);
	/**
	 * notify the user that the problem represented by the string is solved
	 * */
	void showSolved(String name);
	/**
	 * gets the data for the observer's update
	 * */
	Object getData(String string);
	
	
	
	/////////////////////////////////////////////
	
	/**
	 * function finds a way to show the user current status of mazes and their solutions
	 * based on a string array of [name][boolean status] format
	 * */
	void showUpdatedList(String []elements);
	
	
	/**
	 * notifies the user the a maze has been created by the model
	 * */
	 void showMazeIsReady(String s);
	

}
