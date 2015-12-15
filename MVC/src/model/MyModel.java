package model;

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
		c.addMaze(name,gen.generate(y,x,z));
		c.toView("maze "+name+" is ready");
		
	}

}
