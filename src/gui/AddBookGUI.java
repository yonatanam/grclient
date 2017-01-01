package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Image;
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
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class AddBookGUI extends JFrame {
	
	private JTextField txtBookId;
	private JTextField txtBookName;
	private JButton ApplyButton;
	private JLabel BNameWarningLabel;
	private JLabel BidWarningLabel;
	private JLabel BackGround;
	private String[] langs = {"Hebrew", "English", "Arabic", "Albanian", "Franch", "Pakistanian"};
	private ButtonGroup formats;
	private JButton btnCancel;
	private JComboBox comboBox;
	private JRadioButton rdbtnPdf;
	private JRadioButton rdbtnDoc;
	private JRadioButton rdbtnFb;
	private JLabel lblPrice;
	private JTextField Price;
	private JLabel PriceWarningLabel;
	
	


	public AddBookGUI() {
		getContentPane().setLayout(null);
		Image imgbg = new ImageIcon(this.getClass().getResource("/addbooklogo.jpg")).getImage();
		
		JLabel lblAddingABook = new JLabel("Adding new book");
		lblAddingABook.setForeground(Color.WHITE);
		lblAddingABook.setFont(new Font("Arial", Font.BOLD, 24));
		lblAddingABook.setBounds(12, 13, 257, 37);
		getContentPane().add(lblAddingABook);
		
		txtBookId = new JTextField();
		
		txtBookId.setText("exp: 911283");
		txtBookId.setBounds(153, 100, 153, 22);
		getContentPane().add(txtBookId);
		txtBookId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Book id:*");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(12, 102, 88, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFillInThe = new JLabel("Fill all relevan data (stars means obligatory)");
		lblFillInThe.setForeground(Color.GRAY);
		lblFillInThe.setFont(new Font("Arial", Font.BOLD, 15));
		lblFillInThe.setBounds(12, 63, 311, 16);
		getContentPane().add(lblFillInThe);
		
		JLabel lblBookName = new JLabel("Book name:*");
		lblBookName.setFont(new Font("Arial", Font.BOLD, 18));
		lblBookName.setForeground(Color.LIGHT_GRAY);
		lblBookName.setBounds(12, 169, 129, 16);
		getContentPane().add(lblBookName);
		
		txtBookName = new JTextField();
		txtBookName.setText("exp: The way of rice");
		txtBookName.setBounds(153, 168, 153, 22);
		getContentPane().add(txtBookName);
		txtBookName.setColumns(10);
		
		ApplyButton = new JButton("Apply");
		ApplyButton.setFont(new Font("Arial", Font.BOLD, 16));
		ApplyButton.setForeground(Color.WHITE);
		Image buttonapp = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		ApplyButton.setIcon(new ImageIcon(buttonapp));
		ApplyButton.setBounds(174, 597, 116, 37);
		ApplyButton.setHorizontalTextPosition(JButton.CENTER);
		ApplyButton.setVerticalTextPosition(JButton.CENTER);
		ApplyButton.setOpaque(false);
		ApplyButton.setContentAreaFilled(false);
		ApplyButton.setBorderPainted(false);
		getContentPane().add(ApplyButton);
		
		BidWarningLabel = new JLabel("");
		BidWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		BidWarningLabel.setBounds(318, 106, 239, 16);
		getContentPane().add(BidWarningLabel);
		
		BNameWarningLabel = new JLabel("");
		BNameWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		BNameWarningLabel.setBounds(318, 169, 237, 16);
		getContentPane().add(BNameWarningLabel);
		
		JLabel lblLanguage = new JLabel("Language:");
		lblLanguage.setForeground(Color.LIGHT_GRAY);
		lblLanguage.setFont(new Font("Arial", Font.BOLD, 18));
		lblLanguage.setBounds(12, 237, 102, 22);
		getContentPane().add(lblLanguage);
		
		comboBox = new JComboBox(langs);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Arial", Font.BOLD, 15));
		comboBox.setForeground(Color.LIGHT_GRAY);
		comboBox.setBounds(153, 238, 116, 22);
		getContentPane().add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane sc = new JScrollPane(textArea);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sc.setBounds(153, 300, 239, 64);
		getContentPane().add(sc);
		
		JLabel lblSummary = new JLabel("Summary:");
		lblSummary.setForeground(Color.LIGHT_GRAY);
		lblSummary.setFont(new Font("Arial", Font.BOLD, 18));
		lblSummary.setBounds(12, 303, 102, 22);
		getContentPane().add(lblSummary);
		
		JLabel lblFormat = new JLabel("Format:");
		lblFormat.setForeground(Color.LIGHT_GRAY);
		lblFormat.setFont(new Font("Arial", Font.BOLD, 18));
		lblFormat.setBounds(12, 402, 78, 22);
		getContentPane().add(lblFormat);
		
		formats = new ButtonGroup();
		
		rdbtnPdf = new JRadioButton("PDF");
		rdbtnPdf.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnPdf.setForeground(Color.LIGHT_GRAY);
		rdbtnPdf.setBackground(Color.BLACK);
		rdbtnPdf.setBounds(12, 445, 61, 25);
		rdbtnPdf.setSelected(true);
		getContentPane().add(rdbtnPdf);

		rdbtnDoc = new JRadioButton("DOC");
		rdbtnDoc.setForeground(Color.LIGHT_GRAY);
		rdbtnDoc.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnDoc.setBackground(Color.BLACK);
		rdbtnDoc.setBounds(103, 445, 72, 25);
		getContentPane().add(rdbtnDoc);
		
		rdbtnFb = new JRadioButton("FB2");
		rdbtnFb.setForeground(Color.LIGHT_GRAY);
		rdbtnFb.setFont(new Font("Arial", Font.BOLD, 15));
		rdbtnFb.setBackground(Color.BLACK);
		rdbtnFb.setBounds(197, 445, 61, 25);
		getContentPane().add(rdbtnFb);
		
		// for radio buttons to work properly
		formats.add(rdbtnPdf);
		formats.add(rdbtnDoc);
		formats.add(rdbtnFb);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setVerticalTextPosition(SwingConstants.CENTER);
		btnCancel.setOpaque(false);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancel.setForeground(Color.WHITE);
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		btnCancel.setIcon(new ImageIcon(buttoncan));
		btnCancel.setFont(new Font("Arial", Font.BOLD, 16));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBounds(12, 597, 116, 37);
		getContentPane().add(btnCancel);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setForeground(Color.LIGHT_GRAY);
		lblPrice.setFont(new Font("Arial", Font.BOLD, 18));
		lblPrice.setBounds(12, 503, 61, 22);
		getContentPane().add(lblPrice);
		
		Price = new JTextField();
		Price.setText("0");
		Price.setColumns(10);
		Price.setBounds(153, 504, 153, 22);
		getContentPane().add(Price);
		
		PriceWarningLabel = new JLabel("");
		PriceWarningLabel.setFont(new Font("Arial", Font.BOLD, 15));
		PriceWarningLabel.setBounds(318, 507, 239, 16);
		getContentPane().add(PriceWarningLabel);
		
		BackGround = new JLabel("");
		BackGround.setIcon(new ImageIcon(imgbg));
		BackGround.setBounds(0, 0, 851, 762);
		getContentPane().add(BackGround);
		
		
		this.setTitle("Adding new book - Initial Config");
		this.setBounds(400, 100, 857, 797);
		this.setVisible(true);
		this.setResizable(false);
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

	
	//listeners for AddBookGUI
	



	public void AddTextBookIdMouseListener(MouseListener e)
	{
		txtBookId.addMouseListener(e);
	}
	
	public void AddTextBookNameMouseListener(MouseListener e)
	{
		txtBookName.addMouseListener(e);
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
