package serverModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <h1>MyClientHandler</h1>
 * a common client handler
 * 
 * 
 * <p>
 * <b>Notes:</b> .
 *
 * @author  Lior Shachar
 * @version 1.0
 * @since   2016-1-26
 */



public abstract class MyClientHandler implements ClientHandler{

	@Override
	public void handleClient(Socket sock) {
		
		BufferedReader in=null;
		PrintWriter out=null;
		try {
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			out = new PrintWriter(sock.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String line;
		try{
			while(!(line=in.readLine()).equals("exit")){
				
				StringBuilder sb=new StringBuilder(line);
				out.println(sb.reverse().toString());
				out.flush();
			}
			out.println("good bye");
			out.flush();
		}catch(Exception e){}
	}

	@Override
	public Object getData(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
