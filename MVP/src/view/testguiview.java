/*package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import algorithms.search.State;

import widgets.GameCharacter;
import widgets.MainWindow;
import widgets.Maze2D;
import widgets.Maze3D;
import widgets.MazeDisplayer;

public class testguiview extends CommonView {

	MainWindow mainGuiWindow; // the main menu widget
	MazeDisplayer mazeWin; // the maze widget
	HashMap<String, Listener> listeners;
	KeyListener keylis;
	Timer timer;
	TimerTask task;
	Position current;
	HashMap<String, Object> notifications;
	public String temp; // a temporary string to pass arguements between
						// temporary events,listeners etc

	public testguiview() {
		super();
		listeners = new HashMap<String, Listener>();
		notifications = new HashMap<String, Object>();
		initListeners();
		mainGuiWindow = new MainWindow("Maze Game", 250, 250, listeners, keylis); ///////////////////// init
																					///////////////////// the
																					///////////////////// outside
																					///////////////////// widget

	}

	@Override
	public void start() {
		mainGuiWindow.run();

	}

	public void initListeners() {

		// **************************************{ KEY LISTENER
		// }***********************************
		this.keylis = new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_UP) {
					mazeWin.moveUp();
				} else if (e.keyCode == SWT.ARROW_DOWN) {
					mazeWin.moveDown();
				} else if (e.keyCode == SWT.ARROW_LEFT) {
					mazeWin.moveLeft();
				} else if (e.keyCode == SWT.ARROW_RIGHT) {
					mazeWin.moveRight();
				} else if (e.keyCode == SWT.PAGE_UP) {
					Object objs[] = { new Position(mazeWin.getCurLvl(), mazeWin.getCharacterPositionX(),
							mazeWin.getCharacterPositionY()), mazeWin.getMazeName() };
					scno("RequestUp", objs);
				} else if (e.keyCode == SWT.PAGE_DOWN) {
					Object objs[] = { new Position(mazeWin.getCurLvl(), mazeWin.getCharacterPositionX(),
							mazeWin.getCharacterPositionY()), mazeWin.getMazeName() };
					scno("RequestDown", objs);

				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		};

		// ***************************************************************************************************************
		listeners.put("about", new Listener() {
			public void handleEvent(Event event) {
				MessageBox messageBox = new MessageBox(mainGuiWindow.getShell(), SWT.ICON_INFORMATION);
				messageBox.setMessage("Made By Lior Shachar 2016, all rights reserved.");
				messageBox.setText("About my java algorithmic project");
				messageBox.open();

			}
		});
		// ***************************************************************************************************************
		listeners.put("loadmazefile", new Listener() {
			public void handleEvent(Event event) {

				FileDialog fd = new FileDialog(mainGuiWindow.getShell(), SWT.OPEN);
				fd.setText("Please choose a file to load from");
				fd.setFilterPath("C:/");
				String[] filterExt = { "*.maz", "*.*" };
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();

				Shell dialog = new Shell(mainGuiWindow.getShell(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);

				dialog.setLayout(new GridLayout(3, false));
				dialog.setSize(400, 100);

				Label messageLabel = new Label(dialog, SWT.BORDER);
				messageLabel.setText("Please Choose a name for the loaded maze");
				messageLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 2));
				Text txt = new Text(dialog, SWT.CENTER | SWT.BORDER);
				Button okButton = new Button(dialog, SWT.PUSH);
				okButton.setText("Ok");
				okButton.setLayoutData(new GridData(SWT.None, SWT.None, false, false, 1, 1));

				okButton.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						temp = txt.getText();
						if (temp.matches("([A-Za-z0-9])\\w+")) {
							String args[] = { selected, temp };
							scno("loadfrom", args);
							dialog.close();
						} else
							showError("Invalid name");

					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}
				});
				if (selected != null)
					dialog.open();

			}
		});

		// ***************************************************************************************************************

		listeners.put("mazewindowsthread", new Listener() {
			public void handleEvent(Event event) {
				
				 * new Thread(new Runnable() {
				 * 
				 * @Override public void run() { ChildWindow another= new
				 * ChildWindow("Maze Game", 800, 500,listeners,keylis);
				 * another.run();
				 * 
				 * } }).start();
				 
			}
		});
		// ***************************************************************************************************************

		listeners.put("mazewindow", new Listener() {
			public void handleEvent(Event event) {
				if (mainGuiWindow.l.getSelection().length > 0) {
					Shell mazeshell = new Shell(mainGuiWindow.getDisplay());
					mazeshell.setLayout(new GridLayout(5, false));
					mazeWin = new Maze2D(mazeshell, SWT.BORDER);
					mazeWin.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));
					mazeWin.setCharacter(new GameCharacter(mazeWin, SWT.None, 0, 2, 1, 1));
					mazeWin.addKeyListener(keylis);
					mazeshell.pack();
					mazeshell.open();
					String selected = mainGuiWindow.l.getSelection()[0];
					scno("initMazeWidgetRequest", selected);
				} else {
					scno("error", "no maze selected");
					mainGuiWindow.solveButton.setEnabled(false);
				}
			}
		});
		// ***************************************************************************************************************
		listeners.put("generateButton", new Listener() {
			public void handleEvent(Event event) {
				if (mainGuiWindow.heighttxt.getText().matches("[1-9]\\d*")
						&& mainGuiWindow.widthtxt.getText().matches("[1-9]\\d*")
						&& mainGuiWindow.levelstxt.getText().matches("[1-9]\\d*")
						&& mainGuiWindow.nametxt.getText() != null) {
					String param[] = { mainGuiWindow.nametxt.getText(), mainGuiWindow.levelstxt.getText(),
							mainGuiWindow.heighttxt.getText(), mainGuiWindow.widthtxt.getText() };
					scno("generateDetails", param);
				} else
					showError("Invalid Values");

			}
		});

		// ***************************************************************************************************************
		listeners.put("MazeSize", new Listener() {
			public void handleEvent(Event event) {
				if (mainGuiWindow.l.getSelection().length > 0) {
					String selected = mainGuiWindow.l.getSelection()[0];
					scno("MazeSizeRequest", selected);
				} else
					scno("error", "no maze selected");
			}
		});
		// ***************************************************************************************************************
		listeners.put("FileSize", new Listener() {
			public void handleEvent(Event event) {
				if (mainGuiWindow.l.getSelection().length > 0) {
					String selected = mainGuiWindow.l.getSelection()[0];
					scno("FileSizeRequest", selected);
				} else
					scno("error", "no maze selected");
			}
		});
		// ***************************************************************************************************************
		listeners.put("SaveToFile", new Listener() {
			public void handleEvent(Event event) {
				FileDialog savedialog = new FileDialog(mainGuiWindow.getShell(), SWT.SAVE);
				savedialog.setFilterNames(new String[] { "Maze Files", "All Files (*.*)" });
				savedialog.setFilterExtensions(new String[] { "*.maz", "*.*" }); // Windows
				// wild
				// cards
				savedialog.setFilterPath("c:\\"); // Windows path
				savedialog.setFileName("newMaze.maz");
				if (mainGuiWindow.l.getSelection().length > 0) {
					String filepath = savedialog.open();
					if (filepath != null) {
						String saveparams[] = { mainGuiWindow.l.getSelection()[0], filepath };
						scno("PathToSaveMaze", saveparams);
					}
				} else
					scno("error", "no maze selected");
			}
		});
		// ***************************************************************************************************************
		listeners.put("solveButton", new Listener() {
			public void handleEvent(Event event) {
				int x = mazeWin.getCharacterPositionY();
				int z = mazeWin.getCharacterPositionX();
				int y = mazeWin.getCurLvl();
				String solvename = mazeWin.getMazeName();
				Object solvedetails[] = { new Position(y, x, z), solvename };
				scno("solveRequest", solvedetails);
			}
		});
		// ***************************************************************************************************************
	}

	@Override
	public void showList(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMsg(String s) {
		MessageBox messageBox = new MessageBox(mainGuiWindow.getShell(), SWT.ICON_WORKING);
		messageBox.setMessage(s);
		messageBox.setText("Message");
		messageBox.open();

	}


	/////////////////////////////////////////////////////////////////// TEST
	public void displayLoadedMaze(String s) {
		mainGuiWindow.ListAdd(s);

	}
	/////////////////////////////////////////////////////////////////// TEST

	@Override
	public void showMaze(byte[] arr) {

	}

	@Override
	public void showCross(byte[] arr, String by, int i) {
		Maze3d maze = new Maze3d(arr);
		if (this.mazeWin != null) {
			mazeWin.setCurLvl(i);
			switch (by) {
			case "x":
				mazeWin.setMazeData(maze.getCrossSectionByX(i));
				break;
			case "X":
				mazeWin.setMazeData(maze.getCrossSectionByX(i));
				break;
			case "y":
				mazeWin.setMazeData(maze.getCrossSectionByY(i));
				break;
			case "Y":
				mazeWin.setMazeData(maze.getCrossSectionByY(i));
				break;
			case "z":
				mazeWin.setMazeData(maze.getCrossSectionByZ(i));
				break;
			case "Z":
				mazeWin.setMazeData(maze.getCrossSectionByZ(i));
				break;
			}

		}

	}

	@Override
	public void showSolution(Solution<Position> s) {
		mainGuiWindow.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				MyViewCLI oview = new MyViewCLI();
				oview.showSolution(s);
				ArrayList<State<Position>> states = s.getSolution(); // an
																		// arraylist
																		// of
																		// states
																		// to
																		// get
																		// from
																		// our
																		// solution

				System.out.println(s.getSolution().size());

				mazeWin.setFocus();
				timer = new Timer();
				
				

				Iterator<State<Position>> mazeIterator = states.iterator();
				timer.scheduleAtFixedRate(new TimerTask() {
					// for every state in the
					// solution extract the position
					// from the
					// states array and compare it
					// with a relative position,
					// that way the view knows how
					// to present the position(same
					// function as a key pressed)
					@Override
					public void run() {
						if(mazeIterator.hasNext()&&!mazeWin.getDisplay().isDisposed()){ 
							current = new Position(mazeWin.getCurLvl(), mazeWin.getCharacterPositionY(),
									mazeWin.getCharacterPositionX());
							mazeWin.getDisplay().syncExec(new Runnable() {

								@Override
								public void run() {
									State state=mazeIterator.next();
									if (current.up().equals(state.getState())) {// if
										// the
										// next
										// step
										// in
										// the solution is to
										// lvl up
										Object objs[] = { new Position(mazeWin.getCurLvl(), mazeWin.getCharacterPositionY(),
												mazeWin.getCharacterPositionX()), mazeWin.getMazeName() };
										scno("RequestUp", objs);
									} else if (current.down().equals(state.getState()))// if
									// the
									// next
									// step
									// is
									// to
									// level
									// down
									{
										Object objs[] = { new Position(mazeWin.getCurLvl(), mazeWin.getCharacterPositionY(),
												mazeWin.getCharacterPositionX()), mazeWin.getMazeName() };
										scno("RequestDown", objs);
									} else if (current.forward().equals(state.getState())) {
										mazeWin.moveRight();
									} // if the next step is to move right
									else if (current.backward().equals(state.getState())) {
										mazeWin.moveLeft();
									} // if the next step is to move left
									else if (current.right().equals(state.getState())) {
										mazeWin.moveDown();
									} // if the next step is to move down
									else if (current.left().equals(state.getState())) {
										mazeWin.moveUp();
									} // if the next step is to move up

								}
							});
							
							
					}
						else
						{
							mainGuiWindow.solveButton.setEnabled(false);
							timer.cancel();
							}
					}
						
				}, 0, 200);

			}
		});

	}

	
	
	
	
	
	@Override
	public void showExit() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getData(String string) {
		return notifications.get(string);
	}

	void scno(String type, Object data) {
		notifications.put(type, data);
		setChanged();
		notifyObservers(type);

	}

	@Override
	public void showError(String s) {
		MessageBox messageBox = new MessageBox(mainGuiWindow.getShell(), SWT.ICON_ERROR);
		messageBox.setMessage(s);
		messageBox.setText("Error!");
		messageBox.open();

	}

	

}
*/