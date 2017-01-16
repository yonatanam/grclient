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
import gui.CancelSubscriptionGUI;
import gui.CreateAccountGUI;
import gui.MainWindowGUI;
import models.Envelope;

public class CancelSubscriptionController extends AbstractController{
	
	private CancelSubscriptionController cancelSubscriptionController;
	private CancelSubscriptionGUI cancelSubscriptionGUI;
	
	public CancelSubscriptionController(CancelSubscriptionGUI craccount) {
		
		cancelSubscriptionGUI = craccount;
		cancelSubscriptionController = this;

		craccount.AddbuttonApplyactionListener(new ButtonApplyActionListener());
		craccount.addButtonCancelActionListener(new ButtonCancelActionListener());
		cancelSubscriptionGUI.addWindowListener(new CustomWindowListener());
	}
	
	
	class ButtonApplyActionListener implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			
		
				
				Map<String, Object> params = new HashMap<String, Object>();
				
				params.put("msg", "cancelSubscription");
				params.put("username", App.client.getCurrentUser().getUserName());
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(cancelSubscriptionController);
				sendToServer(envelope);	
			}
			
				
			
		}
		
	
	
class ButtonCancelActionListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		
		cancelSubscriptionGUI.dispose();
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
			case "CancelSubscriptionOK":
				JOptionPane.showMessageDialog(null,"Subscription was cancelled!");
				break;
			case "CancelSubscriptionFailed":
				JOptionPane.showMessageDialog(null,"You don't own a subscription","Error", JOptionPane.ERROR_MESSAGE);
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
