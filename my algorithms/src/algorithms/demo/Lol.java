package algorithms.demo;

import java.io.IOException;
import java.nio.ByteBuffer;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;

public class Lol {

	public static void main(String[] args) {
StreamTest bah = new StreamTest();
	try {
		bah.run();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	
	}
		
		
		


	public static byte[] intToBytes(int i){
		
		ByteBuffer b = ByteBuffer.allocate(4);
		//b.order(ByteOrder.BIG_ENDIAN); // optional, the initial order of a byte buffer is always BIG_ENDIAN.
		b.putInt(i);
		return b.array();
		}
	
public static int bytesToInt(byte[] arr){
		
		ByteBuffer b = ByteBuffer.wrap(arr);
		return b.getInt();
		
		}
}
