package model;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MyModelGui extends CommonModel implements Model {
	
	
	private HashMap<String, Maze3d> mazes;
	private HashMap<Maze3d, Solution<Position>> solutions;
	
	private LinkedHashMap<String, Object> noteDataMap; // the data which is sent to the presenter according to the notification
	
	
	private ExecutorService threadPool;
	
	
	
	
	
	

	@Override
	public void handleGenerate(String name, int y, int x, int z) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleDir(String string) {
		File lister = new File(string);
		try {
			String[] pathdetails = lister.list();
			for (String s: pathdetails)
				printMsg(s);
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
	public String getError() {
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
		
		

	}
	

}
