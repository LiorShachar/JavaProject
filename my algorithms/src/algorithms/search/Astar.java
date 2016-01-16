package algorithms.search;

import java.util.Objects;

/**
 * 
 * 
* <h1>A* 3d Maze Solver</h1>
* 
* this algorithm is a tweaked BFS which is Heuristic dependent, in order for it to calculate its nodes weight more cleverly.
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/
public class Astar<T> extends BFS<T> {

	private Heuristic<T> h;
	private Searchable<T> searchable;
	
	public Astar(Heuristic<T> h) {
		this.h=h;
		
	}
	
	@Override
	public Solution<T> search(Searchable<T> s) {
		this.searchable=s;
		return super.search(searchable);
	}

	@Override
	double updateCost(State<T> current, State<T> neighbor) {
		Objects.requireNonNull(current);Objects.requireNonNull(neighbor);
		if(current != null)
		return current.getCost()+h.calc(neighbor,searchable.getGoalState());
		else
			return h.calc(neighbor, searchable.getGoalState());
	}

}
