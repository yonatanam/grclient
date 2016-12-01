package server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Hashtable;

import models.User;

/**
 * This Class is a server Controller 
 *
 */
public class ServerController 
{
	private ServerController serverController;
	private Connection conn; 
	private String username = "root";
	private String password = "";
	private int port = 5555;
	private String database = "jdbc:mysql://localhost/test";
	private EchoServer echoServer;

	public ServerController()
	{
		if(openConnectionDB())
		{
			echoServer = new EchoServer(port);
			echoServer.setConn(conn);
			try 
			{
				echoServer.listen(); //Start listening for connections
				echoServer.setController(serverController);

			} 
			catch (Exception ex) 
			{
				//TODO Change this to log4j
				System.out.println("ERROR - Could not listen for clients!");
			}
		}
	}


	/**
	 * openConnectionDB is method that check if the open Connection to DB
	 * @return boolean
	 */
	public boolean openConnectionDB(){
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch (Exception ex) 
		{
			//TODO  handle the error

		}

		try 
		{
			conn = DriverManager.getConnection(database,username,password);
			System.out.println("SQL connection succeed");
			return true;

		} catch (SQLException ex) 
		{/* handle any errors*/

			//TODO change to log4j
			System.out.println("SQL Exception: "+ ex.getMessage());
			return false;
		}

	}
	/************************************************Getters and setters***************************************/

	public void setPassword1(String password1) {
		this.password = password1;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}

}
