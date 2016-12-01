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
	private JButton btnHome;
	private JButton btnManageFiles;
	private JButton btnManageGroups;
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
		//setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowGUI.class.getResource("/images/logo2.png")));
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
		//lblLogo.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/images/bar.png")));
		lblLogo.setBounds(0, 0, 794, 79);
		contentPane.add(lblLogo);
		
		btnHome = new JButton("Home");
		//btnHome.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/images/School_Building_with_Flag_32.png")));
		btnHome.setForeground(SystemColor.control);
		btnHome.setBackground(SystemColor.activeCaptionBorder);
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnHome.setBounds(10, 81, 252, 79);
		contentPane.add(btnHome);
		
		btnManageFiles = new JButton("Button 1");
		//btnManageFiles.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/images/Three_Document_Folders_32.png")));
		btnManageFiles.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnManageFiles.setBounds(268, 81, 252, 79);
		contentPane.add(btnManageFiles);
		
		btnManageGroups = new JButton("Button 2");
		//btnManageGroups.setIcon(new ImageIcon(MainWindowGUI.class.getResource("/images/College_Classroom_32.png")));
		btnManageGroups.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnManageGroups.setBounds(528, 81, 252, 79);
		contentPane.add(btnManageGroups);
		
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
	
	public void addbtnHomeActionListener(ActionListener e)
	{
		btnHome.addActionListener(e);
	}
	
	public void addbtnManageFilesActionListener(ActionListener e)
	{
		btnManageFiles.addActionListener(e);
	}
	
	public void addbtnManageGroupsActionListener(ActionListener e)
	{
		btnManageGroups.addActionListener(e);
	}
}
