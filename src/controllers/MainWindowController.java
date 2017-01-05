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
		mwGui.addButtonCreateNewCategoryActionListener(new CreateNewCategoryActionListener());
		mwGui.addButtonReadFromWorkerActionListener(new ReadFromWorkerListener());
		mwGui.addButtonWriteToWorkerActionListener(new WriteToWorkerListener());
		mwGui.addButtonAddBookActionListener(new AddBookListener());
		mwGui.addButtonPublishReviewActionListener(new PublishReviewListener());
		mwGui.addBtnSettlePaymentActionListener(new SettlePaymentListener());
		mwGui.addBtnSearchReviewActionListener(new SearchReviewListener());
		mwGui.addWindowListenerFromController(new CustomWindowListener());
	}
	
	
	
	/**
	 * Inner class where button Manage Files pressed , implements action listener which opens the manage files window.
	 *
	 */
	class CreateNewCategoryActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			AddCategoryGUI acg = new AddCategoryGUI();
			CategoryController cc = new CategoryController(acg);
		}
		
	}
	
	
	
	
	// Adding new book to system by the manager
	class AddBookListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			//mwGui.dispose();
			AddBookGUI adg = new AddBookGUI();
			BookController abk = new BookController(adg);
			
		}
	}
	
	
	class ReadFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			mwGui.dispose();
			ReadWorkerGUI rwg = new ReadWorkerGUI();
			ReadWorkerController rwc = new ReadWorkerController(rwg);
		}
	}
	
	
	/**
	 * Inner class where button Manage Groups pressed , implements action listener which opens the manage groups window.
	 *
	 */
	class WriteToWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
			mwGui.dispose();
			WriteWorkerGUI wwg = new WriteWorkerGUI();
			WriteWorkerController rwc = new WriteWorkerController(wwg);

		}
		
	}
	
	class PublishReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
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
	    	  System.out.println("Window opened");

	    	  
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
