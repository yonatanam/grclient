package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.App;

public class AddSubjectGUI extends JFrame {
	
	private JLabel BackGround;
	private JTextField TextSubId;
	private JTextField TextSubName;
	private JLabel lblSubjectsThatWere;
	private JLabel lblCreateNewSubjects; 
	private JLabel Name_Warning;
	private JLabel Id_Warning;
	private JLabel lblAddingNewSubject;
	private JLabel label_1;
	private JLabel label;
	private JButton Button_Cancel;
	private JButton Button_Apply;
	private JLabel What_Subjects;
	private UserMenu userMenu;
	
	public AddSubjectGUI() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setSize(800, 600);
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);
		
		/** End menu */
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		getContentPane().setLayout(null);
		
		lblAddingNewSubject = new JLabel("Adding new Subject");
		lblAddingNewSubject.setForeground(Color.WHITE);
		lblAddingNewSubject.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingNewSubject.setBounds(69, 11, 255, 29);
		getContentPane().add(lblAddingNewSubject);
		
		label_1 = new JLabel("");
		Image firstimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(firstimg));
		label_1.setBounds(271, 259, 224, 32);
		getContentPane().add(label_1);
		
		label = new JLabel("");
		Image secondimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label.setIcon(new ImageIcon(secondimg));
		label.setBounds(271, 213, 224, 32);
		getContentPane().add(label);
		
		Button_Cancel = new JButton("Cancel");
		Image canimg = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		Button_Cancel.setIcon(new ImageIcon(canimg));
		Button_Cancel.setVerticalTextPosition(SwingConstants.CENTER);
		Button_Cancel.setOpaque(false);
		Button_Cancel.setHorizontalTextPosition(SwingConstants.CENTER);
		Button_Cancel.setForeground(Color.WHITE);
		Button_Cancel.setFont(new Font("Arial", Font.BOLD, 16));
		Button_Cancel.setContentAreaFilled(false);
		Button_Cancel.setBorderPainted(false);
		Button_Cancel.setBounds(231, 485, 131, 31);
		getContentPane().add(Button_Cancel);
		
		Button_Apply = new JButton("Apply");
		Image appimg = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		Button_Apply.setIcon(new ImageIcon(appimg));
		Button_Apply.setVerticalTextPosition(SwingConstants.CENTER);
		Button_Apply.setOpaque(false);
		Button_Apply.setHorizontalTextPosition(SwingConstants.CENTER);
		Button_Apply.setForeground(Color.WHITE);
		Button_Apply.setFont(new Font("Arial", Font.BOLD, 16));
		Button_Apply.setContentAreaFilled(false);
		Button_Apply.setBorderPainted(false);
		Button_Apply.setBounds(451, 485, 131, 31);
		
		getContentPane().add(Button_Apply);
		
		TextSubId = new JTextField();
		TextSubId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TextSubId.setText("Enter Subject id");
		TextSubId.setOpaque(false);   
		TextSubId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		TextSubId.setForeground(Color.white);
		TextSubId.setBounds(278, 218, 206, 22);
		getContentPane().add(TextSubId);
		TextSubId.setColumns(10);
		
		TextSubName = new JTextField();
		TextSubName.setText("Enter Subject name");
		TextSubName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TextSubName.setOpaque(false);   
		TextSubName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		TextSubName.setForeground(Color.white);
		TextSubName.setBounds(280, 263, 206, 22);
		getContentPane().add(TextSubName);
		TextSubName.setColumns(10);
		
		Id_Warning = new JLabel("");
		Id_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		Id_Warning.setForeground(Color.RED);
		Id_Warning.setBounds(504, 217, 213, 22);
		getContentPane().add(Id_Warning);
		
		Name_Warning = new JLabel("");
		Name_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		Name_Warning.setForeground(Color.RED);
		Name_Warning.setBounds(504, 265, 213, 22);
		getContentPane().add(Name_Warning);
		
		lblCreateNewSubjects = new JLabel("Create new Subjects for your library ");
		lblCreateNewSubjects.setForeground(Color.LIGHT_GRAY);
		lblCreateNewSubjects.setFont(new Font("Arial", Font.BOLD, 19));
		lblCreateNewSubjects.setBounds(23, 78, 380, 29);
		getContentPane().add(lblCreateNewSubjects);
		
		lblSubjectsThatWere = new JLabel("Subjects that were already created are:");
		lblSubjectsThatWere.setFont(new Font("Arial", Font.BOLD, 19));
		lblSubjectsThatWere.setForeground(Color.LIGHT_GRAY);
		lblSubjectsThatWere.setBounds(23, 352, 393, 22);
		getContentPane().add(lblSubjectsThatWere);
		
		What_Subjects = new JLabel("");
		What_Subjects.setForeground(Color.LIGHT_GRAY);
		What_Subjects.setFont(new Font("Arial", Font.BOLD, 19));
		What_Subjects.setBounds(23, 387, 737, 29);
		getContentPane().add(What_Subjects);
		
		
		BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 797, 565);
		BackGround.setIcon(new ImageIcon(imgbg));
		getContentPane().add(BackGround);
		
		
	
		
		
		this.setTitle("Adding new Subject - Initial Config");
		
		this.setVisible(true);
		
	}

	
	// Setters and Getters 
	
	public JTextField getTextSubId() {
		return TextSubId;
	}

	public JTextField getTextSubName() {
		return TextSubName;
	}

	public JLabel getName_Warning() {
		return Name_Warning;
	}

	public JLabel getId_Warning() {
		return Id_Warning;
	}

	public JLabel getWhat_Subjects() {
		return What_Subjects;
	}
	

	// Adding Action Listeners
	
	public void AddTextSubjIdMouseListener(MouseListener e)
	{
		TextSubId.addMouseListener(e);
	}
	
	public void AddTextSubjNameMouseListener(MouseListener e)
	{
		TextSubName.addMouseListener(e);
	}
	
	public void AddbuttonApplyactionListener(ActionListener e)
	{
		Button_Apply.addActionListener(e);
	}
	
	public void addButtonCancelFromCreateNewSubjActionListener(ActionListener e)
	{
		Button_Cancel.addActionListener(e);
	}
		

}
