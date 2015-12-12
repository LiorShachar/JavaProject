package algorithms.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import io.MyCompressorOutputStream;

public class Run {

	public static void main(String[] args) throws IOException {
		/*
		FileOutputStream out = new FileOutputStream("testingme.txt");
		MyCompressorOutputStream myc = new MyCompressorOutputStream(out);
		MyMaze3dGenerator creator = new MyMaze3dGenerator();
		Maze3d maze = creator.generate(12 ,13, 15);
		myc.write(maze.toByteArray());
		*/
		FileInputStream newin = new  FileInputStream("testingme.txt");
		DataInputStream bla = new DataInputStream(newin);
		int i=0;
		try {
			while (true){
				System.out.println(bla.readInt());
				i++;
				}
		} catch (Exception e) {
			System.out.println(i);
		}
		
		
	
	
	
	
	

	

				
		
		
		

	

}
	
	public static byte[] intToBytes(int my_int) throws IOException {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ObjectOutput out = new ObjectOutputStream(bos);
	    out.writeInt(my_int);
	    out.close();
	    byte[] int_bytes = bos.toByteArray();
	    bos.close();
	    return int_bytes;
	}
	
	public static int bytesToInt(byte[] int_bytes) throws IOException {
	    ByteArrayInputStream bis = new ByteArrayInputStream(int_bytes);
	    ObjectInputStream ois = new ObjectInputStream(bis);
	    int my_int = ois.readInt();
	    ois.close();
	    return my_int;
	}
}