package algorithms.search;

import algorithms.mazeGenerators.Position;
import static java.lang.Math.*;


/**
 * 
 * 
* <h1>Air Distance Heuristic</h1>
this class is the air distance heuristic injected to an astar algorithm in order to produce a smarter weight for the nodes
by calculating its price * the square root of its columns^2 and rows^2
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/




public class MazeAirDis implements Heuristic<Position> {

	@Override
	public double calc(State<Position> init, State<Position> goal) {
		
		return init.getCost()+sqrt(pow(goal.getState().getX()-init.getState().getX(),2)+pow(goal.getState().getY()-init.getState().getY(),2)+pow(goal.getState().getZ()-init.getState().getZ(),2));
	}

}
