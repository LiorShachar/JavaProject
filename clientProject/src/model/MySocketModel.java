package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import javax.management.NotificationBroadcasterSupport;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import controller.Properties;


public class MySocketModel extends CommonModel implements Model {
	
	
	
	int port;
	
	String ipaddress;
	
	Socket serverSocket;
	
	Properties prop;
	
	//streams for communicating with simple strings
	PrintWriter stringToServer; 
	BufferedReader stringFromServer;
	
	
	//streams for sending and getting objects
	ObjectOutputStream dataWriter;
	ObjectInputStream dataReader;
	
	//temporary data holders so we can get the items when we want to
	private HashMap<String, Maze3d> mazes;
	private HashMap<Maze3d, Solution<Position>> solutions;
	
	
	
	
	public MySocketModel() {
		super();
		
	}

	
	
	
	
	
	
	
	@Override
	public void handleGenerate(String name, int y, int x, int z) {
		Object m[]={name,y,x,z};
		packageToServer("handleGenerate",m);

	}

	@Override
	public void handleDir(String string) {
		Object m=string;
		packageToServer("handleDir",m);

	}

	@Override
	public void handleSaveMaze(byte[] maze, String path) {
		Object m[]={maze,path};
		packageToServer("handleSaveMaze",m);

	}

	@Override
	public void handleUpdatePosition(Position p, String name) {
		Object m[]={p,name};
		packageToServer("handleUpdatePosition",m);

	}

	@Override
	public void handleLoadMaze(String path, String name) {
		Object m[]={path,name};
		packageToServer("handleLoadMaze",m);

	}

	@Override
	public void handleFileSize(String name) {
		Object m=name;
		packageToServer("handleFileSize",m);

	}

	@Override
	public void handleMazeSize(String name) {
		Object m=name;
		packageToServer("handleMazeSize",m);

	}

	@Override
	public Solution<Position> getSolutionFor(String name) {
		Maze3d maze=getMazeByName(name);
		if(maze!=null){
			if(solutions.containsKey(maze)){
				return solutions.get(maze);
			}
			scno("error", "no solution for this maze");
		}
		scno("error", "Maze name doesn't exist");
		return null;
	}


	@Override
	public void handleSolveMaze(String name, String algo) {
		Object m[]={name, algo};
		packageToServer("handleSolveMaze",m);

	}


	@Override
	public void handleExit() {
		Object m="";
		packageToServer("handleExit",m);

	}


	@Override
	public Maze3d getMazeByName(String string) {
		return new Maze3d(mazes.get(string).toByteArray());
	}

	@Override
	public void handleLoadProperties(String path) {
		
		
		//TODO load settings for socket model
		

	}

	@Override
	public void handleSaveProperties(String path) {
		//TODO  Save Settings for socket model

	}

	@Override
	public void serializeAndCachSolutions() {
		//TODO tell the remote to cach?! might not even needed

	}

	
	void packageToServer(String details,Object object) {
		try {
			dataWriter.writeObject(new DataObject(details, object));
		} catch (IOException e) {
			scno("error","FATAL ERROR: cannot write to the server, check please connection");
		}
	}
	
	DataObject packageFromServer()  {
		try {
			return (DataObject) dataReader.readObject();
		} catch (ClassNotFoundException e) {
			
			scno("error", "request from the server failed");
			e.printStackTrace();
		} catch (IOException e) {
			
			scno("error", "request from the server failed");
			e.printStackTrace();
		}
		return null;
	}
	
}
