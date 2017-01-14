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
	private JButton searchButton;
	private JButton advancedSearch;

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
		searchBar.setBounds(227, 250, 268, 31);
		contentPane.add(searchBar);
		//searchBar END
		
		
		JLabel searchIcon = new JLabel();
		searchIcon.setBounds(224, 250, 268, 31);
		Image img2 = new ImageIcon(this.getClass().getResource("/searchBar.png")).getImage();
		searchIcon.setIcon(new ImageIcon(img2));
		contentPane.add(searchIcon);
		//SearchBar label END
		
		
		
		//Sign IN Button
		searchButton = new JButton("");
		searchButton.setFont(new Font("Arial", Font.BOLD, 15));
		Image imgLogin = new ImageIcon(this.getClass().getResource("/Zoom.png")).getImage();
		searchButton.setIcon(new ImageIcon(imgLogin));
		searchButton.setBounds(491, 250, 44, 31);
		searchButton.setOpaque(false);
		searchButton.setContentAreaFilled(false);
		searchButton.setBorderPainted(false);
		searchButton.setHorizontalTextPosition(JButton.CENTER);
		searchButton.setVerticalTextPosition(JButton.CENTER);
		searchButton.setForeground(Color.WHITE);
		contentPane.add(searchButton);
		//Sign IN button END
		
		
		
		//Forgot Password Button
		advancedSearch = new JButton("ADVANCED SEARCH");
		advancedSearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		advancedSearch.setBounds(290, 307, 143, 25);
		advancedSearch.setForeground(Color.WHITE);
		advancedSearch.setOpaque(false);
		advancedSearch.setContentAreaFilled(false);
		advancedSearch.setBorderPainted(false);
		advancedSearch.setHorizontalTextPosition(JButton.CENTER);
		advancedSearch.setVerticalTextPosition(JButton.CENTER);
		contentPane.add(advancedSearch);
		//Forgot Password Button END
		
		
		
		//keywordField field JText
		keywordField = new JTextField()
		{
		    @Override public void setBorder(Border border) {   //Disable field's border
		        // No!
		    }
		};
		
		keywordField.setForeground(Color.WHITE);
		keywordField.setFont(new Font("Arial", Font.PLAIN, 13));
		keywordField.setOpaque(false);       //Make Field transparent 
		keywordField.setText("Search by keyword");
		keywordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				keywordField.setText("");               
			}
		});
		
		keywordField.setToolTipText("");
		keywordField.setColumns(10);
		keywordField.setBounds(256, 358, 224, 39);
		contentPane.add(keywordField);
		keywordField.setVisible(false);
		//keywordField field JText END
		
		//Email Label
		keywordLabel = new JLabel();
		keywordLabel.setBounds(253, 358, 229, 40);
		Image img4 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		keywordLabel.setIcon(new ImageIcon(img4));
		contentPane.add(keywordLabel);
		keywordLabel.setVisible(false);
		//Email Label END
		

		//This is the LOGO
		JLabel Logo = new JLabel();
		Logo.setBounds(306, 129, 139, 82);
		Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		Logo.setIcon(new ImageIcon(logo));
		contentPane.add(Logo);
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(10, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		contentPane.add(bg);
		setLocationRelativeTo(null);
		//panel.setOpaque(false);
		setVisible(true);
	} 
	
	
	
	public void advancedSearchcActionListener(ActionListener e)
	{
		advancedSearch.addActionListener(e);
	}
	
	public void displayAdvancedSearchField()
	{
		keywordLabel.setVisible(true);
		keywordField.setVisible(true);
	}
	
	public void disposeAdvancedSearchField()
	{
		keywordLabel.setVisible(false);
		keywordField.setVisible(false);
	}


}
