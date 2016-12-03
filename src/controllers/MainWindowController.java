package controllers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import client.App;

import models.User;
import gui.MainWindowGUI;
import gui.ReadWorkerGUI;


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
	}
	
	
	/**
	 * Inner class where button Manage Files pressed , implements action listener which opens the manage files window.
	 *
	 */
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
			//ManageGroupsGUI mgg = new ManageGroupsGUI();
			//ManageGroupsController mgc = new ManageGroupsController(user,lc,mgg);

		}
		
	}
	

	
}
