package algorithms.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class Run {

	public static void main(String[] args) throws IOException {
		
		
		StreamTest start = new StreamTest();
		start.run();
		
		/*
		FileOutputStream out = new FileOutputStream("lol.txt");
		MyCompressorOutputStream myc = new MyCompressorOutputStream(out);
		MyMaze3dGenerator creator = new MyMaze3dGenerator();
		Maze3d maze = creator.generate(1 ,10, 10);
		myc.write(maze.toByteArray());
		out.flush();
		out.close();
		
		FileInputStream newin = new  FileInputStream("lol.txt");
		MyDecompressorInputStream dc = new MyDecompressorInputStream(newin);
		byte b[] = new byte[maze.toByteArray().length];
		System.out.println(dc.read(b)); 
		
		*/
		
	
	
	
	
	

	

				
		
		
		

	

}
	
	
}