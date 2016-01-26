package singletonexplicitpack;



import java.io.Serializable;

/**
*
* 
* 
* 
* 
* <h1>Properties</h1> 
* a class that holdes all the information needed to initialize 
* dynamic vars in the project, it is held static in the XMLproperties class
* 
* <p>
* <b>Notes:</b>
*

* @author Lior Shachar
* @version 1.0
* @since 2016-1-26
*/

public class Properties implements Serializable {
	

	
	 String server_ip;
	 int server_port;
	 String userInterface;
	 String generatingAlgorithm;
	 String searchingAlgorithm;
	 String heuristic;
	
	 boolean sound;
	
	
	public Properties() { 	
		 server_ip="localhost";
		 server_port=5400;
		  userInterface="gui";
		 generatingAlgorithm="dfs";
		 searchingAlgorithm="astar";
		  heuristic="manhatten";
		 
		 sound=true;
		
		
		
	}
	
	
	
	

	public Properties(String server_ip, int server_port, String userInterface, String generatingAlgorithm,
			String searchingAlgorithm, String heuristic,  boolean sound) {
		
		this.server_ip = server_ip;
		this.server_port = server_port;
		this.userInterface = userInterface;
		this.generatingAlgorithm = generatingAlgorithm;
		this.searchingAlgorithm = searchingAlgorithm;
		this.heuristic = heuristic;
		
		this.sound = sound;
	}





	public String getServer_ip() {
		return server_ip;
	}





	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}





	public int getServer_port() {
		return server_port;
	}





	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}





	public String getUserInterface() {
		return userInterface;
	}





	public void setUserInterface(String userInterface) {
		this.userInterface = userInterface;
	}





	public String getGeneratingAlgorithm() {
		return generatingAlgorithm;
	}





	public void setGeneratingAlgorithm(String generatingAlgorithm) {
		this.generatingAlgorithm = generatingAlgorithm;
	}





	public String getSearchingAlgorithm() {
		return searchingAlgorithm;
	}





	public void setSearchingAlgorithm(String searchingAlgorithm) {
		this.searchingAlgorithm = searchingAlgorithm;
	}





	public String getHeuristic() {
		return heuristic;
	}





	public void setHeuristic(String heuristic) {
		this.heuristic = heuristic;
	}








	public boolean isSound() {
		return sound;
	}





	public void setSound(boolean sound) {
		this.sound = sound;
	}





	public void print(){
		System.out.println("generateing algorithm: "+generatingAlgorithm);
		System.out.println("solving algorithm: "+searchingAlgorithm);
		System.out.println("Astar Heuristic: "+heuristic);
		System.out.println("User Interface: "+userInterface);
	
		System.out.println("Sound is Enabled "+sound);
		
		
		
	}

	
	
	

		
		
	}
	
	
	
	
	
	
	


