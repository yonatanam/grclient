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

	public void handleMessageFromClient (Object msg, ConnectionToClient client)
	{
		Envelope ne=(Envelope) msg;
		try {
			Statement stmt = conn.createStatement();  
			if (ne.getParams().get("msg").equals("LoginOK")) //Login
			{
				String userName = ne.getParams().get("username");
				String password = ne.getParams().get("password");
				System.out.println("Starting login process");
				ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE username = '"+userName+"' LIMIT 1;"); //Check If username exists
				int rcount = getRowCount(res);
				System.out.println("rcount is "+rcount);
				if (rcount == 0) 
				{ //If not exists  
					System.out.println("User "+userName+" tried to login");
					client.sendToClient("NoSuchUser");
				}
				else
				{			  
					res = stmt.executeQuery("SELECT * FROM users WHERE username='"+userName+"' AND password='"+password+"' LIMIT 1;");
					rcount = getRowCount(res);
					if (rcount == 0) 
						client.sendToClient("UserOrPassIncorrect");
					else 
					{ //If  exists					
						client.sendToClient("LoginOK");
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
	
	 private int getRowCount(ResultSet resultSet) //helper method to see if a select query has any matching rows
	 {
	        if (resultSet == null) {
	            return 0;
	        }
	        try {
	            resultSet.last();
	            return resultSet.getRow();
	        } catch (SQLException exp) {
	            exp.printStackTrace();
	        } finally {
	            try {
	                resultSet.beforeFirst();
	            } catch (SQLException exp) {
	                exp.printStackTrace();
	            }
	        }
	        return 0;
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
