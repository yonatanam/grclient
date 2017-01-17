package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import client.App;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class AddSubjectGUI extends JFrame {
	
	private JLabel BackGround;
	private JTextField TextSubName;
	private JLabel lblCreateNewSubjects; 
	private JLabel Name_Warning;
	private JLabel lblAddingNewSubject;
	private JLabel label_1;
	private JButton Button_Cancel;
	private JButton Button_Apply;
	private UserMenu userMenu;
	private JLabel Subject_CategoryLabel;
	private JList ActualCategories_List;
	private JLabel ChooseCategWarning_Label;
	private JLabel SubCatAlreadyHaveWarning_Label;
	private JList<String> Categories_List;
	private JButton AddCategoryButton;
	private JButton RemoveCategoryButton;
	private JButton BackToManageBooksButton;
	private String[] AllCategories = null;
	private ArrayList<String> ActCat;
	
	public AddSubjectGUI() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);		
		/** End menu */
		//ShowAllComponentsIfFlag1();
	}

	
	// this methods showing all components when flag is 1 , means there are categories in db
	
	

	public void ShowAllComponentsIfFlag1()
	{
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		getContentPane().setLayout(null);
		
		 ActCat = new ArrayList<String>();
		
		lblAddingNewSubject = new JLabel("Adding new Subject");
		lblAddingNewSubject.setForeground(Color.WHITE);
		lblAddingNewSubject.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingNewSubject.setBounds(69, 11, 255, 29);
		getContentPane().add(lblAddingNewSubject);
		
		
		label_1 = new JLabel("");
		Image firstimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(firstimg));
		label_1.setBounds(280, 249, 224, 32);
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
		Button_Cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Button_Cancel.setBounds(222, 485, 131, 31);
		getContentPane().add(Button_Cancel);
		
		
		Button_Apply = new JButton("Apply");
		Image appimg = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		Button_Apply.setIcon(new ImageIcon(appimg));
		Button_Apply.setVerticalTextPosition(SwingConstants.CENTER);
		Button_Apply.setOpaque(false);
		Button_Apply.setHorizontalTextPosition(SwingConstants.CENTER);
		Button_Apply.setForeground(Color.WHITE);
		Button_Apply.setFont(new Font("Arial", Font.BOLD, 16));
		Button_Apply.setCursor(new Cursor(Cursor.HAND_CURSOR));
		Button_Apply.setContentAreaFilled(false);
		Button_Apply.setBorderPainted(false);
		Button_Apply.setBounds(442, 485, 131, 31);
		
		getContentPane().add(Button_Apply);
		
		TextSubName = new JTextField();
		TextSubName.setText("Enter Subject name");
		TextSubName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		TextSubName.setOpaque(false);   
		TextSubName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		TextSubName.setForeground(Color.white);
		TextSubName.setBounds(289, 253, 206, 22);
		getContentPane().add(TextSubName);
		TextSubName.setColumns(10);
		
		Name_Warning = new JLabel("");
		Name_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		Name_Warning.setForeground(Color.RED);
		Name_Warning.setBounds(513, 255, 213, 22);
		getContentPane().add(Name_Warning);
		
		lblCreateNewSubjects = new JLabel("Create new Subjects for your library ");
		lblCreateNewSubjects.setForeground(Color.LIGHT_GRAY);
		lblCreateNewSubjects.setFont(new Font("Arial", Font.BOLD, 19));
		lblCreateNewSubjects.setBounds(23, 78, 380, 29);
		getContentPane().add(lblCreateNewSubjects);
		
		Subject_CategoryLabel = new JLabel("Choose Category:");
		Subject_CategoryLabel.setForeground(Color.LIGHT_GRAY);
		Subject_CategoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
		Subject_CategoryLabel.setBounds(23, 305, 156, 29);
		getContentPane().add(Subject_CategoryLabel);
		
		// AAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
		
		//AllCategories
		Categories_List = new JList<String>(AllCategories);
		Categories_List.setVisibleRowCount(3);
		Categories_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Categories_List.setForeground(new Color(0, 0, 0));
		Categories_List.setFont(new Font("Arial", Font.BOLD, 15));
		Categories_List.setBackground(Color.LIGHT_GRAY);
		Categories_List.setBounds(176, 311, 156, 49);
		JScrollPane scrollPane = new JScrollPane(Categories_List);
		scrollPane.setLocation(174, 311);
		scrollPane.setSize(156, 66);
		getContentPane().add(scrollPane);
		
		
		
		ActualCategories_List = new JList();
		
		ActualCategories_List.setFont(new Font("Arial", Font.BOLD, 15));
		ActualCategories_List.setBackground(Color.LIGHT_GRAY);
		ActualCategories_List.setBounds(451, 311, 156, 49);
		ActualCategories_List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_1 = new JScrollPane(ActualCategories_List);
		scrollPane_1.setLocation(457, 311);
		scrollPane_1.setSize(156, 66);
		getContentPane().add(scrollPane_1);
		
		
		AddCategoryButton = new JButton("Add");
		Image addimg = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		AddCategoryButton.setIcon(new ImageIcon(addimg));
		AddCategoryButton.setVerticalTextPosition(SwingConstants.CENTER);
		AddCategoryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		AddCategoryButton.setOpaque(false);
		AddCategoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
		AddCategoryButton.setForeground(Color.WHITE);
		AddCategoryButton.setFont(new Font("Arial", Font.BOLD, 16));
		AddCategoryButton.setContentAreaFilled(false);
		AddCategoryButton.setBorderPainted(false);
		AddCategoryButton.setBounds(335, 304, 119, 31);
		getContentPane().add(AddCategoryButton);
		
		RemoveCategoryButton = new JButton("Remove");
		Image remimg = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		RemoveCategoryButton.setIcon(new ImageIcon(remimg));
		RemoveCategoryButton.setVerticalTextPosition(SwingConstants.CENTER);
		RemoveCategoryButton.setOpaque(false);
		RemoveCategoryButton.setHorizontalTextPosition(SwingConstants.CENTER);
		RemoveCategoryButton.setForeground(Color.WHITE);
		RemoveCategoryButton.setFont(new Font("Arial", Font.BOLD, 16));
		RemoveCategoryButton.setContentAreaFilled(false);
		RemoveCategoryButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		RemoveCategoryButton.setBorderPainted(false);
		RemoveCategoryButton.setBounds(335, 329, 119, 31);
		getContentPane().add(RemoveCategoryButton);
		
		ChooseCategWarning_Label = new JLabel("");
		ChooseCategWarning_Label.setFont(new Font("Arial", Font.BOLD, 15));
		ChooseCategWarning_Label.setForeground(Color.RED);
		ChooseCategWarning_Label.setBounds(457, 381, 255, 22);
		getContentPane().add(ChooseCategWarning_Label);
		
		SubCatAlreadyHaveWarning_Label = new JLabel("");
		SubCatAlreadyHaveWarning_Label.setForeground(Color.RED);
		SubCatAlreadyHaveWarning_Label.setFont(new Font("Arial", Font.BOLD, 15));
		SubCatAlreadyHaveWarning_Label.setBounds(307, 451, 155, 22);
		getContentPane().add(SubCatAlreadyHaveWarning_Label);
		
	
		BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 797, 565);
		BackGround.setIcon(new ImageIcon(imgbg));
		getContentPane().add(BackGround);
	
		this.setTitle("Adding new Subject - Initial Config");
		
		this.setVisible(true);
		
	}
	
	public void ShowNoCategoriesinDBYetifflag0()
	{
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		getContentPane().setLayout(null);
		
		
		lblAddingNewSubject = new JLabel("Adding new Subject");
		lblAddingNewSubject.setForeground(Color.WHITE);
		lblAddingNewSubject.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingNewSubject.setBounds(69, 11, 255, 29);
		getContentPane().add(lblAddingNewSubject);
		
		JLabel lblNewLabel = new JLabel("There are no categories yet, go back to add category.");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 19));
		lblNewLabel.setBounds(144, 175, 514, 40);
		getContentPane().add(lblNewLabel);
		
		BackToManageBooksButton = new JButton("back to Manage Books window");
		Image backimg = new ImageIcon(this.getClass().getResource("/Button.png")).getImage();
		BackToManageBooksButton.setIcon(new ImageIcon(backimg));
		BackToManageBooksButton.setBounds(257, 249, 269, 40);
		BackToManageBooksButton.setVerticalTextPosition(SwingConstants.CENTER);
		BackToManageBooksButton.setOpaque(false);
		BackToManageBooksButton.setHorizontalTextPosition(SwingConstants.CENTER);
		BackToManageBooksButton.setForeground(Color.WHITE);
		BackToManageBooksButton.setFont(new Font("Arial", Font.BOLD, 14));
		BackToManageBooksButton.setContentAreaFilled(false);
		BackToManageBooksButton.setBorderPainted(false);
		BackToManageBooksButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getContentPane().add(BackToManageBooksButton);
		
		

		
		BackGround = new JLabel("");
		BackGround.setBounds(0, 0, 797, 565);
		BackGround.setIcon(new ImageIcon(imgbg));
		getContentPane().add(BackGround);
		
		
	
		
		
		this.setTitle("Adding new Subject - Initial Config");
		
		this.setVisible(true);
	}
	
	
	
	
	// Setters and Getters 


	public void setActualCategories_List(JList actualCategories_List) {
		ActualCategories_List = actualCategories_List;
	}

	

	public ArrayList<String> getActCat() {
		return ActCat;
	}

	public JTextField getTextSubName() {
		return TextSubName;
	}

	public JLabel getName_Warning() {
		return Name_Warning;
	}


	public JList getActualCategories_List() {
		return ActualCategories_List;
	}

	public String[] getAllCategories() {
		return AllCategories;
	}

	public void setActCat(ArrayList<String> actCat) {
		ActCat = actCat;
	}

	public void setAllCategories(String[] allCategories) {
		AllCategories = allCategories;
	}

	public JLabel getChooseCategWarning_Label() {
		return ChooseCategWarning_Label;
	}

	public JLabel getSubCatAlreadyHaveWarning_Label() {
		return SubCatAlreadyHaveWarning_Label;
	}

	public JList getCategories_List() {
		return Categories_List;
	}
	

	// Adding Action Listeners
	

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
	
	public void addButtonBackTomanageActionListener(ActionListener e)
	{
		BackToManageBooksButton.addActionListener(e);
	}
	
	public void addButtonAddCategoryActionListener(ActionListener e)
	{
		AddCategoryButton.addActionListener(e);
	}
	
	public void addButtonRemoveCategoryActionListener(ActionListener e)
	{
		RemoveCategoryButton.addActionListener(e);
	}
}
