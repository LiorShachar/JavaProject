package boot;

import controller.Presenter;
import serverModel.MyServerModel;
import serverView.ServerView;




public class ServerRun {

	public static void main(String[] args) {
		
		MyServerModel m = new MyServerModel();
		ServerView v= new ServerView("Maze Server", 450, 300);
		Presenter p= new Presenter(m, v);
		m.addObserver(p);
		v.addObserver(p);
		m.start();
		
	}

}
