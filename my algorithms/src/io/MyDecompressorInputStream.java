package io;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

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
				else return -1;
			}
		}
		return i;
	}

}
