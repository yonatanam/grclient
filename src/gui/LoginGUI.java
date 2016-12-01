package gui;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Toolkit;


public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userField;
	private JTextField passwordField;
	private JButton buttonCancel;
	private JButton buttonLogin;
	private JButton buttonForgotPassword;
	private JLabel labelWarningMessage = null;
	private JButton buttonChangePassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(LoginGUI.class.getResource("/images/logo2.png")));
		setTitle("Good Reading - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblImageHere = new JLabel("Image Here");
		lblImageHere.setBounds(120, 0, 156, 150);
		//lblImageHere.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/logo2.png")));
		contentPane.add(lblImageHere);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 16));
		lblUsername.setBounds(76, 146, 87, 16);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
		lblPassword.setBounds(76, 177, 87, 16);
		contentPane.add(lblPassword);

		userField = new JTextField();
		userField.setBounds(163, 144, 139, 22);
		contentPane.add(userField);
		userField.setColumns(10);

		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(163, 175, 139, 22);
		contentPane.add(passwordField);

		buttonLogin = new JButton("Login");
		//buttonLogin.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/login.PNG")));
		buttonLogin.setBounds(94, 235, 97, 25);
		contentPane.add(buttonLogin);

		buttonCancel = new JButton("Cancel");
		//buttonCancel.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/cancel.PNG")));
		buttonCancel.setBounds(203, 235, 97, 25);
		contentPane.add(buttonCancel);

		buttonForgotPassword = new JButton("Forgot your password?");
		//buttonForgotPassword.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/update.png")));
		buttonForgotPassword.setForeground(Color.RED);
		buttonForgotPassword.setBounds(94, 265, 206, 25);
		contentPane.add(buttonForgotPassword);

		buttonChangePassword = new JButton("Change Password");
		//buttonChangePassword.setIcon(new ImageIcon(LoginGUI.class.getResource("/images/Password_protection_symbol_on_monitor_screen_24.png")));
		buttonChangePassword.setForeground(Color.BLACK);
		buttonChangePassword.setBounds(94, 295, 206, 25);
		contentPane.add(buttonChangePassword);
		setLocationRelativeTo(null);
		setVisible(true);

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
		buttonForgotPassword.addActionListener(e);
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
