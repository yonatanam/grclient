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
		
		String str = (String)en.getParams().get("msg");
		if(str.equals("UserOrPassIncorrect"))			
		{
		/*//TODO Change this to server side
			//if user or password incorrect show warning message 
															//and increase counter by 1
			JOptionPane.showMessageDialog(null,"User Or Password Incorrect!\n" + loginCounter + "(out of 3) attempts","Error", JOptionPane.ERROR_MESSAGE);   
			loginCounter++;
			if (loginCounter==4)								//if user entered wrong details 3 times
			{													//Show warning message, close program and send notification to the administrator
				JOptionPane.showMessageDialog(null,"3rd Login Try\nTerminating!","Error", JOptionPane.ERROR_MESSAGE); 
				loginGUI.dispose();
				//Block user here
				loginCounter=1;							//reset counter	
			}//if counter			
		}//if	 
		*/
		}
		else 
			if (str.equals("LoginOK"))
			{
			loginGUI.dispose();											 //Close login GUI
			//make a switch here with GUIS depending on user level
			System.out.println("User is a "+ en.getParams().get("permission"));
				MainWindowGUI mwg = new MainWindowGUI();
				MainWindowController mwc = new MainWindowController(mwg);

				
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
