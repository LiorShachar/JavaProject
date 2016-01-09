package controller;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;



public class Properties implements Serializable {
	
	
	String genAlgo;
	String solveAlgo;
	String heuristic;
	String Ui;
	int numberOfThreads;
	boolean sound;
	
	
	public Properties() {
		this.Ui="GUI";
		this.genAlgo = "DFS";
		this.solveAlgo = "BFS";
		this.heuristic = "Manhatten";
		this.numberOfThreads = 5;
		this.sound = true;
		
	}
	
	
	public Properties(String genAlgo, String solveAlgo, String heuristic, int numberOfThreads, boolean sound,String Ui) {
		super();
		this.Ui=Ui;
		this.genAlgo = genAlgo;
		this.solveAlgo = solveAlgo;
		this.heuristic = heuristic;
		this.numberOfThreads = numberOfThreads;
		this.sound = sound;
	}


	public String getUi() {
		return Ui;
	}


	public void setUi(String ui) {
		Ui = ui;
	}


	public String getGenAlgo() {
		return genAlgo;
	}


	public void setGenAlgo(String genAlgo) {
		this.genAlgo = genAlgo;
	}


	public String getSolveAlgo() {
		return solveAlgo;
	}


	public void setSolveAlgo(String solveAlgo) {
		this.solveAlgo = solveAlgo;
	}


	public String getHeuristic() {
		return heuristic;
	}


	public void setHeuristic(String heuristic) {
		this.heuristic = heuristic;
	}


	public int getNumberOfThreads() {
		return numberOfThreads;
	}


	public void setNumberOfThreads(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
	}


	public boolean isSound() {
		return sound;
	}


	public void setSound(boolean sound) {
		this.sound = sound;
	}
	
	
	
	

	
	
	

		
		
	}
	
	
	
	
	
	
	


