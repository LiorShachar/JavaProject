package io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * 
 * 
* <h1>MyCompressorOutputStream</h1>
* 
* a class which represent a {@link Maze3d} compressor.
* it uses write() in order to get an array of bytes, count the returns and
* write them with the value, instead of repeating and writing the same value all over again.
*  
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-12-17
*/



public class MyCompressorOutputStream extends OutputStream {

	private DataOutputStream out;

	public MyCompressorOutputStream(OutputStream out) {
		this.out = new DataOutputStream(out);
	}

	@Override
	public void write(int b) throws IOException {
		
				 out.writeInt(b);
	}

	
	public void write(byte b) throws IOException {
		
				 out.write(b);
				
	}
	
	
	/**
	 * writes a compressed version of the array by reading the amount of element returns and write it instead
	 * of repeating the same element 
	 */
	@Override
	public void write(byte[] b) throws IOException {
		
		
		int len = b.length; 
	
		byte c=0;
		byte t;
		
		if (len > 1){
			for (int i=0 ; i<len; i++){
			t= b[i];
			c=1;
			while (i+1 < len && b[i+1]==b[i]){
				i++;
				c++;
			}
			write(t);
			write(c);
			
			}
		}
		else if (len == 1){
		write(b[0]);
		c=1;
		write(c);
		}
		
	
			
			out.close();
		}
	
	
	

	}


