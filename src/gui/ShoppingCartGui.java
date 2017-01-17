package gui;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import client.App;
import javax.swing.JTextField;

public class ShoppingCartGui extends JFrame
{
	
	private final int PRICE_COLUMN = 2;
	
	private JLabel lblBooksPayment;
	private JLabel lblDir;
	private JLabel lblTotal;
	private JLabel lblSumResult;
	
	
	private JTable jtBooksTable;
	
	
	private JScrollPane jspBooksTable;
	
	private JButton btnPay;
	private JButton btnBrowse;
	private JButton btnBack;
	
	private UserMenu userMenu;
	
	
	private JTextField jtDir;
	
	
    private Object[][] rowData = { { "Harry Potter 1", "J.K Rowling", "50" },
            						{ "Harry Potter 2", "J.K Rowling", "90" } };
    private final String[]  columnNames = { "Book's name", "Authors", "Price" };
	
	public ShoppingCartGui()
	{
		/** Menu */
		/*
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		add(menu);	
		/** End menu */
		
		
		lblBooksPayment = new JLabel("Books Payment");
		lblBooksPayment.setFont(new Font("Tahoma", Font.PLAIN, 18));		
		lblBooksPayment.setBounds(95, 44, 174, 29);
		getContentPane().add(lblBooksPayment); 
		
		
		/*----------------------------Properties of JTable----------------------------*/
		jtBooksTable = new JTable(rowData, columnNames);
		jtBooksTable.setDefaultEditor(Object.class, null);
		jtBooksTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtBooksTable.setFillsViewportHeight(true);		
		jtBooksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtBooksTable.setRowHeight(50);		
		
		jspBooksTable = new JScrollPane(jtBooksTable);
		jspBooksTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspBooksTable.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Books"),
									BorderFactory.createEmptyBorder(5,5,5,5)),
						jspBooksTable.getBorder()));	
		jspBooksTable.setBounds(21, 91, 497, 325);	
		getContentPane().add(jspBooksTable);

	    /*----------------------------End of properties of JTable----------------------------*/	

		
		lblTotal = new JLabel("Total (" + rowData.length + " Books):");
		lblTotal.setBounds(39, 444, 89, 14);
		getContentPane().add(lblTotal);
		
		lblSumResult = new JLabel(getTotalPrice() + "$.");
		lblSumResult.setBounds(153, 444, 196, 14);
		getContentPane().add(lblSumResult);
		
		lblDir = new JLabel("Directory:");
		lblDir.setBounds(39, 473, 69, 14);
		getContentPane().add(lblDir);
		
		jtDir = new JTextField();
		jtDir.setBounds(153, 470, 245, 20);
		getContentPane().add(jtDir);
		jtDir.setColumns(10);
		jtDir.setOpaque(false);
		jtDir.setEditable(false);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(429, 469, 89, 23);
		getContentPane().add(btnBrowse);		
		
		btnPay = new JButton("Pay");
		btnPay.setBounds(570, 538, 89, 23);
		getContentPane().add(btnPay);
		btnPay.setEnabled(false);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(694, 538, 89, 23);
		getContentPane().add(btnBack);
		
		setTitle("Books Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		setLocationRelativeTo(null);		
		getContentPane().setLayout(null);			
		setVisible(true);
	}

	public void setJtDir(String path)
	{
		jtDir.setText(path);
	}

	
	
	public String getDirecotry()
	{
		return jtDir.getText();
	}
	
	public int getTotalPrice() //NOT IN HERE in the controller!!!
	{
		int total = 0;
		for(int i = 0; i < rowData.length; i++)
			total += Integer.valueOf(jtBooksTable.getValueAt(i, PRICE_COLUMN).toString());
		return total;
	}
	
	public void enableBtnPay()	
	{
		btnPay.setEnabled(true);
	}
	
	public void addBtnBrowseActionListener(ActionListener listener)
	{
		btnBrowse.addActionListener(listener);
	}
	
	public void addBtnBackActionListener(ActionListener listener)
	{
		btnBack.addActionListener(listener);
	}
	
	public void addBtnPayActionListener(ActionListener listener)
	{
		btnPay.addActionListener(listener);
	}	
	
	
	
	
}
