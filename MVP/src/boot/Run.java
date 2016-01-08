package boot;
import controller.Presenter;
import model.Model;
import model.MyModel;
import view.MyViewCLI;
import view.View;
import widgets.MainWindow;
import widgets.MazeWindow;

public class Run {

	public static void main(String[] args) {
		System.out.println("[Lior Shachar 304848427]");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		
		Presenter p = new Presenter();
		//MyViewCLI v = new MyViewCLI();  
		//MazeWindow v=new MazeWindow("Maze Template", 800, 500);
		MainWindow v= new MainWindow("Menu", 800, 500);
		MyModel m = new MyModel();
		   p.setModel(m);
		   p.setView(v);
		//   v.getCli().addObserver(v);
		  v.addObserver(p);
		  m.addObserver(p);
		   v.start();
		   


	}
	
	


	


}
