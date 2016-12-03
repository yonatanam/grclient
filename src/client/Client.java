

package client;

import server.*;
import gui.*;
import models.Envelope;
import models.User;
import ocsf.client.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Window;
import java.io.*;
import javax.swing.JOptionPane;

import controllers.*;


/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.

 */
public class Client extends ObservableClient
{

	private Object currentController;
	private User currentUser = null;


	public Client(String host, int port)  throws IOException 
	{
		super(host, port); //Call the superclass constructor
		openConnection();
	}
	private LoginGUI loginG;
	//Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	public synchronized void handleMessageFromServer(Object message)  
	{
		String msg;
		if (message instanceof String)
			msg = ((String)message);
		else
		msg = (String) ((Envelope)message).getParams().get("msg");
		
		switch (msg)
		{
		case "LoginOK":
			((LoginController)currentController).handleDBResult(message);
			break;
		case "UserOrPassIncorrect":
			((LoginController)currentController).handleDBResult(message);
			break;
		case "NoSuchUser":
			JOptionPane.showMessageDialog(null,"No Such User!","Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "WorkerData":
			((ReadWorkerController)currentController).handleDBResult(message);
			break;
		case "WorkerUpdatedOK":
			JOptionPane.showMessageDialog(null,"Worker was updated successfuly!");
		}
		notify();   
	}


	public void handleMessageFromClientUI(String message)
	{
		try
		{
			sendToServer(message);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Could not send message to server.  Terminating client.");
			quit();
		}
	}



	public Object getCurrentController() {
		return currentController;
	}

	public User getCurrentUser() {
		return currentUser;
	}


	public  void setCurrentUser(User currUser) {
		this.currentUser = currUser;
	}


	public void setCurrentController(Object currObj) {
		this.currentController = currObj;
	}

	//terminate client
	public void quit()
	{
		try
		{
			closeConnection();
		}
		catch(IOException e) {}
		System.exit(0);
	}
}
