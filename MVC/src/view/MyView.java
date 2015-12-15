package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import algorithms.search.Solution;
import controller.Controller;

public class MyView implements View {
	private Controller c;
	private CLI cli;
	
	 public MyView(Controller c){
	   this.c=c;
	   this.cli =new CLI(c.getCommandCreator(),new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out));
	 }
	 
	 
	 
	 
	public Controller getC() {
		return c;
	}




	public void setC(Controller c) {
		this.c = c;
	}




	public CLI getCli() {
		return cli;
	}




	public void setCli(CLI cli) {
		this.cli = cli;
	}




	@Override
	public void start() {
		cli.start();
		
		
	}
	
	@Override
	public void displaySolution(Solution s) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void list(String string) {
		File lister = new File(string);
		String[] pathdetails = lister.list();
		for (String s: pathdetails)
			System.out.println(s);
		
	}
@Override
public void printMsg(String s) {
	System.out.println(s);
	
}




@Override
public void displayMaze(String string) {
	
	
}
}
