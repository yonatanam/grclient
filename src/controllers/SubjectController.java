package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import client.App;
import controllers.AbstractController.CustomWindowListener;
import controllers.CategoryController.ButtonApplyActionListener;
import controllers.CategoryController.ButtonCancelActionListener;
import controllers.CategoryController.TextCategNameMouseListener;
import gui.AddCategoryGUI;
import gui.AddSubjectGUI;
import gui.EditSubjectGUI;
import gui.MainWindowGUI;
import gui.ManageBooksGUI;
import models.Envelope;

public class SubjectController extends AbstractController{
	
	private int flag = 0;
	private String[] categoriesvec = null;
	private ArrayList<String> categories;
	private SubjectController subjectController;
	private AddSubjectGUI addsubjectGUI;
	private Validate_textFields val;
	private EditSubjectGUI EditSubjectGui=null;
	
	
	public SubjectController(AddSubjectGUI addsub) {
		
		addsubjectGUI = addsub;
		subjectController = this;
		
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "GetCategories");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(subjectController);
		sendToServer(envelope);	

		
		// DON'T TOUCH THIS COMMENT BLOCK
		
		/*System.out.println("flag is :" + flag);
			if(flag == 0)
			{
				addsub.ShowNoCategoriesinDBYetifflag0();	
				addsub.addButtonBackTomanageActionListener(new BackTomanageActionListener());
			
			}
			else
			{
				val = new Validate_textFields();
				addsubjectGUI.setAllCategories(categoriesvec);
				addsub.ShowAllComponentsIfFlag1();
				addsub.AddbuttonApplyactionListener(new ButtonApplyActionListener());
				addsub.addButtonCancelFromCreateNewSubjActionListener(new ButtonCancelActionListener());
				addsub.AddTextSubjIdMouseListener(new TextSubjIdMouseListener());
				addsub.AddTextSubjNameMouseListener(new TextSubjNameMouseListener());
				addsub.addWindowListener(new CustomWindowListener());
				addsub.addButtonAddCategoryActionListener(new AddCategoryActionListener());
				addsub.addButtonRemoveCategoryActionListener(new RemoveCategoryActionListener());
			}
			System.out.println("flag is " + flag);*/
		}
	public SubjectController(EditSubjectGUI editsub)	
	{
		subjectController = this;
		EditSubjectGui = editsub;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "GetDisSubjects");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(subjectController);
		sendToServer(envelope);	
		val = new Validate_textFields();
		
		EditSubjectGui.addBackButtonActionListener(new BackButtonActionListener());
		EditSubjectGui.AddSubjectComboBoxListener(new itemListener());
		EditSubjectGui.addWindowListener(new CustomWindowListener());
		EditSubjectGui.AdditemComboBoxListener(new itemListener());
		EditSubjectGui.addRemoveButtonActionListener(new RemoveButtonActionListener());
		EditSubjectGui.addChangeButtonActionListener(new ChangeButtonActionListener());
	}	
	class itemListener implements ItemListener
    {

		@Override
		public void itemStateChanged(ItemEvent e) {
			int state = e.getStateChange();
	          if(state == ItemEvent.SELECTED)
	          {
	        	  String sub=(String)EditSubjectGui.getSubComboBox().getSelectedItem();
	     			Map<String, Object> params = new HashMap<String, Object>();
	     			params.put("msg", "GetrelevantCat");
	     			params.put("subject", sub);
	     			Envelope envelope = new Envelope(params);
	     			sendToServer(envelope);	
	     			EditSubjectGui.getTextsubName().setText(sub);
	          }
			
		}

		
    	
    }
	class ChangeButtonActionListener implements ActionListener
   	{

   		@Override
   		public void actionPerformed(ActionEvent e) {
   			String oldsub=(String)EditSubjectGui.getSubComboBox().getSelectedItem();
   			String oldcategory=(String) EditSubjectGui.getcategorylist().getSelectedValue();
   			String newcategory;
   			if(oldcategory==null) EditSubjectGui.getCategory_Warning().setText("you must choose subject's category");
   			else if(!val.Check_text_empty(EditSubjectGui.getTextsubName(), EditSubjectGui.getName_Warning(), "you must fill subject name"))
   			{    newcategory=(String) EditSubjectGui.getCatComboBox().getSelectedItem();
   				if(newcategory.equals("none"))newcategory= oldcategory;
   				
   				Map<String, Object> params = new HashMap<String, Object>();
   				params.put("msg", "editsubject");
   				params.put("oldcategory",oldcategory);
   				params.put("newcategory", newcategory);
   				params.put("oldsub", oldsub);
   				params.put("newsub", EditSubjectGui.getTextsubName().getText());
   				Envelope envelope = new Envelope(params);
   				App.client.setCurrentController(subjectController);
   				sendToServer(envelope);	
   				
   			}
   		}
   		
   	}
class BackButtonActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			EditSubjectGui.dispose();
			new ManageBooksController(new ManageBooksGUI());	
		}
		
	}
class RemoveButtonActionListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent e) {
		    String sub=(String)EditSubjectGui.getSubComboBox().getSelectedItem();
			String category=(String) EditSubjectGui.getcategorylist().getSelectedValue();
		if(category==null) EditSubjectGui.getCategory_Warning().setText("you must choose subject's category");
		else
		{
			Map<String, Object> params = new HashMap<String, Object>();
				params.put("msg", "RemoveSubjects");
				params.put("SubName",sub);
				params.put("CatName", category);
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(subjectController);
				sendToServer(envelope);	
		}
		
	}
	
}
	
class ButtonApplyActionListener implements ActionListener
	{
	
		
		public void actionPerformed(ActionEvent e) {
			
			int flag2 = 0;
			int flag3 = 0;
			
			
			addsubjectGUI.getName_Warning().setText("");
			addsubjectGUI.getChooseCategWarning_Label().setText("");
			
			if(val.Check_text_empty(addsubjectGUI.getTextSubName(), addsubjectGUI.getName_Warning(), "Subject name"))
				flag2 = 0;
			else
				flag2 = 1;
			
			if(addsubjectGUI.getActCat().isEmpty())
			{
				addsubjectGUI.getChooseCategWarning_Label().setText("You must choose at least 1 category");
				flag3 = 0;
			}
			else
				flag3 = 1;
			
			
			
			
			if(flag2 == 1 && flag3 == 1)
			{
				String SubName = addsubjectGUI.getTextSubName().getText();
				String[] SubCat = new String[addsubjectGUI.getActCat().size()];
				
				for(int i = 0 ; i < SubCat.length ; i++)
					SubCat[i] = addsubjectGUI.getActCat().get(i);
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "CreateNewSubject");
				params.put("SubName", SubName);
				params.put("SubCat", SubCat);
				
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(subjectController);
				sendToServer(envelope);	
			}
				
			
		}
		
	}
	
	class ButtonCancelActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			addsubjectGUI.dispose();
			ManageBooksGUI mbg = new ManageBooksGUI();
			ManageBooksController mbc = new ManageBooksController(mbg);	
		}
				
	}
	
	class BackTomanageActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			addsubjectGUI.dispose();
			ManageBooksGUI mbg = new ManageBooksGUI();
			ManageBooksController mbc = new ManageBooksController(mbg);	
		}
				
	}
	
	class AddCategoryActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			int flag = 1;
			String temp = (String)addsubjectGUI.getCategories_List().getSelectedValue();
			//addsubjectGUI.getActCat().add((String)addsubjectGUI.getCategories_List().getSelectedValue());		
			for(int i = 0 ; i < addsubjectGUI.getActCat().size(); i++)
			{
				if(addsubjectGUI.getActCat().get(i) == temp)
					flag = 0;
			}
			if(flag == 1)
			{
				addsubjectGUI.getActCat().add(temp);
				addsubjectGUI.getChooseCategWarning_Label().setText("");
			}
	
			String[] temps = new String[addsubjectGUI.getActCat().size()];
			for(int i = 0 ; i < temps.length; i++)
				temps[i] = addsubjectGUI.getActCat().get(i);
			
			addsubjectGUI.getActualCategories_List().setListData(temps);
			
			/*for(int i = 0 ; i < arr.length; i++)
				addsubjectGUI.getActualCategories_List().add
			addsubjectGUI.getActualCategories_List().setListData(addsubjectGUI.getCategories_List().getSelectedValues());*/
		}
				
	}
	
	
	class RemoveCategoryActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(addsubjectGUI.getActualCategories_List().getSelectedIndex() == -1)
			{
				//TODO - dont need to
			}
			else
			{
				addsubjectGUI.getActCat().remove(addsubjectGUI.getActualCategories_List().getSelectedIndex());
				String[] temp = new String[addsubjectGUI.getActCat().size()];
				for(int i = 0 ; i < temp.length; i++)
					temp[i] = addsubjectGUI.getActCat().get(i);
				
				addsubjectGUI.getActualCategories_List().setListData(temp);
			}
			
		}
				
	}
	
	
	
	class TextSubjNameMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addsubjectGUI.getTextSubName().setText("");
			
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
			case "CreateNewSubjectOK":
				JOptionPane.showMessageDialog(null,"subject was inserted successfuly!");
				break;
			case "Subject_nameExist":
				ArrayList<String> categoryname = (ArrayList<String>)((Envelope)message).getParams().get("CatBadId");
				String catmsg = "";
				if(categoryname.size() == 2)
					catmsg = categoryname.get(0) + " and " + categoryname.get(1);
				else if(categoryname.size() == 1)
					catmsg = categoryname.get(0);
				else
				{
					for(int i = 0 ; i < categoryname.size() - 1 ; i++)
					{
						if(i == categoryname.size() - 2)
							catmsg = catmsg + categoryname.get(i) ;
						else
							catmsg = catmsg + categoryname.get(i) + ", ";
					}		
					catmsg = catmsg + " and " + categoryname.get(categoryname.size() - 1);
				}
				
				
				JOptionPane.showMessageDialog(null,"This subject name is already affiliated to " + catmsg + " in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				break;
			case "AlldisSubjectsInDB":
				ArrayList<String> subjects = (ArrayList<String>)((Envelope)message).getParams().get("subjects");
				if(!subjects.isEmpty())
				{
					JComboBox s=EditSubjectGui.getSubComboBox();
					for(int i=0;i<subjects.size();i++)
						s.addItem(subjects.get(i));
				}
				break;
			case "relevantCat":
				ArrayList<String> incategories = (ArrayList<String>)((Envelope)message).getParams().get("incategories");
				 DefaultListModel listModel=EditSubjectGui.getlistmodel();
				 listModel.clear();
				if(!incategories.isEmpty())
					for(int i=0;i<incategories.size();i++)
						listModel.addElement(incategories.get(i));
				ArrayList<String> outcategories = (ArrayList<String>)((Envelope)message).getParams().get("outcategories");
				JComboBox s=EditSubjectGui.getCatComboBox();
				s.removeAllItems();
				for(int i=0;i<outcategories.size();i++)
					s.addItem(outcategories.get(i));
				s.addItem("none");
				s.setSelectedItem("none");
				break;
			case "editsubjectOK":
				JOptionPane.showMessageDialog(null,"This subject edited successfully");
				this.EditSubjectGui.dispose();
				new ManageBooksController(new ManageBooksGUI());
				break;
			case "editsubjectERROR":
				JOptionPane.showMessageDialog(null,"ERROR occuerd in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				this.EditSubjectGui.dispose();
				new ManageBooksController(new ManageBooksGUI());
				break;
			case "subjectalreadyexist":
				String sub=(String) ((Envelope)message).getParams().get("newsub");
				String category=(String) ((Envelope)message).getParams().get("newcategory");
				JOptionPane.showMessageDialog(null,"This subject "+ sub+" is already exit in "+category,"Error", JOptionPane.ERROR_MESSAGE);
				
				break;
			case "SubjectRemoved":
				JOptionPane.showMessageDialog(null,"subject was removed successfuly!");
				EditSubjectGui.dispose();
				new ManageBooksController(new ManageBooksGUI());	
				break;
				
			case "AllCategoriesInDB":
				categories = (ArrayList<String>)((Envelope)message).getParams().get("categories");
				System.out.println("categories" + categories);

				if(categories.isEmpty())
				{
					flag = 0;
					addsubjectGUI.ShowNoCategoriesinDBYetifflag0();	
					addsubjectGUI.addButtonBackTomanageActionListener(new BackTomanageActionListener());		
				}
				else
				{
					categoriesvec = new String[categories.size()];
					for(int i = 0 ; i < categoriesvec.length; i++)
						categoriesvec[i] = categories.get(i);
					flag = 1;
					
					val = new Validate_textFields();
					addsubjectGUI.setAllCategories(categoriesvec);
					addsubjectGUI.ShowAllComponentsIfFlag1();
					addsubjectGUI.AddbuttonApplyactionListener(new ButtonApplyActionListener());
					addsubjectGUI.addButtonCancelFromCreateNewSubjActionListener(new ButtonCancelActionListener());
					addsubjectGUI.AddTextSubjNameMouseListener(new TextSubjNameMouseListener());
					addsubjectGUI.addWindowListener(new CustomWindowListener());
					addsubjectGUI.addButtonAddCategoryActionListener(new AddCategoryActionListener());
					addsubjectGUI.addButtonRemoveCategoryActionListener(new RemoveCategoryActionListener());
					
				}
				break;
		
		}
	}
}
