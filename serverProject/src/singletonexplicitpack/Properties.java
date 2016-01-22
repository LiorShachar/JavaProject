package singletonexplicitpack;



import java.io.Serializable;



public class Properties implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7284442227337269279L;
	// String server_ip;
	 int server_port;
	 int clientsCapacity;
	// String userInterface;
	// String generatingAlgorithm;
	// String searchingAlgorithm;
	// String heuristic;
	
	// boolean sound;
	
	
	public Properties() { 	
		 
		server_port=5400;
		clientsCapacity=10;
		
		
	}
	
	
	
	




	public int getServer_port() {
		return server_port;
	}








	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}








	public int getClientsCapacity() {
		return clientsCapacity;
	}








	public void setClientsCapacity(int clientsCapacity) {
		this.clientsCapacity = clientsCapacity;
	}








	public void print(){
		
		System.out.println(this.server_port);
		System.out.println(this.clientsCapacity);
		
		
	}

	
	
	

		
		
	}
	
	
	
	
	
	
	


