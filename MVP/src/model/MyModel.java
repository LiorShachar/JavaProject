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
	
	
	private ExecutorService threadPool;
	
	private String error;
	private String msg;
	
	

	public MyModel() {

		mazes = new HashMap<String,Maze3d>();
		solutions = new HashMap<Maze3d, Solution<Position>>();
		threadPool = Executors.newCachedThreadPool();
		
		
		
	}

	/**
	 * generate a maze based on the coordinates size given and save it on the
	 * system with the specified name uses submit and callable to generate the
	 * thread in a separate thread
	 */

	public void handleGenerate(String name, int y, int x, int z) {

		if (!this.mazes.containsKey(name)) {
			scno("m", "**generating maze**");
			
			
			Future<Maze3d> futurem = threadPool.submit(new Callable<Maze3d>() {

				@Override
				public Maze3d call() throws Exception {
					MyMaze3dGenerator gen = new MyMaze3dGenerator();
					return gen.generate(y, x, z);

				}

			});
			try {
				mazes.put(name, futurem.get());
				
				scno("m", "**maze " + name + " is ready**");
			} catch (InterruptedException e) {
				scno("e", " thread interrupted, maze generation aborted");
				e.printStackTrace();
			} catch (ExecutionException e) {
				scno("e", " execution problem, maze generation aborted");
				e.printStackTrace();
			}

		} else {
			scno("e", "**Name already exists, please use a valid name**");
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
			msg = "**Save completed successfuly**";
			setChanged();
			notifyObservers("msg");
		} catch (IOException e) {
			error = "error: could not save";
			setChanged();
			notifyObservers("err");
			e.printStackTrace();
		}

	}

	/**
	 * load a maze from the path given and save it in the system with the
	 * specified name
	 */

	@Override
	public void handleLoadMaze(String path, String name) {
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
			msg = "Loading completed successfuly";
			setChanged();
			notifyObservers("msg");

		} catch (IOException e) {
			error = ("error: could not load from the file specified");
			setChanged();
			notifyObservers("err");
		}

	}

	/**
	 * saves the maze in a temporary file in order to test its size and return
	 * it
	 */

	@Override
	public void handleFileSize(String name) {
		handleSaveMaze(mazes.get(name).toByteArray(), "testfile.maz");
		File test = new File("testfile.maz");

		msg = "File Size of " + name + ": " + test.length() + " Bytes";
		setChanged();
		notifyObservers("msg");
		test.delete();

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
			scno("m", "Maze name found");
			Future<Solution<Position>> futures = null;
			if (algo.matches("[Bb][Ff][Ss]") || algo.matches("[Aa][Ss][Tt][Aa][Rr]")) {

				scno("m", "Solving " + name + " using " + algo);
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
					scno("m", "Solution for " + name + " is ready");
				} catch (InterruptedException e) {
					scno("e", "Cannot solve thread interrupted");
					e.printStackTrace();
				} catch (ExecutionException e) {
					scno("e", "Cannot solve, the thread was unable to send solution");
					e.printStackTrace();
				}
				
			}

			else {
				scno("e", "Mo such algorithm");
			}

		}
			else{
				scno("m", "Solution for " + name + " is ready");
			}	
		}
		else {
			scno("e", "Maze name doesn't exist");
			
			
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

	public String getError() {
		return error;
	}

	public String getMsg() {
		return msg;
	}

	/**
	 * this method serves as an easy way to notify the observers with the
	 * appropriate outcome in this case we want to save 3 lines by specifing in
	 * the parameters whether the data passed is an error a message or some
	 * other data it will set changed and notify the observers accordingly.
	 * 
	 * @param  s
	 *            acts as the notification type
	 * @param o
	 *            acts as the data passed
	 */
	public void scno(String s, Object o) {
		switch (s) {
		case "m":
			this.msg = (String) o;
			if (this.msg != null) {
				setChanged();
				notifyObservers("msg");
			}
			break;
		case "e":
			this.error = (String) o;
			if (this.error != null) {
				setChanged();
				notifyObservers("err");
			}
			break;
		default:
			System.out.println("unrecognized notification type, no changes were made");

		}

	}

}
