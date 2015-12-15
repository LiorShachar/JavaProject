package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.MazeProblem;
import algorithms.search.Solution;
import model.Model;
import view.View;

public class MyController implements Controller {

	private Model m;
	private View v;
	HashMap<String, Command> commandCreator;
	

	
	public MyController(Model m, View v) {
		super();
		this.m = m;
		this.v = v;
		commandCreator = new HashMap<String, Command>();
		fillMap(commandCreator);
	}
	public MyController() {
		super();
		commandCreator = new HashMap<String, Command>();
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
				
				
				
			}});
		
		//generate 3d maze <name> <Y size> <X size> <Z size>
		map.put("generate 3d maze [^\n\r]+ [0-9]+ [0-9]+ [0-9]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("display (?!cross section by)(?!solution)[^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("display cross section by [XYZxyz] [0-9]+ for [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("save maze [^\n\r]+ [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
			}});
		

		map.put("load maze [^\n\r]+ [^\n\r]+",new Command(){

			@Override
			public void doCommand(String[] args) {
				// TODO Auto-generated method stub
				
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
	
	
	
	
	}
	

