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
import controllers.AbstractController.CustomWindowListener;
import controllers.CategoryController.ButtonApplyActionListener;
import controllers.CategoryController.ButtonCancelActionListener;
import controllers.CategoryController.TextCategIdMouseListener;
import controllers.CategoryController.TextCategNameMouseListener;
import gui.AddCategoryGUI;
import gui.AddSubjectGUI;
import gui.MainWindowGUI;
import gui.ManageBooksGUI;
import models.Envelope;

public class SubjectController extends AbstractController{
	
	private SubjectController subjectController;
	private AddSubjectGUI addsubjectGUI;
	private Validate_textFields val;
	
public SubjectController(AddSubjectGUI addsub) {
		
	addsubjectGUI = addsub;
	subjectController = this;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "GetSubjects");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(subjectController);
		sendToServer(envelope);	
		
		
		val = new Validate_textFields();
		
		addsub.AddbuttonApplyactionListener(new ButtonApplyActionListener());
		addsub.addButtonCancelFromCreateNewSubjActionListener(new ButtonCancelActionListener());
		addsub.AddTextSubjIdMouseListener(new TextSubjIdMouseListener());
		addsub.AddTextSubjNameMouseListener(new TextSubjNameMouseListener());
		addsub.addWindowListener(new CustomWindowListener());
	}
	
	class ButtonApplyActionListener implements ActionListener
	{
	
		
		public void actionPerformed(ActionEvent e) {
			
			int flag1 = 0;
			int flag2 = 0;

			
			addsubjectGUI.getId_Warning().setText("");
			addsubjectGUI.getName_Warning().setText("");
			
			if(val.Check_text_empty(addsubjectGUI.getTextSubId(), addsubjectGUI.getId_Warning(), "Subject id"))
				flag1 = 0;
			else if(val.Check_text_onlyNumbers(addsubjectGUI.getTextSubId(), addsubjectGUI.getId_Warning()))
				flag1 = 1;
			else
				flag1 = 0;
			
			if(val.Check_text_empty(addsubjectGUI.getTextSubName(), addsubjectGUI.getName_Warning(), "Subject name"))
				flag2 = 0;
			else
				flag2 = 1;
			
			
			
			
			if(flag1 == 1 && flag2 == 1)
			{
			
				String SubId = addsubjectGUI.getTextSubId().getText();
				String SubName = addsubjectGUI.getTextSubName().getText();
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "CreateNewSubject");
				params.put("SubId", SubId);
				params.put("SubName", SubName);
				
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
	
	
	
	class TextSubjIdMouseListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			addsubjectGUI.getTextSubId().setText("");
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
			case "SubjectidExist":
				JOptionPane.showMessageDialog(null,"This subject id is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				break;
			case "Subject_nameExist":
				JOptionPane.showMessageDialog(null,"This subject name is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				break;
			case "AllSubjectsInDB":
				ArrayList<String> subjects = (ArrayList<String>)((Envelope)message).getParams().get("subjects");
				if(!subjects.isEmpty())
					addsubjectGUI.getWhat_Subjects().setText(subjects.toString());
				break;
		}
	}
}
