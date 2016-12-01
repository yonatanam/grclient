package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.JList;
import javax.swing.JTree;

import java.awt.FlowLayout;

//import org.eclipse.wb.swing.FocusTraversalOnArray;

//import controllers.ExplorerController;

import java.awt.Component;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

//import models.Envelope;
import models.User;
//import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class MainWindowGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton buttonHome;
	private JButton buttonReadFromDB;
	private JButton buttonWriteToDB;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowGUI frame = new MainWindowGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/**
	 * Create the frame. This is the Home window.
	 */
	public MainWindowGUI() {

		setResizable(false);
		setTitle("Good Reading");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		buttonHome = new JButton("Home");
		buttonHome.setForeground(SystemColor.control);
		buttonHome.setBackground(SystemColor.activeCaptionBorder);
		buttonHome.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonHome.setBounds(10, 81, 252, 79);
		contentPane.add(buttonHome);
		
		buttonReadFromDB = new JButton("Read from Worker Table");
		buttonReadFromDB.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonReadFromDB.setBounds(268, 81, 252, 79);
		contentPane.add(buttonReadFromDB);
		
		buttonWriteToDB = new JButton("Write to Worker Table");
		buttonWriteToDB.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonWriteToDB.setBounds(528, 81, 252, 79);
		contentPane.add(buttonWriteToDB);
		
		JLabel lblIPersonalBox = new JLabel("Some text here");
		lblIPersonalBox.setForeground(new Color(51, 51, 255));
		lblIPersonalBox.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblIPersonalBox.setBounds(315, 169, 194, 63);
		contentPane.add(lblIPersonalBox);
		
		panel = new JPanel();
		panel.setBounds(7, 222, 780, 320);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
			setVisible(true);
		
	} 
	

	// Action Listeners
	
	public void addButtonHomeActionListener(ActionListener e)
	{
		buttonHome.addActionListener(e);
	}
	
	public void addButtonReadFromWorkerActionListener(ActionListener e)
	{
		buttonReadFromDB.addActionListener(e);
	}
	
	public void addButtonWriteToWorkerActionListener(ActionListener e)
	{
		buttonWriteToDB.addActionListener(e);
	}
}
