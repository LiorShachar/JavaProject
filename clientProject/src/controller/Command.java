package controller;





/**
 * <h1>Command</h1>
 * The interface of a UI command
 * 
 * 
 * <p>
 * <b>Notes:</b> .
 *
 * @author  Lior Shachar
 * @version 1.0
 * @since   2015-12-17
 */

public interface Command {
	
	/**
	 * Do command.
	 *
	 * @param args the args
	 */
	void doCommand(String[] args);



}
