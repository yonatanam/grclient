package server;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.JTextArea;

import controllers.ShoppingCartController;
import gui.ServerLogGUI;


/**
 * This Class is a server Controller 
 *
 */
public class ServerController 
{
	private ServerController serverController;
	private Connection conn; 
	private String username="root" ;
	private String password="root" ;
	private int port = 5555;
	private String database ;
	private String dbname="grproj";
	private String host="localhost";
	private EchoServer echoServer;
	private String filesDir;
    private String openedtime;
	
	public ServerController()
	{
		
		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_h_mm_ss");
    	openedtime = sdf.format(date);
    	setFilesDir();
	}
	public void disconnect()
	{
		if(echoServer.isListening())
		{
		try {
			echoServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("zdfgzdfg");
		}
	}
	public boolean  Connect() 
	{
		database = "jdbc:mysql://"+host+"/"+dbname;
		if(openConnectionDB())
		{
			echoServer = new EchoServer(port);
			echoServer.setConn(conn);
			try 
			{
				echoServer.listen(); //Start listening for connections
				echoServer.setController(this);
				return true;
			} 
			catch (Exception ex) 
			{
				//TODO Change this to log4j
				Print("ERROR - Could not listen for clients!");
				
			}
		}
		return false;
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
			server.GoogleMail.getDBConnection(conn);
			Print("SQL connection succeed");
			return true;
		} catch (SQLException ex) 
		{/* handle any errors*/
			//TODO change to log4j
			Print("SQL ex"+ ex.getMessage());
			return false;
		}
	}
	/************************************************Getters and setters***************************************/

	public void setFilesDir()
	{
		try 
		{
			filesDir = ShoppingCartController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "Books/";
		} 
		catch (URISyntaxException e1) { ServerLogGUI.Print("Files directory not found!");	}
	}
	
	public void setPassword1(String password1) {
		this.password = password1;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public void setDbName(String DBname) {
		this.dbname = DBname;
	}
	public void setHostName(String HostName) {
		this.host = HostName;
	}
	
	public String getFilesDir() 
	{
		return filesDir;
	}
	
	public static void Print(String msg)
	{
		 ServerLogGUI.Print(msg);	
		 
	}
	public void readProperties() throws IOException
	{


		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// set properties values
			username = prop.getProperty("dbuser");
			password = prop.getProperty("dbpassword");
			dbname = prop.getProperty("dbname");
			host = prop.getProperty("host");
			database = "jdbc:mysql://"+host+"/"+dbname;

		} catch (IOException ex) {
			Print("Config file not found!");
			System.exit(0);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}

    public void SaveLog(JTextArea textArea) 
    {
    	
    	FileWriter fw;
    	
		try {
			fw = new FileWriter("Log_"+openedtime+".txt");
			PrintWriter print=new PrintWriter(fw);
	    	print.println(textArea.getText());
	    	fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
    		
			
    		
    }
    	
        
       
         

          

}
