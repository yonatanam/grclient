package gui;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.App;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class ManageBooksGUI extends JFrame {
	
	private UserMenu userMenu;
	private JButton AddBookButton;
	private JButton AddCategoryButton;
	private JButton AddSubjectButton;
	
	public ManageBooksGUI()
	{	
		setResizable(false);
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		
		JLabel FingerLabel = new JLabel("");
		FingerLabel.setBounds(229, 262, 140, 119);
		FingerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(FingerLabel);
		
		JLabel AddBookLabel = new JLabel("Add new book");
		AddBookLabel.setForeground(Color.WHITE);
		AddBookLabel.setFont(new Font("Arial", Font.BOLD, 15));
		AddBookLabel.setBounds(249, 316, 106, 35);
		
		getContentPane().add(AddBookLabel);
		
		AddBookButton = new JButton("");
		AddBookButton.setBounds(229, 262, 140, 119);
		AddBookButton.setHorizontalTextPosition(JButton.CENTER);
		AddBookButton.setVerticalTextPosition(JButton.CENTER);
		AddBookButton.setOpaque(false);
		AddBookButton.setContentAreaFilled(false);
		AddBookButton.setBorderPainted(false);
		Image firthimg = new ImageIcon(this.getClass().getResource("/WriteToDB.png")).getImage();	
		AddBookButton.setIcon(new ImageIcon(firthimg));
		getContentPane().add(AddBookButton);
		
		JLabel Finger2Label = new JLabel("");
		Finger2Label.setBounds(381, 262, 140, 119);
		Finger2Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(Finger2Label);
		
		
		AddCategoryButton = new JButton("");
		AddCategoryButton.setBounds(381, 262, 140, 119);
		AddCategoryButton.setHorizontalTextPosition(JButton.CENTER);
		AddCategoryButton.setVerticalTextPosition(JButton.CENTER);
		AddCategoryButton.setOpaque(false);
		AddCategoryButton.setContentAreaFilled(false);
		AddCategoryButton.setBorderPainted(false);
		Image secondimg = new ImageIcon(this.getClass().getResource("/WriteToDB.png")).getImage();
		
		
		
		JLabel AddCategoryLabel = new JLabel("Add Category");
		AddCategoryLabel.setForeground(Color.WHITE);
		AddCategoryLabel.setFont(new Font("Arial", Font.BOLD, 15));
		AddCategoryLabel.setBounds(401, 317, 111, 35);
		getContentPane().add(AddCategoryLabel);
		AddCategoryButton.setIcon(new ImageIcon(secondimg));
		getContentPane().add(AddCategoryButton);
		
		
		

		
		JLabel Finger3Label = new JLabel("");
		Finger3Label.setBounds(381, 381, 140, 119);
		Finger3Label.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(Finger3Label);
		
		JLabel AddSubjectLabel = new JLabel("Add Subject");
		AddSubjectLabel.setForeground(Color.WHITE);
		AddSubjectLabel.setFont(new Font("Arial", Font.BOLD, 15));
		AddSubjectLabel.setBounds(408, 438, 97, 35);
		getContentPane().add(AddSubjectLabel);
		
		AddSubjectButton = new JButton("");
		AddSubjectButton.setVerticalTextPosition(SwingConstants.CENTER);
		AddSubjectButton.setOpaque(false);
		AddSubjectButton.setHorizontalTextPosition(SwingConstants.CENTER);
		AddSubjectButton.setContentAreaFilled(false);
		AddSubjectButton.setBorderPainted(false);
		AddSubjectButton.setBounds(381, 381, 140, 119);
		Image thirdimg = new ImageIcon(this.getClass().getResource("/WriteToDB.png")).getImage();
		AddSubjectButton.setIcon(new ImageIcon(thirdimg));
		getContentPane().add(AddSubjectButton);
		
		
		JLabel BackGround = new JLabel("");
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithLogo.png")).getImage();
		BackGround.setIcon(new ImageIcon(imgbg));
		BackGround.setBounds(0, 0, 794, 565);
		getContentPane().add(BackGround);
		
		
		
		setVisible(true);
	}
	
	
	
	
	// Adding action listeners to all buttons in manage books gui
	
	
	public void AddbuttonAddBookactionListener(ActionListener e)
	{
		AddBookButton.addActionListener(e);
	}
	
	public void AddbuttonAddCategoryactionListener(ActionListener e)
	{
		AddCategoryButton.addActionListener(e);
	}
	
	public void AddbuttonAddSubjectactionListener(ActionListener e)
	{
		AddSubjectButton.addActionListener(e);
	}
	
}
