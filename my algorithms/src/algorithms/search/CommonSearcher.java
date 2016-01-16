package algorithms.search;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 
 * 
* <h1>Common Searcher</h1>
* an abstract class with all the common features of the solving algorithms
*  
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-28
*/




public abstract class CommonSearcher<T> implements Searcher<T> {

	


		 protected PriorityQueue<State<T>> openList;
		 private int evaluatedNodes;

		 public CommonSearcher() {    // c'tor which initialize the evaluated nodes to 0 and creates the priority queue 
		  openList=new PriorityQueue<State<T>>(new Comparator<State<T>>() {

				@Override
				public int compare(State<T> current, State<T> next) {
					return (int)(current.getCost() - next.getCost()); // we want the cheaper element to be first in queue
				}
			});
		  
		  evaluatedNodes=0;
		 }

		 protected State<T> popOpenList() {  //gets the first "best" state in the priority queue
		  evaluatedNodes++;
		  return openList.poll();
		 }
		 
		 @Override
		 public abstract Solution<T> search(Searchable<T> s);

		 @Override
		 public int getNumberOfNodesEvaluated() {
		  return evaluatedNodes;
		 }
		 
		 
		 
		 
		 
		 /**
		  * 
		  * 
		 * <h1>Back Trace</h1>
		 get a start state and a goal state as parameters and return
		 an array consisted of the parent states all the way to the start.
			
		 * 
		 * 
		 * <b>Notes:</b> 
		 *
		 * @author  Lior Shachar
		 * @version 1.0
		 * @since   2015-11-29
		 */

		 
		 public Solution<T> backTrace(State<T> start , State<T> end){
			 Solution<T> path = new Solution<T>(null);
			 ArrayList<State<T>> list = new ArrayList<State<T>>();
			 State<T> current = end;
			 list.add(current);
			 while (!current.equals(start))
			 {
				 if(current.getCameFrom()!= null){
				 list.add(current.getCameFrom());
				 current=current.getCameFrom();
				 }
				 else
					 System.out.println("ERROR : path not complete");
			 }
			 Collections.reverse(list);
			 path.setSolution(list);
			 return path;
		 }
		 }
	
		 
		



