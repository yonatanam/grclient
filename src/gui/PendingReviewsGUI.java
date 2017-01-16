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

public class PendingReviewsGUI extends JFrame {


	private JButton BackButton;
	private JTable reviewsData;
	private JScrollPane scrollPane;
	private UserMenu userMenu;
	private JButton btnApprove;
	private JButton btnDeny;
	private JButton btnEditReview;

	public PendingReviewsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		
		JLabel lblPendingReviews = new JLabel("Pending reviews:");
		lblPendingReviews.setForeground(Color.WHITE);
		lblPendingReviews.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPendingReviews.setBounds(39, 17, 179, 31);
		getContentPane().add(lblPendingReviews);
		
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		
		
		
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
		
		reviewsData = new JTable();
		scrollPane.setViewportView(reviewsData);
		reviewsData.setDefaultEditor(Object.class, null); //prevents from editing the fields but allows row selection
		reviewsData.getTableHeader().setReorderingAllowed(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		btnEditReview = new JButton("Edit review");
		btnEditReview.setBounds(22, 412, 131, 41);
		btnEditReview.setFont(new Font("Arial", Font.BOLD, 15));
		btnEditReview.setIcon(new ImageIcon(imgLogin));
		btnEditReview.setOpaque(false);
		btnEditReview.setContentAreaFilled(false);
		btnEditReview.setBorderPainted(false);
		btnEditReview.setHorizontalTextPosition(JButton.CENTER);
		btnEditReview.setVerticalTextPosition(JButton.CENTER);
		btnEditReview.setForeground(Color.WHITE);
		getContentPane().add(btnEditReview);
		
		
		btnApprove = new JButton("Approve");
		btnApprove.setBounds(290, 412, 131, 41);
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
		btnDeny.setBounds(151, 412, 131, 41);
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
	public void addButtonApproveReviewActionListener(ActionListener e)
	{
		btnApprove.addActionListener(e);
	}
	public void addButtonDenyReviewActionListener(ActionListener e)
	{
		btnDeny.addActionListener(e);
	}
	public void addButtonEditReviewActionListener(ActionListener e)
	{
		btnEditReview.addActionListener(e);
	}
	public void addButtonBackFromReadFromWorkerActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
//Setters and Getters
	
	public JTable getReviewsData() {
		return reviewsData;
	}

	public void setReviewsData(JTable data) {
		this.reviewsData = data;
	}
	
	public void populateTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		reviewsData.setModel(model);
	}
}
