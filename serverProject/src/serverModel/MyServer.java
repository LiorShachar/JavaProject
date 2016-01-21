/*package serverModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyServer {

	int port;
	ServerSocket server;
	
	ClientHandler clinetHandler;
	
	ExecutorService threadpool;
	Thread mainServerThread;
	
	
	volatile boolean stop;
	
	
	int numOfClients;
	int clientsHandled=0;
	
	
	
	
	public MyServer() {
		this.port=port;
		this.clientHandler=clinetHandler;
		this.numOfClients=numOfClients;
	}
	
	
	public void start() throws Exception{
		server=new ServerSocket(port);
		server.setSoTimeout(10*1000);
		threadpool=Executors.newFixedThreadPool(numOfClients);// we want to prevent a DOS attack so we use a thread pool
		
		mainServerThread=new Thread(new Runnable() {	// we listen inside a thread		
			@Override
			public void run() {
				while(!stop){
					try {
						final Socket someClient=server.accept(); //  get the client socket
						if(someClient!=null){
							threadpool.execute(new Runnable() {									
								@Override
								public void run() {
									try{										
										clientsHandled++;
										System.out.println("\thandling client "+clientsHandled);
										clinetHandler.handleClient(someClient.getInputStream(), someClient.getOutputStream()); //create the right client handler
										someClient.close(); 
										System.out.println("\tdone handling client "+clientsHandled);										
									}catch(IOException e){
										e.printStackTrace();
									}									
								}
							});								
						}
					}
					catch (SocketTimeoutException e){
						System.out.println("no clinet connected...");
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("done accepting new clients.");
			} // end of the mainServerThread task
		});
		
		mainServerThread.start();
		
	}
	
	public void close() throws Exception{
		stop=true;	
		// do not execute jobs in queue, continue to execute running threads
		System.out.println("shutting down");
		threadpool.shutdown();
		// wait 10 seconds over and over again until all running jobs have finished
		boolean allTasksCompleted=false;
		while(!(allTasksCompleted=threadpool.awaitTermination(10, TimeUnit.SECONDS)));
		
		System.out.println("all the tasks have finished");

		mainServerThread.join();		
		System.out.println("main server thread is done");
		
		server.close();
		System.out.println("server is safely closed");
	}
	
	
}
*/