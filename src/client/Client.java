

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

		((LoginController)currController).handleDBResult(message);
		if(message instanceof String ){ 

			if(((String)message).equals("UserOrPassIncorrect") )
			{
				((LoginController)currController).handleDBResult(message);
			}
			if(((String)message).equals("NoUser") ){
				JOptionPane.showMessageDialog(null,"No Such User!","Error", JOptionPane.ERROR_MESSAGE);
			}
			if(((String)message).equals("UserOrPassIncorrectChange") ){

				JOptionPane.showMessageDialog(null,"User Or Passwod Incorrect","Error", JOptionPane.ERROR_MESSAGE);
			}
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
