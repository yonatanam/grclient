package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import client.App;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

public class EditReviewGUI extends JFrame {


	private JButton BackButton;
	private JButton btnSubmit;
	private JTextArea textArea;
	private UserMenu userMenu;
	private String reviewText;
	

	public EditReviewGUI(String reviewText) {
		this.reviewText = reviewText;
		initialize();

	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		setBounds(100, 100, 600, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.BLACK);
		setUndecorated(true);
		Image imgLogin = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();



		//Back button
		BackButton = new JButton("Back");
		BackButton.setBounds(137, 380, 131, 41);
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setIcon(new ImageIcon(imgLogin));
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setForeground(Color.WHITE);
		getContentPane().add(BackButton);



		/*Text area*/
		textArea = new JTextArea();
		textArea.setBounds(20, 46, 535, 323);
		textArea.setText(reviewText);
		getContentPane().add(textArea);
		/*End sign check box*/

		/*Submit button*/
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(292, 380, 131, 41);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 15));
		btnSubmit.setIcon(new ImageIcon(imgLogin));
		btnSubmit.setOpaque(false);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorderPainted(false);
		btnSubmit.setHorizontalTextPosition(JButton.CENTER);
		btnSubmit.setVerticalTextPosition(JButton.CENTER);
		btnSubmit.setForeground(Color.WHITE);
		getContentPane().add(btnSubmit);

		JLabel lblEditReviewText = new JLabel("Edit review text:");
		lblEditReviewText.setForeground(Color.WHITE);
		lblEditReviewText.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEditReviewText.setBounds(20, 11, 154, 14);
		getContentPane().add(lblEditReviewText);

		setLocationRelativeTo(null);
		setVisible(true);

		//BackGround END
	}


	//Action Listeners

	public void addButtonBackFromEditReviewActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}

	public void addButtonSubmitEditReviewActionListener(ActionListener e)
	{
		btnSubmit.addActionListener(e);
	}
	
	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}
