package io;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}

	
	
	
	
	@Override
	public void write(int b) throws IOException {
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeInt(b);
		
		
	}
	


}
