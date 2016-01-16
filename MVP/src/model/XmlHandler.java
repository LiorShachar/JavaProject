package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import controller.Preferences;

public class XmlHandler {

		Properties prop;
		Preferences pref;
		
		InputStream in;
		OutputStream out;
		
		

	/**
	 * a ctor in case there are no values in Preferences*
	 */
	
	public XmlHandler() {
		super();
		this.prop =  new Properties();
		this.pref = new Preferences();
	}
	
	
	


	public void SaveDataToXml(String path){
		
		 
		 prop.put("genAlgo",Preferences.getGenAlgo());
		 prop.put("solveAlgo",Preferences.getSolveAlgo());
		 prop.put("heuristic",Preferences.getHeuristic());
		 prop.put("Ui",Preferences.getUi());
		 prop.put("numberOfThreads",Integer.toString(Preferences.getNumberOfThreads()));
		 prop.put("sound",Boolean.toString(Preferences.isSound()));
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

			Preferences.setGenAlgo(prop.getProperty("genAlgo"));
			
			Preferences.setSolveAlgo(prop.getProperty("solveAlgo"));
			
			Preferences.setHeuristic(prop.getProperty("heuristic"));
			
			Preferences.setUi(prop.getProperty("Ui"));
			
			Preferences.setNumberOfThreads(Integer.parseInt(prop.getProperty("numberOfThreads")));
			
			Preferences.setSound(Boolean.parseBoolean(prop.getProperty("sound")));
			
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
