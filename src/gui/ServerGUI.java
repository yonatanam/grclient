package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.ServerController;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class ServerGUI extends JFrame {
	private JTextField textField;
	private JTextField UserNameText;
	private JTextField PasswordText;
	private JTextField DataBaseText;
	private JTextField HostText;
	private JButton CancelButton;
	private JButton ConnectButton;
	private ServerController servercon;
	public static ServerGUI serverGUI;

	

	/**
	 * Create the frame.
	 */
	public ServerGUI()
	{
		
		
		serverGUI=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(32, 32, 66, 19);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(32, 64, 66, 19);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DataBase Name");
		lblNewLabel_2.setBounds(32, 96, 96, 19);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(32, 128, 66, 14);
		getContentPane().add(lblHost);
		
		UserNameText = new JTextField();
		UserNameText.setText("grproj");
		UserNameText.setBounds(131, 31, 86, 20);
		getContentPane().add(UserNameText);
		UserNameText.setColumns(10);
		
		PasswordText = new JTextField();
		PasswordText.setText("");
		PasswordText.setBounds(131, 63, 86, 20);
		getContentPane().add(PasswordText);
		PasswordText.setColumns(10);
		
		DataBaseText = new JTextField();
		DataBaseText.setText("grproj");
		DataBaseText.setBounds(131, 95, 86, 20);
		getContentPane().add(DataBaseText);
		DataBaseText.setColumns(10);
		
		HostText = new JTextField();
		HostText.setText("localhost");
		HostText.setBounds(131, 125, 86, 20);
		getContentPane().add(HostText);
		HostText.setColumns(10);
		
		 ConnectButton = new JButton("connect");
		 ConnectButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {		 		
		 		servercon=new ServerController();
		 		/*servercon.setDbName(DataBaseText.getText());
		 		servercon.setUserName(UserNameText.getText());
		 		servercon.setHostName(HostText.getText());
		 		servercon.setPassword1(PasswordText.getText());*/
		 		servercon.setDbName("grproj");
		 		servercon.setUserName("grproj");
		 		servercon.setHostName("localhost");
		 		ServerLogGUI serverLogGUI = new ServerLogGUI(servercon);
		 		ServerGUI.serverGUI.dispose();
		 		if(servercon.Connect()) serverLogGUI.addDisconnectButton();			
		 		else serverLogGUI.addBackButton();
		 		serverLogGUI.setVisible(true);
		 	}
		 });
		ConnectButton.setBounds(52, 208, 89, 23);
		getContentPane().add(ConnectButton);
		
		CancelButton = new JButton("cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerGUI.serverGUI.dispose();
			}
		});
		CancelButton.setBounds(204, 208, 89, 23);
		getContentPane().add(CancelButton);		
		textField = new JTextField();
		textField.setColumns(10);
		setVisible(true);
	}
	

	
}
