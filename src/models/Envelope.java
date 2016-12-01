package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import models.AbstractModel;
/**
 * this class is Envelope Model which saves the Object ,task and message fields to send to server , extends AbstractModel
 *
 */
public class Envelope  extends AbstractModel{
	/**the model that we send to server*/
	private Object object;
	
	/** the message that return from server*/
	private Map<String, String> params; //consider implementing an arraylist for values
	//string msg should always exist
	public Envelope(Object object,Map<String, String> params){
		this.object = object;
		this.params = params;
	}

	// Getters And Setters

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Object getObject() 
	{
		return object;
	}
	public void setObject(Object obj1) 
	{
		this.object = obj1; 
	}
}
