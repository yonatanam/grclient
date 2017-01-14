package controllers;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private boolean status = true;
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
		mwGui.addWindowListener(new CustomWindowListener());
		mwGui.advancedSearchcActionListener(new advancedSearchListener());
	}
	
	
	class advancedSearchListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(status)
			{
				mwGui.displayAdvancedSearchField();
				status = false;
			}
			else
			{
				mwGui.disposeAdvancedSearchField();
				status = true;
			}

			
		}	
	}//action
	
	
}
