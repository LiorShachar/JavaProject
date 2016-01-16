package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;
import controller.Presenter;

/**
*
* 
* 
* 
* 
* <h1>MyView</h1>
* this class represent my View part of the project, it is suitable for a Maze3d problem.
* initialized CLI with the regular system input/output and a command map from MyController
* 
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-12-17
*/

public class MyViewCLI extends CommonView implements View,Observer {
	
	 CLI cli;
	String todo;
	
	
	
	
	 public String getTodo() {
		return todo;
	}

	 public void showExit(){
		this.cli.flag=false;
	 } 

	public void setTodo(String todo) {
		this.todo = todo;
		hasChanged();
		notifyObservers(this.todo);
	}


	public MyViewCLI(){
	   
	   this.cli =new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
	   
	  
	 }
	 

	public CLI getCli() {
		return cli;
	}




	public void setCli(CLI cli) {
		this.cli = cli;
		this.cli.addObserver(this);
	}




	/**
	 * starts the CLI thread
	 */
	
	@Override
	public void start() {
		
		
		new Thread(cli).start();
		
		
		
		
	}
	
	
	


/**
 * uses the File class in order to execute the Dir command.
 * print the names of files and folders in the path provided
 */

	@Override
	public void showList(String string) {
		File lister = new File(string);
		try {
			String[] pathdetails = lister.list();
			for (String s: pathdetails)
				showMsg(s);
		} catch (NullPointerException e) {
			showMsg("File or Directory not found");
		}
		
		
	}

	
	
	/**
	 * prints the string provided using the scanner in the cli, so the view will interact with the user the way he chose.
	 */
	@Override
public void showMsg(String s) {
	cli.getOut().println(s);
	cli.getOut().flush();
	

	
	
}


/**
 * gets a maze3d provided by its byte array and prints its details.
 * the maze is printed as cross sections of its levels. (the Y axis is fixed)
 */

@Override
public void showMaze(byte[] arr) {
	Maze3d maze = new Maze3d(arr);
	int levels = maze.getySize();
	int lvl=0;
	int[][] twodi;
	System.out.println("********************************************************");
	System.out.println("Maze Starting point:" + maze.getStartPosition().toString());
	System.out.println("Maze Ending point:" + maze.getGoalPosition().toString());
	while(lvl<levels){
		System.out.println("**************************[   level:"+lvl+"       ]******************************");
		twodi=maze.getCrossSectionByY(lvl);
	for (int i=0; i < twodi.length ; i++){
		
		for (int j=0 ; j < twodi[0].length; j++){ 
			System.out.print(twodi[i][j]);
		}
		System.out.print("\n");
	}
	
	lvl++;
	}
}


/**
 * prints the maze cross section provided by byte array of maze3d, a fixed axis and its index. 
 */

@Override
public void showCross(byte[] arr, String by, int index) {
	Maze3d maze = new Maze3d(arr);
	
	int[][] twodi;
	switch (by) {
	case "y":
		if(!(index>=maze.getySize())){
		twodi=maze.getCrossSectionByY(index);
		for (int i=0; i < twodi.length ; i++){
			
			for (int j=0 ; j < twodi[0].length; j++){ 
				System.out.print(twodi[i][j]);
			}
			System.out.print("\n");
		}
		}
		else
			System.out.println("ilegal index");
		break;
	case "Y":
		if(!(index>=maze.getySize())){
		twodi=maze.getCrossSectionByY(index);
		for (int i=0; i < twodi.length ; i++){
			
			for (int j=0 ; j < twodi[0].length; j++){ 
				System.out.print(twodi[i][j]);
			}
			System.out.print("\n");
		}
		}
		else
			System.out.println("ilegal index");
		break;
		
	case "x":
		if(!(index>=maze.getxSize())){
		twodi=maze.getCrossSectionByX(index);
		for (int i=0; i < twodi.length ; i++){
			
			for (int j=0 ; j < twodi[0].length; j++){ 
				System.out.print(twodi[i][j]);
			}
			System.out.print("\n");
		}
		}
		else
			System.out.println("ilegal index");
		break;
	case "X":
		if(!(index>=maze.getxSize())){
		twodi=maze.getCrossSectionByX(index);
		for (int i=0; i < twodi.length ; i++){
			
			for (int j=0 ; j < twodi[0].length; j++){ 
				System.out.print(twodi[i][j]);
			}
			System.out.print("\n");
		}
		}
		else
			System.out.println("ilegal index");
		break;
	case "z":
		if(!(index>=maze.getzSize())){
		twodi=maze.getCrossSectionByZ(index);
		for (int i=0; i < twodi.length ; i++){
			
			for (int j=0 ; j < twodi[0].length; j++){ 
				System.out.print(twodi[i][j]);
			}
			System.out.print("\n");
		}
	}
	else
		System.out.println("ilegal index");
		break;
	case "Z":
		if(!(index>=maze.getzSize())){
		twodi=maze.getCrossSectionByZ(index);
		for (int i=0; i < twodi.length ; i++){
			
			for (int j=0 ; j < twodi[0].length; j++){ 
				System.out.print(twodi[i][j]);
			}
			System.out.print("\n");
		}
		}
		else
			System.out.println("ilegal index");
		break;
		


	default:
		System.out.println("ilegal axis; choose x/y/z");
		break;
	}
	
}


/**
 * gets a solution representing a path of Position array and prints every Position as part of the path from start to exit
 */

@Override
public void showSolution(Solution<Position> s) {
	ArrayList<State<Position>> sol= s.getSolution();
	for (State<Position> p : sol)
		showMsg(p.toString());
	
}


@Override
public void update(Observable o, Object arg) {
	
	if (o==cli){
		this.todo=(String)arg;
		setChanged();
		notifyObservers();
	}
	
}

@Override
public Object getData(String string) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void displayLoadedMaze(String s) {
	// TODO Auto-generated method stub
	
}

@Override
public void showError(String s) {
	// TODO Auto-generated method stub
	
}

@Override
public String getViewType() {

	ViewType=this.getClass().getSimpleName();
	return ViewType;
}

@Override
public void initMazeWidget(Maze3d maze, String name) {
	// TODO Auto-generated method stub
	
}






}
