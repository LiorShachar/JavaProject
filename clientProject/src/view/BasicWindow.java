package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public abstract class BasicWindow extends CommonView implements Runnable{
	
	Display display;
	Shell shell;
	
 	public BasicWindow(String title, int width,int height) {
 		display=new Display();
 		shell  = new Shell(display);
 		shell.setSize(width,height);
 		shell.setText(title);
	}
 	
 	abstract void initWidgets();
 	
 	public Shell getShell(){
 		return this.shell;
 	}
 	public Display getDisplay(){
 		return this.display;
 	}
 	
 	
 	
	@Override
	public void run() {
		initWidgets();
		shell.open();
		// main event loop
		 while(!shell.isDisposed()){ // while window isn't closed

		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ 	// if the queue is empty
		       display.sleep(); 			// sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components
	}
	

}
