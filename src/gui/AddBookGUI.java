package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.Scrollable;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import client.App;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuItem;

public class AddBookGUI extends JFrame {
	
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JButton ApplyButton;
	private JLabel BNameWarningLabel;
	private JLabel BidWarningLabel;
	private JLabel BackGround;
	private String[] langs = {"Hebrew", "English", "Arabic", "Albanian", "Franch", "Pakistanian"};
	private ButtonGroup formats;
	private ButtonGroup yesno;
	private JButton btnCancel;
	private JComboBox comboBox;
	private JRadioButton rdbtnPdf;
	private JRadioButton rdbtnDoc;
	private JRadioButton rdbtnFb;
	private JTextField Price;
	private JLabel lblWillItBe;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JLabel label;
	private JLabel label_1;
	private JTextArea synopsis_area;
	private JLabel label_2;
	private JPanel panel;
	private JTextArea TOC_area;
	private JTextArea keywords_area;
	private JLabel BpriceWarningLabel;
	private JLabel lblSelectBookAfilliation;
	private JLabel label_3;
	private JTextField txtBookAuthorid;
	private JLabel BAuthoridWarningLabel;
	private JLabel label_4;
	private JTextField txtBookAuthorname;
	private JLabel BAuthornameWarningLabel;
	private JComboBox<String> Subject_combobox;
	private JComboBox<String> Category_combobox;
	private UserMenu userMenu;
	
	
	public AddBookGUI() {
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		
		this.setTitle("Adding new book - Initial Config");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, 800, 600);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAddingABook = new JLabel("Adding new book");
		lblAddingABook.setForeground(Color.WHITE);
		lblAddingABook.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingABook.setBounds(79, 13, 203, 29);
		panel.add(lblAddingABook);
		
		txtBookId = new JTextField();
		txtBookId.setOpaque(false);   
		txtBookId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBookId.setForeground(Color.white);
		txtBookId.setText("Enter Book id*");
		txtBookId.setBounds(13, 87, 200, 16);
		panel.add(txtBookId);
		txtBookId.setColumns(10);
		
		label = new JLabel("");
		Image firstimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label.setIcon(new ImageIcon(firstimgfield));
		label.setBounds(10, 80, 224, 32);
		panel.add(label);
		
		JLabel lblFillInThe = new JLabel("Fill in all relevant data (star means obligatory)");
		lblFillInThe.setForeground(Color.GRAY);
		lblFillInThe.setFont(new Font("Arial", Font.BOLD, 15));
		lblFillInThe.setBounds(10, 49, 334, 18);
		panel.add(lblFillInThe);
		
