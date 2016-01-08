package view;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import widgets.BasicWindow;
import widgets.MainWindow;
import widgets.MazeDisplayer;

public class MyGuiView extends CommonView {
	
		
	MainWindow mainGuiWindow; // the main menu widget
	HashMap<String,Listener> listeners;
	KeyListener keylis;
	Timer timer;
	TimerTask task;
	HashMap<String,Object> notifications;
	
	
	
	
	


	public MyGuiView() {
		super();
		listeners= new HashMap<String,Listener>();
		notifications=new HashMap<String,Object>();
		initListeners();
		mainGuiWindow= new MainWindow("Maze Game", 800, 500,listeners); ///////////////////// init the outside widget
		
	}

	@Override
	public void start() {
		mainGuiWindow.run();

	}

	public void initListeners() {
		
		//**************************************{          KEY LISTENER              }***********************************
		this.keylis=new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.keyCode==SWT.ARROW_UP){}
				else if(e.keyCode==SWT.ARROW_UP){}
				else if(e.keyCode==SWT.ARROW_UP){}
				else if(e.keyCode==SWT.ARROW_UP){}
				
				
				
				
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		//***************************************************************************************************************
		listeners.put("about",new Listener() 
		{
			 public void handleEvent(Event event) 
			 {
				 MessageBox messageBox = new MessageBox(mainGuiWindow.getShell(), SWT.ICON_INFORMATION);
				 messageBox.setMessage("Made By Lior Shachar 2016, all rights reserved.");
				 messageBox.setText("About my java algorithmic project");
				 messageBox.open();
				 
				 

			 }
		}); 
		//***************************************************************************************************************
		listeners.put("loadmazefile",new Listener() 
		{
			 public void handleEvent(Event event) 
			 {
				 
				 FileDialog fd = new FileDialog(mainGuiWindow.getShell(), SWT.OPEN);
			        fd.setText("Please choose a file to load from");
			        fd.setFilterPath("C:/");
			        String[] filterExt = { "*.maz", "*.*" };
			        fd.setFilterExtensions(filterExt);
			        String selected = fd.open();
			        scno("loadfrom",selected);

			 }
		}); 
		

	}

	
	
	
	
	
	@Override
	public void list(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printMsg(String s) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTodo() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	///////////////////////////////////////////////////////////////////TEST
	public void displayLoadedMaze(int[][] arr) {
		mainGuiWindow.MazeWidget.setMazeData(arr);
		mainGuiWindow.MazeWidget.redraw();
	}
///////////////////////////////////////////////////////////////////TEST
	
	
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
