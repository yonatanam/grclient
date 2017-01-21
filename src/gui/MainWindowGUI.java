package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

//import org.eclipse.wb.swing.FocusTraversalOnArray;

//import controllers.ExplorerController;

import java.awt.Component;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

//import models.Envelope;
import models.User;
//import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import client.App;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindowGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private UserMenu userMenu;
	private JTextField searchBar;


	private JTextField keywordField;
	private JLabel keywordLabel;
	private JTextField CategoryField;
	private JLabel CategoryLabel;
	private JTextField authorField;
	private JLabel authorLabel;
	private JButton authorButton;
	private JTextField tocField;
	private JLabel tocLabel;
	private JButton tocButton;
	private JTextField langField;
	private JLabel langLabel;
	private JButton langButton;
	private JTextField SynopsisField;
	private JLabel SynopsisLabel;
	private JButton SynopsisButton;
	private JButton searchButton;
	private JButton advancedSearch;
	private JButton Keywordsbutton;
	private JButton categorybutton;
	private JLabel keyWord_Warning;
	private JLabel category_Warning;
	private JLabel author_Warning;
	private JLabel language_Warning;
	private JLabel toc_Warning;
	private JLabel synopsis_Warning;
	private JButton btnAny;
	private JLabel Any_Warning;
	
	/**
	 * Create the frame. This is the Home window.
	 */
	public MainWindowGUI() {
		setResizable(false);
		setTitle("Good Reading");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		contentPane.add(menu);
		
		
		//searchBar Field JText
		searchBar = new JTextField()
		{
				  @Override public void setBorder(Border border) {   //Disable field's border
				      // No!
				  }
		};
		searchBar.setForeground(Color.WHITE);
		searchBar.setFont(new Font("Arial", Font.PLAIN, 13));
		searchBar.setOpaque(false);       //Make Field transparent 
		searchBar.setText("Search book here");
		searchBar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				searchBar.setText("");                   //Clear "Password String" on focus
			}
		});
		searchBar.setToolTipText("");
		searchBar.setColumns(10);
		searchBar.setBounds(227, 212, 268, 31);
		contentPane.add(searchBar);		
		searchButton = new JButton("");
		searchButton.setFont(new Font("Arial", Font.BOLD, 15));
		Image imgLogin = new ImageIcon(this.getClass().getResource("/Zoom.png")).getImage();
		searchButton.setIcon(new ImageIcon(imgLogin));
		searchButton.setBounds(443, 212, 44, 31);
		searchButton.setOpaque(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setHorizontalTextPosition(JButton.CENTER);
		searchButton.setVerticalTextPosition(JButton.CENTER);
		searchButton.setForeground(Color.WHITE);
		contentPane.add(searchButton);
		//searchBar END		
		searchButton.setBounds(491, 212, 44, 31);
		searchButton.setOpaque(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setHorizontalTextPosition(JButton.CENTER);
		searchButton.setVerticalTextPosition(JButton.CENTER);
		searchButton.setForeground(Color.WHITE);
		
		
		JLabel searchIcon = new JLabel();
		searchIcon.setBounds(224, 212, 268, 31);
		Image img2 = new ImageIcon(this.getClass().getResource("/searchBar.png")).getImage();
		searchIcon.setIcon(new ImageIcon(img2));
		contentPane.add(searchIcon);
		//SearchBar label END
		// advancedSearch Button
		advancedSearch = new JButton("ADVANCED SEARCH");
		advancedSearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		advancedSearch.setBounds(306, 243, 143, 25);
		advancedSearch.setForeground(Color.WHITE);
		advancedSearch.setOpaque(false);
		advancedSearch.setContentAreaFilled(false);
		advancedSearch.setBorderPainted(false);
		advancedSearch.setHorizontalTextPosition(JButton.CENTER);
		advancedSearch.setVerticalTextPosition(JButton.CENTER);
		contentPane.add(advancedSearch);
		// advancedSearch Button END
		//----------------------------------------
		//keyword
		Image img4 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		keywordField = new JTextField()
		{
		    @Override public void setBorder(Border border) {   //Disable field's border
		        // No!
		    }
		};		
		this.settextfield(keywordField,265,"for many keyword write after each keyword ';'","Search by keyword");
		keywordLabel = new JLabel();
		setLabel(keywordLabel,265);		
		Keywordsbutton = new JButton("");
		setbutton(Keywordsbutton, 265);			
		keyWord_Warning = new JLabel("");
		setWarning(keyWord_Warning,275);
		
		//keyword end
		
		//Category		
		CategoryField = new JTextField()
		{
		   @Override public void setBorder(Border border) {   //Disable field's border
				        // No!
	        }
		};
		this.settextfield(CategoryField, 305, "for many categories write after each category ';'", "Search by Category");		
		CategoryLabel = new JLabel();
		this.setLabel(CategoryLabel, 305);
		categorybutton = new JButton("");	
		this.setbutton(categorybutton, 305);
		category_Warning = new JLabel("");
		setWarning(category_Warning,315);
		//category end
		
		//author
		authorField = new JTextField()
		{
		   @Override public void setBorder(Border border) {   //Disable field's border
						        // No!
	        }
		};
		this.settextfield(authorField, 345, "for many authors write after each author ';'", "Search by author");
		authorLabel = new JLabel();
		this.setLabel(authorLabel, 345);		
		authorButton = new JButton("");
		this.setbutton(authorButton, 345);
		author_Warning = new JLabel("");
		setWarning(author_Warning,355);
		//author end
		
		//TOC

		tocField = new JTextField()
		{
		   @Override public void setBorder(Border border) {   //Disable field's border
						        // No!
	        }
		};
		this.settextfield(tocField, 385,"enter part of the table of content", "Search by table of content");
		tocLabel = new JLabel();
		this.setLabel(tocLabel, 385);		
		tocButton = new JButton("");
		this.setbutton(tocButton, 385);
		toc_Warning = new JLabel("");
		setWarning(toc_Warning,395);
			
		//language
		
		langField = new JTextField()
		{
		   @Override public void setBorder(Border border) {   //Disable field's border
						        // No!
		       }
		};
		this.settextfield(langField, 425,"for many languages write after each language ';'", "Search by book's language");
		langLabel = new JLabel();
		this.setLabel(langLabel, 425);		
		langButton = new JButton("");
		this.setbutton(langButton, 425);
		language_Warning = new JLabel("");
		setWarning(language_Warning,435);
		//language
		//synopsis
		
		SynopsisField = new JTextField()
		{
		   @Override public void setBorder(Border border) {   //Disable field's border
						        // No!
		       }
		};
		this.settextfield(SynopsisField, 465,"enter part of the book's Synopsis", "Search by book's Synopsis");
		SynopsisLabel = new JLabel();
		this.setLabel(SynopsisLabel, 465);		
		SynopsisButton = new JButton("");
		this.setbutton(SynopsisButton, 465);
		synopsis_Warning = new JLabel("");
		setWarning(synopsis_Warning,475);
		//language
		
				
		//This is the LOGO
		JLabel Logo = new JLabel();
	    Logo.setBounds(306, 91, 139, 82);
		Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		Logo.setIcon(new ImageIcon(logo));
		contentPane.add(Logo);
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(10, 11, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		//any type
		btnAny = new JButton("any");
		btnAny.setBounds(306, 502, 89, 23);
		
		btnAny.setFont(new Font("Arial", Font.BOLD, 15));
		Image imgbutton = new ImageIcon(this.getClass().getResource("/Button.png")).getImage();
		btnAny.setIcon(new ImageIcon(imgbutton));
		btnAny.setOpaque(false);
		btnAny.setContentAreaFilled(false);
		btnAny.setBorderPainted(false);
		btnAny.setHorizontalTextPosition(JButton.CENTER);
		btnAny.setVerticalTextPosition(JButton.CENTER);
		btnAny.setForeground(Color.WHITE);
		btnAny.setVisible(false);
		btnAny.setToolTipText("click here to search with multiple criteria");
		contentPane.add(btnAny);
		
		Any_Warning = new JLabel("");
		Any_Warning.setBounds(227, 536, 290, 25);
		Any_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		Any_Warning.setForeground(Color.RED);
		contentPane.add(Any_Warning);
		//end any type button
		bg.setIcon(new ImageIcon(img1));
		contentPane.add(bg);
		setLocationRelativeTo(null);
		//panel.setOpaque(false);
		setVisible(true);
	} 
	
	private void setbutton(JButton b,int Y)
	{
		Image imgLogin = new ImageIcon(this.getClass().getResource("/Zoom.png")).getImage();
				
		b.setVerticalTextPosition(SwingConstants.CENTER);
		b.setOpaque(false);
		b.setHorizontalTextPosition(SwingConstants.CENTER);
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Arial", Font.BOLD, 15));
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setBounds(439, Y, 44, 40);
		b.setIcon(new ImageIcon(imgLogin));
		b.setVisible(false);
		contentPane.add(b);
	}
	private void setWarning(JLabel label ,int y)
	{
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setForeground(Color.RED);
		label.setBounds(504, y, 233, 22);
		getContentPane().add(label);
	}
	private void settextfield(JTextField text,int Y,String tooltip,String search)
	{
		text.setToolTipText(tooltip);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(text.getText().equals(search))
				text.setText("");  }});
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText().equals(""))
					text.setText(search);}});
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Arial", Font.PLAIN, 13));
		text.setOpaque(false);       //Make Field transparent 
		text.setText(search);
		text.setColumns(10);
		text.setBounds(253, Y, 183, 40);
		contentPane.add(text);
		text.setVisible(false);
	}
	private void setLabel(JLabel label,int Y)
	{
	
	label.setBounds(253, Y, 183, 40);
	Image img4 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
	label.setIcon(new ImageIcon(img4));
	contentPane.add(label);
	label.setVisible(false);
	}
	public void addSearchButtonActionListener(ActionListener listener)
	{
		searchButton.addActionListener(listener);
	}	
	public void addAuthorSearchButtonActionListener(ActionListener listener)
	{
		authorButton.addActionListener(listener);
	}	
	public void addlangSearchButtonActionListener(ActionListener listener)
	{
		langButton.addActionListener(listener);
	}	
	public void addtocSearchButtonActionListener(ActionListener listener)
	{
		tocButton.addActionListener(listener);
	}
	public void addCategorySearchButtonActionListener(ActionListener listener)
	{
		categorybutton.addActionListener(listener);
	}
	public void addKeySearchButtonActionListener(ActionListener listener)
	{
		Keywordsbutton.addActionListener(listener);
	}
	public void addSynopsisSearchButtonActionListener(ActionListener listener)
	{
		SynopsisButton.addActionListener(listener);
	}
	public void addanySearchButtonActionListener(ActionListener listener)
	{
		btnAny.addActionListener(listener);
	}
	
	
	
	public void advancedSearchcActionListener(ActionListener e)
	{
		advancedSearch.addActionListener(e);
	}
	
	public void displayAdvancedSearchField()
	{
		keywordLabel.setVisible(true);
		keywordField.setVisible(true);
		CategoryField.setVisible(true);
		CategoryLabel.setVisible(true);
		authorField.setVisible(true);
		authorLabel.setVisible(true);
		categorybutton.setVisible(true);
		Keywordsbutton.setVisible(true);
		authorButton.setVisible(true);
		tocButton.setVisible(true);
		tocField.setVisible(true);
		tocLabel.setVisible(true);
		langButton.setVisible(true);
		langField.setVisible(true);
		langLabel.setVisible(true);
		SynopsisField.setVisible(true);
		SynopsisButton.setVisible(true);
		SynopsisLabel.setVisible(true);
		btnAny.setVisible(true);
		
	}
	
	public void disposeAdvancedSearchField()
	{
		keywordLabel.setVisible(false);
		keywordField.setVisible(false);
		CategoryField.setVisible(false);
		CategoryLabel.setVisible(false);
		categorybutton.setVisible(false);
		Keywordsbutton.setVisible(false);
		authorButton.setVisible(false);
		authorField.setVisible(false);
		authorLabel.setVisible(false);
		tocButton.setVisible(false);
		tocField.setVisible(false);
		tocLabel.setVisible(false);
		langButton.setVisible(false);
		langField.setVisible(false);
		langLabel.setVisible(false);
		SynopsisField.setVisible(false);
		SynopsisButton.setVisible(false);
		SynopsisLabel.setVisible(false);
		btnAny.setVisible(false);
	}
	public void setkeyWord_Warning(String t)
	{
		 keyWord_Warning.setText(t);
	}
	public void setany_Warning(String t)
	{
		 Any_Warning.setText(t);
	}
	public void setcategory_Warning(String t)
	{
		 category_Warning.setText(t);
	}
	public void setauthor_Warning(String t)
	{
		 author_Warning.setText(t);
  	}
	public void setlanguage_Warning(String t)
	{
		 language_Warning.setText(t);
	}
	public void settoc_Warning(String t)
	{
		 toc_Warning.setText(t);
	}
	public void setsynopsis_Warning(String t)
	{
		 synopsis_Warning.setText(t);
	}
	 
	/**Getters and setters*/
	public JTextField getSynopsisSearchBar() {
		return SynopsisField;
	}
	public JTextField getSearchBar() {
		return searchBar;
	}
	public JTextField getCategorySearchBar() {
		return this.CategoryField;
	}
	public JTextField getlangSearchBar() {
		return this.langField;
	}
	public JTextField getKeyWordsSearchBar() {
		return this.keywordField;
	}
	public JTextField gettocSearchBar() {
		return this.tocField;
	}
	public JTextField getauthorSearchBar() {
		return this.authorField;
	}

	public void setSearchBar(JTextField searchBar) {
		this.searchBar = searchBar;
	}
}
