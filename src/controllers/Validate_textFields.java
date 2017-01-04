package controllers;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Validate_textFields{
	
	
	public Validate_textFields() {
				
	}
	
	public boolean Check_text_onlyNumbers(JTextField Jtextfield, JLabel warning_label)
	{
		boolean flag = false;
		
		if(!Jtextfield.getText().matches("[0-9]+"))
		{
			warning_label.setText("Wrong input! only numbers");
			flag = false;
		}
		else
			flag = true;
		
		return flag;
	}
	
	public boolean Check_text_onlyNumbers(JTextField Jtextfield)
	{
		boolean flag = false;
		
		if(!Jtextfield.getText().matches("[0-9]+"))
			flag = false;
		else
			flag = true;
		
		return flag;
	}
	
	public boolean Check_text_onlyChars(JTextField Jtextfield, JLabel warning_label)
	{
		boolean flag = false;
		
		if(!Jtextfield.getText().matches("[a-zA-Z]+"))
		{
			warning_label.setText("Wrong input! only chars");
			flag = false;
		}
		else
			flag = true;
		
		return flag;
	}
	
	public boolean Check_text_onlyChars(JTextField Jtextfield)
	{
		boolean flag = false;
		
		if(!Jtextfield.getText().matches("[a-zA-Z]+"))
			flag = false;
		else
			flag = true;
		
		return flag;
	}
	
	
	public boolean Check_text_empty(JTextField Jtextfield, JLabel warning_label, String field)
	{
		boolean flag = false;
		
		if(Jtextfield.getText().equals(""))
		{
			warning_label.setText("You must fill " + field);
			flag = true;
		}
		else
			flag = false;
		
		return flag;
	}
	
	public boolean Check_text_empty(JTextField Jtextfield)
	{
		boolean flag = false;
		
		if(Jtextfield.getText().equals(""))
			flag = true;
		else
			flag = false;
		
		return flag;
	}
	
}
