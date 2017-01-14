package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.ManageUsersController;
import controllers.SearchReviewController;
import controllers.SettlePaymentController;
import gui.UserMenu.CancelSubscriptionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ManagerMenu extends LibrarianMenu{
	private JButton btnManageUsers;
	private JButton btnReports;
	private JButton btnManageCatalog;
	private JFrame currentGUI;
	public ManagerMenu(JFrame GUI) {
		super(GUI);
		this.currentGUI=GUI;
		setLayout(null);
		
		
		/*btnManageUsers = new JButton("Manage Users");
		btnManageUsers.setBounds(10, 334, 140, 23);
		btnManageUsers.addActionListener(new ManageUsersListener());
		add(btnManageUsers);*/
		
		
		//AddReview Button
		btnManageUsers = new JButton("Manage Users");
		btnManageUsers.setFont(new Font("Candara", Font.BOLD, 15));
		Image buttSmall1 = new ImageIcon(this.getClass().getResource("/Coverbutton.png")).getImage();
		btnManageUsers.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnManageUsers.setIcon(new ImageIcon(buttSmall1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnManageUsers.setIcon(null);
		    }
		});
		btnManageUsers.setBounds(-8, 440, 199, 23);
		btnManageUsers.setOpaque(false);
		btnManageUsers.setContentAreaFilled(false);
		btnManageUsers.setBorderPainted(false);
		btnManageUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnManageUsers.setHorizontalTextPosition(JButton.CENTER);
		btnManageUsers.setVerticalTextPosition(JButton.CENTER);
		btnManageUsers.setForeground(Color.WHITE);
		btnManageUsers.addActionListener(new ManageUsersListener());
		add(btnManageUsers);
		btnManageUsers.setVisible(true);
		//AddReview Button END
		
		
		/*btnReports = new JButton("Reports");
		btnReports.setBounds(10, 368, 140, 23);
		btnReports.addActionListener(new ReportsListener());
		add(btnReports);*/
		
		
		
		//AddReview Button
		btnReports = new JButton("Reports");
		btnReports.setFont(new Font("Candara", Font.BOLD, 15));
		btnReports.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnReports.setIcon(new ImageIcon(buttSmall1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnReports.setIcon(null);
		    }
		});
		btnReports.setBounds(-8, 480, 199, 23);
		btnReports.setOpaque(false);
		btnReports.setContentAreaFilled(false);
		btnReports.setBorderPainted(false);
		btnReports.setHorizontalAlignment(SwingConstants.LEFT);
		btnReports.setHorizontalTextPosition(JButton.CENTER);
		btnReports.setVerticalTextPosition(JButton.CENTER);
		btnReports.setForeground(Color.WHITE);
		btnReports.addActionListener(new ReportsListener());
		add(btnReports);
		btnReports.setVisible(true);
		//AddReview Button END
		
		
		
		/*btnManageCatalog = new JButton("Manage Catalog");
		btnManageCatalog.setBounds(10, 402, 140, 23);
		btnManageCatalog.addActionListener(new ManageCatalogListener());
		add(btnManageCatalog);*/
		
		
		
		//AddReview Button
		btnManageCatalog = new JButton("Manage Catalog");
		btnManageCatalog.setFont(new Font("Candara", Font.BOLD, 15));
		btnManageCatalog.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnManageCatalog.setIcon(new ImageIcon(buttSmall1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnManageCatalog.setIcon(null);
		    }
		});
		btnManageCatalog.setBounds(-8, 520, 199, 23);
		btnManageCatalog.setOpaque(false);
		btnManageCatalog.setContentAreaFilled(false);
		btnManageCatalog.setBorderPainted(false);
		btnManageCatalog.setHorizontalAlignment(SwingConstants.LEFT);
		btnManageCatalog.setHorizontalTextPosition(JButton.CENTER);
		btnManageCatalog.setVerticalTextPosition(JButton.CENTER);
		btnManageCatalog.setForeground(Color.WHITE);
		btnManageCatalog.addActionListener(new ManageCatalogListener());
		add(btnManageCatalog);
		btnManageCatalog.setVisible(true);
		//AddReview Button END
		
		
		
		
		//Background Image
		JLabel SmallMenuLine = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/bigMenuLine.png")).getImage();
		SmallMenuLine.setBounds(3, 510, 199, 2);
		SmallMenuLine.setIcon(new ImageIcon(img));
		add(SmallMenuLine);
		//Background Image END
		
		
		
		//Background Image
		JLabel SmallMenuLine1 = new JLabel();
		SmallMenuLine1.setBounds(3, 550, 199, 2);
		SmallMenuLine1.setIcon(new ImageIcon(img));
		add(SmallMenuLine1);
		//Background Image END
		
		
		//Background Image
		JLabel SmallMenuLine2 = new JLabel();
		SmallMenuLine2.setBounds(3, 470, 199, 2);
		SmallMenuLine2.setIcon(new ImageIcon(img));
		add(SmallMenuLine2);
		//Background Image END
		
		
		//Background Image
		JLabel SmallMenuLine3 = new JLabel();
		SmallMenuLine3.setBounds(3, 590, 199, 2);
		SmallMenuLine3.setIcon(new ImageIcon(img));
		add(SmallMenuLine3);
		//Background Image END
		
		
		
		//Background Image
		JLabel MenuBackGround = new JLabel();
		MenuBackGround.setBounds(0, 0, 800, 600);
		Image img2 = new ImageIcon(this.getClass().getResource("/BigMenu.png")).getImage();
		MenuBackGround.setIcon(new ImageIcon(img2));
		add(MenuBackGround);
		//Background Image END
		
	}
	
	
	
	/**Action listeners*/
	class ManageUsersListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentGUI.dispose();
			new ManageUsersController(new ManageUsersGUI());
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
				
		}		
	}	
	/**End action listeners*/
}
//
//
