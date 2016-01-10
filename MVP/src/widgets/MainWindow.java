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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MainWindow extends BasicWindow {
	
	
	public MazeDisplayer MazeWidget;
	public HashMap<String,Listener> listeners;
	KeyListener keylis;

	List l;
	Text nametxt,heighttxt,widthtxt,levelstxt;
	Label genlbl,mazelistlbl,namelbl,heightlbl,widthlbl,levelslbl;
	Button playButton;
	
	
	
	
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
		
		shell.setLayout(new GridLayout(5,false));
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
        
     
        
      //*****************************************************************//"generate a maze" label
        genlbl = new Label(shell,SWT.None);
        genlbl.setText("Generate a new maze");
        genlbl.setBounds(shell.getClientArea());
        genlbl.setLayoutData(new GridData(SWT.None, SWT.None, true,false, 2, 1));
        
      //*****************************************************************//
        
        
      //*****************************************************************//"maze list" label
        mazelistlbl = new Label(shell,SWT.None);
        mazelistlbl.setText("Maze List");
        mazelistlbl.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 3, 1));
        //*****************************************************************//
        
        //*****************************************************************//"name" label
        namelbl = new Label(shell,SWT.BORDER);
        namelbl.setText("Maze Name");
        namelbl.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
        
      //*****************************************************************//
        
        //*****************************************************************//"maze name" text
       nametxt = new Text(shell, SWT.SINGLE | SWT.BORDER);
       nametxt.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
       
        //*****************************************************************//

       //*****************************************************************// Maze List
 		l = new List(shell, SWT.MULTI | SWT.BORDER  );
 	    l.setLayoutData(new GridData(SWT.None, SWT.None, false,true, 1, 4));
       //*****************************************************************
       
      //*****************************************************************//Play Button
        playButton=new Button(shell, SWT.PUSH);
		playButton.setText("Play");
		playButton.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 2, 4));
		playButton.addListener(SWT.Selection,listeners.get("openmazewin"));
      //*****************************************************************
		
		//*****************************************************************//"height" label
        heightlbl = new Label(shell,SWT.BORDER);
        heightlbl.setText("Height(in cells)");
        heightlbl.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
        
      //*****************************************************************//
        
        //*****************************************************************//"height" text
        heighttxt = new Text(shell, SWT.SINGLE | SWT.BORDER);
       heighttxt.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
       
        //*****************************************************************//
       
     //*****************************************************************//width label
       widthlbl = new Label(shell,SWT.BORDER);
       widthlbl.setText("Width(in cells)");
       widthlbl.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
       
     //*****************************************************************//
       
       //*****************************************************************//width text
       widthtxt = new Text(shell, SWT.SINGLE | SWT.BORDER);
       widthtxt.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
      
       //*****************************************************************//
	    
     //*****************************************************************//levels label
       levelslbl = new Label(shell,SWT.BORDER);
       levelslbl.setText("levels");
       levelslbl.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
       
     //*****************************************************************//
       
       //*****************************************************************//levels text
       levelstxt = new Text(shell, SWT.SINGLE | SWT.BORDER);
       levelstxt.setLayoutData(new GridData(SWT.None, SWT.None, false,false, 1, 1));
      
       //*****************************************************************//
        
       
        

        shell.setMenuBar(menuBar);
        shell.pack();
        
	}
	
	public void ListAdd(String s){
		l.add(s);
	}
	

}
