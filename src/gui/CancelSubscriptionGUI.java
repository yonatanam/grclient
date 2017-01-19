package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import client.App;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class CancelSubscriptionGUI extends JFrame {
	private JButton ApplyButton;
	private JLabel BackGround;
	private JLabel miniLogo;
	private JButton btnCancel;
	private JPanel panel;
	private UserMenu userMenu;

	private JTextField nullField;
	
	public CancelSubscriptionGUI() {
		
		
		this.setTitle("Cancel Subscription - Initial Config");
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this);
		/** End menu */
		
		
		nullField = new JTextField();
		nullField.setOpaque(false);     
		panel.add(nullField);
		
		JLabel lblAddingABook = new JLabel("Are you sure you want");
		lblAddingABook.setForeground(Color.WHITE);
		lblAddingABook.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		lblAddingABook.setBounds(72, 68, 341, 55);
		panel.add(lblAddingABook);
		
		JLabel CancelSub = new JLabel("to cancel your subscription?");
		CancelSub.setForeground(Color.WHITE);
		CancelSub.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20));
		CancelSub.setBounds(50, 106, 341, 55);
		panel.add(CancelSub);
		
		Image firstimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		Image img2 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		Image secondimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
	
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		
		
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(50, 193, 131, 31);
		panel.add(btnCancel);
		btnCancel.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancel.setOpaque(false);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setIcon(new ImageIcon(buttoncan));
		btnCancel.setFont(new Font("Arial", Font.BOLD, 16));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		
		ApplyButton = new JButton("Apply");
		ApplyButton.setBounds(193, 193, 131, 31);
		panel.add(ApplyButton);
		ApplyButton.setFont(new Font("Arial", Font.BOLD, 16));
		ApplyButton.setForeground(Color.WHITE);
		ApplyButton.setIcon(new ImageIcon(buttoncan));
		ApplyButton.setHorizontalTextPosition(JButton.CENTER);
		ApplyButton.setVerticalTextPosition(JButton.CENTER);
		ApplyButton.setOpaque(false);
		ApplyButton.setContentAreaFilled(false);
		ApplyButton.setBorderPainted(false);
				Image thirdimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
				
				
		miniLogo = new JLabel("");
		miniLogo.setBounds(298, 0, 93, 55);
		Image miniLogoImage = new ImageIcon(this.getClass().getResource("/miniLogo.png")).getImage();
		panel.add(miniLogo);
		miniLogo.setIcon(new ImageIcon(miniLogoImage));
		
		
		
		BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 794, 566);
		panel.add(BackGround);
		BackGround.setIcon(new ImageIcon(imgbg));
				
				
			
		
		
		
		this.setVisible(true);
		
	}
	
	
	// ------------------ Getters and Setters for all components ---------------------------------//


	
	// ----------------------------------- end of getters and setters -------------------------------------------//
	
	
	//listeners for AddBookGUI
	

	
	
	public void AddbuttonApplyactionListener(ActionListener e)
	{
		ApplyButton.addActionListener(e);
	}
	
	public void addButtonCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
}
