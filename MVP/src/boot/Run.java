package boot;
import controller.Presenter;
import controller.Presenter;
import model.Model;
import model.MyModel;
import view.MyViewCLI;
import view.View;

public class Run {

	public static void main(String[] args) {
		System.out.println("[Lior Shachar 304848427]");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		
		Presenter c = new Presenter();
		   View v = new MyViewCLI();  
		   Model m = new MyModel();
		   c.setModel(m);
		   c.setView(v);
		   v.start();
		   


	}
	
	


	


}
