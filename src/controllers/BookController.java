package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JOptionPane;

import client.App;

import java.util.Map;


import gui.AddBookGUI;
import gui.MainWindowGUI;
import gui.ManageBooksGUI;
import models.Envelope;


// this class contains all book functionality
// add to this class all the relevant data you need

public class BookController extends AbstractController{
	
	private BookController bookController;
	private AddBookGUI addBookGUI;
	private int flag_id = 0;
	private int flag_name = 0;
	private int flag_price = 0;
	private int flag_authorid = 0;
	private int flag_authorname = 0;
	private int flag_categoryANDsubject = 0;
	private Validate_textFields val;
	
	public BookController(AddBookGUI abg){  // add your GUI functionality here ('AddBook' is one of them)
		addBookGUI = abg;
		bookController = this;
		
		// getting the *categories* that are in the db
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "GetCategoriesAtLeast1");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(bookController);
		sendToServer(envelope);	

		abg.AddTextBookIdMouseListener(new TextBookIdMouseListener());
		abg.AddTextBookNameMouseListener(new TextBookNameMouseListener());
		abg.AddTextPriceMouseListener(new TextPriceMouseListener());
		abg.AddTextAuthoridMouseListener(new TextAuthoridMouseListener());
		abg.AddTextAuthorNameMouseListener(new TextAuthorNameMouseListener());
		abg.AddbuttonApplyactionListener(new buttonApplyActionListener());
		abg.addButtonCancelFromAddBookActionListener(new CancelFromReadFromWorkerListener());
		abg.addWindowListener(new CustomWindowListener());
		abg.AddCategoryComboItemListener(new CategoryComboItemListener());
		abg.addButtonremCategoryFromListActionListener(new remCategoryFromListActionListener());
		abg.addButtonAddCategoryToListActionListener(new addCategoryFromListActionListener());
	}
	
	public class addCategoryFromListActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			int flag = 1;
			
			// this block of code checks if there is a category in the array list.
			// if there is ==> we won't add it to the array list because book cannot affiliated 
			// to 2 same categories.
			
			
			String temp[];
			for(int i = 0; i < addBookGUI.getBook_Sub_Cat().size() ; i++)
			{
				if(addBookGUI.getBook_Sub_Cat().get(i).contains((String)addBookGUI.getCategory_combobox().getSelectedItem()))
					flag = 0;			
			}
			
			if(flag == 1)
			{
				addBookGUI.getBook_Sub_Cat().add((String)addBookGUI.getSubject_combobox().getSelectedItem() + "->" + (String)addBookGUI.getCategory_combobox().getSelectedItem());
				addBookGUI.getNoAddedCatSubWarning().setForeground(Color.red);
				addBookGUI.getNoAddedCatSubWarning().setText("");
			}
				
			else
				addBookGUI.getNoAddedCatSubWarning().setText("No same books in same category");
			
			
			
			temp = new String[addBookGUI.getBook_Sub_Cat().size()];
			for(int i = 0 ; i < temp.length; i++)
				temp[i] = addBookGUI.getBook_Sub_Cat().get(i);
			
			addBookGUI.getCategory_SubjectList().setListData(temp);            
			// TODO -- NEED TO ARRAY LIST , AND CHECK FOR DUPLICATE STRINGS IN ARRAY
			// TODO -- NEED CHANGE ECHO SERVER -- BUILD ITERATION -- NUM OF CATEGORIES GET IN THE 
			// TODO -- JLIST. DONT FORGET TO SAVE ARRAYLIST AND STRING[] IN ADDBOOKGUI
			
		}
		
	}
	
	
	public class remCategoryFromListActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(addBookGUI.getCategory_SubjectList().getSelectedIndex() == -1)
			{
				//TODO - dont need to
			}
			else
			{
				addBookGUI.getBook_Sub_Cat().remove(addBookGUI.getCategory_SubjectList().getSelectedIndex());
				String[] temp = new String[addBookGUI.getBook_Sub_Cat().size()];
				for(int i = 0 ; i < temp.length; i++)
					temp[i] = addBookGUI.getBook_Sub_Cat().get(i);
				
				addBookGUI.getCategory_SubjectList().setListData(temp);
			}
		}
		
	}
	
	
	
	public class CategoryComboItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange() == ItemEvent.SELECTED)
			{
				String CategoryName = (String)addBookGUI.getCategory_combobox().getSelectedItem();
				// getting the *subjects* which in this category
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("msg", "GetSubjects");
				params.put("CategoryName", CategoryName);
				Envelope envelope2 = new Envelope(params);
				App.client.setCurrentController(bookController);
				sendToServer(envelope2);	
			}
			
		}
		
	}
	
	
	public class TextBookIdMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addBookGUI.SetBookIdText("");
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
	}
	
	public class TextBookNameMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addBookGUI.SetBookNameText("");
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
	}
	
	public class TextPriceMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addBookGUI.getPrice().setText("");
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
	}
	
	public class TextAuthoridMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addBookGUI.getTxtBookAuthorid().setText("");
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
	}
	
	public class TextAuthorNameMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addBookGUI.getTxtBookAuthorname().setText("");
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
	}
	
	
	
	// ------------- when user click the apply button to insert the new book to the DB ---------- //
	
	public class buttonApplyActionListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			addBookGUI.getBidWarningLabel().setText("");
			addBookGUI.getBNameWarningLabel().setText("");
			addBookGUI.getBAuthornameWarningLabel().setText("");
			addBookGUI.getBAuthoridWarningLabel().setText("");
			addBookGUI.getBpriceWarningLabel().setText("");
			addBookGUI.getNoAddedCatSubWarning().setText("");
			
			// Checking relevant fields that the user must fill in
			
			if((addBookGUI.getBookIdText()).getText().equals(""))
			{
				(addBookGUI.getBidWarningLabel()).setForeground(Color.red);
				(addBookGUI.getBidWarningLabel()).setText("You must fill Book Id");
				flag_id = 0;
			}
			else if(!(addBookGUI.getBookIdText()).getText().matches("[0-9]+"))
			{
				(addBookGUI.getBidWarningLabel()).setForeground(Color.red);
				(addBookGUI.getBidWarningLabel()).setText("Wrong input! only numbers");
				flag_id = 0;
			}
			else
				flag_id = 1;
				
			if((addBookGUI.getBookNameText()).getText().equals(""))
			{
				(addBookGUI.getBNameWarningLabel()).setForeground(Color.red);
				(addBookGUI.getBNameWarningLabel()).setText("You must fill Book Name");
				flag_name = 0;
			}
			else
				flag_name = 1;
			
			if((addBookGUI.getPrice()).getText().equals(""))
			{
				(addBookGUI.getBpriceWarningLabel()).setForeground(Color.red);
				(addBookGUI.getBpriceWarningLabel()).setText("You must fill Book Price");
				flag_price = 0;
			}
			else if(!(addBookGUI.getPrice()).getText().matches("[0-9]+"))
			{
				(addBookGUI.getBpriceWarningLabel()).setForeground(Color.red);
				(addBookGUI.getBpriceWarningLabel()).setText("Wrong input! only numbers");
				flag_price = 0;
			}
			else
				flag_price = 1;
			
			if(addBookGUI.getTxtBookAuthorid().getText().equals(""))
			{
				addBookGUI.getBAuthoridWarningLabel().setForeground(Color.red);
				addBookGUI.getBAuthoridWarningLabel().setText("You must fill Author id");
				flag_authorid = 0;
			}
			else if(!addBookGUI.getTxtBookAuthorid().getText().matches("[0-9]+"))
			{
				addBookGUI.getBAuthoridWarningLabel().setForeground(Color.red);
				addBookGUI.getBAuthoridWarningLabel().setText("Wrong input! only numbers");
				flag_authorid = 0;
			}
			else
				flag_authorid = 1;
			
			if(addBookGUI.getTxtBookAuthorname().getText().equals(""))
			{
				addBookGUI.getBAuthornameWarningLabel().setForeground(Color.red);
				addBookGUI.getBAuthornameWarningLabel().setText("You must fill Author Name");
				flag_authorname = 0;
			}
			else
				flag_authorname = 1;
			
			if(addBookGUI.getCategory_SubjectList().getModel().getSize() == 0)
			{
				addBookGUI.getNoAddedCatSubWarning().setForeground(Color.red);
				addBookGUI.getNoAddedCatSubWarning().setText("You must add at least 1 category and subject");
			}
			else
				flag_categoryANDsubject = 1;
			
			//this if statement checks all the flags (should all be 1 to send to server)
			
			if(flag_name == 1 && flag_id == 1 && flag_price == 1 && flag_authorid == 1 && flag_authorname == 1 && flag_categoryANDsubject == 1)
			{
				String Book_id = (addBookGUI.getBookIdText()).getText();
				String Book_Name = (addBookGUI.getBookNameText()).getText();
				String Book_lang = (addBookGUI.getComboBox().getSelectedItem()).toString();
				String Book_Price = addBookGUI.getPrice().getText();
				String Book_inCatalog = null;
				String Book_Synopsis = addBookGUI.getSynopsisArea().getText();
				String Book_TOC = addBookGUI.getTOC_area().getText();
				String Book_Keywords = addBookGUI.getKeywords_area().getText();
				String Book_Author_id = addBookGUI.getTxtBookAuthorid().getText();
				String Book_Author_name = addBookGUI.getTxtBookAuthorname().getText();
				ArrayList<String> subcat = new ArrayList<String>();
			
				for(int i = 0 ; i < addBookGUI.getCategory_SubjectList().getModel().getSize() ; i++)
					subcat.add((String)addBookGUI.getCategory_SubjectList().getModel().getElementAt(i));
				
				if(addBookGUI.getRdbtnNo().isSelected())
					Book_inCatalog = "NO";
				else
					Book_inCatalog = "YES";
				
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "AddBook");
				params.put("Book_id", Book_id);
				params.put("Book_Name", Book_Name);
				params.put("Book_lang", Book_lang);
				params.put("Book_Price", Book_Price);
				params.put("Book_inCatalog", Book_inCatalog);
				params.put("Book_Synopsis", Book_Synopsis);
				params.put("Book_Keywords", Book_Keywords);
				params.put("Book_TOC", Book_TOC);
				params.put("Book_Author_id", Book_Author_id);
				params.put("Book_Author_name", Book_Author_name);
				params.put("Book_subcat", subcat);
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(bookController);
				sendToServer(envelope);	
			}
				
			
			
		}
		
	}
	// ---------------------- end of apply button ---------------------------//
	
	
	// -------------------------- cancel button ------------------------------//
	
	class CancelFromReadFromWorkerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			addBookGUI.dispose();
			ManageBooksGUI mbg = new ManageBooksGUI();
			ManageBooksController mbc = new ManageBooksController(mbg);	
		}
	}
	
	// ---------------------- end of cancel button ------------------------//
	
	
	// ----------------- the controller handle message from client (DB result) -------------------------//
	public void handleDBResult(Object message)
	{	
		String msg;
		if (message instanceof String)
			msg = ((String)message);
		else
		msg = (String) ((Envelope)message).getParams().get("msg");
		
		switch (msg)
		{
		case "BookInsertedOK":
			JOptionPane.showMessageDialog(null,"Book was inserted successfuly!");
			break;
		case "BookIsInTheDB":
			JOptionPane.showMessageDialog(null,"This book is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
			break;
		case "AllCategoriesInAtLeast1DB":
			ArrayList<String> categories = (ArrayList<String>)((Envelope)message).getParams().get("categories");
			for(int i = 0; i < categories.size(); i++)
				addBookGUI.getCategory_combobox().addItem(categories.get(i));						
			break;
		case "AllSubjectsInDB":
			ArrayList<String> subjects = (ArrayList<String>)((Envelope)message).getParams().get("subjects");
			addBookGUI.getSubject_combobox().removeAllItems();
			for(int i = 0; i < subjects.size(); i++)
				addBookGUI.getSubject_combobox().addItem(subjects.get(i));		
			break;
		}
	}

}
