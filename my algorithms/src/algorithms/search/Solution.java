package algorithms.search;

import java.util.ArrayList;

/**
 * 
 * 
* <h1>Solution</h1>
a class which represens a series of states leading from initial state to the goal, in a regular maze
it will contain the moves from the start position to the exit.
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/


public class Solution<T> {
	
	private ArrayList<State<T>> solution;

	public Solution() {
		
	}
	

	
	public Solution(ArrayList<State<T>> solution) {
		this.solution=solution;
	
	}
	
	
	public ArrayList<State<T>> getSolution() {
		return solution;
	}

	public void setSolution(ArrayList<State<T>> solution) {
		this.solution = solution;
	}
	
	public void printSolution(){
		for (State<T> s : solution)
			System.out.println(s);
	}
	

}
