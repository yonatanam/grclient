package models;


import java.util.HashMap;
import java.util.Map;

import models.AbstractModel;
/**
 * this class is Envelope Model which saves the Object ,task and message fields to send to server , extends AbstractModel
 *
 */
public class Envelope  extends AbstractModel{
	/**the model that we send to server*/

	/** the message that return from server*/
	private Map<String, Object> params; //object can also be arraylist for multiple values per key
	//string msg should always exist
	public Envelope(Map<String, Object> params){
		this.params = params;
	}
	

	// Getters And Setters

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
