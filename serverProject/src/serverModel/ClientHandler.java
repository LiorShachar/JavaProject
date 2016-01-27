package serverModel;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * <h1>ClientHandler</h1>
 * a class which is responsible to handle a specific client, it is threaded since it should implement an IO loop
 * 
 * 
 * <p>
 * <b>Notes:</b> .
 *
 * @author  Lior Shachar
 * @version 1.0
 * @since   2016-1-26
 */


public interface ClientHandler {
	
	void handleClient(Socket sock);
	
	
	/**
	 * returns the data for the notification key
	 * @param string the key for the data being held (data ment for the updating the observers)
	 * */
	public Object getData(String string);
	
	void close();
	
}
