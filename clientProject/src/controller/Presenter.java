package controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.ClientModel;
import model.Model;
import model.XMLproperties;
import singletonexplicitpack.Properties;
import view.GuiWindowView;
import view.MyViewCLI;
import view.View;
import view.commonGuiView;

/**
 *
 * 
 * 
 * 
 * 
 * <h1>Presenter</h1> this class represent my controller part of the project,
 * acts as an agent between the view and the model
 * it is suitable for a Maze3d problem.
 * 
 * 
 * <p>
 * <b>Notes:</b>
 *
 * @author Lior Shachar
 * @version 1.0
 * @since 2015-12-17
 */

public class Presenter implements Observer {

	private Model m;
	private View v;
	HashMap<String, Command> commandCreator; // our command map (usually for
												// cli)
/**
 * 
 * */
	public Presenter(Model m, View v) {
		super();
		this.m = m;
		this.v = v;
		commandCreator = new HashMap<String, Command>();
		fillMap(commandCreator);
	}
	/***/
	public Presenter() {
		super();
		commandCreator = new HashMap<String, Command>();
		fillMap(commandCreator);
	}

	/**
	 * this method puts a regEx string as a key value for our generated Commands
	 * inside our command map. that way, the CLI is able to distinct whether the
	 * input from the user matches the correct pattern for the right command.
	 * since some of the commands have similar words or multiple different
	 * parameters regEx patterns make sure we get the right syntax.
	 * <p>
	 * <b>Notes:</b>
	 * 
	 * a very helpful website which helps build the right regEx syntax
	 * {@link regexr.com http://regexr.com/}
	 * 
	 * @param
	 * 
	 */
	public void fillMap(HashMap<String, Command> map) {

		// dir <directory/path>
		map.put("dir [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleDir(args[1]);

			}
		});

		// generate 3d maze <name> <x size (rows)> <y size (levels)> <z
		// size(columns)>
		map.put("generate 3d maze [^\n\r]+ [0-9]+ [0-9]+ [0-9]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleGenerate(args[3], Integer.parseInt(args[5]), Integer.parseInt(args[4]),
						Integer.parseInt(args[6]));

			}
		});

		// display <name>
		map.put("display (?!cross section by)(?!solution)[^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				v.showMaze(((Maze3d) m.getMazeByName(args[1])).toByteArray());

			}
		});

		// display cross section by {X,Y,Z} <index> for <name>
		map.put("display cross section by [XYZxyz] [0-9]+ for [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				v.showCross(((Maze3d) m.getMazeByName(args[7])).toByteArray(), args[4], Integer.parseInt(args[5]));

			}
		});
		// save maze <name> <file name>
		map.put("save maze [^\n\r]+ [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleSaveMaze(((Maze3d) m.getMazeByName(args[2])).toByteArray(), args[3]);

			}
		});

		// load maze <file name> <name>
		map.put("load maze [^\n\r]+ [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleLoadMaze(args[2], args[3]);

			}
		});
		// maze size <name>
		map.put("maze size [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				v.showMsg("Maze size of " + args[2] + " is: " + ((Maze3d) m.getMazeByName(args[2])).toByteArray().length
						+ " Bytes");

			}
		});
		// file size <name>
		map.put("file size [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleFileSize(args[2]);

			}
		});
		// solve <name> <algorithm>
		map.put("solve [^\n\r]+ [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleSolveMaze(args[1], args[2]);

			}
		});

		// display solution <name>
		map.put("display solution [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {

				if (m.getSolutionFor(args[2]) != null) {
					v.showSolution((Solution<Position>) m.getSolutionFor(args[2]));
				} else {
					v.showError("no solution found for this maze");
				}

			}
		});

		map.put("exit", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.close();
				v.close();

			}
		});

		///////////////////////////////////////////////////// commands for
		///////////////////////////////////////////////////// testing

		map.put("[^\n\r]+ equals [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {

				if (((Maze3d) m.getMazeByName(args[0])).equals((Maze3d) (m.getMazeByName(args[2]))))
					v.showMsg("mazes are equal");
				else
					v.showMsg("mazes are NOT equal");
			}
		});

		//////////////////////////////////////////////// adds a test thread to
		//////////////////////////////////////////////// run in background and
		//////////////////////////////////////////////// print until stopped
		map.put("runtestthread", new Command() {

			@Override
			public void doCommand(String[] args) {

			}
		});

		/////////////////////////////////////////////////////////// Get Back to
		/////////////////////////////////////////////////////////// GUI view
		map.put("GUI", new Command() {

			@Override
			public void doCommand(String[] args) {
				v.close();
				switchToGUI();

			}
		});
		///////////////////////////////////////////////////// list the commands
		///////////////////////////////////////////////////// available to the
		///////////////////////////////////////////////////// user
		map.put("help", new Command() {

			@Override
			public void doCommand(String[] args) {
				v.showMsg(
						"*************************************************************************************************");
				v.showMsg("dir <directory/path>");
				v.showMsg("generate 3d maze <name of the maze> <x size (rows)> <y size (levels)> <z size(columns)>");
				v.showMsg("display <name of the maze>");
				v.showMsg("display cross section by <X/Y/Z> <index> for <name of the maze>");
				v.showMsg("save maze <name of the maze> <file name / path to file name>");
				v.showMsg("load maze <file name / path to file name> <name of the maze>");
				v.showMsg("maze size <name of the maze>");
				v.showMsg("file size <name of the maze>");
				v.showMsg("solve <name of the maze> <BFS/Astar>");
				v.showMsg("display solution <name of the maze>");
				v.showMsg(
						"*************************************************************************************************");

			}
		});
	}
	/***/
	public Model getModel() {
		return m;
	}
	/***/
	public void setModel(Model m) {
		this.m = m;
	}
	/***/
	public View getView() {
		return v;
	}
	/***/
	public void setView(View v) {
		this.v = v;
	}
	/***/
	public HashMap<String, Command> getCommandCreator() {
		return commandCreator;
	}
	/***/
	public void setCommandCreator(HashMap<String, Command> commandCreator) {

		this.commandCreator = commandCreator;
	}

	
	
	
	
	
	/**
	 * the update method check whether the presenter got a command from the view or from the model
	 * since the CLI commands have regex keys we are able to generate commands based on the right regex pattern
	 *
	 * **/
	
	@Override
	public void update(Observable arg0, Object arg1) {
		String note = (String) arg1;
		///////////////////////////////////////////////////////// NOTIFICATIONS
		///////////////////////////////////////////////////////// FROM THE
		///////////////////////////////////////////////////////// VIEW//////////////////////////////////////////////////////////////////
		if (arg0 == v) {
			switch (note) {
			case "CLI":/// if the command is from a CLI take the input from
						/// CLIview, see if it matches any regex keys in the
						/// command map, if it does, split it and send the
						/// arguments to the right command
				String input = (String) v.getData("");
				for (String s : commandCreator.keySet()) {
					if (input.matches(s)) {
						String[] args = input.split(" ");
						commandCreator.get(s).doCommand(args);
					}

				}
				break;
				
			case "connectBtn":
				if(!((ClientModel)m).isConnected()){
				((ClientModel)m).connectToServer();
				}
				else{
					v.showMsg("already connected");
				}
				break;
				
				
				
			case "GuiDisposed":
				if (((GuiWindowView) v).isCanExitAll())
					m.close();
				break;

			case "loadSettings":
				 String xmlloadpath = (String) v.getData(note);
				
				m.handleCustomProperties(xmlloadpath);
				break;

			case "saveSettings":
				
				Object[] savedetails = (Object[]) v.getData(note);
				m.handleSaveProperties((Properties) savedetails[0], (String) savedetails[1]);

				break;

			case "loadfrom":
				String param[] = (String[]) v.getData(note);
				m.handleLoadMaze(param[0], param[1]);
				break;
			case "generateDetails":
				String gendetails[] = (String[]) v.getData(note);
				m.handleGenerate(gendetails[0], Integer.parseInt(gendetails[1]), Integer.parseInt(gendetails[2]),
						Integer.parseInt(gendetails[3]));
				break;
			case "MazeSizeRequest":
				String mname = (String) v.getData(note);
				m.handleMazeSize(mname);
				break;
			case "FileSizeRequest":
				String fmname = (String) v.getData(note);
				m.handleFileSize(fmname);
				break;

			case "initMazeWidgetRequest":
				
				String mazetoinit = (String) v.getData(note);
				Maze3d maze = (Maze3d) m.getMazeByName(mazetoinit);
				((GuiWindowView) v).initMazeWidget(maze, mazetoinit);
				break;

			case "error":
				v.showError((String) v.getData(note));
				break;
			case "msg":
				v.showMsg((String) v.getData(note));
				break;

			case "PathToSaveMaze":
				String mazesavedetails[] = (String[]) v.getData(note);

				Maze3d lol = (Maze3d) m.getMazeByName(mazesavedetails[0]); // gets
																			// the
																			// maze
																			// from
																			// the
																			// model
				byte b[];
				b = lol.toByteArray(); // turn the maze into a byte array

				m.handleSaveMaze(b, mazesavedetails[1]);
				break;

			case "solveRequest":
				String target = (String) v.getData(note);
				try {
					m.handleSolveMaze(target,XMLproperties.getMyPropertiesInstance().getSearchingAlgorithm());
				} catch (FileNotFoundException e1) {
					v.showError("problem loading the searching algorithm from the properties file");
				}
				
				break;
				
			case "ShowMe":
				if(((ClientModel)m).isConnected())
			{
			Object solvedetails[] = (Object[]) v.getData(note);
			m.handleUpdatePosition(new Position((Position) solvedetails[0]), (String) solvedetails[1]);
			try {
				m.handleSolveMaze((String) solvedetails[1],
						XMLproperties.getMyPropertiesInstance().getSearchingAlgorithm());
			} catch (FileNotFoundException e) {
				v.showError("problem loading the searching algorithm from the properties file");
			}
			while(!m.hasSolution((String) solvedetails[1]));
			v.showSolution( (Solution<Position>) m.getSolutionFor( (String) solvedetails[1]) );
			}
				else
					v.showError("not connected to the server!");
			break;
				

			case "updateStart":
				Object updatedetails[] = (Object[]) v.getData(note);
				m.handleUpdatePosition(new Position((Position) updatedetails[0]), (String) updatedetails[1]);
				break;


			}
			/////////////////////////////////////////////////// NOTIFICATIONS
			/////////////////////////////////////////////////// FROM THE
			/////////////////////////////////////////////////// MODEL///////////////////////////////////////////////////////////
		} else if (arg0 == m)

		{
			switch (note) {
			
			
			case "updateListStatus":
				
				String []elements=(String[])m.getData(note);
				
				
				 v.showUpdatedList(elements);
				break;
			
			
			
			case "modelReady":
				updateView();
				break;

			case "loadedCustomSettings":
				updateView();
				break;
				
			case "loaded":
				v.showMazeIsReady((String) m.getData(note));
				break;
			case "error":
				if (v != null) {
					v.showError((String) m.getData(note));
				} else {
					System.out.println((String) m.getData(note));
				}
				break;
			case "msg":
				if (v != null) {
					v.showMsg((String) m.getData(note));
				} else {
					System.out.println((String) m.getData(note));
				}
				break;

			case "solutionReady":
				String mazeSolved = (String) m.getData(note);
				v.showSolved(mazeSolved); 
				break;

			case "solutionExist":
				String mazeAlreadySolved = (String) m.getData(note);
				v.showMsg("Solution found in cached memory for"+mazeAlreadySolved);
				
				

				break;
			}

		}

	}

	/**
	 * 
	 * whenever something happend to the view this method can check its own
	 * status and get a new view according to the properties
	 */
	public void updateView() {
		
		
		
		try {
			String choice = XMLproperties.getMyPropertiesInstance().getUserInterface();
			if (choice.matches("[Cc][Ll][Ii]")) {
				switchToCLI();
			} else
				switchToGUI();

		} catch (FileNotFoundException e) {
			System.out.println("User Interface choice isnt reachable, properties file failed to load");
			e.printStackTrace();
		}
	}

	public void switchToCLI() {
		if (v != null)
			v.close();
		setView(new MyViewCLI());
		((Observable) v).addObserver(this);
		((MyViewCLI) v).getCli().addObserver((Observer) v);
		v.start();
	}

	public void switchToGUI() {
		if (v != null){
			v.close();
		}
		
		GuiWindowView newv =new GuiWindowView("My View", 800, 500);
		
		newv.addObserver(this);
		v=newv;
		newv.start();
		
	}

}
