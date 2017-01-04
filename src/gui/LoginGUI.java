package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userField;
	private JTextField nullField;
	private JTextField passwordField;
	private JTextField emailField;
	private JButton buttonCancel;
	private JButton buttonLogin;
	private JButton btnForgotPassword;
	private JButton buttonSend;
	private JLabel labelWarningMessage = null;
	private JLabel FieldEmail;
	private JButton buttonChangePassword;
	private JTextField txtSignIn;


	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(LoginGUI.class.getResource("/images/logo2.png")));
		setTitle("Good Reading - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		nullField = new JTextField();
		nullField.setOpaque(false);     
		contentPane.add(nullField);
		
	
		
		//Passowrd Field JText
		passwordField = new JTextField()
		{
				  @Override public void setBorder(Border border) {   //Disable field's border
				      // No!
				  }
		};
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 13));
		passwordField.setOpaque(false);       //Make Field transparent 
		passwordField.setText("Password");
		passwordField.setText("1234");//////////////////////////////////////////////////////////
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				passwordField.setText("");                   //Clear "Password String" on focus
			}
		});
		passwordField.setToolTipText("");
		passwordField.setColumns(10);
		passwordField.setBounds(295, 305, 224, 40);
		contentPane.add(passwordField);
		//Password END
		
		
		
		//Email Field 
		emailField = new JTextField()
		{
		    @Override public void setBorder(Border border) {   //Disable field's border
		        // No!
		    }
		};
		
		emailField.setForeground(Color.WHITE);
		emailField.setFont(new Font("Arial", Font.PLAIN, 13));
		emailField.setOpaque(false);       //Make Field transparent 
		emailField.setText("Enter your email");
		emailField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				emailField.setText("");                //Clear "Email String" on focus
			}
		});
		
		emailField.setToolTipText("");
		emailField.setColumns(10);
		emailField.setBounds(295, 429, 224, 39);
		contentPane.add(emailField);
		emailField.setVisible(false);
		//Email field JText END
		
		
		
		//Username field JText
		userField = new JTextField()
		{
		    @Override public void setBorder(Border border) {   //Disable field's border
		        // No!
		    }
		};
		
		userField.setForeground(Color.WHITE);
		userField.setFont(new Font("Arial", Font.PLAIN, 13));
		userField.setOpaque(false);       //Make Field transparent 
		userField.setText("Username");
		userField.setText("adi");/////////////////////////////////////////////////////////////////////////
		userField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				userField.setText("");                //Clear "Username String" on focus
			}
		});
		
		userField.setToolTipText("");
		userField.setColumns(10);
		userField.setBounds(295, 266, 224, 39);
		contentPane.add(userField);
		//Username field JText END
		
				
		//Username and Password Lables		
		JLabel FieldUser = new JLabel();
		FieldUser.setBounds(292, 266, 229, 40);
		Image img1 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		FieldUser.setIcon(new ImageIcon(img1));
		contentPane.add(FieldUser);
		
		JLabel FieldPass = new JLabel();
		FieldPass.setBounds(292, 305, 229, 40);
		Image img2 = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		FieldPass.setIcon(new ImageIcon(img1));
		contentPane.add(FieldPass);
		//Username and Password Labels END
		
		//Email Label
		FieldEmail = new JLabel();
		FieldEmail.setBounds(290, 428, 229, 40);
		FieldEmail.setIcon(new ImageIcon(img1));
		contentPane.add(FieldEmail);
		FieldEmail.setVisible(false);
		//Email Label END
		
		
		
		//Sign IN Button
		buttonLogin = new JButton("Sign In");
		buttonLogin.setFont(new Font("Arial", Font.BOLD, 15));
		Image imgLogin = new ImageIcon(this.getClass().getResource("/Button.png")).getImage();
		buttonLogin.setIcon(new ImageIcon(imgLogin));
		buttonLogin.setBounds(292, 347, 224, 39);
		buttonLogin.setOpaque(false);
		buttonLogin.setContentAreaFilled(false);
		buttonLogin.setBorderPainted(false);
		buttonLogin.setHorizontalTextPosition(JButton.CENTER);
		buttonLogin.setVerticalTextPosition(JButton.CENTER);
		buttonLogin.setForeground(Color.WHITE);
		contentPane.add(buttonLogin);
		//Sign IN button END
		
		buttonCancel = new JButton("Cancel");
		//buttonCancel.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/cancel.PNG")));
		buttonCancel.setBounds(203, 235, 97, 25);
		//contentPane.add(buttonCancel);

		//Forgot Password Button
		btnForgotPassword = new JButton("FORGOT PASSWORD");
		btnForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnForgotPassword.setBounds(325, 399, 143, 25);
		btnForgotPassword.setForeground(Color.WHITE);
		btnForgotPassword.setOpaque(false);
		btnForgotPassword.setContentAreaFilled(false);
		btnForgotPassword.setBorderPainted(false);
		btnForgotPassword.setHorizontalTextPosition(JButton.CENTER);
		btnForgotPassword.setVerticalTextPosition(JButton.CENTER);
		contentPane.add(btnForgotPassword);
		//Forgot Password Button END

		buttonChangePassword = new JButton("Change Password");
		//buttonChangePassword.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/Password_protection_symbol_on_monitor_screen_24.png")));
		buttonChangePassword.setForeground(Color.BLACK);
		buttonChangePassword.setBounds(94, 295, 206, 25);
		//contentPane.add(buttonChangePassword);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
		//Send Button
		buttonSend = new JButton("Send");
		buttonSend.setFont(new Font("Arial", Font.BOLD, 15));
		Image buttSmall = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		buttonSend.setIcon(new ImageIcon(buttSmall));
		buttonSend.setBounds(338, 481, 120, 31);
		buttonSend.setOpaque(false);
		buttonSend.setContentAreaFilled(false);
		buttonSend.setBorderPainted(false);
		buttonSend.setHorizontalTextPosition(JButton.CENTER);
		buttonSend.setVerticalTextPosition(JButton.CENTER);
		buttonSend.setForeground(Color.WHITE);
		getContentPane().add(buttonSend);
		buttonSend.setVisible(false);
		//Send Button END
		
		
		
		//Background Image
		JLabel lblImageHere = new JLabel();
		lblImageHere.setBounds(0, 0, 800, 600);
		Image img = new ImageIcon(this.getClass().getResource("/bgWithLogo.png")).getImage();
		lblImageHere.setIcon(new ImageIcon(img));
		contentPane.add(lblImageHere);
		//Background Image END

	}
	// Action Listeners


	public JLabel getLblwarningMessage() {
		if(labelWarningMessage == null){
			labelWarningMessage = new JLabel("user name or password is wrong");
			labelWarningMessage.setForeground(Color.RED);
			labelWarningMessage.setBounds(10, 165, 200, 30);
			labelWarningMessage.setVisible(false);
		}
		return labelWarningMessage;
	}
	public void setWarningMessageVisibleTrue() {
		labelWarningMessage.setVisible(true);	
	}
	
	public void setWarningMessageVisibleTrue(String st) {
		labelWarningMessage.setText(st);
		labelWarningMessage.setForeground(Color.RED);
		labelWarningMessage.setBounds(10, 165, 245, 30);
		labelWarningMessage.setVisible(true);	
	}

	public void undisplayWarningMessage() {
		labelWarningMessage.setVisible(false);

	}
	
	public void displayEmailField()
	{
		
		FieldEmail.setVisible(true);
		emailField.setVisible(true);
		buttonSend.setVisible(true);
	}
	
	
	public void disposeEmailField()
	{
		
		FieldEmail.setVisible(false);
		emailField.setVisible(false);
		buttonSend.setVisible(false);
	}

	public void addLoginActionListener(ActionListener e)
	{
		buttonLogin.addActionListener(e);
	}

	public void addCancelActionListener(ActionListener e)
	{
		buttonCancel.addActionListener(e);
	}

	public void addbtnForgotYourPasswordActionListener(ActionListener e)
	{
		btnForgotPassword.addActionListener(e);
	}

	public void addbtnChangePasswordActionListener(ActionListener e)
	{
		buttonChangePassword.addActionListener(e);
	}
	// Getters

	public String getUserField() {
		return userField.getText();
	}

	public String getPasswordField() {
		return passwordField.getText();
	}
}
