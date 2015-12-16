package model;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.text.AbstractDocument.LeafElement;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MazeProblem;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import controller.Controller;
import gnu.trove.list.array.TByteArrayList;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

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
		// we need to know the array size, the compressed version of the maze have a pattern of (value,number of returns)
		//so if we sum up all the values in the odd index places well get the right size
		DataInputStream reader = new DataInputStream(new FileInputStream(path));
		int val=0;
		int i=0;
		int sum=0;
		while(reader.available()>=4){
			val=reader.readInt();
			if(!(i%2==0)){
				sum+=val;
			}
				i++;
		}
		reader.close();
		
		byte[] arr= new byte[sum];
		MyDecompressorInputStream comp = new MyDecompressorInputStream(new FileInputStream(path));
		comp.read(arr);
		comp.close();
		c.toView("Loading completed successfuly");
		return arr;
	} catch (IOException e) {
		c.toView("error: could not load");
		e.printStackTrace();
	}
	return null;
	
}


@Override
public void fileSize(byte[] maze) {
	saveMaze(maze,"test.maz");
	File test = new File("test.maz");
	c.toView("File Size: "+test.length()+" Bytes");
	test.delete();

	
}


}
