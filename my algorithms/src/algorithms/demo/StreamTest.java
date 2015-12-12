package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class StreamTest {
	public void run() throws IOException{
		MyMaze3dGenerator creator = new MyMaze3dGenerator();
		Maze3d maze = creator.generate(1 ,10, 10);
	
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
	
	
	
	int z=0;
	while (z<30){
		System.out.println((int)o[z] +" against "+(int)b[z] );
		z++;
	}
	
	
	
	
	}
	
}
