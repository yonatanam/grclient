package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.App;
import controllers.PublishReviewController;
import controllers.SearchReviewController;
import controllers.SettlePaymentController;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class UserMenu extends JPanel{
	private JButton btnAddReview;
	private JButton btnSettlePayment;
	private JButton btnSearchReview;
	private JButton btnCancelSubscription;
	private JFrame currentGUI;


	public UserMenu(JFrame GUI) {
		this.currentGUI = GUI;
		setLayout(null);
		setOpaque(false);
		JButton btnSearchBook = new JButton("Search Book");
		btnSearchBook.setBounds(10, 32, 140, 23);
		add(btnSearchBook);

		btnAddReview = new JButton("Add review");
		btnAddReview.setBounds(10, 66, 140, 23);
		btnAddReview.addActionListener(new PublishReviewListener());
		add(btnAddReview);

		btnSettlePayment = new JButton("Settle Payment");
		btnSettlePayment.setBounds(10, 132, 140, 23);
		btnSettlePayment.addActionListener(new SettlePaymentListener());
		add(btnSettlePayment);

		btnSearchReview = new JButton("Search Review");
		btnSearchReview.setBounds(10, 100, 140, 23);
		btnSearchReview.addActionListener(new SearchReviewListener());
		add(btnSearchReview);

		btnCancelSubscription = new JButton("Cancel Subscription");
		btnCancelSubscription.setBounds(10, 166, 140, 23);
		btnCancelSubscription.addActionListener(new CancelSubscriptionListener());
		add(btnCancelSubscription);
	}

	


	/**Action listeners*/

	class PublishReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			currentGUI.dispose();
			PublishReviewGUI prg = new PublishReviewGUI();
			PublishReviewController prc = new PublishReviewController(prg);

		}
	}
	class SettlePaymentListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentGUI.dispose();
			new SettlePaymentController(new SettlePaymentGUI());
		}		
	}	

	class SearchReviewListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentGUI.dispose();
			new SearchReviewController(new SearchReviewGUI());			
		}		
	}		

	class CancelSubscriptionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			currentGUI.dispose();
			//TODO		
		}		
	}	

	/**End action listeners*/
}
