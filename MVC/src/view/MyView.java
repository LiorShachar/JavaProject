package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Solution;
import controller.Controller;

public class MyView implements View {
	private Controller c;
	private CLI cli;
	
	 public MyView(Controller c){
	   this.c=c;
	   this.cli =new CLI(c.getCommandCreator(),new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
	  
	 }
	 
	 
	 
	 
	public Controller getC() {
		return c;
	}




	public void setC(Controller c) {
		this.c = c;
	}




	public CLI getCli() {
		return cli;
	}




	public void setCli(CLI cli) {
		this.cli = cli;
	}




	@Override
	public void start() {
		cli.start();
		
		
	}
	
	@Override
	public void displaySolution(Solution s) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void list(String string) {
		File lister = new File(string);
		String[] pathdetails = lister.list();
		for (String s: pathdetails)
			System.out.println(s);
		
	}

	@Override
public void printMsg(String s) {
	cli.getOut().println(s);
	cli.getOut().flush();
	

	
	
}




@Override
public void displayMaze(byte[] arr) {
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




@Override
public void displayCross(byte[] arr, String by, int index) {
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
}
