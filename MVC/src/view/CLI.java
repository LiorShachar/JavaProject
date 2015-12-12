package view;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI {

	private HashMap<String, Command> hmap;
	private BufferedReader in;
	private PrintWriter out;
	

	
	
	 public CLI(HashMap<String, Command> hmap, BufferedReader in, PrintWriter out) {
		super();
		this.hmap = hmap;
		this.in = in;
		this.out = out;
	}




	//THREADED
	public void start(){
		//TODO
	}
	
	
}
