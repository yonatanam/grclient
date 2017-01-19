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
import javax.swing.SwingConstants;

public class UserReportGUI extends JFrame {


	private JButton BackButton;

	private JTable usersData;
	private JTable ordersData;
	private JTable booksData;
	private JScrollPane userScrollPane;
	private JScrollPane ordersScrollPane;
	private JScrollPane booksScrollPane;

	private UserMenu userMenu;
	private JButton btnViewOrders;
	private JButton btnViewBooks;
	private JTable table;

	public UserReportGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {

		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);


		JLabel lblUserReport = new JLabel("User orders report:");
		lblUserReport.setForeground(Color.WHITE);
		lblUserReport.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUserReport.setBounds(39, 17, 179, 31);
		getContentPane().add(lblUserReport);





		//Back button
		Image imgLogin = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setIcon(new ImageIcon(imgLogin));
		BackButton.setBounds(39, 509, 106, 41);
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setForeground(Color.WHITE);
		getContentPane().add(BackButton);
		//Back button END


		userScrollPane = new JScrollPane();
		userScrollPane.setBounds(39, 63, 713, 149);
		getContentPane().add(userScrollPane);

		usersData = new JTable();
		userScrollPane.setViewportView(usersData);
		usersData.setDefaultEditor(Object.class, null); //prevents from editing the fields but allows row selection
		usersData.getTableHeader().setReorderingAllowed(false);


		ordersScrollPane = new JScrollPane();
		ordersScrollPane.setBounds(39, 275, 271, 156);
		getContentPane().add(ordersScrollPane);


		ordersData = new JTable();
		ordersScrollPane.setViewportView(ordersData);
		ordersData.setDefaultEditor(Object.class, null);
		ordersData.getTableHeader().setReorderingAllowed(false);

		booksScrollPane = new JScrollPane();
		booksScrollPane.setBounds(366, 275, 386, 156);
		getContentPane().add(ordersScrollPane);
		booksData = new JTable();
		booksScrollPane.setViewportView(booksData);
		booksData.setDefaultEditor(Object.class, null);
		booksData.getTableHeader().setReorderingAllowed(false);
		getContentPane().add(booksScrollPane);






		btnViewOrders = new JButton("View orders");
		btnViewOrders.setBounds(25, 223, 131, 41);
		btnViewOrders.setFont(new Font("Arial", Font.BOLD, 15));
		btnViewOrders.setIcon(new ImageIcon(imgLogin));
		btnViewOrders.setOpaque(false);
		btnViewOrders.setContentAreaFilled(false);
		btnViewOrders.setBorderPainted(false);
		btnViewOrders.setHorizontalTextPosition(JButton.CENTER);
		btnViewOrders.setVerticalTextPosition(JButton.CENTER);
		btnViewOrders.setForeground(Color.WHITE);
		getContentPane().add(btnViewOrders);


		btnViewBooks = new JButton("View books");
		btnViewBooks.setVerticalTextPosition(JButton.CENTER);
		btnViewBooks.setIcon(new ImageIcon(imgLogin));
		btnViewBooks.setOpaque(false);
		btnViewBooks.setHorizontalTextPosition(JButton.CENTER);
		btnViewBooks.setForeground(Color.WHITE);
		btnViewBooks.setFont(new Font("Arial", Font.BOLD, 15));
		btnViewBooks.setContentAreaFilled(false);
		btnViewBooks.setBorderPainted(false);
		btnViewBooks.setBounds(25, 457, 131, 41);
		getContentPane().add(btnViewBooks);
		
		setLocationRelativeTo(null);
		setVisible(true);


		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		






	}
	/**Action Listeners*/

	public void addButtonViewOrdersActionListener(ActionListener e)
	{
		btnViewOrders.addActionListener(e);
	}
	public void addButtonBackFromUserReportActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	public void addButtonViewBooksActionListener(ActionListener e)
	{
		btnViewBooks.addActionListener(e);
	}

	/**End action listeners*/
	public void populateUserTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		usersData.setModel(model);

	}

	public void populateOrdersTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		ordersData.setModel(model);

	}

	public void populateBooksTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		booksData.setModel(model);

	}


	/**Setters and Getters*/

	public JTable getUsersData() {
		return usersData;
	}

	public void setUsersData(JTable data) {
		this.usersData = data;
	}

	public JTable getBooksData() {
		return booksData;
	}

	public void setBooksData(JTable booksData) {
		this.booksData = booksData;
	}

	public JScrollPane getBooksScrollPane() {
		return booksScrollPane;
	}

	public void setBooksScrollPane(JScrollPane booksScrollPane) {
		this.booksScrollPane = booksScrollPane;
	}

	public JButton getBackButton() {
		return BackButton;
	}

	public void setBackButton(JButton backButton) {
		BackButton = backButton;
	}

	public JTable getOrdersData() {
		return ordersData;
	}

	public void setOrdersData(JTable ordersData) {
		this.ordersData = ordersData;
	}

	public JScrollPane getUserScrollPane() {
		return userScrollPane;
	}

	public void setUserScrollPane(JScrollPane userScrollPane) {
		this.userScrollPane = userScrollPane;
	}

	public JScrollPane getOrdersScrollPane() {
		return ordersScrollPane;
	}

	public void setOrdersScrollPane(JScrollPane ordersScrollPane) {
		this.ordersScrollPane = ordersScrollPane;
	}

	public UserMenu getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(UserMenu userMenu) {
		this.userMenu = userMenu;
	}
}
