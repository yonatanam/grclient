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
	private JButton buttonReadFromDB;
	private JButton buttonWriteToDB;
	private JButton buttonAddBook;
	private JButton buttonCreateCategory;
	private JTextField nullField;
	private JButton publishReviewButton;
	private JButton btnSettlepayment;
	private JButton btnSearchReview;

	
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
		
		
		//------ button for adding a book (for manager) -------//
		
		buttonAddBook = new JButton("Add book");
		buttonAddBook.setFont(new Font("Arial", Font.BOLD, 14));
		Image addbook = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		buttonAddBook.setIcon(new ImageIcon(addbook));
		buttonAddBook.setBounds(92, 287, 113, 25);
		buttonAddBook.setOpaque(false);
		buttonAddBook.setContentAreaFilled(false);
		buttonAddBook.setBorderPainted(false);
		buttonAddBook.setHorizontalTextPosition(JButton.CENTER);
		buttonAddBook.setVerticalTextPosition(JButton.CENTER);
		buttonAddBook.setForeground(Color.WHITE);
		//if(App.client.getCurrentUser())
		contentPane.add(buttonAddBook);
		
		
		
		// ----------------- end button ------------------------//
		
		// --------------- Creating new category --------------------//
		
		buttonCreateCategory = new JButton("Create new category");
		Image createnewcat = new ImageIcon(this.getClass().getResource("/Button.png")).getImage();
		buttonCreateCategory.setIcon(new ImageIcon(createnewcat));
		buttonCreateCategory.setVerticalTextPosition(SwingConstants.CENTER);
		buttonCreateCategory.setOpaque(false);
		buttonCreateCategory.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonCreateCategory.setForeground(Color.WHITE);
		buttonCreateCategory.setFont(new Font("Arial", Font.BOLD, 14));
		buttonCreateCategory.setContentAreaFilled(false);
		buttonCreateCategory.setBorderPainted(false);
		buttonCreateCategory.setBounds(540, 259, 228, 41);
		contentPane.add(buttonCreateCategory);
		
		// --------------------- end button --------------------------------//
		
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
		publishReviewButton.setBounds(241, 381, 130, 110);
		contentPane.add(publishReviewButton);
		/* End publish review button */
		
		/**----------------------------Settle Payment Button----------------------------*/
		btnSettlepayment = new JButton("SettlePayment");
		btnSettlepayment.setBounds(85, 367, 120, 23);
		contentPane.add(btnSettlepayment);
		/**----------------------------End Settle Payment Button----------------------------*/

		/**----------------------------Search Review Button----------------------------*/
		btnSearchReview = new JButton("Search review");
		btnSearchReview.setFont(new Font("Arial", Font.BOLD, 14));
		Image imgBtnSearchReview = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		btnSearchReview.setIcon(new ImageIcon(imgBtnSearchReview));
		btnSearchReview.setBounds(79, 425, 139, 25);
		btnSearchReview.setOpaque(false);
		btnSearchReview.setContentAreaFilled(false);
		btnSearchReview.setBorderPainted(false);
		btnSearchReview.setHorizontalTextPosition(JButton.CENTER);
		btnSearchReview.setVerticalTextPosition(JButton.CENTER);
		btnSearchReview.setForeground(Color.WHITE);
		contentPane.add(btnSearchReview);
		/**----------------------------End Search Review Button----------------------------*/
		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(10, 0, 800, 600);
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
		//panel.setOpaque(false);
		setVisible(true);
	
		
	} 
	

	// Action Listeners
	
	public void addButtonAddBookActionListener(ActionListener e)
	{
		buttonAddBook.addActionListener(e);
	}
	
	public void addButtonCreateNewCategoryActionListener(ActionListener e)
	{
		buttonCreateCategory.addActionListener(e);
	}
	
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
	public void addBtnSettlePaymentActionListener(ActionListener e)
	{
		btnSettlepayment.addActionListener(e);
	}	

	public void addBtnSearchReviewActionListener(ActionListener e)
	{
		btnSearchReview.addActionListener(e);
	}	
	
	public void addWindowListenerFromController(WindowListener e)
	{
		addWindowListener(e);
	}
}
