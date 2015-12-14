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



/**
 * this method puts a regex string as a key value for our generated Commands.
 * that way, the cli is able to distinct whether the input from the user matches the correct pattern
 *  for the right command. since some of the commands have similar words or multiple different parameters
 *  regex patterns make sure we get the right match. 
 *  
 *  <P>
 *   http://regexr.com/
 *  a very helpful website which helps build the right regex syntax
 *   
 * @param map
 */
	public void fillMap(HashMap<String, Command> map){
		
		
		
		map.put("dir [^\n\r]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		//generate 3d maze <name> <Y size> <X size> <Z size>
		map.put("generate 3d maze [^\n\r]+ [0-9]+ [0-9]+ [0-9]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("display (?!cross section by)[^\n\r]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("display cross section by [XYZxyz] [0-9]+ for [^\n\r]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("save maze [^\n\r]+ [^\n\r]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		

		map.put("load maze [^\n\r]+ [^\n\r]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("maze size [^\n\r]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("file size [^\n\r]+",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		
		
		
		
		
		
	}
	
	
	
	
	@Override
	public void setModel(Model m) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setView(View v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSolution(Solution s) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void calculate(MazeProblem p) {
		// TODO Auto-generated method stub
		
	}
	
}
