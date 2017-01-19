package gui;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import client.App;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class ReportsGUI extends JFrame {
	
	private UserMenu userMenu;
	private JButton btnUserReport;
	
	public ReportsGUI()
	{	
		setResizable(false);
		setSize(800, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		getContentPane().add(menu);	
		/** End menu */
		
		
		btnUserReport = new JButton("User Orders");
		btnUserReport.setBounds(381, 275, 140, 119);
		btnUserReport.setFont(new Font("Arial", Font.BOLD, 15));
		btnUserReport.setForeground(Color.WHITE);
		btnUserReport.setHorizontalTextPosition(JButton.CENTER);
		btnUserReport.setVerticalTextPosition(JButton.CENTER);
		btnUserReport.setOpaque(false);
		btnUserReport.setContentAreaFilled(false);
		btnUserReport.setBorderPainted(false);
		Image firthimg = new ImageIcon(this.getClass().getResource("/WriteToDB.png")).getImage();	
		btnUserReport.setIcon(new ImageIcon(firthimg));
		getContentPane().add(btnUserReport);
		
		
		JLabel BackGround = new JLabel("");
		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithLogo.png")).getImage();
		BackGround.setIcon(new ImageIcon(imgbg));
		BackGround.setBounds(0, 0, 800, 600);
		getContentPane().add(BackGround);

		setVisible(true);
	}
	
	
	
	
	// Adding action listeners to all buttons in manage books gui
	
	
	public void AddButtonUserReportActionListener(ActionListener e)
	{
		btnUserReport.addActionListener(e);
	}
	

	
}
