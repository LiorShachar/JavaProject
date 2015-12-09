package algorithms.demo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;

public class StreamTest {
	public void run(){
	Maze3d maze; //... generate it
	
	// save it to a file
	OutputStream out=new MyCompressorOutputStream(
	new FileOutputStream("1.maz"));
	out.write(maze.toByteArray());
	out.flush();
	out.close();
	
	InputStream in=new MyDecompressorInputStream(
	new FileOutputStream("1.maz"));
	byte b[]=new byte[maze.toByteArray().length];
	in.read(b);
	in.close();
	Maze3d loaded=new Maze3d(b);
	System.out.println(loaded.equals(maze));
	
	
	}
	
}
