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

import java.awt.Color;
import javax.swing.JComboBox;

public class ManageUsersGUI extends JFrame {


	private JButton BackButton;
	
	private JTable usersData;
	private JScrollPane scrollPane;


	private UserMenu userMenu;
	private JButton btnEditUser;
	
	public ManageUsersGUI() {
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
		BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setIcon(new ImageIcon(imgLogin));
		BackButton.setBounds(32, 464, 106, 41);
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setForeground(Color.WHITE);
		getContentPane().add(BackButton);
		//Back button END
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 63, 713, 338);
		getContentPane().add(scrollPane);
		
		usersData = new JTable();
		scrollPane.setViewportView(usersData);
		usersData.setDefaultEditor(Object.class, null); //prevents from editing the fields but allows row selection
		usersData.getTableHeader().setReorderingAllowed(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		btnEditUser = new JButton("Edit user");
		btnEditUser.setBounds(22, 412, 131, 41);
		btnEditUser.setFont(new Font("Arial", Font.BOLD, 15));
		btnEditUser.setIcon(new ImageIcon(imgLogin));
		btnEditUser.setOpaque(false);
		btnEditUser.setContentAreaFilled(false);
		btnEditUser.setBorderPainted(false);
		btnEditUser.setHorizontalTextPosition(JButton.CENTER);
		btnEditUser.setVerticalTextPosition(JButton.CENTER);
		btnEditUser.setForeground(Color.WHITE);
		getContentPane().add(btnEditUser);
		
	
		
		

		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		
	}
	//Action Listeners

	public void addButtonEditUserActionListener(ActionListener e)
	{
		btnEditUser.addActionListener(e);
	}
	public void addButtonBackFromManageUsersActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
//Setters and Getters
	
	public JTable getUsersData() {
		return usersData;
	}

	public void setUsersData(JTable data) {
		this.usersData = data;
	}
	
	public void populateTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		
		usersData.setModel(model);
		
	}
	public JButton getBackButton() {
		return BackButton;
	}

	public void setBackButton(JButton backButton) {
		BackButton = backButton;
	}
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
}
