package controller;

import java.util.HashMap;


import model.Model;
import view.View;


/**
*
* 
* 
* 
* 
* <h1>Controller</h1>
* The interface façade of the controller section in our MVC architectural pattern
* the controller acts as a "middleman" between the view section and the model section
* 
* 
* <p>
* <b>Notes:</b> 
*
* @author  Lior Shachar
* @version 1.0
* @since   2015-12-17
*/
public interface Controller {
	   
	   public Model getModel();
		public void setModel(Model m);
		public View getView();
		public void setView(View v);
		public HashMap<String, Command> getCommandCreator();
		public void setCommandCreator(HashMap<String, Command> commandCreator);
		public void toView(String s);
		
		
			
}
