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

			case "start":
				m.runServer();
				break;
				
			}
			/////////////////////////////////////////////////// NOTIFICATIONS
			/////////////////////////////////////////////////// FROM THE
			/////////////////////////////////////////////////// MODEL///////////////////////////////////////////////////////////
			
		} else if (arg0 == m)

		{
			switch (note) {

			case "updateListStatus":

				
				break;

			}

		}

	}

}
