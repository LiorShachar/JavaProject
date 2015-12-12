package controller;

import algorithms.mazeGenerators.MazeProblem;
import algorithms.search.Solution;
import model.Model;
import view.View;

public interface Controller {
	   void setModel(Model m);
	   void setView(View v);
	   void setSolution(Solution s);
	   void calculate( MazeProblem p);

}
