package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.App;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class UserMenu extends JPanel{
	private JButton btnAddReview;
	
	public UserMenu() {
		setLayout(null);
		setOpaque(false);
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.setBounds(10, 32, 140, 23);
		add(btnSearchBook);
		
		btnAddReview = new JButton("Add review");
		btnAddReview.setBounds(10, 66, 140, 23);
		add(btnAddReview);
		
		JButton btnSettlePayment = new JButton("Settle Payment");
		btnSettlePayment.setBounds(10, 132, 140, 23);
		add(btnSettlePayment);
		
		JButton btnSearchReview = new JButton("Search Review");
		btnSearchReview.setBounds(10, 100, 140, 23);
		add(btnSearchReview);
		
		JButton btnCancelSubscription = new JButton("Cancel Subscription");
		btnCancelSubscription.setBounds(10, 166, 140, 23);
		add(btnCancelSubscription);
	}
	
	/** Getters and setters*/
	public JButton getBtnAddReview() {
		return btnAddReview;
	}
	public void setBtnAddReview(JButton btnAddReview) {
		this.btnAddReview = btnAddReview;
	}
	/** End getters and setters*/
}
