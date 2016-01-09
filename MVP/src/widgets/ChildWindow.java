package widgets;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Listener;

public abstract class ChildWindow extends BasicWindow {
	
	




	public MazeDisplayer MazeWidget;
	public HashMap<String,Listener> listeners;
	KeyListener keylis;
	

	public ChildWindow(String title, int width, int height ,HashMap<String,Listener> listeners,KeyListener keylis ) {
		super(title, width, height);
		this.listeners=listeners;
		this.keylis=keylis;
	}


	@Override
	void initWidgets() {
		shell.setLayout(new GridLayout(2,false));
		//////////////////////////////////////////////////////////////////////////////////////
		MazeWidget=new Maze2D(shell, SWT.BORDER);		
		//MazeWidget=new Maze3D(shell, SWT.BORDER);
		MazeWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,true,1,2));
		//MazeWidget.addKeyListener(keylis);
		MazeWidget.setVisible(true);
		MazeWidget.setCharacter(new GameCharacter(MazeWidget, SWT.None, 0, 2, 1, 1));
/////////////////////////////////////////////////////////////////////////////////////////////////
	}

}
