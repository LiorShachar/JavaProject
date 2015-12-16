package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeProblem;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import gnu.trove.TByteCollection;
import gnu.trove.list.array.*;

public class MyModel implements Model {

	
	private Controller c;
	
	 public MyModel(Controller c){
	   this.c = c;
	 }

	
	@Override
	public void search(MazeProblem p) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void generateMaze(String name, int y, int x, int z) {
		
		c.toView("generating maze...");
		MyMaze3dGenerator gen = new MyMaze3dGenerator();
		Maze3d created = gen.generate(y,x,z);
		c.addMaze(name,created.toByteArray());
		c.toView("maze "+name+" is ready");
		
	}
	
public void generateMazeThread(String name, int y, int x, int z) {
		
	new Thread(new Runnable() {
		   public void run() {
			   generateMaze(name,y,x,z);
		   }
		 }).start();

		
	}


@Override
public void saveMaze(byte[] maze, String path) {
	try {
		MyCompressorOutputStream writer = new MyCompressorOutputStream(new FileOutputStream(path));
		writer.write(maze);
		writer.close();
		c.toView("Save completed successfuly");
	} catch (IOException e) {
		c.toView("error: could not save");
		e.printStackTrace();
	}
	
}

@Override
public byte[] loadMaze(String path) {
	try {
		byte[] temp= new byte[5000000];
		MyDecompressorInputStream reader = new MyDecompressorInputStream(new FileInputStream(path));
		reader.read(temp);
		TByteArrayList arr = new TByteArrayList(temp);
		TByteArrayList nuller = new TByteArrayList();
		nuller.add(null);
		arr.removeAll(nuller);
		reader.close();
		c.toView("Loading completed successfuly");
		return arr.toArray();
	} catch (IOException e) {
		c.toView("error: could not load");
		e.printStackTrace();
	}
	return null;
	
}


}
