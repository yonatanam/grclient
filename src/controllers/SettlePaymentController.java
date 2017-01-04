package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import gui.SettlePaymentGUI;

public class SettlePaymentController extends  AbstractController
{
	private SettlePaymentGUI spGUI;
	//private LoginModel loginModel;
	
	public SettlePaymentController(SettlePaymentGUI sp/*, LoginModel lm*/)
	{
		spGUI = sp;
		//spGUI.addCbSubscriptionTypeActionListener(new handler());
		getSubscriptionNames();
		spGUI.addSubmitActionListener(new submitHandler());
		//spGUI.addTextFiledListener(new HandleJtextField(2));
		
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
		/*
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getSubscriptionsNames");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(spController);
		sendToServer(envelope);
		*/
	}
}
