package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import controller.Properties;

public class XmlHandler {

		Properties prop;
		Properties pref;
		
		InputStream in;
		OutputStream out;
		
		

	/**
	 * a ctor in case there are no values in Preferences*
	 */
	
	public XmlHandler() {
		super();
		this.prop =  new Properties();
		this.pref = new Properties();
	}
	
	
	


	public void SaveDataToXml(String path){
		
		 
		 prop.put("genAlgo",Properties.getGenAlgo());
		 prop.put("solveAlgo",Properties.getSolveAlgo());
		 prop.put("heuristic",Properties.getHeuristic());
		 prop.put("Ui",Properties.getUi());
		 prop.put("numberOfThreads",Integer.toString(Properties.getNumberOfThreads()));
		 prop.put("sound",Boolean.toString(Properties.isSound()));
		try {
			
			out = new FileOutputStream(path);
			prop.storeToXML(out, "Maze Settings", "UTF-8");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public void LoadDataFromXml(String path){
		try {
			in = new FileInputStream(path);
			prop.loadFromXML(in);

			Properties.setGenAlgo(prop.getProperty("genAlgo"));
			
			Properties.setSolveAlgo(prop.getProperty("solveAlgo"));
			
			Properties.setHeuristic(prop.getProperty("heuristic"));
			
			Properties.setUi(prop.getProperty("Ui"));
			
			Properties.setNumberOfThreads(Integer.parseInt(prop.getProperty("numberOfThreads")));
			
			Properties.setSound(Boolean.parseBoolean(prop.getProperty("sound")));
			
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	public void PrintProperties(){
		prop.list(System.out);
		
	}
	
	
	
	
}
