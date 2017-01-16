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
import gui.ManageUsersGUI;
import gui.ManageUsersGUI;
import gui.ReadWorkerGUI;
import models.Envelope;
import models.User;;

public class ManageUsersController extends AbstractController {

	private ManageUsersGUI manageUsersGUI;
	private ManageUsersController manageUsersController;



	public ManageUsersController(ManageUsersGUI mug) {

		this.manageUsersGUI =mug;
		manageUsersController = this;  //IMPORTANT
		mug.addButtonBackFromManageUsersActionListener(new BackFromManageUsersListener());
		mug.addButtonEditUserActionListener(new EditUserActionListener());
		mug.addWindowListener(new CustomWindowListener());
		fetchUsersData();



	}


	class BackFromManageUsersListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			manageUsersGUI.dispose();
			MainWindowGUI mwg = new MainWindowGUI();
			MainWindowController mwc = new MainWindowController(mwg);


		}
	}



	class EditUserActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			try
			{
				String userName = (String)manageUsersGUI.getUsersData().getValueAt(manageUsersGUI.getUsersData().getSelectedRow(), 0);
				String password = (String)manageUsersGUI.getUsersData().getValueAt(manageUsersGUI.getUsersData().getSelectedRow(), 1);
				String fname = (String)manageUsersGUI.getUsersData().getValueAt(manageUsersGUI.getUsersData().getSelectedRow(), 2);
				String lname = (String)manageUsersGUI.getUsersData().getValueAt(manageUsersGUI.getUsersData().getSelectedRow(), 3);
				String email = (String)manageUsersGUI.getUsersData().getValueAt(manageUsersGUI.getUsersData().getSelectedRow(), 4);
				String permission = (String)manageUsersGUI.getUsersData().getValueAt(manageUsersGUI.getUsersData().getSelectedRow(), 5);
				String status = (String)manageUsersGUI.getUsersData().getValueAt(manageUsersGUI.getUsersData().getSelectedRow(), 6);
				User user = new User(userName,password,fname,lname,email,permission,status);
				System.out.println("fname = "+fname+" lname = "+lname);
				manageUsersGUI.dispose();
				EditUserController euc = new EditUserController(new EditUserGUI(), user);
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
		App.client.setCurrentController(getManageUsersController());
		sendToServer(envelope);

	}

	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;
		Vector<Object> columnNames = (Vector<Object>) en.getParams().get("usersColumnNames");
		Vector<Object> data = (Vector<Object>) en.getParams().get("usersData");
		manageUsersGUI.populateTable(columnNames, data);
	}



	/**Getters and Setters*/
	public ManageUsersGUI getManageUsersGUI() {
		return manageUsersGUI;
	}

	public void setManageUsersGUI(ManageUsersGUI manageUsersGUI) {
		this.manageUsersGUI = manageUsersGUI;
	}

	public ManageUsersController getManageUsersController() {
		return manageUsersController;
	}

	public void setManageUsersController(ManageUsersController manageUsersController) {
		this.manageUsersController = manageUsersController;
	}



	/**End getters and setters*/
}

