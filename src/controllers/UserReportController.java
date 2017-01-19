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
import gui.EditUserGUI;
import gui.MainWindowGUI;
import gui.UserReportGUI;
import gui.UserReportGUI;
import gui.ReadWorkerGUI;
import gui.ReportsGUI;
import models.Envelope;
import models.User;;

public class UserReportController extends AbstractController {

	private UserReportGUI userReportGUI;
	private UserReportController userReportController;



	public UserReportController(UserReportGUI urg) {

		this.userReportGUI =urg;
		userReportController = this;  //IMPORTANT
		urg.addButtonBackFromUserReportActionListener(new BackFromUserReportListener());
		urg.addButtonViewOrdersActionListener(new ViewOrdersActionListener());
		urg.addWindowListener(new CustomWindowListener());
		fetchUsersData();


	}


	class BackFromUserReportListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			userReportGUI.dispose();
			new ReportsController(new ReportsGUI());
		}
	}



	class ViewOrdersActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			try
			{
				String userName = (String)userReportGUI.getUsersData().getValueAt(userReportGUI.getUsersData().getSelectedRow(), 0);
				Map<String,Object> params = new HashMap<String,Object>();
				params.put("msg", "getUserOrdersData");
				params.put("username", userName);
				Envelope envelope = new Envelope(params);
				App.client.setCurrentController(getUserReportController());
				sendToServer(envelope);
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null,"User wasn't selected!","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void fetchUsersData()
	{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("msg", "getUsersData");
		Envelope envelope = new Envelope(params);
		App.client.setCurrentController(getUserReportController());
		sendToServer(envelope);

	}

	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		String msg = (String) en.getParams().get("msg");
		switch (msg)
		{
		case "UsersData":
			Vector<Object> usersColumnNames = (Vector<Object>) en.getParams().get("usersColumnNames");
			Vector<Object> usersData = (Vector<Object>) en.getParams().get("usersData");
			userReportGUI.populateUserTable(usersColumnNames, usersData);
			break;
		case "UserOrdersData":
			Vector<Object> ordersColumnNames = (Vector<Object>) en.getParams().get("ordersColumnNames");
			Vector<Object> ordersData = (Vector<Object>) en.getParams().get("ordersData");
			userReportGUI.populateOrdersTable(ordersColumnNames, ordersData);
			break;
		}
	}



	/**Getters and Setters*/
	public UserReportGUI getUserReportGUI() {
		return userReportGUI;
	}

	public void setUserReportGUI(UserReportGUI userReportGUI) {
		this.userReportGUI = userReportGUI;
	}

	public UserReportController getUserReportController() {
		return userReportController;
	}

	public void setUserReportController(UserReportController userReportController) {
		this.userReportController = userReportController;
	}
	/**End getters and setters*/
}

