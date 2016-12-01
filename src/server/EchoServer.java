package server;



import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ocsf.server.*;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.STRING;
import javax.swing.JOptionPane;


import client.App;
import models.LoginModel;
import models.Envelope;
import models.User;
/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 */
public class EchoServer extends AbstractServer 
{
	private Connection conn;
	private ServerController controller;
	public int LoginCounter=0;
	public String UserNameGlobal;
	ResultSet rs;
	//Class variables *************************************************
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	//Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port) 
	{
		super(port);
	}

	//Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg The message received from the client.
	 * @param client The connection from which the message originated.
	 */

	public void handleMessageFromClient
	(Object msg, ConnectionToClient client)
	{
		User user = null;
		Envelope ne=(Envelope) msg;

		try {
			Statement stmt = conn.createStatement();  
			if(ne.getTask().equals("searchLogin")) //Login
			{
				UserNameGlobal=((LoginModel)ne.getObject()).getUserName();//user name
				System.out.println("Login");

				ResultSet res = stmt.executeQuery("SELECT count(*) FROM users WHERE uname = '"+((LoginModel)ne.getObject()).getUserName()+"';"); //Check If username exists
				res.next();
				if ( res.getInt(1) == 0) { //If not exists  
					System.out.printf("User %s Tried To Log In\n",res.getString(1));
					client.sendToClient("NoUser");}
				else{			  
					ResultSet result = stmt.executeQuery("SELECT COUNT(*) FROM users WHERE uname= '"+((LoginModel)ne.getObject()).getUserName()+"' AND password='"+((LoginModel)ne.getObject()).getPassword()+"';");
					result.next();
					if 
					( 
							result.getInt(1) == 0){ //If not exists   
						client.sendToClient("UserOrPassIncorrect");
					}
					else 
					{ //If  exists
						ArrayList<Object> LoginObj = new ArrayList<Object>();
						LoginObj.add(user);  
						client.sendToClient(LoginObj);
					}
				}

			}//end Login		  


			/**An example for Server communication function
		  if(ne.getTask().equals("OpenFileInClient")){//Send file to client 
			  String fname = ((File)ne.getObject()).getFName();
			  String cl = client.toString();
			  ArrayList<String> ClientHost = new ArrayList<String>(Arrays.asList(((String) cl).split(" ")));  
			  System.out.println(ClientHost.get(0));
			  String ClientToSendoTo=ClientHost.get(0);
			  String ffname = fname.substring(0, fname.indexOf(" -"));
			  SendFileToClient.SimpleFileClient.main(ffname,"",ClientToSendoTo);

			  client.sendToClient("OpenFile");  
		  }
			 */

		} catch (Exception x) {
			JOptionPane.showMessageDialog(null, "Unable to connect to the database", "Connection error", JOptionPane.ERROR_MESSAGE);
		}//outer try catch closed


	}


	

	
	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public void setController(ServerController controller) {
		this.controller = controller;
	}

	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server starts listening for connections.
	 */
	protected void serverStarted()
	{
		System.out.println("Server listening for connections on port " + DEFAULT_PORT);
	}

	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server stops listening for connections.
	 */
	protected void serverStopped()
	{
		System.out.println
		("Server has stopped listening for connections.");
	}


}
//End of EchoServer class   
