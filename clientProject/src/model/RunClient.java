package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RunClient {

	// testing a client
	public static void main(String[] args) throws Exception{
		System.out.println("Client Side");
		Socket theServer=new Socket("localhost",5400); // get the server socket
		System.out.println("connected to server!");
		
		PrintWriter outToServer=new PrintWriter(theServer.getOutputStream()); // get the server stream source to write to
		
		/////////////////////////////////////////////////////////
		outToServer.println("Hey there");
		outToServer.flush(); // we flush after every print
		//////////////////////////////////////////////////////////
		
		BufferedReader in=new BufferedReader(new InputStreamReader(theServer.getInputStream())); //get the server stream to read from
		
		
		////////////////////////////////////////////////////////////////we get the response from the server to the console
		System.out.println(in.readLine());// 
		////////////////////////////////////////////////////////////////
		
		
		
	///////***************************[input loop]*****************************************************************
		
		
			BufferedReader inputfromuser=new BufferedReader(new InputStreamReader(System.in));
			String s;
			s=inputfromuser.readLine();
			outToServer.println("Hey there");
			outToServer.flush();
			
			
			
		///////**************************************************************************************************************
			
		
		
		
		
		
		
	/*	BufferedInputStream maggieFromFile=new BufferedInputStream(new FileInputStream("resources/Maggie.gif"));
		//a buffered inputstream from a file so we can read from the file
		
		
		
		
		BufferedOutputStream maggieToServer=new BufferedOutputStream(theServer.getOutputStream());
		//we wrap the server output stream with a buffer
		

		byte[] myBuffer=new byte[100]; // set the buffer packet size to read
		int bytesRead;
		while((bytesRead=maggieFromFile.read(myBuffer))!=-1){ // if we the buffer read something
			maggieToServer.write(myBuffer, 0, bytesRead); // write the amount of bytes read to the server
		}		
		
		maggieToServer.flush();		
		maggieFromFile.close();
		
		BufferedReader asciiMaggieFromServer=new BufferedReader(new InputStreamReader(theServer.getInputStream()));
		String line;
		
		
		while(!(line=asciiMaggieFromServer.readLine()).equals("done")){ //read from the bufferedreader to the string line, if its not DONE just print it
			System.out.println(line);
		}
		
		outToServer.println("exit"); // the command that get the server to disconnect
		outToServer.flush();

		asciiMaggieFromServer.close();
		outToServer.close();
		
		theServer.close();*/
	}

}
