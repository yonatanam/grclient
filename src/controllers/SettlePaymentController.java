package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Vector;
import client.App;
import gui.MainWindowGUI;
import gui.SettlePaymentGUI;
import models.Envelope;

public class SettlePaymentController extends AbstractController
{
	private SettlePaymentGUI spGUI;
	//private LoginModel loginModel;
	
	public SettlePaymentController(SettlePaymentGUI sp/*, LoginModel lm*/)
	{
		spGUI = sp;
		getSubscriptionNames();
		spGUI.addSubmitActionListener(new submitHandler());
		spGUI.addBtnBackActionListener(new BackHandler());
		spGUI.addCbSubscriptionTypeActionListener(new ComboboxSubscriptionHandler());
		spGUI.setVisible(true);
		spGUI.addWindowListener(new CustomWindowListener());
		
	}
	
	class submitHandler implements ActionListener 
	{
		private boolean valid;
		@Override
		public void actionPerformed(ActionEvent ae) 
		{			
			valid = true;
			if(!isValidlenNumber(spGUI.getCardNumber(), spGUI.getMaxlenCardNumber()))
			{
				spGUI.setLblErrorCardNum("Please enter " + spGUI.getMaxlenCardNumber() + " digits.");
				valid = false;
			}
			else
				spGUI.setLblErrorCardNum("");
			
			if(!isValidlenNumber(spGUI.getSecurityCode(), spGUI.getMaxLenSecuritydNumber()))
			{
				spGUI.setLblErrorSecurity("Please enter " + spGUI.getMaxLenSecuritydNumber() + " digits.");
				valid = false;
			}
			else
				spGUI.setLblErrorSecurity("");
			
			if(!isValidDate())
			{
				spGUI.setLblErrorExpiration("Invalid date.");
				valid = false;
			}
			else
				spGUI.setLblErrorExpiration("");
			
			if(valid) //everything is valid
			{
			
			}

		}
	}
	
	private SettlePaymentController getSettlePaymentController()
	{
		return this;
	}
	
	public boolean isValidlenNumber(String number, int maxLen)
	{
		if(number.length() < maxLen)
			return false;
		return true;
	}
	
	public boolean isValidDate()
	{
		LocalDateTime now = LocalDateTime.now();
		if(spGUI.getYear() == now.getYear())
			if(spGUI.getMonth() < now.getMonthValue())
				return false;
		return true;
	}
	
	public void getSubscriptionNames()
	{		
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getSubscriptionsNames");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(getSettlePaymentController());
		sendToServer(envelope);
	}
	
	private class BackHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{			
			spGUI.dispose();
			new MainWindowController(new MainWindowGUI());
		}
	}
	
	private class ComboboxSubscriptionHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println(":aa");
			spGUI.viewSubscriptionDes();
		}	
	}
	
	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		String msg = (String) en.getParams().get("msg");
		if(msg.equals("getSubscriptionsNames"))
		{
			Vector<Object> data = (Vector<Object>) en.getParams().get("data");
			String[] descriptions = new String[data.size()];
			for(int  i = 0; i < data.size(); i++)
			{
				spGUI.addItemToCbSubscriptionName((String)((Vector<Object>) data.get(i)).get(0));
				descriptions[i] = (String)((Vector<Object>) data.get(i)).get(1);
			}
			
			spGUI.setSubscriptionDesc(descriptions);
			spGUI.viewSubscriptionDes();
		}
		else
		{
	
		}
	}
}
