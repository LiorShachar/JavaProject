package model;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.eclipse.swt.SWT;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.Heuristic;
import algorithms.search.MazeAirDis;
import algorithms.search.MazeManDis;
import algorithms.search.Solution;
import algorithms.search.searchableMaze3d;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import singletonexplicitpack.Properties;
import sun.management.ManagementFactoryHelper;

/**
 *
 * 
 * 
 * 
 * 
 * <h1>MyModel</h1> this class represent my controller part of the project, it
 * is suitable for a Maze3d problem.
 * 
 * 
 * <p>
 * <b>Notes:</b>
 *
 * @author Lior Shachar
 * @version 1.0
 * @since 2015-12-17
 */

public class MyModel extends CommonModel {

	private HashMap<String, Maze3d> mazes;
	private HashMap<Maze3d, Solution<Position>> solutions;
	Properties prop;
	private ExecutorService threadPool;
	
	
	
	

	
	
	
	public MyModel() {

		mazes = new HashMap<String,Maze3d>();
		solutions = new HashMap<Maze3d, Solution<Position>>();
		threadPool=Executors.newCachedThreadPool();
		
		
	}

	/**
	 * generate a maze based on the coordinates size given and save it on the
	 * system with the specified name uses submit and callable to generate the
	 * thread in a separate thread
	 */

	public void handleGenerate(String name, int y, int x, int z) {

		if (!this.mazes.containsKey(name)) {
			
			
			
			Future<Maze3d> futurem = threadPool.submit(new Callable<Maze3d>() {

				@Override
				public Maze3d call() throws Exception {
					Maze3dGenerator gen;
					if(prop.getGeneratingAlgorithm().matches("[Dd][Ff][Ss]")){
					gen = new MyMaze3dGenerator();
					}
					else{
						gen = new SimpleMaze3dGenerator();
					}
					return gen.generate(y, x, z);

				}

			});
			try {
				mazes.put(name, futurem.get());
				
				scno("loaded", name);
				generateListStatus();
			} catch (InterruptedException e) {
				scno("error", " thread interrupted, maze generation aborted");
				e.printStackTrace();
			} catch (ExecutionException e) {
				scno("error", " thread execution problem, maze generation aborted");
				e.printStackTrace();
			}

		} else {
			scno("error", "Name already exists, please use a valid name");
		}
	}

	/**
	 * save an array of bytes that represent a maze in the specified path
	 */
	@Override
	public void handleSaveMaze(byte[] maze, String path) {
		try {
			MyCompressorOutputStream writer = new MyCompressorOutputStream(new FileOutputStream(path));
			writer.write(maze);
			writer.close();
			scno("msg", "Save completed successfuly");
		} catch (IOException e) {
			scno("error", "There was a problem saving the maze");
			e.printStackTrace();
		}

	}

	/**
	 * load a maze from the path given and save it in the system with the
	 * specified name
	 */

	@Override
	public void handleLoadMaze(String path, String name) {
		if (!this.mazes.containsKey(name)) {
		try {
			// we need to know the array size, the compressed version of the
			// maze have a pattern of (value,number of returns)
			// so if we sum up all the values in the odd index places we'll get
			// the right size
			DataInputStream reader = new DataInputStream(new FileInputStream(path));
			int val = 0;
			int i = 0;
			int sum = 0;
			while (reader.available() >0) {
				val = reader.read();
				if (!(i % 2 == 0)) {
					sum += val;
				}
				i++;
			}
			reader.close();

			byte[] arr = new byte[sum];
			MyDecompressorInputStream comp = new MyDecompressorInputStream(new FileInputStream(path));
			comp.read(arr);
			comp.close();
			mazes.put(name, new Maze3d(arr));
			scno("loaded",name);
			generateListStatus();

		} catch (IOException e) {
			scno("error","loading maze failed");
		}
		
		}
		else
			scno("error", "Name already exists, please use a valid name");
		
	}

	/**
	 * saves the maze in a temporary file in order to test its size and return
	 * it
	 */

	@Override
	public void handleFileSize(String name) {
		handleSaveMaze(mazes.get(name).toByteArray(), "testfile.maz");
		File test = new File("testfile.maz");
		scno("msg", "File Size of " + name + ": " + test.length() + " Bytes");
		test.delete();
		//TODO computes the file size by saving it...need another way
	}

	
	@Override
	public void handleUpdatePosition(Position p,String name){
		
		
		Maze3d currentmaze=mazes.get(name);
		currentmaze.setStartPosition(new Position(p));
		
		
		
		
	}
	
	
	/**
	 * solve a maze represented by a key name with the algorithm specified in
	 * the string using a callable, in order to handle the solving in a separate thread
	 * 	
	 *  
	 */
	@Override
	public void handleSolveMaze(String name, String algo) {
		
		if (mazes.containsKey(name)) {
			
			if(!solutions.containsKey(mazes.get(name))){
			
			Future<Solution<Position>> futures = null;
			if (algo.matches("[Bb][Ff][Ss]") || algo.matches("[Aa][Ss][Tt][Aa][Rr]")) {

				scno("msg", "Solving " + name + " using " + algo);
				if (algo.matches("[Bb][Ff][Ss]")) {

					futures = threadPool.submit(new Callable<Solution<Position>>() {

						@Override
						public Solution<Position> call() throws Exception {

							return new BFS<Position>().search(new searchableMaze3d(mazes.get(name)));

						}

					});

				} else if (algo.matches("[Aa][Ss][Tt][Aa][Rr]")) {

					futures = threadPool.submit(new Callable<Solution<Position>>() {

						@Override
						public Solution<Position> call() throws Exception {
							Heuristic heur=null;
							if (prop.getHeuristic().matches("[Mm][Aa][Nn][Hh][Aa][Tt][Tt][Ee][Nn]"))
							{heur = new MazeManDis();}
							else if(prop.getHeuristic().matches("[Aa][Ii][Rr][Dd][Ii][Ss][Tt][Aa][Nn][Cc][Ee]"))
							{heur = new MazeAirDis();}
							else{
								scno("error", "illegal Astar heuristic");
								return null;}
							
							return new Astar<Position>(heur)
									.search(new searchableMaze3d(mazes.get(name)));

						}

					});
				}
					try {
					solutions.put(mazes.get(name), futures.get());
					scno("solutionReady",name);
					generateListStatus();
				} catch (InterruptedException e) {
					scno("error", "Cannot solve thread interrupted");
					e.printStackTrace();
				} catch (ExecutionException e) {
					scno("error", "Cannot solve, the thread was unable to send solution");
					e.printStackTrace();
				}
				
			}

			else {
				scno("error", "Mo such algorithm");
			}

		}
			else{
				scno("solutionExist",name);
				generateListStatus();
			}	
		}
		else {
			scno("error", "Maze name doesn't exist");
			
			
		}

	}



	
	/**
	 * shutting down every thread that was created by this model
	 */

