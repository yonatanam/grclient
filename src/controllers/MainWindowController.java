package controllers;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedHashMap;
import java.util.Map;



import client.App;
import models.Envelope;

import gui.MainWindowGUI;



/**
 * Main Window Controller , handles all the events in the relevant GUI.
 *
 */
public class MainWindowController extends  AbstractController {
	
	private LoginController lc;
	private MainWindowGUI mwGui;
	private MainWindowController tempL;
	public static final long DEFAULT_THREAD = 0;

	
	/**
	 * This is constructor of the main window, construct all the listeners and attributes.
	 * @param user
	 * @param lc
	 * @param mwGui
	 */
	public MainWindowController(MainWindowGUI mwGui) {
		this.mwGui = mwGui;
		tempL = this;		
	}
	
				
	
	class CustomWindowListener implements WindowListener {	      
	      public void windowClosing(WindowEvent e) {    
				Map<String, Object> params = new LinkedHashMap<String,Object>();
				Envelope envelope = new Envelope(params);			
				params.put("username",  App.client.getCurrentUser().getUserName());
				params.put("status", "ACTIVE");
				params.put("threadnum", DEFAULT_THREAD);
				params.put("msg",  "UpdateUserLoginStatus");
				sendToServer(envelope);					
	    	  System.out.println("CLOSING");
	      }	      
	      public void windowClosed(WindowEvent e) {   	  
	      }
	      public void windowOpened(WindowEvent e) {	    	  
	      }
	      public void windowIconified(WindowEvent e) {
	      }
	      public void windowDeiconified(WindowEvent e) {
	      }
	      public void windowActivated(WindowEvent e) {
	      }
	      public void windowDeactivated(WindowEvent e) {
	      }
	   }   	
	
}
