package boot;

import serverModel.MyServerModel;
import serverView.ServerView;

public class ServerRun {

	public static void main(String[] args) {
		MyServerModel m = new MyServerModel();
		ServerView v= new ServerView("Maze Server", 450, 300);
		m.start();
		v.run();
	}

}
