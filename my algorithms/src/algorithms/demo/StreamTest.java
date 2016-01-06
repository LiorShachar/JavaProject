package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;


public class StreamTest {
	public void run() throws IOException{
		long x=System.currentTimeMillis();
		MyMaze3dGenerator creator = new MyMaze3dGenerator();
		Maze3d maze = creator.generate(200 ,200, 200);
	
	// save it to a file
	OutputStream out=new MyCompressorOutputStream(
	new FileOutputStream("1.maz"));
	out.write(maze.toByteArray());
	out.flush();
	out.close();
	byte[] o = maze.toByteArray();
	
	InputStream in=new MyDecompressorInputStream(
	new FileInputStream("1.maz"));
	byte b[]=new byte[maze.toByteArray().length];
	in.read(b);
	in.close();
	Maze3d loaded=new Maze3d(b);
	System.out.println(loaded.equals(maze));
	System.out.println(Arrays.equals(o, b));
	System.out.println(System.currentTimeMillis()-x);
	
	for(int i=0;i<300;i++)
	{
		if(o[i]!=b[i]){
		{System.out.println("problem in index: "+i);}
		{System.out.println("original value: "+o[i]);}
		{System.out.println("actual value: "+b[i]);}
		}
	}
	
	
	
	for(int i=0;i<60;i++)
	System.out.print(" "+b[i]);
	System.out.println(" ");
	System.out.println(maze.getxSize()+","+maze.getySize()+","+maze.getzSize()+","+maze.getGoalPosition().toString()+","+maze.getStartPosition().toString());
	System.out.println("");
	System.out.println("********************************");
	
	for(int i=0;i<60;i++)
		System.out.print(" "+o[i]);
	System.out.println(" ");
	System.out.println(loaded.getxSize()+","+loaded.getySize()+","+loaded.getzSize()+","+loaded.getGoalPosition().toString()+","+loaded.getStartPosition().toString());
	
	}
	
}
