package io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

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
			return in.readInt();
		} catch (EOFException e) {
			return -1;
		}
		
	}

	@Override
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

}
