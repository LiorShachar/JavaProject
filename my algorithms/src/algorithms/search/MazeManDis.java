package algorithms.search;

import algorithms.mazeGenerators.Position;
import static java.lang.Math.*;

/**
 * 
 * 
* <h1>Manhattan Distance Heuristic</h1>
this class is the Manhattan distance heuristic injected to an astar algorithm in order to produce a smarter weight for the nodes
by calculating its distance from the goal position by rows and columns.
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/





public class MazeManDis implements Heuristic<Position> {

	@Override
	public double calc(State<Position> init, State<Position> goal) {
		if (init!=null)
		return init.getCost()+ abs(goal.getState().getX()-init.getState().getX())+abs(goal.getState().getY()-init.getState().getY())+abs(goal.getState().getZ()-init.getState().getZ());
		else
			return abs(goal.getState().getX())+abs(goal.getState().getY())+abs(goal.getState().getZ());
	}
}
