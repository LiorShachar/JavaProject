package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
public class CLI extends Thread {

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
	 
	 
	@Override
	public void run() {
		String input;
		Set<String> keys=hmap.keySet();
		Command c;
		System.out.println("** Greetings! please enter your desired command, for the commands list type \"help\" **");
		try {
			while (true){
				if(!(input=in.readLine()).isEmpty()){
					
					
				}
					
			}
			System.out.println("Shutting down...");
			c=hmap.get("exit");
			c.doCommand(null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	 
	 


	
	
}
