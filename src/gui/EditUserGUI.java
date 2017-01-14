package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import client.App;
import models.User;

import java.awt.Color;
import javax.swing.JComboBox;

public class EditUserGUI extends JFrame {


	private UserMenu userMenu;
	private JLabel lblUserName;
	private JLabel lblPassword;
	private JTextField userNameTextField;
	private JTextField passwordTextField;
	private JLabel lblFirstName;
	private JTextField fNameTextField;
	private JLabel lblLastName;
	private JTextField lNameTextField;
	private JLabel lblEmail;
	private JTextField emailTextField;
	private JLabel lblPermission;
	private JLabel lblStatus;
	private JComboBox permissionComboBox;
	private JComboBox statusComboBox;
	private JLabel bg;
	private JButton btnBackToManageUsers;
	private JButton btnApply;
	public EditUserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		
		JLabel lblManageUsers = new JLabel("Manage users:");
		lblManageUsers.setForeground(Color.WHITE);
		lblManageUsers.setFont(new Font("Arial", Font.PLAIN, 20));
		lblManageUsers.setBounds(39, 17, 179, 31);
		getContentPane().add(lblManageUsers);
		

		
		
		
		//Back button
		Image imgLogin = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	
		
		
		
		lblUserName = new JLabel("User name:");
		lblUserName.setBounds(39, 55, 99, 25);
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblUserName);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(199, 59, 138, 20);
		userNameTextField.setColumns(10);
		userNameTextField.setEditable(false);
		getContentPane().add(userNameTextField);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(39, 95, 99, 14);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblPassword);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(199, 92, 138, 20);
		passwordTextField.setColumns(10);
		getContentPane().add(passwordTextField);
		
		
		lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(39, 130, 99, 14);
		lblFirstName.setForeground(Color.WHITE);
		lblFirstName.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblFirstName);
		
		fNameTextField = new JTextField();
		fNameTextField.setBounds(199, 127, 138, 20);
		fNameTextField.setColumns(10);
		getContentPane().add(fNameTextField);
		
		lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(39, 165, 99, 14);
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblLastName);

		lNameTextField = new JTextField();
		lNameTextField.setBounds(199, 162, 138, 20);
		lNameTextField.setColumns(10);
		getContentPane().add(lNameTextField);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(39, 199, 63, 14);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblEmail);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(199, 196, 138, 20);
		emailTextField.setColumns(10);
		getContentPane().add(emailTextField);
		
		lblPermission = new JLabel("Permission:");
		lblPermission.setBounds(39, 239, 99, 14);
		lblPermission.setForeground(Color.WHITE);
		lblPermission.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblPermission);
		String [] permissionValues = new String[] {"USER", "LIBRARIAN", "LIBRARY_MANAGER"};
		permissionComboBox = new JComboBox(permissionValues);
		permissionComboBox.setBounds(199, 236, 138, 20);
		getContentPane().add(permissionComboBox);
		
		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(39, 279, 99, 14);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 16));
		getContentPane().add(lblStatus);
		String [] statusValues = new String[] {"ACTIVE", "SUSPENDED", "BLOCKED", "LOGGED_IN"};
		statusComboBox = new JComboBox(statusValues);
		statusComboBox.setBounds(199, 279, 138, 20);
		getContentPane().add(statusComboBox);
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(23, 330, 131, 41);
		btnApply.setFont(new Font("Arial", Font.BOLD, 15));
		btnApply.setIcon(new ImageIcon(imgLogin));
		btnApply.setOpaque(false);
		btnApply.setContentAreaFilled(false);
		btnApply.setBorderPainted(false);
		btnApply.setHorizontalTextPosition(JButton.CENTER);
		btnApply.setVerticalTextPosition(JButton.CENTER);
		btnApply.setForeground(Color.WHITE);
		getContentPane().add(btnApply);
		
		btnBackToManageUsers = new JButton("Back");
		btnBackToManageUsers.setBounds(23, 487, 131, 41);
		btnBackToManageUsers.setFont(new Font("Arial", Font.BOLD, 15));
		btnBackToManageUsers.setIcon(new ImageIcon(imgLogin));
		btnBackToManageUsers.setOpaque(false);
		btnBackToManageUsers.setContentAreaFilled(false);
		btnBackToManageUsers.setBorderPainted(false);
		btnBackToManageUsers.setHorizontalTextPosition(JButton.CENTER);
		btnBackToManageUsers.setVerticalTextPosition(JButton.CENTER);
		btnBackToManageUsers.setForeground(Color.WHITE);
		getContentPane().add(btnBackToManageUsers);
		//Background
		
		bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
	
		
	}

	public void setInitialValues(User user)
	{
		System.out.println(user);
		userNameTextField.setText(user.getUserName());
		passwordTextField.setText(user.getPassword());
		lNameTextField.setText(user.getLastName());
		fNameTextField.setText(user.getFirstName());
		emailTextField.setText(user.getEmail());
		permissionComboBox.setSelectedItem(user.getPermission());
		statusComboBox.setSelectedItem(user.getStatus());
	}
	
	public User getCurrentUserData()
	{
		String userName = userNameTextField.getText();
		String password = passwordTextField.getText();
		String fname = lNameTextField.getText();
		String lname = fNameTextField.getText();
		String email = emailTextField.getText();
		String permission = (String) permissionComboBox.getSelectedItem();
		String status = (String) statusComboBox.getSelectedItem();
		
		return new User(userName,password,fname,lname,email,permission,status);
	
	}
	//Action Listeners

	public void addButtonApplyActionListener(ActionListener e)
	{
		btnApply.addActionListener(e);
	}
	public void addButtonBackToManageUsersActionListener(ActionListener e)
	{
		btnBackToManageUsers.addActionListener(e);
	}
	
//Setters and Getters
	

}
