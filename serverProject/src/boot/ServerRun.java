package boot;

import serverModel.MyServerModel;

public class ServerRun {

	public static void main(String[] args) {
		MyServerModel m = new MyServerModel();
		m.start();
		m.runServer();
	}

}
