package algorithms.search;

import java.io.Serializable;

/**
 * 
 * 
* <h1>State</h1>
this class represent a state, a given situation of our problem, in case of a maze, state will be the position of a character.<P>
states can be manipulated and created accordingly to our needs  for example potential states are possibilities and our searcher algorithms use them as nodes 
to asses and find the best ones in order to create a solution.

* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-11-29
*/


public class State<T> implements Serializable{
    private T state;    // the state represented by a <T> type
    private double cost;     // State Cost (domain specific)
    private State<T> cameFrom;  // Father State 

    
public State(T state){     // a simple C'tor initialized with a specific domain state type object (e.g "position" in maze)
	this.state = state;
	this.cost = 1;
	this.cameFrom= null;
	
}
public State(){     // a simple C'tor 
	
	
}

public State(State<T> other){ // Copy C'tor
	this.state = other.getState();
	this.cost = other.getCost();
	this.cameFrom=other.getCameFrom();
	
}


	public T getState() {
		return state;
	}




	public void setState(T state) {
		this.state = state;
	}




	public double getCost() {
		return cost;
	}




	public void setCost(double cost) {
		this.cost = cost;
	}




	public State<T> getCameFrom() {
		return cameFrom;
	}




	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		State other = (State) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.state.toString();
	}


}

