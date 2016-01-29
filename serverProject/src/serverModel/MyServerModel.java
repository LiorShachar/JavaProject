package serverModel;


import java.io.FileNotFoundException;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;

import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import singletonexplicitpack.Properties;

/**
 *
 * 
 * 
 * 
 * 
 * <h1>ServerModel</h1> 
 * the Model part of the server, responsible for handling the threadpool for handling different clients, and handling 
 * the sockets
 * 
 * <p>
 * <b>Notes:</b>
 *
 * @author Lior Shachar
 * @version 1.0
 * @since 2016-1-26
 */

public class MyServerModel extends Observable  implements Observer{

	private ConcurrentHashMap<String, Object> notifications;

	int port;
	
	ServerSocket server;

	Properties prop;

	private ExecutorService threadPool;
	Thread mainServerThread;

	
	ArrayList<Socket> SocketHolder; // an array list the holds all the client sockets
	ClientHandler clientHandler;

	

	volatile boolean stop;

	int numOfClients = 0;
	int clientsHandled = 0;

	public MyServerModel() {

		notifications = new ConcurrentHashMap<String, Object>();
		threadPool = Executors.newCachedThreadPool();
		SocketHolder=new ArrayList<Socket>();
	}

	void scno(String type, Object data) {
		notifications.put(type, data);
		setChanged();
		notifyObservers(type);

	}

	public Object getData(String string) {
		return notifications.get(string);
	}

	
	/**
	 * initiate an ordered shutdown for every part in the system
	 * */
	public void close() {
		
		
			
			scno("msg", "Shutting down...");
			stop=true;
			if (clientHandler!=null)
			clientHandler.close();
			threadPool.shutdown();
			
	}
	

	public void start() {
		handleLoadProperties();
		port = prop.getServer_port();
		
		
		threadPool = Executors.newFixedThreadPool(prop.getClientsCapacity());
		

		scno("modelReady", "");

	}

	
	/**
	 * runs the server thread to accept new client
	 * */
	
	public void runServer() {
		stop=false;
		clientHandler= new MyMaze3dClientHandler();
		((MyMaze3dClientHandler)clientHandler).addObserver(this);
		port = prop.getServer_port();
		
		
		try {
			this.server=new ServerSocket(this.port);
			server.setSoTimeout(10*1000);
			mainServerThread=new Thread(new Runnable() {	// we listen inside a thread		
				@Override
				public void run() {
					scno("status","Server UP and running....");
					while(!stop){
						try {
							final Socket someClient=server.accept(); //  get the client socket
							SocketHolder.add(someClient);
							scno("newConnection",String.valueOf(SocketHolder.size()-1)+" "+someClient.getInetAddress());
							if(someClient!=null){
								threadPool.execute(new Runnable() {									
									@Override
									public void run() {
										try{										
											clientsHandled++;
											scno("status","handling client "+clientsHandled);
											clientHandler.handleClient(someClient); 
											 
											someClient.close(); 
											System.out.println();
											scno("status","done handling client "+clientsHandled);
										}catch(IOException e){
											e.printStackTrace();
										}									
									}
								});								
							}
						}
						catch (SocketTimeoutException e){
							scno("status","no client connected...");
						} 
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					scno("status","*** Done accepting new clients ***");
					try {
						
						server.close();
					} catch (IOException e) {
						scno("error","Failed to close the server, socket error");
						e.printStackTrace();
					}
				} // end of the mainServerThread task
			});
			
			mainServerThread.start();
			
			
			
			
		} catch (IOException e) {
			scno("error","can't create server socket");
			e.printStackTrace();
		}
		
		
	}

	
	/**
	 * saves the current properties to the given path
	 * */
	public void handleSaveProperties(Properties p, String path) {
		try {
			XMLproperties.writeProperties(p, path);
			scno("msg", "settings saved successfuly");
		} catch (FileNotFoundException e) {
			scno("error", "File Not Found Exception");

		}

	}

	
	/***
	 * load properties from the default path 
	 * */
	public void handleLoadProperties() {
		try {
			prop = XMLproperties.getMyPropertiesInstance();

		} catch (FileNotFoundException e) {
			try {

				XMLproperties.writeProperties(new Properties(), "resources/properties.xml");
				System.out.println("error : no xml profile, creating a default one");
				prop = XMLproperties.getMyPropertiesInstance();
			} catch (FileNotFoundException e1) {
				System.out.println("fatal error: system cant write or load settings");
			}

		}
	}
	
	
	/**
	 * disconnect the specified client index (in the socketHolder)
	 * */
	public void DcClient(int index){
		try {
			SocketHolder.get(index).close();
		} catch (IOException e) {
			scno("error", "Session not available");
		}
		
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		String note = (String)arg;
		
		if(o==clientHandler){
			
			scno(note, (String)(clientHandler.getData(note)));
			
		}
		
	}

}
