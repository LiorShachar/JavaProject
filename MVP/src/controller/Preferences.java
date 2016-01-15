package controller;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;



public class Preferences implements Serializable {
	
	
	public static String genAlgo;
	public static String solveAlgo;
	public static String heuristic;
	public static String Ui;
	public static int numberOfThreads;
	public static boolean sound;
	
	
	public Preferences() {
		
		
		
	}
	
	
	public Preferences(String genAlgo, String solveAlgo, String heuristic, int numberOfThreads, boolean sound,String Ui) {
		super();
		Preferences.Ui=Ui;
		Preferences.genAlgo = genAlgo;
		Preferences.solveAlgo = solveAlgo;
		Preferences.heuristic = heuristic;
		Preferences.numberOfThreads = numberOfThreads;
		Preferences.sound = sound;
	}


	public static String getGenAlgo() {
		return genAlgo;
	}


	public static void setGenAlgo(String genAlgo) {
		Preferences.genAlgo = genAlgo;
	}


	public static String getSolveAlgo() {
		return solveAlgo;
	}


	public static void setSolveAlgo(String solveAlgo) {
		Preferences.solveAlgo = solveAlgo;
	}


	public static String getHeuristic() {
		return heuristic;
	}


	public static void setHeuristic(String heuristic) {
		Preferences.heuristic = heuristic;
	}


	public static String getUi() {
		return Ui;
	}


	public static void setUi(String ui) {
		Ui = ui;
	}


	public static int getNumberOfThreads() {
		return numberOfThreads;
	}


	public static void setNumberOfThreads(int numberOfThreads) {
		Preferences.numberOfThreads = numberOfThreads;
	}


	public static boolean isSound() {
		return sound;
	}


	public static void setSound(boolean sound) {
		Preferences.sound = sound;
	}


	public static void print(){
		System.out.println("generateing algorithm: "+genAlgo);
		System.out.println("solving algorithm: "+solveAlgo);
		System.out.println("Astar Heuristic: "+heuristic);
		System.out.println("User Interface: "+Ui);
		System.out.println("Number of Threads "+numberOfThreads);
		System.out.println("Sound is Enabled "+sound);
		
		
	}

	
	
	

		
		
	}
	
	
	
	
	
	
	


