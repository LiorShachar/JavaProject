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
* it should be threaded since it has an I\O loop, which would be blocked.
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
	 boolean flag;
	

	
	
	 public boolean isFlag() {
		return flag;
	}




	public void setFlag(boolean flag) {
		this.flag = flag;
	}




	public PrintWriter getOut() {
		return out;
	}




	public BufferedReader getIn() {
		return in;
	}



/**
 * set the input stream to communicate with the user
 * */

	public void setIn(BufferedReader in) {
		this.in = in;
	}



	/**
	 * set the output stream to communicate with the user
	 * */

	public void setOut(PrintWriter out) {
		this.out = out;
	}


/**
 * gets the user input command
 * */

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
		this.flag=true;
	}


/**
 * an Input loop from the user, matching the input using regex in order to get the right command from the controller
 * and executing it.
 * once it gets "exit" as input it will kill all running threads and shutdown.
 */
	 
	 
	





@Override
public void run() {
	String input="";
	System.out.println("** Greetings! please enter your desired command **");
	System.out.println("** for the commands list type \"help\" , for GUI type \"GUI\" **");
	
	try {
		while (flag){
			if(!(input=in.readLine()).isEmpty()){
				this.inputCom=input;
				setChanged();
				notifyObservers(this.inputCom);
				
				
			}
				
		}
		
		
	} catch (IOException e) {
		System.out.println("invalid input error ");
		e.printStackTrace();
	}
	
	
}

	 
	 


	
	
}
