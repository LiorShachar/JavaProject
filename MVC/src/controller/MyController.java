package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeProblem;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class MyController implements Controller {
	HashMap<String, byte[]> mazes;
	private Model m;
	private View v;
	HashMap<String, Command> commandCreator;
	

	
	public MyController(Model m, View v) {
		super();
		this.m = m;
		this.v = v;
		commandCreator = new HashMap<String, Command>();
		mazes=new HashMap<String, byte[]>();
		fillMap(commandCreator);
	}
	public MyController() {
		super();
		commandCreator = new HashMap<String, Command>();
		mazes=new HashMap<String,  byte[]>();
		fillMap(commandCreator);
	}


/**
 * this method puts a regEx string as a key value for our generated Commands.
 * that way, the CLI is able to distinct whether the input from the user matches the correct pattern
 *  for the right command. since some of the commands have similar words or multiple different parameters
 *  regEx patterns make sure we get the right syntax. 
 *  
 *  <P>
 *   http://regexr.com/
 *  a very helpful website which helps build the right regEx syntax
 *   
 * @param map
 */
	public void fillMap(HashMap<String, Command> map){
		
		
		
		map.put("dir [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				v.list(args[1]);
			}});
		
		//generate 3d maze <name> <x size (rows)> <y size (levels)> <z size(columns)>
		map.put("generate 3d maze [^\n\r]+ [0-9]+ [0-9]+ [0-9]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				m.generateMazeThread(args[3],Integer.parseInt(args[5]),Integer.parseInt(args[4]),Integer.parseInt(args[6]));
				
				
			}});
		
		//display <name>
		map.put("display (?!cross section by)(?!solution)[^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				v.displayMaze(mazes.get(args[1]));
				
			}});
		
		map.put("display cross section by [XYZxyz] [0-9]+ for [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				v.displayCross(mazes.get(args[7]),args[4],Integer.parseInt(args[5]));
				
			}});
		
		map.put("save maze [^\n\r]+ [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				m.saveMaze(mazes.get(args[2]),args[3]);
				
			}});
		

		map.put("load maze [^\n\r]+ [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
			addMaze(args[3], m.loadMaze(args[2]));
				
			}});
		
		map.put("maze size [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("file size [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("solve [^\n\r]+ [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		
		map.put("display solution [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("display solution [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		
		
		map.put("test",new Command(){

			@Override
			public void doCommand(String[] args) {
				System.out.println("test activated successfuly u guys");
				int d=0;
				
				for (int k=0; k < 10000;k++)
					for (int j=0; j < 1000000;j++){
						d=0;
						while (d<2)
							d++;
						}
						
						
					
				
				System.out.println("Test completed");
					
					
					
				
			}});
		map.put("threadtest",new Command(){

			@Override
			public void doCommand(String[] args) {
				
						
					
				
				System.out.println("Test completed");
					
					
					
				
			}});		
		
		
	}



public Model getModel() {
	return m;
}



public void setModel(Model m) {
	this.m = m;
}



public View getView() {
	return v;
}



public void setView(View v) {
	this.v = v;
}



public HashMap<String, Command> getCommandCreator() {
	return commandCreator;
}



public void setCommandCreator(HashMap<String, Command> commandCreator) {

	this.commandCreator = commandCreator;
}


@Override

public void toView(String s) {
	v.printMsg(s);
	
}
@Override
public void addMaze(String name, byte[] maze) {
	mazes.put(name, maze);
}
	
	
	
	
	}
	

