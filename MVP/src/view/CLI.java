package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

import controller.Command;

/**
*
* 
* 
* 
* 
* <h1>CLI</h1><h4>(Command Line Interface)</h4>
* this class represent a user interface based on textual commands as input.
* it is threaded since it has an I\O loop.
* 
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-12-17
*/
public class CLI extends Observable implements Runnable{

	private String inputCom;
	private BufferedReader in; // source for reading
	private PrintWriter out; // source for writing
	

	
	
	 public PrintWriter getOut() {
		return out;
	}




	public BufferedReader getIn() {
		return in;
	}





	public void setIn(BufferedReader in) {
		this.in = in;
	}





	public void setOut(PrintWriter out) {
		this.out = out;
	}




	public String getInputCom() {
		return inputCom;
	}




	public void setInputCom(String inputCom) {
		this.inputCom = inputCom;
	}




	public CLI(BufferedReader in, PrintWriter out) {
		super();
		
		this.in = in;
		this.out = out;
	}


/**
 * an Input loop from the user, matching the input using regex in order to get the right command from the controller
 * and executing it.
 * once it gets "exit" as input it will kill all running threads and shutdown.
 */
	 
	 
	
	public void start() {
		String input;
		System.out.println("** Greetings! please enter your desired command, for the commands list type \"help\" **");
		
		
		try {
			while (true){
				if(!(input=in.readLine()).isEmpty()){
					this.inputCom=input;
					hasChanged();
					notifyObservers(this.inputCom);
					
					
				}
					
			}
			
			
		} catch (IOException e) {
			System.out.println("invalid input error ");
			e.printStackTrace();
		}
		
		
		
		
	}

	 
	 


	
	
}
