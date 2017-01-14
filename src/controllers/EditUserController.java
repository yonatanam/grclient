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

public class EditUserController extends AbstractController {

	private EditUserGUI editUserGUI;
	private EditUserController editUserController;




	public EditUserController(EditUserGUI editUserGUI, User user) {

		this.editUserGUI =editUserGUI;
		editUserController = this;  //IMPORTANT
		editUserGUI.addButtonBackToManageUsersActionListener(new BackToManageUsersActionListener());
		editUserGUI.addButtonApplyActionListener(new ApplyActionListener());
		editUserGUI.addWindowListener(new CustomWindowListener());
		editUserGUI.setInitialValues(user);
	}


	class BackToManageUsersActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			editUserGUI.dispose();
			new ManageUsersController(new ManageUsersGUI());
		}
	}



	class ApplyActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			System.out.println("Apply clicked");
			Map<String,Object> params = new HashMap<String,Object>();
			Envelope en = new Envelope(params);
			params.put("msg", "changeUserData");
			params.put("user", editUserGUI.getCurrentUserData());
			App.client.setCurrentController(getEditUserController());
			sendToServer(en);
		}
	}



	public void handleDBResult(Object message)
	{	
		Envelope en = (Envelope)message;

		switch ((String)en.getParams().get("msg"))
		{
		case "editUserOK":
			JOptionPane.showMessageDialog(null,"User was edited successfuly!");
			break;
		case "editUserNOTOK":
			JOptionPane.showMessageDialog(null,"User editing failed!","Error", JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	}
	
	
	
	/**Getters and Setters*/


	public EditUserGUI getEditUserGUI() {
		return editUserGUI;
	}



	public void setEditUserGUI(EditUserGUI editUserGUI) {
		this.editUserGUI = editUserGUI;
	}



	public EditUserController getEditUserController() {
		return editUserController;
	}



	public void setEditUserController(EditUserController editUserController) {
		this.editUserController = editUserController;
	}


	

	/**End getters and setters*/
}

