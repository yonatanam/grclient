

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

	private Object currController;
	private User currUser = null;


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
		String msg = (String)message;
		System.out.println("Message is : "+msg);
		switch (msg)
		{
		case "LoginOK":
			((LoginController)currController).handleDBResult(message);
			break;
		case "UserOrPassIncorrect":
			((LoginController)currController).handleDBResult(message);
			break;
		case "NoSuchUser":
			JOptionPane.showMessageDialog(null,"No Such User!","Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		System.out.println("Handle message from server" + message);
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



	public Object getCurrObj() {
		return currController;
	}

	public User getCurrUser() {
		return currUser;
	}


	public  void setCurrUser(User currUser) {
		this.currUser = currUser;
	}


	public void setCurrObj(Object currObj) {
		this.currController = currObj;
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
