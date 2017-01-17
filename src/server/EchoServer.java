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
import controllers.Functions;
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
		String query = null;
		String searchExp = null;
		try 
		{
			Statement stmt = conn.createStatement();  
			switch (message)
			{
			case "LoginOK":
				String userName = (String) en.getParams().get("username");
				String password = (String) en.getParams().get("password");
				params.put("username", userName);
				ResultSet res = stmt.executeQuery("SELECT * FROM users WHERE username = '"+userName+"' LIMIT 1;"); //Check If username exists
				int rcount = getRowCount(res);
				if (rcount == 0) 
				{ //If not exists  
					controller.Print("User "+userName+" tried to login");
					params.put("msg", "NoSuchUser");
					client.sendToClient(envelope);
					break;
				}
				else
				{		//User exists, check if blocked/suspended 
					res.next();
					String status = res.getString("status");
					status = ""; ///////////////////////////////////////////////////
					if (status.equals("LOGGED_IN"))
					{
						params.put("msg", "AlreadyLoggedIn");
						controller.Print("User "+userName+" tried to login while already being logged in");
						client.sendToClient(envelope);
						break;
					}
					if (status.equals("SUSPENDED"))
					{
						params.put("msg", "UserSuspended");
						controller.Print("User "+userName+" is suspended and tried to login");
						client.sendToClient(envelope);
						break;
					}
					if (status.equals("BLOCKED"))
					{
						params.put("msg", "UserBlocked");
						controller.Print("User "+userName+" is blocked and tried to login");
						client.sendToClient(envelope);
						break;
					}
					//Check if password is valid
					res = stmt.executeQuery("SELECT * FROM users WHERE username='"+userName+"' AND password='"+password+"' LIMIT 1;");
					rcount = getRowCount(res);
					if (rcount == 0) 
					{
						res = stmt.executeQuery("SELECT login_attempts FROM users WHERE username='"+userName+"'");
						res.next();
						int currentLoginAttempts = res.getInt("login_attempts");
						params.put("msg", "IncorrectPassword");
						params.put("login_attempts", currentLoginAttempts+1);
						client.sendToClient(envelope);
						if (currentLoginAttempts +1 >=3)
						{
							//block user
							stmt.executeUpdate("UPDATE users SET status='BLOCKED', login_attempts=0 WHERE username='"+userName+"'");
							Map<String,Object> params2 = new LinkedHashMap<String,Object>();	
							Envelope en2 = new Envelope(params2);
							params2.put("username", userName);
							params2.put("msg", "BlockUser");
							client.sendToClient(en2);
							break;
						}
						stmt.executeUpdate("UPDATE users SET login_attempts="+(currentLoginAttempts+1)+" WHERE username='"+userName+"'");

					}
					else 
					{ //If  user exists	
						/* Get permission level and account status */
						res = stmt.executeQuery("SELECT * from users WHERE username='"+userName+"'");
						if (res.next())
						{

							String permission = res.getString("permission");
							params.put("msg", "LoginOK");
							params.put("permission", permission);
							params.put("username", res.getString("username"));
							params.put("email", res.getString("email"));
							params.put("fname", res.getString("fname"));
							params.put("lname", res.getString("lname"));
							params.put("status",status);
							client.sendToClient(envelope);
							stmt.executeUpdate("UPDATE users SET login_attempts=0 where userName='"+userName+"'");
						}
					}
				}

				//end Login
				break;

			case "ForgotPassword":

				String user = (String) en.getParams().get("username");
				if(server.GoogleMail.main(2,user,conn)==1)
					//client.sendToClient("ForgotPassword");
					controller.Print("Mail was sent!");
				else
					client.sendToClient("ForgotPasswordFalse");	
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
				String Book_inCatalog = (String) en.getParams().get("Book_inCatalog");
				String Book_Synopsis = (String) en.getParams().get("Book_Synopsis");
				String Book_Keywords = (String) en.getParams().get("Book_Keywords");
				String Book_TOC = (String) en.getParams().get("Book_TOC");

				// adding this information to diffrent *TABLE* (authors and book_authors id DB)
				String Book_Author_id = (String) en.getParams().get("Book_Author_id");
				String Book_Author_name = (String) en.getParams().get("Book_Author_name");

				// adding this information to diffrent *TABLE* (Book_categories DB)
				String Book_Category = (String) en.getParams().get("Book_Category");
				String Book_Subject = (String) en.getParams().get("Book_Subject");

				res = stmt.executeQuery("SELECT * from authors WHERE authorid='"+Book_Author_id+"'");
				rcount = getRowCount(res);
				if (rcount == 0) //If such author id doesn't exist
					stmt.executeUpdate("INSERT into authors (authorid, authorname) values ('"+ Book_Author_id +"', '"+ Book_Author_name +"')");


				res = stmt.executeQuery("SELECT * from Books WHERE bookid='"+Book_id+"'");
				rcount = getRowCount(res);
				if (rcount == 0) //If such BID doesn't exist
				{
					stmt.executeUpdate("INSERT into books (bookid, booktitle, booklang, synopsis, toc, keywords, format, incatalog, price) values ('"+ Book_id +"', '"+ Book_Name +"', '"+ Book_lang +"', '"+ Book_Synopsis +"', '"+ Book_TOC +"', '"+ Book_Keywords +"', '"+ Book_Format +"', '"+ Book_inCatalog +"', '"+ Book_Price +"' )");
					// inserting the data into the book_authors TABLE in db
					stmt.executeUpdate("INSERT into book_authors (bookid, authorid) values ('"+ Book_id +"', '"+ Book_Author_id +"')");

					res = stmt.executeQuery("SELECT categoryid from categories WHERE category_name='"+Book_Category+"'");	
					rcount = getRowCount(res);
					if (rcount > 0)
					{
						String categoryid = null;
						while(res.next())									
							categoryid = res.getString("categoryid");
						res = stmt.executeQuery("SELECT subjectid from subjects WHERE subjectname='"+Book_Subject+"'");
						rcount = getRowCount(res);
						if (rcount > 0)
						{
							String subjectid = null;
							while(res.next())
								subjectid = res.getString("subjectid");
							stmt.executeUpdate("INSERT into book_categories values ('"+ Book_id +"', '"+ categoryid +"', '"+ subjectid +"')");
						}
					}

					client.sendToClient("BookInsertedOK");
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
					query = "INSERT into reviews VALUES (NULL,'"+bookid+"','"+currentTime+"','"+content+"','"
							+username+"','PENDING')";
					stmt.executeUpdate(query);

					/*Send notification regarding new review*/
					Thread[] clientThreadList = getClientConnections();
					Map<String,Object> params2 = new LinkedHashMap<String,Object>();	
					Envelope envelope2 = new Envelope(params2);
					params2.put("msg", "ReviewPopUp");
					query = "SELECT threadnum from users WHERE status='LOGGED_IN' AND threadnum <> 0 AND (permission='LIBRARIAN' OR "
							+ "permission='LIBRARY_MANAGER')";
					res = stmt.executeQuery(query);

					//
					while (res.next())
					{
						long threadnum = res.getLong("threadnum");
						for (Thread x : clientThreadList)
						{
							controller.Print("Thread id: "+ x.getId());
							if (x.getId() == threadnum)
								((ConnectionToClient)x).sendToClient(envelope2);
						}
					}
					/*Thread[] clientThreadList = getClientConnections();
					for (Thread x : clientThreadList)
					((ConnectionToClient)x).sendToClient(envelope2);*/

					//((ConnectionToClient)clientThreadList[0]).sendToClient("ReviewPopUp");
					/*End notification*/
					params.put("msg","PublishReviewOK");
					client.sendToClient(envelope);
				}

				break;
			case "UpdateUserLoginStatus":
				userName = (String)en.getParams().get("username");

				long threadnum = getThreadNum();
				String status = (String)en.getParams().get("status");
				if (status.equals("ACTIVE"))
					threadnum=0;
				query = "UPDATE users SET STATUS='"+status+"' , threadnum="+threadnum+" WHERE username='"+userName+"'";
				stmt.executeUpdate(query);
				break;

				/*----------------------------Search Review----------------------------*/ 
			case "SearchReview":	

				String searchType = (String)en.getParams().get("searchType");
				String searchText = (String)en.getParams().get("searchText");
				searchExp = Functions.getSearchExp(searchText);
				switch(searchType)
				{				
				case "Book's name":											
					query = "SELECT R.username, R.date, R.content FROM reviews AS R, books AS B WHERE R.status = 'APPROVED' AND R.bookid = B.bookid AND B.booktitle REGEXP '" + searchExp + "'";
					break;
				case "Book's writer":	
					query = "SELECT DISTINCT R.username, R.date, R.content FROM reviews AS R, books AS B, authors AS A, book_authors as B_A WHERE R.status = 'APPROVED' AND R.bookid = B.bookid AND A.authorid = B_A.authorid AND A.authorname REGEXP '" + searchExp + "'";
					break;
				case "Key words":
					query = "SELECT DISTINCT R.username, R.date, R.content FROM reviews AS R, books AS B, authors AS A WHERE R.status = 'APPROVED' AND R.bookid = B.bookid AND B.keywords REGEXP '" + searchExp + "'";				
					break;
				}

				res = stmt.executeQuery(query);
				if(getRowCount(res) == 0)
					params.put("msg","SearchReviewResultsEmpty");
				else
				{
					md = res.getMetaData();
					columns = md.getColumnCount();
					data = new Vector<Object>();
					columnNames = new Vector<Object>();

					//Get columns names
					for (int i = 1; i <= columns; i++)
						columnNames.addElement(md.getColumnName(i));
					//Get rows
					while (res.next())
					{
						Vector<Object> row = new Vector<Object>(columns);
						for (int i = 1; i <= columns; i++)
							row.addElement(res.getObject(i));
						data.addElement(row);
					}

					params.put("msg","SearchReviewResults");
					params.put("columnNames",columnNames);
					params.put("data",data);
				}
				client.sendToClient(envelope);
				break;	
				/*----------------------------Search Review----------------------------*/ 

				/**----------------------------Get Subscriptions Names----------------------------*/ 	
			case "getSubscriptionsNames":
				query = "SELECT name, description FROM subscriptions";
				res = stmt.executeQuery(query);
				md = res.getMetaData();
				columns = md.getColumnCount();
				data = new Vector<Object>();
				//Get rows
				while (res.next())
				{
					Vector<Object> row = new Vector<Object>(columns);
					for (int i = 1; i <= columns; i++)
						row.addElement(res.getObject(i));
					data.addElement(row);
				}
				params.put("msg","getSubscriptionsNames");
				params.put("data",data);
				client.sendToClient(envelope);
				break;
				/*----------------------------End of Get Subscriptions Names----------------------------*/ 
			
				/*----------------------------Settle Payment----------------------------*/ 	
			case "submitSettlePayment":
				username = (String)en.getParams().get("username");
				String cardNumber = (String)en.getParams().get("cardNumber");
				String ccv = (String)en.getParams().get("ccv");
				String expirationDate = (String)en.getParams().get("expirationDate");
				int subscriptionType = (int)en.getParams().get("subscriptionType");
				String today = (String)en.getParams().get("commencementOfSubscription");			


				query = "SELECT username FROM accounts WHERE username = '" + username + "'";
				res = stmt.executeQuery(query);

				if(getRowCount(res) == 0)
				{
					query = "INSERT into accounts (username, creditcard, creditdate, ccv, subscription_type, creation_date) values ('"+ username + "','" + cardNumber + "','" + expirationDate + "','" + ccv + "','" + subscriptionType + "','" + today + "')";
					params.put("msg","SettlePaymentSucceeded");
					stmt.executeUpdate(query);
				}
				else
					params.put("msg","SettlePaymentFalied");

				client.sendToClient(envelope);
				break;
				/*----------------------------End Of Settle Payment----------------------------*/	
				
				/*----------------------------Settle Payment----------------------------*/ 	
			case "DownloadBooks":
				Vector<String> bookID = new Vector<String>();
				Vector<String> filesNames = new Vector<String>();
				Vector<File> files = new Vector<File>();
				Vector<byte[]> bytesOfFiles = new Vector<byte[]>();
		       
				FileInputStream fis = null;
				String filesDirectory = controller.getFilesDir();
								
		        bookID.add("6");
		        bookID.add("7");
		        bookID.add("8");

		        boolean valid = true;
		        
				for(int i = 0; i < bookID.size() && valid; i++)		
				{
					query = "SELECT booktitle,format FROM books WHERE bookid = '" + bookID.get(i) + "'";
					res = stmt.executeQuery(query);
					
					if(getRowCount(res) != 0)
					{
						res.next();
						filesNames.add(res.getString("booktitle") + "." + res.getString("format"));
						files.add(new File(filesDirectory + filesNames.get(i)));	
					}
					else
					{
						params.put("msg","DownloadFailed");
						valid = false;
					}
				}
				
		        if(valid)
		        {	
		        	for(int i = 0; i < files.size(); i++)
		        	{
		        		fis = new FileInputStream(files.get(i));
		        		byte[] temp = new byte[(int) files.get(i).length()];
		                fis.read(temp);
		                fis.close();
		        		bytesOfFiles.add(temp);
		        	}
		        	params.put("bytesOfFiles", bytesOfFiles);			
		    		params.put("filesNames", filesNames); 
					params.put("msg","DownloadApproved"); 
		        }
		        
				client.sendToClient(envelope);			
				break;
				/*----------------------------End Of Settle Payment----------------------------*/					
				
				
				/*----------------------------Create Account----------------------------*/ 	
			case "createAccount":
				username = (String)en.getParams().get("UserName");
				password = (String)en.getParams().get("Password");
				String email = (String)en.getParams().get("Email");
				String firstname = (String)en.getParams().get("FirstName");
				String lastname = (String)en.getParams().get("LastName");
				String permission = (String)en.getParams().get("Permission");
				int loginattempts = (int)en.getParams().get("login_attempts");
				status = (String)en.getParams().get("status");
				threadnum = 0;
						
				
				query = "SELECT username FROM users WHERE username = '" + username + "'";
				res = stmt.executeQuery(query);
				if(getRowCount(res) == 0)
				{
					stmt.executeUpdate("INSERT into users (username,password,fname,lname,email,permission,login_attempts,status,threadnum) values ('"+ username +"','"+ password +"','"+ firstname +"','"+ lastname +"','"+ email +"','"+ permission +"','"+ loginattempts +"','"+ status +"','"+ threadnum +"')");
					params.put("msg","CreateAccountOK");
				}
				else
					params.put("msg","CreateAccountFailed");
				
				client.sendToClient(envelope);
				break;
				/*----------------------------End Of Create Account----------------------------*/
				
				
			case "CreateNewCategory":      // insert new category for the library
				//System.out.println("in here!!!");
				String CatId = (String) en.getParams().get("CatId");
				String CatName = (String) en.getParams().get("CatName");

				res = stmt.executeQuery("SELECT categoryid from categories where categoryid = '"+ CatId +"'");


				if(getRowCount(res) == 0) // checking if there are any categories with the same name or id
				{
					res = stmt.executeQuery("SELECT category_name from categories where category_name = '"+ CatName +"'");
					if(getRowCount(res) == 0)
					{
						stmt.executeUpdate("INSERT into categories (categoryid, category_name) values ('"+ CatId +"', '"+ CatName +"')");
						params.put("msg", "CreateNewCategoryOK");
						client.sendToClient(envelope);
					}
				}
				else if(getRowCount(res) > 0)
				{
					params.put("msg", "CategoryidExist");
					client.sendToClient(envelope);
				}
				else
				{
					res = stmt.executeQuery("SELECT category_name from categories where category_name = '"+ CatName +"'");
					if(getRowCount(res) > 0)
					{
						params.put("msg", "Category_nameExist");
						client.sendToClient(envelope);
					}
				}
				break;
			case "CreateNewSubject":		// insert new subject for the library
				String SubId = (String) en.getParams().get("SubId");
				String SubName = (String) en.getParams().get("SubName");

				res = stmt.executeQuery("SELECT subjectid from subjects where subjectid = '"+ SubId +"'");


				if(getRowCount(res) == 0) // checking if there are any categories with the same name or id
				{
					res = stmt.executeQuery("SELECT subjectname from subjects where subjectname = '"+ SubName +"'");
					if(getRowCount(res) == 0)
					{
						stmt.executeUpdate("INSERT into subjects (subjectid, subjectname) values ('"+ SubId +"', '"+ SubName +"')");
						params.put("msg", "CreateNewSubjectOK");
						client.sendToClient(envelope);
					}
				}
				else if(getRowCount(res) > 0)
				{
					params.put("msg", "SubjectidExist");
					client.sendToClient(envelope);
				}
				else
				{
					res = stmt.executeQuery("SELECT subjectname from subjects where subjectname = '"+ SubName +"'");
					if(getRowCount(res) > 0)
					{
						params.put("msg", "Subject_nameExist");
						client.sendToClient(envelope);
					}
				}
				break;
			case "GetSubjects":
				ArrayList<String> subjects = new ArrayList<String>();

				ResultSet res4 = stmt.executeQuery("SELECT subjectname from subjects");
				while(res4.next())
					subjects.add(res4.getString("subjectname"));

				params.put("msg", "AllSubjectsInDB");
				params.put("subjects", subjects);
				client.sendToClient(envelope);
				break;
			case "GetCategories":
				ArrayList<String> categories = new ArrayList<String>();

				ResultSet res5 = stmt.executeQuery("SELECT category_name from categories");
				while(res5.next())
					categories.add(res5.getString("category_name"));

				params.put("msg", "AllCategoriesInDB");
				params.put("categories", categories);
				client.sendToClient(envelope);
				break;
			case "getPendingReviews":
				res = stmt.executeQuery("SELECT * from reviews WHERE status='PENDING'");	
				Vector<Object> reviewsColumnNames = new Vector<Object>();
				Vector<Object> reviewsData = new Vector<Object>();
				ResultSetMetaData rmd = res.getMetaData();
				int reviewColumnCount = rmd.getColumnCount();

				//  Get column names
				for (int i = 1; i <= reviewColumnCount; i++)
					reviewsColumnNames.addElement( rmd.getColumnName(i) );
				//  Get row data

				while (res.next())
				{
					Vector<Object> row = new Vector<Object>(reviewColumnCount);
					for (int i = 1; i <= reviewColumnCount; i++)
					{
						row.addElement( res.getObject(i) );
					}
					reviewsData.addElement( row );
				}

				params.put("reviewsColumnNames", reviewsColumnNames);
				params.put("reviewsData", reviewsData);
				params.put("msg", "ReviewData");
				client.sendToClient(envelope);
				//End get worker data
				break;
			case "approveReview":
				int reviewID = (Integer)en.getParams().get("reviewid");
				stmt.executeUpdate("UPDATE reviews SET status='APPROVED' WHERE reviewid="+reviewID);
				break;
			case "denyReview":
				reviewID = (Integer)en.getParams().get("reviewid");
				stmt.executeUpdate("UPDATE reviews SET status='REJECTED' WHERE reviewid="+reviewID);
				break;
			case "editReview":
				try
				{
					String newReviewText = (String) en.getParams().get("reviewcontent");
					reviewID = (Integer)en.getParams().get("reviewid");
					stmt.executeUpdate("UPDATE reviews SET content='"+newReviewText+"' WHERE reviewid="+reviewID);
					params.put("msg", "editReviewOK");
					client.sendToClient(envelope);
				}
				catch (Exception ex)
				{
					params.put("msg", "editReviewNOTOK");
					client.sendToClient(envelope);	
				}
				break;
			case "getUsersData":
				res = stmt.executeQuery("SELECT username,password,fname,lname,email,permission,status from users");	
				Vector<Object> usersColumnNames = new Vector<Object>();
				Vector<Object> usersData = new Vector<Object>();
				ResultSetMetaData rmd2 = res.getMetaData();
				int usersColumnCount = rmd2.getColumnCount();
				for (int i = 1; i <= usersColumnCount; i++)
					usersColumnNames.addElement( rmd2.getColumnName(i) );
				while (res.next())
				{
					Vector<Object> row = new Vector<Object>(usersColumnCount);
					for (int i = 1; i <= usersColumnCount; i++)
					{
						row.addElement( res.getObject(i) );
					}
					usersData.addElement( row );
				}
				params.put("usersColumnNames", usersColumnNames);
				params.put("usersData", usersData);
				params.put("msg", "UsersData");
				client.sendToClient(envelope);
				break;
				/**End get users data*/
			case "changeUserData":
				User newUserData = (User)en.getParams().get("user");
				try
				{
					stmt.executeUpdate("UPDATE users SET "
							+ "password='"+newUserData.getPassword()+"', "
							+ "fname='"+newUserData.getFirstName()+"', "
							+ "lname='"+newUserData.getLastName()+"', "
							+ "email='"+newUserData.getEmail()+"', "
							+ "permission='"+newUserData.getPermission()+"', "
							+ "status='"+newUserData.getStatus()+"' WHERE username='"+newUserData.getUserName()+"'");
					params.put("msg", "editUserOK");
					client.sendToClient(envelope);

				}
				catch (Exception ex)
				{
					params.put("msg", "editUserNOTOK");
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
		controller.Print("Server listening for connections on port " + DEFAULT_PORT);
	}

	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server stops listening for connections.
	 */
	protected void serverStopped()
	{
		controller.Print("Server has stopped listening for connections.");
	}



}
//End of EchoServer class 