package controller;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import model.Model;
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
 * <h1>MyController</h1> this class represent my controller part of the project,
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

	public Presenter(Model m, View v) {
		super();
		this.m = m;
		this.v = v;
		commandCreator = new HashMap<String, Command>();
		fillMap(commandCreator);
	}

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
				v.showList(args[1]);
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
				v.showMaze(m.getMazes().get(args[1]).toByteArray());

			}
		});

		// display cross section by {X,Y,Z} <index> for <name>
		map.put("display cross section by [XYZxyz] [0-9]+ for [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				v.showCross(m.getMazes().get(args[7]).toByteArray(), args[4], Integer.parseInt(args[5]));

			}
		});
		// save maze <name> <file name>
		map.put("save maze [^\n\r]+ [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleSaveMaze(m.getMazes().get(args[2]).toByteArray(), args[3]);

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
				v.showMsg("Maze size of " + args[2] + " is: " + m.getMazes().get(args[2]).toByteArray().length
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

				if (m.getSolutions().containsKey(m.getMazeByName(args[2]))) {
					v.showSolution(m.getSolutions().get(m.getMazeByName(args[2])));
				} else {
					toView("no solution found for this maze");
				}

			}
		});

		map.put("exit", new Command() {

			@Override
			public void doCommand(String[] args) {
				m.handleExit();
				v.showExit();

			}
		});

		///////////////////////////////////////////////////// commands for
		///////////////////////////////////////////////////// testing

		map.put("[^\n\r]+ equals [^\n\r]+", new Command() {

			@Override
			public void doCommand(String[] args) {

				System.out.println(m.getMazes().get(args[0]).equals(m.getMazes().get(args[2])));

			}
		});

		//////////////////////////////////////////////// adds a test thread to
		//////////////////////////////////////////////// run in background and
		//////////////////////////////////////////////// print until stopped
		map.put("runtestthread", new Command() {

			@Override
			public void doCommand(String[] args) {

				m.testThread();

			}
		});

		/////////////////////////////////////////////////////////// Get Back to
		/////////////////////////////////////////////////////////// GUI view
		map.put("GUI", new Command() {

			@Override
			public void doCommand(String[] args) {
				((MyViewCLI) v).getCli().setFlag(false);
				switchToGUI();

			}
		});
		///////////////////////////////////////////////////// list the commands
		///////////////////////////////////////////////////// available to the
		///////////////////////////////////////////////////// user
		map.put("help", new Command() {

			@Override
			public void doCommand(String[] args) {
				toView("*************************************************************************************************");
				toView("dir <directory/path>");
				toView("generate 3d maze <name of the maze> <x size (rows)> <y size (levels)> <z size(columns)>");
				toView("display <name of the maze>");
				toView("display cross section by <X/Y/Z> <index> for <name of the maze>");
				toView("save maze <name of the maze> <file name / path to file name>");
				toView("load maze <file name / path to file name> <name of the maze>");
				toView("maze size <name of the maze>");
				toView("file size <name of the maze>");
				toView("solve <name of the maze> <BFS/Astar>");
				toView("display solution <name of the maze>");
				toView("*************************************************************************************************");

			}
		});
	}

	public Model getModel() {
		return m;
	}

	public void setModel(Model m) {
		this.m = m;
	}

	public View getView() {
		return v;
	}

	public void setView(View v) {
		this.v = v;
	}

	public HashMap<String, Command> getCommandCreator() {
		return commandCreator;
	}

	public void setCommandCreator(HashMap<String, Command> commandCreator) {

		this.commandCreator = commandCreator;
	}

	public void toView(String s) {
		v.showMsg(s);

	}

	public void loadSettings(String xmlpath) {

		m.handleLoadSettings(xmlpath);

		//////////////////////////////// Cases of changing view in run time.
		if (v != null) {///// in case theres a current view
			// if gui is on but user wants a cli dispose the gui and start the
			// cli and the opposite
			System.out.println(v.getViewType());
			System.out.println(Properties.getUi());

			if (Properties.getUi().matches("[Cc][Ll][Ii]")
					&& !v.getViewType().matches("[Mm][Yy][Vv][Ii][Ee][Ww][Cc][Ll][Ii]")) {

				((commonGuiView) v).getDisplay().dispose();

				switchToCLI();
			} else if (Properties.getUi().matches("[Gg][Uu][Ii]")
					&& !v.getViewType().matches("[Gg][Uu][Ii][Ww][Ii][Nn][Dd][Oo][Ww][Vv][Ii][Ee][Ww]")) {
				switchToGUI();
			}

		} else if (Properties.getUi().matches("[Cc][Ll][Ii]")) {
			switchToCLI();
		} else if (Properties.getUi().matches("[Gg][Uu][Ii]")) {
			switchToGUI();
		}

	}

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
						/// command map, if it does split it and send the
						/// arguments to the right command
				String input = (String) v.getData("");
				for (String s : commandCreator.keySet()) {
					if (input.matches(s)) {
						String[] args = input.split(" ");
						commandCreator.get(s).doCommand(args);
					}

				}
				break;

			case "loadSettings":
				String xmlpath = (String) v.getData(note);
				m.handleLoadSettings(xmlpath);
				break;

			case "saveSettings":
				String xmlsavepath = (String) v.getData(note);
				m.handleSaveSettings(xmlsavepath);
				
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
				v.initMazeWidget(maze, mazetoinit);
				break;

			case "error":
				v.showError((String) v.getData(note));
				break;
			case "msg":
				v.showMsg((String) v.getData(note));
				break;

			case "PathToSaveMaze":
				String savedetails[] = (String[]) v.getData(note);

				Maze3d lol = (Maze3d) m.getMazeByName(savedetails[0]); // gets
																		// the
																		// maze
																		// from
																		// the
																		// model
				byte b[];
				b = lol.toByteArray(); // turn the maze into a byte array

				m.handleSaveMaze(b, savedetails[1]);
				break;

			case "solveRequest":
				Object solvedetails[] = (Object[]) v.getData(note);
				m.handleUpdatePosition(new Position((Position) solvedetails[0]), (String) solvedetails[1]);
				m.handleSolveMaze((String) solvedetails[1], Properties.getSolveAlgo());

				break;

			case "updateStart":
				Object updatedetails[] = (Object[]) v.getData(note);
				m.handleUpdatePosition(new Position((Position) updatedetails[0]), (String) updatedetails[1]);
				break;

			case "exit":
				m.serializeAndCachSolutions();
				m.handleExit();
				break;

			}
			/////////////////////////////////////////////////// NOTIFICATIONS
			/////////////////////////////////////////////////// FROM THE
			/////////////////////////////////////////////////// MODEL///////////////////////////////////////////////////////////
		} else if (arg0 == m)

		{
			switch (note) {
			
			
			case "loaded":
				v.showMazeIsReady((String) m.getData(note));
				break;
			case "error":
				v.showError((String) m.getData(note));
				break;
			case "msg":
				v.showMsg((String) m.getData(note));
				break;

			case "solutionReady":
				String mazeSolved = (String) m.getData(note);
				v.showSolution(m.getSolutionFor(mazeSolved));
				break;

			case "solutionExist":
				v.showMsg("Maze Solution found in cached memory");
				String mazeallSolved = (String) m.getData(note);
				v.showSolution(m.getSolutionFor(mazeallSolved));

				break;
			}

		}

	}

	public void switchToCLI() {
		setView(new MyViewCLI());
		((Observable) v).addObserver(this);
		((MyViewCLI) v).getCli().addObserver((Observer) v);
		getView().start();
	}

	public void switchToGUI() {
		setView(new GuiWindowView("My View", 800, 500));
		((Observable) v).addObserver(this);
		getView().start();
	}

}
