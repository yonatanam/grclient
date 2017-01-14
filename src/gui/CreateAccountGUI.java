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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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

public class CreateAccountGUI extends JFrame {
	
	private JTextField txtUsername;
	private JTextField Password;
	private JTextField firstName;
	private JTextField Email;
	private JTextField lastName;
	private JButton ApplyButton;
	private JLabel BackGround;
	private JLabel username_Warning;
	private JLabel password_Warning;
	private JLabel email_Warning;
	private JLabel firstName_Warning;
	private JLabel lastName_Warning;
	private JLabel miniLogo;
	private JButton btnCancel;
	private JLabel labelUsername;
	private JLabel labelPassword;
	private JPanel panel;
	private UserMenu userMenu;

	private JTextField nullField;
	
	public CreateAccountGUI() {
		
		
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
		
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		panel.add(menu);	
		/** End menu */
		
		
		nullField = new JTextField();
		nullField.setOpaque(false);     
		panel.add(nullField);
		
		JLabel lblAddingABook = new JLabel("Create Account");
		lblAddingABook.setForeground(Color.WHITE);
		lblAddingABook.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 26));
		lblAddingABook.setBounds(268, 67, 254, 29);
		panel.add(lblAddingABook);
		
		
		
		//Passowrd Field JText
		txtUsername = new JTextField()
		{
				  @Override public void setBorder(Border border) {   //Disable field's border
				      // No!
				  }
		};
		txtUsername.setForeground(Color.WHITE);
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 13));
		txtUsername.setOpaque(false);       //Make Field transparent 
		txtUsername.setText("Enter username");
		
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtUsername.setText("");                   //Clear "Password String" on focus
			}
		});
		txtUsername.setToolTipText("");
		txtUsername.setColumns(10);
		txtUsername.setBounds(271, 166, 207, 27);
		panel.add(txtUsername);
		//Password END
		
		labelUsername = new JLabel("");
		Image firstimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		labelUsername.setIcon(new ImageIcon(firstimgfield));
		labelUsername.setBounds(268, 161, 224, 32);
		panel.add(labelUsername);
		
		
		
		
		//Passowrd Field JText
		firstName = new JTextField()
		{
				  @Override public void setBorder(Border border) {   //Disable field's border
				      // No!
				  }
		};
		firstName.setForeground(Color.WHITE);
		firstName.setFont(new Font("Arial", Font.PLAIN, 13));
		firstName.setOpaque(false);       //Make Field transparent 
		firstName.setText("Enter your first name");
		firstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				firstName.setText("");                   //Clear "Password String" on focus
			}
		});
		firstName.setToolTipText("");
		firstName.setColumns(10);
		firstName.setBounds(271, 286, 212, 27);
		panel.add(firstName);
		//Password END
		
		
		JLabel labelFirstName = new JLabel();
		labelFirstName.setBounds(268, 281, 224, 39);
		Image img2 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		labelFirstName.setIcon(new ImageIcon(img2));
		panel.add(labelFirstName);
		//Username and Password Labels END
		
		
		
		
		
		lastName = new JTextField()
		{
				  @Override public void setBorder(Border border) {   //Disable field's border
				      // No!
				  }
		};
		lastName.setForeground(Color.WHITE);
		lastName.setFont(new Font("Arial", Font.PLAIN, 13));
		lastName.setOpaque(false);       //Make Field transparent 
		lastName.setText("Enter your last name");
		lastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				lastName.setText("");                   //Clear "Password String" on focus
			}
		});
		lastName.setToolTipText("");
		lastName.setColumns(10);
		lastName.setBounds(271, 346, 212, 27);
		panel.add(lastName);
		//Password END
		
		
		JLabel labelLastName = new JLabel();
		labelLastName.setBounds(268, 341, 224, 39);
		labelLastName.setIcon(new ImageIcon(img2));
		panel.add(labelLastName);
		//Username and Password Labels END
		
		
		
		
		Email = new JTextField()
		{
				  @Override public void setBorder(Border border) {   //Disable field's border
				      // No!
				  }
		};
		Email.setForeground(Color.WHITE);
		Email.setFont(new Font("Arial", Font.PLAIN, 13));
		Email.setOpaque(false);       //Make Field transparent 
		Email.setText("Enter your email address");
		Email.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Email.setText("");                   //Clear "Password String" on focus
			}
		});
		Email.setToolTipText("");
		Email.setColumns(10);
		Email.setBounds(271, 406, 212, 27);
		panel.add(Email);
		//Password END
		
		
		JLabel labelEmail = new JLabel();
		labelEmail.setBounds(268, 401, 224, 39);
		labelEmail.setIcon(new ImageIcon(img2));
		panel.add(labelEmail);
		//Username and Password Labels END
		
		
		
		
		
		JLabel lblFillInThe = new JLabel("You must fill all relevant fields");
		lblFillInThe.setForeground(Color.GRAY);
		lblFillInThe.setFont(new Font("Arial", Font.BOLD, 15));
		lblFillInThe.setBounds(270, 111, 240, 26);
		panel.add(lblFillInThe);
		
		
		
		
		
		Password = new JTextField()
		{
				  @Override public void setBorder(Border border) {   //Disable field's border
				      // No!
				  }
		};
		Password.setForeground(Color.WHITE);
		Password.setFont(new Font("Arial", Font.PLAIN, 13));
		Password.setOpaque(false);       //Make Field transparent 
		Password.setText("Choose password");
		Password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Password.setText("");                   //Clear "Password String" on focus
			}
		});
		Password.setToolTipText("");
		Password.setColumns(10);
		Password.setBounds(271, 226, 212, 27);
		panel.add(Password);
		//Password END
		
		
		labelPassword = new JLabel("");
		Image secondimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		labelPassword.setIcon(new ImageIcon(secondimgfield));
		labelPassword.setBounds(268, 221, 224, 32);
		panel.add(labelPassword);
	
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		
		
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(251, 514, 131, 31);
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
		ApplyButton.setBounds(379, 514, 131, 31);
		panel.add(ApplyButton);
		ApplyButton.setFont(new Font("Arial", Font.BOLD, 16));
		ApplyButton.setForeground(Color.WHITE);
		ApplyButton.setIcon(new ImageIcon(buttoncan));
		ApplyButton.setHorizontalTextPosition(JButton.CENTER);
		ApplyButton.setVerticalTextPosition(JButton.CENTER);
		ApplyButton.setOpaque(false);
		ApplyButton.setContentAreaFilled(false);
		ApplyButton.setBorderPainted(false);
				Image thirdimgfield = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
				
			
				
				
				
				
				username_Warning = new JLabel("");
				username_Warning.setBounds(528, 166, 213, 22);
				panel.add(username_Warning);
				username_Warning.setFont(new Font("Arial", Font.BOLD, 15));
				username_Warning.setForeground(Color.RED);
				username_Warning.setVisible(true);
				
				
				password_Warning = new JLabel("");
				password_Warning.setBounds(528, 226, 213, 22);
				panel.add(password_Warning);
				password_Warning.setFont(new Font("Arial", Font.BOLD, 15));
				password_Warning.setForeground(Color.RED);
				
				
				email_Warning = new JLabel("");
				email_Warning.setBounds(528, 409, 213, 22);
				panel.add(email_Warning);
				email_Warning.setFont(new Font("Arial", Font.BOLD, 15));
				email_Warning.setForeground(Color.RED);
				
				firstName_Warning = new JLabel("");
				firstName_Warning.setBounds(528, 288, 213, 22);
				panel.add(firstName_Warning);
				firstName_Warning.setFont(new Font("Arial", Font.BOLD, 15));
				firstName_Warning.setForeground(Color.RED);
				
				
				lastName_Warning = new JLabel("");
				lastName_Warning.setBounds(528, 351, 213, 22);
				panel.add(lastName_Warning);
				lastName_Warning.setFont(new Font("Arial", Font.BOLD, 15));
				lastName_Warning.setForeground(Color.RED);
				
				
				miniLogo = new JLabel("");
				miniLogo.setBounds(680, 13, 93, 55);
				Image miniLogoImage = new ImageIcon(this.getClass().getResource("/miniLogo.png")).getImage();
				panel.add(miniLogo);
				miniLogo.setIcon(new ImageIcon(miniLogoImage));
				
				
				
				BackGround = new JLabel("");
				BackGround.setBounds(0, 0, 794, 566);
				panel.add(BackGround);
				BackGround.setIcon(new ImageIcon(imgbg));
				
				
			
		
		
		
		this.setVisible(true);
		
	}
	
	
	// ------------------ Getters and Setters for all components ---------------------------------//
	

	public JLabel password_Warning() {
		return password_Warning;
	}

    public JLabel username_Warning() {
    	
    	return username_Warning;
    }
    
    public JLabel firstName_Warning() {
    	
    	return firstName_Warning;
    }
    
    public JLabel lastName_Warning() {
    	
    	return lastName_Warning;
    }
    
    public JLabel email_Warning() {
    	
    	return email_Warning;
    }
    
    public JTextField getEmailAddressText()
   	{
   		return Email;
   	}
    
    public JTextField getFirstNameText()
	{
		return firstName;
	}
    
    public JTextField getLastNameText()
   	{
   		return lastName;
   	}
    
	public JTextField getPasswordText()
	{
		return Password;
	}
	
	public JTextField getUsernameText()
	{		
		return txtUsername;
	}
	


	
	// ----------------------------------- end of getters and setters -------------------------------------------//
	
	
	//listeners for AddBookGUI
	







	public void txtUsernameMouseListener(MouseListener e)
	{
		txtUsername.addMouseListener(e);
	}
	
	
	public void AddbuttonApplyactionListener(ActionListener e)
	{
		ApplyButton.addActionListener(e);
	}
	
	public void addButtonCancelActionListener(ActionListener e)
	{
		btnCancel.addActionListener(e);
	}
}
