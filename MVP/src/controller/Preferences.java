package controller;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;



public class Preferences implements Serializable {
	
	
	public static String generatingAlgorithm;
	public static String searchingAlgorithm;
	public static String heuristic;
	public static String userInterface;
	public static int numberOfThreads;
	public static boolean sound;
	
	
	public Preferences() {
		
		
		
	}
	
	
	public Preferences(String genAlgo, String solveAlgo, String heuristic, int numberOfThreads, boolean sound,String Ui) {
		super();
		
		Preferences.userInterface=Ui;
		Preferences.generatingAlgorithm = genAlgo;
		Preferences.searchingAlgorithm = solveAlgo;
		Preferences.heuristic = heuristic;
		Preferences.numberOfThreads = numberOfThreads;
		Preferences.sound = sound;
	}



	public static String getGenAlgo() {
		return generatingAlgorithm;
	}


	public static void setGenAlgo(String genAlgo) {
		Preferences.generatingAlgorithm = genAlgo;
	}


	public static String getSolveAlgo() {
		return searchingAlgorithm;
	}


	public static void setSolveAlgo(String solveAlgo) {
		Preferences.searchingAlgorithm = solveAlgo;
	}


	public static String getHeuristic() {
		return heuristic;
	}


	public static void setHeuristic(String heuristic) {
		Preferences.heuristic = heuristic;
	}


	public static String getUi() {
		return userInterface;
	}


	public static void setUi(String ui) {
		userInterface = ui;
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
		System.out.println("generateing algorithm: "+generatingAlgorithm);
		System.out.println("solving algorithm: "+searchingAlgorithm);
		System.out.println("Astar Heuristic: "+heuristic);
		System.out.println("User Interface: "+userInterface);
		System.out.println("Number of Threads "+numberOfThreads);
		System.out.println("Sound is Enabled "+sound);
		
		
		
	}

	
	
	

		
		
	}
	
	
	
	
	
	
	