	@Override
	public void close() {
		
		try {
			serializeAndCachSolutions();
			scno("msg", "Shutting down...");
			this.threadPool.shutdown();
			this.threadPool.awaitTermination(10,TimeUnit.SECONDS );
			
			
		} catch (InterruptedException e) {
			scno("error", "InterruptedException");
			
		}
		
	}

	// adds a background thread for testing

	
	public void testThread() {

		threadPool.execute(new Runnable() {
			public void run() {
				
				int c=0;
				while (c!=1000000) {
					c++;
					c++;
					c++;
					c++;
					c++;
					c++;
					c++;
					
					c--;
					c--;
					c--;
					c--;
					c--;
					c--;
					
					
				}
				
			}
		});

	}

	public HashMap<String, Maze3d> getMazes() {
		return mazes;
	}

	public HashMap<Maze3d, Solution<Position>> getSolutions() {
		return solutions;
	}

	@Override
	public Solution<Position> getSolutionFor(String name) {
		Maze3d maze= (Maze3d)getMazeByName(name);
		if(maze!=null){
			if(hasSolution(name)){
				return solutions.get(maze);
			}
			scno("error", "no solution for this maze (NULL POINTER TO MAZE)");
		}
		scno("error", "Maze name doesn't exist");
		return null;
	}

	@Override
	public boolean hasSolution(String name){
		return solutions.containsKey((Maze3d)getMazeByName(name));
		
		}
	
	

	/**
	 * 
	 * updates the presentor about current list of items and their statuses 
	 * method can be called whenever it seems appropriate to update on some changes
	 * **/
	void generateListStatus(){
		ArrayList<String> status = new ArrayList<String>();
		for(String m : mazes.keySet()){
			if(hasSolution(m))
			status.add(m+" [V]");
			else
		status.add(m+" [X]");
		}
		String items[]=new String[status.size()];
		status.toArray(items);
		if(!(items.length<1))
		scno("updateListStatus",items);
	
	}
	



	@Override
	public void handleDir(String string) {
		File lister = new File(string);
		try {
			String[] pathdetails = lister.list();
			scno("DirDetails", pathdetails);
		} catch (NullPointerException e) {
			scno("error", "file or directory isn't found");
		}
		
		
	}
	@Override
	public Object getMazeByName(String string) {
		if(mazes.containsKey(string))
		 return mazes.get(string);
		else{
			scno("error", "cant save maze");
		return null;
		}
	}
	
	
	
	
	@Override
	public void handleMazeSize(String name) {
		scno("msg","Maze size in memory: "+mazes.get(name).toByteArray().length+"Bytes");
		
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
		      scno("msg","Cached memory loaded");
		      generateListStatus();
	      }catch(IOException ioe)
	      {
	    	  scno("error", "problem loading the cach file: file not found");
	         
	      }catch(ClassNotFoundException c)
	      {
	         
	         scno("error", "problem loading the cach file: class not found");
	         
	      }
	    
	}

	
	
	
	@Override
	public void handleLoadProperties() {
		try {
			prop=XMLproperties.getMyPropertiesInstance();
			
		} catch (FileNotFoundException e) {
			try {
				
				XMLproperties.writeProperties(new Properties(), "resources/properties.xml");
				System.out.println("error : no xml profile, creating a default one");
				prop=XMLproperties.getMyPropertiesInstance();
			} catch (FileNotFoundException e1) {
				System.out.println("fatal error: system cant write or load settings");
			}
			
		}
	}

	
	@Override
	public void handleSaveProperties(Properties p,String path) {
		try {
			XMLproperties.writeProperties(p, path);
			scno("msg","settings saved successfuly");
		} catch (FileNotFoundException e) {
			scno("error","File Not Found Exception");
			
		}
		
		
	}

	

	@Override
	public void start() {
		handleLoadProperties();
		loadCachedSolutions();
		scno("modelReady", "");
		
	}

	@Override
	public void handleCustomProperties(String path) {
		try {
			prop=XMLproperties.getCustomProperties(path);
			scno("loadedCustomSettings","");
		} catch (FileNotFoundException e) {
			try {
				
				
				System.out.println("error : problem loading xml profile, loading the default one");
				prop=XMLproperties.getMyPropertiesInstance();
			} catch (FileNotFoundException e1) {
				System.out.println("fatal error: system cant write or load settings");
			}
			
		}
		
	}
	

	}
