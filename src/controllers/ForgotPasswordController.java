package controllers;
import models.Envelope;
import server.GoogleMail;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import client.App;
import gui.LoginGUI;
import controllers.LoginController.LoginListener;
import controllers.LoginController.ForgotPasswordListener;
import models.Envelope;
import models.LoginModel;
import models.User;

/**
 * Controller of the forgot password event, handle all the events on the relavent GUI
 *
 */
public class ForgotPasswordController extends  AbstractController {
	
	private LoginGUI FG;
	private String Uname;
	private LoginGUI fpGui;
	private ForgotPasswordController fpc;
	
	/**
	 * This is the constructor of the class, construct the listeners and attributes of the GUI.
	 * @param fpg
	 */
	public ForgotPasswordController(LoginGUI fpg) {
		this.fpc=this;
		FG=fpg;
		fpGui=fpg;
		fpGui.addRecoverMyPasswordActionListener(new RecoverMyPasswordListener());
	}
	
	
	/**
	 * Inner class where button Recover My Password pressed , implements action listener which send the user his password to his mail.
	 *
	 */
	class RecoverMyPasswordListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
	
  		  String user = FG.ForgotPasswordUserField();
  		  System.out.println("This is the user name:" + user );
  		try
		{		 
			Map<String, Object> params = new LinkedHashMap<String,Object>();
			params.put("username",  user);
			params.put("password", "124");
			params.put("msg",  "ForgotPassword");
			Envelope envelope = new Envelope(params);
			sendToServer(envelope);
			
		}
		catch(Exception e1)
		{
		System.out.println("Exception at sending login user info to server "+ e1 );
		}
  		  
  		  
  		  
  		 //Envelope en = new Envelope("","ForgotPassword",user);
		 //sendToServer(en);	
			App.client.setCurrentController(fpc);
			}

		}
	
	
	
	public void handleDBResult(Object message)
	{
		
		fpGui.dispose(); 
		
	}

	}
	
	
	
	
	
	
	

