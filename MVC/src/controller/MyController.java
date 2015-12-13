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




	public void fillMap(HashMap<String, Command> map){
		
		
		
		map.put("dir",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("dir",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("dir",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("dir",new Command(){

			@Override
			public void doCommand() {
				// TODO Auto-generated method stub
				
			}});
		
		map.put("dir",new Command(){

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
