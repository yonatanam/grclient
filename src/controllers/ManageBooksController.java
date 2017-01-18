package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.AddBookGUI;
import gui.AddCategoryGUI;
import gui.AddSubjectGUI;
import gui.MainWindowGUI;
import gui.ManageBooksGUI;

public class ManageBooksController extends AbstractController {
	
	private ManageBooksController manageBooksController;
	private ManageBooksGUI manageBooksGUI;
	
	 public ManageBooksController(ManageBooksGUI mgb)
	 {
		 manageBooksGUI = mgb;
		 manageBooksController = this;
		 
		 mgb.AddbuttonAddBookactionListener(new AddBookActionListener());
		 mgb.AddbuttonAddCategoryactionListener(new AddCategoryActionListener());
		 mgb.AddbuttonAddSubjectactionListener(new AddSubjectActionListener());
	 }
	 
	 
	 class AddBookActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e){
				manageBooksGUI.dispose();
				AddBookGUI adg = new AddBookGUI();
				BookController mwc = new BookController(adg);	
			}
		}
	 
	 class AddCategoryActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e){
				manageBooksGUI.dispose();
				AddCategoryGUI acg = new AddCategoryGUI();
				CategoryController cc = new CategoryController(acg);	
			}
		}
	 
	 
	 class AddSubjectActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e){
				manageBooksGUI.dispose();
				AddSubjectGUI asg = new AddSubjectGUI();
				SubjectController cc = new SubjectController(asg);	
			}
		}

}
