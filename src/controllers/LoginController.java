package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.App;

import models.LoginModel;
import models.User;
//import client.MyBoxApp;
//import client.IObserve;
//import client.CliMessage;
//import models.Envelope;
//import server.ServerGui;
//import server.serverLogGui;
//import view.AdminManageFilesGUI;
//import view.ChangePasswordGUI;
//import view.ExplorerGUI;
//import view.ForgotPasswordGUI;
//import view.MainWindowGUI;
//import view.RegisterGUI;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import gui.LoginGUI;
import gui.MainWindowGUI;


/**
 * This is the login controller, handles all the events on the Login GUI
 *
 */
public class LoginController extends  AbstractController 
{

	private LoginGUI loginGUI;
	private LoginModel loginModel;
	private LoginController loginController;
	private User user;
	private User U;
	public int loginCounter=1;


	public LoginController (LoginGUI lg,LoginModel lm )
	{
		loginGUI = lg;
		loginModel = lm;
		loginController = this;
		loginGUI.addLoginActionListener(new LoginListener());
		//loginG.addbtnForgotYourPasswordActionListener(new forgotPassListener());
		loginGUI.addCancelActionListener(new CancelListener());
		//loginG.addbtnChangePasswordActionListener(new changePassListener());
		
	}
	

	

	
	/*class forgotPassListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ForgotPasswordGUI fpg = new ForgotPasswordGUI(); //Open the forgot password window
			ForgotPasswordController fpc = new ForgotPasswordController(fpg);	
		}
	}*/
	

	 
	class CancelListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
		loginGUI.dispose();	//Closes the login window
		}	
	}//action
	
	
	 
	/*class changePassListener implements ActionListener 
	{
        public void actionPerformed(ActionEvent ev)
        {
        	loginG.dispose(); 								 //Closes the login window
        	ChangePasswordGUI cpg = new ChangePasswordGUI(); //Opens the Change password window
        	ChangePasswordController cpc = new ChangePasswordController(cpg);
        }
	}*/
	
	class LoginListener implements ActionListener
	{
         public void actionPerformed(ActionEvent ev)
         {      		
        	 String pass = loginGUI.getPasswordField();	//Gets the password the user entered
        	 String user = loginGUI.getUserField();		//Gets the user name the user entered
        	 User U = new User(user,null,null,null,null,null);
        	 if(pass.equals("")|| user.equals("")) 		//If fields are empty , show error message
        	 {
        		 JOptionPane.showMessageDialog(null,"Empty Fields!","Error", JOptionPane.INFORMATION_MESSAGE);    
        		 return;								//return to the login window
        	 }//if
        	 else
        	 { 
        		 try
        		 {		 //set the user name and password and send to server
        		   loginModel.setPassword(pass);
        		   loginModel.setUserName(user);
        		   loginGUI.dispose(); //remove login window
        		   
        			
        			MainWindowGUI mwg = new MainWindowGUI();
        			MainWindowController mwc = new MainWindowController(mwg);
        		   //make a switch here with GUIS depending on user level
        		   
        		 }
        		 catch(Exception e){}
		      }//else
          }
	}
	
	

	 
	 /*public void handleDBResult(Object message)
	 {	
		 if(message instanceof ArrayList<?>)					//Get from the server the list of files and folders
		 {
		  FilesInRoot=((ArrayList<String>) message).get(1); 	//String of Files In Root Folder
		  FoldersInRoot=((ArrayList<String>) message).get(2);	//String of Folders In Root Folder
		 }

		 String str = message.toString();
		 String euser = loginG.getUserField();					//save current user name

		 if(str.equals("UserOrPassIncorrect"))					//if user or password incorrect show warning message 
		 {														//and increase counter by 1
			 JOptionPane.showMessageDialog(null,"User Or Password Incorrect!\n" + LoginCounter + "(out of 3) try","Error", JOptionPane.ERROR_MESSAGE);   
			 LoginCounter++;
			 if (LoginCounter==4)								//if user entered wrong details 3 times
			 {													//Show warning message, close program and send notification to the administrator
				 JOptionPane.showMessageDialog(null,"3rd Login Try\nTerminating!","Error", JOptionPane.ERROR_MESSAGE); 
				 loginG.dispose();
				 Envelope en = new Envelope(loginM,"SendMail3Times",euser);
				 sendToServer(en);
				 LoginCounter=1;							//reset counter	
			 }//if counter			
		 }//if	 
		 else
		 {
			 User U = new User (euser,euser,euser, euser, euser, euser); //Set new user instance (only for the user name)
		
			 loginG.dispose();											 //Close login GUI

			 if(euser.equalsIgnoreCase("Admin"))						//Check if the user is Admin
			 {	
				 AdminManageFilesGUI amfg = new AdminManageFilesGUI(FilesInRoot);	 //Open Admin Main window GUI
				 AdminManageFilesController amfc = new AdminManageFilesController(U,amfg,FilesInRoot);
				 JOptionPane.showMessageDialog(null,"You Are Logged As Admin!","Admin", JOptionPane.INFORMATION_MESSAGE); 
			 }//if
			 else
			 {
				 GoHome gh = new GoHome(U);
			 }//else

			
		 } 
		
	 }*/
	 


	public LoginGUI getLoginG() {
		return loginGUI;
	}
	public void setLoginG(LoginGUI loginG) {
		this.loginGUI = loginG;
	}
	
	public LoginModel getLoginM() {
		return loginModel;
	}

	public void setLoginM(LoginModel loginM) {
		this.loginModel = loginM;
	}
	
	
	public LoginController getTempL() {
		return loginController;
	}
		
}
