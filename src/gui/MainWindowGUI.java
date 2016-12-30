package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

//import org.eclipse.wb.swing.FocusTraversalOnArray;

//import controllers.ExplorerController;

import java.awt.Component;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.Image;
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
import java.awt.event.ActionEvent;
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
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class MainWindowGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton buttonHome;
	private JButton buttonReadFromDB;
	private JButton buttonWriteToDB;
	private JTextField nullField;
	private JButton publishReviewButton;

	
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
		
		
		
		nullField = new JTextField();
		nullField.setOpaque(false);     
		contentPane.add(nullField);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 0, 0, 0);
		contentPane.add(label);
		
		/*buttonHome = new JButton("Home");
		buttonHome.setForeground(SystemColor.control);
		buttonHome.setBackground(SystemColor.activeCaptionBorder);
		buttonHome.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonHome.setBounds(10, 81, 252, 79);
		contentPane.add(buttonHome);*/
		
		/*buttonReadFromDB = new JButton("Read from Worker Table");
		buttonReadFromDB.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonReadFromDB.setBounds(268, 81, 252, 79);
		contentPane.add(buttonReadFromDB);
		
		buttonWriteToDB = new JButton("Write to Worker Table");
		buttonWriteToDB.setFont(new Font("Tahoma", Font.BOLD, 15));
		buttonWriteToDB.setBounds(528, 81, 252, 79);
		contentPane.add(buttonWriteToDB);*/
		Image WriteToDB = new ImageIcon(this.getClass().getResource("/WriteToDB.png")).getImage();
		//buttonWrite To Database END
		
		
		//ButtonRead From Database
		buttonReadFromDB = new JButton("");
		buttonReadFromDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonReadFromDB.setFont(new Font("Arial", Font.BOLD, 15));
		Image ReadFromDB = new ImageIcon(this.getClass().getResource("/RetInfo.png")).getImage();
		buttonReadFromDB.setIcon(new ImageIcon(ReadFromDB));
		buttonReadFromDB.setBounds(363, 358, 194, 151);
		buttonReadFromDB.setOpaque(false);
		buttonReadFromDB.setContentAreaFilled(false);
		buttonReadFromDB.setBorderPainted(false);
		buttonReadFromDB.setHorizontalTextPosition(JButton.CENTER);
		buttonReadFromDB.setVerticalTextPosition(JButton.CENTER);
		buttonReadFromDB.setForeground(Color.WHITE);
		contentPane.add(buttonReadFromDB);
		
		//buttonWrite To Database
		buttonWriteToDB = new JButton("");
		buttonWriteToDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonWriteToDB.setFont(new Font("Arial", Font.BOLD, 15));
		buttonWriteToDB.setIcon(new ImageIcon(WriteToDB));
		buttonWriteToDB.setBounds(207, 248, 194, 110);
		buttonWriteToDB.setOpaque(false);
		buttonWriteToDB.setContentAreaFilled(false);
		buttonWriteToDB.setBorderPainted(false);
		buttonWriteToDB.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonWriteToDB.setVerticalTextPosition(SwingConstants.CENTER);
		buttonWriteToDB.setForeground(Color.WHITE);
		Image UpdateInfo = new ImageIcon(this.getClass().getResource("/UpdateInfo.png")).getImage();
		buttonWriteToDB.setIcon(new ImageIcon(UpdateInfo));
		contentPane.add(buttonWriteToDB);
		
		//ButtonRead From Database END
		
		//HomeButton
		buttonHome = new JButton("");
		buttonHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonHome.setFont(new Font("Arial", Font.BOLD, 15));
		Image Home = new ImageIcon(this.getClass().getResource("/HomeButton.png")).getImage();
		buttonHome.setIcon(new ImageIcon(Home));
		buttonHome.setBounds(372, 238, 176, 131);
		buttonHome.setOpaque(false);
		buttonHome.setContentAreaFilled(false);
		buttonHome.setBorderPainted(false);
		buttonHome.setHorizontalTextPosition(JButton.CENTER);
		buttonHome.setVerticalTextPosition(JButton.CENTER);
		buttonHome.setForeground(Color.WHITE);
		contentPane.add(buttonHome);
		//HomeButton END
		
		
		//This is the LOGO
		JLabel Logo = new JLabel();
		Logo.setBounds(306, 129, 139, 82);
		Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		Logo.setIcon(new ImageIcon(logo));
		contentPane.add(Logo);
		//LOGO END
		
		/* Publish review button*/
		publishReviewButton = new JButton("Publish Review");
		publishReviewButton.setVerticalTextPosition(SwingConstants.CENTER);
		publishReviewButton.setIcon(new ImageIcon(WriteToDB));
		publishReviewButton.setOpaque(false);
		publishReviewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		publishReviewButton.setForeground(Color.WHITE);
		publishReviewButton.setFont(new Font("Arial", Font.BOLD, 15));
		publishReviewButton.setContentAreaFilled(false);
		publishReviewButton.setBorderPainted(false);
		publishReviewButton.setBounds(207, 380, 194, 110);
		contentPane.add(publishReviewButton);
		/* End publish review button */
		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		try {	
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		}
		catch (Exception ex)
		{
		System.out.println("FAILED LOGO!"+ex);
		}
		contentPane.add(bg);
		
		
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
	public void addButtonPublishReviewActionListener(ActionListener e)
	{
		publishReviewButton.addActionListener(e);
	}
}
