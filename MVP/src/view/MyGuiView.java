package view;

import java.util.HashMap;
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

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import widgets.BasicWindow;
import widgets.ChildWindow;
import widgets.MainWindow;
import widgets.MazeDisplayer;

public class MyGuiView extends CommonView {

	MainWindow mainGuiWindow; // the main menu widget
	HashMap<String, Listener> listeners;
	KeyListener keylis;
	Timer timer;
	TimerTask task;
	HashMap<String, Object> notifications;
	public String temp; // a temporary string to pass arguements between
						// temporary events,listeners etc

	public MyGuiView() {
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
					mainGuiWindow.MazeWidget.moveUp();
				} else if (e.keyCode == SWT.ARROW_DOWN) {
					mainGuiWindow.MazeWidget.moveDown();
				} else if (e.keyCode == SWT.ARROW_LEFT) {
					mainGuiWindow.MazeWidget.moveLeft();
				} else if (e.keyCode == SWT.ARROW_RIGHT) {
					mainGuiWindow.MazeWidget.moveRight();
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
						String args[] = { selected, temp };
						scno("loadfrom", args);
						dialog.close();

					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}
				});

				dialog.open();

			}
		});

		// ***************************************************************************************************************

		listeners.put("openmazewin", new Listener() {
			public void handleEvent(Event event) {
				/*
				 * new Thread(new Runnable() {
				 * 
				 * @Override public void run() { ChildWindow another= new
				 * ChildWindow("Maze Game", 800, 500,listeners,keylis);
				 * another.run();
				 * 
				 * } }).start();
				 */
			}
		});

		// ***************************************************************************************************************
		listeners.put("generateButton", new Listener() {
			public void handleEvent(Event event) {
				if (mainGuiWindow.heighttxt.getText().matches("[1-9]\\d+")
						&& mainGuiWindow.widthtxt.getText().matches("[1-9]\\d+")
						&& mainGuiWindow.levelstxt.getText().matches("[1-9]\\d+")
						&& mainGuiWindow.nametxt.getText() != null) {
					String param[] = { mainGuiWindow.nametxt.getText(), mainGuiWindow.levelstxt.getText(),
							mainGuiWindow.heighttxt.getText(), mainGuiWindow.levelstxt.getText() };
					scno("generateDetails", param);
				}
				else
					printMsg("Invalid Values");

			}
		});

	}

	// ***************************************************************************************************************

	@Override
	public void list(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printMsg(String s) {
		MessageBox messageBox = new MessageBox(mainGuiWindow.getShell(), SWT.ICON_WORKING);
		messageBox.setMessage(s);
		messageBox.setText("Message");
		messageBox.open();

	}

	@Override
	public String getTodo() {
		// TODO Auto-generated method stub
		return null;
	}

	/////////////////////////////////////////////////////////////////// TEST
	public void displayLoadedMaze(String s) {
		mainGuiWindow.ListAdd(s);

	}
	/////////////////////////////////////////////////////////////////// TEST

	@Override
	public void displayMaze(byte[] arr) {

	}

	@Override
	public void displayCross(byte[] arr, String by, int i) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displaySolution(Solution<Position> s) {
		// TODO Auto-generated method stub

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

}
