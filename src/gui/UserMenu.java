package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.App;
import controllers.LoginController;
import controllers.PublishReviewController;
import controllers.SearchReviewController;
import controllers.SettlePaymentController;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;


public class UserMenu extends JPanel{
	private JButton btnAddReview;
	private JButton btnSettlePayment;
	private JButton btnSearchReview;
	private JButton btnCancelSubscription;
	private JButton btnLogout;
	private JFrame currentGUI;

	public UserMenu(JFrame GUI) {
		this.currentGUI = GUI;
		setLayout(null);
		setOpaque(false);
		//AddReview Button END
		
		

		/*btnAddReview = new JButton("Add review");
		btnAddReview.setBounds(10, 66, 140, 23);
		btnAddReview.addActionListener(new PublishReviewListener());
		add(btnAddReview);
		*/
		
		//AddReview Button
		btnAddReview = new JButton("Add Review");
		btnAddReview.setFont(new Font("Candara", Font.BOLD, 15));
		Image buttSmall = new ImageIcon(this.getClass().getResource("/Coverbutton.png")).getImage();
		btnAddReview.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnAddReview.setIcon(new ImageIcon(buttSmall));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnAddReview.setIcon(null);
		    }
		});
		btnAddReview.setBounds(-8, 120, 210, 23);
		btnAddReview.setOpaque(false);
		btnAddReview.setContentAreaFilled(false);
		btnAddReview.setBorderPainted(false);
		btnAddReview.setHorizontalAlignment(SwingConstants.LEFT);
		btnAddReview.setHorizontalTextPosition(JButton.CENTER);
		btnAddReview.setVerticalTextPosition(JButton.CENTER);
		btnAddReview.setForeground(Color.WHITE);
		btnAddReview.addActionListener(new PublishReviewListener());
		btnAddReview.setRolloverIcon(new ImageIcon(buttSmall));
		add(btnAddReview);
		btnAddReview.setVisible(true);
		//AddReview Button END
		
		
		
		/*btnSettlePayment = new JButton("Settle Payment");
		btnSettlePayment.setBounds(10, 132, 140, 23);
		btnSettlePayment.addActionListener(new SettlePaymentListener());
		add(btnSettlePayment);*/
		
		
		//AddReview Button
		btnSettlePayment = new JButton("Settle Payment");
		btnSettlePayment.setFont(new Font("Candara", Font.BOLD, 15));
		btnSettlePayment.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnSettlePayment.setIcon(new ImageIcon(buttSmall));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnSettlePayment.setIcon(null);
		    }
		});
		btnSettlePayment.setBounds(-8, 200, 210, 23);
		btnSettlePayment.setOpaque(false);
		btnSettlePayment.setContentAreaFilled(false);
		btnSettlePayment.setBorderPainted(false);
		btnSettlePayment.setHorizontalAlignment(SwingConstants.LEFT);
		btnSettlePayment.setHorizontalTextPosition(JButton.CENTER);
		btnSettlePayment.setVerticalTextPosition(JButton.CENTER);
		btnSettlePayment.setForeground(Color.WHITE);
		btnSettlePayment.setRolloverIcon(new ImageIcon(buttSmall));
		btnSettlePayment.addActionListener(new SettlePaymentListener());
		add(btnSettlePayment);
		btnSettlePayment.setVisible(true);
		//AddReview Button END

		/*btnSearchReview = new JButton("Search Review");
		btnSearchReview.setBounds(10, 100, 140, 23);
		btnSearchReview.addActionListener(new SearchReviewListener());
		add(btnSearchReview);*/
		
		
		
		//AddReview Button
		btnSearchReview = new JButton("Search Review");
		btnSearchReview.setFont(new Font("Candara", Font.BOLD, 15));
		btnSearchReview.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnSearchReview.setIcon(new ImageIcon(buttSmall));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnSearchReview.setIcon(null);
		    }
		});
		btnSearchReview.setBounds(-8, 160, 210, 23);
		btnSearchReview.setOpaque(false);
		btnSearchReview.setContentAreaFilled(false);
		btnSearchReview.setBorderPainted(false);
		btnSearchReview.setHorizontalAlignment(SwingConstants.LEFT);
		btnSearchReview.setHorizontalTextPosition(JButton.CENTER);
		btnSearchReview.setVerticalTextPosition(JButton.CENTER);
		btnSearchReview.setForeground(Color.WHITE);
		btnSearchReview.addActionListener(new SearchReviewListener());
		btnSearchReview.setRolloverIcon(new ImageIcon(buttSmall));
		add(btnSearchReview);
		btnSearchReview.setVisible(true);
		//AddReview Button END
		
		

		/*btnCancelSubscription = new JButton("Cancel Subscription");
		btnCancelSubscription.setBounds(10, 166, 140, 23);
		btnCancelSubscription.addActionListener(new CancelSubscriptionListener());
		add(btnCancelSubscription);
		*/
		
		
		//AddReview Button
		btnCancelSubscription = new JButton("Cancel Subscription");
		btnCancelSubscription.setFont(new Font("Candara", Font.BOLD, 15));
		Image buttSmall1 = new ImageIcon(this.getClass().getResource("/Coverbutton.png")).getImage();
		btnCancelSubscription.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnCancelSubscription.setIcon(new ImageIcon(buttSmall1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnCancelSubscription.setIcon(null);
		    }
		});
		btnCancelSubscription.setBounds(-8, 240, 210, 23);
		btnCancelSubscription.setOpaque(false);
		btnCancelSubscription.setContentAreaFilled(false);
		btnCancelSubscription.setBorderPainted(false);
		btnCancelSubscription.setHorizontalAlignment(SwingConstants.LEFT);
		btnCancelSubscription.setHorizontalTextPosition(JButton.CENTER);
		btnCancelSubscription.setVerticalTextPosition(JButton.CENTER);
		btnCancelSubscription.setForeground(Color.WHITE);
		btnCancelSubscription.addActionListener(new CancelSubscriptionListener());
		add(btnCancelSubscription);
		btnCancelSubscription.setVisible(true);
		//AddReview Button END
		
		
		
		//AddReview Button
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		Image buttSmall12 = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnLogout.setIcon(new ImageIcon(buttSmall12));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnLogout.setIcon(null);
		    }
		});
		
		btnLogout.setBounds(82, 0, 97, 25);
		btnLogout.setOpaque(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setHorizontalTextPosition(JButton.CENTER);
		btnLogout.setVerticalTextPosition(JButton.CENTER);
		btnLogout.setForeground(UIManager.getColor("Button.light"));
		btnLogout.addActionListener(new LogoutListener());
		add(btnLogout);
		btnLogout.setVisible(true);
		//AddReview Button END
		
		
		
		
		//Background Image
		JLabel BigMenuLine = new JLabel();
		BigMenuLine.setBounds(3, 110, 199, 2);
		Image img = new ImageIcon(this.getClass().getResource("/bigMenuLine.png")).getImage();
		BigMenuLine.setIcon(new ImageIcon(img));
		add(BigMenuLine);
		//Background Image END
		
		//Line Image
		JLabel SmallMenuLine = new JLabel();
		SmallMenuLine.setBounds(3, 150, 199, 2);
		Image img1 = new ImageIcon(this.getClass().getResource("/smallMenuLine.png")).getImage();
		SmallMenuLine.setIcon(new ImageIcon(img));
		add(SmallMenuLine);
		//Line Image END
		
		//Line Image
		JLabel SmallMenuLine1 = new JLabel();
		SmallMenuLine1.setBounds(3, 190, 199, 2);
		SmallMenuLine1.setIcon(new ImageIcon(img));
		add(SmallMenuLine1);
		//Line Image END
		
		//Line Image
		JLabel SmallMenuLine2 = new JLabel();
		SmallMenuLine2.setBounds(3, 230, 199, 2);
		SmallMenuLine2.setIcon(new ImageIcon(img));
		add(SmallMenuLine2);
		//Line Image END
		
		
		//Line Image
		JLabel SmallMenuLine3 = new JLabel();
		SmallMenuLine3.setBounds(3, 270, 199, 2);
		SmallMenuLine3.setIcon(new ImageIcon(img));
		add(SmallMenuLine3);
		//Line Image END
		
		//Background Image
		JLabel User = new JLabel();
		User.setBounds(11, 64, 199, 35);
		Image newImage = new ImageIcon(this.getClass().getResource("/"+App.client.getCurrentUser().getPermission()+".png")).getImage();
		User.setIcon(new ImageIcon(newImage));
		add(User);
		//Background Image END
		
	
        //Load Menu Background according to permission level
		if ((App.client.getCurrentUser().getPermission()).equals("USER")){
			JLabel MenuBackGround = new JLabel();
			MenuBackGround.setBounds(0, 0, 800, 600);
			Image img2 = new ImageIcon(this.getClass().getResource("/BigMenu.png")).getImage();
			MenuBackGround.setIcon(new ImageIcon(img2));
			add(MenuBackGround);	 
		}
		
	
	}

	


	/**Action listeners*/
	
	class LogoutListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			currentGUI.dispose();
			LoginGUI lg = new LoginGUI();
			LoginController lgr = new LoginController(lg, null);
		}
		
		
	}
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
}
