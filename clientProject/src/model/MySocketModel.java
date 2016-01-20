package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.management.NotificationBroadcasterSupport;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import protocol.DataObject;
import singletonexplicitpack.Properties;

public class MySocketModel extends CommonModel implements Model {

	int port;

	String ipaddress;

	Socket serverSocket;

	Thread inputThread;

	Properties prop;
	

	// streams for communicating with simple strings
	PrintWriter stringToServer;
	BufferedReader stringFromServer;

	// streams for sending and getting objects
	ObjectOutputStream dataWriter;
	ObjectInputStream dataReader;

	// temporary data holders so we can get the items when we want to
	private HashMap<String, Maze3d> mazes;
	private HashMap<Maze3d, Solution<Position>> solutions;

	

	public MySocketModel() {
		super();
		mazes = new HashMap<String, Maze3d>();
		solutions = new HashMap<Maze3d, Solution<Position>>();

	}

	@Override
	public void start() {
		handleLoadProperties();
		ipaddress = prop.getServer_ip();
		port = prop.getServer_port();

		try {
			serverSocket = new Socket(ipaddress, port);

			dataReader = new ObjectInputStream(serverSocket.getInputStream());
			
			dataWriter = new ObjectOutputStream(serverSocket.getOutputStream());
			
			// TODO delete the stacktraces
			inputThread = new Thread(new Runnable() {

				@Override
				public void run() {

					try {
						DataObject input;
						do {

							input = (DataObject) dataReader.readObject();

							if (input != null)
								scno(input.getDataDetails(), input.getData());

						} while (input != null && !input.getDataDetails().equals("exit"));

						scno("msg", "Connection ended");
					} catch (ClassNotFoundException e) {
						scno("error", "problem reading the object from server");
						e.printStackTrace();
					} catch (IOException e) {
						scno("error", "problem reading the object from server");
						e.printStackTrace();
					}

				}
			});
			inputThread.start();
		} catch (UnknownHostException e) {
			scno("error", "unknown host");
			e.printStackTrace();
		} catch (SocketException e) {
			scno("error", "socket exception");
			e.printStackTrace();
		} catch (IOException e) {
			scno("error", "IOException");
			e.printStackTrace();
		}
		scno("modelReady","");
	}

	@Override
	public void handleGenerate(String name, int y, int x, int z) {
		Object m[] = { name, y, x, z };
		packageToServer("handleGenerate", m);

	}

	@Override
	public void handleDir(String string) {
		Object m = string;
		packageToServer("handleDir", m);

	}

	@Override
	public void handleSaveMaze(byte[] maze, String path) {
		Object m[] = { maze, path };
		packageToServer("handleSaveMaze", m);

	}

	@Override
	public void handleUpdatePosition(Position p, String name) {
		Object m[] = { p, name };
		packageToServer("handleUpdatePosition", m);

	}

	@Override
	public void handleLoadMaze(String path, String name) {
		Object m[] = { path, name };
		packageToServer("handleLoadMaze", m);

	}

	@Override
	public void handleFileSize(String name) {
		Object m = name;
		packageToServer("handleFileSize", m);

	}

	@Override
	public void handleMazeSize(String name) {
		Object m = name;
		packageToServer("handleMazeSize", m);

	}

	@Override
	public Solution<Position> getSolutionFor(String name) {
		Maze3d maze = getMazeByName(name);
		if (maze != null) {
			if (solutions.containsKey(maze)) {
				return solutions.get(maze);
			}
			scno("error", "no solution for this maze");
		}
		scno("error", "Maze name doesn't exist");
		return null;
	}

	@Override
	public void handleSolveMaze(String name, String algo) {
		Object m[] = { name, algo };
		packageToServer("handleSolveMaze", m);

	}

	@Override
	public Maze3d getMazeByName(String string) {
		return new Maze3d(mazes.get(string).toByteArray());
	}

	@Override
	public void handleLoadProperties() {

		try {
			prop = XMLproperties.getMyPropertiesInstance();

		} catch (FileNotFoundException e) {
			try {

				XMLproperties.writeProperties(new Properties(), "resources/properties.xml");
				scno("error", "no xml profile, creating a default one");
				prop = XMLproperties.getMyPropertiesInstance();
			} catch (FileNotFoundException e1) {
				scno("error", "fatal error: system cant write or load settings");
			}

		}

	}

	@Override
	public void handleSaveProperties(Properties p, String path) {
		try {
			XMLproperties.writeProperties(p, path);
		} catch (FileNotFoundException e) {
			scno("error", "File Not Found Exception");

		}

	}

	void packageToServer(String details, Object object) {
		try {
			dataWriter.writeObject(new DataObject(details, object));
			dataWriter.flush();
			dataWriter.reset();
		} catch (IOException e) {
			scno("error", "FATAL ERROR: cannot write to the server, check please connection");
		}
	}

	/*
	 * DataObject packageFromServer() { try { return (DataObject)
	 * dataReader.readObject(); } catch (ClassNotFoundException e) {
	 * 
	 * scno("error", "request from the server failed"); e.printStackTrace(); }
	 * catch (IOException e) {
	 * 
	 * scno("error", "request from the server failed"); e.printStackTrace(); }
	 * return null; }
	 */

	@Override
	public void close() {
		// TODO tell the remote server your'e done

	}

	@Override
	public void handleCustomProperties(String path) {
		try {
			prop = XMLproperties.getCustomProperties(path);
			scno("loadedCustomSettings", "");
		} catch (FileNotFoundException e) {
			try {

				System.out.println("error : problem loading xml profile, loading the default one");
				prop = XMLproperties.getMyPropertiesInstance();
			} catch (FileNotFoundException e1) {
				System.out.println("fatal error: system cant write or load settings");
			}

		}

	}

	@Override
	public boolean hasSolution(String name) {
		// TODO CHECK IF THIS WORKS RIGHT CONSIDER THE REMOTE CACH
		return solutions.containsKey((Maze3d) getMazeByName(name));
	}

}
