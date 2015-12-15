package model;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeProblem;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Controller;

public class MyModel implements Model {

	
	private Controller c;
	
	 public MyModel(Controller c){
	   this.c = c;
	 }

	
	@Override
	public void search(MazeProblem p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void generateMaze(String name, int y, int x, int z) {
		
		System.out.println("generating maze...");
		MyMaze3dGenerator gen = new MyMaze3dGenerator();
		Maze3d created = gen.generate(y,x,z);
		c.addMaze(name,created.toByteArray());
		c.toView("maze "+name+" is ready");
		
	}
	
public void generateMazeThread(String name, int y, int x, int z) {
		
	new Thread(new Runnable() {
		   public void run() {
			   generateMaze(name,y,x,z);
		   }
		 }).start();

		
	}
}
