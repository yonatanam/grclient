package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.text.MaskFormatter;

import client.App;
import models.AdvancedDocument;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SettlePaymentGUI extends JFrame
{
	private JLabel lblSettlePayment;
	private JLabel lblCardNumber;
	private JLabel lblSecurityCode;
	private JLabel lblExpirationDate;
	private JLabel lblSubscriptionType; 
	
	private JLabel lblErrorCardNum;
	private JLabel lblErrorSecurity;
	private JLabel lblErrorExpiration;
	private JLabel miniLogo;
	private JLabel BackGround;
		
	private JTextField tfCardNumber;
	private JTextField tfSecurityCode;
	
	private String[] subscriptioDes;
	
	private JComboBox<String> cbMonth;
	private JComboBox<String> cbYear;
	private JComboBox<String> cbSubscriptionName;
	
	private JButton btnSubmit;
	private JButton btnBack;
	
	private JTextArea jtaSubscriptionDes;
	
	private JScrollPane jspSubscriptionDes;
	
	private final int MAX_LEN_CARD_NUMBER = 6; 
	private final int MAX_LEN_SECURITY_NUMBER = 3; 
	private UserMenu userMenu;
	public SettlePaymentGUI()
	{		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		Font errorFont = new Font("Tahoma", Font.PLAIN, 12);
		
		lblSettlePayment = new JLabel("Settle payment");
		lblSettlePayment.setForeground(Color.WHITE);
		lblSettlePayment.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
		lblSettlePayment.setBounds(139, 35, 214, 48);
		getContentPane().add(lblSettlePayment);
		
		
				
		lblCardNumber = new JLabel("Card number:");
		lblCardNumber.setForeground(Color.WHITE);
		lblCardNumber.setBounds(139, 109, 80, 14);
		getContentPane().add(lblCardNumber);
		
		tfCardNumber = new JTextField();
		tfCardNumber.setBounds(256, 106, 99, 20);
		getContentPane().add(tfCardNumber);
		tfCardNumber.setColumns(10);
		tfCardNumber.setDocument(new AdvancedDocument(MAX_LEN_CARD_NUMBER,AdvancedDocument.ONLY_NUMBERS));
			
		lblErrorCardNum = new JLabel();
		lblErrorCardNum.setFont(errorFont);
		lblErrorCardNum.setForeground(Color.red);	
		lblErrorCardNum.setBounds(365, 109, 153, 14);
		getContentPane().add(lblErrorCardNum);	
		
		lblSecurityCode = new JLabel("Security code:");
		lblSecurityCode.setForeground(Color.WHITE);
		lblSecurityCode.setBounds(139, 154, 92, 14);
		getContentPane().add(lblSecurityCode);
		
		tfSecurityCode = new JTextField();
		tfSecurityCode.setBounds(256, 151, 99, 20);
		getContentPane().add(tfSecurityCode);
		tfSecurityCode.setColumns(10);
		tfSecurityCode.setDocument(new AdvancedDocument(MAX_LEN_SECURITY_NUMBER,AdvancedDocument.ONLY_NUMBERS));
			
		lblErrorSecurity = new JLabel();
		lblErrorSecurity.setFont(errorFont);
		lblErrorSecurity.setForeground(Color.red);		
		lblErrorSecurity.setBounds(365, 154, 153, 14);
		getContentPane().add(lblErrorSecurity);	
		
		lblExpirationDate = new JLabel("Expiration date:");
		lblExpirationDate.setForeground(Color.WHITE);
		lblExpirationDate.setBounds(140, 199, 91, 14);
		getContentPane().add(lblExpirationDate);

		cbMonth = new JComboBox<String>();
		cbMonth.setBounds(256, 196, 39, 20);
		getContentPane().add(cbMonth);
		for(int i = 1; i <= 12; i++)
			cbMonth.addItem(String.valueOf(i));
		cbMonth.setSelectedIndex(0);
		
		cbYear = new JComboBox<String>();
		cbYear.setBounds(305, 196, 50, 20);
		getContentPane().add(cbYear);		
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		for(int i = year; i <= year + 4; i++) //five years ahead
			cbYear.addItem(String.valueOf(i));		
		cbYear.setSelectedIndex(0);
		
		lblErrorExpiration = new JLabel();
		lblErrorExpiration.setFont(errorFont);
		lblErrorExpiration.setForeground(Color.red);			
		lblErrorExpiration.setBounds(365, 199, 153, 14);
		getContentPane().add(lblErrorExpiration);			
		
		lblSubscriptionType = new JLabel("Subscription type:");
		lblSubscriptionType.setForeground(Color.WHITE);
		lblSubscriptionType.setBounds(139, 249, 107, 14);
		getContentPane().add(lblSubscriptionType);
		
		cbSubscriptionName = new JComboBox<String>(); //????????? sql to get types
		cbSubscriptionName.setBounds(256, 246, 99, 20);
		getContentPane().add(cbSubscriptionName);
		
	    jtaSubscriptionDes = new JTextArea();
	    jtaSubscriptionDes.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    jtaSubscriptionDes.setBounds(24, 285, 300, 126);
	    jtaSubscriptionDes.setLineWrap(true);
	    jtaSubscriptionDes.setWrapStyleWord(true);
	    jtaSubscriptionDes.setOpaque(false);
	    jtaSubscriptionDes.setEditable(false);
	    jtaSubscriptionDes.setBorder(null);
	    
	    /** Properties of jspReviewContent **/
	    jspSubscriptionDes = new JScrollPane (jtaSubscriptionDes);
	    jspSubscriptionDes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    jspSubscriptionDes.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createCompoundBorder(
                                    BorderFactory.createTitledBorder("Subscription description"),
                                    BorderFactory.createEmptyBorder(5,5,5,5)), null));
	    getContentPane().add(jspSubscriptionDes);
	    jspSubscriptionDes.setBounds(128, 294, 462, 140);
	    //jspSubscriptionDes.setVisible(false);	    
	    
	    /** End of properties of jspReviewContent*/		
		
		
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(483, 513, 107, 31);
		getContentPane().add(btnSubmit);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 16));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setIcon(new ImageIcon(buttoncan));
		btnSubmit.setHorizontalTextPosition(JButton.CENTER);
		btnSubmit.setVerticalTextPosition(JButton.CENTER);
		btnSubmit.setOpaque(false);
		btnSubmit.setContentAreaFilled(false);
		btnSubmit.setBorderPainted(false);
		
		
		btnBack = new JButton("Back");
		btnBack.setBounds(107, 517, 112, 22);
		getContentPane().add(btnBack);
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setForeground(Color.WHITE);
		btnBack.setIcon(new ImageIcon(buttoncan));
		btnBack.setHorizontalTextPosition(JButton.CENTER);
		btnBack.setVerticalTextPosition(JButton.CENTER);
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
			
		
		/**----------------------------Properties of JFrame----------------------------*/ 
		setTitle("Settle Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		setLocationRelativeTo(null);		
		getContentPane().setLayout(null);			
		//setVisible(true); should be after loading of subscription types!!!!
		/**----------------------------End of Properties of JFrame----------------------------*/ 
		
		
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

	public void viewSubscriptionDes()
	{	
		jtaSubscriptionDes.setText(subscriptioDes[cbSubscriptionName.getSelectedIndex()]);
	}
	
	public void addItemToCbSubscriptionName(String item)
	{
		cbSubscriptionName.addItem(item);
	}
	
	public void setSubscriptionDesc(String[] desc)
	{
		subscriptioDes = desc;
	}	
	
	public void setLblErrorCardNum(String msg)
	{
		lblErrorCardNum.setText(msg);
	}

	public void setLblErrorSecurity(String msg)
	{
		lblErrorSecurity.setText(msg);
	}
	
	public void setLblErrorExpiration(String msg)
	{
		lblErrorExpiration.setText(msg);
	}	

	public String getCardNumber()
	{
		return tfCardNumber.getText();
	}
	
	public String getSecurityCode()
	{
		return tfSecurityCode.getText();
	}

	public int getMaxlenCardNumber()
	{
		return MAX_LEN_CARD_NUMBER;
	}
	
	public int getMaxLenSecuritydNumber()
	{
		return MAX_LEN_SECURITY_NUMBER;
	}
	
	public int getMonth()
	{
		return Integer.valueOf((String)cbMonth.getSelectedItem());
	}
	
	public int getYear()
	{
		return Integer.valueOf((String)cbYear.getSelectedItem());
	}
	
	public int getSubscriptioTypeIndex()
	{
		return cbSubscriptionName.getSelectedIndex();
	}	
		
	public void addCbSubscriptionTypeActionListener(ActionListener listener)
	{
		cbSubscriptionName.addActionListener(listener);
	}	
		
	public void addSubmitActionListener(ActionListener listener)
	{
		btnSubmit.addActionListener(listener);
	}	
		
	public void addBtnBackActionListener(ActionListener listener)
	{
		btnBack.addActionListener(listener);
	}	
}