package controller;

import java.util.Observable;
import java.util.Observer;

import serverModel.MyServerModel;
import serverView.ServerView;

public class Presenter implements Observer {

	private MyServerModel m;
	private ServerView v;

	public Presenter(MyServerModel m, ServerView v) {
		super();
		this.m = m;
		this.v = v;

	}

	public Presenter() {
		super();

	}

	public void update(Observable arg0, Object arg1) {
		String note = (String) arg1;
		///////////////////////////////////////////////////////// NOTIFICATIONS
		///////////////////////////////////////////////////////// FROM THE
		///////////////////////////////////////////////////////// VIEW//////////////////////////////////////////////////////////////////
		if (arg0 == v) {
			switch (note) {

			case "Start":
				
				m.runServer();
				break;

			case "close":
				
				m.close();
				
				break;

			case "kick":
				String[] params=(String[])v.getData(note);
				m.DcClient(Integer.parseInt(params[0]));
				break;

			}
			/////////////////////////////////////////////////// NOTIFICATIONS
			/////////////////////////////////////////////////// FROM THE
			/////////////////////////////////////////////////// MODEL///////////////////////////////////////////////////////////

		} else if (arg0 == m)

		{
			switch (note) {

			case "status":
				String smsg = (String) m.getData(note);
				v.showMsg("Server Status: "+smsg);
				
				break;
			
			case "msg":
				String msg = (String) m.getData(note);
				v.showMsg(msg);
				break;

			case "error":
				String error = (String) m.getData(note);
				v.showError(error);
				break;

			case "newConnection":
				String[] itemElements = ((String) m.getData(note)).split(" ");
				v.addTableItem(itemElements[0], itemElements[1]);
				break;

			case "modelReady":
				v.start();
				break;

			}

		}

	}

}
