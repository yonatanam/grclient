package controllers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import client.App;
import models.Envelope;
import models.User;
import gui.AddBookGUI;
import gui.AddCategoryGUI;
import gui.MainWindowGUI;
import gui.PublishReviewGUI;
import gui.ReadWorkerGUI;
import gui.SearchReviewGUI;
import gui.SettlePaymentGUI;
import gui.WriteWorkerGUI;


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
		mwGui.btnManageBooksActionListener(new ManageBooksListener());
		mwGui.addButtonPublishReviewActionListener(new PublishReviewListener());
		mwGui.addBtnSettlePaymentActionListener(new SettlePaymentListener());
		mwGui.addBtnSearchReviewActionListener(new SearchReviewListener());
		mwGui.addWindowListenerFromController(new CustomWindowListener());
	}
	
	
	
	/**
	 * Inner class where button Manage Files pressed , implements action listener which opens the manage files window.
	 *
	 */
	/*//TODO shouldnt be here
	class CreateNewCategoryActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			mwGui.dispose();
			AddCategoryGUI acg = new AddCategoryGUI();
			CategoryController cc = new CategoryController(acg);
		}
		
	}*/
	
	
	class ManageBooksListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			//mwGui.dispose();
			//TODO ManageBooksGUI mbg = new ManageBooksGUI();
			//BookController abk = new BookController(mbg);
			
		}
	}
	
	// Adding new book to system by the manager
	/*//TODO this should be in ManageBooksCont
	class AddBookListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			mwGui.dispose();
			AddBookGUI adg = new AddBookGUI();
			BookController abk = new BookController(adg);
			
		}
	}*/
	
	
	
	class PublishReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Publish review clicked!");
			mwGui.dispose();
			PublishReviewGUI prg = new PublishReviewGUI();
			PublishReviewController prc = new PublishReviewController(prg);

		}
		
	}
	
	private class SettlePaymentListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			mwGui.dispose();
			new SettlePaymentController(new SettlePaymentGUI());
		}		
	}	

	class SearchReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			mwGui.dispose();
			new SearchReviewController(new SearchReviewGUI());			
		}		
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
