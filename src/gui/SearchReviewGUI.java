package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import client.App;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;


public class SearchReviewGUI extends JFrame
{
	private final int REVIEW_COLUMN = 2;
	
	private JLabel lblSearchreviews;
	private JLabel lblSearchBy;
	private JLabel lblSearchNotFound;
	private JLabel BackGround;
	private JLabel miniLogo;
	
	private JComboBox<String> cbSearchType;
		
	private JTextField SearchFiled;
	
	private JButton btnSearch;
	private JButton btnBack;
	
	private JPanel panelResults;
	private JPanel panelResultsFound;
	private JPanel panelResultsNotFound;
	
	private JTable jtResultsTable;
	
	private JScrollPane jspResultsTable;
	private JScrollPane jspReviewContent;
	
	private JTextArea jtaReviewContent;
	
	private String[] searchTypeData = {"Book's name","Book's writer", "Key words"};
	private String WriterName;
	private UserMenu userMenu;
	public SearchReviewGUI()
	{		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		
		lblSearchreviews = new JLabel("Search Reviews");
		lblSearchreviews.setForeground(Color.WHITE);
		lblSearchreviews.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));		
		lblSearchreviews.setBounds(219, 31, 287, 37);
		getContentPane().add(lblSearchreviews); //JPanel doesn't have contentPane.
		
		
		
		lblSearchBy = new JLabel("Search by: ");
		lblSearchBy.setForeground(Color.WHITE);
		lblSearchBy.setBounds(36, 94, 73, 14);
		getContentPane().add(lblSearchBy);
		
		cbSearchType = new JComboBox(searchTypeData);
		cbSearchType.setBounds(107, 91, 109, 20);
		getContentPane().add(cbSearchType);
		
		SearchFiled = new JTextField();
		SearchFiled.setBounds(226, 91, 235, 21);
		getContentPane().add(SearchFiled);
				
		/*btnSearch = new JButton("Search");
		btnSearch.setBounds(459, 91, 82, 20);
		getContentPane().add(btnSearch);*/
		
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		btnSearch = new JButton("Search");
		btnSearch.setBounds(457, 84, 122, 33);
		getContentPane().add(btnSearch);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setIcon(new ImageIcon(buttoncan));
		btnSearch.setHorizontalTextPosition(JButton.CENTER);
		btnSearch.setVerticalTextPosition(JButton.CENTER);
		btnSearch.setOpaque(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setBorderPainted(false);
		
		panelResults = new JPanel();
		panelResults.setBackground(Color.WHITE);
		panelResults.setLayout(null);
		panelResults.setBounds(20, 137, 664, 386);
		getContentPane().add(panelResults);
		
		panelResultsFound = new JPanel();
		panelResultsFound.setLayout(null);
		panelResultsFound.setBounds(10, 11, 664, 422);
		panelResultsFound.setVisible(false);
		
		panelResultsNotFound = new JPanel();
		panelResultsNotFound.setLayout(null);
		panelResultsNotFound.setBounds(10, 11, 664, 422);
		panelResultsNotFound.setVisible(false);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(666, 536, 128, 25);
		getContentPane().add(btnBack);
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setIcon(new ImageIcon(buttoncan));
		btnBack.setHorizontalTextPosition(JButton.CENTER);
		btnBack.setVerticalTextPosition(JButton.CENTER);
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		
		initializeResults();
		
		setTitle("Search Review");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		setLocationRelativeTo(null);		
		getContentPane().setLayout(null);			
		setVisible(true);
		
		miniLogo = new JLabel("");
		miniLogo.setBounds(680, 13, 93, 55);
		Image miniLogoImage = new ImageIcon(this.getClass().getResource("/miniLogo.png")).getImage();
		getContentPane().add(miniLogo);
		miniLogo.setIcon(new ImageIcon(miniLogoImage));
		
		BackGround = new JLabel("");
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		BackGround.setBounds(0, 0, 794, 566);
		getContentPane().add(BackGround);
		BackGround.setIcon(new ImageIcon(imgbg));
	}

	
	public JScrollPane getJspReviewContent()
	{
		return jspReviewContent;
	}
	
	public int getSelectedRow()
	{
		return jtResultsTable.getSelectedRow();
	}
	
	public String getReviewContentFromJTable(int row)	
	{
		return jtResultsTable.getValueAt(row, REVIEW_COLUMN).toString();
	}
	
	public JTextArea getJtaReviewContent()
	{
		return jtaReviewContent;
	}
	
	public String getSearhType()
	{
		return String.valueOf(cbSearchType.getSelectedItem());
	}	
	
	public String getSearchFieldText()
	{
		return SearchFiled.getText();
	}

	public void addBtnBackActionListener(ActionListener listener)
	{
		btnBack.addActionListener(listener);
	}
	
	public void addBtnSearchActionListener(ActionListener listener)
	{
		btnSearch.addActionListener(listener);
	}
	
	public void addJtResultsTableListSelectionListener(ListSelectionListener listener)
	{
		jtResultsTable.getSelectionModel().addListSelectionListener(listener);
	}	
	
	public void generateResultsTable(Vector<Object> columnNames, Vector<Object> data)
	{
		panelResultsNotFound.setVisible(false);
		jspReviewContent.setVisible(false);
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		jtResultsTable.setModel(model);
		
	   	TableColumnModel columnModel = jtResultsTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(1);
		columnModel.getColumn(1).setPreferredWidth(40);
		columnModel.getColumn(2).setPreferredWidth(180);
		
		panelResultsFound.setVisible(true);
	}	
	
	public void emptyResults()
	{
		panelResultsFound.setVisible(false);
		jspReviewContent.setVisible(false);
		panelResultsNotFound.setVisible(true);
	}
	
	public void initializeResults()
	{
		/**----------------------------Initialize output for search NOT found----------------------------*/
		lblSearchNotFound =  new JLabel("We couldn't find your request");
		lblSearchNotFound.setFont(new Font("Tahoma", Font.BOLD, 14));	
		lblSearchNotFound.setBounds(210, 0, 230, 40);
		panelResultsNotFound.add(lblSearchNotFound);
		panelResults.add(panelResultsNotFound);
		/**----------------------------End of Initialize output for search not found----------------------------*/
		
		
		/**----------------------------Initialize output for search found----------------------------*/
		
		/**----------------------------Properties of JTable----------------------------*/
		jtResultsTable = new JTable();
		jtResultsTable.setDefaultEditor(Object.class, null);
		jtResultsTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jtResultsTable.setFillsViewportHeight(true);		
		jtResultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtResultsTable.setRowHeight(50);		
		
		jspResultsTable = new JScrollPane(jtResultsTable);
		jspResultsTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspResultsTable.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createCompoundBorder(
								BorderFactory.createTitledBorder("Search Results"),
									BorderFactory.createEmptyBorder(5,5,5,5)),
										jspResultsTable.getBorder()));	
		jspResultsTable.setBounds(21, 11, 462, 240);
		panelResultsFound.add(jspResultsTable); 		
		panelResults.add(panelResultsFound);
	    /**----------------------------End of properties of JTable----------------------------*/
		
	    jtaReviewContent = new JTextArea();
	    jtaReviewContent.setBounds(24, 285, 300, 126);
	    jtaReviewContent.setLineWrap(true);
	    jtaReviewContent.setWrapStyleWord(true);
	    jtaReviewContent.setOpaque(false);
	    jtaReviewContent.setEditable(false);
	    jtaReviewContent.setBorder(null);
	    
	    /** Properties of jspReviewContent **/
	    jspReviewContent= new JScrollPane (jtaReviewContent);
	    jspReviewContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    jspReviewContent.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createCompoundBorder(
                                    BorderFactory.createTitledBorder("Review's content"),
                                    BorderFactory.createEmptyBorder(5,5,5,5)), null));	    
	    jspReviewContent.setBounds(20, 271, 462, 140);
	    jspReviewContent.setVisible(false);	    
	    panelResultsFound.add(jspReviewContent);
	    
	    /** End of properties of jspReviewContent*/
	    
	    /**----------------------------End of Initialize output for search found----------------------------*/
	}
	
	
	
	
	
}
