package server;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * This Class is a server Controller 
 *
 */
public class ServerController 
{
	private ServerController serverController;
	private Connection conn; 
	private String username ;
	private String password ;
	private int port = 5555;
	private String database ;
	private String dbname;
	private String host;
	private EchoServer echoServer;

	public ServerController()
	{
		try {
			readProperties();
			System.out.println("Config file loaded successfuly!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Configuration file config.properties not found!");
			System.exit(0);
		}
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
			server.GoogleMail.getDBConnection(conn);
			System.out.println("SQL connection succeed");
			return true;
		} catch (SQLException ex) 
		{/* handle any errors*/
			//TODO change to log4j
			System.out.println("SQL ex"+ ex.getMessage());
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
			System.out.println("Config file not found!");
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



}
