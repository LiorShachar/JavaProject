package protocol;

import java.io.Serializable;
/**
 * this class acts as a tool to communicate with the server,
 * the server doesn't know anything about the object or its purpose
 * the client handlers at the server will get the data objects to where
 * they are supposed to be used.
 * the object contains a String for identification or details about its use
 * and an Object instance that represent any data.
 * 
 * 
 * 
 */
public class DataObject implements Serializable {

	
	private static final long serialVersionUID = 990613559212430670L;
	
	String dataDetails;
	Object data;
	
	
	public DataObject() {
		
	}
	
	
	public DataObject(String dataDetails, Object data) {
		super();
		this.dataDetails = dataDetails;
		this.data = data;
	}

	public String getDataDetails() {
		return dataDetails;
	}
	
	public void setDataDetails(String dataDetails) {
		this.dataDetails = dataDetails;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	
	

}
