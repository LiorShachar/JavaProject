package model;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.eclipse.swt.SWT;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.MazeManDis;
import algorithms.search.Solution;
import algorithms.search.searchableMaze3d;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

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
	private HashMap<String, Object> notifications;
	
	private ExecutorService threadPool;
	
	
	
	

	public MyModel() {

		mazes = new HashMap<String,Maze3d>();
		solutions = new HashMap<Maze3d, Solution<Position>>();
		threadPool = Executors.newCachedThreadPool();
		notifications=new HashMap<String, Object>();
		
		
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
					MyMaze3dGenerator gen = new MyMaze3dGenerator();
					return gen.generate(y, x, z);

				}

			});
			try {
				mazes.put(name, futurem.get());
				
				scno("loaded", name);
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

	}

	/**
	 * solve a maze represented by a key name with the algorithm specified in
	 * the string using a callable, in order to handle the solving in a separate thread
	 * 	
	 *  
	 */
	@Override
	public void handleUpdateStartPosition (Position p,String name){
		
		Maze3d tempmaze = mazes.get(name);
		if(tempmaze!=null){
		tempmaze.setStartPosition(p);
		mazes.put(name, tempmaze);
		}
		else
			scno("error","there was a problem updating the maze location (maze not found)");
		
	}
	
	
	
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

							return new Astar<Position>(new MazeManDis())
									.search(new searchableMaze3d(mazes.get(name)));

						}

					});
				}
				try {
					solutions.put(mazes.get(name), futures.get());
					scno("solutionReady",name);
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
				scno("solutionReady",name);
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
	public void handleKill() {
		
		try {
			this.threadPool.shutdown();
			scno("m", "Shutting down...");
			this.threadPool.awaitTermination(10,TimeUnit.SECONDS );
			scno("m", " --Bye Bye--");
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// adds a background thread for testing

	@Override
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
			if(solutions.containsKey(maze)){
				return solutions.get(maze);
			}
			scno("error", "no solution for this maze");
		}
		scno("error", "Maze name doesn't exist");
		return null;
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

	@Override
	public void handleDir(String string) {
		// TODO Auto-generated method stub
		
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
	public Object getData(String string) {
		 return notifications.get(string);
	}

	@Override
	public void handleMazeSize(String name) {
		scno("msg","Maze size in memory: "+mazes.get(name).toByteArray().length+"Bytes");
		
	}

	

	

}
