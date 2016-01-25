package serverModel;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface ClientHandler {
	void handleClient(Socket sock);
	public Object getData(String string);
	void close();
	
}
