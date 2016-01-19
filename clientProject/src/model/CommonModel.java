package model;

import java.util.HashMap;
import java.util.Observable;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

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
