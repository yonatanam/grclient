package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.text.MaskFormatter;
import models.AdvancedDocument;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
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
		
	public SettlePaymentGUI()
	{		
		
		Font errorFont = new Font("Tahoma", Font.PLAIN, 12);
		
		lblSettlePayment = new JLabel("Settle payment");
		lblSettlePayment.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSettlePayment.setBounds(148, 22, 127, 28);
		getContentPane().add(lblSettlePayment);
				
		lblCardNumber = new JLabel("Card number:");
		lblCardNumber.setBounds(31, 86, 80, 14);
		getContentPane().add(lblCardNumber);
		
		tfCardNumber = new JTextField();
		tfCardNumber.setBounds(148, 83, 99, 20);
		getContentPane().add(tfCardNumber);
		tfCardNumber.setColumns(10);
		tfCardNumber.setDocument(new AdvancedDocument(MAX_LEN_CARD_NUMBER,AdvancedDocument.ONLY_NUMBERS));
			
		lblErrorCardNum = new JLabel();
		lblErrorCardNum.setFont(errorFont);
		lblErrorCardNum.setForeground(Color.red);	
		lblErrorCardNum.setBounds(257, 86, 153, 14);
		getContentPane().add(lblErrorCardNum);	
		
		lblSecurityCode = new JLabel("Security code:");
		lblSecurityCode.setBounds(31, 131, 92, 14);
		getContentPane().add(lblSecurityCode);
		
		tfSecurityCode = new JTextField();
		tfSecurityCode.setBounds(148, 128, 99, 20);
		getContentPane().add(tfSecurityCode);
		tfSecurityCode.setColumns(10);
		tfSecurityCode.setDocument(new AdvancedDocument(MAX_LEN_SECURITY_NUMBER,AdvancedDocument.ONLY_NUMBERS));
			
		lblErrorSecurity = new JLabel();
		lblErrorSecurity.setFont(errorFont);
		lblErrorSecurity.setForeground(Color.red);		
		lblErrorSecurity.setBounds(257, 131, 153, 14);
		getContentPane().add(lblErrorSecurity);	
		
		lblExpirationDate = new JLabel("Expiration date:");
		lblExpirationDate.setBounds(32, 176, 91, 14);
		getContentPane().add(lblExpirationDate);

		cbMonth = new JComboBox<String>();
		cbMonth.setBounds(148, 173, 39, 20);
		getContentPane().add(cbMonth);
		for(int i = 1; i <= 12; i++)
			cbMonth.addItem(String.valueOf(i));
		cbMonth.setSelectedIndex(0);
		
		cbYear = new JComboBox<String>();
		cbYear.setBounds(197, 173, 50, 20);
		getContentPane().add(cbYear);		
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		for(int i = year; i <= year + 4; i++) //five years ahead
			cbYear.addItem(String.valueOf(i));		
		cbYear.setSelectedIndex(0);
		
		lblErrorExpiration = new JLabel();
		lblErrorExpiration.setFont(errorFont);
		lblErrorExpiration.setForeground(Color.red);			
		lblErrorExpiration.setBounds(257, 176, 153, 14);
		getContentPane().add(lblErrorExpiration);			
		
		lblSubscriptionType = new JLabel("Subscription type:");
		lblSubscriptionType.setBounds(31, 226, 107, 14);
		getContentPane().add(lblSubscriptionType);
		
		cbSubscriptionName = new JComboBox<String>(); //????????? sql to get types
		cbSubscriptionName.setBounds(148, 223, 99, 20);
		getContentPane().add(cbSubscriptionName);
		
	    jtaSubscriptionDes = new JTextArea();
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
	    jspSubscriptionDes.setBounds(20, 271, 462, 140);
	    //jspSubscriptionDes.setVisible(false);	    
	    
	    /** End of properties of jspReviewContent*/		
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(570, 538, 89, 23);
		getContentPane().add(btnSubmit);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(694, 538, 89, 23);
		getContentPane().add(btnBack);		
		
		/**----------------------------Properties of JFrame----------------------------*/ 
		setTitle("Settle Payment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800,600);
		setResizable(false);
		setLocationRelativeTo(null);		
		getContentPane().setLayout(null);			
		//setVisible(true); should be after loading of subscription types!!!!
		/**----------------------------End of Properties of JFrame----------------------------*/ 
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
	
	public String getSubscriptioType()
	{
		return"aaa";
		//return String.valueOf(cbSubscriptionName.getSelectedIndex());
	}	
		
	public void addCbSubscriptionTypeActionListener(ActionListener listener)
	{
		cbSubscriptionName.addActionListener(listener);
	}	
		
	public void addSubmitActionListener(ActionListener listener)
	{
		btnSubmit.addActionListener(listener);
	}	
		
	public void addBtnBackActionListener(ActionListener e)
	{
		btnBack.addActionListener(e);
	}	
}