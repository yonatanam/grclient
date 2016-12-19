package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class ReadWorkerGUI extends JFrame {


	private JButton BackButton;
	private JButton ReadButton;
	private JTable workerData;
	private JScrollPane scrollPane;
	private JTextField nullField;
	


	public ReadWorkerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the 
	 */
	private void initialize() {
		
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblReadFromWorker = new JLabel("Read From Worker Database");
		lblReadFromWorker.setForeground(Color.WHITE);
		lblReadFromWorker.setFont(new Font("Arial", Font.PLAIN, 20));
		lblReadFromWorker.setBounds(39, 17, 271, 30);
		getContentPane().add(lblReadFromWorker);
		
		nullField = new JTextField();
		nullField.setOpaque(false);     
		getContentPane().add(nullField);
		
		//Sign IN Button
		ReadButton = new JButton("Read");
		ReadButton.setFont(new Font("Arial", Font.BOLD, 15));
		Image imgLogin = new ImageIcon(this.getClass().getResource("/buttSmall.png")).getImage();
		ReadButton.setIcon(new ImageIcon(imgLogin));
		ReadButton.setBounds(309, 19, 120, 31);
		ReadButton.setOpaque(false);
		ReadButton.setContentAreaFilled(false);
		ReadButton.setBorderPainted(false);
		ReadButton.setHorizontalTextPosition(JButton.CENTER);
		ReadButton.setVerticalTextPosition(JButton.CENTER);
		ReadButton.setForeground(Color.WHITE);
		getContentPane().add(ReadButton);
		//Sign IN button END
		
		/*btnBack = new JButton("Back");
		btnBack.setBounds(28, 499, 89, 23);
		getContentPane().add(btnBack);*/
		
		//Back button
		BackButton = new JButton("Back");
		BackButton.setFont(new Font("Arial", Font.BOLD, 15));
		BackButton.setIcon(new ImageIcon(imgLogin));
		BackButton.setBounds(28, 499, 106, 41);
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setHorizontalTextPosition(JButton.CENTER);
		BackButton.setVerticalTextPosition(JButton.CENTER);
		BackButton.setForeground(Color.WHITE);
		getContentPane().add(BackButton);
		//Back button END
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 63, 713, 425);
		getContentPane().add(scrollPane);
		
		workerData = new JTable();
		scrollPane.setViewportView(workerData);
		workerData.setDefaultEditor(Object.class, null); //prevents from editing the fields but allows row selection

		setLocationRelativeTo(null);
		setVisible(true);
		
		
		//Background
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 800, 600);
		Image img1 = new ImageIcon(this.getClass().getResource("/bgWithOutLogo.png")).getImage();
		bg.setIcon(new ImageIcon(img1));
		getContentPane().add(bg);
		//BackGround END
	}


	//Action Listeners
	public void addButtonReadFromWorkerActionListener(ActionListener e)
	{
		ReadButton.addActionListener(e);
	}
	public void addButtonBackFromReadFromWorkerActionListener(ActionListener e)
	{
		BackButton.addActionListener(e);
	}
	
//Setters and Getters
	
	public JTable getWorkerData() {
		return workerData;
	}

	public void setWorkerData(JTable data) {
		this.workerData = data;
	}
	
	public void populateTable(Vector<Object> columnNames, Vector<Object> data)
	{
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		workerData.setModel(model);
	}
	

}
