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
import javax.swing.JTextField;

public class AddCategoryGUI extends JFrame {

	private JLabel BackGround;
	private JTextField TextCatId;
	private JTextField TextCatName;
	private JLabel lblCategoryName;
	private JLabel lblCategoriesThatWere;
	private JLabel lblCreateNewCategories; 
	private JLabel Name_Warning;
	private JLabel Id_Warning;
	private JLabel lblCategoryId;
	private JLabel lblAddingNewCategory;
	private JLabel label_1;
	private JLabel label;
	private JButton Button_Cancel;
	private JButton Button_Apply;
	private JLabel What_Categories;
	
	
	
	public AddCategoryGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		
		Image imgbg = new ImageIcon(this.getClass().getResource("/addbooklogo.jpg")).getImage();
		getContentPane().setLayout(null);
		
		lblAddingNewCategory = new JLabel("Adding new category");
		lblAddingNewCategory.setForeground(Color.WHITE);
		lblAddingNewCategory.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingNewCategory.setBounds(23, 13, 255, 29);
		getContentPane().add(lblAddingNewCategory);
		
		label_1 = new JLabel("");
		Image firstimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(firstimg));
		label_1.setBounds(338, 306, 224, 32);
		getContentPane().add(label_1);
		
		label = new JLabel("");
		Image secondimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label.setIcon(new ImageIcon(secondimg));
		label.setBounds(338, 260, 224, 32);
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
		Button_Cancel.setBounds(229, 564, 131, 31);
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
		Button_Apply.setBounds(449, 564, 131, 31);
		
		getContentPane().add(Button_Apply);
		
		lblCategoryId = new JLabel("Category id:");
		lblCategoryId.setForeground(Color.LIGHT_GRAY);
		lblCategoryId.setFont(new Font("Arial", Font.BOLD, 18));
		lblCategoryId.setBounds(208, 262, 121, 22);
		getContentPane().add(lblCategoryId);
		
		lblCategoryName = new JLabel("Category name:");
		lblCategoryName.setForeground(Color.LIGHT_GRAY);
		lblCategoryName.setFont(new Font("Arial", Font.BOLD, 18));
		lblCategoryName.setBounds(174, 306, 141, 22);
		getContentPane().add(lblCategoryName);
		
		TextCatId = new JTextField();
		TextCatId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TextCatId.setText("exp:  44332");
		TextCatId.setOpaque(false);   
		TextCatId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		TextCatId.setForeground(Color.white);
		TextCatId.setBounds(345, 265, 116, 22);
		getContentPane().add(TextCatId);
		TextCatId.setColumns(10);
		
		TextCatName = new JTextField();
		TextCatName.setText("exp: Music");
		TextCatName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TextCatName.setOpaque(false);   
		TextCatName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		TextCatName.setForeground(Color.white);
		TextCatName.setBounds(347, 310, 116, 22);
		getContentPane().add(TextCatName);
		TextCatName.setColumns(10);
		
		Id_Warning = new JLabel("");
		Id_Warning.setForeground(Color.RED);
		Id_Warning.setBounds(529, 302, 213, 22);
		getContentPane().add(Id_Warning);
		
		Name_Warning = new JLabel("");
		Name_Warning.setForeground(Color.RED);
		Name_Warning.setBounds(529, 383, 213, 22);
		getContentPane().add(Name_Warning);
		
		lblCreateNewCategories = new JLabel("Create new categories for your library ");
		lblCreateNewCategories.setForeground(Color.WHITE);
		lblCreateNewCategories.setFont(new Font("Arial", Font.BOLD, 19));
		lblCreateNewCategories.setBounds(23, 78, 380, 29);
		getContentPane().add(lblCreateNewCategories);
		
		lblCategoriesThatWere = new JLabel("Categories that were already created are:");
		lblCategoriesThatWere.setFont(new Font("Arial", Font.BOLD, 19));
		lblCategoriesThatWere.setForeground(Color.WHITE);
		lblCategoriesThatWere.setBounds(23, 419, 393, 22);
		getContentPane().add(lblCategoriesThatWere);
		
		What_Categories = new JLabel("");
		What_Categories.setForeground(Color.LIGHT_GRAY);
		What_Categories.setFont(new Font("Arial", Font.BOLD, 19));
		What_Categories.setBounds(417, 418, 380, 29);
		getContentPane().add(What_Categories);
		
		
		BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 851, 762);
		BackGround.setIcon(new ImageIcon(imgbg));
		getContentPane().add(BackGround);
		
		
	
		
		
		this.setTitle("Adding new Category - Initial Config");
		this.setBounds(400, 100, 857, 797);
		this.setVisible(true);
		this.setResizable(false);
	}

	
	
	// Setters and Getters
	
	
	public JTextField getTextCatId() {
		return TextCatId;
	}

	public JTextField getTextCatName() {
		return TextCatName;
	}

	public JLabel getName_Warning() {
		return Name_Warning;
	}

	public JLabel getId_Warning() {
		return Id_Warning;
	}

	public JLabel getWhat_Categories() {
		return What_Categories;
	}
	
	
	
	// Adding Action Listeners
	
	public void AddTextCategIdMouseListener(MouseListener e)
	{
		TextCatId.addMouseListener(e);
	}
	
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
