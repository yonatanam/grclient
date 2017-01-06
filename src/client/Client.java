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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.*;


/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.

 */
public class Client extends ObservableClient
{

	private AbstractController currentController;
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
		currentController.handleDBResult(message);
		/*
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
			break;
			*/
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



	public AbstractController getCurrentController() {
		return currentController;
	}

	public User getCurrentUser() {
		return currentUser;
	}


	public  void setCurrentUser(User currUser) {
		this.currentUser = currUser;
	}


	public void setCurrentController(AbstractController currObj) {
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
	
	public UserMenu menuSwitcher(JFrame gui)
	{
		switch (App.client.currentUser.getPermission())
		{
		case "USER":
			return new UserMenu(gui);
		case "LIBRARIAN":
			return new LibrarianMenu(gui);
		case "LIBRARY_MANAGER":
			return new ManagerMenu(gui);
		}
		return null;
	}
	

	
	
}
