package boot;

import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

public class Run {

	public static void main(String[] args) {
		Controller c = new MyController();
		   View v = new MyView(c);
		   Model m = new MyModel(c);
		   c.setModel(m);
		   c.setView(v);
		   v.start();


	}

}
