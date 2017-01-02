package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import models.AdvancedDocument;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class SettlePaymentGUI extends  JPanel
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
	//private JFormattedTextField tfCardNumber;
	private JTextField tfSecurityCode;
	//private JTextField tfExpirationDate;
	
	
	private JComboBox<String> cbMonth;
	private JComboBox<String> cbYear;
	private JComboBox<String> cbSubscriptionName;
	
	private JButton btnSubmit;
	
	JFormattedTextField zipField;
	
	private final int MAX_LEN_CARD_NUMBER = 6; 
	private final int MAX_LEN_SECURITY_NUMBER = 3; 
	
	
	public SettlePaymentGUI()
	{		
		
		Font errorFont = new Font("Tahoma", Font.PLAIN, 12);
		
		lblSettlePayment = new JLabel("Settle payment");
		lblSettlePayment.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSettlePayment.setBounds(148, 22, 127, 28);
		add(lblSettlePayment);
				
		lblCardNumber = new JLabel("Card number:");
		lblCardNumber.setBounds(31, 86, 80, 14);
		add(lblCardNumber);
		
		
		
		//tfCardNumber = new JFormattedTextField(new MyMaskFormat("##-##-##").getMaskForamt());
		tfCardNumber = new JTextField();
		tfCardNumber.setBounds(148, 83, 99, 20);
		add(tfCardNumber);
		tfCardNumber.setColumns(10);
		tfCardNumber.setDocument(new AdvancedDocument(MAX_LEN_CARD_NUMBER,AdvancedDocument.ONLY_NUMBERS));
			
		
		//SimpleDateFormat format = new SimpleDateFormat("MM-YY");
		//MaskFormatter m = new MaskFormatter("##-##");
		//zipField = new JFormattedTextField(format);
		//zipField.setFormatterFactory(new DefaultFormatterFactory(m));
		
		
	
		
		//zipField.setText("12-12");
		
		//zipField.setBounds(148, 83, 86, 20);
		//add(zipField);
		
	
		//zipField.setColumns(10);

		
		lblErrorCardNum = new JLabel();
		lblErrorCardNum.setFont(errorFont);
		lblErrorCardNum.setForeground(Color.red);	
		lblErrorCardNum.setBounds(257, 86, 153, 14);
		add(lblErrorCardNum);	
		
		lblSecurityCode = new JLabel("Security code:");
		lblSecurityCode.setBounds(31, 131, 92, 14);
		add(lblSecurityCode);
		
		tfSecurityCode = new JTextField();
		tfSecurityCode.setBounds(148, 128, 99, 20);
		add(tfSecurityCode);
		tfSecurityCode.setColumns(10);
		tfSecurityCode.setDocument(new AdvancedDocument(MAX_LEN_SECURITY_NUMBER,AdvancedDocument.ONLY_NUMBERS));
			
		lblErrorSecurity = new JLabel();
		lblErrorSecurity.setFont(errorFont);
		lblErrorSecurity.setForeground(Color.red);		
		lblErrorSecurity.setBounds(257, 131, 153, 14);
		add(lblErrorSecurity);	
		
		lblExpirationDate = new JLabel("Expiration date:");
		lblExpirationDate.setBounds(32, 176, 91, 14);
		add(lblExpirationDate);
		/*
		
		tfExpirationDate = new JTextField();
		tfExpirationDate.setBounds(148, 173, 86, 20);
		add(tfExpirationDate);
		tfExpirationDate.setColumns(10);
		*/
		cbMonth = new JComboBox<String>();
		cbMonth.setBounds(148, 173, 39, 20);
		add(cbMonth);
		for(int i = 1; i <= 12; i++)
			cbMonth.addItem(String.valueOf(i));
		cbMonth.setSelectedIndex(0);
		
		cbYear = new JComboBox<String>();
		cbYear.setBounds(197, 173, 50, 20);
		add(cbYear);		
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		for(int i = year; i <= year + 4; i++) //five years ahead
			cbYear.addItem(String.valueOf(i));		
		cbYear.setSelectedIndex(0);
		
		lblErrorExpiration = new JLabel();
		lblErrorExpiration.setFont(errorFont);
		lblErrorExpiration.setForeground(Color.red);			
		lblErrorExpiration.setBounds(257, 176, 153, 14);
		add(lblErrorExpiration);			
		
		lblSubscriptionType = new JLabel("Subscription type:");
		lblSubscriptionType.setBounds(31, 226, 107, 14);
		add(lblSubscriptionType);
		
		cbSubscriptionName = new JComboBox<String>(); //????????? sql to get types
		cbSubscriptionName.setBounds(148, 223, 99, 20);
		add(cbSubscriptionName);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(351, 256, 89, 23);
		add(btnSubmit);
				
		// JPanel properties
		//setSize(400,438);	
		setLayout(null);			
		setVisible(true);
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
	/*
	public String getExpirationDate()
	{
		return "asdasd";//tfExpirationDate.getText();
	}*/
	
	public String getSubscriptioType()
	{
		return String.valueOf(cbSubscriptionName.getSelectedIndex());
	}			
	
	public void AddItemToCbSubscriptionName(String item)
	{
		cbSubscriptionName.addItem(item);
	}
	
	public void addCbSubscriptionTypeActionListener(ActionListener listener){
		//btnSubmit.addActionListener(listener);
	}	
		
	public void addSubmitActionListener(ActionListener listener){
		btnSubmit.addActionListener(listener);
	}	
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
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
}