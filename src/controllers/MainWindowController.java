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



/**
 * Main Window Controller , handles all the events in the relevant GUI.
 *
 */
public class MainWindowController extends  AbstractController {
	
	private static User user;
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
		this.user = user;
		this.lc = lc;
		this.mwGui = mwGui;
		tempL = this;
		mwGui.addbtnManageFilesActionListener(new ManageFilesListener());
		mwGui.addbtnManageGroupsActionListener(new ManageGroupListener());
	}
	
	
	/**
	 * Inner class where button Manage Files pressed , implements action listener which opens the manage files window.
	 *
	 */
	class ManageFilesListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Button1");
			mwGui.dispose();
			//ManageFilesGUI mfg = new ManageFilesGUI();
			//ManageFilesController mfc = new ManageFilesController(user,lc,mfg);
		}
	}
	
	
	/**
	 * Inner class where button Manage Groups pressed , implements action listener which opens the manage groups window.
	 *
	 */
	class ManageGroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Button2");
			mwGui.dispose();
			//ManageGroupsGUI mgg = new ManageGroupsGUI();
			//ManageGroupsController mgc = new ManageGroupsController(user,lc,mgg);

		}
		
	}
	

	
}
