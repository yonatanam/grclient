package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.App;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class EditCategoryGUI extends JFrame {
	private UserMenu userMenu;
	private JComboBox CatComboBox;
	private JTextField TextCatName;
	private	JLabel Name_Warning ;
	private JButton ChangeButton;
	private JButton BackButton;
	private JButton Removebutton;

	/**
	 * Create the application.
	 */
	public EditCategoryGUI() {
		getContentPane().setLayout(null);
        setSize(800, 600);
        setResizable(false);
 		setLocationRelativeTo(null);
 		setTitle("Edit Category");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/** Menu */
        userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
 		Menu menu = new Menu(userMenu);
 		menu.setBounds(10,11,165,550);
 		getContentPane().add(menu);
 		
 		/**end menu*/
 		//name field
 		 TextCatName = new JTextField();
 		TextCatName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				TextCatName.setText("");                   //Clear "Password String" on focus
			}
		});
		TextCatName.setText("Enter Category name");
		TextCatName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TextCatName.setOpaque(false);   
		TextCatName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		TextCatName.setForeground(Color.white);
		TextCatName.setBounds(258, 208, 231, 22);
		getContentPane().add(TextCatName);
		TextCatName.setColumns(10);
		JLabel label_1 = new JLabel("");
		Image firstimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(firstimg));
		label_1.setBounds(255, 205, 287, 32);
		getContentPane().add(label_1);
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		 ChangeButton = new JButton("Change");
		 ChangeButton.setIcon(new ImageIcon(buttoncan));
		 ChangeButton.setVerticalTextPosition(SwingConstants.CENTER);
		 ChangeButton.setOpaque(false);
		 ChangeButton.setHorizontalTextPosition(SwingConstants.CENTER);
		 ChangeButton.setContentAreaFilled(false);
		 ChangeButton.setBorderPainted(false);
		 ChangeButton.setFont(new Font("Arial", Font.BOLD, 12));
		 ChangeButton.setForeground(Color.WHITE);
		ChangeButton.setBounds(255, 241, 89, 23);
		getContentPane().add(ChangeButton);
		//--remove btn
		Removebutton = new JButton("remove");
		Removebutton.setIcon(new ImageIcon(buttoncan));
		Removebutton.setVerticalTextPosition(SwingConstants.CENTER);
		Removebutton.setOpaque(false);
		Removebutton.setHorizontalTextPosition(SwingConstants.CENTER);
		Removebutton.setContentAreaFilled(false);
		Removebutton.setBorderPainted(false);
		Removebutton.setFont(new Font("Arial", Font.BOLD, 12));
		Removebutton.setForeground(Color.WHITE);
		Removebutton.setBounds(453, 241, 89, 23);
		getContentPane().add(Removebutton);
		//------end remove
		
		
		 BackButton = new JButton("Back");
		 BackButton.setVerticalTextPosition(SwingConstants.CENTER);
		 BackButton.setOpaque(false);
		 BackButton.setForeground(Color.WHITE);
		 BackButton.setHorizontalTextPosition(SwingConstants.CENTER);
		 BackButton.setContentAreaFilled(false);
		 BackButton.setIcon(new ImageIcon(buttoncan));
		 BackButton.setBorderPainted(false);
		 BackButton.setFont(new Font("Arial", Font.BOLD, 12));
		BackButton.setBounds(354, 241, 89, 23);
		getContentPane().add(BackButton);
		
		Name_Warning = new JLabel("");
		Name_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		Name_Warning.setForeground(Color.RED);
		Name_Warning.setBounds(522, 208, 213, 22);
		getContentPane().add(Name_Warning);
 		
		JLabel lblChangeCategorie = new JLabel("Change category name");
		lblChangeCategorie.setForeground(Color.LIGHT_GRAY);
		lblChangeCategorie.setFont(new Font("Arial", Font.BOLD, 19));
		lblChangeCategorie.setBounds(73, 34, 380, 29);
		getContentPane().add(lblChangeCategorie);
		
		JLabel lblCategoryToEdit = new JLabel("choose category to edit");
		lblCategoryToEdit.setForeground(Color.LIGHT_GRAY);
		lblCategoryToEdit.setFont(new Font("Arial", Font.BOLD, 16));
		lblCategoryToEdit.setBounds(255, 83, 296, 29);
		getContentPane().add(lblCategoryToEdit);
		
		
		JLabel lblEnterCategorieName = new JLabel("Enter category's new name ");
		lblEnterCategorieName.setForeground(Color.LIGHT_GRAY);
		lblEnterCategorieName.setFont(new Font("Arial", Font.BOLD, 16));
		lblEnterCategorieName.setBounds(255, 168, 303, 29);
		getContentPane().add(lblEnterCategorieName);
		
 		CatComboBox = new JComboBox();
 		CatComboBox.setBounds(255, 123, 241, 33);
		getContentPane().add(CatComboBox);
 		
 		
 		
 		/**background*/
 		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		
		
		JLabel BackGround = new JLabel("");
		BackGround.setBounds(0, -4, 797, 565);
		BackGround.setIcon(new ImageIcon(imgbg));
		getContentPane().add(BackGround);
 		/**end background*/
		setVisible(true);
	}

	public JTextField getTextCatName() {
		return TextCatName;
	}

	public JComboBox getCatComboBox() {
		return CatComboBox;
	}
	public JLabel getName_Warning () {
		return Name_Warning;
	}
	public void AddChangeButtonactionListener(ActionListener e)
	{
		ChangeButton.addActionListener(e);
	}
	
	public void addBackButtonActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	public void addRemoveButtonActionListener(ActionListener e)
	{
		Removebutton.addActionListener(e);
	}
}
