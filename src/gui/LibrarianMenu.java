package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LibrarianMenu extends UserMenu{
	
	
	public LibrarianMenu() {
		
		setLayout(null);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.setBounds(10, 199, 140, 23);
		add(btnCreateAccount);
		
		JButton btnManageBooks = new JButton("Manage books");
		btnManageBooks.setBounds(10, 233, 140, 23);
		add(btnManageBooks);
		
		JButton btnPendingReview = new JButton("Pending reviews");
		btnPendingReview.setBounds(10, 267, 140, 23);
		add(btnPendingReview);
		
		JButton btnPendingSubscription = new JButton("Pending subscription");
		btnPendingSubscription.setBounds(10, 301, 140, 23);
		add(btnPendingSubscription);
		

	}
	
	/** Getters and setters*/

}
