package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import client.App;

import javax.swing.JTextField;

public class AddCategoryGUI extends JFrame {

	private JLabel BackGround;
	private JTextField TextCatName;
	private JLabel lblCategoriesThatWere;
	private JLabel lblCreateNewCategories; 
	private JLabel Name_Warning;
	private JLabel lblAddingNewCategory;
	private JLabel label_1;
	private JButton Button_Cancel;
	private JButton Button_Apply;
	private JLabel What_Categories;
	private UserMenu userMenu;
	
	
	public AddCategoryGUI() {
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
		
		lblAddingNewCategory = new JLabel("Adding new category");
		lblAddingNewCategory.setForeground(Color.WHITE);
		lblAddingNewCategory.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingNewCategory.setBounds(75, 11, 255, 29);
		getContentPane().add(lblAddingNewCategory);
		
		label_1 = new JLabel("");
		Image firstimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(firstimg));
		label_1.setBounds(263, 265, 224, 32);
		getContentPane().add(label_1);
		Image secondimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		
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
		
		TextCatName = new JTextField();
		TextCatName.setText("Enter Category name");
		TextCatName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TextCatName.setOpaque(false);   
		TextCatName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		TextCatName.setForeground(Color.white);
		TextCatName.setBounds(272, 269, 206, 22);
		getContentPane().add(TextCatName);
		TextCatName.setColumns(10);
		
		Name_Warning = new JLabel("");
		Name_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		Name_Warning.setForeground(Color.RED);
		Name_Warning.setBounds(499, 269, 213, 22);
		getContentPane().add(Name_Warning);
		
		lblCreateNewCategories = new JLabel("Create new categories for your library ");
		lblCreateNewCategories.setForeground(Color.LIGHT_GRAY);
		lblCreateNewCategories.setFont(new Font("Arial", Font.BOLD, 19));
		lblCreateNewCategories.setBounds(23, 78, 380, 29);
		getContentPane().add(lblCreateNewCategories);
		
		lblCategoriesThatWere = new JLabel("Categories that were already created are:");
		lblCategoriesThatWere.setFont(new Font("Arial", Font.BOLD, 19));
		lblCategoriesThatWere.setForeground(Color.LIGHT_GRAY);
		lblCategoriesThatWere.setBounds(23, 352, 393, 22);
		getContentPane().add(lblCategoriesThatWere);
		
		What_Categories = new JLabel("");
		What_Categories.setForeground(Color.LIGHT_GRAY);
		What_Categories.setFont(new Font("Arial", Font.BOLD, 19));
		What_Categories.setBounds(23, 387, 737, 29);
		getContentPane().add(What_Categories);
		
		
		BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 797, 565);
		BackGround.setIcon(new ImageIcon(imgbg));
		getContentPane().add(BackGround);
		
		
	
		
		
		this.setTitle("Adding new Category - Initial Config");
		
		this.setVisible(true);
		
	}

	
	
	// Setters and Getters
	
	
	public JTextField getTextCatName() {
		return TextCatName;
	}

	public JLabel getName_Warning() {
		return Name_Warning;
	}

	public JLabel getWhat_Categories() {
		return What_Categories;
	}
	
	
	
	// Adding Action Listeners
	
	
	public void AddTextCategNameMouseListener(MouseListener e)
	{
		TextCatName.addMouseListener(e);
	}
	
	public void AddbuttonApplyactionListener(ActionListener e)
	{
		Button_Apply.addActionListener(e);
	}
	
	public void addButtonCancelFromCreateNewCategActionListener(ActionListener e)
	{
		Button_Cancel.addActionListener(e);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
