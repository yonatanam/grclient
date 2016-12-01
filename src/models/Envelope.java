package models;

import java.io.Serializable;

import models.AbstractModel;
/**
 * this class is Envelope Model which saves the Object ,task and message fields to send to server , extends AbstractModel
 *
 */
public class Envelope  extends AbstractModel{
	/**the model that we send to server*/
	private Object object;
	/**the task that need to do in server*/
	private String task;
	/** the message that return from server*/
	private String message;

	public Envelope(Object object, String task,String message){
		this.object = object;
		this.task = task;
		this.message = message;
	}



	// Getters And Setters

	public String getMess() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
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
