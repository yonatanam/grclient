package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.App;

import models.LoginModel;
import models.User;
import models.Envelope;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
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
	public boolean status = true;


	public LoginController (LoginGUI lg,LoginModel lm )
	{
		loginGUI = lg;
		loginModel = lm;
		loginController = this;
		loginGUI.addLoginActionListener(new LoginListener());
		loginGUI.addbtnForgotYourPasswordActionListener(new ForgotPasswordListener());
		loginGUI.addCancelActionListener(new CancelListener());
		//loginG.addbtnChangePasswordActionListener(new changePassListener());

	}



	class CancelListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			loginGUI.dispose();	//Closes the login window
		}	
	}//action



	class ForgotPasswordListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(status)
			{
				loginGUI.displayEmailField();	//Display Send mail field
				status = false;
			}
			else
			{
				loginGUI.disposeEmailField();
				status = true;
			}

			ForgotPasswordController fpc = new ForgotPasswordController(loginGUI);
		}	
	}//action


	class LoginListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{      		
			String password = loginGUI.getPasswordField();	//Gets the password the user entered
			String user = loginGUI.getUserField();		//Gets the user name the user entered
			if(password.equals("")|| user.equals("")) 		//If fields are empty , show error message
			{
				JOptionPane.showMessageDialog(null,"Empty Fields!","Error", JOptionPane.INFORMATION_MESSAGE);    
				return;								//return to the login window
			}//if
			else
			{ 
				try
				{		 
					Map<String, Object> params = new LinkedHashMap<String,Object>();
					params.put("username",  user);
					params.put("password", password);
					params.put("msg",  "LoginOK");
					Envelope envelope = new Envelope(params);
					sendToServer(envelope);
					App.client.setCurrentController(getLoginController()); //Set the controller at Client class to LoginController

				}
				catch(Exception e)
				{
					System.out.println("Exception at sending login user info to server "+ e );
				}
			}//else
		}
	}




	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope) message;
		if (((String)en.getParams().get("msg")).equals("ReviewPopUp"))
			JOptionPane.showMessageDialog(null,"New review was added!");
		String str = (String)en.getParams().get("msg");

		switch (str)
		{
		case "NoSuchUser":
			JOptionPane.showMessageDialog(null,"No such user!","Error", JOptionPane.INFORMATION_MESSAGE);    
			break;
		case "IncorrectPassword":
			int loginAttempts = (Integer)en.getParams().get("login_attempts");
			JOptionPane.showMessageDialog(null,"Password is incorrect, "+loginAttempts+" out of 3 attempts","Error", JOptionPane.INFORMATION_MESSAGE);    
			break;
		case "AlreadyLoggedIn":
			JOptionPane.showMessageDialog(null,"User "+(String)en.getParams().get("username")+" is already logged in!","Error", JOptionPane.INFORMATION_MESSAGE);    
			break;
		case "UserBlocked":
			JOptionPane.showMessageDialog(null,"User "+(String)en.getParams().get("username")+" is blocked!","Error", JOptionPane.INFORMATION_MESSAGE);    
			break;		
		case "UserSuspended":
			JOptionPane.showMessageDialog(null,"User "+(String)en.getParams().get("username")+" is suspended!","Error", JOptionPane.INFORMATION_MESSAGE);    
			break;
		case "BlockUser":
			JOptionPane.showMessageDialog(null,"User "+(String)en.getParams().get("username")+" failed to enter correct password 3 times and is now blocked!","Error", JOptionPane.INFORMATION_MESSAGE);    
			break;
		case "LoginOK":
			loginGUI.dispose();											 //Close login GUI
			//make a switch here with GUIS depending on user level
			User user = new User( (String)en.getParams().get("fname"),
					(String)en.getParams().get("lname"),
					(String)en.getParams().get("username"),
					(String)en.getParams().get("email"),
					(String)en.getParams().get("permission"));
			App.client.setCurrentUser(user);
			/*Change status of user in db*/
			Map<String, Object> params = new LinkedHashMap<String,Object>();
			Envelope envelope = new Envelope(params);
			params.put("username",  user.getUserName());
			params.put("status", "LOGGED_IN");
			params.put("msg",  "UpdateUserLoginStatus");
			sendToServer(envelope);
			/*End change status*/
			System.out.println("User is a "+ en.getParams().get("permission"));
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);
			break;
		}



	}



	public LoginGUI getLoginGUI() {
		return loginGUI;
	}
	public void setLoginGUI(LoginGUI loginG) {
		this.loginGUI = loginG;
	}

	public LoginModel getLoginModel() {
		return loginModel;
	}

	public void setLoginModel(LoginModel loginM) {
		this.loginModel = loginM;
	}


	public LoginController getLoginController() {
		return loginController;
	}

}
