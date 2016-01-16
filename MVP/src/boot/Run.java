package boot;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.swt.SWT;

import com.sun.xml.internal.ws.api.server.SDDocumentFilter;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Preferences;
import controller.Presenter;
import io.MyCompressorOutputStream;
import model.Model;
import model.MyModel;
import model.XmlHandler;
import view.GuiWindowView;

import view.View;
import widgets.MainWindow;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;


public class Run {
	
	
	

	
	
	

	
	

	public static void main(String[] args) {
		
			
		 
		
		
		
		System.out.println("[Lior Shachar 304848427]");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		
		
		
		
		Presenter p = new Presenter(); 
		
		
	//
		
		
		MyModel m = new MyModel();
		   p.setModel(m);
		   m.addObserver(p);
		   m.loadCachedSolutions();
		  
		  
		  
			
			
			
		   
		   
		  

	}
	}
	
	


	



