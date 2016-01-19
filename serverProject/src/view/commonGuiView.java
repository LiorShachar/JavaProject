package view;

import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public abstract class commonGuiView extends BasicWindow implements Runnable{
	
	
 	public commonGuiView(String title, int width,int height) {
 		super(title, width, height);
 		
	}
 	
 	abstract void initWidgets();
 	
 	public Shell getShell(){
 		return this.shell;
 	}
 	public Display getDisplay(){
 		return this.display;
 	}
 	
 	
 	

}
