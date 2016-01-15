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
import controller.XmlHandler;
import io.MyCompressorOutputStream;
import model.Model;
import model.MyModel;

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
		
			Preferences p = new Preferences("DFS", "BFS", "manhatten", 5, true, "Gui");
			
		 XmlHandler lol = new XmlHandler();
		 //lol.SaveDataToXml("");
		 lol.LoadDataFromXml("defaultSettings.xml");
		 Preferences.print();
		
		
		
		/*System.out.println("[Lior Shachar 304848427]");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		
		
		
		
		Presenter p = new Presenter();
		//MyViewCLI v = new MyViewCLI();  
		//MazeWindow v=new MazeWindow("Maze Template", 800, 500);
		
	//	testguiview v = new testguiview();
		GuiWindowView v = new GuiWindowView("My View", 800,500);
		MyModel m = new MyModel();
		   p.setModel(m);
		   p.setView(v);
		//   v.getCli().addObserver(v);
		  v.addObserver(p);
		  m.addObserver(p);
		  MyMaze3dGenerator gen = new MyMaze3dGenerator();
		  Maze3d maze=gen.generate(10, 200, 200);
		  MyCompressorOutputStream writer = null;
		try {
			writer = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				writer.write(maze.toByteArray());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.loadCachedSolutions();
		   v.start();
		   */
		  

	}
	}
	
	


	