		txtBookName = new JTextField();
		txtBookName.setOpaque(false);   
		txtBookName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBookName.setForeground(Color.white);
		txtBookName.setText("Enter Book name*");
		txtBookName.setBounds(13, 133, 134, 16);
		panel.add(txtBookName);
		txtBookName.setColumns(10);
		Image buttonapp = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		
		label_1 = new JLabel("");
		Image secondimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(secondimgfield));
		label_1.setBounds(10, 125, 224, 32);
		panel.add(label_1);
		
		BidWarningLabel = new JLabel("");
		BidWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		BidWarningLabel.setBounds(244, 84, 202, 22);
		panel.add(BidWarningLabel);
		
		BNameWarningLabel = new JLabel("");
		BNameWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		BNameWarningLabel.setBounds(245, 129, 200, 21);
		panel.add(BNameWarningLabel);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setForeground(Color.LIGHT_GRAY);
		lblLanguage.setFont(new Font("Arial", Font.BOLD, 18));
		lblLanguage.setBounds(16, 258, 93, 22);
		panel.add(lblLanguage);
		
		comboBox = new JComboBox(langs);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setBounds(128, 258, 110, 24);
		panel.add(comboBox);
		
		formats = new ButtonGroup();
		yesno = new ButtonGroup();
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		
		JScrollPane sc = new JScrollPane();
		sc.setLocation(526, 303);
		sc.setSize(243, 45);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(sc);
		
		synopsis_area = new JTextArea();
		synopsis_area.setText("Enter synopsis");
		synopsis_area.setLineWrap(true);
		synopsis_area.setWrapStyleWord(true);
		sc.setViewportView(synopsis_area);
		
		
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(258, 513, 131, 31);
		panel.add(btnCancel);
		btnCancel.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancel.setOpaque(false);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setIcon(new ImageIcon(buttoncan));
		btnCancel.setFont(new Font("Arial", Font.BOLD, 16));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		
		ApplyButton = new JButton("Apply");
		ApplyButton.setBounds(401, 513, 131, 31);
		panel.add(ApplyButton);
		ApplyButton.setFont(new Font("Arial", Font.BOLD, 16));
		ApplyButton.setForeground(Color.WHITE);
		
		
		
		
		
		ApplyButton.setIcon(new ImageIcon(buttonapp));
		ApplyButton.setHorizontalTextPosition(JButton.CENTER);
		ApplyButton.setVerticalTextPosition(JButton.CENTER);
		ApplyButton.setOpaque(false);
		ApplyButton.setContentAreaFilled(false);
		ApplyButton.setBorderPainted(false);
		
		lblWillItBe = new JLabel("Will the book be in the catalog?");
		lblWillItBe.setBounds(19, 392, 273, 22);
		panel.add(lblWillItBe);
		lblWillItBe.setForeground(Color.LIGHT_GRAY);
		lblWillItBe.setFont(new Font("Arial", Font.BOLD, 18));
		
		rdbtnNo = new JRadioButton("NO");
		rdbtnNo.setBounds(16, 439, 51, 27);
		panel.add(rdbtnNo);
		rdbtnNo.setForeground(Color.LIGHT_GRAY);
		rdbtnNo.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnNo.setOpaque(false);
		
		yesno.add(rdbtnNo);
		
		rdbtnYes = new JRadioButton("YES");
		rdbtnYes.setBounds(136, 439, 59, 27);
		panel.add(rdbtnYes);
		rdbtnYes.setSelected(true);
		rdbtnYes.setForeground(Color.LIGHT_GRAY);
		rdbtnYes.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnYes.setOpaque(false);
		yesno.add(rdbtnYes);
		
		Price = new JTextField();
		Price.setBounds(18, 485, 116, 22);
		Price.setOpaque(false);   
		Price.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Price.setForeground(Color.white);	
		panel.add(Price);
		Price.setText("Enter Book price*");
		Price.setColumns(10);
		
		JLabel lblFormat = new JLabel("Format:");
		lblFormat.setBounds(17, 300, 69, 22);
		panel.add(lblFormat);
		lblFormat.setForeground(Color.LIGHT_GRAY);
		lblFormat.setFont(new Font("Arial", Font.BOLD, 18));
		
				rdbtnDoc = new JRadioButton("DOC");
				rdbtnDoc.setBounds(17, 345, 63, 27);
				panel.add(rdbtnDoc);
				rdbtnDoc.setForeground(Color.LIGHT_GRAY);
				rdbtnDoc.setFont(new Font("Arial", Font.BOLD, 15));
				rdbtnDoc.setBackground(Color.BLACK);
				rdbtnDoc.setOpaque(false);
				formats.add(rdbtnDoc);
				
				rdbtnFb = new JRadioButton("FB2");
				rdbtnFb.setBounds(137, 345, 57, 27);
				panel.add(rdbtnFb);
				rdbtnFb.setForeground(Color.LIGHT_GRAY);
				rdbtnFb.setFont(new Font("Arial", Font.BOLD, 15));
				rdbtnFb.setOpaque(false);
				formats.add(rdbtnFb);
				
				rdbtnPdf = new JRadioButton("PDF");
				rdbtnPdf.setBounds(255, 345, 59, 27);
				panel.add(rdbtnPdf);
				rdbtnPdf.setFont(new Font("Arial", Font.BOLD, 15));
				rdbtnPdf.setForeground(Color.LIGHT_GRAY);
				rdbtnPdf.setOpaque(false);
				
				rdbtnPdf.setSelected(true);
				
				// for radio buttons to work properly
				formats.add(rdbtnPdf);
				
				label_2 = new JLabel("");
				Image thirdimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
				label_2.setIcon(new ImageIcon(thirdimgfield));
				label_2.setBounds(13, 481, 224, 32);
				panel.add(label_2);
				
				JScrollPane sc3 = new JScrollPane();
				sc3.setLocation(526, 455);
				sc3.setSize(243, 45);
	
				sc3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				panel.add(sc3);
				
				TOC_area = new JTextArea();
				TOC_area.setText("Enter TOC");
				TOC_area.setBounds(139, 495, 59, 22);
				sc3.setViewportView(TOC_area);
				
				JScrollPane sc2 = new JScrollPane();
				sc2.setLocation(526, 378);
				sc2.setSize(243, 46);
				sc2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				panel.add(sc2);
				
				keywords_area = new JTextArea();
				keywords_area.setText("Enter keywords");
				//panel.add(textArea_1);
				keywords_area.setBounds(135, 422, 243, 45);
				sc2.setViewportView(keywords_area);
				
				BpriceWarningLabel = new JLabel("");
				BpriceWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
				BpriceWarningLabel.setBounds(244, 482, 224, 29);
				panel.add(BpriceWarningLabel);
				
				JLabel Optional_Options = new JLabel("Additional Options:");
				Optional_Options.setFont(new Font("Arial", Font.BOLD, 18));
				Optional_Options.setForeground(Color.LIGHT_GRAY);
				Optional_Options.setBounds(458, 259, 180, 31);
				panel.add(Optional_Options);
				
				JLabel lblChooseCategory = new JLabel("Choose Category*:");
				lblChooseCategory.setForeground(Color.LIGHT_GRAY);
				lblChooseCategory.setFont(new Font("Arial", Font.BOLD, 18));
				lblChooseCategory.setBounds(458, 96, 180, 31);
				panel.add(lblChooseCategory);
				
				Category_combobox = new JComboBox<String>();
				Category_combobox.setFont(new Font("Arial", Font.BOLD, 15));
				Category_combobox.setForeground(Color.DARK_GRAY);
				Category_combobox.setBounds(635, 100, 134, 22);
				panel.add(Category_combobox);
				
				JLabel lblChooseSubject = new JLabel("Choose Subject*:");
				lblChooseSubject.setForeground(Color.LIGHT_GRAY);
				lblChooseSubject.setFont(new Font("Arial", Font.BOLD, 18));
				lblChooseSubject.setBounds(458, 152, 180, 31);
				panel.add(lblChooseSubject);
				
				Subject_combobox = new JComboBox<String>();
				Subject_combobox.setFont(new Font("Arial", Font.BOLD, 15));
				Subject_combobox.setBounds(635, 156, 134, 22);
				panel.add(Subject_combobox);
				
				lblSelectBookAfilliation = new JLabel("Select book affiliation (obligatory)");
				lblSelectBookAfilliation.setForeground(Color.GRAY);
				lblSelectBookAfilliation.setFont(new Font("Arial", Font.BOLD, 15));
				lblSelectBookAfilliation.setBounds(454, 49, 334, 18);
				panel.add(lblSelectBookAfilliation);
				Image forthimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
				
				txtBookAuthorid = new JTextField();
				txtBookAuthorid.setBounds(16, 176, 197, 16);
				txtBookAuthorid.setOpaque(false);   
				txtBookAuthorid.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				txtBookAuthorid.setForeground(Color.white);
				txtBookAuthorid.setText("Enter Book Author id *");
				panel.add(txtBookAuthorid);
				txtBookAuthorid.setColumns(10);
				
				label_3 = new JLabel("");
				label_3.setIcon(new ImageIcon(forthimgfield));
				label_3.setBounds(10, 169, 224, 32);
				panel.add(label_3);
				
				BAuthoridWarningLabel = new JLabel("");
				BAuthoridWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
				BAuthoridWarningLabel.setBounds(244, 173, 200, 21);
				panel.add(BAuthoridWarningLabel);
				
				label_4 = new JLabel("");
				Image fifthimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
				
				txtBookAuthorname = new JTextField();
				txtBookAuthorname.setText("Enter Book Author name*");
				txtBookAuthorname.setOpaque(false);   
				txtBookAuthorname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				txtBookAuthorname.setForeground(Color.white);
				txtBookAuthorname.setBounds(14, 218, 182, 22);
				panel.add(txtBookAuthorname);
				txtBookAuthorname.setColumns(10);
				label_4.setIcon(new ImageIcon(fifthimgfield));
				label_4.setBounds(10, 213, 224, 32);
				panel.add(label_4);
				
				BAuthornameWarningLabel = new JLabel("");
				BAuthornameWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
				BAuthornameWarningLabel.setBounds(240, 220, 200, 21);
				panel.add(BAuthornameWarningLabel);
				
				BackGround = new JLabel("");
				BackGround.setBounds(0, 0, 794, 566);
				panel.add(BackGround);
				BackGround.setIcon(new ImageIcon(imgbg));
		
		
		
		this.setVisible(true);
		
	}
	
	
	// ------------------ Getters and Setters for all components ---------------------------------//
	
	public JRadioButton getRdbtnYes() {
		return rdbtnYes;
	}


	public JRadioButton getRdbtnNo() {
		return rdbtnNo;
	}




	public JLabel getBpriceWarningLabel() {
		return BpriceWarningLabel;
	}


	public JTextField getPrice() {
		return Price;
	}


	public void SetBookIdText(String text)
	{
		 txtBookId.setText(text);
	}
	
	public void SetBookNameText(String text)
	{
		 txtBookName.setText(text);
	}
	
	public JTextField getBookIdText()
	{		
		return txtBookId;
	}
	
	public JTextField getBookNameText()
	{		
		return txtBookName;
	}
	
	public JLabel getBNameWarningLabel() {
		return BNameWarningLabel;
	}


	public JLabel getBidWarningLabel() {
		return BidWarningLabel;
	}



	public JComboBox getComboBox() {
		return comboBox;
	}
	
	public JRadioButton getRdbtnPdf() {
		return rdbtnPdf;
	}


	public JRadioButton getRdbtnDoc() {
		return rdbtnDoc;
	}


	public JRadioButton getRdbtnFb() {
		return rdbtnFb;
	}

	public JTextArea getSynopsisArea() {
		return synopsis_area;
	}
	



	public JTextArea getTOC_area() {
		return TOC_area;
	}


	public JTextArea getKeywords_area() {
		return keywords_area;
	}
	
	public JTextField getTxtBookAuthorid() {
		return txtBookAuthorid;
	}


	public JLabel getBAuthoridWarningLabel() {
		return BAuthoridWarningLabel;
	}

	public JLabel getBAuthornameWarningLabel() {
		return BAuthornameWarningLabel;
	}

	public JTextField getTxtBookAuthorname() {
		return txtBookAuthorname;
	}


	public JComboBox getSubject_combobox() {
		return Subject_combobox;
	}


	public JComboBox getCategory_combobox() {
		return Category_combobox;
	}





	// ----------------------------------- end of getters and setters -------------------------------------------//
	
	
	//listeners for AddBookGUI

	

	public void AddTextBookIdMouseListener(MouseListener e)
	{
		txtBookId.addMouseListener(e);
	}
	
	public void AddTextBookNameMouseListener(MouseListener e)
	{
		txtBookName.addMouseListener(e);
	}
	
	public void AddTextPriceMouseListener(MouseListener e)
	{
		Price.addMouseListener(e);
	}
	
	public void AddTextAuthoridMouseListener(MouseListener e)
	{
		txtBookAuthorid.addMouseListener(e);
	}
	
	public void AddTextAuthorNameMouseListener(MouseListener e)
	{
		txtBookAuthorname.addMouseListener(e);
	}
	
	public void AddbuttonApplyactionListener(ActionListener e)
	{
		ApplyButton.addActionListener(e);
	}
	
	public void addButtonCancelFromAddBookActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
}
