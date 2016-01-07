package model;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;



/**
 * NEED TO HANDLE NOTIFICATIONS IN A HASH MAP
 * **/



public class MyModelGui extends CommonModel implements Model {
	
	
	private HashMap<String, Maze3d> mazes;
	private HashMap<Maze3d, Solution<Position>> solutions;
	
	private PriorityQueue<String> notificationsDataQueue; // the data which is sent to the presenter according to the notification order
	
	
	private ExecutorService threadPool;
	
	
	
	
	
	

	public MyModelGui() {
		
		this.mazes = new HashMap<String, Maze3d>();
		this.solutions = new HashMap<Maze3d, Solution<Position>>();
		
		this.threadPool = Executors.newCachedThreadPool();
	}

	@Override
	public void handleGenerate(String name, int y, int x, int z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleDir(String string) {
		File lister = new File(string);
		try {
			String[] pathdetails = lister.list();
			
		} catch (NullPointerException e) {
			printMsg("File or Directory not found");
		}

	}

	@Override
	public void handleSaveMaze(byte[] maze, String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleLoadMaze(String path, String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleFileSize(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<String, Maze3d> getMazes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleSolveMaze(String name, String algo) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<Maze3d, Solution<Position>> getSolutions() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object getData(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleKill() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testThread() {
		// TODO Auto-generated method stub

	}
	
	
	void notif(String comKey, Object Data) {
		// TODO Auto-generated method stub
		

	}
	

}
