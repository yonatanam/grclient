package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.App;
import controllers.CategoryController;
import controllers.ManageBooksController;
import gui.UserMenu.CancelSubscriptionListener;
import controllers.PendingReviewsController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;


public class LibrarianMenu extends UserMenu{
	private JButton btnCreateAccount;
	private JButton btnManageBooks;
	private JButton btnPendingReview;
	private JButton btnPendingSubscription;
	private JFrame currentGUI;
	
	

	public LibrarianMenu(JFrame GUI) {
		super(GUI);
		this.currentGUI= GUI;
		setLayout(null);
		
		/*btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(10, 199, 140, 23);
		btnCreateAccount.addActionListener(new CreateAccountListener());
		add(btnCreateAccount);*/
		
		
		//AddReview Button
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setFont(new Font("Candara", Font.BOLD, 15));
		Image buttSmall = new ImageIcon(this.getClass().getResource("/Coverbutton.png")).getImage();
		btnCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnCreateAccount.setIcon(new ImageIcon(buttSmall));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnCreateAccount.setIcon(null);
		    }
		});
		btnCreateAccount.setBounds(-8, 280, 198, 23);
		btnCreateAccount.setOpaque(false);
		btnCreateAccount.setContentAreaFilled(false);
		btnCreateAccount.setBorderPainted(false);
		btnCreateAccount.setHorizontalAlignment(SwingConstants.LEFT);
		btnCreateAccount.setHorizontalTextPosition(JButton.CENTER);
		btnCreateAccount.setVerticalTextPosition(JButton.CENTER);
		btnCreateAccount.setForeground(Color.WHITE);
		btnCreateAccount.addActionListener(new CreateAccountListener());
		add(btnCreateAccount);
		btnCreateAccount.setVisible(true);
		//AddReview Button END
		
		
		
		/*btnManageBooks = new JButton("Manage books");
		btnManageBooks.setBounds(10, 233, 140, 23);
		btnManageBooks.addActionListener(new ManageBooksListener());
		add(btnManageBooks);*/
		
		
		
		//AddReview Button
		btnManageBooks = new JButton("Manage Books");
		btnManageBooks.setFont(new Font("Candara", Font.BOLD, 15));
		btnManageBooks.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnManageBooks.setIcon(new ImageIcon(buttSmall));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnManageBooks.setIcon(null);
		    }
		});
		btnManageBooks.setBounds(-8, 320, 198, 23);
		btnManageBooks.setOpaque(false);
		btnManageBooks.setContentAreaFilled(false);
		btnManageBooks.setBorderPainted(false);
		btnManageBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnManageBooks.setHorizontalTextPosition(JButton.CENTER);
		btnManageBooks.setVerticalTextPosition(JButton.CENTER);
		btnManageBooks.setForeground(Color.WHITE);
		btnManageBooks.addActionListener(new ManageBooksListener());
		add(btnManageBooks);
		btnManageBooks.setVisible(true);
		//AddReview Button END
		
		
		
		/*btnPendingReview = new JButton("Pending reviews");
		btnPendingReview.setBounds(10, 267, 140, 23);
		btnPendingReview.addActionListener(new PendingReviewListener());
		add(btnPendingReview);*/
		
		
		
		//AddReview Button
		btnPendingReview = new JButton("Pending Reviews");
		btnPendingReview.setFont(new Font("Candara", Font.BOLD, 15));
		btnPendingReview.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnPendingReview.setIcon(new ImageIcon(buttSmall));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnPendingReview.setIcon(null);
		    }
		});
		btnPendingReview.setBounds(-8, 360, 198, 23);
		btnPendingReview.setOpaque(false);
		btnPendingReview.setContentAreaFilled(false);
		btnPendingReview.setBorderPainted(false);
		btnPendingReview.setHorizontalAlignment(SwingConstants.LEFT);
		btnPendingReview.setHorizontalTextPosition(JButton.CENTER);
		btnPendingReview.setVerticalTextPosition(JButton.CENTER);
		btnPendingReview.setForeground(Color.WHITE);
		btnPendingReview.addActionListener(new PendingReviewListener());
		add(btnPendingReview);
		btnPendingReview.setVisible(true);
		//AddReview Button END
		
		
		
		
		/*btnPendingSubscription = new JButton("Pending subscription");
		btnPendingSubscription.setBounds(10, 301, 140, 23);
		btnPendingSubscription.addActionListener(new PendingSubsListener());
		add(btnPendingSubscription);*/
		
		
		
		//AddReview Button
		btnPendingSubscription = new JButton("Pending Subscription");
		btnPendingSubscription.setFont(new Font("Candara", Font.BOLD, 15));
		btnPendingSubscription.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnPendingSubscription.setIcon(new ImageIcon(buttSmall));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnPendingSubscription.setIcon(null);
		    }
		});
		btnPendingSubscription.setBounds(-8, 400, 198, 23);
		btnPendingSubscription.setOpaque(false);
		btnPendingSubscription.setContentAreaFilled(false);
		btnPendingSubscription.setBorderPainted(false);
		btnPendingSubscription.setHorizontalAlignment(SwingConstants.LEFT);
		btnPendingSubscription.setHorizontalTextPosition(JButton.CENTER);
		btnPendingSubscription.setVerticalTextPosition(JButton.CENTER);
		btnPendingSubscription.setForeground(Color.WHITE);
		btnPendingSubscription.addActionListener(new PendingSubsListener());
		add(btnPendingSubscription);
		btnPendingSubscription.setVisible(true);
		Image img = new ImageIcon(this.getClass().getResource("/bigMenuLine.png")).getImage();
		//AddReview Button END
		
		
		//Background Image
		JLabel SmallMenuLine = new JLabel();
		SmallMenuLine.setBounds(3, 350, 199, 2);
		SmallMenuLine.setIcon(new ImageIcon(img));
		add(SmallMenuLine);
		//Background Image END
		
		
		//Background Image
		JLabel SmallMenuLine1 = new JLabel();
		SmallMenuLine1.setBounds(3, 390, 199, 2);
		SmallMenuLine1.setIcon(new ImageIcon(img));
		add(SmallMenuLine1);
		//Background Image END
		
		
		//Background Image
		JLabel SmallMenuLine2 = new JLabel();
		SmallMenuLine2.setBounds(3, 430, 199, 2);
		SmallMenuLine2.setIcon(new ImageIcon(img));
		add(SmallMenuLine2);
		//Background Image END
		
		
		/*//Background Image
		JLabel SmallMenuLine3 = new JLabel();
		SmallMenuLine3.setBounds(3, 470, 199, 2);
		SmallMenuLine3.setIcon(new ImageIcon(img));
		add(SmallMenuLine3);
		//Background Image END
*/		
		//Background Image
		JLabel SmallMenuLine4 = new JLabel();
		SmallMenuLine4.setBounds(3, 310, 199, 2);
		SmallMenuLine4.setIcon(new ImageIcon(img));
		add(SmallMenuLine4);
		//Background Image END
		
		if ((App.client.getCurrentUser().getPermission()).equals("LIBRARIAN")){
        JLabel MenuBackGround = new JLabel();
		MenuBackGround.setBounds(0, 0, 800, 600);
		Image img2 = new ImageIcon(this.getClass().getResource("/BigMenu.png")).getImage();
		MenuBackGround.setIcon(new ImageIcon(img2));
		add(MenuBackGround);
		}
		
		
		
	}
	
	
	/**Action listeners*/
	class ManageBooksListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			currentGUI.dispose();
			new ManageBooksController(new ManageBooksGUI());
		}
		
	}
	class CreateAccountListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			currentGUI.dispose();
			//TODO
		}
		
	}
	class PendingReviewListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			currentGUI.dispose();
			PendingReviewsController prc = new PendingReviewsController(new PendingReviewsGUI());
			//TODO
		}
		
	}
	class PendingSubsListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			currentGUI.dispose();
			//TODO
		}
		
	}
	/**End action listeners*/
}
