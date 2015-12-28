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
		
		Presenter p = new Presenter();
		MyViewCLI v = new MyViewCLI();  
		MyModel m = new MyModel();
		   p.setModel(m);
		   p.setView(v);
		   v.getCli().addObserver(v);
		  v.addObserver(p);
		  m.addObserver(p);
		   v.start();
		   


	}
	
	


	


}
