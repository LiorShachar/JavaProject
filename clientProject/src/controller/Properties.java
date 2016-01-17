package controller;



import java.io.Serializable;



public class Properties implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1264210855233611469L;
	/**
	 * 
	 */
	 
	 String generatingAlgorithm;
	 String searchingAlgorithm;
	 String heuristic;
	 String userInterface;
	 int numberOfThreads;
	 boolean sound;
	
	
	public Properties() {	
	}
	
	
	
	

	public void print(){
		System.out.println("generateing algorithm: "+generatingAlgorithm);
		System.out.println("solving algorithm: "+searchingAlgorithm);
		System.out.println("Astar Heuristic: "+heuristic);
		System.out.println("User Interface: "+userInterface);
		System.out.println("Number of Threads "+numberOfThreads);
		System.out.println("Sound is Enabled "+sound);
		
		
		
	}

	
	
	

		
		
	}
	
	
	
	
	
	
	


