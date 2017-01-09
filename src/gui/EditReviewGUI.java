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
		setBounds(100, 100, 600, 498);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setUndecorated(true);



		//Back button
		BackButton = new JButton("Back");
		BackButton.setBounds(370, 407, 89, 23);
		getContentPane().add(BackButton);



		/*Text area*/
		textArea = new JTextArea();
		textArea.setBounds(20, 46, 535, 323);
		textArea.setText(reviewText);
		getContentPane().add(textArea);
		/*End sign check box*/

		/*Submit button*/
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(116, 407, 89, 23);
		getContentPane().add(btnSubmit);

		JLabel lblEditReviewText = new JLabel("Edit review text:");
		lblEditReviewText.setBounds(20, 11, 120, 14);
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
