package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import server.ServerController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;

public class ServerLogGUI extends JFrame{
	
	
	private JButton SaveButton;
	private JButton DisconnectButton;
	private static JTextArea textArea;
	private ServerController servercon;
	private JButton BackButton;
	private ServerLogGUI loggui;
	private JButton closeButton;
	public ServerLogGUI(ServerController servercon) {
		loggui=this;
		this.servercon=servercon;
		setBounds(0, 0, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		 closeButton = new JButton("close");
		 closeButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		servercon.disconnect();
		 		servercon.SaveLog(textArea);
				loggui.dispose();
		 	}
		 });
		closeButton.setBounds(465, 531, 89, 23);
		getContentPane().add(closeButton);
		SaveButton = new JButton("Save Log");
		SaveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				servercon.SaveLog( textArea);
			}
		});
		SaveButton.setBounds(42, 531, 89, 23);
		getContentPane().add(SaveButton);
		
		DisconnectButton = new JButton("Disconnect");
		DisconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				servercon.disconnect();
				move();
				
			}
		});
		DisconnectButton.setBounds(193, 531, 114, 23);
		
		
		textArea = new JTextArea();
		//textArea.setBounds(10, 11, 750, 515);
		//getContentPane().add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 11, 752, 509);
		getContentPane().add(scrollPane);
		
		
		BackButton = new JButton("Back");
		BackButton.setBounds(193, 531, 114, 23);
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				move();
				
			}
		});
		//setVisible(true);
	}
	public  void addDisconnectButton()
	{
		this.getContentPane().add(DisconnectButton);	
	}
	public  void addBackButton()
	{
		this.getContentPane().add(BackButton);	
	}
	public static void Print(String msg)
	{
		 textArea.append(msg+"\n");	
	}
	private void move()
	{
		servercon.SaveLog(textArea);
		loggui.dispose();
		new ServerGUI();
	}
}
