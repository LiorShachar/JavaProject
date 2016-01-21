package serverModel;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import singletonexplicitpack.Properties;


/**
 * an XML properties writer, implementing the singleton design pattern(for its own instance)
 * and allowing the creation of only one Properties instance, to make sure
 * all the parts of the project are sync'ed with the same settings
 * @author Lior Shachar
 * @since 1/17/2016
 * 
 * <b>UPDATE:
 * classmate Bar David deserves a big credit for the 
 * correct implementation of this code
 * </b>
 * 
 * */

public class XMLproperties {

	 private static Properties prop ;
		
		
	
	 XMLproperties() {
		
	}
	
	/**
	 * return the static properties being held, if its null it will go to the default directory to get the xml file from
	 * **/
	 
	 
	//a classic singleton constructor(for the held instance) ensures we always get the same properties, and wont make an instance if it isn't necessary
	 
	 
	 // gets the held object
	 
	public static Properties getMyPropertiesInstance() throws FileNotFoundException  {
	      if(prop == null) {
	    	  prop = readProperties("resources/properties.xml");
	      }
	      return prop;
	   }
	
	public static Properties getCustomProperties(String path) throws FileNotFoundException  {
	      	  	
	    	  prop = readProperties(path);
	      
	      return prop;
	   }
	
	
	
	/**
	 * writes a given properties instance to the given path as an XML file(no relation to the static object being held)
	 * **/
	
	public static void writeProperties(Properties p, String path) throws FileNotFoundException {

		XMLEncoder en = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));

		en.writeObject(p);
		en.flush();
		en.close();
	}
	
	/**
	 * read the Properties object from the given file path and updates the current properties file
	 * 
	 * **/
	private static Properties readProperties(String path) throws FileNotFoundException {
		XMLDecoder de = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
		Properties p = (Properties) de.readObject();
		de.close();
		return p;
	}
	
}
