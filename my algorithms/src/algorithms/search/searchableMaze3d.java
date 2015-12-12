package algorithms.search;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeProblem;
import algorithms.mazeGenerators.Position;

/**
 * 
 * 
* <h1>Searchable Maze 3d</h1>
this class represents the class adapter for maze 3d.
it converts the specific domain info into to an independent domain info.
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/




public class searchableMaze3d implements Searchable<Position> {
	
	private MazeProblem maze; //12/9/2015 changed in order to implement the object adapter strategy and not just a class adapter
	
	
	
public searchableMaze3d(Maze3d maze) {
	this.maze=maze;

}
	
/**
 * right now these methods require some fixing because they are casted to maze3d we need to define
 * global methods in MazeProblem interface that would be common to all puzzles in the future
 * then remove the casting 
 */

	@Override
	public State<Position> getInitialState() {
		return new State<Position>(((Maze3d) maze).getStartPosition());
	}

	@Override
	public State<Position> getGoalState() {
		return new State<Position>(((Maze3d) maze).getGoalPosition());
	}

	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		
		ArrayList<Position> temp = ((Maze3d) maze).getPossibleMovesList(s.getState());
		ArrayList<State<Position>> states = new ArrayList<State<Position>>();
		for (Position p:temp)
		{
			states.add(new State<Position>(p));
			}
		return states;
		
	}
	

}
