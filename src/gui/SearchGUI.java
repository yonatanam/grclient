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

public class SearchGUI extends JFrame {


	private JButton BackButton;
	private JTable searchData;
	private JScrollPane scrollPane;
	private UserMenu userMenu;
	private JButton btnAddToCart;
	private JButton btnCheckout;
	public SearchGUI() {
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
		
		JLabel lblSearchResults = new JLabel("Search results:");
		lblSearchResults.setForeground(Color.WHITE);
		lblSearchResults.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSearchResults.setBounds(39, 17, 179, 31);
		getContentPane().add(lblSearchResults);
		

		
		
		
		//Back button
		Image imgLogin = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setIcon(new ImageIcon(imgLogin));
		BackButton.setBounds(32, 464, 106, 41);
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setForeground(Color.WHITE);
		getContentPane().add(BackButton);
		//Back button END
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 63, 713, 338);
		getContentPane().add(scrollPane);
		
		searchData = new JTable();
		scrollPane.setViewportView(searchData);
		searchData.setDefaultEditor(Object.class, null); //prevents from editing the fields but allows row selection
		searchData.getTableHeader().setReorderingAllowed(false);
		setLocationRelativeTo(null);
		setVisible(true);
		

		
		
		btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.setBounds(290, 412, 131, 41);
		btnAddToCart.setFont(new Font("Arial", Font.BOLD, 15));
		btnAddToCart.setIcon(new ImageIcon(imgLogin));
		btnAddToCart.setOpaque(false);
		btnAddToCart.setContentAreaFilled(false);
		btnAddToCart.setBorderPainted(false);
		btnAddToCart.setHorizontalTextPosition(JButton.CENTER);
		btnAddToCart.setVerticalTextPosition(JButton.CENTER);
		btnAddToCart.setForeground(Color.WHITE);
		getContentPane().add(btnAddToCart);
		
		btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(441, 412, 131, 41);
		btnCheckout.setFont(new Font("Arial", Font.BOLD, 15));
		btnCheckout.setIcon(new ImageIcon(imgLogin));
		btnCheckout.setOpaque(false);
		btnCheckout.setContentAreaFilled(false);
		btnCheckout.setBorderPainted(false);
		btnCheckout.setHorizontalTextPosition(JButton.CENTER);
		btnCheckout.setVerticalTextPosition(JButton.CENTER);
		btnCheckout.setForeground(Color.WHITE);
		getContentPane().add(btnCheckout);
		
		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		
		
		//BackGround END
		
		
		
	}


	//Action Listeners
	public void addButtonAddToCartActionListener(ActionListener e)
	{
		btnAddToCart.addActionListener(e);
	}

	public void addButtonBackFromSearchGUIActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
	public void addButtonCheckoutActionListner(ActionListener e)
	{
		btnCheckout.addActionListener(e);
	}
	

	/**Setters and Getters*/
	public JButton getBackButton() {
		return BackButton;
	}

	public void setBackButton(JButton backButton) {
		BackButton = backButton;
	}

	public JTable getSearchData() {
		return searchData;
	}

	public void setSearchData(JTable searchData) {
		this.searchData = searchData;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public UserMenu getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(UserMenu userMenu) {
		this.userMenu = userMenu;
	}

	public JButton getBtnAddToCart() {
		return btnAddToCart;
	}

	public void setBtnAddToCart(JButton btnAddToCart) {
		this.btnAddToCart = btnAddToCart;
	}
	
	/**End getters and setters*/
	public void populateTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		searchData.setModel(model);
	}
}
