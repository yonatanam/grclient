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
import java.util.Vector;
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
import javax.swing.table.DefaultTableModel;

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
		Map<String,Object> params = new LinkedHashMap<String,Object>();
		Envelope envelope = new Envelope(params);
		Envelope en=(Envelope) msg;
		String message = (String) en.getParams().get("msg");
		try 
		{
			Statement stmt = conn.createStatement();  
			switch (message)
			{
			case "LoginOK":
					String userName = (String) en.getParams().get("username");
					String password = (String) en.getParams().get("password");
					//System.out.println("Starting login process");
					ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE username = '"+userName+"' LIMIT 1;"); //Check If username exists
					int rcount = getRowCount(res);
					//System.out.println("rcount is "+rcount);
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
						{
							client.sendToClient("UserOrPassIncorrect");
							res = stmt.executeQuery("SELECT login_attempts FROM users WHERE username='"+userName+"'");
							if (res.next())
							{
								int currentLoginAttempts = res.getInt("login_attempts");

								stmt.executeUpdate("UPDATE users SET login_attempts="+(currentLoginAttempts+1)+" WHERE username='"+userName+"'");
							}
						}
						else 
						{ //If  user exists	
							/* Get permission level and account status */
							res = stmt.executeQuery("SELECT * from users WHERE username='"+userName+"'");
							if (res.next())
							{
								String permission = res.getString("permission");
								String status = res.getString("status");
								params.put("msg", "LoginOK");
								params.put("permission", permission);
								params.put("username", res.getString("username"));
								params.put("email", res.getString("email"));
								params.put("fname", res.getString("fname"));
								params.put("lname", res.getString("lname"));
								params.put("status",status);
								client.sendToClient(envelope);
							}
						}
					}

				//end Login
				break;
			case "getWorkerData":
				res = stmt.executeQuery("SELECT * from worker");	
				Vector<Object> columnNames = new Vector<Object>();
				Vector<Object> data = new Vector<Object>();
				ResultSetMetaData md = res.getMetaData();
				int columns = md.getColumnCount();

				//  Get column names
				for (int i = 1; i <= columns; i++)
					columnNames.addElement( md.getColumnName(i) );
				//  Get row data

				while (res.next())
				{
					Vector<Object> row = new Vector<Object>(columns);
					for (int i = 1; i <= columns; i++)
					{
						row.addElement( res.getObject(i) );
					}
					data.addElement( row );
				}

				params.put("columns", columnNames);
				params.put("rows", data);
				params.put("msg", "WorkerData");
				client.sendToClient(envelope);
				//End get worker data
				break;
			case "checkWorkerData":
				String wid = (String) en.getParams().get("wid");
				String department = (String) en.getParams().get("dep");
				res = stmt.executeQuery("SELECT * from worker WHERE wid='"+wid+"'");
				rcount = getRowCount(res);
				if (rcount == 0) //If such WID doesn't exist
				{
					client.sendToClient("NoSuchUser");
				}
				else
				{
					stmt.executeUpdate("UPDATE worker SET department='"+department+"' WHERE wid='"+wid+"'");
					client.sendToClient("WorkerUpdatedOK");
				}
				//End update worker data
				break;
			case "AddBook":  // add book to DB handler **NEW HANDLER**				
				String Book_id = (String) en.getParams().get("Book_id");
				String Book_Name = (String) en.getParams().get("Book_Name");
				String Book_lang = (String) en.getParams().get("Book_lang");
				String Book_Format = (String) en.getParams().get("Book_Format");
				String Book_Price = (String) en.getParams().get("Book_Price");

				res = stmt.executeQuery("SELECT * from Books WHERE bookid='"+Book_id+"'");
				rcount = getRowCount(res);
				if (rcount == 0) //If such BID doesn't exist
				{
					stmt.executeUpdate("INSERT into books (bookid, booktitle, booklang, format, price) values ('"+ Book_id +"', '"+ Book_Name +"', '"+ Book_lang +"', '"+ Book_Format +"', '"+ Book_Price +"' )");
					client.sendToClient("BookUpdatedOK");
				}
				else
					client.sendToClient("BookIsInTheDB");
				break;
				// end of add book Handler

			case "getBooksRead":	
				String username = (String) en.getParams().get("username");
				ArrayList<String> bookTitles = new ArrayList<String>();
				res = stmt.executeQuery("SELECT B.booktitle FROM books B, book_orders BO, orders O "
						+ "WHERE B.bookid = BO.bookid AND O.orderid = BO.orderid AND O.username ='"+username+"'");		
				while (res.next())
				{
					String bookTitle = res.getString("booktitle");
					bookTitles.add(bookTitle);
				}
				params.put("msg","GetBooksRead");
				params.put("booktitles", bookTitles);
				client.sendToClient(envelope);

				break;
			case "PublishReview":
				username = (String) en.getParams().get("username");
				String content = (String) en.getParams().get("content");
				String keywords = (String) en.getParams().get("keywords");
				String bookid = null;

				/*Get book id*/
				res = stmt.executeQuery("SELECT bookid from books where booktitle='"+en.getParams().get("booktitle")+"'");	
				while (res.next())
				{
					bookid = res.getString("bookid");
				}
				/*End get book id*/

				/*Check if user hasnt submitted a review for this book yet*/
				res = stmt.executeQuery("SELECT reviewid from reviews where bookid='"+bookid+"' AND username='"+username+"'");	
				if (getRowCount(res) > 0) //a review by this user for this book already exists
				{
					params.put("msg","PublishReviewNOTOK");
					client.sendToClient(envelope);
				}
				/*End duplicate check*/
				else //all good if got here
				{
					java.util.Date dt = new java.util.Date();
					java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String currentTime = sdf.format(dt);
					String query = "INSERT into reviews VALUES (NULL,'"+bookid+"','"+currentTime+"','"+content+"','"
							+username+"','PENDING','"+keywords+"')";
					stmt.executeUpdate(query);
					params.put("msg","PublishReviewOK");
					client.sendToClient(envelope);
				}

				break;
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
