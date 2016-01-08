package boot;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.swt.SWT;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Presenter;
import io.MyCompressorOutputStream;
import model.Model;
import model.MyModel;
import view.MyGuiView;
import view.MyViewCLI;
import view.View;
import widgets.MainWindow;
import widgets.Maze2D;
import widgets.MazeWindow;

public class Run {

	public static void main(String[] args) {
		System.out.println("[Lior Shachar 304848427]");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		
		
		
		
		Presenter p = new Presenter();
		//MyViewCLI v = new MyViewCLI();  
		//MazeWindow v=new MazeWindow("Maze Template", 800, 500);
		
		MyGuiView v = new MyGuiView();
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
		   v.start();
		   


	}
	
	


	


}
