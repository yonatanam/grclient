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
import gui.MainWindowGUI;
import models.Envelope;

public class CategoryController extends AbstractController{
	
	private CategoryController categoryController;
	private AddCategoryGUI addCategoryGUI;
	private Validate_textFields val;
	
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
		addcat.AddTextCategIdMouseListener(new TextCategIdMouseListener());
		addcat.AddTextCategNameMouseListener(new TextCategNameMouseListener());
	}
	
	
	class ButtonApplyActionListener implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			
			int flag1 = 0;
			int flag2 = 0;
			int flag3 = 0;
			
			addCategoryGUI.getId_Warning().setText("");
			addCategoryGUI.getName_Warning().setText("");
			
			if(val.Check_text_empty(addCategoryGUI.getTextCatId(), addCategoryGUI.getId_Warning(), "Category id"))
				flag1 = 0;
			else
				flag1 = 1;
			
			if(val.Check_text_empty(addCategoryGUI.getTextCatName(), addCategoryGUI.getName_Warning(), "Category name"))
				flag2 = 0;
			else
				flag2 = 1;
			
			if(val.Check_text_onlyNumbers(addCategoryGUI.getTextCatId(), addCategoryGUI.getId_Warning()))
				flag3 = 1;
			else
				flag3 = 0;
			
			
			if(flag1 == 1 && flag2 == 1& flag3 == 1)
			{
			
				String CatId = addCategoryGUI.getTextCatId().getText();
				String CatName = addCategoryGUI.getTextCatName().getText();
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "CreateNewCategory");
				params.put("CatId", CatId);
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
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);	
		}
				
	}
	
	
	
	class TextCategIdMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addCategoryGUI.getTextCatId().setText("");
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
	
	
	
	
	
	
	
	public void handleDBResult(Object message)
	{	
		String msg;
		if (message instanceof String)
			msg = ((String)message);
		else
		msg = (String) ((Envelope)message).getParams().get("msg");
		
		switch (msg)
		{
			case "CreateNewCategoryOK":
				JOptionPane.showMessageDialog(null,"Category was inserted successfuly!");
				break;
			case "CategoryidExist":
				JOptionPane.showMessageDialog(null,"This category id is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				break;
			case "Category_nameExist":
				JOptionPane.showMessageDialog(null,"This category name is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				break;
			case "AllCategoriesInDB":
				ArrayList<String> categories = (ArrayList<String>)((Envelope)message).getParams().get("categories");
				String cat = "";
				for(int i = 0 ; i < categories.size(); i++)
					cat += categories.get(i) + ", ";
				
				addCategoryGUI.getWhat_Categories().setText(cat);
				break;
		}
	}

}
