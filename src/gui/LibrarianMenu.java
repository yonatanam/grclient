package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controllers.CategoryController;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

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
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(10, 199, 140, 23);
		btnCreateAccount.addActionListener(new CreateAccountListener());
		add(btnCreateAccount);
		
		btnManageBooks = new JButton("Manage books");
		btnManageBooks.setBounds(10, 233, 140, 23);
		btnManageBooks.addActionListener(new ManageBooksListener());
		add(btnManageBooks);
		
		btnPendingReview = new JButton("Pending reviews");
		btnPendingReview.setBounds(10, 267, 140, 23);
		btnPendingReview.addActionListener(new PendingReviewListener());
		add(btnPendingReview);
		
		btnPendingSubscription = new JButton("Pending subscription");
		btnPendingSubscription.setBounds(10, 301, 140, 23);
		btnPendingSubscription.addActionListener(new PendingSubsListener());
		add(btnPendingSubscription);
		
	}
	
	
	/**Action listeners*/
	class ManageBooksListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			currentGUI.dispose();
			//TODO
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
