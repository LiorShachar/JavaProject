package io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import gnu.trove.list.array.TByteArrayList;

/**
 * 
 * 
* <h1>MyDecompressorInputStream</h1>
* 
* a class which represent a {@link Maze3d} decompressor.
* it uses read() in order to get the original array written to its source by {@link MyCompressorOutputStream}
*  
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-12-17
*/




public class MyDecompressorInputStream extends InputStream {

	private DataInputStream in;

	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = new DataInputStream(in);
	}

	@Override
	public int read() throws IOException {
		try {
			
			return in.read();
			
		} catch (EOFException e) {
			return -1;
		}
		
	}

	/*@Override
	public int read(byte[] b) throws IOException {

		int s = 0;
		int i = 0;
		int a = 0;
		while (i < b.length && s != -1) {
			s = read();
			if (s > -1) {
				a = read();
				if (a>1){
				while (a>0) {
					b[i] = (byte)s;
					i++;
					a--;
					}
				}
				else if (a==1){
					b[i] = (byte)s;
					i++;
				}
				else {in.close();return -1;}
			}
		}
		in.close();return i;
	}
*/
	@Override
	public int read(byte[] temp) throws IOException {
		 
		 byte val=0; // value of the byte read
		 byte counter=0; // number of instances of the byte value
		 int index=0; // the iterator on our destination arr
		 int fileindex=0; // an index of the file iterator
		 int reader; // an int to get the return value from DataInputStream read method.
		 while((reader=in.read())!=-1){ // while we are able to get the next data
			 if(fileindex%2==0){ //if we are on the even index we have the value, else (odd index) we have the number of the instances
			 val=(byte)reader; // val is casted since the return is int and we need a byte.
			 fileindex++;
			 }
			 else{
				counter=(byte)reader; // we are on an odd index so counter get the value
				fileindex++;
			 for(int i=0;i<counter;index++,i++) // a loop that adds the same value * counter of times, while incrementing the destination array index each time.
				 temp[index]=val;
				 
			 }
				 
			 
			 
		 }
		 return fileindex; // we return the amount of bytes we got from the file.
		 
	}

}
