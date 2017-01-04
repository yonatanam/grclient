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
import javax.swing.JPanel;
import javax.swing.JScrollBar;

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
	private JLabel lblPrice;
	private JTextField Price;
	private JLabel PriceWarningLabel;
	private JLabel lblWillItBe;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JLabel lblSynopsis;
	private JLabel label;
	private JLabel label_1;
	private JTextArea synopsis_area;
	private JLabel label_2;
	private JPanel panel;
	private JLabel lblToc;
	private JTextArea TOC_area;
	private JTextArea keywords_area;
	
	public AddBookGUI() {
		
		
		this.setTitle("Adding new book - Initial Config");
		this.setBounds(500, 0, 800, 1014);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		
		
		
		panel = new JPanel();
		panel.setBounds(0, 0, 794, 984);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAddingABook = new JLabel("Adding new book");
		lblAddingABook.setForeground(Color.WHITE);
		lblAddingABook.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingABook.setBounds(31, 3, 203, 29);
		panel.add(lblAddingABook);
		
		txtBookId = new JTextField();
		txtBookId.setOpaque(false);   
		txtBookId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBookId.setForeground(Color.white);
		txtBookId.setText("exp: 911283");
		txtBookId.setBounds(146, 129, 110, 16);
		panel.add(txtBookId);
		txtBookId.setColumns(10);
		
		label = new JLabel("");
		Image firstimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label.setIcon(new ImageIcon(firstimgfield));
		label.setBounds(143, 122, 224, 32);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("Book id:*");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(10, 122, 80, 22);
		panel.add(lblNewLabel);
		
		JLabel lblFillInThe = new JLabel("Fill in all relevant data (stars means obligatory)");
		lblFillInThe.setForeground(Color.GRAY);
		lblFillInThe.setFont(new Font("Arial", Font.BOLD, 15));
		lblFillInThe.setBounds(10, 49, 334, 18);
		panel.add(lblFillInThe);
		
		JLabel lblBookName = new JLabel("Book name:*");
		lblBookName.setFont(new Font("Arial", Font.BOLD, 18));
		lblBookName.setForeground(Color.LIGHT_GRAY);
		lblBookName.setBounds(8, 193, 113, 22);
		panel.add(lblBookName);
		
		txtBookName = new JTextField();
		txtBookName.setOpaque(false);   
		txtBookName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtBookName.setForeground(Color.white);
		txtBookName.setText("exp: The way of rice");
		txtBookName.setBounds(146, 203, 134, 16);
		panel.add(txtBookName);
		txtBookName.setColumns(10);
		Image buttonapp = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		
		label_1 = new JLabel("");
		Image secondimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(secondimgfield));
		label_1.setBounds(143, 195, 224, 32);
		panel.add(label_1);
		
		BidWarningLabel = new JLabel("");
		BidWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		BidWarningLabel.setBounds(381, 123, 224, 22);
		panel.add(BidWarningLabel);
		
		BNameWarningLabel = new JLabel("");
		BNameWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		BNameWarningLabel.setBounds(381, 193, 224, 29);
		panel.add(BNameWarningLabel);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setForeground(Color.LIGHT_GRAY);
		lblLanguage.setFont(new Font("Arial", Font.BOLD, 18));
		lblLanguage.setBounds(8, 275, 93, 22);
		panel.add(lblLanguage);
		
		comboBox = new JComboBox(langs);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox.setForeground(Color.LIGHT_GRAY);
		comboBox.setBounds(135, 275, 110, 24);
		panel.add(comboBox);
		
		
		lblSynopsis = new JLabel("Synopsis:");
		lblSynopsis.setForeground(Color.LIGHT_GRAY);
		lblSynopsis.setFont(new Font("Arial", Font.BOLD, 18));
		lblSynopsis.setBounds(8, 342, 88, 22);
		panel.add(lblSynopsis);
		
		formats = new ButtonGroup();
		yesno = new ButtonGroup();
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		
		PriceWarningLabel = new JLabel("");
		PriceWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		PriceWarningLabel.setBounds(386, 130, 0, 0);
		panel.add(PriceWarningLabel);
		
		JScrollPane sc = new JScrollPane();
		sc.setLocation(135, 342);
		sc.setSize(243, 45);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(sc);
		
		synopsis_area = new JTextArea();
		synopsis_area.setLineWrap(true);
		synopsis_area.setWrapStyleWord(true);
		sc.setViewportView(synopsis_area);
		
		
		Image imgbg = new ImageIcon(this.getClass().getResource("/addbooklogo.jpg")).getImage();
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(0, 901, 131, 31);
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
		ApplyButton.setBounds(143, 901, 131, 31);
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
		lblWillItBe.setBounds(10, 570, 273, 22);
		panel.add(lblWillItBe);
		lblWillItBe.setForeground(Color.LIGHT_GRAY);
		lblWillItBe.setFont(new Font("Arial", Font.BOLD, 18));
		
		rdbtnNo = new JRadioButton("NO");
		rdbtnNo.setBounds(8, 627, 51, 27);
		panel.add(rdbtnNo);
		rdbtnNo.setForeground(Color.LIGHT_GRAY);
		rdbtnNo.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnNo.setBackground(Color.BLACK);
		
		yesno.add(rdbtnNo);
		
		rdbtnYes = new JRadioButton("YES");
		rdbtnYes.setBounds(128, 627, 59, 27);
		panel.add(rdbtnYes);
		rdbtnYes.setSelected(true);
		rdbtnYes.setForeground(Color.LIGHT_GRAY);
		rdbtnYes.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnYes.setBackground(Color.BLACK);
		yesno.add(rdbtnYes);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(7, 820, 52, 22);
		panel.add(lblPrice);
		lblPrice.setForeground(Color.LIGHT_GRAY);
		lblPrice.setFont(new Font("Arial", Font.BOLD, 18));
		
		Price = new JTextField();
		Price.setBounds(151, 824, 116, 22);
		Price.setOpaque(false);   
		Price.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		Price.setForeground(Color.white);	
		panel.add(Price);
		Price.setText("0");
		Price.setColumns(10);
		
		JLabel lblFormat = new JLabel("Format:");
		lblFormat.setBounds(12, 695, 69, 22);
		panel.add(lblFormat);
		lblFormat.setForeground(Color.LIGHT_GRAY);
		lblFormat.setFont(new Font("Arial", Font.BOLD, 18));
		
				rdbtnDoc = new JRadioButton("DOC");
				rdbtnDoc.setBounds(10, 744, 63, 27);
				panel.add(rdbtnDoc);
				rdbtnDoc.setForeground(Color.LIGHT_GRAY);
				rdbtnDoc.setFont(new Font("Arial", Font.BOLD, 15));
				rdbtnDoc.setBackground(Color.BLACK);
				formats.add(rdbtnDoc);
				
				rdbtnFb = new JRadioButton("FB2");
				rdbtnFb.setBounds(130, 744, 57, 27);
				panel.add(rdbtnFb);
				rdbtnFb.setForeground(Color.LIGHT_GRAY);
				rdbtnFb.setFont(new Font("Arial", Font.BOLD, 15));
				rdbtnFb.setBackground(Color.BLACK);
				formats.add(rdbtnFb);
				
				rdbtnPdf = new JRadioButton("PDF");
				rdbtnPdf.setBounds(248, 744, 59, 27);
				panel.add(rdbtnPdf);
				rdbtnPdf.setFont(new Font("Arial", Font.BOLD, 15));
				rdbtnPdf.setForeground(Color.LIGHT_GRAY);
				rdbtnPdf.setBackground(Color.BLACK);
				rdbtnPdf.setSelected(true);
				
				// for radio buttons to work properly
				formats.add(rdbtnPdf);
				
				label_2 = new JLabel("");
				Image thirdimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
				label_2.setIcon(new ImageIcon(thirdimgfield));
				label_2.setBounds(146, 820, 224, 32);
				panel.add(label_2);
				
				JLabel lblKeywords = new JLabel("Keywords:");
				lblKeywords.setForeground(Color.LIGHT_GRAY);
				lblKeywords.setFont(new Font("Arial", Font.BOLD, 18));
				lblKeywords.setBounds(10, 417, 111, 22);
				panel.add(lblKeywords);
				
				lblToc = new JLabel("TOC:");
				lblToc.setForeground(Color.LIGHT_GRAY);
				lblToc.setFont(new Font("Arial", Font.BOLD, 18));
				lblToc.setBounds(10, 494, 111, 22);
				panel.add(lblToc);
				
				JScrollPane sc3 = new JScrollPane();
				sc3.setLocation(135, 494);
				sc3.setSize(243, 45);
	
				sc3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				panel.add(sc3);
				
				TOC_area = new JTextArea();
				TOC_area.setBounds(139, 495, 59, 22);
				sc3.setViewportView(TOC_area);
				
				JScrollPane sc2 = new JScrollPane();
				sc2.setLocation(135, 417);
				sc2.setSize(243, 46);
				sc2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				panel.add(sc2);
				
				keywords_area = new JTextArea();
				//panel.add(textArea_1);
				keywords_area.setBounds(135, 422, 243, 45);
				sc2.setViewportView(keywords_area);
				
				BackGround = new JLabel("");
				BackGround.setBounds(0, 0, 794, 984);
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


	public JLabel getPriceWarningLabel() {
		return PriceWarningLabel;
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
	
	public void AddbuttonApplyactionListener(ActionListener e)
	{
		ApplyButton.addActionListener(e);
	}
	
	public void addButtonCancelFromAddBookActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
}
