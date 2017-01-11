package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

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
	private Validate_textFields val;
	
	public BookController(AddBookGUI abg){  // add your GUI functionality here ('AddBook' is one of them)
		addBookGUI = abg;
		bookController = this;
		
		// getting the *categories* that are in the db
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "GetCategories");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(bookController);
		sendToServer(envelope);	
		
		// getting the *subjects* that are in the db
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("msg", "GetSubjects");
		Envelope envelope2 = new Envelope(params2);
		App.client.setCurrentController(bookController);
		sendToServer(envelope2);	
		
		
		
		
		abg.AddTextBookIdMouseListener(new TextBookIdMouseListener());
		abg.AddTextBookNameMouseListener(new TextBookNameMouseListener());
		abg.AddTextPriceMouseListener(new TextPriceMouseListener());
		abg.AddTextAuthoridMouseListener(new TextAuthoridMouseListener());
		abg.AddTextAuthorNameMouseListener(new TextAuthorNameMouseListener());
		abg.AddbuttonApplyactionListener(new buttonApplyActionListener());
		abg.addButtonCancelFromAddBookActionListener(new CancelFromReadFromWorkerListener());
		abg.addWindowListener(new CustomWindowListener());
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
			
			if(flag_name == 1 && flag_id == 1 && flag_price == 1 && flag_authorid == 1 && flag_authorname == 1)
			{
				String Book_id = (addBookGUI.getBookIdText()).getText();
				String Book_Name = (addBookGUI.getBookNameText()).getText();
				String Book_lang = (addBookGUI.getComboBox().getSelectedItem()).toString();
				String Book_Format = null;
				String Book_Price = addBookGUI.getPrice().getText();
				String Book_inCatalog = null;
				String Book_Synopsis = addBookGUI.getSynopsisArea().getText();
				String Book_TOC = addBookGUI.getTOC_area().getText();
				String Book_Keywords = addBookGUI.getKeywords_area().getText();
				String Book_Author_id = addBookGUI.getTxtBookAuthorid().getText();
				String Book_Author_name = addBookGUI.getTxtBookAuthorname().getText();
				String Book_Category = (addBookGUI.getCategory_combobox().getSelectedItem()).toString();
				String Book_Subject = (addBookGUI.getSubject_combobox().getSelectedItem()).toString();
				
				if(addBookGUI.getRdbtnDoc().isSelected())
					Book_Format = "DOC";
				else if(addBookGUI.getRdbtnFb().isSelected())
					Book_Format = "FB2";
				else if(addBookGUI.getRdbtnPdf().isSelected())
					Book_Format = "PDF";
				
				if(addBookGUI.getRdbtnNo().isSelected())
					Book_inCatalog = "NO";
				else
					Book_inCatalog = "YES";
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "AddBook");
				params.put("Book_id", Book_id);
				params.put("Book_Name", Book_Name);
				params.put("Book_lang", Book_lang);
				params.put("Book_Format", Book_Format);
				params.put("Book_Price", Book_Price);
				params.put("Book_inCatalog", Book_inCatalog);
				params.put("Book_Synopsis", Book_Synopsis);
				params.put("Book_Keywords", Book_Keywords);
				params.put("Book_TOC", Book_TOC);
				params.put("Book_Author_id", Book_Author_id);
				params.put("Book_Author_name", Book_Author_name);
				params.put("Book_Category", Book_Category);
				params.put("Book_Subject", Book_Subject);
				
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
		case "AllCategoriesInDB":
			ArrayList<String> categories = (ArrayList<String>)((Envelope)message).getParams().get("categories");
			for(int i = 0; i < categories.size(); i++)
				addBookGUI.getCategory_combobox().addItem(categories.get(i));						
			break;
		case "AllSubjectsInDB":
			ArrayList<String> subjects = (ArrayList<String>)((Envelope)message).getParams().get("subjects");
			for(int i = 0; i < subjects.size(); i++)
				addBookGUI.getSubject_combobox().addItem(subjects.get(i));		
			break;
		}
	}

}
