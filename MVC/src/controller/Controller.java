package controller;

import java.util.HashMap;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeProblem;
import algorithms.search.Solution;
import model.Model;
import view.View;

public interface Controller {
	   
	   public Model getModel();
		public void setModel(Model m);
		public View getView();
		public void setView(View v);
		public HashMap<String, Command> getCommandCreator();
		public void setCommandCreator(HashMap<String, Command> commandCreator);
		public void toView(String s);
		public void addMaze(String name, Maze3d maze);
		
			
}
