package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * This class handles the email sends from the server
 * It uses Google Mail API 
 */

public class GoogleMail {
	static Connection conne;
    private static  String FName = null;


	public static int main(int MessageType,String user,Connection conn) {
    	conne=conn;
        final String username = "GoodReadingProj@gmail.com";
        final String password = "goodreadingprojpass";
        String ToSend;
        String Email="";
        String Pass="";
        if(MessageType==1){Email="ishi14199121@gmail.com";}
        if(MessageType==2){
        	Email=GetUserMail(user);
        if(Email=="NO"){return 0;}
        	Pass=GetUserPass(user);}
        if(MessageType==3){
            Email=GetUserMail(user);
            if(Email=="NO"){return 0;}
            Pass=GetUserPass(user);}
        if(MessageType==4){
        	 Email=GetUserMail(user);    
        	 
        }
        if(MessageType==5){
       	 Email=GetUserMail(user);    
       	 
       }
        if(MessageType==6){
       	 Email=GetUserMail(user);    
       	 
       }
        
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("GoodReadingProj@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(Email));
            message.setSubject("GoodReading Notification");
            ToSend=SetText(MessageType,user,Pass);
            //message.setText(ToSend);
            
            //Build HTML MESSSAGE
            
			String HtmlMessage = "";
			HtmlMessage += "<head>";
			HtmlMessage += "<link href=\"https://fonts.googleapis.com/css?family=Roboto\" rel=\"stylesheet\">";
			HtmlMessage += "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/css/bootstrap.min.css\" integrity=\"sha384-AysaV+vQoT3kOAXZkl02PThvDr8HYKPZhNT5h/CXfBThSRXQ6jW5DO2ekP5ViFdi\" crossorigin=\"anonymous\">";
			HtmlMessage += "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js\" integrity=\"sha384-BLiI7JTZm+JWlgKa0M0kGRpJbF2J8q+qreVrKBC47e3K6BW78kGLrCkeRX6I9RoK\" crossorigin=\"anonymous\"></script>";
			HtmlMessage += "<style>";
			HtmlMessage += "body { background-color:#edeef2; }";
			HtmlMessage += "</style>";
			HtmlMessage += "</head>";
			HtmlMessage += "<body>";
			HtmlMessage += "<center>";
			HtmlMessage += "<div style=\"border: 2px solid #dfe1e4; border-radius: 30px; width:600px; margin-top:20px; background-color:#ffffff; padding:60px; \">";
			HtmlMessage += "<img src=\"http://i66.tinypic.com/2gtyae9.jpg\" />";
			HtmlMessage += "</br>";
			HtmlMessage += "<h3 style=\" margin-top:40px; font-family: 'Roboto', sans-serif;\">Hello" + " " + user + "</h3>";
			HtmlMessage += "</br>";
			HtmlMessage += "<h4 style=\"  font-family: 'Roboto', sans-serif;\">If you've lost your password or wish to reset it, use the link below to get started</h4>";
			HtmlMessage += "</br>";
			HtmlMessage += "<a href=\"http://www.google.com\"  style=\"text-decoration: none; color:white; background-color:#5cb85c;  border-radius: 5px; padding:12px;\">Reset Password</a>";
			HtmlMessage += "</br>";
			HtmlMessage += "</br>";
			HtmlMessage += "<h5 style=\"  font-family: 'Roboto', sans-serif;\">If you did not request a password reset, you can safely ignore this email. Only a person with access to your email can reset your account password.</h5>";
			HtmlMessage += "</div>";
			HtmlMessage += "</center>";
			HtmlMessage += "</body>";
			HtmlMessage += "";
			//Build HTML Message END
			
			message.setContent(HtmlMessage,"text/html");
            
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		return 1;
    }
    
	
	/**
	 * This method builds the message to send to the user by the message type it gets
	 * @param Type is the message type
	 * @param User is the user name
	 * @param Pass is the password
	 * @return
	 */
    public static String SetText(int Type,String User,String Pass){
    String message="";	
    	switch(Type)
    	{
    	case 1:{ message="The user " + User + " tried to log in 3 times.\n" + "FYI.\n";break;}
    	case 2 :{ message="Here is your password : " + Pass ;break;}
    	case 3 :{ message="Welcome to a whole new world " + User + " !" + "\nThank you for registering\nWe hope you will enjoy your fresh new box" +  "\nThank You" ;break;}
    	case 4 :{ message="Admin changed the file "+ FName+ " in your BOX.\n\nFYI";break;}
    	case 5 :{ message="Admin approved your request to join/leave the group "+ FName+ " .\n\nFYI";break;}
    	case 6 :{ message="Admin rejected your request to join/leave the group "+ FName+ " .\n\nFYI";break;}
    	}
    return message;	
    }

    
    
    public static void getDBConnection(Connection con)
    {
    	conne = con;	
    }
    
    
    //* This method gets the users email from the DB
    public static String GetUserMail(String User){
        Statement stmt;
  		 String MailReturn="";
  		 
    	try 
    	{
    		stmt = conne.createStatement();
    		 ResultSet result = null; 
    		 
    		 result = stmt.executeQuery("SELECT count(*) FROM users WHERE username = '"+User+"'");  
    		 result.next();
    		 if ( result.getInt(1) == 0) { //If not exists  		   
    		   System.out.println("\nUser Name Not Exists, Try Again\n");
    		   return "NO";
    		 } else { //If  exists
    			
    			result = stmt.executeQuery("SELECT email FROM users WHERE username = '"+User+"'");
   			   while (result.next()) {
			   MailReturn=result.getString("email");}
    			return MailReturn;
    		 }
    	
    		 
    	} catch (SQLException e) {e.printStackTrace(); return "NO";}
    	
  }    
    
   
    //This method gets user's password from DB
    public static String GetUserPass(String User){
        Statement stmt;
  		 String PassReturn="";

        
    	try 
    	{
    		stmt = conne.createStatement();
    		 ResultSet result = null; 
    		 
    		 result = stmt.executeQuery("SELECT count(*) FROM users WHERE username = '"+User+"';"); 
    		 result.next();
    		 if ( result.getInt(1) == 0) { //If not exists  		   
    		   return "NO";
    		 } else { //If  exists
    			 result = stmt.executeQuery("SELECT password FROM users WHERE username = '"+User+"';"); 			
    			 while (result.next()) {
    				 PassReturn=result.getString("password");
    			 }
    		   return PassReturn;
    		 }
    	
    		 
    	} catch (SQLException e) {e.printStackTrace(); return "NO";}
    	
  }    
    
    
}





