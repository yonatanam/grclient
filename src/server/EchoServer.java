package server;



import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
		System.out.println("Send to server was initiated");
		Envelope en=(Envelope) msg;
		String message = (String) en.getParams().get("msg");
		try 
		{
			Statement stmt = conn.createStatement();  
			if (message.equals("LoginOK")) //Login
			{
				String userName = (String) en.getParams().get("username");
				String password = (String) en.getParams().get("password");
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
			else
				if ( message.equals("getWorkerData"))
				{
					Map<String,Object> params = new LinkedHashMap<String,Object>();
					Envelope envelope = new Envelope(params);
					ResultSet res = stmt.executeQuery("SELECT * from worker");	
					List<HashMap<String,Object>> res2 = convertResultSetToList(res);
					params.put("data", res2);
					params.put("msg", "WorkerData");
					//Map<String, Object> test = new HashMap<String,Object>();
					client.sendToClient(envelope);
				}

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



	public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException  //Helper class to convert resultset to List since resultset isnt serializable
	{
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
		while (rs.next())
		{
			HashMap<String,Object> row = new HashMap<String, Object>(columns);
			for(int i=1; i<=columns; ++i) 
			{
				row.put(md.getColumnName(i),rs.getObject(i));
			}
			list.add(row);
		}
		return list;
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
