package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.swing.JOptionPane;

import client.App;

import java.util.Map;


import gui.AddBookGUI;
import gui.MainWindowGUI;
import models.Envelope;


// this class contains all book functionality
// add to this class all the relevant data you need

public class BookController extends AbstractController{
	
	private BookController bookController;
	private AddBookGUI addBookGUI;
	private int flag_id = 0;
	private int flag_name = 0;
	
	public BookController(AddBookGUI abg){  // add your GUI functionality here ('AddBook' is one of them)
		addBookGUI = abg;
		bookController = this;
		abg.AddTextBookIdMouseListener(new TextBookIdMouseListener());
		abg.AddTextBookNameMouseListener(new TextBookNameMouseListener());
		abg.AddbuttonApplyactionListener(new buttonApplyActionListener());
		abg.addButtonCancelFromAddBookActionListener(new CancelFromReadFromWorkerListener());
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
	
	// ------------- when user click the apply button to insert the new book to the DB ---------- //
	
	public class buttonApplyActionListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			(addBookGUI.getBidWarningLabel()).setText("");
			(addBookGUI.getBNameWarningLabel()).setText("");
			
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
			
			if(flag_name == 1 && flag_id == 1)
			{
				String Book_id = (addBookGUI.getBookIdText()).getText();
				String Book_Name = (addBookGUI.getBookNameText()).getText();
				String Book_lang = (addBookGUI.getComboBox().getSelectedItem()).toString();
				String Book_Format = null;
				float Book_Price = Float.valueOf(addBookGUI.getPrice().getText());
				
				if(addBookGUI.getRdbtnDoc().isSelected())
					Book_Format = "DOC";
				else if(addBookGUI.getRdbtnFb().isSelected())
					Book_Format = "FB2";
				else if(addBookGUI.getRdbtnPdf().isSelected())
					Book_Format = "PDF";
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "AddBook");
				params.put("Book_id", Book_id);
				params.put("Book_Name", Book_Name);
				params.put("Book_lang", Book_lang);
				params.put("Book_Format", Book_Format);
				params.put("Book_Price", Book_Price);
				
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
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);	
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
		case "BookIsInTheDB":
			JOptionPane.showMessageDialog(null,"This book is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

}
