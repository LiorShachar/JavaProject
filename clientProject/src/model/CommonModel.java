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


	void scno(String type, Object data) {
		notifications.put(type, data);
		setChanged();
		notifyObservers(type);
		
	}
	
	public Object getData(String string) {
		 return notifications.get(string);
	}
}
