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

public class PublishReviewGUI extends JFrame {


	private JButton BackButton;
	private JLabel lblChooseBook;
	private JComboBox chooseBookComboBox;
	private JLabel lblKeywords;
	private JTextField keywordTextField;
	private JLabel lblSign;
	private JCheckBox chckbxNewCheckBox;
	private JButton btnSubmit;
	private JTextArea textArea;
	private UserMenu userMenu;
	
	public PublishReviewGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		
		Image buttonIcon = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		//Back button
		BackButton = new JButton("Back");
		BackButton.setBounds(387, 504, 131, 31);
		BackButton.setFont(new Font("Arial", Font.BOLD, 16));
		BackButton.setForeground(Color.WHITE);
		BackButton.setIcon(new ImageIcon(buttonIcon));
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		getContentPane().add(BackButton);

		setLocationRelativeTo(null);
		setVisible(true);
		
		/*Choose Book label*/
		lblChooseBook = new JLabel("Choose Book:");
		lblChooseBook.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChooseBook.setForeground(Color.WHITE);
		lblChooseBook.setBounds(89, 38, 133, 41);
		getContentPane().add(lblChooseBook);
		/*Choose Book label*/
		
		/*Choose book combobox*/
		chooseBookComboBox = new JComboBox();
		chooseBookComboBox.setBounds(246, 51, 165, 20);
		getContentPane().add(chooseBookComboBox);
		/*End review text field*/
		
		/*Text area*/
		textArea = new JTextArea();
		textArea.setBounds(89, 114, 593, 269);
		getContentPane().add(textArea);
		/*End text area*/
		
		/*Keywords label*/
		lblKeywords = new JLabel("Keywords:");
		lblKeywords.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKeywords.setForeground(Color.WHITE);
		lblKeywords.setBounds(89, 390, 106, 31);
		getContentPane().add(lblKeywords);
		/*End keywords label*/
		
		/*Keywords textfield */
		keywordTextField = new JTextField();
		keywordTextField.setBounds(194, 398, 488, 20);
		getContentPane().add(keywordTextField);
		keywordTextField.setColumns(10);
		/*End keywords textfield*/
		
		/*Sign label*/
		lblSign = new JLabel("Sign:");
		lblSign.setBounds(89, 432, 45, 31);
		lblSign.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSign.setForeground(Color.WHITE);
		getContentPane().add(lblSign);
		/*End sign label */
		
		/*Sign check box*/
		chckbxNewCheckBox = new JCheckBox("Signature");
		chckbxNewCheckBox.setBounds(195, 439, 97, 23);
		getContentPane().add(chckbxNewCheckBox);
		/*End sign check box*/
		
		/*Submit button*/
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(246, 504, 131, 31);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setIcon(new ImageIcon(buttonIcon));
		btnSubmit.setHorizontalTextPosition(JButton.CENTER);
		btnSubmit.setVerticalTextPosition(JButton.CENTER);
		btnSubmit.setOpaque(false);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorderPainted(false);
		getContentPane().add(btnSubmit);
		/*End submit button*/
		

		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		


		
		
		
		
		//BackGround END
	}


	//Action Listeners

	public void addButtonBackFromReadFromWorkerActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
	public void addButtonSubmitPublishReviewActionListener(ActionListener e)
	{
		btnSubmit.addActionListener(e);
	}
	
	//Getters and setters
	
	public JComboBox getChooseBookComboBox() {
		return chooseBookComboBox;
	}

	public void setChooseBookComboBox(JComboBox chooseBookComboBox) {
		this.chooseBookComboBox = chooseBookComboBox;
	}
	public JTextField getKeywordTextField() {
		return keywordTextField;
	}

	public void setKeywordTextField(JTextField keywordTextField) {
		this.keywordTextField = keywordTextField;
	}

	public JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	public void setChckbxNewCheckBox(JCheckBox chckbxNewCheckBox) {
		this.chckbxNewCheckBox = chckbxNewCheckBox;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	
	//End getters setters
}
