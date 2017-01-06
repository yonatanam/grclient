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

import client.App;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindowGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton buttonHome;
	private UserMenu userMenu;

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

		/** Menu */
		userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
		Menu menu = new Menu(userMenu);
		menu.setBounds(10,11,165,550);
		contentPane.add(menu);	
		/** End menu */


		//HomeButton
		buttonHome = new JButton("");
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
		Image addbook = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		Image createnewcat = new ImageIcon(this.getClass().getResource("/Button.png")).getImage();	
		// --------------------- end home button --------------------------------//

		//This is the LOGO
		JLabel Logo = new JLabel();
		Logo.setBounds(306, 129, 139, 82);
		Image logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		Logo.setIcon(new ImageIcon(logo));
		contentPane.add(Logo);
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(10, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		contentPane.add(bg);
		setLocationRelativeTo(null);
		//panel.setOpaque(false);
		setVisible(true);
	} 

	public void addWindowListenerFromController(WindowListener e)
	{
		addWindowListener(e);
	}
}
