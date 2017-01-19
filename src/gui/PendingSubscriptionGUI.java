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

public class PendingSubscriptionGUI extends JFrame {


	private JButton BackButton;
	private JTable subscriptionData;
	private JScrollPane scrollPane;
	private UserMenu userMenu;
	private JButton btnApprove;
	private JButton btnDeny;

	public PendingSubscriptionGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		
		JLabel lblPendingSubscription = new JLabel("Pending subscription requests");
		lblPendingSubscription.setForeground(Color.WHITE);
		lblPendingSubscription.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
		lblPendingSubscription.setBounds(63, 11, 453, 31);
		getContentPane().add(lblPendingSubscription);
		

		
		
		
		//Back button
		Image imgLogin = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setIcon(new ImageIcon(imgLogin));
		BackButton.setBounds(646, 499, 106, 41);
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
		
		subscriptionData = new JTable();
		scrollPane.setViewportView(subscriptionData);
		subscriptionData.setDefaultEditor(Object.class, null); //prevents from editing the fields but allows row selection
		subscriptionData.getTableHeader().setReorderingAllowed(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		btnApprove = new JButton("Approve");
		btnApprove.setBounds(144, 430, 131, 41);
		btnApprove.setFont(new Font("Arial", Font.BOLD, 15));
		btnApprove.setIcon(new ImageIcon(imgLogin));
		btnApprove.setOpaque(false);
		btnApprove.setContentAreaFilled(false);
		btnApprove.setBorderPainted(false);
		btnApprove.setHorizontalTextPosition(JButton.CENTER);
		btnApprove.setVerticalTextPosition(JButton.CENTER);
		btnApprove.setForeground(Color.WHITE);
		getContentPane().add(btnApprove);
		
		btnDeny = new JButton("Deny");
		btnDeny.setBounds(24, 430, 131, 41);
		btnDeny.setFont(new Font("Arial", Font.BOLD, 15));
		btnDeny.setIcon(new ImageIcon(imgLogin));
		btnDeny.setOpaque(false);
		btnDeny.setContentAreaFilled(false);
		btnDeny.setBorderPainted(false);
		btnDeny.setHorizontalTextPosition(JButton.CENTER);
		btnDeny.setVerticalTextPosition(JButton.CENTER);
		btnDeny.setForeground(Color.WHITE);
		getContentPane().add(btnDeny);
		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		
		
		//BackGround END
		
		
		
	}


	//Action Listeners
	public void addButtonApproveSubscriptionActionListener(ActionListener e)
	{
		btnApprove.addActionListener(e);
	}
	public void addButtonDenySubscriptionActionListener(ActionListener e)
	{
		btnDeny.addActionListener(e);
	}
	public void addButtonBackFromPendingSubscriptionrActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
//Setters and Getters
	
	public JTable getSubscriptionData() {
		return subscriptionData;
	}

	public void setSubscriptionData(JTable data) {
		this.subscriptionData = data;
	}
	
	public void populateTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		subscriptionData.setModel(model);
	}
}
