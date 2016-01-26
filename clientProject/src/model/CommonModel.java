package model;

import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
*
* 
* 
* 
* 
* <h1>CommonModel</h1> 
* has all the common features of the models in this package
* 
* <p>
* <b>Notes:</b>
*
* @author Lior Shachar
* @version 1.0
* @since 2016-1-26
*/



public abstract class CommonModel extends Observable implements Model {

	private HashMap<String, Object> notifications;
	
	
	public CommonModel() {
		notifications=new HashMap<String, Object>();
	}


	
	
	
	

	/**
	 * this method serves as an easy way to notify the observers with the
	 * appropriate outcome in this case we want to specifiy in
	 * the parameters what type of data we want the presenter to check.
	 * 
	 * @param  s
	 *            acts as the notification type
	 * @param o
	 *            acts as the data passed
	 */
	 
	void scno(String type, Object data) {
		notifications.put(type, data);
		setChanged();
		notifyObservers(type);
		
	}
	
	public Object getData(String string) {
		 return notifications.get(string);
	}
}
