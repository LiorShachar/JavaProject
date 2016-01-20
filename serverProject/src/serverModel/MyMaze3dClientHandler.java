package serverModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;


import protocol.DataObject;

public class MyMaze3dClientHandler implements ClientHandler,Observer{
	ObjectOutputStream dataWriter;
	ObjectInputStream dataReader;
	
	volatile DataObject outPackage;
	volatile DataObject inPackage;
	

	MyServerModel originModel;
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		
		/*
		BufferedReader in=new BufferedReader(new InputStreamReader(inFromClient));
		
		PrintWriter out=new PrintWriter(outToClient);*/
		
		
		//streams for sending and getting objects
		
		try {
			 dataWriter=new ObjectOutputStream(outToClient);
			 dataReader=new ObjectInputStream(inFromClient);
			 dataWriter.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			outPackage= new DataObject("connected","connected man !");
			dataWriter.writeObject(outPackage);
			dataWriter.flush();
			dataWriter.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String modelCommand;
		String []params;
			do{ // after the first ACK we start recieving orders from the client
				inPackage=(DataObject)dataReader.readObject();
				
				modelCommand= inPackage.getDataDetails();
				params =(String[])inPackage.getData();
				
				switch (modelCommand){
				
				case "handleGenerate":
					break;
				case "handleDir":
					break;
				case "handleSaveMaze":
					break;
				case "handleUpdatePosition":
					break;
				case "handleLoadMaze":
					break;
				case "handleFileSize":
					break;
				case "handleMazeSize":
					break;
				case "handleSolveMaze":
					break;
				
				
				}
				
				
				
			}
			while(inPackage != null && !inPackage.getDataDetails().equals("exit"));
			
		
	}

/*
	@Override
	public void update(Observable arg0, Object arg1) {
		//TODO 
		if(arg0==originModel){
			
			
			
		}
		
	}
*/
}
