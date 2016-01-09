package widgets;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MainWindow extends BasicWindow {
	
	
	public MazeDisplayer MazeWidget;
	public HashMap<String,Listener> listeners;
	KeyListener keylis;

	List l;
	
	
	public MainWindow(String title, int width, int height ,HashMap<String,Listener> lis,KeyListener keylis) {
		super(title, width, height);
		listeners=lis;
		this.keylis=keylis;
	}

	

	@Override
	void initWidgets() {
		/*
		//////////////////////////////////////////// INIT THE MAZE WIDGET AND THE GAME CHARCTER
		MazeWidget=new Maze2D(shell, SWT.BORDER);		
		//MazeWidget=new Maze3D(shell, SWT.BORDER);
		MazeWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
		MazeWidget.addKeyListener(keylis);
		MazeWidget.setVisible(true);
		MazeWidget.setCharacter(new GameCharacter(MazeWidget, SWT.None, 0, 2, 1, 1));
		////////////////////////////////////////////
		*/
		
		shell.setLayout(new GridLayout(2,false));
		//***************************************************************** MAIN MENU BAR
        Menu menuBar = new Menu(shell, SWT.BAR);
      //***************************************************************** File Cascade
        MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE); // file is a  top tier menu item and menu bar is its parent
        cascadeFileMenu.setText("&File");
      //*****************************************************************// turn the file menu to a drop down menu
        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeFileMenu.setMenu(fileMenu);
      //*****************************************************************// 
        MenuItem xmlItem = new MenuItem(fileMenu, SWT.PUSH);
        xmlItem.setText("Open properties");
      //*****************************************************************// 
        MenuItem loadItem = new MenuItem(fileMenu, SWT.PUSH);
        loadItem.setText("Load");
        loadItem.addListener(SWT.Selection ,listeners.get("loadmazefile"));
      //*****************************************************************
        MenuItem saveItem = new MenuItem(fileMenu, SWT.PUSH);
        saveItem.setText("Save");
      //*****************************************************************  
        MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
        exitItem.setText("&Exit");
      //*****************************************************************//
        
        
      //*****************************************************************// Options Cascade
        MenuItem cascadeOptionsMenu = new MenuItem(menuBar, SWT.CASCADE);  
        cascadeOptionsMenu.setText("Options");
        Menu OptionsMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeOptionsMenu.setMenu(OptionsMenu);
      //*****************************************************************
        MenuItem generateItem = new MenuItem(OptionsMenu, SWT.PUSH);
        generateItem.setText("Generate a new maze");
      //*****************************************************************
        MenuItem solutionItem = new MenuItem(OptionsMenu, SWT.PUSH);
        solutionItem.setText("Solve the maze for me!");
      //*****************************************************************
        MenuItem fileSizeItem = new MenuItem(OptionsMenu, SWT.PUSH);
        fileSizeItem.setText("Maze file size details");
      //*****************************************************************
        MenuItem mazeSizeItem = new MenuItem(OptionsMenu, SWT.PUSH);
        mazeSizeItem.setText("Maze memory size details");
      //*****************************************************************
        
        
      //*****************************************************************
        MenuItem cascadeHelpMenu = new MenuItem(menuBar, SWT.CASCADE); // Help Cascade
        cascadeHelpMenu.setText("Help");
        Menu HelpMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeHelpMenu.setMenu(HelpMenu);
      //*****************************************************************
        MenuItem aboutItem = new MenuItem(HelpMenu, SWT.PUSH);
        aboutItem.setText("About");
        aboutItem.addListener(SWT.Selection ,listeners.get("about"));
      //*****************************************************************
        
      //*****************************************************************//Start Button
        Button startButton=new Button(shell, SWT.PUSH);
		startButton.setText("Start");
		startButton.setLayoutData(new GridData(SWT.FILL, SWT.None, false, false, 1, 1));
		startButton.addListener(SWT.Selection,listeners.get("openmazewin"));
      //*****************************************************************
		
      //*****************************************************************
		l = new List(shell, SWT.MULTI | SWT.BORDER  );
	    l.setBounds(50, 50, 75, 75);
      //*****************************************************************

        shell.setMenuBar(menuBar);
        
	}
	
	public void ListAdd(String s){
		l.add(s);
	}
	

}
