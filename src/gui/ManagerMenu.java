package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ManagerMenu extends LibrarianMenu{
	
	public ManagerMenu() {
		
		setLayout(null);
		
		
		JButton btnManageUsers = new JButton("Manage Users");
		btnManageUsers.setBounds(10, 334, 140, 23);
		add(btnManageUsers);
		
		JButton btnReports = new JButton("Reports");
		btnReports.setBounds(10, 368, 140, 23);
		add(btnReports);
		
		JButton btnManageCatalog = new JButton("Manage Catalog");
		btnManageCatalog.setBounds(10, 402, 140, 23);
		add(btnManageCatalog);
	}
	
	/** Getters and setters*/

	/** End getters and setters*/
}
//
//
