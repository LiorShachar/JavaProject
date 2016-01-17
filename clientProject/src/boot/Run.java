package boot;

import controller.Presenter;
import model.MyModel;

public class Run {
	
	
	

	
	
	

	
	

	public static void main(String[] args) {
		
			
		 
		
		
		
		System.out.println("[Lior Shachar 304848427]");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		
		
		
		
		Presenter p = new Presenter(); 
		
		
	//
		
		
		MyModel m = new MyModel();
		   p.setModel(m);
		   m.addObserver(p);
		   m.loadCachedSolutions();
		   p.loadSettings("resources/properties.xml");
		  
		  
		  
			
			
			
		   
		   
		  

	}
	}
	
	


	



