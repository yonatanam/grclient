package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSet;

import client.App;
import controllers.AbstractController.CustomWindowListener;
import gui.EditReviewGUI;
import gui.MainWindowGUI;
import gui.PendingReviewsGUI;
import gui.PendingSubscriptionGUI;
import gui.ReadWorkerGUI;
import models.Envelope;;

public class PendingSubscriptionController extends AbstractController {

	private PendingSubscriptionGUI pendingSubscriptionGUI;
	private PendingSubscriptionController pendingSubscriptionController;




	public PendingSubscriptionController(PendingSubscriptionGUI psg) {

		this.pendingSubscriptionGUI =psg;
		pendingSubscriptionController = this;  //IMPORTANT
		psg.addButtonBackFromPendingSubscriptionrActionListener(new BackFromPendingSubscriptionListener());
		psg.addButtonApproveSubscriptionActionListener(new ApproveSubscriptionActionListener());
		psg.addButtonDenySubscriptionActionListener(new DenySubscriptionActionListener());
		psg.addWindowListener(new CustomWindowListener());
		fetchSubscriptionData();



	}




	class BackFromPendingSubscriptionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			pendingSubscriptionGUI.dispose();
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);


		}
	}

	class ApproveSubscriptionActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			Map<String,Object> params = new HashMap<String,Object>();
			Envelope en = new Envelope(params);
			try
			{
			params.put("msg", "approve_subscription");
			params.put("username", pendingSubscriptionGUI.getSubscriptionData().getValueAt(pendingSubscriptionGUI.getSubscriptionData().getSelectedRow(), 0));
			System.out.println("selected user id is "+ params.get("username"));	
			sendToServer(en);
			fetchSubscriptionData();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Subscription request wasn't selected!","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	class DenySubscriptionActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			Map<String,Object> params = new HashMap<String,Object>();
			Envelope en = new Envelope(params);
			try
			{
			params.put("msg", "deny_subscription");
			params.put("username", pendingSubscriptionGUI.getSubscriptionData().getValueAt(pendingSubscriptionGUI.getSubscriptionData().getSelectedRow(), 0));
			System.out.println("selected user id is "+ params.get("username"));	
			sendToServer(en);
			fetchSubscriptionData();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,"Subscription request wasn't selected!","Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}


	public void fetchSubscriptionData()
	{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getPendingSubscription");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(getPendingSubscriptionController());
		sendToServer(envelope);

	}

	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;

		switch ((String)en.getParams().get("msg"))
		{
		case "approveSubscriptionOK":
			JOptionPane.showMessageDialog(null,"Review was edited successfuly!");
			break;
		case "denySubscriptionOK":
			JOptionPane.showMessageDialog(null,"Review editing failed!","Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		Vector<Object> columnNames = (Vector<Object>) en.getParams().get("subscriptionColumnNames");
		Vector<Object> data = (Vector<Object>) en.getParams().get("subscriptionData");
		pendingSubscriptionGUI.populateTable(columnNames, data);

	}
	/**Getters and Setters*/
	public PendingSubscriptionGUI getPendingSubscriptionGUI() {
		return pendingSubscriptionGUI;
	}

	public void setPendingSubscriptionGUI(PendingSubscriptionGUI pendingSubscriptionGUI) {
		this.pendingSubscriptionGUI = pendingSubscriptionGUI;
	}

	public PendingSubscriptionController getPendingSubscriptionController() {
		return pendingSubscriptionController;
	}

	public void setPendingSubscriptionController(PendingSubscriptionController pendingSubscriptionController) {
		this.pendingSubscriptionController = pendingSubscriptionController;
	}

	
	
	/**End getters and setters*/
}

