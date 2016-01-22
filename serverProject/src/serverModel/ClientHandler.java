package serverModel;

import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	void handleClient(InputStream inFromClient, OutputStream outToClient);
	public Object getData(String string);
	
}
