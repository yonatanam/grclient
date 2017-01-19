package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import client.App;
import gui.AddCategoryGUI;
import gui.EditCategoryGUI;
import gui.MainWindowGUI;
import gui.ManageBooksGUI;
import models.Envelope;

public class CategoryController extends AbstractController{
	
	private CategoryController categoryController;
	private AddCategoryGUI addCategoryGUI=null;
	private EditCategoryGUI editCategoryGUI=null;	
	private Validate_textFields val;
	
	
	public CategoryController(EditCategoryGUI editcat) {
		editCategoryGUI=editcat;
		categoryController = this;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "GetCategories");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(categoryController);
		sendToServer(envelope);	
		val = new Validate_textFields();
		editCategoryGUI.addRemoveButtonActionListener(new ButtonRemoveActionListener());
		editCategoryGUI.addBackButtonActionListener(new BackButtonActionListener() );
		editCategoryGUI.AddChangeButtonactionListener(new ChangeButtonActionListener() );
		editCategoryGUI.addWindowListener(new CustomWindowListener());
	}
	public CategoryController(AddCategoryGUI addcat) {
		
		addCategoryGUI = addcat;
		categoryController = this;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "GetCategories");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(categoryController);
		sendToServer(envelope);	
		
		
		val = new Validate_textFields();
		
		addcat.AddbuttonApplyactionListener(new ButtonApplyActionListener());
		addcat.addButtonCancelFromCreateNewCategActionListener(new ButtonCancelActionListener());
		addcat.AddTextCategNameMouseListener(new TextCategNameMouseListener());
		addcat.addWindowListener(new CustomWindowListener());
	}
	
	
	
	class ButtonRemoveActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("msg", "RemoveCategory");
			params.put("CatName", (String)editCategoryGUI.getCatComboBox().getSelectedItem());
			
			Envelope envelope = new Envelope(params);
			App.client.setCurrentController(categoryController);
			sendToServer(envelope);	
		}
				
	}
	class ButtonApplyActionListener implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			
			int flag1 = 0;
			
			addCategoryGUI.getName_Warning().setText("");
			
			if(val.Check_text_empty(addCategoryGUI.getTextCatName(), addCategoryGUI.getName_Warning(), "Category name"))
				flag1 = 0;
			else
				flag1 = 1;

			if(flag1 == 1)
			{
			
				String CatName = addCategoryGUI.getTextCatName().getText();
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "CreateNewCategory");
				params.put("CatName", CatName);
				
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(categoryController);
				sendToServer(envelope);	
			}
				
			
		}
		
	}
	
	class ButtonCancelActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			addCategoryGUI.dispose();
			ManageBooksGUI mbg = new ManageBooksGUI();
			ManageBooksController mbc = new ManageBooksController(mbg);	
		}
				
	}
	
	
	
	class TextCategNameMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addCategoryGUI.getTextCatName().setText("");
			
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
	
	class BackButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			editCategoryGUI.dispose();
			editCategoryGUI=null;
			ManageBooksGUI mbg = new ManageBooksGUI();
			ManageBooksController mbc = new ManageBooksController(mbg);	
		}
		
	}
	class ChangeButtonActionListener implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			if(!val.Check_text_empty(editCategoryGUI.getTextCatName(), editCategoryGUI.getName_Warning(), "Category name"))
				{
				if(editCategoryGUI.getTextCatName().getText().equals("Enter Category name")) editCategoryGUI.getName_Warning().setText("You must fill Category name");
				else
			    {
					editCategoryGUI.getName_Warning().setText("");
				String CatName = editCategoryGUI.getTextCatName().getText();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("msg", "EditCategory");
				params.put("CatName", CatName);
				params.put("existName",(String)editCategoryGUI.getCatComboBox().getSelectedItem());
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(categoryController);
				sendToServer(envelope);
			    }
				}
		}
	}
	
	
	
	
	public void handleDBResult(Object message)
	{	
		String msg;
		if (message instanceof String)
			msg = ((String)message);
		else
		msg = (String) ((Envelope)message).getParams().get("msg");
		
		switch (msg)
		{
			case "CategoryHasBeenChanged":
				JOptionPane.showMessageDialog(null,"Category was Changed successfuly!");
				editCategoryGUI.dispose();
				new ManageBooksController(new ManageBooksGUI());	
			break;
			case "CreateNewCategoryOK":
				JOptionPane.showMessageDialog(null,"Category was inserted successfuly!");
				break;
			case "CategoryRemoved":
				JOptionPane.showMessageDialog(null,"Category and all the subjects that belong to this category was removed successfuly!");
				editCategoryGUI.dispose();
				new ManageBooksController(new ManageBooksGUI());	
				break;
			case "Category_nameExist":
				JOptionPane.showMessageDialog(null,"This category name is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				break;
			case "AllCategoriesInDB":
				ArrayList<String> categories = (ArrayList<String>)((Envelope)message).getParams().get("categories");
				if(!categories.isEmpty())
				{
					if(editCategoryGUI!=null)
						for(int i=0;i<categories.size();i++)
							editCategoryGUI.getCatComboBox().addItem(categories.get(i));
					
					if (addCategoryGUI!=null)
						addCategoryGUI.getWhat_Categories().setText(categories.toString());
				}
					break;
		}
	}

}
