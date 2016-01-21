package serverModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Heuristic;
import algorithms.search.MazeAirDis;
import algorithms.search.MazeManDis;
import algorithms.search.Solution;
import algorithms.search.searchableMaze3d;
import protocol.DataObject;

public class MyMaze3dClientHandler extends Observable implements ClientHandler{
	
	
	
	ObjectOutputStream dataWriter;
	ObjectInputStream dataReader;

	volatile DataObject outPackage;
	volatile DataObject inPackage;

	private HashMap<String, Object> notifications;

	private static ConcurrentHashMap<Maze3d, Solution<Position>> solutions; // a
																			// shared
																			// map
																			// for
																			// all
																			// the
																			// client
																			// handlers
	
	
	public MyMaze3dClientHandler() {
		super();
		 solutions = new ConcurrentHashMap<Maze3d, Solution<Position>>();
		 notifications= new HashMap<String, Object>();
		loadCachedSolutions();
		
	}
	

	
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {

		// streams for sending and getting objects

		try {
			dataWriter = new ObjectOutputStream(outToClient);
			dataReader = new ObjectInputStream(inFromClient);
			dataWriter.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			outPackage = new DataObject("connected", "");
			dataWriter.writeObject(outPackage);
			dataWriter.flush();
			dataWriter.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String modelCommand;
		byte[] problem;
		do { // after the first ACK we start recieving orders from the client
			try {
				inPackage = (DataObject) dataReader.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			modelCommand = inPackage.getDataDetails();
			String params[]=modelCommand.split(" ");
			problem = (byte[]) inPackage.getData();

			switch (params[0]) {

			case "handleSolve":
				handleSolveMaze(params[1], params[1], params[1], new Maze3d(problem));
				break;

			}

		} while (inPackage != null && !inPackage.getDataDetails().equals("exit"));
		close();

	}

	

	public void handleSolveMaze(String name, String algo, String heurName ,Maze3d maze ) {
		
		Solution<Position> tempsol=null;
		boolean flag=false;
		if(!solutions.containsKey(maze)){
		
		if (algo.matches("[Bb][Ff][Ss]") || algo.matches("[Aa][Ss][Tt][Aa][Rr]")) {

			packageToClient("msgFromServer", "Solving " + name + " using " + algo);//TODO add this command to the client
			if (algo.matches("[Bb][Ff][Ss]")) {

				

						tempsol= new BFS<Position>().search(new searchableMaze3d(maze));
						flag=true;
					}

			

			} else if (algo.matches("[Aa][Ss][Tt][Aa][Rr]")) {

				
						Heuristic heur=null;
						if (heurName.matches("[Mm][Aa][Nn][Hh][Aa][Tt][Tt][Ee][Nn]"))
						{heur = new MazeManDis(); tempsol= new Astar<Position>(heur)
						.search(new searchableMaze3d(maze));}
						else if(heurName.matches("[Aa][Ii][Rr][Dd][Ii][Ss][Tt][Aa][Nn][Cc][Ee]"))
						{heur = new MazeAirDis();tempsol= new Astar<Position>(heur)
						.search(new searchableMaze3d(maze));}
						
						
						tempsol= new Astar<Position>(heur)
								.search(new searchableMaze3d(maze));
						flag=true;
			}		
			else{
				packageToClient("errorFromServer", "illegal Astar heuristic"); //TODO add error command to the client
				}		
		}
		else{
		tempsol=solutions.get(maze);
		flag=true;
		}
		
		
		if (flag)
		{
			
			
			packageToClient("solution "+name, tempsol);}
		
		
		
			}

	
	
	
	void close(){
		serializeAndCachSolutions();
		
		try {
			if(dataWriter!=null)
			dataWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			if(dataWriter!=null)
			dataReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/***
	 * writes a DataObject to the client input, also notifies the model about
	 * the command sent
	 */

	void packageToClient(String details, Object object) {
		try {
			dataWriter.writeObject(new DataObject(details, object));
			dataWriter.flush();
			dataWriter.reset();
			scno("packetSent", details); // TODO check that the view shows a log
											// with these details
		} catch (IOException e) {
			scno("error", "FATAL ERROR: cannot write to the client, IO exception");
		}
	}

	/**
	 * this method serves as an easy way to notify the observers with the
	 * appropriate outcome in this case we want to specifiy in the parameters
	 * what type of data we want the presenter to check.
	 * 
	 * @param s
	 *            acts as the notification type
	 * @param o
	 *            acts as the data passed
	 */
	void scno(String type, Object data) {
		notifications.put(type, data);
		setChanged();
		notifyObservers(type);

	}

	public void serializeAndCachSolutions(){
		HashMap<byte[], Solution<Position>> serialized = new HashMap<byte[], Solution<Position>>();
		Iterator<Maze3d> itr= solutions.keySet().iterator();
		while(itr.hasNext()){
			Maze3d temp=itr.next();
			serialized.put(temp.toByteArray(), solutions.get(temp));
		}

		try
        {
		FileOutputStream fos =
                new FileOutputStream("resources/memoryCach.zip");
		GZIPOutputStream gos = new GZIPOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(gos);
             oos.writeObject(serialized);
             oos.close();
             fos.close();
             System.out.println("cach updated successfuly to memoryCach.zip");
      }catch(IOException ioe)
       {
             scno("error", "problem updating the cach file");
       }
		
		
	}

	public void loadCachedSolutions(){
		HashMap<byte[], Solution<Position>> serialized = new HashMap<byte[], Solution<Position>>();
		try
	      {
	         FileInputStream fis = new FileInputStream("resources/memoryCach.zip");
	         GZIPInputStream gis = new GZIPInputStream(fis);
	         
	         ObjectInputStream ois = new ObjectInputStream(gis);
	         serialized = (HashMap) ois.readObject();
	         ois.close();
	         fis.close();
	     //  System.out.println("Deserialized HashMap..");
		      // Display content using Iterator
		      Set set = serialized.entrySet();
		      Iterator iterator = set.iterator();
		      while(iterator.hasNext()) {
		    	  Map.Entry mentry = ( Map.Entry)iterator.next();
		    	  solutions.put(new Maze3d((byte[]) mentry.getKey()), (Solution<Position>)mentry.getValue());
		      
		      }
		      System.out.println("Cached memory loaded"); //TODO turn this to a GUI msg
		      
	      }catch(IOException ioe)
	      {
	    	  scno("error", "problem loading the cach file: file not found");
	         
	      }catch(ClassNotFoundException c)
	      {
	         
	         scno("error", "problem loading the cach file: class not found");
	         
	      }
	    
	}
	
	
	
	public Object getData(String string) {
		return notifications.get(string);
	}




	
}
