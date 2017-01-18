package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import client.App;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;

public class EditSubjectGUI extends JFrame {
	private UserMenu userMenu;
	private JTextField TextsubName;
	private JComboBox SubComboBox;
	private JComboBox categorycomboBox;
	private JList categorylist;
	private JLabel Name_Warning;
	private JLabel category_Warning;
	private JButton btnBack;
	private JButton btnChange;
	private JButton Removebtn;
	private DefaultListModel listModel;
	/**
	 * Create the application.
	 */
	public EditSubjectGUI() {
		
		getContentPane().setLayout(null);
        setSize(800, 600);
        setResizable(false);
 		setLocationRelativeTo(null);
 		setTitle("Edit Category");
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/** Menu */
        userMenu = App.client.menuSwitcher(this); //Gets the specific menu per user permission
 		Menu menu = new Menu(userMenu);
 		menu.setBounds(10,11,165,550);
 		getContentPane().add(menu);
 		
 		/**end menu*/
 	//---
 		JLabel lblChangeSubject = new JLabel("Edit Subject");
 		lblChangeSubject.setForeground(Color.LIGHT_GRAY);
		lblChangeSubject.setFont(new Font("Arial", Font.BOLD, 21));
		lblChangeSubject.setBounds(69, 11, 380, 29);
		getContentPane().add(lblChangeSubject);
		
	//-----
		JLabel lblSubjectToEdit = new JLabel("choose Subject to edit");
		lblSubjectToEdit.setForeground(Color.LIGHT_GRAY);
		lblSubjectToEdit.setFont(new Font("Arial", Font.BOLD, 16));
		lblSubjectToEdit.setBounds(212, 39, 268, 29);
		getContentPane().add(lblSubjectToEdit);
	//-----
		SubComboBox = new JComboBox();
		SubComboBox.setBounds(208, 73, 195, 22);
		getContentPane().add(SubComboBox);
	//-----
 		TextsubName = new JTextField();
 		TextsubName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				TextsubName.setText("");                   //Clear "Password String" on focus
			}
		});
 		TextsubName.setText("Enter subject name");
 		TextsubName.setFont(new Font("Tahoma", Font.PLAIN, 13));
 		TextsubName.setOpaque(false);   
 		TextsubName.setBorder(javax.swing.BorderFactory.createEmptyBorder());
 		TextsubName.setForeground(Color.white);
 		TextsubName.setBounds(212, 239, 206, 22);
 		getContentPane().add(TextsubName);
 		//------
 		listModel = new DefaultListModel();
 		
 		categorylist = new JList(listModel);
 		categorylist.setForeground(Color.RED);
 		categorylist.setCellRenderer(new TransparentListCellRenderer());
 		categorylist.setVisible(true);
 		categorylist.setOpaque(false);
 		categorylist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
 		categorylist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
 		categorylist.setVisibleRowCount(10);
		
 		JScrollPane scrollPane = new JScrollPane(categorylist);
 		scrollPane.setOpaque(false);
 		scrollPane.setBounds(212, 123, 152, 86);
 		scrollPane.getViewport().setOpaque(false);
 		
 		getContentPane().add(scrollPane);
 		
 		
 		
 		
 	//----
 		JLabel lblEnterSubjectName = new JLabel("Enter Subject's new name (for any change don't do anything) ");
 		lblEnterSubjectName.setForeground(Color.LIGHT_GRAY);
 		lblEnterSubjectName.setFont(new Font("Arial", Font.BOLD, 16));
 		lblEnterSubjectName.setBounds(207, 205, 475, 29);
		getContentPane().add(lblEnterSubjectName);
 		JLabel label_1 = new JLabel("");
		Image firstimg = new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_1.setIcon(new ImageIcon(firstimg));
		label_1.setBounds(208, 235, 224, 32);
		getContentPane().add(label_1);
		
 		
 		
 	//----	
		JLabel bininglabel = new JLabel("choose category to rebind(choose none for any change)");
		bininglabel.setForeground(Color.LIGHT_GRAY);
		bininglabel.setFont(new Font("Arial", Font.BOLD, 16));
		bininglabel.setBounds(207, 272, 475, 29);
		getContentPane().add(bininglabel);
		
		 categorycomboBox = new JComboBox();
		 categorycomboBox.setBounds(212, 297, 224, 22);
		getContentPane().add(categorycomboBox);
		//button image
		Image buttoncan = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
	//-----
		 btnChange = new JButton("Change");
		 btnChange.setIcon(new ImageIcon(buttoncan));
		 btnChange.setVerticalTextPosition(SwingConstants.CENTER);
		 btnChange.setOpaque(false);
		 btnChange.setHorizontalTextPosition(SwingConstants.CENTER);
		 btnChange.setContentAreaFilled(false);
		 btnChange.setBorderPainted(false);
		 btnChange.setFont(new Font("Arial", Font.BOLD, 12));
		 btnChange.setForeground(Color.WHITE);
		btnChange.setBounds(302, 345, 89, 23);
		getContentPane().add(btnChange);
	//------
		 btnBack = new JButton("Back");	
		 btnBack.setIcon(new ImageIcon(buttoncan));
		 btnBack.setVerticalTextPosition(SwingConstants.CENTER);
		 btnBack.setOpaque(false);
		 btnBack.setHorizontalTextPosition(SwingConstants.CENTER);
		 btnBack.setContentAreaFilled(false);
		 btnBack.setBorderPainted(false);
		 btnBack.setFont(new Font("Arial", Font.BOLD, 12));
		 btnBack.setForeground(Color.WHITE);
		btnBack.setBounds(203, 345, 89, 23);
		getContentPane().add(btnBack);
 	//------
		Removebtn = new JButton("remove");	
		Removebtn.setIcon(new ImageIcon(buttoncan));
		Removebtn.setVerticalTextPosition(SwingConstants.CENTER);
		Removebtn.setOpaque(false);
		Removebtn.setHorizontalTextPosition(SwingConstants.CENTER);
		Removebtn.setContentAreaFilled(false);
		Removebtn.setBorderPainted(false);
		Removebtn.setFont(new Font("Arial", Font.BOLD, 12));
		Removebtn.setForeground(Color.WHITE);
		Removebtn.setBounds(391, 153, 89, 23);
		getContentPane().add(Removebtn);
	//------	
		category_Warning=new JLabel("");
		category_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		category_Warning.setForeground(Color.RED);
		category_Warning.setBounds(374, 123, 308, 22);
		getContentPane().add(category_Warning);	
		
		Name_Warning = new JLabel("");
		Name_Warning.setFont(new Font("Arial", Font.BOLD, 15));
		Name_Warning.setForeground(Color.RED);
		Name_Warning.setBounds(442, 239, 213, 22);
		getContentPane().add(Name_Warning);	
		
		
		
		JLabel BackGround = new JLabel("");
		/**background*/
 		Image imgbg = new ImageIcon(this.getClass().getResource("/bgWithoutLogo.png")).getImage();
		
		JLabel lblTheSubjectExist = new JLabel("the subject exist in:");
		lblTheSubjectExist.setForeground(Color.LIGHT_GRAY);
		lblTheSubjectExist.setFont(new Font("Arial", Font.BOLD, 16));
		lblTheSubjectExist.setBounds(212, 95, 268, 29);
		getContentPane().add(lblTheSubjectExist);
		BackGround.setBounds(0, 0, 797, 565);
		BackGround.setIcon(new ImageIcon(imgbg));
		getContentPane().add(BackGround);
		
		
 		/**end background*/
		setVisible(true);
		
		
	}
	 class TransparentListCellRenderer extends DefaultListCellRenderer {

	    @Override
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        setOpaque(isSelected);
	        return this;
	    }

	}
	public void addBackButtonActionListener(ActionListener e)
	{
		btnBack.addActionListener(e);
	}
	public void addRemoveButtonActionListener(ActionListener e)
	{
		Removebtn.addActionListener(e);
	}
	public void addChangeButtonActionListener(ActionListener e)
	{
		btnChange.addActionListener(e);
	}
	public void AdditemComboBoxListener(ItemListener e)
	{
		SubComboBox.addItemListener(e);
	}
	public  JList getcategorylist()
	{
		return categorylist;	
		}
	public void AddSubjectComboBoxListener(ItemListener i){
		this.SubComboBox.addItemListener(i);
	}
	public JComboBox getSubComboBox() {
		return SubComboBox;
	}
	public JComboBox getCatComboBox() {
		return categorycomboBox;
	}
	public JTextField getTextsubName() {
		return TextsubName;
	}
	public JLabel getName_Warning() {
		return Name_Warning;
	}
	public JLabel getCategory_Warning() {
		return this.category_Warning;
	}
	public DefaultListModel getlistmodel()
	{
		return this.listModel;
	}
}
