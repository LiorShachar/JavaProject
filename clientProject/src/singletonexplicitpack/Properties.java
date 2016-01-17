package singletonexplicitpack;



import java.io.Serializable;



public class Properties implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7914288303263635885L;
	/**
	 * 
	 */
	
	 String server_ip;
	 int server_port;
	 String userInterface;
	 String generatingAlgorithm;
	 String searchingAlgorithm;
	 String heuristic;
	 int numberOfThreads;
	 boolean sound;
	
	
	public Properties() {	
	}
	
	
	
	

	public Properties(String server_ip, int server_port, String userInterface, String generatingAlgorithm,
			String searchingAlgorithm, String heuristic, int numberOfThreads, boolean sound) {
		super();
		this.server_ip = server_ip;
		this.server_port = server_port;
		this.userInterface = userInterface;
		this.generatingAlgorithm = generatingAlgorithm;
		this.searchingAlgorithm = searchingAlgorithm;
		this.heuristic = heuristic;
		this.numberOfThreads = numberOfThreads;
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





	public int getNumberOfThreads() {
		return numberOfThreads;
	}





	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
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
		System.out.println("Number of Threads "+numberOfThreads);
		System.out.println("Sound is Enabled "+sound);
		
		
		
	}

	
	
	

		
		
	}
	
	
	
	
	
	
	


