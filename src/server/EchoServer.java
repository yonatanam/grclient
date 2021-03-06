package server;



import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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
import gui.SearchType;
import models.LoginModel;
import models.Book;
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
	private static final String Controller = null;

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
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		String formattedDate = sdf.format(date);
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
					ServerController.Print("User "+userName+" tried to login");
					params.put("msg", "NoSuchUser");
					client.sendToClient(envelope);
					break;
				}
				else
				{		//User exists, check if blocked/suspended 
					res.next();
					String status = res.getString("status");
					//status = ""; ///////////////////////////////////////////////////
					if (status.equals("LOGGED_IN"))
					{
						params.put("msg", "AlreadyLoggedIn");
						ServerController.Print("User "+userName+" tried to login while already being logged in");
						client.sendToClient(envelope);
						break;
					}
					if (status.equals("SUSPENDED"))
					{
						params.put("msg", "UserSuspended");
						ServerController.Print("User "+userName+" is suspended and tried to login");
						client.sendToClient(envelope);
						break;
					}
					if (status.equals("BLOCKED"))
					{
						params.put("msg", "UserBlocked");
						ServerController.Print("User "+userName+" is blocked and tried to login");
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
					ServerController.Print("Mail was sent!");
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
				//String Book_Format = (String) en.getParams().get("Book_Format");
				String Book_Price = (String) en.getParams().get("Book_Price");
				String Book_inCatalog = (String) en.getParams().get("Book_inCatalog");
				String Book_Synopsis = (String) en.getParams().get("Book_Synopsis");
				String Book_Keywords = (String) en.getParams().get("Book_Keywords");
				String Book_TOC = (String) en.getParams().get("Book_TOC");

				// adding this information to diffrent *TABLE* (authors and book_authors id DB)
				String Book_Author_id = (String) en.getParams().get("Book_Author_id");
				String Book_Author_name = (String) en.getParams().get("Book_Author_name");

				// adding this information to diffrent *TABLE* (Book_categories DB)
				//String Book_Category = (String) en.getParams().get("Book_Category");
				//String Book_Subject = (String) en.getParams().get("Book_Subject");
				ArrayList<String> subcat = (ArrayList<String>) en.getParams().get("Book_subcat");
				res = stmt.executeQuery("SELECT * from authors WHERE authorid='"+Book_Author_id+"'");
				rcount = getRowCount(res);
				if (rcount == 0) //If such author id doesn't exist
					stmt.executeUpdate("INSERT into authors (authorid, authorname) values ('"+ Book_Author_id +"', '"+ Book_Author_name +"')");


				res = stmt.executeQuery("SELECT * from Books WHERE bookid='"+Book_id+"'");
				rcount = getRowCount(res);
				if (rcount == 0) //If such BID doesn't exist
				{
					//stmt.executeUpdate("INSERT into books (bookid, booktitle, booklang, synopsis, toc, keywords, format, incatalog, price) values ('"+ Book_id +"', '"+ Book_Name +"', '"+ Book_lang +"', '"+ Book_Synopsis +"', '"+ Book_TOC +"', '"+ Book_Keywords +"', '"+ Book_Format +"', '"+ Book_inCatalog +"', '"+ Book_Price +"' )");
					stmt.executeUpdate("INSERT into books (bookid, booktitle, booklang, synopsis, toc, keywords, incatalog, price) values ('"+ Book_id +"', '"+ Book_Name +"', '"+ Book_lang +"', '"+ Book_Synopsis +"', '"+ Book_TOC +"', '"+ Book_Keywords +"', '"+ Book_inCatalog +"', '"+ Book_Price +"' )");
					// inserting the data into the book_authors TABLE in db
					stmt.executeUpdate("INSERT into book_authors (bookid, authorid) values ('"+ Book_id +"', '"+ Book_Author_id +"')");

					/*
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
					}*/
					// We need to extract from the arraylist the subject and the category using split
					for(int i = 0 ; i < subcat.size() ; i++)
					{
						String[] temp = subcat.get(i).split("->");
						res = stmt.executeQuery("SELECT categoryid FROM categories WHERE category_name = '"+temp[1]+"'");
						res.next();
						String temp_id_cat = res.getString("categoryid");
						res = stmt.executeQuery("SELECT DISTINCT subjectid FROM subjects WHERE subjectname = '"+temp[0]+"'");
						res.next();
						String temp_id_sub = res.getString("subjectid");
						//inserting all the data to book_categories in db
						stmt.executeUpdate("INSERT into book_categories (bookid, categoryid, subjectid) values ('"+Book_id+"', '"+temp_id_cat+"', '"+temp_id_sub+"')"); 

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
				
					query = "INSERT into reviews VALUES (NULL,'"+bookid+"','"+formattedDate+"','"+content+"','"
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
							ServerController.Print("Thread id: "+ x.getId());
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

				/*----------------------------Downloads ----------------------------*/ 	
			case "DownloadBooks":
				ArrayList<Book> books = (ArrayList<Book>)en.getParams().get("books");
				if (books.size() == 0)
				{
					params.put("msg","DownloadFailed");
					client.sendToClient(envelope);
					break;
				}
				Vector<String> filesNames = new Vector<String>();
				Vector<File> files = new Vector<File>();
				Vector<byte[]> bytesOfFiles = new Vector<byte[]>();

				FileInputStream fis = null;
				String filesDirectory = controller.getFilesDir();
				boolean valid = true;
				int j=0;
				for (Book b : books)
				{
					filesNames.add(b.getBooktitle()+"."+b.getFormat());
					files.add(new File(filesDirectory + filesNames.get(j)));
					j++;
				}

				for(j = 0; j < files.size(); j++)
				{
					fis = new FileInputStream(files.get(j));
					byte[] temp = new byte[(int) files.get(j).length()];
					fis.read(temp);
					fis.close();
					bytesOfFiles.add(temp);
				}
				params.put("bytesOfFiles", bytesOfFiles);			
				params.put("filesNames", filesNames); 
				params.put("msg","DownloadApproved"); 



				//Generete order ID and Date
				String[] UID = UUID.randomUUID().toString().split("-", 2);
				String uniqueID = UID[0];
				userName = (String) en.getParams().get("username");
				//Insert order into DB
				query = "INSERT INTO orders VALUES ('"+uniqueID+"','"+userName+"','"+formattedDate+"')";
				stmt.executeUpdate(query);
				for (Book b : books)
				{
					query = "INSERT INTO book_orders VALUES ('"+uniqueID+"','"+b.getBookid()+"')";
					stmt.executeUpdate(query);
				}
				client.sendToClient(envelope);			
				break;
				/*----------------------------End Of Downloads----------------------------*/					


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


				/*----------------------------Cancel Subscription----------------------------*/ 	
			case "cancelSubscription":
				username = (String)en.getParams().get("username");

				query = "SELECT username FROM accounts WHERE username = '" + username + "'";
				res = stmt.executeQuery(query);
				if(getRowCount(res) == 1)
				{
					stmt.executeUpdate("DELETE from accounts WHERE username='"+username+"'");
					params.put("msg","CancelSubscriptionOK");
				}
				else
					params.put("msg","CancelSubscriptionFailed");

				client.sendToClient(envelope);
				break;
				/*----------------------------End Of Cancel Subscription----------------------------*/


			case "CreateNewCategory":      // insert new category for the library
				String CatName = (String) en.getParams().get("CatName");
				// check if there is a Category name in db with the same CatName
				res = stmt.executeQuery("SELECT category_name from categories where category_name = '"+ CatName +"'");
				if(getRowCount(res) == 0)
				{
					stmt.executeUpdate("INSERT into categories (category_name) values ('"+ CatName +"')");
					params.put("msg", "CreateNewCategoryOK");
					client.sendToClient(envelope);
				}
				else if(getRowCount(res) > 0)
				{
					params.put("msg", "CategoryidExist");
					client.sendToClient(envelope);
				}
				break;
			case "CreateNewSubject":		// insert new subject for the library
				String SubName = (String) en.getParams().get("SubName");
				String[] SubCat = (String[])en.getParams().get("SubCat");
				String CategoryId = null;
				String SubjectId = null;
				ArrayList<String> temp = new ArrayList<String>();

				int flag = 0;

				for(int i = 0 ; i < SubCat.length; i++) // for every category that the user inserted we check for duplicates
				{
					res = stmt.executeQuery("SELECT categoryid FROM categories WHERE category_name = '" +SubCat[i]+ "'");
					while(res.next()) // insert the answer to CategoryId
						CategoryId = res.getString("categoryid");

					res = stmt.executeQuery("SELECT subjectid FROM subjects WHERE subjectname = '"+ SubName +"'");

					if(getRowCount(res) == 0) // check if there is a subject name in db and if there is we will get the subject id
					{ // this is new subject, otherwise we would find it's name in other rows with it's id
						stmt.executeUpdate("INSERT into subjects (categoryid, subjectname) values ('"+CategoryId+"', '"+SubName+"')");
						flag = 1;
					}
					else
					{
						res.next();
						SubjectId = res.getString("subjectid");
						// now we will have to check duplicates of rows - can't be in the same category 2 subjects for example

						res = stmt.executeQuery("SELECT categoryid, subjectid FROM subjects WHERE categoryid = '"+ CategoryId +"' AND subjectid = '"+ SubjectId +"'" );

						if(getRowCount(res) == 0) // check if there is row with the same category id and subject id.... it means 2 subjects in the same category
						{ 
							stmt.executeUpdate("INSERT into subjects (categoryid, subjectid, subjectname) values ('"+CategoryId+"', '"+SubjectId+"', '"+SubName+"')");
							flag = 1;

						}
						else
						{     // there is subject name affiliated to this category
							flag = 0;
							temp.add(SubCat[i]);
						}		
					}
				}

				if(flag == 1)
				{
					params.put("msg", "CreateNewSubjectOK");
					client.sendToClient(envelope);
				}
				else
				{
					params.put("msg", "Subject_nameExist");
					params.put("CatBadId", temp);
					client.sendToClient(envelope);
				}
				break;
				case "editsubject":      // edit subject name in the DB
				String oldsub=(String) en.getParams().get("oldsub");
	   			String oldcategory=(String) en.getParams().get("oldcategory");
	   			String newcategory=(String) en.getParams().get("newcategory");
	   			String newsub=(String) en.getParams().get("newsub");
	   			String newcategoryid = null;
	   			String oldcategoryid = null;
	   			res = stmt.executeQuery("SELECT * from categories where category_name = '"+ oldcategory +"'"
						+ "or category_name ='"+ newcategory +"'");
				boolean equalcat=oldcategory.equals(newcategory);
				if(getRowCount(res) ==0) 
				{
					ServerController.Print("error occured during edit subject ,check the subjects table subject name="+
							oldsub+" category name="+oldcategory);
					params.clear();
					params.put("msg", "editsubjectERROR");
					client.sendToClient(envelope);
				}
				else
				{
					if(!equalcat)
					{
					while(res.next())
					{	if(res.getString("category_name").equals(oldcategory)) oldcategoryid=Integer.toString(res.getInt("categoryid"));
						else newcategoryid=Integer.toString(res.getInt("categoryid"));
					}
					res = stmt.executeQuery("SELECT * from subjects where categoryid = '"+ newcategoryid +"'"
							+ "and subjectname ='"+ newsub +"'");
				    if(getRowCount(res)>0)
				    {
				    	params.clear();
						params.put("newsub", newsub);
						params.put("newcategory", newcategory);
						params.put("msg", "subjectalreadyexist");
						client.sendToClient(envelope);
						break;
				    }
					stmt.executeUpdate("UPDATE subjects SET categoryid='"+newcategoryid+"',subjectname='"+newsub+"'"
							+ " WHERE categoryid='"+oldcategoryid+"' and subjectname='"+oldsub+"' ");
					res = stmt.executeQuery("SELECT subjectid from subjects where subjectname = '"+ newsub +"'"
							+ "and categoryid ='"+ newcategoryid +"'");
					if(getRowCount(res) ==0) 
					{
						ServerController.Print("error occured during edit subject ,check the subjects table subject name="+
								newsub+" category id="+newcategoryid);
						params.clear();
						params.put("msg", "editsubjectERROR");
						client.sendToClient(envelope);
						
					}
					else
					{
					res.next();
					String subid=Integer.toString(res.getInt("subjectid"));
					stmt.executeUpdate("UPDATE book_categories SET categoryid='"+newcategoryid+"'"
							+ " WHERE subjectid='"+subid+"' and categoryid='"+oldcategoryid+"' ");
					}
					}
					else 
						{
						res.next();
						oldcategoryid=Integer.toString(res.getInt("categoryid"));
						res = stmt.executeQuery("SELECT * from subjects where categoryid = '"+ oldcategoryid +"'"
								+ "and subjectname ='"+ newsub +"'");
					    if(getRowCount(res)>0)
					    {
					    	params.clear();
							params.put("newsub", newsub);
							params.put("newcategory", newcategory);
							params.put("msg", "subjectalreadyexist");
							client.sendToClient(envelope);
							break;
					    }
						
						stmt.executeUpdate("UPDATE subjects SET subjectname='"+newsub+"'"
							+ " WHERE categoryid='"+oldcategoryid+"' and subjectname='"+oldsub+"' ");
						}
					if(!oldsub.equals(newsub))
					ServerController.Print("subject "+oldsub+"has been changed to "+newsub);
					if(!equalcat)
						ServerController.Print("subject "+newsub+"rebind to category "+newcategory);
					params.clear();
					params.put("msg", "editsubjectOK");
					client.sendToClient(envelope);
				}
								
				break;
			case "GetDisSubjects":
				ArrayList<String>	subjects = new ArrayList<String>();				
				res = stmt.executeQuery("SELECT DISTINCT subjectname FROM subjects");
				while(res.next())
					subjects.add(res.getString("subjectname"));
				params.clear();
				params.put("msg", "AlldisSubjectsInDB");
				params.put("subjects", subjects);
				client.sendToClient(envelope);
				break;
				
			case "EditCategory":      // edit category name in the DB
				
				
				 CatName = (String) en.getParams().get("CatName");
				 String Exist = (String) en.getParams().get("existName");

				res = stmt.executeQuery("SELECT categoryid from categories where category_name = '"+ CatName +"'");
				
				if(getRowCount(res) == 0) // checking if there are any categories with the same name 
					
				{		res = stmt.executeQuery("SELECT categoryid from categories where category_name = '"+ Exist +"'");
						res.next();
						stmt.executeUpdate("UPDATE categories SET category_name='"+CatName+"' WHERE categoryid='"+Integer.toString(res.getInt("categoryid"))+"'");
						ServerController.Print("category "+Exist+"has been changed to"+CatName);
						params.put("msg", "CategoryHasBeenChanged");
						client.sendToClient(envelope);				
				}
				else 
				{
					params.clear();
					params.put("msg", "CategoryidExist");
					client.sendToClient(envelope);
				}				
				break;
			case "RemoveCategory":      // edit category name in the DB
				
				
				 CatName = (String) en.getParams().get("CatName");
				 res = stmt.executeQuery("SELECT categoryid from categories where category_name = '"+ CatName +"'");
				res.next();
				String categoryid=Integer.toString(res.getInt("categoryid"));
				stmt.executeUpdate("DELETE from categories WHERE category_name='"+CatName+"'");
				stmt.executeUpdate("DELETE from subjects WHERE categoryid='"+categoryid+"'");
				stmt.executeUpdate("DELETE from book_categories WHERE categoryid='"+categoryid+"'");
				params.clear();
				 params.put("msg", "CategoryRemoved");
				 client.sendToClient(envelope);
								
				break;
			case "RemoveSubjects":      // edit category name in the DB
				
				
				SubName = (String) en.getParams().get("SubName");
				CatName = (String) en.getParams().get("CatName");
				res = stmt.executeQuery("SELECT categoryid from categories where category_name = '"+ CatName +"'");
				res.next();		
				String categoryid1=Integer.toString(res.getInt("categoryid"));
				try{
					res = stmt.executeQuery("SELECT subjectid from subjects where categoryid='"+categoryid1+"'"
							+ " and subjectname='"+ SubName +"'");
					res.next();
					String subjectid1=Integer.toString(res.getInt("subjectid"));
					stmt.executeUpdate("DELETE from subjects WHERE categoryid='"+categoryid1+"' and "
							+ "subjectname='"+ SubName +"'");
					stmt.executeUpdate("DELETE from book_categories WHERE categoryid='"+categoryid1+"' and "
							+ "subjectid='"+subjectid1+"'");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				
				
				 params.clear();
				 params.put("msg", "SubjectRemoved");
				 client.sendToClient(envelope);
								
				break;
			case "GetSubjects":
				 subjects = new ArrayList<String>();
				String CategoryName = (String) en.getParams().get("CategoryName");
				String Category_id = null;

				//getting the id of the category, then check which subjects affiliated to this category

				ResultSet res4 = stmt.executeQuery("SELECT categoryid FROM categories WHERE category_name = '"+CategoryName+"'");
				while(res4.next())
					Category_id = res4.getString("categoryid");

				res4 = stmt.executeQuery("SELECT subjectname FROM subjects WHERE categoryid = '"+Category_id+"'");
				//adding all subjects affiliated to this category

				while(res4.next())
					subjects.add(res4.getString("subjectname"));


				params.put("msg", "AllSubjectsInDB");
				params.put("subjects", subjects);
				client.sendToClient(envelope);

				break;
			case "GetrelevantCat":
				ArrayList<String> categories = new ArrayList<String>();
				 String s=(String) en.getParams().get("subject");
				 res = stmt.executeQuery("SELECT c.category_name  FROM grproj.subjects s,grproj.categories c where s.subjectname='"+ s +"' and s.categoryid=c.categoryid ");
				while(res.next())
					categories.add(res.getString("category_name"));
				params.put("incategories", categories);
				ArrayList<String> outcategories = new ArrayList<String>();
				res = stmt.executeQuery("SELECT category_name from categories where category_name not in (SELECT c.category_name  FROM grproj.subjects s,grproj.categories c where s.subjectname='"+ s +"' and s.categoryid=c.categoryid) ");
				while(res.next())
					outcategories.add(res.getString("category_name"));
				params.put("outcategories", outcategories);
				params.put("msg", "relevantCat");
				client.sendToClient(envelope);
				break;
			case "GetCategories":
				 categories = new ArrayList<String>();

				ResultSet res5 = stmt.executeQuery("SELECT category_name from categories");
				while(res5.next())
					categories.add(res5.getString("category_name"));

				params.put("msg", "AllCategoriesInDB");
				params.put("categories", categories);
				client.sendToClient(envelope);
				break;
			case "GetCategoriesAtLeast1":	 /* this case is for BookController to show only categories with at 
			 	least 1 subject affiliated.
			   (you cannot addbook without subject and category affiliation)*/
				ArrayList<String> categories2 = new ArrayList<String>();
				ArrayList<String> id = new ArrayList<String>();
				ResultSet res6 = stmt.executeQuery("SELECT DISTINCT categoryid FROM subjects");
				while(res6.next())
					id.add(res6.getString("categoryid"));

				for(int i = 0 ; i < id.size() ;  i++)
				{
					res6 = stmt.executeQuery("SELECT category_name FROM categories WHERE categoryid='"+id.get(i)+"'");
					res6.next();
					categories2.add(res6.getString("category_name"));
				}
				params.put("msg", "AllCategoriesInAtLeast1DB");
				params.put("categories", categories2);
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
			case "getPendingSubscription":
				res = stmt.executeQuery("SELECT * from accounts WHERE status='PENDING'");	
				Vector<Object> subscriptionColumnNames = new Vector<Object>();
				Vector<Object> subscriptionData = new Vector<Object>();
				ResultSetMetaData smd = res.getMetaData();
				int subscriptionColumnCount = smd.getColumnCount();

				//  Get column names
				for (int i = 1; i <= subscriptionColumnCount; i++)
					subscriptionColumnNames.addElement( smd.getColumnName(i) );
				//  Get row data

				while (res.next())
				{
					Vector<Object> row = new Vector<Object>(subscriptionColumnCount);
					for (int i = 1; i <= subscriptionColumnCount; i++)
					{
						row.addElement( res.getObject(i) );
					}
					subscriptionData.addElement( row );
				}

				params.put("subscriptionColumnNames", subscriptionColumnNames);
				params.put("subscriptionData", subscriptionData);
				params.put("msg", "SubscriptionData");
				client.sendToClient(envelope);
				//End get worker data
				break;
			case "approve_subscription":
				String subUserName = (String)en.getParams().get("username");
				stmt.executeUpdate("UPDATE accounts SET status='APPROVED' WHERE username='"+subUserName+"'");
				break;
			case "deny_subscription":
				subUserName = (String)en.getParams().get("username");
				stmt.executeUpdate("DELETE from accounts WHERE username='"+subUserName+"'");
				break;
			case "getSimpleSearchData":
				/**Simple search via booktitle only*/
				Statement stmt2 = conn.createStatement();
				SearchType t = (SearchType)en.getParams().get("searchby");
				ResultSet ressearch = null;
				switch(t)
				{
				case Any_Type:
					int orflag=0;
					Map<String,String> arr=(Map<String,String>)en.getParams().get("data");
					String bigquery="SELECT distinct bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books where ( ";
					if(arr.containsKey("synopsis"))
					{
						bigquery=bigquery+" (synopsis like '%"+arr.get("synopsis")+"%') ";
						orflag=1;
					}
					if(arr.containsKey("category"))
					{
						String[] tempcat=arr.get("category").split(";");
						String tempcat1=" (select categoryid from categories where category_name like '%"+tempcat[0]+"%'";
						for(int i=1;i<tempcat.length;i++)
							tempcat1=tempcat1+ " or category_name like '%"+tempcat[i]+"%'";					
						tempcat1=tempcat1+")";  					
					tempcat1="(select distinct bookid from book_categories where categoryid in "+ tempcat1+" )";
					if(orflag==1)
						bigquery=bigquery+" or ";
					bigquery=bigquery+" (bookid in "+tempcat1+")";
					orflag=1;
					
					}
					if(arr.containsKey("language"))
					{
						String[] templang=arr.get("language").split(";");
						if(orflag==1)
							bigquery=bigquery+" or (booklang like '%"+templang[0]+"%')";
						else bigquery=bigquery+" (booklang like '%"+templang[0]+"%')";
						for(int i=1;i<templang.length;i++)
							bigquery=bigquery+ " or (booklang like '%"+templang[i]+"%')";		
						orflag=1;
					}
					if(arr.containsKey("toc"))
					{
						if(orflag==1)
							bigquery=bigquery+" or ";
						bigquery=bigquery+" (toc like '%"+arr.get("toc")+"%')";
						orflag=1;
					}
					if(arr.containsKey("author"))
					{
						String[] tempauthor=arr.get("author").split(";");
						String tempauthor1=" (select authorid from authors where authorname like '%"+tempauthor[0]+"%'";
						for(int i=1;i<tempauthor.length;i++)
							tempauthor1=tempauthor1+ " or authorname like '%"+tempauthor[i]+"%'";					
						tempauthor1=tempauthor1+")";  					
						tempauthor1="(select distinct bookid from book_authors where authorid in "+ tempauthor1+" )";
						if(orflag==1)
							bigquery=bigquery+" or ";
						bigquery=bigquery+" (bookid in "+tempauthor1+")";				
						orflag=1;
					}
					if(arr.containsKey("keywords"))
					{
						String[]	tempkeyword=arr.get("keywords").split(";");	
						if(orflag==1)
							bigquery=bigquery+" or ";
						 bigquery=bigquery +" keywords like '%;"+tempkeyword[0]+";%'";
						for(int i=1;i<tempkeyword.length;i++)
							bigquery=bigquery+ " or (keywords like '%;"+tempkeyword[i]+";%')";		
												
					}
					bigquery=bigquery+ " ) and incatalog='YES'";
					try{
					ressearch=stmt.executeQuery(bigquery);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					break;	
				case By_Category:	
					ArrayList<String> many=(ArrayList<String>)en.getParams().get("data");
					String tempcat=" (select categoryid from categories where category_name like '%"+many.get(0)+"%'";
					for(int i=1;i<many.size();i++)
						tempcat=tempcat+ " or category_name like '%"+many.get(i)+"%'";					
					tempcat=tempcat+")";  					
				tempcat="(select distinct bookid from book_categories where categoryid in "+ tempcat+" )";
				tempcat="SELECT bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books WHERE incatalog='YES' and bookid in "+tempcat;
				ressearch=stmt.executeQuery(tempcat);
					break;			
				case By_Name:	
					String bookName = (String)en.getParams().get("data");
					ressearch = stmt.executeQuery("SELECT bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books WHERE booktitle like '%"+bookName+"%' AND incatalog='YES'");
					break;
				case By_Author:	
					 many=(ArrayList<String>)en.getParams().get("data");
					String tempauthor=" (select authorid from authors where authorname like '%"+many.get(0)+"%'";
					for(int i=1;i<many.size();i++)
						tempauthor=tempauthor+ " or authorname like '%"+many.get(i)+"%'";					
					tempauthor=tempauthor+")";  					
					tempauthor="(select distinct bookid from book_authors where authorid in "+ tempauthor+" )";
				
					tempauthor="SELECT bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books WHERE incatalog='YES' and bookid in "+tempauthor;
				
					ressearch=stmt.executeQuery(tempauthor);
					break;
					
				case By_Synop:		
					String syn = (String)en.getParams().get("data");
					ressearch = stmt.executeQuery("SELECT bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books WHERE synopsis like '%"+syn+"%' AND incatalog='YES'");
					break;
				case By_Content:
					String toc = (String)en.getParams().get("data");
					ressearch = stmt.executeQuery("SELECT bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books WHERE toc like '%"+toc+"%' AND incatalog='YES'");
					break;
				case By_Lang:
					 many=(ArrayList<String>)en.getParams().get("data");
						String templang="SELECT bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books WHERE ( booklang like '%"+many.get(0)+"%'";
						for(int i=1;i<many.size();i++)
							templang=templang+ " or booklang like '%"+many.get(i)+"%'";		
						templang=templang+ ") and incatalog='YES'";						
					ressearch=stmt.executeQuery(templang);
					
					break;
				case By_KeyWords:					
					 many=(ArrayList<String>)en.getParams().get("data");
					String tempkey="SELECT bookid,booktitle, booklang,synopsis,toc,keywords,format,price  from books WHERE ( keywords like '%;"+many.get(0)+";%'";
					for(int i=1;i<many.size();i++)
						tempkey=tempkey+ " or keywords like '%;"+many.get(i)+";%'";		
					tempkey=tempkey+ ") and incatalog='YES'";
				ressearch=stmt.executeQuery(tempkey);
					
					break;
				}
		     	
		     	
				Vector<Object> booksColumnNames = new Vector<Object>();
				Vector<Object> booksData = new Vector<Object>();
				ResultSetMetaData bsd = ressearch.getMetaData();
				int booksColumnCount = bsd.getColumnCount();
				for (int i = 1; i <= booksColumnCount; i++)
					booksColumnNames.addElement( bsd.getColumnName(i) );
				while (ressearch.next())
				{
					if((Integer)en.getParams().get("inc")==1)
					{
					String sql = "INSERT INTO book_searches VALUES ('"+ressearch.getString("bookid")+"','"+formattedDate+"')";
					stmt2.executeUpdate(sql);
					}
					Vector<Object> row = new Vector<Object>(booksColumnCount);
					for (int i = 1; i <= booksColumnCount; i++)
					{
						row.addElement( ressearch.getObject(i) );
						//TODO Add matching books to book searches
					}
					booksData.addElement( row );
				}

				params.put("booksColumnNames", booksColumnNames);
				params.put("booksData", booksData);
				params.put("msg", "BooksData");
				client.sendToClient(envelope);
		     	

				break;
			case "getUserOrdersData":
				String ordersUserName = (String) en.getParams().get("username");
				res = stmt.executeQuery("SELECT orderid, date FROM orders where username='"+ordersUserName+"'");
				Vector<Object> ordersColumnNames = new Vector<Object>();
				Vector<Object> ordersData = new Vector<Object>();
				ResultSetMetaData ormd = res.getMetaData();
				int ordersColumnCount = ormd.getColumnCount();
				for (int i = 1; i <= ordersColumnCount; i++)
					ordersColumnNames.addElement( ormd.getColumnName(i) );
				while (res.next())
				{
					Vector<Object> row = new Vector<Object>(ordersColumnCount);
					for (int i = 1; i <= ordersColumnCount; i++)
					{
						row.addElement( res.getObject(i) );
					}
					ordersData.addElement( row );
				}
				params.put("ordersColumnNames", ordersColumnNames);
				params.put("ordersData", ordersData);
				params.put("msg", "UserOrdersData");
				client.sendToClient(envelope);
				break;
			case "getOrderBooksData":
				String orderID = (String) en.getParams().get("orderid");
				String query1 = "SELECT B.booktitle FROM book_orders BO, books B WHERE BO.bookid = B.bookid AND BO.orderid='"+orderID+"'";
				res = stmt.executeQuery(query1);
				Vector<Object> booksColumnNames1 = new Vector<Object>();
				Vector<Object> booksData1 = new Vector<Object>();
				ResultSetMetaData bomd = res.getMetaData();
				int booksColumnCount1 = bomd.getColumnCount();
				for (int i = 1; i <= booksColumnCount1; i++)
					booksColumnNames1.addElement( bomd.getColumnName(i) );
				while (res.next())
				{
					Vector<Object> row = new Vector<Object>(booksColumnCount1);
					for (int i = 1; i <= booksColumnCount1; i++)
					{
						row.addElement( res.getObject(i) );
					}
					booksData1.addElement( row );
				}
				params.put("booksColumnNames", booksColumnNames1);
				params.put("booksData", booksData1);
				params.put("msg", "OrderBooksData");
				client.sendToClient(envelope);
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
		ServerController.Print("Server listening for connections on port " + DEFAULT_PORT);
	}

	/**
	 * This method overrides the one in the superclass.  Called
	 * when the server stops listening for connections.
	 */
	protected void serverStopped()
	{
		ServerController.Print("Server has stopped listening for connections.");
	}



}
//End of EchoServer class 