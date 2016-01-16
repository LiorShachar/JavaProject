package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
/**
 * 
 * 
* <h1>Breadth First Search maze solver</h1>
* this class uses the bfs algorithm in order to arrange different states in a priority queue,
* it manipulates the states in order to create the perfect path.
*  
* 
* <p>
* <b>Notes:</b> 
*in this specific algorithm all the states have the same default weight.
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/
public class BFS<T> extends CommonSearcher<T> {
public BFS() {
	
}


	@Override
	public Solution<T> search(Searchable<T> s) {
		Objects.requireNonNull(s);

		openList.add(s.getInitialState()); // a priority queue of states to be
											// evaluated
		HashSet<State<T>> closedSet = new HashSet<State<T>>(); // a set of
																// states
																// already
																// evaluated

		while (openList.size() > 0) { // while p.queue is not empty

			State<T> n = popOpenList();// dequeue the best choice
			closedSet.add(n); // add it to the evaluated set so we won't check
								// it again

			if (n.equals(s.getGoalState())) // if we arrived to our desired
			{		// destination
				
				return backTrace(s.getInitialState(),n ); // return
																			// the
			}														// path
																			// of
																			// states
																			// from
																			// start
																			// state
																			// to
																			// goal
																			// state

			ArrayList<State<T>> successors = s.getAllPossibleStates(n); // create
																		// a
																		// list
																		// which
																		// contains
																		// the
																		// available
																		// nodes
																		// from
																		// our
																		// current
																		// node

			for (State<T> state : successors) { // for every available node
				if (!closedSet.contains(state) && !openList.contains(state)) { // if
																				// it
																				// isn't
																				// evaluated
																				// and
																				// not
																				// on
																				// the
																				// p.queue
					state.setCameFrom(n); // set its father node to be the
											// current node
					state.setCost(updateCost(n,state));
					openList.add(state); // add it to the p.queue

				} 
				else {
					if( !(state.getCameFrom()==null)&& n.getCost()<state.getCameFrom().getCost() ){ //if the neighbor node's path cost more
						state.setCameFrom(n); // set a new path by changing the parent
						state.setCost(updateCost(n,state)); // update the cost accordingly
						
						if(!openList.contains(state))
						openList.add(state);
						else
						{
							openList.remove(state);
							openList.add(state);
							
						}
					}

				}
			}
			 
		}
		
		System.out.println("no path created returned null");
		return null;

	}
	
	
	
	double updateCost(State<T> current, State<T> neighbor){
		return current.getCost()+neighbor.getCost();
	}

}


