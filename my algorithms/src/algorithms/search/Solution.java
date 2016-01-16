package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * 
* <h1>Solution</h1>
a class which represent a series of states leading from initial state to the goal, in a regular maze
it will contain the moves from the start position to the exit.
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/


public class Solution<T> implements Serializable{
	
	/**
	 * 
	 */
	
	private ArrayList<State<T>> solution;

	public Solution() {
		
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((solution == null) ? 0 : solution.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Solution other = (Solution) obj;
		if (solution == null) {
			if (other.solution != null)
				return false;
		} else if (!solution.equals(other.solution))
			return false;
		return true;
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
	
	
	

}
