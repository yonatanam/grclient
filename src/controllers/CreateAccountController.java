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
import gui.CreateAccountGUI;
import gui.MainWindowGUI;
import models.Envelope;

public class CreateAccountController extends AbstractController{
	
	private CreateAccountController createAccountController;
	private CreateAccountGUI createAccountGUI;
	private Validate_textFields val;
	
	public CreateAccountController(CreateAccountGUI craccount) {
		
		createAccountGUI = craccount;
		createAccountController = this;
		
/*		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", "createAccount");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(createAccountController);
		sendToServer(envelope);	*/
		
		
		val = new Validate_textFields();
		
		craccount.AddbuttonApplyactionListener(new ButtonApplyActionListener());
		craccount.addButtonCancelActionListener(new ButtonCancelActionListener());
		createAccountGUI.addWindowListener(new CustomWindowListener());
		/*addcat.AddTextCategIdMouseListener(new TextCategIdMouseListener());
		addcat.AddTextCategNameMouseListener(new TextCategNameMouseListener());
		addcat.addWindowListener(new CustomWindowListener());*/
	}
	
	
	class ButtonApplyActionListener implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			
			int flag1 = 0;
			int flag2 = 0;
			
			
			createAccountGUI.username_Warning().setText("");
			createAccountGUI.password_Warning().setText("");
			createAccountGUI.firstName_Warning().setText("");
			createAccountGUI.lastName_Warning().setText("");
			createAccountGUI.email_Warning().setText("");
			
			if(val.Check_text_empty(createAccountGUI.getUsernameText(), createAccountGUI.username_Warning(), "Username"))
				flag1 = 0;   //If Username field is empty!
			else if (val.Check_text_empty(createAccountGUI.getFirstNameText(), createAccountGUI.firstName_Warning(), "First Name"))
				flag1 = 0; //If firstName field is empty
			else if (val.Check_text_empty(createAccountGUI.getLastNameText(), createAccountGUI.lastName_Warning(), "LastName"))
				flag1 = 0; //If LastName field is empty
			else if (val.Check_text_empty(createAccountGUI.getEmailAddressText(), createAccountGUI.email_Warning(), "Email"))
				flag1 = 0; //If Email field is empty
			else if (!(val.isValidEmailAddress(createAccountGUI.getEmailAddressText().getText()))){
				flag1 = 0;
				createAccountGUI.email_Warning().setText("Invalid email address");
			}
			else
				flag1 = 1;
			
			if(val.Check_text_onlyNumbers(createAccountGUI.getPasswordText(), createAccountGUI.password_Warning()))
				flag2 = 1;
			else
				flag2 = 0;
			
			
			
			
			if(flag1 == 1 && flag2 == 1)
			{
			
				String UserName = createAccountGUI.getUsernameText().getText();
				String Password = createAccountGUI.getPasswordText().getText();
				String Email = createAccountGUI.getEmailAddressText().getText();
				String FirstName = createAccountGUI.getFirstNameText().getText();
				String LastName = createAccountGUI.getLastNameText().getText();
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "createAccount");
				params.put("UserName", UserName);
				params.put("Password", Password);
				params.put("Email", Email);
				params.put("FirstName", FirstName);
				params.put("LastName", LastName);
				params.put("Permission", "USER");
				params.put("Password", Password);
				params.put("login_attempts", 0);
				params.put("status", "ACTIVE");
				params.put("threadnum", 0);
				
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(createAccountController);
				sendToServer(envelope);	
			}
			
			else
				System.out.println("Something went wrong!");
				
			
		}
		
	}
	
	
	class ButtonCancelActionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			createAccountGUI.dispose();
			new MainWindowController(new MainWindowGUI());
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
			case "CreateAccountOK":
				JOptionPane.showMessageDialog(null,"Account created successfuly!");
				break;
			case "CreateAccountFailed":
				JOptionPane.showMessageDialog(null,"Something went wrong","Error", JOptionPane.ERROR_MESSAGE);
				break;
			/*case "Category_nameExist":
				JOptionPane.showMessageDialog(null,"This category name is already in the DataBase!","Error", JOptionPane.ERROR_MESSAGE);
				break;*/
			/*case "AllCategoriesInDB":
				ArrayList<String> categories = (ArrayList<String>)((Envelope)message).getParams().get("categories");
				if(!categories.isEmpty())
					addCategoryGUI.getWhat_Categories().setText(categories.toString());
				break;*/
		}
	}

}
