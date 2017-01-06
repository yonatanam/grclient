package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;


public class LibrarianMenu extends UserMenu{
	private JButton btnCreateAccount;
	private JButton btnManageBooks;
	private JButton btnPendingReview;
	private JButton btnPendingSubscription;
	
	

	public LibrarianMenu() {
		
		setLayout(null);
		
		btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(10, 199, 140, 23);
		add(btnCreateAccount);
		
		btnManageBooks = new JButton("Manage books");
		btnManageBooks.setBounds(10, 233, 140, 23);
		add(btnManageBooks);
		
		btnPendingReview = new JButton("Pending reviews");
		btnPendingReview.setBounds(10, 267, 140, 23);
		add(btnPendingReview);
		
		btnPendingSubscription = new JButton("Pending subscription");
		btnPendingSubscription.setBounds(10, 301, 140, 23);
		add(btnPendingSubscription);
		
	}
	
	/**Getters and setters*/
	public JButton getBtnCreateAccount() {
		return btnCreateAccount;
	}

	public void setBtnCreateAccount(JButton btnCreateAccount) {
		this.btnCreateAccount = btnCreateAccount;
	}

	public JButton getBtnManageBooks() {
		return btnManageBooks;
	}

	public void setBtnManageBooks(JButton btnManageBooks) {
		this.btnManageBooks = btnManageBooks;
	}

	public JButton getBtnPendingReview() {
		return btnPendingReview;
	}

	public void setBtnPendingReview(JButton btnPendingReview) {
		this.btnPendingReview = btnPendingReview;
	}

	public JButton getBtnPendingSubscription() {
		return btnPendingSubscription;
	}

	public void setBtnPendingSubscription(JButton btnPendingSubscription) {
		this.btnPendingSubscription = btnPendingSubscription;
	}
	/** End getters and setters*/
}
