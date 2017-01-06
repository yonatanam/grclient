package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.SearchReviewController;
import controllers.SettlePaymentController;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class ManagerMenu extends LibrarianMenu{
	private JButton btnManageUsers;
	private JButton btnReports;
	private JButton btnManageCatalog;
	private JFrame currentGUI;
	public ManagerMenu(JFrame GUI) {
		super(GUI);
		this.currentGUI=GUI;
		setLayout(null);
		
		
		btnManageUsers = new JButton("Manage Users");
		btnManageUsers.setBounds(10, 334, 140, 23);
		btnManageUsers.addActionListener(new ManageUsersListener());
		add(btnManageUsers);
		
		btnReports = new JButton("Reports");
		btnReports.setBounds(10, 368, 140, 23);
		btnReports.addActionListener(new ReportsListener());
		add(btnReports);
		
		btnManageCatalog = new JButton("Manage Catalog");
		btnManageCatalog.setBounds(10, 402, 140, 23);
		btnManageCatalog.addActionListener(new ManageCatalogListener());
		add(btnManageCatalog);
	}
	
	
	
	/**Action listeners*/
	class ManageUsersListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentGUI.dispose();
			//TODO
		}		
	}	

	class ReportsListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentGUI.dispose();
			//TODO		
		}		
	}		

	class ManageCatalogListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentGUI.dispose();
			//TODO		
		}		
	}	
	/**End action listeners*/
}
//
//
