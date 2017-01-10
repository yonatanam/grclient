package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import client.App;
import gui.MainWindowGUI;
import gui.SettlePaymentGUI;
import models.Envelope;

public class SettlePaymentController extends AbstractController
{
	private SettlePaymentGUI spGUI;
	private LocalDateTime today = LocalDateTime.now();
		
	public SettlePaymentController(SettlePaymentGUI sp)
	{
		App.client.setCurrentController(this);
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
		private HashMap<String,Object> params;
		private String username;
		private String cardNumber;
		private String ccv;
		private int year;
		private int month;
		private int subscriptionType;
		private String todayString;
		private SimpleDateFormat DateSQLFormat = new SimpleDateFormat("Y-M-d");
		private Date date = new Date();
		
		
		@Override
		public void actionPerformed(ActionEvent ae) 
		{	
			valid = true;
			
			HashMap<String,Object> params = new HashMap<String,Object>();
			username = App.client.getCurrentUser().getUserName();
			cardNumber = spGUI.getCardNumber();
			ccv = spGUI.getSecurityCode();
			year = spGUI.getYear();
			month = spGUI.getMonth();
			subscriptionType = spGUI.getSubscriptioTypeIndex();					
			todayString = DateSQLFormat.format(date);

			if(!isValidlenNumber(cardNumber, spGUI.getMaxlenCardNumber()))
			{
				spGUI.setLblErrorCardNum("Please enter " + spGUI.getMaxlenCardNumber() + " digits.");
				valid = false;
			}
			else
				spGUI.setLblErrorCardNum("");
			
			if(!isValidlenNumber(ccv, spGUI.getMaxLenSecuritydNumber()))
			{
				spGUI.setLblErrorSecurity("Please enter " + spGUI.getMaxLenSecuritydNumber() + " digits.");
				valid = false;
			}
			else
				spGUI.setLblErrorSecurity("");
			
			if(!isValidDate(year,month))
			{
				spGUI.setLblErrorExpiration("Invalid date.");
				valid = false;
			}
			else
				spGUI.setLblErrorExpiration("");
			
			if(valid) //everything is valid
			{				
				params.put("msg", "submitSettlePayment");
				params.put("username", username);
				params.put("cardNumber", cardNumber);
				params.put("ccv", ccv);
				params.put("expirationDate", month + "/" + year);				
				params.put("subscriptionType", subscriptionType);
				params.put("commencementOfSubscription", todayString);				
				Envelope envelope = new Envelope(params);				
				sendToServer(envelope);
			}

		}
	}
	
	private boolean isValidlenNumber(String number, int maxLen)
	{
		if(number.length() < maxLen)
			return false;
		return true;
	}
	
	private boolean isValidDate(int year, int month)
	{		
		if(year == today.getYear())
			if(month < today.getMonthValue())
				return false;
		return true;
	}
	
	private void getSubscriptionNames()
	{		
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getSubscriptionsNames");
		Envelope envelope = new Envelope(params);
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
			spGUI.viewSubscriptionDes();
		}	
	}
	
	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		String msg = (String) en.getParams().get("msg");
		
		switch(msg)
		{
			case "getSubscriptionsNames":
				Vector<Object> data = (Vector<Object>) en.getParams().get("data");
				String[] subscriptionsNames = new String[data.size()];
				String[] descriptions = new String[data.size()];
				for(int  i = 0; i < data.size(); i++)
				{
					subscriptionsNames[i] = ((String)((Vector<Object>) data.get(i)).get(0));
					descriptions[i] = (String)((Vector<Object>) data.get(i)).get(1);
					descriptions[i] = descriptions[i].replaceAll(Pattern.quote("\\n"), "\n");
				}
				spGUI.setSubscriptionDesc(descriptions);
				for(int  i = 0; i < data.size(); i++)
					spGUI.addItemToCbSubscriptionName((String)((Vector<Object>) data.get(i)).get(0));
							
				spGUI.viewSubscriptionDes();
				break;
			
			case "SettlePaymentSucceeded":
				JOptionPane.showMessageDialog(spGUI,"Settle payment has been succeeded.\nYou can purchase books now.","Confirmation", JOptionPane.INFORMATION_MESSAGE);   
				break;
					
			case "SettlePaymentFalied":
				JOptionPane.showMessageDialog(spGUI,"User has settled his payment already!","Error", JOptionPane.ERROR_MESSAGE);   
				break;
		}
	}
}
