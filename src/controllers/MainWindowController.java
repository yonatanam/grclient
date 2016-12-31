package controllers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import client.App;

import models.User;
import gui.AddBookGUI;
import gui.MainWindowGUI;
import gui.PublishReviewGUI;
import gui.ReadWorkerGUI;
import gui.WriteWorkerGUI;


/**
 * Main Window Controller , handles all the events in the relevant GUI.
 *
 */
public class MainWindowController extends  AbstractController {
	
	private LoginController lc;
	private MainWindowGUI mwGui;
	private MainWindowController tempL;

	
	/**
	 * This is constructor of the main window, construct all the listeners and attributes.
	 * @param user
	 * @param lc
	 * @param mwGui
	 */
	public MainWindowController(MainWindowGUI mwGui) {
		this.mwGui = mwGui;
		tempL = this;
		mwGui.addButtonReadFromWorkerActionListener(new ReadFromWorkerListener());
		mwGui.addButtonWriteToWorkerActionListener(new WriteToWorkerListener());
		mwGui.addButtonAddBookActionListener(new AddBookListener());
		mwGui.addButtonPublishReviewActionListener(new PublishReviewListener());
	}
	
	
	/**
	 * Inner class where button Manage Files pressed , implements action listener which opens the manage files window.
	 *
	 */
	
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
	

	
}
